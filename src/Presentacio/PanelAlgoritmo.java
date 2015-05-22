package Presentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Created by falc on 20/05/15.
 */
public class PanelAlgoritmo extends Panel{

    CPAlgoritmo cpa;

    public PanelAlgoritmo(CPAlgoritmo cont) {
        super();
        cpa = cont;
        initUI();
    }

    public PanelAlgoritmo() {
        super();
        initUI();
    }

    private void initUI()
    {
        final JLabel rekt = new JLabel("This is...");
        rekt.setBorder(BorderFactory.createLineBorder(Color.red));
        add(rekt, BorderLayout.SOUTH);
    }
}
