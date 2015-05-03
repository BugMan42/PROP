package Domini;

import Persistencia.ControladorPersistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jose on 15/04/15.
 */
public class ControladorRelaciones {

    private static final int max_lineas_guardar = 300;
    private static final int max_lineas_cargar = 300;

    private ControladorCongreso c;
    private ControladorCjtEvento e;
    private Relaciones rs;

    public ControladorRelaciones(ControladorCongreso cc, ControladorCjtEvento ce){
        c = cc;
        e = ce;
        rs = new Relaciones();
    }

    public void agregarRelacion(String dni, String nombre, String fecha) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        Evento ev = e.ConsultarEvento(nombre, fecha);
        Relacion r = new Relacion(con,ev);
        rs.agregarRelacion(r);
    }

    public void eliminarRelacion(String dni, String nombre, String fecha) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        Evento ev = e.ConsultarEvento(nombre, fecha);
        Relacion r = new Relacion(con,ev);
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

    ArrayList<Relacion> obtRelaciones(String dni) throws Exception {
        Congresista con = c.consultarCongresista(dni);
        return rs.obtRelaciones(con);
    }

    ArrayList<Relacion> obtRelaciones(String nombre, String fecha) throws Exception {
        Evento ev = e.ConsultarEvento(nombre, fecha);
        return rs.obtRelaciones(ev);
    }

    ArrayList<Congresista> obtCongresistas(){
        return rs.obtCongresistas();
    }

    ArrayList<Relacion> obtTodasLasRelaciones() throws Exception {
        return rs.obtTodasLasRelaciones();
    }

    public void guardar(String ruta) throws Exception {
        if (!c.esVacio()) {
            ControladorPersistencia cp = new ControladorPersistencia(ruta);
            ArrayList<Relacion> rel = rs.obtTodasLasRelaciones();
            Iterator<Relacion> it = rel.iterator();
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
                agregarRelacion(prm[0], prm[1], prm[2]);
            }
            r = cp.leer(max_lineas_cargar);
        }
        cp.cerrarFichero();
    }

}
