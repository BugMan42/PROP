package Presentacio;

/**
 * Created by bug on 22/05/15.
 */
public class CPCongreso {
    private PanelCongreso congreso;

    public CPAlgoritmo()
    {
    }

    public PanelAlgoritmo obtPanel() {
        if (congreso == null) congreso = new PanelAlgoritmo(this);
        return congreso;
    }
}
