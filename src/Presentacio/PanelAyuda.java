package Presentacio;

import sun.awt.VerticalBagLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by falc on 3/06/15.
 */
public class PanelAyuda extends Panel {
    public PanelAyuda()
    {
        super();
        initUI();
    }
    private void initUI()
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel titulo = new JLabel("AYUDA");
        titulo.setFont(new Font("Ubuntu", 0, 20));
        add(titulo);

        JLabel vc = new JLabel("Rueda del raton: Zoom In / Zoom Out");
        add(vc);

        JLabel vc2 = new JLabel("Botón central del ratón: Centrar vista en punto");
        add(vc2);
    }
}
