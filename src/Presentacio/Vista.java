package Presentacio;

import Domini.Congresista;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by falc on 7/05/15.
 *
 */

public class Vista extends JFrame {

    //Referència al controlador de presentació que crea la vista
    ControladorPresentacion cp;

    public Vista(ControladorPresentacion c)
    {
        cp = c;
        initUI();
    }

    public Vista() {

        // Inicializa los componentes de la ventana
        initUI();
    }

    private void initUI()
    {
        // [this] Operación heredada de JFrame, define el título de la ventana
        setTitle("Graph Communities");
        // [this] Operación heredada de JFrame, define el método por defecto para el cierre
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /// Barra de menús
        JMenuBar menuBar = new JMenuBar();

        /// Menú 1 (Archivo)

        JMenu menu1 = new JMenu("Archivo");
        menu1.setMnemonic(KeyEvent.VK_A);

        // Item de menú 1 (Nuevo)
        JMenuItem item1 = new JMenuItem("Nuevo", KeyEvent.VK_N);
        item1.setToolTipText("Nuevo proyecto");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle("Proyecto ~");
            }
        });
        menu1.add(item1);

        // Item de menú 2 (Cargar)
        JMenuItem item2 = new JMenuItem("Cargar", KeyEvent.VK_L);
        item2.setToolTipText("Cargar un proyecto");
        /*item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });*/
        menu1.add(item2);

        // Item de menú 3 (Guardar)
        JMenuItem item3 = new JMenuItem("Guardar", KeyEvent.VK_S);
        item3.setToolTipText("Guardar proyecto");
        /*item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });*/
        menu1.add(item3);

        menu1.addSeparator();

        // Item de menú 4 (Salir)
        ImageIcon i_exit = new ImageIcon("images/exit.png");
        JMenuItem item4 = new JMenuItem("Salir", KeyEvent.VK_X);
        item4.setIcon(i_exit);
        item4.setToolTipText("Salir de la aplicación");
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu1.add(item4);

        menuBar.add(menu1);

        JMenu menu1_5 = new JMenu("Editar");

        JMenuItem mi_cong = new JMenuItem("Congresistas", KeyEvent.VK_C);
        mi_cong.setToolTipText("Editar congresistas");
        menu1_5.add(mi_cong);

        JMenuItem mi_eve = new JMenuItem("Eventos", KeyEvent.VK_E);
        mi_cong.setToolTipText("Editar eventos");
        menu1_5.add(mi_eve);

        menuBar.add(menu1_5);

        /// Menú 2 (Ayuda)

        JMenu menu2 = new JMenu("Ayuda");

        JMenuItem item5 = new JMenuItem("Ayuda", KeyEvent.VK_H);
        item5.setToolTipText("Consulta la ayuda");
        menu2.add(item5);

        menuBar.add(menu2);

        setJMenuBar(menuBar);

        //Toolbar (será la sustituta del TabbedPane)
        JToolBar toolbar = new JToolBar("REKT");
        toolbar.setOrientation(0);
        JButton bCongreso = new JButton("Congresistas");
        toolbar.add(bCongreso);
        JButton bEventos = new JButton("Eventos");
        toolbar.add(bEventos);
        JButton bRelaciones = new JButton("Relaciones");
        toolbar.add(bRelaciones);
        JButton bAlgoritmo = new JButton("Algoritmo y preferencias");
        toolbar.add(bAlgoritmo);
        add(toolbar, BorderLayout.NORTH);

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        Vista frame = new Vista();

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
