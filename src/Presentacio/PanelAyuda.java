package Presentacio;

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
        JLabel titulo = new JLabel("AYUDA");
        titulo.setFont(new Font("Ubuntu", 0, 20));
        add(titulo);
    }
}
