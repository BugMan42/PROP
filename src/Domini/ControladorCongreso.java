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

    public boolean esVacio(){
        return c.esVacio();
    }

    public void agregarCongresista(String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        Dni d = new Dni(dni);
        c.agregarCongresista(d,nombre,apellido,edad,ciudad,estado,partido);
    }

    public ArrayList<String> obtenerListaID(){
        return c.obtenerListaID();
    }

    public boolean contieneCongresista(String dni) throws Exception {
        Dni d = new Dni(dni);
        return c.contieneCongresista(d);
    }

    public void eliminarCongresista(String dni) throws Exception {
        Dni d = new Dni(dni);
        c.eliminarCongresista(d);
    }

    public void eliminarCongreso() {
        c.eliminarCongreso();
    }

    public void modNombreCongresista(String dni, String nombre) throws Exception {
        Dni d = new Dni(dni);
        c.modNombreCongresista(d, nombre);
    }

    public void modApellidoCongresista(String dni, String apellido) throws Exception {
        Dni d = new Dni(dni);
        c.modApellidoCongresista(d, apellido);
    }

    public void modEdadCongresista(String dni, int edad) throws Exception {
        Dni d = new Dni(dni);
        c.modEdadCongresista(d, edad);
    }

    public void modCiudadCongresista(String dni, String ciudad) throws Exception {
        Dni d = new Dni(dni);
        c.modCiudadCongresista(d, ciudad);
    }

    public void modEstadoCongresista(String dni, String estado) throws Exception {
        Dni d = new Dni(dni);
        c.modEstadoCongresista(d, estado);
    }

    public void modPartidoCongresista(String dni, String partido) throws Exception {
        Dni d = new Dni(dni);
        c.modPartidoCongresista(d, partido);
    }

    public void modDniCongresista(String dni, String dni_nuevo) throws Exception {
        Dni d = new Dni(dni);
        Dni d_nuevo = new Dni(dni_nuevo);
        c.modDniCongresista(d, d_nuevo);
    }

    public void modCongresista(String dni, String dni_nuevo, String nombre, String apellido, int edad, String ciudad,
                               String estado, String partido) throws  Exception{
        Dni d = new Dni(dni);
        Dni d_nuevo = new Dni(dni_nuevo);
        c.modCongresista(d, d_nuevo, nombre, apellido, edad, ciudad, estado, partido);
    }

    public Congresista consultarCongresista(String dni) throws Exception {
        Dni d = new Dni(dni);
        return c.consultarCongresista(d);
    }

    public String toString() {
        return c.toString();
    }
}
