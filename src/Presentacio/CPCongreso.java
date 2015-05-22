package Presentacio;

import Domini.ControladorCongreso;


public class CPCongreso {

    private PanelCongreso PC = null;
    private ControladorCongreso CC;

    public CPCongreso()
    {
    }

    public PanelCongreso obtPanel() {
        if (PC == null) PC = new PanelCongreso(this);
        return PC;
    }
}
