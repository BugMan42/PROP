package Domini;

import Persistencia.ControladorPersistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by jose on 15/04/15.
 */
public class ControladorRelaciones {
    static final private String E1 = "El tipo de evento debe ser: ReunionPersonal, ReunionProfesional, ActoOficial " +
            "o ActoNoOficial. Para votar utiliza agregarVoto(String dni, String nombre, String fecha, String voto)";
    static final private String E2 = "El evento debe ser una Votación.";
    static final private String E3 = "Tipo de voto incorrecto. Tipos disponibles: Abstencion, Blanco, Negativo, " +
            "Nulo y Positivo.";


    private static final int max_lineas_guardar = 300;
    private static final int max_lineas_cargar = 300;

    private ControladorCongreso c;
    private ControladorCjtEvento e;
    private Relaciones rs;
    private Grafo g;
    private GrafoNodoArista nuevog;
    private ArrayList<String> cache;

    public ControladorRelaciones(ControladorCongreso cc, ControladorCjtEvento ce){
        c = cc;
        e = ce;
        rs = new Relaciones();
        cache = new ArrayList<String>();
    }

    public boolean esVacioSimples(){
        return rs.esVacioSimples();
    }

    public int sizeSimples() throws Exception {
        return rs.sizeSimples();
    }

    public int sizeCache(){
        return cache.size();
    }

