package Domini;

import java.util.*;


public class Relaciones {
    private void cout(String str) {
        System.out.println(str);
    }
    private class NodeC {
        private ArrayList<RelacionSimple> eventos;
        public NodeC(RelacionSimple r) {
            eventos = new ArrayList<RelacionSimple>();
            eventos.add(0,r);
        }
        public Congresista obtCongresista() {
            return eventos.get(0).obtCongresista();
        }
        public boolean esVacio() {
            return eventos.size() == 0;
        }
        public String obtenerIDCongresista() {
            return eventos.get(0).obtCongresista().obtID();
        }
        public void agregarRelacion(RelacionSimple r) throws Exception {
            boolean found = false;
            int i;
            for (i = 0; i < eventos.size(); ++i) {
                if (eventos.get(i).obtEvento().ID().equals(r.obtEvento().ID())) {
                    //throw new Exception("Ya existe la RelacionSimple: "+r.toString());
                    found = true;
                }
            }
            if (!found) eventos.add(i, r);
            //else cout("ya estaba");
        }
        public void eliminarRelacion(RelacionSimple r) throws Exception {
            for (int i = 0; i < eventos.size(); ++i) {
                if (eventos.get(i).obtEvento().ID().equals(r.obtEvento().ID())) {
                    eventos.remove(i);
                    return;
                }
            }
            throw new Exception("No existe RelacionSimple");
        }
        public boolean existeRelacion(RelacionSimple r) {
            for (int i = 0; i < eventos.size(); ++i) {
                if (eventos.get(i).obtEvento().ID().equals(r.obtEvento().ID())) {
                    return true;
                }
            }
            return false;
        }
        //cuidado no es deep copy
        public ArrayList<RelacionSimple> obtenerRelaciones() {
            return eventos;
        }
        public ArrayList<Evento> obtenerEventos() {
            ArrayList<Evento> aux = new ArrayList<Evento>();
            for (int i = 0; i < eventos.size(); ++i) {
                aux.add(i,eventos.get(i).obtEvento());
            }
            return aux;
        }
    }
    private class NodeE {
        ArrayList<RelacionSimple> congresistas;
        public NodeE(RelacionSimple r) {
            congresistas = new ArrayList<RelacionSimple>();
            congresistas.add(0,r);
        }
        public Evento obtEvento() {
            return congresistas.get(0).obtEvento();
        }
        public boolean esVacio() {
            return congresistas.size() == 0;
        }
        public void agregarRelacion(RelacionSimple r) throws Exception {
            boolean found = false;
            int i;
            for (i = 0; i < congresistas.size(); ++i) {
                if (congresistas.get(i).obtCongresista().obtID().equals(r.obtCongresista().obtID())) {
                    found = true;
                    // throw new Exception("Ya existe la RelacionSimple");
                }
            }
            if (!found) congresistas.add(i, r);
            //else cout("ya estaba");
        }

        public void eliminarRelacion(RelacionSimple r) throws Exception {
            for (int i = 0; i < congresistas.size(); ++i) {
                if (congresistas.get(i).obtCongresista().obtID().equals(r.obtCongresista().obtID())) {
                    congresistas.remove(i);
                    return;
                }
            }
            throw new Exception("No existe RelacionSimple");
        }
        //cuidado no es deep copy
        public ArrayList<RelacionSimple> obtenerRelaciones() {
            return congresistas;
        }
        public ArrayList<Congresista> obtenerCongresistas() {
            ArrayList<Congresista> aux = new ArrayList<Congresista>();
            for (int i = 0; i < congresistas.size(); ++i) {
                aux.add(i,congresistas.get(i).obtCongresista());
            }
            return aux;
        }
    }
    /*
    private class RelacionesCompuestas {
        private ArrayList<RelacionCompuestaAnd> AND;
        private ArrayList<RelacionCompuestaOr> OR;
        private ArrayList<RelacionCompuestaNot> NOT;
        public RelacionesCompuestas() {
            AND = new ArrayList<RelacionCompuestaAnd>();
            OR = new ArrayList<RelacionCompuestaOr>();
            NOT = new ArrayList<RelacionCompuestaNot>();
        }
        public void agregarOR(RelacionCompuestaOr aux) {
            OR.add(aux);
        }
        public void agregarNOT(RelacionCompuestaNot aux) {
            NOT.add(aux);
        }
        public void agregarAND(RelacionCompuestaAnd aux) {
            AND.add(aux);
        }
        public void eliminarOR(RelacionCompuestaOr aux) {
            OR.remove(aux);
        }
        public void eliminarNOT(RelacionCompuestaNot aux) {
            NOT.remove(aux);
        }
        public void eliminarAND(RelacionCompuestaAnd aux) {
            AND.remove(aux);
        }
    }*/
    private TST<NodeC> congresistas;
    private TST<NodeE> eventos;
    //private RelacionesCompuestas RC;

    private ArrayList<RelacionCompuesta> conjuntos;
    private ArrayList<RelacionCompuesta> compuestas;

