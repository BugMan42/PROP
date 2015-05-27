package Presentacio;


import Domini.ControladorAlgoritmo;

public class CPAlgoritmo {

    private PanelAlgoritmo PA;
    private ControladorAlgoritmo CA;


    public CPAlgoritmo(CPRelaciones cpr) {
        CA = new ControladorAlgoritmo(cpr.CR);
    }

    public PanelAlgoritmo obtPanel() {
        if (PA == null) PA = new PanelAlgoritmo(this);
        return PA;
    }



}
