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

    public Congreso() {
        tst = new TST<Congresista>();
    }

    public void agregarCongresista(Congresista c) throws Exception {
        tst.insertar(c.ID(),c);
    }

    public void eliminarCongresista(Dni dni) throws Exception {
        try {
            tst.borrar(dni.toString());
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

    public void eliminarCongreso() {
        tst.vaciar();
    }

    //####################################
    //########MODIFICADORAS###############
    //####################################

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

    public void modNombreCongresista(Dni dni,String nombre) throws Exception {
        try {
            tst.obtener(dni.toString()).modNombre(nombre);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

    public void modApellidoCongresista(Dni dni,String apellido) throws Exception {
        try {
            tst.obtener(dni.toString()).modApellido(apellido);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

    public void modEdadCongresista(Dni dni,int edad) throws Exception {
        try {
            tst.obtener(dni.toString()).modEdad(edad);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

    public void modCiudadCongresista(Dni dni,String ciudad) throws Exception {
        try {
            tst.obtener(dni.toString()).modCiudad(ciudad);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

    public void modEstadoCongresista(Dni dni,String estado) throws Exception {
        try {
            tst.obtener(dni.toString()).modEstado(estado);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

    public void modPartidoCongresista(Dni dni,String partido) throws Exception {
        try {
            tst.obtener(dni.toString()).modPartido(partido);
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

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

    public Congresista consultarCongresista(Dni dni) throws Exception {
        try {
            return tst.obtener(dni.toString());
        }
        catch (Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

    public Congresista copiaConsultarCongresista(Dni dni) throws Exception {
        try {
            Congresista aux = new Congresista(tst.obtener(dni.toString()));
            return aux;
        }
        catch(Exception a) {
            throw new NoExisteC(dni.toString());
        }
    }

    public int size() {
        return tst.size();
    }

    public boolean esVacio() {
        return tst.esVacio();
    }

    public ArrayList<String> obtenerListaID() {
        return tst.consultarClaves();
    }

    public List<Congresista> obtenerCongreso() {
        return tst.consultarObjetos();
    }

    public boolean contieneCongresista(Dni dni) {
        return tst.existe(dni.toString());
    }

    public String toString() {
        String aux = tst.toString();
        return aux;
    }

}
