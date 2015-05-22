package Presentacio;

/**
 * Created by bug on 22/05/15.
 */
public class CPCongreso {

    private PanelCongreso congreso = null;

    public CPCongreso()
    {
    }

    public PanelCongreso obtPanel() {
        if (congreso == null) congreso = new PanelCongreso(this);
        return congreso;
    }
}