    public Relaciones() {
        congresistas = new TST<NodeC>();
        eventos = new TST<NodeE>();
        //RC = new RelacionesCompuestas();
        conjuntos = new ArrayList<RelacionCompuesta>();
        compuestas = new ArrayList<RelacionCompuesta>();
    }

    public boolean esVacioSimples(){
        return congresistas.esVacio();
    }

    public int sizeSimples() throws Exception {
        return obtTodasLasRelaciones().size();
    }

    public void agregarRelacionSimple(RelacionSimple r) throws Exception {
        NodeC aux = new NodeC(r);
        if (!congresistas.existe(r.obtCongresista().obtID())) {
            congresistas.insertar(r.obtCongresista().obtID(),aux);
        }
        else {
            aux = congresistas.obtener(r.obtCongresista().obtID());
            aux.agregarRelacion(r);
        }
        NodeE aux2 = new NodeE(r);
        if (!eventos.existe(r.obtEvento().ID())) {
            eventos.insertar(r.obtEvento().ID(),aux2);
        }
        else {
            aux2 = eventos.obtener(r.obtEvento().ID());
            aux2.agregarRelacion(r);
        }
    }
    public boolean existeRelacionSimple(RelacionSimple r) throws Exception {
        if (congresistas.existe(r.obtCongresista().obtID()))
            return congresistas.obtener(r.obtCongresista().obtID()).existeRelacion(r);
        return false;
    }
    public boolean tieneRelacionesSimples(Congresista c) throws Exception {
        ArrayList<String> sc = congresistas.consultarClaves();
        return sc.contains(c.obtID());
    }
    public boolean tieneRelacionesSimples(Evento e) throws Exception {
        ArrayList<String> se = eventos.consultarClaves();
        return se.contains(e.ID());
    }
    public void eliminarRelacionSimple(RelacionSimple r) throws Exception {
        NodeC aux = congresistas.obtener(r.obtCongresista().obtID());
        aux.eliminarRelacion(r);
        if (aux.esVacio()) congresistas.borrar(r.obtCongresista().obtID());
        NodeE aux2 = eventos.obtener(r.obtEvento().ID());
        aux2.eliminarRelacion(r);
        if (aux2.esVacio()) eventos.borrar(r.obtEvento().ID());
    }
    public void eliminarRelacionesSimples(Congresista c) throws Exception {
        ArrayList<RelacionSimple> r = new ArrayList<RelacionSimple>(obtRelaciones(c));
        for(RelacionSimple rs : r) eliminarRelacionSimple(rs);
    }
    public void eliminarRelacionesSimples(Evento e) throws Exception {
        ArrayList<RelacionSimple> r = new ArrayList<RelacionSimple>(obtRelaciones(e));
        for(RelacionSimple rs : r) eliminarRelacionSimple(rs);
    }
    public void eliminarRelaciones(){
        congresistas.vaciar();
        eventos.vaciar();
        conjuntos.clear();
        compuestas.clear();
    }
    public void eliminarRelacionesSimples(){
        congresistas.vaciar();
        eventos.vaciar();
    }
    public void eliminarRelacionesCompuestas(){
        conjuntos.clear();
        compuestas.clear();
    }
    public void eliminarCongresistaCompuestas(Congresista c) throws Exception {
        for(int i=0; i<conjuntos.size(); ++i) {
            RelacionCompuesta rc = conjuntos.get(i);
            ArrayList<Congresista> a = rc.obtConjunto();
            if (a.contains(c)){
                a.remove(c);
                if (a.isEmpty()){
                    deshacerCompuestaConj(rc.obtId());
                    conjuntos.remove(i);
                    --i;
                }
            }
        }
    }
    public void eliminarEventoCompuestas(Evento e) throws Exception {
        for(int i=0; i<conjuntos.size(); ++i) {
            RelacionCompuesta rc = conjuntos.get(i);
            if (rc.obtEvento().equals(e)){
                deshacerCompuestaConj(rc.obtId());
                conjuntos.remove(i);
                --i;
            }
        }
    }
    public ArrayList<Evento> obtEventos(Congresista c) throws Exception {
        return congresistas.obtener(c.obtID()).obtenerEventos();
    }
    public ArrayList<Congresista> obtCongresistas(Evento e) throws Exception {
        return eventos.obtener(e.ID()).obtenerCongresistas();
    }
    public ArrayList<RelacionSimple> obtRelaciones(Congresista c) throws Exception {
        return congresistas.obtener(c.obtID()).obtenerRelaciones();
    }
    public ArrayList<RelacionSimple> obtRelaciones(Evento e) throws Exception {
        return eventos.obtener(e.ID()).obtenerRelaciones();
    }
    public ArrayList<Evento> obtEventos() {
        List<NodeE> array = eventos.consultarObjetos();
        ArrayList<Evento> aux = new ArrayList<Evento>();
        ListIterator<NodeE> it = array.listIterator();
        while (it.hasNext()) {
            aux.add(it.next().obtEvento());
        }
        return aux;
    }
    public ArrayList<Congresista> obtCongresistas() {
        List<NodeC> array = congresistas.consultarObjetos();
        ArrayList<Congresista> aux = new ArrayList<Congresista>();
        ListIterator<NodeC> it = array.listIterator();
        while (it.hasNext()) {
            aux.add(it.next().obtCongresista());
        }
        return aux;
    }
    public ArrayList<RelacionSimple> obtTodasLasRelaciones() throws Exception {
        ArrayList<Congresista> aux = obtCongresistas();
        ArrayList<RelacionSimple> array = new ArrayList<RelacionSimple>();
        for (int i = 0; i < aux.size(); ++i) {
            array.addAll(congresistas.obtener(aux.get(i).obtID()).obtenerRelaciones());
        }
        return array;
    }
    public void modEvento(String id, String new_id) throws Exception {
        NodeE ne = eventos.obtener(id);
        eventos.borrar(id);
        eventos.insertar(new_id,ne);
    }
    public void modCongresista(String id, String new_id) throws Exception {
        NodeC nc = congresistas.obtener(id);
        congresistas.borrar(id);
        congresistas.insertar(new_id,nc);
    }

