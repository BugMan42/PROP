package Presentacio;

import Domini.ControladorCjtEvento;
import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;

import java.util.ArrayList;

//Por defecto cambiar si conviene
public class CPRelaciones {

    private CPCongreso CPC;
    private CPEventos CPE;
    private ControladorRelaciones CR;
    private PanelRelaciones PR;
    private PanelRelacionesCongresista PRC;
    private PanelRelacionesEvento PRE;
    private PanelRelacionesGeneral PRG;
    private int pan;

    public ControladorRelaciones obtCR(){
        return CR;
    }

    public CPRelaciones(CPCongreso cpc, CPEventos cpe) {
        CPC = cpc;
        CPE = cpe;

        CR = new ControladorRelaciones(cpc.obtCC(), cpe.CCE);
        PR = null;
        PRC = null;
        PRE = null;
        PRG = null;

        cpc.modCPR(this);
        cpe.CR = this;
    }

    public PanelRelaciones obtPanel() throws Exception {
        if (PR == null) PR = new PanelRelaciones(this);
        switch (pan){
            case 1: PRC.actualizar();
                    break;
            case 2: PRE.actualizar();
                    break;
            case 3: PRG.actualizar();
                    break;
        }
        return PR;
    }

    public PanelRelacionesCongresista obtPanelRC() throws Exception {
        if (PRC == null) PRC = new PanelRelacionesCongresista(this);
        pan = 1;
        PRC.actualizar();
        return PRC;
    }

    public PanelRelacionesEvento obtPanelRE(){
        if (PRE == null) PRE = new PanelRelacionesEvento(this);
        pan = 2;
        PRE.actualizar();
        return PRE;
    }

    public PanelRelacionesGeneral obtPanelRG(){
        if (PRG == null) PRG = new PanelRelacionesGeneral(this);
        pan = 3;
        PRG.actualizar();
        return PRG;
    }

    public String[] obtCongreso(){
        return CPC.obtCC().obtCongresoPR().split("\n");
    }

    public String[] obtEventos(){
        return CPE.obtCCE().obtEventosPR().split("\n");
    }

    public String[] obtRelaciones() throws Exception {
        return CR.obtRelacionesPR().split("\n");
    }

    public String[] obtRelaciones(String dni) throws Exception {
        String[] res;
        if (CR.tieneRelaciones(dni)) res = CR.obtRelacionesPR(dni).split("\n");
        else res = new String[0];
        return res;
    }

    public void agregarRelacion(String dni, String nombre, String fecha) throws Exception {
        CR.agregarRelacion(dni,nombre,fecha);
    }

    public void agregarVoto(String dni, String nombre, String fecha, String voto) throws Exception {
        CR.agregarVoto(dni, nombre, fecha, voto);
    }

    public void eliminarRelacion(String dni, String nombre, String fecha) throws Exception {
        CR.eliminarRelacion(dni,nombre,fecha);
    }
}
