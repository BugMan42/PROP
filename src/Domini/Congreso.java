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
    public boolean esVacio() {
        return tst.esVacio();
    }
    public void agregarCongresista(Dni dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        Congresista aux = new Congresista(dni,nombre,apellido,edad,ciudad,estado,partido);
        tst.insertar(dni.toString(),aux);
    }
    public ArrayList<String> obtenerListaID() {
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
    public void modNombreCongresista(Dni dni,String nombre) throws Exception {
        tst.obtener(dni.toString()).modNombre(nombre);
    }
    public void modApellidoCongresista(Dni dni,String apellido) throws Exception {
        tst.obtener(dni.toString()).modApellido(apellido);
    }
    public void modEdadCongresista(Dni dni,int edad) throws Exception {
        tst.obtener(dni.toString()).modEdad(edad);
    }
    public void modCiudadCongresista(Dni dni,String ciudad) throws Exception {
        tst.obtener(dni.toString()).modCiudad(ciudad);
    }
    public void modEstadoCongresista(Dni dni,String estado) throws Exception {
        tst.obtener(dni.toString()).modEstado(estado);
    }
    public void modPartidoCongresista(Dni dni,String partido) throws Exception {
        tst.obtener(dni.toString()).modPartido(partido);
    }
    public void modDniCongresista(Dni dni, Dni dniNuevo) throws Exception {
        if (!dni.equals(dniNuevo)) {
            print("antes mod");
            tst.modificar(dni.toString(), dniNuevo.toString());
            print("despues mod");
            //tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
        }
    }
    public void modCongresista(Dni dni,Dni dniNuevo , String nombre, String apellido,
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
    public void print(String d) {
        System.out.println(d);
    }

}