    public int sizeConjuntos(){
        return conjuntos.size();
    }

    public void agregarConjunto(RCConjunto conj){
        conjuntos.add(conj);
        compuestas.add(conj);
    }

    public ArrayList<RelacionCompuesta> obtConjuntos(){
        return conjuntos;
    }


    public void eliminarConjunto(int id) throws Exception {
        for(int i=0; i<conjuntos.size(); ++i){
            if(conjuntos.get(i).obtId()==id){
                conjuntos.remove(i);
                deshacerCompuestaConj(id);
                return;
            }
        }
    }

    public int sizeCompuestas(){
        return compuestas.size();
    }

    public void agregarCompuesta(RelacionCompuesta comp){
        compuestas.add(comp);
    }

    public ArrayList<RelacionCompuesta> obtCompuestas(){
        return compuestas;
    }

    public RelacionCompuesta obtCompuesta(int id) throws Exception {
        for(int i=0; i<compuestas.size(); ++i)
            if(compuestas.get(i).obtId()==id)
                return compuestas.get(i);
        throw new Exception("No existe la relación compuesta con id "+id+".");
    }

    public String consultarDescripcionComp(int id){
        for(RelacionCompuesta r : compuestas)
            if(r.obtId()==id) return r.descripcion();
        return "";
    }

    public ArrayList<Congresista> obtCongresistasComp(int id){
        for(RelacionCompuesta r : compuestas)
            if(r.obtId()==id) return r.obtConjunto();
        return new ArrayList<Congresista>();
    }

    public ArrayList<ArrayList<Congresista>> obtConjuntosComp(){
        ArrayList<ArrayList<Congresista>> alc = new ArrayList<ArrayList<Congresista>>();
        for(RelacionCompuesta r : compuestas) alc.add(r.obtConjunto());
        return alc;
    }

    public void eliminarCompuesta(int id){
        for(int i=0; i<compuestas.size(); ++i){
            if(compuestas.get(i).obtId()==id){
                compuestas.remove(i);
                return;
            }
        }
    }

    public void deshacerCompuesta(int id) throws Exception {
        for(int i=0; i<compuestas.size(); ++i){
            if(compuestas.get(i).obtId()==id){
                RelacionCompuesta r = compuestas.get(i);
                if(r.esNot()) compuestas.add(r.obtHijo());
                else if (r.esAnd() || r.esOr()){
                    compuestas.add(r.obtHijoIzq());
                    compuestas.add(r.obtHijoDer());
                }
                compuestas.remove(i);
                return;
            }
        }
    }

    public void deshacerCompuestaConj(int id) throws Exception {
        for(int i=0; i<compuestas.size(); ++i){
            if(compuestas.get(i).obtId()==id){
                compuestas.remove(i);
                return;
            }
            if(!compuestas.get(i).esConjunto() && compuestas.get(i).contieneConjunto(id)){
                RelacionCompuesta r = compuestas.get(i);
                if(r.esNot()) compuestas.add(r.obtHijo());
                else if (r.esAnd() || r.esOr()){
                    compuestas.add(r.obtHijoIzq());
                    compuestas.add(r.obtHijoDer());
                }
                compuestas.remove(i);
                --i;
            }
        }
    }

    public void deshacerTodasCompuestas() throws Exception {
        for(int i=0; i<compuestas.size(); ++i){
            if(!compuestas.get(i).esConjunto()){
                RelacionCompuesta r = compuestas.get(i);
                if(r.esNot()) compuestas.add(r.obtHijo());
                else if (r.esAnd() || r.esOr()){
                    compuestas.add(r.obtHijoIzq());
                    compuestas.add(r.obtHijoDer());
                }
                compuestas.remove(i);
                --i;
            }
        }
    }

}
