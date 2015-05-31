package Presentacio;

import Domini.ControladorCongreso;

import java.util.List;


public class CPCongreso {

    private PanelCongreso PC;
    private ControladorCongreso CC;
    private CPRelaciones CPR;

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
        indexa = 0;
        indexb = -1;
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
    private void cargarbqb(int bq) {
        bqb = CC.obtBloque(bq).split(CC.obtSep());
    }
    private void cargarbqa(int bq) {
        bqa = CC.obtBloque(bq).split(CC.obtSep());
    }

    public void agregarCongresista(String dni, String name, String surname, int age, String city, String state, String party) throws Exception{
        CC.agregarCongresista(dni, name, surname, age, city, state, party);
        refresh(indexa, indexb);
        //TODO falta actualizar bloques
    }
    public void agregarCongresistaRandom(int n) {
        //print("antes de petar");
        long timeini = System.nanoTime();
        CC.agregarCongresistaRandom(n);
        print((System.nanoTime()-timeini)/1000000.0 + "");
        //print("despues");
        cargarBloqueA(0);
       // print("despues");
        //TODO falta actualizar bloques
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
    }
    public String obtBQ(int bq) {
        return CC.obtBloque(bq);
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
       int baux = queBq(index);
       if (estaEnCache(baux)) return obtEnCache(index,baux);
       else {
           cargarCache(baux);
           return obtEnCache(index,baux);
       }
    }
    private void cargarCache(int bq) {
        if (aIsUp()) {
            if (indexb == -1 || bq < indexa) {
                bqb = obtBQ(bq).split(obtSep());
                indexb = bq;
                return;
            }
            if (bq > indexb) {
                bqa = obtBQ(bq).split(obtSep());
                indexa = bq;
                return;
            }
        }
        else {
            if (indexb == -1 || bq > indexa) {
                bqb = obtBQ(bq).split(obtSep());
                indexb = bq;
                return;
            }
            if (bq < indexb) {
                bqa = obtBQ(bq).split(obtSep());
                indexa = bq;
                return;
            }
        }
    }
    public void cargarBloqueA(int ba) {
        bqa = obtBQ(ba).split(obtSep());
        indexa = ba;
    }

    public void cargarBloqueB(int bb) {
        bqb = obtBQ(bb).split(obtSep());
        indexb = bb;
    }
    public void refresh(int ba, int bb) {
        bqa = obtBQ(ba).split(obtSep());
        indexa = ba;
        if (bqb != null) {
            bqb = obtBQ(bb).split(obtSep());
            indexb = bb;
        }
    }
    private String obtEnCache(int index, int bq) {
        if (indexa == bq) {
            return bqa[(index%100)];
        }
        else return bqb[(index%100)];
    }
    private boolean estaEnCache(int bq) {
        if (bq >= 0) return ((bq == indexa) || (bq == indexb));
        else return false;
    }
    private int queBq(int index) {
        return (index/100);
    }

    /**SORT */
    public void sortByDni() {
        //Thread workingThread = new Thread(CC.sortByDni()
        //{ IsBackground = true };
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
    }
    public void cargar(String path) throws Exception {
        CC.cargar(path,CPR.obtCR());
    }


}
