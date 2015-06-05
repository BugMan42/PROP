package Presentacio;

import Domini.Congresista;
import Domini.ControladorCongreso;
import Domini.TSTIterator;

import java.util.ArrayList;
import java.util.List;


public class CPCongreso {

    private static final int tamBloque = 100;

    private PanelCongreso PC;
    private ControladorCongreso CC;
    private CPRelaciones CPR;

    private int order;
    private int RUCon;
    private int[] indCon;
    private String[][] bCon;
    private int RUConB;
    private int[] indConB;
    private String[][] bConB;

    public void reiniciarCache(){
        RUCon = 1;
        indCon = new int[2];
        indCon[0] = -1;
        indCon[1] = -1;
        bCon = new String[2][];
    }
    public void reiniciarBusqueda(){
        RUConB = 1;
        indConB = new int[2];
        indConB[0] = -1;
        indConB[1] = -1;
        bConB = new String[2][];
    }
    public CPCongreso() {
        CC = new ControladorCongreso();
        reiniciarCache();
        reiniciarBusqueda();
    }
    public void modOrder(int ord) {
        order = ord;
        reiniciarCache();
        reiniciarBusqueda();
    }
    public ControladorCongreso obtCC() {
        return CC;
    }
    public CPRelaciones obtCPR() {
        return CPR;
    }
    public void modCC(ControladorCongreso aux) {
        CC = aux;
    }
    public void modCPR(CPRelaciones aux) {
        CPR = aux;
    }
    public PanelCongreso obtPanel() {
        if (PC == null) PC = new PanelCongreso(this);
        return PC;
    }
    public int size() {
        return CC.size();
    }
    public void agregarCongresista(String dni, String name, String surname, int age, String city, String state, String party) throws Exception {
        CC.agregarCongresista(dni, name, surname, age, city, state, party);
        reiniciarCache();
        reiniciarBusqueda();
        //refresh(indexa, indexb);
    }
    public void modCongresista(String dni, String dni_nuevo, String nombre, String nombre_nuevo, String apellido,
                               String apellido_nuevo, int edad, int edad_nuevo, String ciudad, String ciudad_nuevo,
                               String estado, String estado_nuevo, String partido, String partido_nuevo) throws Exception {
        CC.modCongresista(dni, dni_nuevo, nombre, nombre_nuevo, apellido, apellido_nuevo, edad, edad_nuevo, ciudad, ciudad_nuevo, estado, estado_nuevo, partido, partido_nuevo, CPR.obtCR());
        reiniciarCache();
        reiniciarBusqueda();
       // refresh(indexa,indexb);
    }
    public void agregarCongresistaRandom(int n) throws Exception {
        CC.agregarCongresistaRandom(n);
        reiniciarCache();
        reiniciarBusqueda();
        //refresh(indexa, indexb);
    }
    private void print(String str) {
        System.out.println(str);
    }
    public void eliminarCongresista(String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        CC.eliminarCongresista(dni,nombre,apellido,edad,ciudad,estado,partido,CPR.obtCR());
        reiniciarCache();
        reiniciarBusqueda();
        //refresh(indexa, indexb);
    }

    public void eliminarCongreso() {
        CC.eliminarCongreso(CPR.obtCR());
        reiniciarCache();
        reiniciarBusqueda();
    }
    public void nuevo(){
        if (CC.size()>0) {
            CC.eliminarCongreso(CPR.obtCR());
            reiniciarCache();
            reiniciarBusqueda();
            //order = 0;
        }
        actualizar();
    }
    public void actualizar(){
        if(PC!=null) {
            obtPanel().emptyLError();
            obtPanel().setDefaultText();
            obtPanel().ListUpdate();
        }
    }
    public String obtBQOrden(int bq) {
        switch (order) {
            case 0: //DNI
                return CC.obtBloqueDNI(bq,tamBloque);
            case 1: //Nombre
                return CC.obtBloqueNombre(bq, tamBloque);
            case 2: //Apellido
                return CC.obtBloqueApellido(bq, tamBloque);
            case 3: //Edad
                return CC.obtBloqueEdad(bq,tamBloque);
            case 4: //Ciudad
                return CC.obtBloqueCiudad(bq, tamBloque);
            case 5: //Estado
                return CC.obtBloqueEstado(bq, tamBloque);
            case 6: // Partido
                return CC.obtBloquePartido(bq,tamBloque);
        }
        return CC.obtBloqueDNI(bq, 100);
    }

