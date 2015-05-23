package Presentacio;

import Domini.ControladorCongreso;


public class CPCongreso {

    PanelCongreso PC = null;
    ControladorCongreso CC;

    public CPCongreso() {
        CC = new ControladorCongreso();
        try {
            CC.agregarCongresistaRandom(1000);
        }catch (Exception e) {
            //WTF
        }
    }

    public PanelCongreso obtPanel() {
        if (PC == null) PC = new PanelCongreso(this);
        return PC;
    }
}
