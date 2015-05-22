package Presentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Created by falc on 21/05/15.
 */
public class Panel extends JPanel {

    private Vista vis;

    public Panel()
    {
        super();
        setBorder(BorderFactory.createLineBorder(Color.cyan));
    }

    public Panel(Vista v)
    {
        super();
        setBorder(BorderFactory.createLineBorder(Color.cyan));
        vis = v;
    }

    public void establecerVista(Vista v)
    {
        vis = v;
    }

    public Vista obtenerVista()
    {
        return vis;
    }
}
