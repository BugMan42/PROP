package Presentacio;

import Domini.ControladorCjtEvento;
import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;

//Por defecto cambiar si conviene
public class CPRelaciones {

    CPCongreso CPC;
    CPEventos CPE;
    ControladorRelaciones CR;
    PanelRelaciones PR;
    PanelRelacionesCongresista PRC;
    PanelRelacionesEvento PRE;
    PanelRelacionesGeneral PRG;


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


    public PanelRelaciones obtPanel() {
        if (PR == null) PR = new PanelRelaciones(this);
        return PR;
    }

    public PanelRelacionesCongresista obtPanelRC(){
        if (PRC == null) PRC = new PanelRelacionesCongresista();
        return PRC;
    }

    public PanelRelacionesEvento obtPanelRE(){
        if (PRE == null) PRE = new PanelRelacionesEvento();
        return PRE;
    }

    public PanelRelacionesGeneral obtPanelRG(){
        if (PRG == null) PRG = new PanelRelacionesGeneral();
        return PRG;
    }
}
