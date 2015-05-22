package Presentacio;

/**
 * Created by falc on 20/05/15.
 */
public class CPAlgoritmo {

    private PanelAlgoritmo pa;

    public CPAlgoritmo()
    {
    }

    public PanelAlgoritmo obtPanel() {
        if (pa == null) pa = new PanelAlgoritmo(this);
        return pa;
    }

}
