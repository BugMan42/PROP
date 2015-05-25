package Domini;

import java.util.*;


public class Relaciones {
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
            int i;
            for (i = 0; i < eventos.size(); ++i) {
                if (eventos.get(i).obtEvento().ID().equals(r.obtEvento().ID())) {
                    throw new Exception("Ya existe la RelacionSimple: "+r.toString());
                }
            }
            eventos.add(i,r);
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
            int i;
            for (i = 0; i < congresistas.size(); ++i) {
                if (congresistas.get(i).obtCongresista().obtID().equals(r.obtCongresista().obtID())) {
                    throw new Exception("Ya existe la RelacionSimple");
                }
            }
            congresistas.add(i,r);
        }
        public void eliminarRelacion(RelacionSimple r) throws Exception {
            for (int i = 0; i < eventos.size(); ++i) {
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
    private TST<NodeC> congresistas;
    private TST<NodeE> eventos;

    public Relaciones() {
        congresistas = new TST<NodeC>();
        eventos = new TST<NodeE>();
    }
    public void agregarRelacion(RelacionSimple r) throws Exception {
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
    public boolean existeRelacion(RelacionSimple r) throws Exception {
        return congresistas.obtener(r.obtCongresista().obtID()).existeRelacion(r);
    }
    public boolean tieneRelaciones(Congresista c) throws Exception {
        ArrayList<String> sc = congresistas.consultarClaves();
        return sc.contains(c.obtID());
    }
    public boolean tieneRelaciones(Evento e) throws Exception {
        ArrayList<String> se = eventos.consultarClaves();
        return se.contains(e.ID());
    }
    public void eliminarRelacion(RelacionSimple r) throws Exception {
        NodeC aux = congresistas.obtener(r.obtCongresista().obtID());
        aux.eliminarRelacion(r);
        if (aux.esVacio()) congresistas.borrar(r.obtCongresista().obtID());
        NodeE aux2 = eventos.obtener(r.obtEvento().ID());
        aux2.eliminarRelacion(r);
        if (aux2.esVacio()) eventos.borrar(r.obtEvento().ID());
    }
    public void eliminarRelaciones(Congresista c) throws Exception {
        ArrayList<RelacionSimple> r = new ArrayList<RelacionSimple>(obtRelaciones(c));
        for(RelacionSimple rs : r) eliminarRelacion(rs);
    }
    public void eliminarRelaciones(Evento e) throws Exception {
        ArrayList<RelacionSimple> r = new ArrayList<RelacionSimple>(obtRelaciones(e));
        for(RelacionSimple rs : r) eliminarRelacion(rs);
    }
    public void eliminarRelaciones(){
        congresistas.vaciar();
        eventos.vaciar();
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
}
