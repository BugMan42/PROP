package Domini;

import java.util.ArrayList;

/**
 * Created by bug on 8/04/15.
 */
public class ControladorCongreso {

    private Congreso c;

    public ControladorCongreso(){
        c = new Congreso();
    }

    public int size(){
        return c.size();
    }

    public void agregarCongresista(String s) throws Exception {
        String[] aux = s.split("\\s");
        Dni dni = new Dni(aux[0]);
        Congresista con = new Congresista(dni,aux[1],aux[2],Integer.valueOf(aux[3]),aux[4],aux[5],aux[6]);
        c.agregarCongresista(dni,aux[1],aux[2],Integer.valueOf(aux[3]),aux[4],aux[5],aux[6]);
    }

    public ArrayList<String> obtenerLista(){
        return c.obtenerListaID();
    }

    public boolean contieneCongresista(String s) throws Exception {
        Dni dni = new Dni(s);
        return c.contieneCongresista(dni);
    }

    public void eliminarCongresista(String s) throws Exception {
        Dni dni = new Dni(s);
        c.eliminarCongresista(dni);
    }
    public void eliminarCongreso() {
        c.eliminarCongreso();
    }

    public void modificarNombreCongresista(String s) throws Exception {
        String[] aux = s.split("\\s");
        Dni dni = new Dni(aux[0]);
        c.modNombreCongresista(dni, aux[1]);
    }

    public void modificarApellidoCongresista(String s) throws Exception {
        String[] aux = s.split("\\s");
        Dni dni = new Dni(aux[0]);
        c.modApellidoCongresista(dni, aux[1]);
    }

    public void modificarEdadCongresista(String s) throws Exception {
        String[] aux = s.split("\\s");
        Dni dni = new Dni(aux[0]);
        c.modEdadCongresista(dni, Integer.parseInt(aux[1]));
    }

    public void modificarCiudadCongresista(String s) throws Exception {
        String[] aux = s.split("\\s");
        Dni dni = new Dni(aux[0]);
        c.modCiudadCongresista(dni, aux[1]);
    }

    public void modificarEstadoCongresista(String s) throws Exception {
        String[] aux = s.split("\\s");
        Dni dni = new Dni(aux[0]);
        c.modEstadoCongresista(dni, aux[1]);
    }

    public void modificarPartidoCongresista(String s) throws Exception {
        String[] aux = s.split("\\s");
        Dni dni = new Dni(aux[0]);
        c.modPartidoCongresista(dni, aux[1]);
    }

    public void modificarDni(String s) throws Exception {
        String[] aux = s.split("\\s");
        Dni dni = new Dni(aux[0]);
        Dni dni_nuevo = new Dni(aux[1]);
        c.modDniCongresista(dni, dni_nuevo);
    }

    public void modificarCongresista(String s) throws  Exception{
        String[] aux = s.split("\\s");
        Dni dni = new Dni(aux[0]);
        Dni dni_nuevo = new Dni(aux[1]);
        c.modCongresista(dni, dni_nuevo, aux[2], aux[3], Integer.parseInt(aux[4]), aux[5], aux[6], aux[7]);
    }

    public Congresista consultarCongresista(String s) throws Exception {
        Dni dni = new Dni(s);
        return c.consultarCongresista(dni);
    }

    public String toString() {
        return c.toString();
    }
}
