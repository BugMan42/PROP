package Domini;

import Persistencia.ControladorPersistencia;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ControladorCongreso {

    private static final int max_lineas_guardar = 300;
    private static final int max_lineas_cargar = 300;
    private static final String sep = "@#@";

    private List<Congresista> referencia;
    private List<Congresista> cache;
    private int orden;
    private Congreso c;

    public ControladorCongreso(){
        c = new Congreso();
        referencia = new ArrayList<Congresista>();
        cache = new ArrayList<Congresista>();
        orden = 0;
    }

    public int size(){
        return c.size();
    }
    public String obtSep() {
        return sep;
    }

    public boolean esVacio(){
        return c.esVacio();
    }

    public void agregarCongresista(String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        Dni d = new Dni(dni);
        Congresista con = new Congresista(d,nombre,apellido,edad,ciudad,estado,partido);
        c.agregarCongresista(con);

        // Insertar ordenadamente en referencia.
        int pos = 0;
        switch (orden){
            case 0: pos = Collections.binarySearch(referencia,con,Congresista.DNI);
                break;
            case 1: pos = Collections.binarySearch(referencia,con,Congresista.NAME);
                break;
            case 2: pos = Collections.binarySearch(referencia,con,Congresista.SURNAME);
                break;
            case 3: pos = Collections.binarySearch(referencia,con,Congresista.AGE);
                break;
            case 4: pos = Collections.binarySearch(referencia,con,Congresista.CITY);
                break;
            case 5: pos = Collections.binarySearch(referencia,con,Congresista.STATE);
                break;
            case 6: pos = Collections.binarySearch(referencia,con,Congresista.PARTY);
                break;
        }
        // Cuando binarySearch no encuentra el elemento buscado devuelve: (-(insertion point) - 1).
        pos = pos*-1 -1;
        referencia.add(pos, con);
    }

    public void agregarCongresistaRandom(long n) {

        for (long i = 0; i < n; ++i) {
            c.agregarCongresistaRandom();
        }
        //referencia = c.obtenerCongreso();
        reordenar();
    }

    private void reordenar(){
        referencia = c.obtenerCongreso();
        switch (orden){
            case 0:
                //sortByDni();
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
        referencia.remove(obtindiceRef(new Congresista(d, "", "", 1, "", "", "")));

    }

    public void eliminarCongreso(ControladorRelaciones cr) {
        c.eliminarCongreso();
        cr.eliminarRelaciones();
        referencia.clear();
    }

    public void modNombreCongresista(String dni, String nombre) throws Exception {
        Dni d = new Dni(dni);
        c.modNombreCongresista(d, nombre);
        referencia = obtenerCongreso();
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
        //print(size()+"size");
        c.modCongresista(d, d_nuevo, nombre, apellido, edad, ciudad, estado, partido);
        reordenar();
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
                String[] prm = con.split(" ");
                agregarCongresista(prm[0], prm[1], prm[2], Integer.parseInt(prm[3]), prm[4], prm[5], prm[6]);
            }
            r = cp.leer(max_lineas_cargar);
        }
        cp.cerrarFichero();
    }

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
    private void cerr(String str) {
        System.out.println(str);
    }
    public void searchByDni(String aux) throws Exception {
        cache.clear();
        for (int i = 0; i < c.size(); ++i) {
            if(referencia.get(i).obtDni().toString().startsWith(aux)) cache.add(referencia.get(i));
        }
    }
    public void searchByName(String aux) {
        cache.clear();
        //int n = 0;
        for (int i = 0; i < c.size(); ++i) {
            if(referencia.get(i).obtNombre().startsWith(aux)) {
                cache.add(referencia.get(i));
               //++n;
            }
        }
    }
    public void searchBySurName(String aux) {
        cache.clear();
        for (int i = 0; i < c.size(); ++i) {
            if(referencia.get(i).obtApellido().startsWith(aux)) cache.add(referencia.get(i));
        }
    }
    public void searchByAge(int aux) {
        cache.clear();
        for (int i = 0; i < c.size(); ++i) {
            if(referencia.get(i).obtEdad() == aux) cache.add(referencia.get(i));
        }
    }
    public void searchByCity(String aux) {
        cache.clear();
        for (int i = 0; i < c.size(); ++i) {
            if(referencia.get(i).obtCiudad().startsWith(aux)) cache.add(referencia.get(i));
        }
    }
    public void searchByState(String aux) {
        cache.clear();
        for (int i = 0; i < c.size(); ++i) {
            if(referencia.get(i).obtEstado().startsWith(aux)) cache.add(referencia.get(i));
        }
    }
    public void searchByParty(String aux) {
        cache.clear();
        for (int i = 0; i < c.size(); ++i) {
            if(referencia.get(i).obtPartido().startsWith(aux)) cache.add(referencia.get(i));
        }
    }
    private boolean searchOptions(String congresista, String prefixe, int op) {
        switch (op) {
            case 0:
                return congresista.startsWith(prefixe);
            case 1:
                return congresista.contains(prefixe);
            case 2:
                return congresista.contentEquals(prefixe);
        }
        return congresista.equals(prefixe);
    }

    public String obtenerBloqueCongresoPR(int i, int tam) {
        List<Congresista> con = c.obtenerCongreso();
        int j=i*tam;
        int lim = i*tam + tam;
        String res = "";
        while (j<con.size() && j<lim){
            Congresista co = con.get(j);
            res += co.obtID()+" "+co.obtNombre()+" "+co.obtApellido()+" "+String.valueOf(co.obtEdad())+"\n";
            ++j;
        }
        return res;
    }

    public int obtenerBloqueCongresista(String dni, int tam_bloque) throws Exception {
        Dni d = new Dni(dni);
        int i = 0;
        while (i<referencia.size() && !referencia.get(i).obtDni().equals(d)) ++i;
        if (i<referencia.size()) return (i/tam_bloque) * tam_bloque;
        return -1;
    }

    public ArrayList<String> obtenerBusqueda() {
        ArrayList<String> a = new ArrayList<String>(cache.size());
        //if (referencia == null) referencia = c.obtenerCongreso();
        for (int i = 0; i < cache.size(); ++i) {
            a.add(cache.get(i).toString());
        }
        return a;
    }
    private void print(String str) {
        System.out.println(str);
    }

    //Otra que me hacia falta aqui tengo que hablar contigo
    public ArrayList<String> obtenerCongresoTotal() {
        ArrayList<String> a = new ArrayList<String>(c.size());
        if (referencia == null) referencia = c.obtenerCongreso();
        //print("Capacidad de: "+size());
        for (int i = 0; i < c.size(); ++i) {
            a.add(referencia.get(i).toString());
        }
        return a;
    }

    private int obtindiceRef(Congresista a) {
        return Collections.binarySearch(referencia,a,Congresista.DNI);
    }

    public boolean cacheVacia() {
        return cache.size() == 0;
    }

    public String obtCongresoPR(){
        List<Congresista> con = c.obtenerCongreso();
        String res = "";
        for (Congresista co: con)
            res += co.obtID()+" "+co.obtNombre()+" "+co.obtApellido()+" "+String.valueOf(co.obtEdad())+"\n";
        return res;
    }

    public String obtBloquePR(int bloque, int tam_bloque){
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > size()) fin = size();
        List<Congresista> con = c.obtenerCongreso().subList(ini,fin);
        String res = "";
        for (Congresista co: con)
            res += co.obtID()+" "+co.obtNombre()+" "+co.obtApellido()+" "+String.valueOf(co.obtEdad())+"\n";
        return res;
    }

    public String obtBloquePR2(int bloque, int tam_bloque){
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        List<Congresista> con = c.obtCongreso(bloque, tam_bloque);
        String res = "";
        for (Congresista co: con)
            res += co.obtID()+" "+co.obtNombre()+" "+co.obtApellido()+" "+String.valueOf(co.obtEdad())+"\n";
        return res;
    }

    //1 bloque = 1 string
    //def bloques de 100 strings
    public String obtBloque(int bq) {
        List<Congresista> con = c.obtenerCongreso();
        String res = "";
        for (int i = 100*bq; i < con.size();++i) {
            res += con.get(i).toString() + sep;
        }
        return res;
    }

}
