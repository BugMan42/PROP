package Presentacio;

import Domini.ControladorRelaciones;

import javax.swing.*;

//Por defecto cambiar si conviene
public class CPRelaciones {

    private static final int tam_bloque = 100;

    private CPCongreso CPC;
    private CPEventos CPE;
    private ControladorRelaciones CR;
    private PanelRelaciones PR;
    private PanelRelacionesCongresista PRC;
    private PanelRelacionesEvento PRE;
    private PanelRelacionesGeneral PRG;
    private PanelRCConjuntos PRCC;
    private PanelRCOperaciones PRCO;
    private int RUCon, RUEv, RURel;
    private int[] indCon, indEv, indRel;
    private String[][] bCon;
    private String[][] bEv;
    private String[][] bRel;
    private String[] conj;
    private String[] comp;
    private String[] cong;

    private int pan;

    public ControladorRelaciones obtCR(){
        return CR;
    }

    public CPRelaciones(CPCongreso cpc, CPEventos cpe) {
        CPC = cpc;
        CPE = cpe;

        CR = new ControladorRelaciones(cpc.obtCC(), cpe.obtCCE());
        PR = null;
        PRC = null;
        PRE = null;
        PRG = null;
        PRCC = null;
        PRCO = null;

        reiniciarCacheCon();
        reiniciarCacheEv();
        reiniciarCacheRel();

        cpc.modCPR(this);
        cpe.modCR(this);
    }

    public PanelRelaciones obtPanel() {
        if (PR == null) PR = new PanelRelaciones(this);
        switch (pan) {
            case 1:
                PRG.actualizar();
                break;
            case 2:
                PRC.actualizar();
                break;
            case 3:
                PRE.actualizar();
                break;
            case 4:
                PRCC.actualizar();
                break;
            case 5:
                PRCO.actualizar();
                break;
        }
        return PR;
    }

    public PanelRelacionesGeneral obtPanelRG() {
        if (PRG == null) PRG = new PanelRelacionesGeneral(this);
        pan = 1;
        PRG.actualizar();
        return PRG;
    }

    public PanelRelacionesCongresista obtPanelRC() {
        if (PRC == null) PRC = new PanelRelacionesCongresista(this);
        pan = 2;
        PRC.actualizar();
        return PRC;
    }

    public PanelRelacionesEvento obtPanelRE() {
        if (PRE == null) PRE = new PanelRelacionesEvento(this);
        pan = 3;
        PRE.actualizar();
        return PRE;
    }

    public PanelRCConjuntos obtPanelRCC() {
        if (PRCC == null) PRCC = new PanelRCConjuntos(this);
        pan = 4;
        PRCC.actualizar();
        return PRCC;
    }

    public PanelRCOperaciones obtPanelRCO() {
        if (PRCO == null) PRCO = new PanelRCOperaciones(this);
        pan = 5;
        PRCO.actualizar();
        return PRCO;
    }

    public void actualizar(){
        if(PR!=null) {
            switch (pan) {
                case 1:
                    PRG.actualizar();
                    break;
                case 2:
                    PRC.actualizar();
                    break;
                case 3:
                    PRE.actualizar();
                    break;
                case 4:
                    PRCC.actualizar();
                    break;
                case 5:
                    PRCO.actualizar();
                    break;
            }
        }
    }

    public boolean esVacioCongreso(){
        return CPC.obtCC().esVacio();
    }

    public boolean esVacioEventos(){
        return CPE.obtCCE().size()==0;
    }

    public boolean esVacioRelaciones(){
        return CR.esVacio();
    }

    public int sizeCongreso(){
        return CPC.size();
    }

    public int sizeEventos() {
        return CPE.obtCCE().size();
    }

