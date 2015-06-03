package Presentacio;

import Domini.Congresista;
import Domini.ControladorCongreso;
import Domini.TSTIterator;

import java.util.ArrayList;
import java.util.List;


public class CPCongreso {

    private PanelCongreso PC;
    private ControladorCongreso CC;
    private CPRelaciones CPR;

    private int order;
    private String[] bqa;
    private String[] bqb;
    private int indexa;
    private int indexb;

    //return whether a is
    private boolean aIsUp() {
        return indexa < indexb;
    }

    public CPCongreso() {
        CC = new ControladorCongreso();
        bqa = new String[100];
        bqb = null;
        order = 0;
        indexa = 0;
        indexb = -1;
    }
    public void modOrder(int ord) {
        order = ord;
        bqa = new String[100];
        bqb = null;
        indexa = 0;
        indexb = -1;
        refresh(0,-1);
    }
    public int obtBQArriba() {
        if (aIsUp()) return indexa;
        else return indexb;
    }
    public int obtBQAbajo() {
        if (!aIsUp()) return indexa;
        else return indexb;
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
    /*private void cargarbqb(int bq) {
        bqb = CC.obtBloque(bq).split(CC.obtSep());
    }
    private void cargarbqa(int bq) {
        bqa = CC.obtBloque(bq).split(CC.obtSep());
    }*/
    public void agregarCongresista(String dni, String name, String surname, int age, String city, String state, String party) throws Exception{
        CC.agregarCongresista(dni, name, surname, age, city, state, party);
        refresh(indexa, indexb);
    }
    public void agregarCongresistaRandom(int n) throws Exception {
        CC.agregarCongresistaRandom(n);
        //print("he agregado");
        refresh(indexa, indexb);
        //print("final refresh");
        //print(bqa[0]);
        //print(bqa.length+"");
    }
    private void print(String str) {
        System.out.println(str);
    }
    public void eliminarCongresista(String dni) throws Exception {
        CC.eliminarCongresista(dni, CPR.obtCR());
        refresh(indexa, indexb);
        //TODO falta actualizar bloques
    }
    public void eliminarCongreso() {
        CC.eliminarCongreso(CPR.obtCR());
        bqa = new String[100];
        bqb = new String[100];
        indexa = 0;
        indexb = -1;
        order = 0;
    }
    public void nuevo(){
        if (CC.size()>0) {
            CC.eliminarCongreso(CPR.obtCR());
            bqa = new String[100];
            bqb = new String[100];
            indexa = 0;
            indexb = -1;
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
                return CC.obtBloquePR2(bq,100);
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
    public String obtBQ(int bq) {
        return CC.obtBloquePR2(bq,100);
    }
    public String[] obtBqMenor() {
        if (aIsUp()) return bqa;
        else return bqb;
    }
    public String[] obtBqMayor() {
        if (!aIsUp()) return bqa;
        else return bqb;
    }
    public String obtSep() {
        return CC.obtSep();
    }
    public String obtCongresista(int index) {
       //print("Debug: obtCongr"+index) ;
       int baux = queBq(index);
       if (estaEnCache(baux)) {
           //print("dentro de obtener: "+index);
           return obtEnCache(index,baux);
       }
       else {
           cargarCache(baux);
           return obtEnCache(index,baux);
       }
    }
    public String obtCongresistaAct(int index) {
       // print("obetnemos");
        int baux = queBq(index);
        if (estaEnCache(baux)) {
            //print("esta en cache");
            return obtEnCache(index,baux);
        }
        else {
            cargarCache(baux);
            return obtEnCache(index,baux);
        }
    }
    private void cargarCache(int bq) {
       // print("Cargamos: "+bq);
        if (aIsUp()) {
            if (indexb == -1 || bq < indexa) {
                bqb = obtBQOrden(bq).split(obtSep());
                indexb = bq;
               // print("BQA: "+indexa+" BQB: "+indexb);
                return;
            }
            if (bq > indexb) {
                bqa = obtBQOrden(bq).split(obtSep());
                indexa = bq;
                //print("BQA: "+indexa+" BQB: "+indexb);
                return;
            }
        }
        else {
            if (indexb == -1 || bq > indexa) {
                bqb = obtBQOrden(bq).split(obtSep());
                indexb = bq;
                //print("BQA: "+indexa+" BQB: "+indexb);
                return;
            }
            if (bq < indexb) {
                bqa = obtBQOrden(bq).split(obtSep());
                indexa = bq;
                //print("BQA: "+indexa+" BQB: "+indexb);
                return;
            }
        }

    }
   /* public void cargarBloqueA(int ba) {
        bqa = obtBQ(ba).split(obtSep());
        indexa = ba;
    }
    public void cargarBloqueB(int bb) {
        bqb = obtBQ(bb).split(obtSep());
        indexb = bb;
    }*/
    public void refresh(int ba, int bb) {
        bqa = obtBQOrden(ba).split(obtSep());
        //print("en refresh: "+bqa[0].toString());
        indexa = ba;
        if (indexb != -1) {
            bqb = obtBQOrden(bb).split(obtSep());
            indexb = bb;
        }
    }
    private String obtEnCache(int index, int bq) {
        //print("obtener en cache:"+order);
        if (indexa == bq) {
            //print("indexa");
           // print(bqa[0].toString());
            return bqa[(index%100)];
        }
        else return bqb[(index%100)];
    }
    private boolean estaEnCache(int bq) {
        if (bq >= 0) return ((bq == indexa) || (bq == indexb));
        else return false;
    }
    public boolean indexVisible(int index) {
        return estaEnCache(index/100);
    }
    private int queBq(int index) {
        return (index/100);
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
        CC.cargar(path,CPR.obtCR());
        if (CC.size() > 100) refresh(0,1);
        else refresh(0, -1);
    }


}
