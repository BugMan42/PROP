package Domini;

import Persistencia.ControladorPersistencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ControladorCongreso {

    private static final int max_lineas_guardar = 300;
    private static final int max_lineas_cargar = 300;

    private List<Congresista> referencia;
    private Congreso c;

    public ControladorCongreso(){
        c = new Congreso();
        referencia = new ArrayList<Congresista>();
    }

    public int size(){
        return c.size();
    }

    public boolean esVacio(){
        return c.esVacio();
    }

    public void agregarCongresista(String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        Dni d = new Dni(dni);
        Congresista con = new Congresista(d,nombre,apellido,edad,ciudad,estado,partido);
        c.agregarCongresista(con);
        referencia.add(con);
    }

    public void agregarCongresistaRandom(int n) {
        for (int i = 0; i < n; ++i) {
            c.agregarCongresistaRandom();
        }
        referencia.clear();
        referencia.addAll(obtenerCongreso());
    }

    public ArrayList<String> obtenerListaID(){
        return c.obtenerListaID();
    }

    public List<Congresista> obtenerCongreso(){
        return c.obtenerCongreso();
    }

    public boolean contieneCongresista(String dni) throws Exception {
        Dni d = new Dni(dni);
        return c.contieneCongresista(d);
    }

    public void eliminarCongresista(String dni, ControladorRelaciones cr) throws Exception {
        if(cr.tieneRelaciones(dni)) cr.eliminarRelaciones(dni);
        Dni d = new Dni(dni);
        c.eliminarCongresista(d);
        referencia.remove(d);
    }

    public void eliminarCongreso(ControladorRelaciones cr) {
        c.eliminarCongreso();
        cr.eliminarRelaciones();
        referencia.clear();
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

    public void modDniCongresista(String dni, String dni_nuevo, ControladorRelaciones cr) throws Exception {
        if(cr.tieneRelaciones(dni)) cr.modCongresista(dni,dni_nuevo);
        Dni d = new Dni(dni);
        Dni d_nuevo = new Dni(dni_nuevo);
        c.modDniCongresista(d, d_nuevo);
    }

    public void modCongresista(String dni, String dni_nuevo, String nombre, String apellido, int edad, String ciudad,
                               String estado, String partido, ControladorRelaciones cr) throws  Exception{
        if(cr.tieneRelaciones(dni)) cr.modCongresista(dni,dni_nuevo);
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

    public void guardar(String ruta) throws Exception {
        if (!c.esVacio()) {
            ControladorPersistencia cp = new ControladorPersistencia(ruta);
            List<Congresista> cs = c.obtenerCongreso();
            Iterator<Congresista> it = cs.iterator();
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

    public void cargar(String ruta, ControladorRelaciones cr) throws Exception {
        ControladorPersistencia cp = new ControladorPersistencia(ruta);
        cp.abrirLectura();
        eliminarCongreso(cr);
        String r = cp.leer(max_lineas_cargar);
        while (!r.equals("")){
            String[] aux = r.split("\n");
            for(String con : aux){
                String[] prm = con.split("\\s");
                agregarCongresista(prm[0], prm[1], prm[2], Integer.parseInt(prm[3]), prm[4], prm[5], prm[6]);
            }
            r = cp.leer(max_lineas_cargar);
        }
        cp.cerrarFichero();
    }


    /** FUNCIONES QUE HAY QUE IMPLEMENTAR */
    public void sortByDni() {
        Collections.sort(referencia, Congresista.DNI);
    }
    public void sortByName() {
        Collections.sort(referencia, Congresista.NAME);
    }
    public void sortBySurName() {
        Collections.sort(referencia, Congresista.SURNAME);
    }
    public void sortByAge() {
        Collections.sort(referencia, Congresista.AGE);
    }
    public void sortByCity() {
        Collections.sort(referencia, Congresista.CITY);
    }
    public void sortByState() {
        Collections.sort(referencia, Congresista.STATE);
    }
    public void sortByParty() {
        Collections.sort(referencia, Congresista.PARTY);
    }

    //Pre: fin > inicio
    public ArrayList<String> obtenerBloque(int inicio, int fin) {
        ArrayList<String> a = new ArrayList<String>(fin-inicio);
        List<Congresista> b = referencia.subList(inicio,fin);
        for (int i = 0; i < c.size(); ++i) {
            a.add(b.get(i).toString());
        }
        return a;
    }

    public ArrayList<String> obtenerBloqueCongresista(String dni, int tam_bloque) throws Exception {
        Dni d = new Dni(dni);
        ArrayList<String> bloque = new ArrayList<String>();
        int i = 0;
        while (i<referencia.size() && !referencia.get(i).obtDni().equals(d)) ++i;
        if (i<referencia.size()) {
            int inicio = (i/tam_bloque) * tam_bloque;
            int fin = inicio + tam_bloque;
            if (fin > referencia.size()) fin = referencia.size();
            for (i=inicio; i<fin; ++i) bloque.add(referencia.get(i).toString());
        }
        return bloque;
    }

    //Otra que me hacia falta aqui tengo que hablar contigo
    public ArrayList<String> obtenerCongresoTotal() {
        ArrayList<String> a = new ArrayList<String>(c.size());
        if (referencia == null) referencia = c.obtenerCongreso();
        for (int i = 0; i < c.size(); ++i) {
            a.add(referencia.get(i).toString());
        }
        return a;
    }

}