    public int sizeRelaciones() {
        try {
            return CR.size();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int sizeResCache(){
        return CR.sizeCache();
    }

    public int sizeConjuntos(){
        return conj.length;
    }

    public void cargarConjuntos(){
        conj = CR.consultarConjuntos().split("\n");
    }

    public String obtConjunto(int i){
        return conj[i];
    }

    public int sizeCompuestas(){
        return comp.length;
    }

    public void cargarCompuestas(){
        comp = CR.consultarCompuestas().split("\n");
    }

    public String obtCompuesta(int i){
        return comp[i];
    }

    public int sizeCongresistasComp(){
        return cong.length;
    }

    public void cargarCongresistasComp(int id){
        cong = CR.consultarCongresistasComp(id).split("\n");
        if (cong.length==1 && cong[0]=="") cong = new String[0];
    }

    public String obtCongresistaComp(int i){
        return cong[i];
    }

    public String obtDescripcionComp(int id){
        return CR.consultarDescripcionComp(id);
    }

    public void agregarConjuntoSinVoto(String nombre, String fecha, String congresistas) {
        try {
            CR.agregarConjuntoSinVoto(nombre,fecha,congresistas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarConjuntoConVoto(String nombre, String fecha, String voto, String congresistas) {
        try {
            CR.agregarConjuntoConVoto(nombre, fecha, voto, congresistas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarCompuestaAnd(int i, int d){
        try {
            CR.agregarCompuestaAnd(i, d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarCompuestaOr(int i, int d){
        try {
            CR.agregarCompuestaOr(i, d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarCompuestaNot(int id){
        try {
            CR.agregarCompuestaNot(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deshacerCompuesta(int id){
        try {
            CR.deshacerCompuesta(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deshacerTodasCompuestas() {
        try {
            CR.deshacerTodasCompuestas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarConjunto(int id){
        try {
            CR.eliminarConjunto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reiniciarCacheCon(){
        RUCon = 1;
        indCon = new int[2];
        indCon[0] = -1;
        indCon[1] = -1;
        bCon = new String[2][];
    }

    public void reiniciarCacheEv(){
        RUEv = 1;
        indEv = new int[2];
        indEv[0] = -1;
        indEv[1] = -1;
        bEv = new String[2][];
    }

    public void reiniciarCacheRel(){
        RURel = 1;
        indRel = new int[2];
        indRel[0] = -1;
        indRel[1] = -1;
        bRel = new String[2][];
    }

    public String obtCongresistaCache(int i){
        String res;
        int bloque = i/tam_bloque;
        // Comprobar si está en el primer bloque.
        if (indCon[0] == bloque){
            res = bCon[0][i%tam_bloque];
            RUCon = 0;
        }
        // Si está en el segundo.
        else if (indCon[1] == bloque){
            res = bCon[1][i%tam_bloque];
            RUCon = 1;
        }
        // Si no está en ninguno.
        else {
            if(RUCon==1) RUCon = 0;
            else RUCon = 1;
            indCon[RUCon] = bloque;
            bCon[RUCon] = CPC.obtCC().obtBloquePR(bloque,tam_bloque).split("\n");
            res = bCon[RUCon][i%tam_bloque];
        }
        return res;
    }

    public String obtEventoCache(int i){
        String res;
        int bloque = i/tam_bloque;
        // Comprobar si está en el primer bloque.
        if (indEv[0] == bloque){
            res = bEv[0][i%tam_bloque];
            RUEv = 0;
        }
        // Si está en el segundo.
        else if (indEv[1] == bloque){
            res = bEv[1][i%tam_bloque];
            RUEv = 1;
        }
        // Si no está en ninguno.
        else {
            if(RUEv==1) RUEv = 0;
            else RUEv = 1;
            indEv[RUEv] = bloque;
            bEv[RUEv] = CPE.obtCCE().obtBloquePR(bloque,tam_bloque).split("\n");
            res = bEv[RUEv][i%tam_bloque];
        }
        return res;
    }

    public String obtRelacionesCache(int i){
        String res;
        int bloque = i/tam_bloque;
        // Comprobar si está en el primer bloque.
        if (indRel[0] == bloque){
            int ini = bloque * tam_bloque;
            res = bRel[0][i-ini];
            RURel = 0;
        }
        // Si está en el segundo.
        else if (indRel[1] == bloque){
            res = bRel[1][i%tam_bloque];
            RURel = 1;
        }
        // Si no está en ninguno.
        else {
            if(RURel==1) RURel = 0;
            else RURel = 1;
            indRel[RURel] = bloque;
            try {
                bRel[RURel] = CR.obtBloquePR(bloque,tam_bloque).split("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            res = bRel[RURel][i%tam_bloque];
        }
        return res;
    }

    public void cargarCache(String dni){
        try {
            CR.cargarCache(dni);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarCache(String nombre, String fecha){
        try {
            CR.cargarCache(nombre, fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String obtResCache(int i){
        String res;
        int bloque = i/tam_bloque;
        // Comprobar si está en el primer bloque.
        if (indRel[0] == bloque){
            res = bRel[0][i%tam_bloque];
            RURel = 0;
        }
        // Si está en el segundo.
        else if (indRel[1] == bloque){
            res = bRel[1][i%tam_bloque];
            RURel = 1;
        }
        // Si no está en ninguno.
        else {
            if(RURel==1) RURel = 0;
            else RURel = 1;
            indRel[RURel] = bloque;
            try {
                bRel[RURel] = CR.obtBloqueCachePR(bloque,tam_bloque).split("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            res = bRel[RURel][i%tam_bloque];
        }
        return res;
    }

    public void agregarRelacion(String dni, String nombre, String fecha) {
        try {
            CR.agregarRelacion(dni, nombre, fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarVoto(String dni, String nombre, String fecha, String voto) {
        try {
            CR.agregarVoto(dni, nombre, fecha, voto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarRelacionRandom(int n){
        try {
            CR.agregarRelacionRandom(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarRelacion(String dni, String nombre, String fecha) {
        try {
            CR.eliminarRelacion(dni, nombre, fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarVoto(String dni, String nombre, String fecha, String voto) {
        try {
            CR.eliminarVoto(dni, nombre, fecha, voto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarRelaciones(){
        try {
            CR.eliminarRelaciones();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardar(String ruta){
        try {
            CR.guardar(ruta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(obtPanel(),
                    "Se ha producido un error al guardar el proyecto.",
                    "Guardar proyecto",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargar(String ruta){
        try {
            CR.cargar(ruta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(obtPanel(),
                    "Se ha producido un error al cargar el proyecto.",
                    "Abrir proyecto",
                    JOptionPane.ERROR_MESSAGE);
        }
        CPC.actualizar();
        CPE.actualizar();
        actualizar();
    }

    public String obtCongresista(String dni) {
        return CPC.obtCongresista(dni);
    }

}
