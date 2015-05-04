package Domini;

import Persistencia.ControladorPersistencia;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jose on 15/04/15.
 */
public class ControladorRelaciones {
    static final private String E1 = "El tipo de evento debe ser: ReunionPersonal, ReunionProfesional o Acto." +
            " Para votar utiliza agregarVoto(String dni, String nombre, String fecha, String voto)";
    static final private String E2 = "El evento debe ser una Votación.";
    static final private String E3 = "Tipo de voto incorrecto. Tipos disponibles: Abstencion, Blanco, Negativo, " +
            "Nulo y Positivo.";


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
        RelacionSimple1 r = new RelacionSimpleSinVoto1(con,ev);
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
        RelacionSimple1 r = new RelacionSimpleConVoto1(con,ev,v);
        rs.agregarRelacion(r);
    }

    public void eliminarRelacion(String dni, String nombre, String fecha) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        Evento ev = e.ConsultarEvento(nombre, fecha);
        RelacionSimple1 r = new RelacionSimpleSinVoto1(con,ev);
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

    ArrayList<RelacionSimple1> obtRelaciones(String dni) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        return rs.obtRelaciones(con);
    }

    ArrayList<RelacionSimple1> obtRelaciones(String nombre, String fecha) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        return rs.obtRelaciones(ev);
    }

    ArrayList<Congresista> obtCongresistas(){
        return rs.obtCongresistas();
    }

    ArrayList<RelacionSimple1> obtTodasLasRelaciones() throws Exception {
        return rs.obtTodasLasRelaciones();
    }

    public void guardar(String ruta) throws Exception {
        if (!c.esVacio()) {
            ControladorPersistencia cp = new ControladorPersistencia(ruta);
            ArrayList<RelacionSimple1> rel = rs.obtTodasLasRelaciones();
            Iterator<RelacionSimple1> it = rel.iterator();
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
        ArrayList<RelacionSimple1> r = obtTodasLasRelaciones();
        for(RelacionSimple1 re : r){
            String origen = re.obtCongresista().ID();
            if(re.obtEvento().tipo().equals("Votacion")) {
                ArrayList<RelacionSimple1> rv = obtRelaciones(re.obtEvento().obt_nombre(),re.obtEvento().obt_fecha());
                for (RelacionSimple1 rvi : rv) {
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
