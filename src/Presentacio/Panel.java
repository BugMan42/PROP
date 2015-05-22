package Presentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Created by falc on 21/05/15.
 */
public class Panel extends JPanel {

    private VistaPrincipal vis;

    public Panel()
    {
        super();
        setBorder(BorderFactory.createLineBorder(Color.cyan));
    }

    public Panel(VistaPrincipal v)
    {
        super();
        setBorder(BorderFactory.createLineBorder(Color.cyan));
        vis = v;
    }

    public void establecerVista(VistaPrincipal v)
    {
        vis = v;
    }

    public VistaPrincipal obtenerVista()
    {
        return vis;
    }
}
