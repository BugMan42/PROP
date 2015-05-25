package Presentacio;

import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;


public class CPCongreso {

    PanelCongreso PC = null;
    ControladorCongreso CC;
    CPRelaciones CR;

    public CPCongreso() {
        CC = new ControladorCongreso();
    }

    public PanelCongreso obtPanel() {
        if (PC == null) PC = new PanelCongreso(this);
        return PC;
    }
}