    public String obtCongresistaCache(int i){
        String res;
        int bloque = i/tamBloque;
        if (indCon[0] == bloque){
            res = bCon[0][i%tamBloque];
            RUCon = 0;
        }
        else if (indCon[1] == bloque){
            res = bCon[1][i%tamBloque];
            RUCon = 1;
        }
        else {
            if(RUCon==1) RUCon = 0;
            else RUCon = 1;
            indCon[RUCon] = bloque;
            bCon[RUCon] = obtBQOrden(bloque).split(obtSep());
            res = bCon[RUCon][i%tamBloque];
        }
        return res;
    }
    public String obtCongresistaBusqueda(int i){
        String res;
        int bloque = i/tamBloque;
        if (indConB[0] == bloque){
            res = bConB[0][i%tamBloque];
            RUConB = 0;
        }
        else if (indConB[1] == bloque){
            res = bConB[1][i%tamBloque];
            RUConB = 1;
        }
        else {
            if(RUConB==1) RUConB = 0;
            else RUConB = 1;
            indConB[RUConB] = bloque;
            bConB[RUConB] = obtBQBusqueda(bloque).split(obtSep());
            res = bConB[RUConB][i%tamBloque];
        }
        return res;
    }
    public String obtBQBusqueda(int bq) {
        switch (order) {
            case 0: // Dni
                return CC.obtBloqueCacheBusquedaDni(bq,tamBloque);
            case 1: // Nombre
                return CC.obtBloqueCacheBusquedaNombre(bq, tamBloque);
            case 2: // Apellido
                return CC.obtBloqueCacheBusquedaApellido(bq, tamBloque);
            case 3: // Edad
                return CC.obtBloqueCacheBusquedaEdad(bq,tamBloque);
            case 4: // Ciudad
                return CC.obtBloqueCacheBusquedaCiudad(bq, tamBloque);
            case 5: // Estado
                return CC.obtBloqueCacheBusquedaEstado(bq, tamBloque);
            case 6: // Partido
                return CC.obtBloqueCacheBusquedaPartido(bq,tamBloque);
        }
        return CC.obtBloqueCacheBusquedaDni(bq, tamBloque);
    }

    public String obtSep() {
        return CC.obtSep();
    }
    public String obtCongresista(String dni) {
        try {
            return CC.consultarStringCongresista(dni);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



    public boolean resultados() {
        return CC.cacheBusquedaVacia();
    }
    public int sizeBusqueda() {
        return CC.sizeBusqueda();
    }

    /**Buscar*/
    public void searchByDni(String aux)  {
        if (!aux.isEmpty()) CC.searchByDni(aux);
    }
    public void searchByName(String aux) {
        if (!aux.isEmpty()) CC.searchByName(aux);
    }
    public void searchBySurName(String aux) {
        if (!aux.isEmpty()) CC.searchBySurName(aux);
    }
    public void searchByAge(String aux) {
        if (!aux.isEmpty())  CC.searchByAge(aux);
    }
    public void searchByCity(String aux) {
        if (!aux.isEmpty())  CC.searchByCity(aux);
    }
    public void searchByState(String aux) {
        if (!aux.isEmpty()) CC.searchByState(aux);
    }
    public void searchByParty(String aux) {
        if (!aux.isEmpty()) CC.searchByParty(aux);
    }

    public void guardar(String path) throws Exception {
        CC.guardar(path);
        //refresh(indexa, indexb);
    }
    public void cargar(String path) throws Exception {
        CC.cargar(path, CPR.obtCR());
        reiniciarCache();
    }


}
