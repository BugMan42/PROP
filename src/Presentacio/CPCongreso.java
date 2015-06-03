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

    public void reiniciarCache(){
        RUCon = 1;
        indCon = new int[2];
        indCon[0] = -1;
        indCon[1] = -1;
        bCon = new String[2][];
    }
    public CPCongreso() {
        CC = new ControladorCongreso();
        reiniciarCache();
    }
    public void modOrder(int ord) {
        order = ord;
        reiniciarCache();
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
        //refresh(indexa, indexb);
    }
    public void modCongresista(String dni, String dni_nuevo, String nombre, String nombre_nuevo, String apellido,
                               String apellido_nuevo, int edad, int edad_nuevo, String ciudad, String ciudad_nuevo,
                               String estado, String estado_nuevo, String partido, String partido_nuevo) throws Exception {
        CC.modCongresista(dni, dni_nuevo, nombre, nombre_nuevo, apellido, apellido_nuevo, edad, edad_nuevo, ciudad, ciudad_nuevo, estado, estado_nuevo, partido, partido_nuevo, CPR.obtCR());
       // refresh(indexa,indexb);
    }
    public void agregarCongresistaRandom(int n) throws Exception {
        CC.agregarCongresistaRandom(n);
        //refresh(indexa, indexb);
    }
    private void print(String str) {
        System.out.println(str);
    }
    public void eliminarCongresista(String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        CC.eliminarCongresista(dni,nombre,apellido,edad,ciudad,estado,partido,CPR.obtCR());
        //refresh(indexa, indexb);
    }

    public void eliminarCongreso() {
        CC.eliminarCongreso(CPR.obtCR());
        reiniciarCache();
    }
    public void nuevo(){
        if (CC.size()>0) {
            CC.eliminarCongreso(CPR.obtCR());
            reiniciarCache();
            order = 0;
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
                return CC.obtBloqueDNI(bq,100);
            case 1: //Nombre
                return CC.obtBloqueNombre(bq, 100);
            case 2: //Apellido
                return CC.obtBloqueApellido(bq, 100);
            case 3: //Edad
                return CC.obtBloqueEdad(bq,100);
            case 4: //Ciudad
                return CC.obtBloqueCiudad(bq, 100);
            case 5: //Estado
                return CC.obtBloqueEstado(bq, 100);
            case 6: // Partido
                return CC.obtBloquePartido(bq,100);
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
   /* public void refresh(int ba, int bb) {
        bqa = obtBQOrden(ba).split(obtSep());
        indexa = ba;
        if (indexb != -1) {
            bqb = obtBQOrden(bb).split(obtSep());
            indexb = bb;
        }
    }*/

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


    /**SORT */
    public void sortByDni() {
        CC.sortByDni();
    }
    public void sortByName() {
        CC.sortByName();
    }
    public void sortBySurName() {
        CC.sortBySurName();
    }
    public void sortByAge() {
        CC.sortByAge();
    }
    public void sortByCity() {
        CC.sortByCity();
    }
    public void sortByState() {
        CC.sortByState();
    }
    public void sortByParty() {
        CC.sortByParty();
    }
    public boolean resultados() {
        return CC.cacheVacia();
    }

    /**Buscar*/
    public void searchByDni(String aux) throws Exception {
        CC.searchByDni(aux);
    }
    public void searchByName(String aux) {
        CC.searchByName(aux);
    }
    public void searchBySurName(String aux) {
        CC.searchBySurName(aux);
    }
    public void searchByAge(int aux) {
        CC.searchByAge(aux);
    }
    public void searchByCity(String aux) {
        CC.searchByCity(aux);
    }
    public void searchByState(String aux) {
        CC.searchByState(aux);
    }
    public void searchByParty(String aux) {
        CC.searchByParty(aux);
    }

    public void guardar(String path) throws Exception {
        CC.guardar(path);
        //refresh(indexa, indexb);
    }
    public void cargar(String path) throws Exception {
        CC.cargar(path, CPR.obtCR());
        //if (CC.size() > 100) refresh(0,1);
        //else refresh(0, -1);
    }


}