    public void agregarRelacion(String dni, String nombre, String fecha) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        if(ev.tipo().equals("Votacion")) throw new Exception(E1);
        Congresista con = c.consultarCongresista(dni);
        RelacionSimple r;
        if (ev.tipo().equals("ActoOficial") || ev.tipo().equals("ActoNoOficial")) {
            Acto aux = (Acto) ev;
            r = new RelacionSimpleSinVoto(con,aux);
        }
        else {
            Reunion aux = (Reunion) ev;
            r = new RelacionSimpleSinVoto(con,aux);
        }
        rs.agregarRelacionSimple(r);
    }

    public void agregarVoto(String dni, String nombre, String fecha, String voto) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        if(!ev.tipo().equals("Votacion")) throw new Exception(E2);
        Votacion aux = (Votacion) ev;
        Congresista con = c.consultarCongresista(dni);
        Voto v;
        if (voto.equals("Abstencion")) v = new Abstencion();
        else if (voto.equals("Blanco")) v = new Blanco();
        else if (voto.equals("Negativo")) v = new Negativo();
        else if (voto.equals("Nulo")) v = new Nulo();
        else if (voto.equals("Positivo")) v = new Positivo();
        else throw new Exception(E3);
        RelacionSimple r = new RelacionSimpleConVoto(con,aux,v);
        rs.agregarRelacionSimple(r);
    }

    public void agregarRelacionRandom(int n) throws Exception {
        long maxr = (long)c.size()*(long)e.size();
        int nr = rs.sizeSimples();
        //System.out.println("maxr="+maxr+" nr="+nr);
        if(nr < maxr){
            List<Congresista> lc = c.obtenerCongreso();
            List<Evento> le = e.ConsultarTodosEventos();
            Random r = new Random();
            int i=0;
            while(nr<maxr && i<n) {
                Congresista con = lc.get(r.nextInt(lc.size()));
                Evento ev = le.get(r.nextInt(le.size()));
                if (ev.tipo().equals("Votacion")) {
                    String tipo = "Abstencion";
                    int rand = r.nextInt(5);
                    switch (rand) {
                        case 1:
                            tipo = "Blanco";
                            break;
                        case 2:
                            tipo = "Negativo";
                            break;
                        case 3:
                            tipo = "Nulo";
                            break;
                        case 4:
                            tipo = "Positivo";
                            break;
                    }
                    if(!existeVoto(con.obtID(), ev.obt_nombre(), ev.obt_fecha(), tipo)) {
                        agregarVoto(con.obtID(), ev.obt_nombre(), ev.obt_fecha(), tipo);
                        ++i;
                        ++nr;
                    }
                } else {
                    if(!existeRelacion(con.obtID(), ev.obt_nombre(), ev.obt_fecha())) {
                        agregarRelacion(con.obtID(), ev.obt_nombre(), ev.obt_fecha());
                        ++i;
                        ++nr;
                    }
                }
            }
        }
    }

    public boolean existeRelacion(String dni, String nombre, String fecha) throws Exception{
        Evento ev = e.ConsultarEvento(nombre, fecha);
        if(ev.tipo().equals("Votacion")) throw new Exception(E1);
        Congresista con = c.consultarCongresista(dni);
        RelacionSimple r;
        // Modificalo como quieras
        if (ev.tipo().equals("ActoOficial") || ev.tipo().equals("ActoNoOficial")) {
            Acto aux = (Acto) ev;
            r = new RelacionSimpleSinVoto(con,aux);
        }
        else {
            Reunion aux = (Reunion) ev;
            r = new RelacionSimpleSinVoto(con,aux);
        }
        return rs.existeRelacionSimple(r);
    }

    public boolean existeVoto(String dni, String nombre, String fecha, String voto) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        if(!ev.tipo().equals("Votacion")) throw new Exception(E2);
        Votacion aux = (Votacion) ev;
        Congresista con = c.consultarCongresista(dni);
        Voto v;
        if (voto.equals("Abstencion")) v = new Abstencion();
        else if (voto.equals("Blanco")) v = new Blanco();
        else if (voto.equals("Negativo")) v = new Negativo();
        else if (voto.equals("Nulo")) v = new Nulo();
        else if (voto.equals("Positivo")) v = new Positivo();
        else throw new Exception(E3);
        RelacionSimple r = new RelacionSimpleConVoto(con,aux,v);
        return rs.existeRelacionSimple(r);
    }

    public boolean tieneRelacionesSimples(String dni) throws Exception{
        Congresista con = c.consultarCongresista(dni);
        return rs.tieneRelacionesSimples(con);
    }

    public boolean tieneRelacionesSimples(String nombre, String fecha) throws Exception{
        Evento ev = e.ConsultarEvento(nombre, fecha);
        return rs.tieneRelacionesSimples(ev);
    }

    public void eliminarRelacion(String dni, String nombre, String fecha) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        Evento ev = e.ConsultarEvento(nombre, fecha);
        RelacionSimple r;
        if (ev.tipo().equals("ActoOficial") || ev.tipo().equals("ActoNoOficial")) {
            Acto aux = (Acto) ev;
            r = new RelacionSimpleSinVoto(con,aux);
        }
        else {
            Reunion aux = (Reunion) ev;
            r = new RelacionSimpleSinVoto(con,aux);
        }
        rs.eliminarRelacionSimple(r);
    }

    public void eliminarVoto(String dni, String nombre, String fecha, String voto) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        Votacion vot = (Votacion)e.ConsultarEvento(nombre, fecha);
        Voto v;
        if (voto.equals("Abstencion")) v = new Abstencion();
        else if (voto.equals("Blanco")) v = new Blanco();
        else if (voto.equals("Negativo")) v = new Negativo();
        else if (voto.equals("Nulo")) v = new Nulo();
        else if (voto.equals("Positivo")) v = new Positivo();
        else throw new Exception(E3);
        RelacionSimple r = new RelacionSimpleConVoto(con,vot,v);
        rs.eliminarRelacionSimple(r);
    }

    public void eliminarRelacionesSimples(String dni) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        rs.eliminarRelacionesSimples(con);
    }

    public void eliminarRelacionesSimples(String nombre, String fecha) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        rs.eliminarRelacionesSimples(ev);
    }

    public void eliminarRelaciones() throws Exception {
        rs.eliminarRelaciones();
    }

    public void eliminarRelacionesSimples() throws Exception {
        rs.eliminarRelacionesSimples();
    }

    public void eliminarRelacionesCompuestas() throws Exception {
        rs.eliminarRelacionesCompuestas();
    }

    ArrayList<Evento> obtEventos(String dni) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        return rs.obtEventos(con);
    }

    ArrayList<Congresista> obtCongresistas(String nombre, String fecha) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        return rs.obtCongresistas(ev);
    }

    ArrayList<RelacionSimple> obtRelaciones(String dni) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        return rs.obtRelaciones(con);
    }

    ArrayList<RelacionSimple> obtRelaciones(String nombre, String fecha) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        return rs.obtRelaciones(ev);
    }

    ArrayList<Evento> obtEventos(){
        return rs.obtEventos();
    }

    ArrayList<Congresista> obtCongresistas(){
        return rs.obtCongresistas();
    }

    ArrayList<RelacionSimple> obtTodasLasRelaciones() throws Exception {
        return rs.obtTodasLasRelaciones();
    }

    public void modEvento(String id, String new_id) throws Exception {
        rs.modEvento(id, new_id);
    }

    public void modCongresista(String id, String new_id) throws Exception{
        rs.modCongresista(id, new_id);
    }

    public void guardar(String ruta) throws Exception {
        ControladorPersistencia cp = new ControladorPersistencia(ruta);
        cp.abrirEscritura();
        if (!c.esVacio()) {
            List<Congresista> cs = c.obtenerCongreso();
            Iterator<Congresista> it = cs.iterator();
            while (it.hasNext()){
                String datos = "";
                int j = 0;
                while (j < max_lineas_guardar && it.hasNext()){
                    datos += it.next().toString()+"\n";
                    ++j;
                }
                cp.escribir(datos);
            }
            cp.escribir("\n");
        }
        if (e.size()>0) {
            List<Evento> es = e.ConsultarTodosEventos();
            Iterator<Evento> it = es.iterator();
            while (it.hasNext()){
                String datos = "";
                int j = 0;
                while (j < max_lineas_guardar && it.hasNext()){
                    datos += it.next().toString()+"\n";
                    ++j;
                }
                cp.escribir(datos);
            }
            cp.escribir("\n");
        }
        if (!rs.esVacioSimples()){
            ArrayList<RelacionSimple> rel = rs.obtTodasLasRelaciones();
            Iterator<RelacionSimple> it = rel.iterator();
            while (it.hasNext()){
                String datos = "";
                int j = 0;
                while (j < max_lineas_guardar && it.hasNext()){
                    datos += it.next().toString()+"\n";
                    ++j;
                }
                cp.escribir(datos);
            }
        }
        cp.cerrarFichero();
    }

    public void cargar(String ruta) throws Exception {
        ControladorPersistencia cp = new ControladorPersistencia(ruta);
        cp.abrirLectura();
        c.eliminarCongreso(this);
        String[] aux =  cp.leer(max_lineas_cargar).split("\n");
        int i = 0;
        while(!aux[i].equals("")) {
            String[] prm = aux[i].split(" ");
            c.agregarCongresista(prm[0], prm[1], prm[2], Integer.parseInt(prm[3]), prm[4], prm[5], prm[6]);
            ++i;
            if (i==aux.length) {
                aux =  cp.leer(max_lineas_cargar).split("\n");
                i = 0;
            }
        }
        e.EliminarCjtEvento(this);
        ++i;
        if (i==aux.length) {
            aux =  cp.leer(max_lineas_cargar).split("\n");
            i = 0;
        }
        while(!aux[i].equals("")) {
            String[] prm = aux[i].split(" ");
            if(prm[0].equals("Votacion")) e.AgregarVotacion(prm[1], prm[2], Integer.parseInt(prm[3]));
            else if(prm[0].equals("ReunionPersonal")) e.AgregarReunionPersonal(prm[1], prm[2], Integer.parseInt(prm[3]));
            else if(prm[0].equals("ReunionProfesional")) e.AgregarReunionProfesional(prm[1],prm[2],Integer.parseInt(prm[3]));
            else if(prm[0].equals("ActoOficial")) e.AgregarActoOficial(prm[1], prm[2], Integer.parseInt(prm[3]));
            else if(prm[0].equals("ActoNoOficial")) e.AgregarActoNoOficial(prm[1], prm[2], Integer.parseInt(prm[3]));
            else throw new Exception(E1+prm[0]);
            ++i;
            if (i==aux.length) {
                aux =  cp.leer(max_lineas_cargar).split("\n");
                i = 0;
            }
        }
        ++i;
        if (i==aux.length) {
            aux =  cp.leer(max_lineas_cargar).split("\n");
            i = 0;
        }
        while(!aux[i].equals("")) {
            String[] prm = aux[i].split(" ");
            if(prm.length < 4) agregarRelacion(prm[0], prm[1], prm[2]);
            else agregarVoto(prm[0], prm[1], prm[2], prm[3]);
            ++i;
            if (i==aux.length) {
                aux =  cp.leer(max_lineas_cargar).split("\n");
                i = 0;
            }
        }
        cp.cerrarFichero();
    }

    public void crearGrafoRelaciones() throws Exception {
        g = new Grafo();
        List<Congresista> lc = c.obtenerCongreso();
        for(Congresista con : lc) g.agregarVertice(con.obtID());
        // Rel. simples
        ArrayList<RelacionSimple> r = obtTodasLasRelaciones();
        for(RelacionSimple re : r){
            String origen = re.obtCongresista().obtID();
            if(re.obtEvento().tipo().equals("Votacion")) {
                ArrayList<RelacionSimple> rv = obtRelaciones(re.obtEvento().obt_nombre(),re.obtEvento().obt_fecha());
                for (RelacionSimple rvi : rv) {
                    String fin = rvi.obtCongresista().obtID();
                    if (!origen.equals(fin)) {
                        Voto v_origen = re.obtVoto();
                        Voto v_fin = rvi.obtVoto();
                        if (v_origen.obt_tipo().equals(v_fin.obt_tipo()))
                            g.agregarArista(origen, fin, re.obtEvento().obt_importancia());
                    }
                }
            }
            else {
                ArrayList<Congresista> ce = obtCongresistas(re.obtEvento().obt_nombre(),re.obtEvento().obt_fecha());
                for (Congresista cone : ce)
                    if (!origen.equals(cone.obtID()))
                        g.agregarArista(origen, cone.obtID(), re.obtEvento().obt_importancia());
            }
        }
        // Rel. compuestas
        ArrayList<ArrayList<Congresista>> alc = rs.obtConjuntosComp();
        for(ArrayList<Congresista> al : alc){
            for(Congresista con : al){
                String origen = con.obtID();
                for(Congresista con2 : al){
                    String fin = con2.obtID();
                    if(!origen.equals(fin)) g.agregarArista(origen,fin,1.0);
                }
            }
        }
    }

    public Grafo crearGrafoAlgoritmo() throws Exception {
        Grafo golf = new Grafo();
        for(String s : g.consultarVerticesID()) golf.agregarVertice(s);
        for(int i : g.consultarVertices())
            for(int j : g.nodosSalida(i))
                if(i!=j) golf.agregarArista(i,j,g.pesoAristasVertices(i,j));
        return golf;
    }

    public void NuevoCrearGrafoRelaciones() throws Exception {
        nuevog = new GrafoNodoArista();
        List<Congresista> lc = c.obtenerCongreso();
        for(Congresista con : lc) nuevog.agregarVertice(con);
        // Rel. simples
        ArrayList<RelacionSimple> r = obtTodasLasRelaciones();
        for(RelacionSimple re : r){
            Congresista origen = re.obtCongresista();
            if(re.obtEvento().tipo().equals("Votacion")) {
                ArrayList<RelacionSimple> rv = obtRelaciones(re.obtEvento().obt_nombre(),re.obtEvento().obt_fecha());
                for (RelacionSimple rvi : rv) {
                    Congresista fin = rvi.obtCongresista();
                    if (!origen.equals(fin)) {
                        Voto v_origen = re.obtVoto();
                        Voto v_fin = rvi.obtVoto();
                        if (v_origen.obt_tipo().equals(v_fin.obt_tipo()))
                            nuevog.agregarArista(new Edge<Congresista>(origen,fin,re.obtEvento().obt_importancia()));
                    }
                }
            }
            else {
                ArrayList<Congresista> ce = obtCongresistas(re.obtEvento().obt_nombre(), re.obtEvento().obt_fecha());
                for (Congresista cone : ce)
                    if (!origen.equals(cone.obtID()))
                        nuevog.agregarArista(new Edge<Congresista>(origen,cone,re.obtEvento().obt_importancia()));
            }
        }
        // Rel. compuestas
        ArrayList<ArrayList<Congresista>> alc = rs.obtConjuntosComp();
        for(ArrayList<Congresista> al : alc){
            for(Congresista con : al)
                for(Congresista con2 : al)
                    if(!con.equals(con2))
                        new Edge<Congresista>(con,con2,3);
        }
    }

    public GrafoNodoArista NuevoCrearGrafoAlgoritmo() throws Exception {
        GrafoNodoArista golf = new GrafoNodoArista();
        for(Object s : nuevog.consultarVertices()) golf.agregarVertice((Congresista) s);
        for(Object i : nuevog.consultarVertices())
            for(Object j : nuevog.nodosSalida((Congresista) i)) //if(!i.equals(j))
                golf.agregarArista(new Edge((Congresista)i,(Congresista)j,nuevog.pesoAristasVertice((Congresista)i,(Congresista)j)));
        return golf;
    }

    public String obtBloquePR(int bloque, int tam_bloque) throws Exception {
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > sizeSimples()) fin = sizeSimples();
        List<RelacionSimple> lr = rs.obtTodasLasRelaciones().subList(ini,fin);
        String res = "";
        for (RelacionSimple rs : lr) res += rs.toString()+"\n";
        return res;
    }

    public void cargarCache(String dni) throws Exception {
        ArrayList<String> res = new ArrayList<String>();
        if (tieneRelacionesSimples(dni)) {
            ArrayList<RelacionSimple> lr = obtRelaciones(dni);
            for (RelacionSimple rs : lr) {
                Evento e = rs.obtEvento();
                String s = e.tipo() + " " + e.obt_nombre() + " " + e.obt_fecha();
                if (e.tipo().equals("Votacion")) s += " " + rs.obtVoto().obt_tipo();
                res.add(s);
            }
        }
        cache = res;
    }

    public void cargarCache(String nombre, String fecha) throws Exception {
        ArrayList<String> res = new ArrayList<String>();
        if (tieneRelacionesSimples(nombre, fecha)) {
            ArrayList<RelacionSimple> lr = obtRelaciones(nombre, fecha);
            for (RelacionSimple rs : lr) {
                Congresista c = rs.obtCongresista();
                String s = c.obtID() + " " + c.obtNombre() + " " + c.obtApellido() + " " + c.obtEdad();
                if (rs.obtEvento().tipo().equals("Votacion")) s += " " + rs.obtVoto().obt_tipo();
                res.add(s);
            }
        }
        cache = res;
    }

    public String obtBloqueCachePR(int bloque, int tam_bloque) throws Exception {
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > sizeCache()) fin = sizeCache();
        List<String> ls = cache.subList(ini, fin);
        String res = "";
        for (String s : ls) res += s+"\n";
        return res;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // Conjuntos (Relaciones Compuestas)

    public int sizeConjuntos(){
        return rs.sizeConjuntos();
    }

    public void agregarConjuntoSinVoto(String nombre, String fecha, String congresistas) throws Exception {
        Evento ev = e.ConsultarEvento(nombre,fecha);
        if(ev.tipo().equals("Votacion")) throw new Exception(E1);
        String[] aux = congresistas.split("\n");
        ArrayList<Congresista> lc = new ArrayList<Congresista>();
        for(int i=0; i<aux.length; ++i){
            Congresista con = c.consultarCongresista(aux[i]);
            lc.add(con);
        }
        if(ev.tipo().equals("ActoOficial") || ev.tipo().equals("ActoNoOficial")) {
            RelacionCompuesta rc = new RCConjuntoSinVoto(lc, (Acto) ev);
            rs.agregarConjunto((RCConjunto) rc);
        }
        else {
            RelacionCompuesta rc = new RCConjuntoSinVoto(lc, (Reunion) ev);
            rs.agregarConjunto((RCConjunto) rc);
        }
    }

    public void agregarConjuntoConVoto(String nombre, String fecha, String voto, String congresistas) throws Exception {
        Evento ev = e.ConsultarEvento(nombre,fecha);
        if(!ev.tipo().equals("Votacion")) throw new Exception(E2);
        String[] aux = congresistas.split("\n");
        ArrayList<Congresista> lc = new ArrayList<Congresista>();
        for(int i=0; i<aux.length; ++i){
            Congresista con = c.consultarCongresista(aux[i]);
            lc.add(con);
        }
        Voto v;
        if (voto.equals("Abstencion")) v = new Abstencion();
        else if (voto.equals("Blanco")) v = new Blanco();
        else if (voto.equals("Negativo")) v = new Negativo();
        else if (voto.equals("Nulo")) v = new Nulo();
        else if (voto.equals("Positivo")) v = new Positivo();
        else throw new Exception(E3);
        RelacionCompuesta rc = new RCConjuntoConVoto(lc, (Votacion) ev, v);
        rs.agregarConjunto((RCConjunto) rc);
    }

    public String consultarConjuntos(){
        String res = "";
        ArrayList<RelacionCompuesta> lrc = rs.obtConjuntos();
        for(RelacionCompuesta rc : lrc) res += rc+"\n";
        return res;
    }

    public void agregarCompuestaAnd(int i, int d) throws Exception {
        RelacionCompuesta hi = rs.obtCompuesta(i);
        RelacionCompuesta hd = rs.obtCompuesta(d);
        RCAnd and = new RCAnd(hi,hd);
        rs.agregarCompuesta(and);
        rs.eliminarCompuesta(i);
        rs.eliminarCompuesta(d);
    }

    public void agregarCompuestaOr(int i, int d) throws Exception {
        RelacionCompuesta hi = rs.obtCompuesta(i);
        RelacionCompuesta hd = rs.obtCompuesta(d);
        RCOr or = new RCOr(hi,hd);
        rs.agregarCompuesta(or);
        rs.eliminarCompuesta(i);
        rs.eliminarCompuesta(d);
    }

    public void agregarCompuestaNot(int id) throws Exception {
        RelacionCompuesta rc = rs.obtCompuesta(id);
        RCNot not = new RCNot(rc, c);
        rs.agregarCompuesta(not);
        rs.eliminarCompuesta(id);
    }

    public void deshacerCompuesta(int id) throws Exception {
        rs.deshacerCompuesta(id);
    }

    public void deshacerTodasCompuestas() throws Exception {
        rs.deshacerTodasCompuestas();
    }

    public String consultarCompuestas(){
        String res = "";
        ArrayList<RelacionCompuesta> lrc = rs.obtCompuestas();
        for(RelacionCompuesta rc : lrc) res += rc+"\n";
        return res;
    }

    public String consultarCongresistasComp(int id){
        String res = "";
        ArrayList<Congresista> lc = rs.obtCongresistasComp(id);
        for(Congresista co : lc) res += co.obtID()+" "+co.obtNombre()+" "+co.obtApellido()+" "+String.valueOf(co.obtEdad())+"\n";
        return res;
    }

    public String consultarDescripcionComp(int id){
        return rs.consultarDescripcionComp(id);
    }

    public void eliminarConjunto(int id) throws Exception {
        rs.eliminarConjunto(id);
    }

    public void eliminarCongresistaCompuestas(String dni) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        rs.eliminarCongresistaCompuestas(con);
    }

    public void eliminarEventoCompuestas(String nombre, String fecha) throws Exception {
        Evento ev = e.ConsultarEvento(nombre,fecha);
        rs.eliminarEventoCompuestas(ev);
    }

}
