package Presentacio;

import javax.swing.*;

/**
 * Created by falc on 11/05/15.
 */
public class DriverVista {

    public static void main (String[] args)
    {
        final ControladorPresentacion cp = new ControladorPresentacion();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                VistaPrincipal frame = cp.getVista();

                //Display the window.
                frame.pack();
                frame.setVisible(true);
            }
        });

    }


}
