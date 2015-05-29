import Presentacio.ControladorPresentacion;
import Presentacio.VistaPrincipal;

import javax.swing.*;
import java.awt.*;

public class Main {

    private ControladorPresentacion cp;

    /*public static void main(String[] args) {
        Main m = new Main();
        m.cp = new ControladorPresentacion();
    }*/

    public static void main(String[] args) {
        lf(true,0);
        javax.swing.SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        ControladorPresentacion ctrlPres = new ControladorPresentacion();
                        ctrlPres.inicializarPresentacion();
                    }
                }
        );
    }
    private static void lf(boolean op,int e) {
        if (op) {
            //String[] eleccion = {"javax.swing.plaf.metal","Nimbus","GTK+","Motif","IBM*","Macintosh*","Windows Vista","Windows XP", "Windows"};
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        //UIManager.put("defaultFont", new Font("Arial", Font.PLAIN, 16));
                        break;
                    }
                }
            } catch (Exception enim) {
                // If Nimbus is not available, fall back to cross-platform
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    // not worth my time
                }
            }

        }
    }
}
