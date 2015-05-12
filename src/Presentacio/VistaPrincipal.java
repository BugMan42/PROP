package Presentacio;

import javax.swing.*;

/**
 * Created by Jose on 09/05/2015.
 */
public class VistaPrincipal extends JFrame {

    private VistaMenu vm;

    public VistaPrincipal(){
        vm = new VistaMenu();

        setTitle("Diputats dels EUA");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(vm);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
