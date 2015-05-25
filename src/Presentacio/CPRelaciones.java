package Presentacio;

import Domini.ControladorCjtEvento;
import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;

//Por defecto cambiar si conviene
public class CPRelaciones {
    PanelRelaciones PR;
    ControladorRelaciones CR;
    CPCongreso CPC;
    CPEventos CPE;

    public CPRelaciones(CPCongreso cpc, CPEventos cpe) {
        CPC = cpc;
        CPE = cpe;

        CR = new ControladorRelaciones(cpc.CC, cpe.CCE);

        cpc.CR = this;
        cpe.CR = this;
    }

    public PanelRelaciones obtPanel() {
        if (PR == null) PR = new PanelRelaciones(this);
        return PR;
    }
}
