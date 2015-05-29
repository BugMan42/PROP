package Presentacio;


import Domini.ControladorAlgoritmo;

public class CPAlgoritmo {

    private PanelAlgoritmo PA;
    private ControladorAlgoritmo CA;


    public CPAlgoritmo(CPRelaciones cpr) {
        CA = new ControladorAlgoritmo(cpr.obtCR());
    }

    public PanelAlgoritmo obtPanel() {
        if (PA == null) PA = new PanelAlgoritmo(this);
        return PA;
    }



}
