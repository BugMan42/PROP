package Domini;

import java.util.*;

public class Congreso {
    private class ExisteC extends Exception {
        public ExisteC(String str) {
            super("Ya existe el Congresista: "+str);
        }
    }
    private class NoExisteC extends Exception {
        public NoExisteC(String str) {
            super("No existe el Congresista: "+str);
        }
    }

    private TST<Congresista> tst;

    /**
     * Creadora Congreso
     */
    public Congreso() {
        tst = new TST<Congresista>();
    }

    /**
     * Retorna el numero de Congresistas
     */
    public int size() {
        return tst.size();
    }

    /**
     * Retorna un booleano que indica si
     * el congreso esta vacio
     */
    public boolean esVacio() {
        return tst.esVacio();
    }

    /**
     * Agrega el Congresista c al Congreso
     */
    public void agregarCongresista(Congresista c) throws Exception {
        tst.insertar(c.ID(),c);
    }

    /**
     * Devuelve el listado de dnis del Congreso
     */
    public ArrayList<String> obtenerListaID() {
        return tst.consultarClaves();
    }

    /**
     * devuelve el listado de Congresistas del Congreso
     */
    public List<Congresista> obtenerCongreso() {
        return tst.consultarObjetos();
    }

    /**
     * Retorna si existe en el Congreso
     * un Congresista con dni igual al parametro
     */
    public boolean contieneCongresista(Dni dni) {
        return tst.existe(dni.toString());
    }

    /**
     * Elimina el Congresista del Congreso con
     * Dni=dni en el caso que exista
     */
    public void eliminarCongresista(Dni dni) throws Exception {
        try {
            tst.borrar(dni.toString());
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

    /**
     * Elimina todo el Congreso
     */
    public void eliminarCongreso() {
        tst.vaciar();
    }

    //####################################
    //########MODIFICADORAS###############
    //####################################

    /**
     * Modifica el nombre del Congresista con Dni=dni
     * si existe
     */
    public void modNombreCongresista(Dni dni,String nombre) throws Exception {
        try {
            tst.obtener(dni.toString()).modNombre(nombre);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }
    /**
     * Modifica el apellido del Congresista con Dni=dni
     * si existe
     */
    public void modApellidoCongresista(Dni dni,String apellido) throws Exception {
        try {
            tst.obtener(dni.toString()).modApellido(apellido);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }
    /**
     * Modifica la edad del Congresista con Dni=dni
     * si existe
     */
    public void modEdadCongresista(Dni dni,int edad) throws Exception {
        try {
            tst.obtener(dni.toString()).modEdad(edad);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }
    /**
     * Modifica la ciudad del Congresista con Dni=dni
     * si existe
     */
    public void modCiudadCongresista(Dni dni,String ciudad) throws Exception {
        try {
            tst.obtener(dni.toString()).modCiudad(ciudad);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }
    /**
     * Modifica el estado del Congresista con Dni=dni
     * si existe
     */
    public void modEstadoCongresista(Dni dni,String estado) throws Exception {
        try {
            tst.obtener(dni.toString()).modEstado(estado);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }
    /**
     * Modifica el partido del Congresista con Dni=dni
     * si existe
     */
    public void modPartidoCongresista(Dni dni,String partido) throws Exception {
        try {
            tst.obtener(dni.toString()).modPartido(partido);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }
    /**
     * Modifica el Dni del Congresista con Dni=dni
     * si existe y solo en el caso que no exista otro
     * Congresista con Dni=DniNuevo
     */
    public void modDniCongresista(Dni dni, Dni dniNuevo) throws Exception {
        if (!dni.equals(dniNuevo)) {
            try {
                tst.modificar(dni.toString(), dniNuevo.toString());
                tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
            }
            catch(Exception a) {
                if (a.getClass().equals(KeyNotExistsTST.class)) throw new NoExisteC(dni.toString());
                if (a.getClass().equals(KeyAlreadyExistsTST.class)) throw new ExisteC(dniNuevo.toString());

            }
        }
    }
    /**
     * Modifica Todos los atributos del Congresista
     * de una vez
     */
    public void modCongresista(Dni dni,Dni dniNuevo , String nombre, String apellido,
                                     int edad, String ciudad, String estado, String partido) throws  Exception{
        try {
            if (!dni.equals(dniNuevo)) {
                tst.modificar(dni.toString(),dniNuevo.toString());
                //tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
                tst.obtener(dniNuevo.toString()).mod(dniNuevo, nombre, apellido, edad, ciudad, estado, partido);
            }
        }
        catch (Exception a) {
            if (a.getClass().equals(KeyNotExistsTST.class)) throw new NoExisteC(dni.toString());
            if (a.getClass().equals(KeyAlreadyExistsTST.class)) throw new ExisteC(dniNuevo.toString());
        }
    }

    /**
     * Retorna la instancia del Congresista con Dni=dni
     */
    public Congresista consultarCongresista(Dni dni) throws Exception {
        try {
            return tst.obtener(dni.toString());
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }
    /**
     * Retorna una copia del Congresista con Dni=dni
     */
    public Congresista copiaConsultarCongresista(Dni dni) throws Exception {
        try {
            Congresista aux = new Congresista(tst.obtener(dni.toString()));
            return aux;
        }
        catch(Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }
    /**
     * Retorna un String que contiene los dnis de todo
     * el congreso
     */
    public String toString() {
        String aux = tst.toString();
        return aux;
    }
    private void print(String d) {
        System.out.println(d);
    }

}
