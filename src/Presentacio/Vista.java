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
        setTitle("Vista");
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

        // Item de menú 3 (Salir)
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

        /// Menú 2 (Ayuda)

        JMenu menu2 = new JMenu("Ayuda");

        JMenuItem item5 = new JMenuItem("Ayuda", KeyEvent.VK_H);
        item5.setToolTipText("Consulta la ayuda");
        menu2.add(item5);

        menuBar.add(menu2);

        add(menuBar, BorderLayout.NORTH);


        /// Panel con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = panelCongreso(); //makeTextPanel("Panel #1");
        tabbedPane.addTab("Congresistas", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Eventos", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Algoritmo y parámetros", panel3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent panel4 = makeTextPanel(
                "Panel #4 (has a preferred size of 410 x 50).");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Grafo y comunidades", panel4);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);


        JComponent panel5 = makeTextPanel("Panel #5");
        tabbedPane.addTab("Miscelánea", panel5);
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);


        // [this] Operación heredada de JFrame, añade el panel con pestañas a Vista.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }

    protected JComponent panelCongreso()
    {
        JPanel panel = new JPanel();
        JLabel name = new JLabel("Nombre:");
        JLabel surname = new JLabel("Apellido:");
        JLabel dni = new JLabel("DNI:");
        JLabel partido = new JLabel("Partido:");
        JLabel age = new JLabel("Edad:");
        JLabel city = new JLabel("Ciudad:");
        JLabel state = new JLabel("Estado:");
        final JTextField name_field = new JTextField("Introduzca nombre");
        name_field.setMaximumSize(new Dimension(200, 25));
        final JTextField dni_field = new JTextField("00000000A");
        dni_field.setMaximumSize(new Dimension(200, 25));
        final JTextField surname_field = new JTextField("Introduzca apellido");
        surname_field.setMaximumSize(new Dimension(200, 25));
        final JTextField partido_field = new JTextField("Introduzca partido");
        partido_field.setMaximumSize(new Dimension(200, 25));
        final JTextField age_field = new JTextField("21");
        age_field.setMaximumSize(new Dimension(200, 25));
        final JTextField city_field = new JTextField("Introduzca ciudad");
        city_field.setMaximumSize(new Dimension(200, 25));
        final JTextField state_field = new JTextField("Introduzca estado");
        state_field.setMaximumSize(new Dimension(200, 25));

        final JTextArea error_field = new JTextArea("No errors found");
        error_field.setMaximumSize(new Dimension(200, 50));
        error_field.setEditable(false);


        final JButton acceptButton;
        acceptButton = new JButton("Aceptar");
        acceptButton.setEnabled(false);

        //JButton delete = new JButton("Eliminar");

        final DefaultListModel def = new DefaultListModel();
        final JList name_list = new JList(def);

        name_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        name_list.setLayoutOrientation(JList.VERTICAL);
        //Acción realizada al seleccionar un elemento
        name_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    if (name_list.getSelectedIndex() == -1) {
                        //No selection, disable fire button.
                        acceptButton.setEnabled(false);

                    } else {
                        //Selection, enable the fire button.
                        acceptButton.setEnabled(true);
                        String dato = (String) name_list.getSelectedValue();
                        error_field.setText("#" + name_list.getSelectedIndex());

                        // Del congresista a los campos
                        String[] campos = dato.split(" ");

                        if (campos.length >=2 && campos[1].charAt(0) == '[')
                        {
                            String iden = campos[1].substring(1,campos[1].length()-1);
                            name_field.setText(campos[0]);
                            dni_field.setText(iden);

                            error_field.append("\n");
                            error_field.append(dato);
                            error_field.append("\n");
                            error_field.append(iden);
                        }
                        else
                        {
                            name_field.setText(campos[0]);
                        }



                    }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(name_list);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int in = name_list.getSelectedIndex();
                String nom = name_field.getText();
                String iden = dni_field.getText();

                Congresista cong = null;

                try {
                    cp.getControlCongreso().agregarCongresista(dni_field.getText(), name_field.getText(), surname_field.getText(), Integer.parseInt(age_field.getText()), city_field.getText(), partido_field.getText(), state_field.getText());
                    cong = cp.getControlCongreso().consultarCongresista(iden);
                    error_field.setText("");
                    def.setElementAt(nom + " [" + iden + "]", in);
                } catch (Exception e1) {
                    //e1.printStackTrace();
                    error_field.setText(e1.getMessage());
                }



            }
        });

        //Botón eliminar
        ImageIcon i_del = new ImageIcon("images/del.png");
        final JButton del = new JButton(i_del);
        del.setEnabled(false);
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String iden = dni_field.getText();
                def.remove(name_list.getSelectedIndex());


                try {
                    cp.getControlCongreso().eliminarCongresista(iden, cp.getControlRelaciones());
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                }

                if (def.getSize()-1 < 0 ) del.setEnabled(false);
                else name_list.setSelectedIndex(def.getSize()-1);

            }
        });

        //Botón añadir
        ImageIcon i_add = new ImageIcon("images/add.png");
        JButton add = new JButton(i_add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.addElement("Nuevo");
                //name_field.setText("Insert troll here");
                name_field.requestFocusInWindow();
                //surname_field.setText("Insert meme here");
                //dni_field.setText("66666666X");
                //partido_field.setText("Insert RageQuit here");
                //age_field.setText("21");
                //city_field.setText("Insert Mordor here");
                name_list.setSelectedIndex(def.getSize() - 1);


                del.setEnabled(true);
            }
        });


        GroupLayout gr = new GroupLayout(panel);
        panel.setLayout(gr);
        gr.setAutoCreateGaps(true);
        gr.setAutoCreateContainerGaps(true);


        // Layout
        gr.setHorizontalGroup(
                gr.createSequentialGroup()
                        .addGroup(gr.createParallelGroup()
                                        .addComponent(scroll)
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(add)
                                                        .addComponent(del)
                                        )


                        )
                        .addGroup(gr.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gr.createSequentialGroup()
                                                        .addGroup(gr.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(name)
                                                                        .addComponent(name_field)
                                                                        .addComponent(dni)
                                                                        .addComponent(dni_field)
                                                                        .addComponent(age)
                                                                        .addComponent(age_field)
                                                                        .addComponent(state)
                                                                        .addComponent(state_field)
                                                        )
                                                        .addGroup(gr.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(surname)
                                                                        .addComponent(surname_field)
                                                                        .addComponent(partido)
                                                                        .addComponent(partido_field)
                                                                        .addComponent(city)
                                                                        .addComponent(city_field)
                                                        )
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(error_field)
                                                                //.addComponent(delete)
                                                        .addComponent(acceptButton)
                                        )
                        )
        );

        gr.setVerticalGroup(
                gr.createParallelGroup()
                        .addGroup(gr.createSequentialGroup()
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(add)
                                                        .addComponent(del)
                                        )
                                        .addComponent(scroll)

                        )
                        .addGroup(gr.createSequentialGroup()
                                        .addGroup(gr.createParallelGroup()
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(name)
                                                                        .addComponent(name_field)
                                                        )
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(surname)
                                                                        .addComponent(surname_field)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(dni)
                                                                        .addComponent(dni_field)
                                                        )
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(partido)
                                                                        .addComponent(partido_field)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(age)
                                                                        .addComponent(age_field)
                                                        )
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(city)
                                                                        .addComponent(city_field)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(state)
                                                                        .addComponent(state_field)
                                                        )
                                                        /*.addGroup(gr.createSequentialGroup()
                                                                        .addComponent(city)
                                                                        .addComponent(city_field)
                                                        )*/
                                        )
                                        .addGroup(gr.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(error_field)
                                                                //.addComponent(delete)
                                                        .addComponent(acceptButton)
                                        )
                        )


        );


        return panel;
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
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
