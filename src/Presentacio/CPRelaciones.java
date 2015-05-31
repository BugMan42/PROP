package Presentacio;

import Domini.ControladorRelaciones;

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
    private int RUCon, RUEv, RURel;
    private int[][] indCon, indEv, indRel;
    private String[][] bCon;
    private String[][] bEv;
    private String[][] bRel;

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

        reiniciarBCon();
        //reiniciarBEv();
        //reiniciarBRel();

        cpc.modCPR(this);
        cpe.modCR(this);
    }

    public PanelRelaciones obtPanel() {
        if (PR == null) PR = new PanelRelaciones(this);
        try {
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
            }
        } catch (Exception e1) {
            e1.printStackTrace();
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

    public void reiniciarBCon(){
        RUCon = 1;
        indCon = new int[2][2];
        indCon[0][0] = -1;
        indCon[0][1] = -1;
        indCon[1][0] = -1;
        indCon[1][1] = -1;
        bCon = new String[2][];
    }
/*
    public void reiniciarBEv(){
        RUEv = 1;
        indEv[0] = new Pair<Integer, Integer>(-1,-1);
        indEv[1] = new Pair<Integer, Integer>(-1,-1);
    }
    public void reiniciarBRel(){
        RURel = 1;
        indRel[0] = new Pair<Integer, Integer>(-1,-1);
        indRel[1] = new Pair<Integer, Integer>(-1,-1);
    }*/

    public String[] obtCongreso(){
        return CPC.obtCC().obtCongresoPR().split("\n");
    }

    public String obtCongresista(int i){
        String res;
        // Comprobar si está en el primer bloque.
        if (indCon[0][0]<=i && indCon[0][1]>=i){
            int ini = (i/tam_bloque) * tam_bloque;
            res = bCon[0][i-ini];
            RUCon = 0;
        }
        // Si está en el segundo.
        else if (indCon[1][0]<=i && indCon[1][1]>=i){
            int ini = (i/tam_bloque) * tam_bloque;
            res = bCon[1][i-ini];
            RUCon = 1;
        }
        // Si no está en ninguno.
        else {
            if(RUCon==1) RUCon = 0;
            else RUCon = 1;
            int ini = (i/tam_bloque) * tam_bloque;
            int fin = ini + tam_bloque - 1;
            indCon[RUCon][0] = ini;
            indCon[RUCon][1] = fin;
            bCon[RUCon] = CPC.obtCC().obtBloquePR(i,tam_bloque).split("\n");
            res = bCon[RUCon][i-ini];
        }
        return res;
    }

    public String[] obtBloqueCongreso(int i, int tam){
        return CPC.obtCC().obtenerBloqueCongresoPR(i, tam).split("\n");
    }

    public String[] obtEventos(){
            return CPE.obtCCE().obtEventosPR().split("\n");
    }

    public String[] obtRelaciones() {
        try {
            return CR.obtRelacionesPR().split("\n");
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return new String[0];
    }

    public String[] obtRelaciones(String dni) {
        try {
            if (CR.tieneRelaciones(dni)) return CR.obtRelacionesPR(dni).split("\n");
            else return new String[0];
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return new String[0];
    }

    public String[] obtRelaciones(String nombre, String fecha) {
         try {
            if (CR.tieneRelaciones(nombre, fecha)) return CR.obtRelacionesPR(nombre, fecha).split("\n");
            else return new String[0];
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return new String[0];
    }

    public void agregarRelacion(String dni, String nombre, String fecha) {
        try {
            CR.agregarRelacion(dni,nombre,fecha);
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
        CR.eliminarRelaciones();
    }

}
