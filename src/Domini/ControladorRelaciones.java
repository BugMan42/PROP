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
    static final private String E4 = "Para crear una relación aleatoria debe existir al menos un congresista " +
            "en el congreso y un evento en el conjunto de eventos.";


    private static final int max_lineas_guardar = 300;
    private static final int max_lineas_cargar = 300;

    private ControladorCongreso c;
    private ControladorCjtEvento e;
    private Relaciones rs;
    private Grafo g;

    public ControladorRelaciones(ControladorCongreso cc, ControladorCjtEvento ce){
        c = cc;
        e = ce;
        rs = new Relaciones();
    }

    public void agregarRelacion(String dni, String nombre, String fecha) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        if(ev.tipo().equals("Votacion")) throw new Exception(E1);
        Congresista con = c.consultarCongresista(dni);
        RelacionSimple r = new RelacionSimpleSinVoto(con,ev);
        rs.agregarRelacion(r);
    }

    public void agregarVoto(String dni, String nombre, String fecha, String voto) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        if(!ev.tipo().equals("Votacion")) throw new Exception(E2);
        Congresista con = c.consultarCongresista(dni);
        Voto v;
        if (voto.equals("Abstencion")) v = new Abstencion();
        else if (voto.equals("Blanco")) v = new Blanco();
        else if (voto.equals("Negativo")) v = new Negativo();
        else if (voto.equals("Nulo")) v = new Nulo();
        else if (voto.equals("Positivo")) v = new Positivo();
        else throw new Exception(E3);
        RelacionSimple r = new RelacionSimpleConVoto(con,ev,v);
        rs.agregarRelacion(r);
    }

    public void agregarRelacionRandom() throws Exception {
        if(!c.esVacio() && e.size() > 0){
            List<Congresista> lc = c.obtenerCongreso();
            List<Evento> le = e.ConsultarTodosEventos();
            Random r = new Random();
            Congresista con = lc.get(r.nextInt(lc.size()));
            Evento ev = le.get(r.nextInt(le.size()));
            if(ev.tipo().equals("Votacion")) {
                String tipo = "Abstencion";
                int rand = r.nextInt(5);
                switch (rand){
                    case 1: tipo = "Blanco";
                        break;
                    case 2: tipo = "Negativo";
                        break;
                    case 3: tipo = "Nulo";
                        break;
                    case 4: tipo = "Positivo";
                        break;
                }
                agregarVoto(con.ID(),ev.obt_nombre(),ev.obt_fecha(),tipo);
            }
            else agregarRelacion(con.ID(),ev.obt_nombre(),ev.obt_fecha());
        }
        else throw new Exception(E4);
    }

    public void eliminarRelacion(String dni, String nombre, String fecha) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        Evento ev = e.ConsultarEvento(nombre, fecha);
        RelacionSimple r = new RelacionSimpleSinVoto(con,ev);
        rs.eliminarRelacion(r);
    }

    public void eliminarRelaciones() throws Exception {
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

    ArrayList<Congresista> obtCongresistas(){
        return rs.obtCongresistas();
    }

    ArrayList<RelacionSimple> obtTodasLasRelaciones() throws Exception {
        return rs.obtTodasLasRelaciones();
    }

    public void guardar(String ruta) throws Exception {
        if (!c.esVacio()) {
            ControladorPersistencia cp = new ControladorPersistencia(ruta);
            ArrayList<RelacionSimple> rel = rs.obtTodasLasRelaciones();
            Iterator<RelacionSimple> it = rel.iterator();
            cp.abrirEscritura();
            while (it.hasNext()){
                String datos = "";
                int j = 0;
                while (j < max_lineas_guardar && it.hasNext()){
                    datos += it.next().toString()+"\n";
                    ++j;
                }
                cp.escribir(datos);
            }
            cp.cerrarFichero();
        }
    }

    public void cargar(String ruta) throws Exception {
        ControladorPersistencia cp = new ControladorPersistencia(ruta);
        cp.abrirLectura();
        rs.eliminarRelaciones();
        String r = cp.leer(max_lineas_cargar);
        while (!r.equals("")){
            String[] aux = r.split("\n");
            for(String con : aux){
                String[] prm = con.split("\\s");
                if(prm.length < 4) agregarRelacion(prm[0], prm[1], prm[2]);
                else agregarVoto(prm[0], prm[1], prm[2], prm[3]);
            }
            r = cp.leer(max_lineas_cargar);
        }
        cp.cerrarFichero();
    }

    public void crearGrafoRelaciones() throws Exception {
        g = new Grafo();
        ArrayList<Congresista> c = obtCongresistas();
        for(Congresista con : c) g.agregarVertice(con.ID());
        ArrayList<RelacionSimple> r = obtTodasLasRelaciones();
        for(RelacionSimple re : r){
            String origen = re.obtCongresista().ID();
            if(re.obtEvento().tipo().equals("Votacion")) {
                ArrayList<RelacionSimple> rv = obtRelaciones(re.obtEvento().obt_nombre(),re.obtEvento().obt_fecha());
                for (RelacionSimple rvi : rv) {
                    String fin = rvi.obtCongresista().ID();
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
                    if (!origen.equals(cone.ID()))
                        g.agregarArista(origen, cone.ID(), re.obtEvento().obt_importancia());
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

}
