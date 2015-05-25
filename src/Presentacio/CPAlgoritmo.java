package Presentacio;


public class CPAlgoritmo {

    private PanelAlgoritmo pa;

    public CPAlgoritmo() {
    }

    public PanelAlgoritmo obtPanel() {
        if (pa == null) pa = new PanelAlgoritmo(this);
        return pa;
    }

}
