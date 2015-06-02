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
    private ArrayList<String> cache;

    public ControladorRelaciones(ControladorCongreso cc, ControladorCjtEvento ce){
        c = cc;
        e = ce;
        rs = new Relaciones();
        cache = new ArrayList<String>();
    }

    public boolean esVacio(){
        return rs.esVacio();
    }

    public int size() throws Exception {
        return rs.size();
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
        rs.agregarRelacion(r);
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
        rs.agregarRelacion(r);
    }

    public void agregarRelacionRandom(int n) throws Exception {
        long maxr = (long)c.size()*(long)e.size();
        int nr = rs.size();
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
        return rs.existeRelacion(r);
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
        return rs.existeRelacion(r);
    }

    public boolean tieneRelaciones(String dni) throws Exception{
        Congresista con = c.consultarCongresista(dni);
        return rs.tieneRelaciones(con);
    }

    public boolean tieneRelaciones(String nombre, String fecha) throws Exception{
        Evento ev = e.ConsultarEvento(nombre, fecha);
        return rs.tieneRelaciones(ev);
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
        rs.eliminarRelacion(r);
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
        rs.eliminarRelacion(r);
    }

    public void eliminarRelaciones(String dni) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        rs.eliminarRelaciones(con);
    }

    public void eliminarRelaciones(String nombre, String fecha) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        rs.eliminarRelaciones(ev);
    }

    public void eliminarRelaciones() {
        rs.eliminarRelaciones();
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
        if (!rs.esVacio()){
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
        ArrayList<Congresista> c = obtCongresistas();
        for(Congresista con : c) g.agregarVertice(con.obtID());
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
    }

    public Grafo crearGrafoAlgoritmo() throws Exception {
        Grafo golf = new Grafo();
        for(String s : g.consultarVerticesID()) golf.agregarVertice(s);
        for(int i : g.consultarVertices())
            for(int j : g.nodosSalida(i))
                if(i!=j) golf.agregarArista(i,j,g.pesoAristasVertices(i,j));
        return golf;
    }

    public String obtBloquePR(int bloque, int tam_bloque) throws Exception {
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > size()) fin = size();
        List<RelacionSimple> lr = rs.obtTodasLasRelaciones().subList(ini,fin);
        String res = "";
        for (RelacionSimple rs : lr) res += rs.toString()+"\n";
        return res;
    }

    public void cargarCache(String dni) throws Exception {
        ArrayList<String> res = new ArrayList<String>();
        if (tieneRelaciones(dni)) {
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
        if (tieneRelaciones(nombre, fecha)) {
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

}
