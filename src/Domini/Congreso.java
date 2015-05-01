package Domini;

import java.util.*;

public class Congreso {
    static final String error1 = "Congreso no contiene el dni";
    static final String error2 = "Congreso ya contiene el dni";
    static final String error3 = "DNI NO VALIDO";

    private TST<Congresista> tst;

    public Congreso() {
        tst = new TST<Congresista>();
    }
    public int size() {
        return tst.size();
    }
    public void agregarCongresista(Congresista C) throws Exception {
        tst.insertar(C.obtDni().toString(),C);
    }
    public ArrayList<String> obtenerLista() {
        return tst.consultarClaves();
    }
    public boolean contieneCongresista(Dni dni) {
        return tst.existe(dni.toString());
    }
    public void eliminarCongresista(Dni dni) throws Exception {
        tst.borrar(dni.toString());
    }
    public void eliminarCongreso() {
        tst.vaciar();
    }

    //####################################
    //########MODIFICADORAS###############
    //####################################
    public void modificarDniCongresista(Dni dni,int edad) throws Exception {
        tst.obtener(dni.toString()).modEdad(edad);
    }
    public void modificarNombreCongresista(Dni dni,String nombre) throws Exception {
        tst.obtener(dni.toString()).modNombre(nombre);
    }
    public void modificarApellidoCongresista(Dni dni,String apellido) throws Exception {
        tst.obtener(dni.toString()).modApellido(apellido);
    }
    public void modificarEdadCongresista(Dni dni,int edad) throws Exception {
        tst.obtener(dni.toString()).modEdad(edad);
    }
    public void modificarCiudadCongresista(Dni dni,String ciudad) throws Exception {
        tst.obtener(dni.toString()).modCiudad(ciudad);
    }
    public void modificarEstadoCongresista(Dni dni,String estado) throws Exception {
        tst.obtener(dni.toString()).modEstado(estado);
    }
    public void modificarPartidoCongresista(Dni dni,String partido) throws Exception {
        tst.obtener(dni.toString()).modPartido(partido);
    }
    /** Bueno hay algo mejor????? */
    public void modificarDni(Dni dni, Dni dniNuevo) throws Exception {
        tst.modificar(dni.toString(),dniNuevo.toString());
        tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
    }
    public void modificarCongresista(Dni dni,Dni dniNuevo , String nombre, String apellido,
                                     int edad, String ciudad, String estado, String partido) throws  Exception{
        tst.modificar(dni.toString(),dniNuevo.toString());
        tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
        tst.obtener(dniNuevo.toString()).mod(dniNuevo,nombre,apellido,edad,ciudad,estado,partido);
    }
    public Congresista consultarCongresista(Dni dni) throws Exception {
        Congresista aux = new Congresista(tst.obtener(dni.toString()));
        return aux;
    }
    public String toString() {
        String aux = tst.toString();
        return aux;
    }


}
