package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;

/**
 * Created by falc on 7/05/15.
 *
 */

public class VistaPrincipal extends JFrame {

    //Referència al controlador de presentació que crea la vista
    ControladorPresentacion cp;
    JPanel pan;

    public VistaPrincipal(ControladorPresentacion c)
    {
        cp = c;
        setMinimumSize(new Dimension(470, 350));
        initUI();

    }

    public VistaPrincipal() {

        // Inicializa los componentes de la ventana
        initUI();
    }

    public void setCP(ControladorPresentacion c)
    {
        cp = c;
    }

    private void initUI()
    {
        pan = cp.getCPC().obtPanel();
        //pan = cp.getCPA().obtPanel();

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
        //menu1_5.putClientProperty(TextAttribute.SIZE, 18);

        JMenuItem mi_cong = new JMenuItem("Congreso", KeyEvent.VK_C);
       // mi_cong.putClientProperty(TextAttribute.SIZE, 18);
        mi_cong.setToolTipText("Editar congresistas");
        mi_cong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                pan = cp.getCPC().obtPanel();
                add(pan);
                invalidate();
                revalidate();
                repaint();
            }
        });
        menu1_5.add(mi_cong);

        JMenuItem mi_eve = new JMenuItem("Eventos", KeyEvent.VK_E);
        mi_eve.setToolTipText("Editar eventos");
        mi_eve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                pan = cp.getCPC().obtPanel();
                add(pan);
                invalidate();
                revalidate();
                repaint();
            }
        });
        menu1_5.add(mi_eve);

        JMenuItem mi_rel = new JMenuItem("Relaciones", KeyEvent.VK_R);
        mi_rel.setToolTipText("Editar relaciones");
        menu1_5.add(mi_rel);

        JMenuItem mi_alg = new JMenuItem("Algoritmo", KeyEvent.VK_R);
        mi_alg.setToolTipText("Editar algoritmo y preferencias");
        mi_alg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                pan = cp.getCPA().obtPanel();
                add(pan);
                invalidate();
                revalidate();
                repaint();

            }
        });
        menu1_5.add(mi_alg);

        menuBar.add(menu1_5);

        /// Menú 2 (Ayuda)

        JMenu menu2 = new JMenu("Ayuda");

        JMenuItem item5 = new JMenuItem("Ayuda", KeyEvent.VK_H);
        item5.setToolTipText("Consulta la ayuda");
        menu2.add(item5);

        menuBar.add(menu2);

        setJMenuBar(menuBar);

        //Toolbar (será la sustituta del TabbedPane)
        JToolBar toolbar = new JToolBar("");
        //Evita que la toolbar pueda ser extraída
        toolbar.setFloatable(false);
        //toolbar.setFont(new java.awt.Font("Sans Serif", 0, 17));

        JButton bCongreso = new JButton("Congreso");
        bCongreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                pan = cp.getCPC().obtPanel();
                add(pan);
                invalidate();
                revalidate();
                repaint();

            }
        });
        toolbar.add(bCongreso);
        JButton bEventos = new JButton("Eventos");
        toolbar.add(bEventos);
        bEventos.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(pan);
                        pan = cp.obtCPE().obtPanel();
                        add(pan);
                        invalidate();
                        revalidate();
                        repaint();
                    }
                }
        );
        JButton bRelaciones = new JButton("Relaciones");
        toolbar.add(bRelaciones);
        JButton bAlgoritmo = new JButton("Algoritmo y preferencias");
        bAlgoritmo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                pan = cp.getCPA().obtPanel();
                add(pan);
                invalidate();
                revalidate();
                repaint();
            }
        });
        bCongreso.setFont(new java.awt.Font("Sans Serif", 0, 17));
        bAlgoritmo.setFont(new java.awt.Font("Sans Serif", 0, 17));
        bEventos.setFont(new java.awt.Font("Sans Serif", 0, 17));
        bRelaciones.setFont(new java.awt.Font("Sans Serif", 0, 17));
        toolbar.add(bAlgoritmo);
        add(toolbar, BorderLayout.NORTH);
        add(pan);

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        VistaPrincipal frame = new VistaPrincipal();

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
