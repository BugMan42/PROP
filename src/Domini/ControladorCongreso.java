package Domini;

import Persistencia.ControladorPersistencia;
import scala.Array;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ControladorCongreso {

    private static final int max_lineas_guardar = 300;
    private static final int max_lineas_cargar = 300;
    private static final String sep = "@#@";
    private List<Congresista> cacheBusqueda;
    private Congreso c;

    public ControladorCongreso(){
        c = new Congreso();
        cacheBusqueda = new ArrayList<Congresista>();
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
    }

    public void agregarCongresistaRandom(long n) {
        for (long i = 0; i < n; ++i) {
            c.agregarCongresistaRandom();
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

    public void eliminarCongresista(String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido, ControladorRelaciones cr) throws Exception {
        if(cr.tieneRelaciones(dni)) cr.eliminarRelaciones(dni);
        Congresista a = new Congresista(new Dni(dni),nombre,apellido,edad,ciudad,estado,partido);
        c.eliminarCongresista(a);
    }

    public void eliminarCongreso(ControladorRelaciones cr) {
        c.eliminarCongreso();
        cr.eliminarRelaciones();
    }

    public void modCongresista(String dni, String dni_nuevo, String nombre, String nombre_nuevo, String apellido,
                               String apellido_nuevo, int edad, int edad_nuevo, String ciudad, String ciudad_nuevo,
                               String estado, String estado_nuevo, String partido, String partido_nuevo, ControladorRelaciones cr) throws  Exception{
        if(cr.tieneRelaciones(dni)) cr.modCongresista(dni,dni_nuevo);
        Dni d = new Dni(dni);
        Dni d_nuevo = new Dni(dni_nuevo);
        Congresista con = new Congresista(d, nombre, apellido, edad, ciudad, estado, partido);
        Congresista con2 = new Congresista(d_nuevo, nombre_nuevo, apellido_nuevo, edad_nuevo, ciudad_nuevo, estado_nuevo, partido_nuevo);
        c.modCongresista(con, con2);
    }


    public Congresista consultarCongresista(String dni) throws Exception {
        Dni d = new Dni(dni);
        return c.consultarCongresista(d);
    }

    public String consultarStringCongresista(String dni) throws Exception {
        Dni d = new Dni(dni);
        return c.consultarCongresista(d).toString();
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

    public int sizeBusqueda() {
        return cacheBusqueda.size();
    }
    public List<Congresista> obtCache() {
        return cacheBusqueda;
    }
    public void searchByDni(String aux) {
        cacheBusqueda.clear();
        cacheBusqueda = c.searchPrefixDni(aux.toUpperCase());
    }
    public void searchByName(String aux) {
        cacheBusqueda.clear();
        cacheBusqueda = c.searchPrefixNombre(aux.toUpperCase());
    }
    public void searchBySurName(String aux) {
        cacheBusqueda.clear();
        cacheBusqueda = c.searchPrefixApellido(aux.toUpperCase());
    }
    public void searchByAge(String aux) {
        cacheBusqueda.clear();
        cacheBusqueda = c.searchPrefixEdad(String.valueOf(aux));
    }
    public void searchByCity(String aux) {
        cacheBusqueda.clear();
        cacheBusqueda = c.searchPrefixCiudad(aux.toUpperCase());
    }
    public void searchByState(String aux) {
        cacheBusqueda.clear();
        cacheBusqueda = c.searchPrefixEstado(aux.toUpperCase());
    }
    public void searchByParty(String aux) {
        cacheBusqueda.clear();
        cacheBusqueda = c.searchPrefixPartido(aux.toUpperCase());
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


    public ArrayList<String> obtenerBusqueda() {
        ArrayList<String> a = new ArrayList<String>(cacheBusqueda.size());
        //if (referencia == null) referencia = c.obtenerCongreso();
        for (int i = 0; i < cacheBusqueda.size(); ++i) {
            a.add(cacheBusqueda.get(i).toString());
        }
        return a;
    }
    private void print(String str) {
        System.out.println(str);
    }

    public boolean cacheBusquedaVacia() {
        if (cacheBusqueda == null) return true;
        else return cacheBusqueda.size() == 0;
    }

    public String obtBloquePR(int bloque, int tam_bloque){
        List<Congresista> con = c.obtCongresoDni(bloque, tam_bloque);
        String res = "";
        for (Congresista co: con)
            res += co.obtID()+" "+co.obtNombre()+" "+co.obtApellido()+" "+String.valueOf(co.obtEdad())+"\n";
        return res;
    }
    public String obtBloqueDNI(int bloque, int tam_bloque){
        List<Congresista> con = c.obtCongresoDni(bloque, tam_bloque);
        String res = "";
        for (Congresista co: con)
            res += co.toString() + sep;
        return res;
    }
    public String obtBloqueNombre(int bloque, int tam_bloque){
        List<Congresista> con = c.obtCongresoNombre(bloque, tam_bloque);
        String res = "";
        for (Congresista co: con)
            res += co.toStringByName() + sep;
        return res;
    }
    public String obtBloqueApellido(int bloque, int tam_bloque){
        List<Congresista> con = c.obtCongresoApellido(bloque, tam_bloque);
        String res = "";
        for (Congresista co: con)
            res += co.toStringBySurname() + sep;
        return res;
    }
    public String obtBloqueEdad(int bloque, int tam_bloque){
        List<Congresista> con = c.obtCongresoEdad(bloque, tam_bloque);
        String res = "";
        for (Congresista co: con)
            res += co.toStringByAge() + sep;
        return res;
    }
    public String obtBloqueCiudad(int bloque, int tam_bloque){
        List<Congresista> con = c.obtCongresoCiudad(bloque, tam_bloque);
        String res = "";
        for (Congresista co: con)
            res += co.toStringByCity() + sep;
        return res;
    }
    public String obtBloqueEstado(int bloque, int tam_bloque){
        List<Congresista> con = c.obtCongresoEstado(bloque, tam_bloque);
        String res = "";
        for (Congresista co: con)
            res += co.toStringByState() + sep;
        return res;
    }
    public String obtBloquePartido(int bloque, int tam_bloque){
        List<Congresista> con = c.obtCongresoPartido(bloque, tam_bloque);
        String res = "";
        for (Congresista co: con)
            res += co.toStringByParty() + sep;
        return res;
    }

    public String obtBloqueCacheBusqueda(int bloque, int tam_bloque){
        String res = "";
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > cacheBusqueda.size()) fin = cacheBusqueda.size();
        List<Congresista> lc = cacheBusqueda.subList(ini, fin);
        for (Congresista c : lc) {
            res += c.toString()+sep;
        }
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
    public String obtBloqueCacheBusquedaDni(int bloque, int tam_bloque){
        String res = "";
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > cacheBusqueda.size()) fin = cacheBusqueda.size();
        for (int i = ini; i < fin; ++i) {
            res+=cacheBusqueda.get(i).toString()+sep;
        }
        return res;
    }
    public String obtBloqueCacheBusquedaNombre(int bloque, int tam_bloque){
        String res = "";
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > cacheBusqueda.size()) fin = cacheBusqueda.size();
        for (int i = ini; i < fin; ++i) {
            res+=cacheBusqueda.get(i).toStringByName()+sep;
        }
        return res;
    }
    public String obtBloqueCacheBusquedaApellido(int bloque, int tam_bloque){
        String res = "";
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > cacheBusqueda.size()) fin = cacheBusqueda.size();
        for (int i = ini; i < fin; ++i) {
            res+=cacheBusqueda.get(i).toStringBySurname()+sep;
        }
        return res;
    }
    public String obtBloqueCacheBusquedaEdad(int bloque, int tam_bloque){
        String res = "";
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > cacheBusqueda.size()) fin = cacheBusqueda.size();
        for (int i = ini; i < fin; ++i) {
            res+=cacheBusqueda.get(i).toStringByAge()+sep;
        }
        return res;
    }
    public String obtBloqueCacheBusquedaCiudad(int bloque, int tam_bloque){
        String res = "";
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > cacheBusqueda.size()) fin = cacheBusqueda.size();
        for (int i = ini; i < fin; ++i) {
            res+=cacheBusqueda.get(i).toStringByCity()+sep;
        }
        return res;
    }
    public String obtBloqueCacheBusquedaEstado(int bloque, int tam_bloque){
        String res = "";
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > cacheBusqueda.size()) fin = cacheBusqueda.size();
        for (int i = ini; i < fin; ++i) {
            res+=cacheBusqueda.get(i).toStringByState()+sep;
        }
        return res;
    }
    public String obtBloqueCacheBusquedaPartido(int bloque, int tam_bloque){
        String res = "";
        int ini = bloque * tam_bloque;
        int fin = ini + tam_bloque;
        if (fin > cacheBusqueda.size()) fin = cacheBusqueda.size();
        for (int i = ini; i < fin; ++i) {
            res+=cacheBusqueda.get(i).toStringByParty()+sep;
        }
        return res;
    }


}
