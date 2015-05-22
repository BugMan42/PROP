package Presentacio;

import javax.swing.*;

/**
 * Created by Jose on 09/05/2015.
 */
public class VistaPrincipal1 extends JFrame {

    private VistaMenu vm;

    public VistaPrincipal1(){
        vm = new VistaMenu();

        setTitle("Diputats dels EUA");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(vm);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
