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
    private int orden;
    private Congreso c;

    public ControladorCongreso(){
        c = new Congreso();
        referencia = new ArrayList<Congresista>();
        orden = 0;
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
        mantenerOrden();
    }
    private void mantenerOrden() {
        switch (orden) {
            case 0:
                sortByDni();
                break;
            case 1:
                sortByName();
                break;
            case 2:
                sortBySurName();
                break;
            case 3:
                sortByAge();
                break;
            case 4:
                sortByCity();
                break;
            case 5:
                sortByState();
                break;
            case 6:
                sortByParty();
                break;

        }
    }

    public void agregarCongresistaRandom(int n) {
        for (int i = 0; i < n; ++i) {
            c.agregarCongresistaRandom();
        }
        referencia = c.obtenerCongreso();
        mantenerOrden();
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
        //guarrada util
        referencia.remove(obtindiceRef(new Congresista(d,"","",1,"","","")));
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
        if (size() > 1) {
            Collections.sort(referencia, Congresista.DNI);
            orden = 0;
        }
    }
    public void sortByName() {
        if (size() > 1) {
            Collections.sort(referencia, Congresista.NAME);
            orden = 1;
        }
    }
    public void sortBySurName() {
        if (size() > 1) {
            Collections.sort(referencia, Congresista.SURNAME);
            orden = 2;
        }
    }
    public void sortByAge() {
        if (size() > 1) {
            Collections.sort(referencia, Congresista.AGE);
            orden = 3;
        }
    }
    public void sortByCity() {
        if (size() > 1) {
            Collections.sort(referencia, Congresista.CITY);
            orden = 4;
        }
    }
    public void sortByState() {
        if (size() > 1) {
            Collections.sort(referencia, Congresista.STATE);
            orden = 5;
        }
    }
    public void sortByParty() {
        if (size() > 1) {
            Collections.sort(referencia, Congresista.PARTY);
            orden = 6;
        }
    }
    public void searchByDni() {
    }
    public void searchByName() {
    }
    public void searchBySurName() {
    }
    public void searchByAge() {
    }
    public void searchByCity() {
    }
    public void searchByState() {
    }
    public void searchByParty() {
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

    private int obtindiceRef(Congresista a) {
        return Collections.binarySearch(referencia,a,Congresista.DNI);
    }

}
