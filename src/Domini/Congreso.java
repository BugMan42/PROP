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
    public void addCongresista(Congresista C) throws Exception {
        tst.insertar(C.obtDni().toString(),C);
    }
    public void eliminarCongresista(Congresista C) {
        tst.borrar(C.obtDni().toString());
    }
    public ArrayList<String> obtener_lista() {
        return tst.consultarClaves();
    }
    public boolean contieneCongresista(Dni dni) {
        return tst.existe(dni.toString());
    }
    public void EliminarCongresista(Dni dni) {
        tst.borrar(dni.toString());
    }
    public void EliminarCongreso() {
        tst.vaciar();
    }

    //####################################
    //########MODIFICADORAS###############
    //####################################
    public void ModificarDniCongresista(Dni dni,int edad) throws Exception {
        tst.obtener(dni.toString()).modEdad(edad);
    }
    public void ModificarNombreCongresista(Dni dni,String nombre) throws Exception {
        tst.obtener(dni.toString()).modNombre(nombre);
    }
    public void ModificarApellidoCongresista(Dni dni,String apellido) throws Exception {
        tst.obtener(dni.toString()).modApellido(apellido);
    }
    public void ModificarEdadCongresista(Dni dni,int edad) throws Exception {
        tst.obtener(dni.toString()).modEdad(edad);
    }
    public void ModificarCiudadCongresista(Dni dni,String ciudad) throws Exception {
        tst.obtener(dni.toString()).modCiudad(ciudad);
    }
    public void ModificarEstadoCongresista(Dni dni,String estado) throws Exception {
        tst.obtener(dni.toString()).modEstado(estado);
    }
    public void ModificarPartidoCongresista(Dni dni,String partido) throws Exception {
        tst.obtener(dni.toString()).modPartido(partido);
    }
    /** Bueno hay algo mejor????? */
    public void ModificarDni(Dni dni, Dni dniNuevo) throws Exception {
        tst.modificar(dni.toString(),dniNuevo.toString());
        tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
    }
    public void ModificarCongresista(Dni dni,Dni dniNuevo , String nombre, String apellido,
                                     int edad, String ciudad, String estado, String partido) throws  Exception{
        tst.modificar(dni.toString(),dniNuevo.toString());
        tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
        tst.obtener(dniNuevo.toString()).mod(dniNuevo,nombre,apellido,edad,ciudad,estado,partido);
    }
    public Congresista ConsultarCongresista(Dni dni) throws Exception {
        Congresista aux = new Congresista(tst.obtener(dni.toString()));
        return aux;
    }
    public String toString() {
        String aux = tst.toString();
        return aux;
    }


}
