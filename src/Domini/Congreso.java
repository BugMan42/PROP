package Domini;

import java.util.*;

public class Congreso {
    static final String error1 = "Congreso no contiene el dni";
    static final String error2 = "Congreso ya contiene el dni";
    static final String error3 = "DNI NO VALIDO";
    private TST<Congresista> tst;
    //private int N;
    public Congreso() {
        tst = new TST<Congresista>();
        //N = 0;
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
        tst.obtener(dni.toString()).modDni(dniNuevo);
        tst.modificar(dni.toString(),dniNuevo.toString());
    }
    public void ModificarCongresista(Dni dni,Dni dni2 , String nombre, String apellido, int edad, String ciudad, String estado, String partido) {

    }
    public Congresista ConsultarCongresista(Dni dni) throws Exception {
        Congresista aux = new Congresista(tst.obtener(dni.toString()));
        return aux;
    }


}
