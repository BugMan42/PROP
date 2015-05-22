package Presentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Created by falc on 20/05/15.
 */
public class PanelAlgoritmo extends Panel{

    CPAlgoritmo cva;




    public PanelAlgoritmo(CPAlgoritmo cont) {
        super();
        cva = cont;
        initUI();
        setLayout(new BorderLayout());
    }

    public PanelAlgoritmo() {
        super();
        initUI();
    }

    private void initUI()
    {
        final JLabel rekt = new JLabel("This is...");
        rekt.setBorder(BorderFactory.createLineBorder(Color.black));
        add(rekt, BorderLayout.SOUTH);
    }
}
