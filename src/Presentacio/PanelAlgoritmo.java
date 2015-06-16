package Presentacio;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

/**
 * Clase PanelAlgoritmo
 * Created by falc on 20/05/15.
 */
public class PanelAlgoritmo extends Panel{

    CPAlgoritmo cpa;
    private volatile Graph g = new SingleGraph("");
    private Viewer viewer;
    private ViewPanel view;

    //Lógica interna
    private int option = 0;
    private String[] colors = {"blue", "green", "red", "yellow", "#B280B2", "sienna", "tan", "turquoise", "pink", "khaki",
            "orange", "seagreen", "darkmagenta", "#574926", "cadetblue", "palegreen", "maroon"};
    private String node_id_sel = "";

    private JProgressBar pb1;
    private JProgressBar pb2;
    private JProgressBar pb3;

    private DefaultListModel lm1;
    private DefaultListModel lm2;
    private DefaultListModel lm3;

    private JList<String> lst1;
    private JList<String> lst2;
    private JList<String> lst3;

    private JLabel name_data;
    private JLabel dni_data;
    private JTextField community_data;

    private int im1;
    private int im2;
    private int im3;
    private Ejecutor ej = new Ejecutor(g, colors);

    private JLabel time_data;

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
        //Establecer Layout de PanelAlgoritmo
        GroupLayout gr = new GroupLayout(this);
        setLayout(gr);

        //Inicialización de datos
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        g.addAttribute("ui.antialias");
        g.addAttribute(
                "ui.stylesheet",
                "node { shape: pie-chart; size: 17.5px; text-alignment: above; fill-color: #CCC; stroke-mode: plain; stroke-color: #999; }"+
                        "node:selected { stroke-width: 4px; stroke-color: black; }"+
                        "node:clicked { stroke-width: 4px; stroke-color: black; }"+
                        "edge { fill-color: #777; }");

        viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.disableAutoLayout();
        view = viewer.addDefaultView(false);

        final ViewerPipe fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(new ViewerListener() {
            @Override
            public void viewClosed(String viewName) {
            }

            @Override
            public void buttonPushed(String id) {
                //System.out.println("Button pushed on node "+id);
            }

            @Override
            public void buttonReleased(String id) {
                //System.out.println("Button released on node "+id);
                node_id_sel = id;

            }
        });
        fromViewer.addSink(g);

        //<editor-fold desc="Implementación Zoom">
        // Zoom con rueda ratón
        view.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double scale = view.getCamera().getViewPercent();
                int notches = e.getWheelRotation();
                if (notches > 0)
                {
                    scale *=  (double)notches * 1.25;
                    view.getCamera().setViewPercent(scale);
                }
                else
                {
                    scale *= (double)notches * 0.75;
                    view.getCamera().setViewPercent(-scale);
                }
            }
        });
        //</editor-fold>

        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane[Enabled].backgroundPainter", Color.black);


        //<editor-fold desc="Columna 1 (GN)">


        lm1 = new DefaultListModel();
        lst1 = new JList<String>(lm1);
        lst1.setBackground(Color.black);
        lst1.setForeground(Color.green);
        lst1.setEnabled(false);
        lst1.setOpaque(true);
        lst1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lst1.setSelectedIndex(-1);

        //JScrollPane sp1 = new JScrollPane(tp1);
        JRadioButton rb1 = new JRadioButton("Girvan-Newman");
        final JButton mostrar1 = new JButton("Mostrar grafo");
        final JButton up1 = new JButton(new ImageIcon(getClass().getResource("/images/up.png")));
        up1.setEnabled(false);
        final JButton down1 = new JButton(new ImageIcon(getClass().getResource("/images/down.png")));
        down1.setEnabled(false);
        final JButton top1 = new JButton(new ImageIcon(getClass().getResource("/images/top.png")));
        top1.setEnabled(false);
        final JButton bottom1 = new JButton(new ImageIcon(getClass().getResource("/images/bottom.png")));
        bottom1.setEnabled(false);
        mostrar1.setEnabled(false);
        pb1 = new JProgressBar(0, 100);
        pb1.setValue(0);
        pb1.setStringPainted(true);

        //</editor-fold>

        //<editor-fold desc="Columna 2 (Lv)">

        lm2 = new DefaultListModel();
        lst2 = new JList<String>(lm2);
        lst2.setBackground(new Color(0,0,102));
        lst2.setForeground(new Color(255, 102, 0));
        lst2.setOpaque(true);
        lst2.setEnabled(false);
        lst2.setSelectedIndex(-1);

        JRadioButton rb2 = new JRadioButton("Louvain");
        final JButton mostrar2 = new JButton("Mostrar grafo");
        final JButton up2 = new JButton(new ImageIcon(getClass().getResource("/images/up.png")));
        up2.setEnabled(false);
        final JButton down2 = new JButton(new ImageIcon(getClass().getResource("/images/down.png")));
        down2.setEnabled(false);
        final JButton top2 = new JButton(new ImageIcon(getClass().getResource("/images/top.png")));
        top2.setEnabled(false);
        final JButton bottom2 = new JButton(new ImageIcon(getClass().getResource("/images/bottom.png")));
        bottom2.setEnabled(false);
        mostrar2.setEnabled(false);
        pb2 = new JProgressBar(0, 100);
        pb2.setValue(0);
        pb2.setStringPainted(true);
        //</editor-fold>

        //<editor-fold desc="Columna 3 (CP)">
        lm3 = new DefaultListModel();
        lst3 = new JList<String>(lm3);
        lst3.setBackground(new Color(127, 0, 255));
        lst3.setForeground(new Color(255, 255, 0));
        lst3.setOpaque(true);
        lst3.setEnabled(false);

        JRadioButton rb3 = new JRadioButton("Clique Percolation");
        final JButton mostrar3 = new JButton("Mostrar grafo");
        final JButton up3 = new JButton(new ImageIcon(getClass().getResource("/images/up.png")));
        up3.setEnabled(false);
        final JButton down3 = new JButton(new ImageIcon(getClass().getResource("/images/down.png")));
        down3.setEnabled(false);
        final JButton top3 = new JButton(new ImageIcon(getClass().getResource("/images/top.png")));
        top3.setEnabled(false);
        final JButton bottom3 = new JButton(new ImageIcon(getClass().getResource("/images/bottom.png")));
        bottom3.setEnabled(false);
        mostrar3.setEnabled(false);
        pb3 = new JProgressBar(0, 100);
        pb3.setValue(0);
        pb3.setStringPainted(true);
        //</editor-fold>

        final JButton exe = new JButton("Ejecutar");
        exe.setEnabled(false);

        ButtonGroup buttongroup = new ButtonGroup();
        buttongroup.add(rb1);
        buttongroup.add(rb2);
        buttongroup.add(rb3);

        final JLabel l_pref = new JLabel("Parámetro 1:");
        l_pref.setMaximumSize(new Dimension(200,20));
        final JTextField pref = new JTextField("0");
        pref.setPreferredSize(new Dimension(100,20));
        pref.setMaximumSize(new Dimension(200,20));
        pref.setEnabled(false);

        final JLabel l_pref2 = new JLabel("Parámetro 2:");
        final JTextField pref2 = new JTextField("0");
        pref2.setPreferredSize(new Dimension(100,20));
        pref2.setMaximumSize(new Dimension(200,20));
        pref2.setEnabled(false);
        l_pref2.setVisible(false);
        pref2.setVisible(false);

        time_data = new JLabel("");

        //<editor-fold desc="Panel Información Nodo">
        //Panel informació nodes
        final JPanel info = new JPanel();
        info.setLayout(new GridBagLayout());
        info.setBorder(new LineBorder(Color.black));
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel name = new JLabel("Nombre y Apellidos: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 2;
        gbc.ipadx = 2;
        info.add(name, gbc);

        name_data = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        info.add(name_data, gbc);

        JLabel dni = new JLabel("Dni: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        info.add(dni, gbc);

        dni_data = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        info.add(dni_data, gbc);

        JLabel community = new JLabel("Comunidad/es:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        info.add(community, gbc);

        community_data = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        info.add(community_data, gbc);

        final JButton okay = new JButton("OK");
        okay.setEnabled(false);
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] s = community_data.getText().split(",\\s");
                if (s[0].equals("Sin comunidad"))
                {
                    g.getNode(node_id_sel).addAttribute("comm", Integer.toString(0));
                    g.getNode(node_id_sel).removeAttribute("nco");
                    g.getNode(node_id_sel).removeAttribute("ui.pie-values");
                    g.getNode(node_id_sel).addAttribute("ui.style", "fill-color: #CCC;");
                }
                else {
                    int n = s.length;
                    g.getNode(node_id_sel).addAttribute("comm", Integer.toString(n));
                    g.getNode(node_id_sel).addAttribute("nco", new Object[]{s});

                    Double corel = 1.0 / n;

                    Object[] copie = new Object[n];

                    String fc = "fill-color: ";
                    for (int i = 0; i < n; i++) {
                        copie[i] = Double.toString(corel);
                        if (i < n - 1) {
                            int prop = Integer.parseInt(s[i]);
                            if (prop < colors.length) fc += colors[prop] + ", ";
                            else fc += obtColorRandom() + ", ";
                        } else {
                            int prop = Integer.parseInt(s[i]);
                            if (prop < colors.length) fc += colors[prop];
                            else fc += obtColorRandom();

                        }
                    }
                    fc += ";";

                    //System.out.println(fc);
                    g.getNode(node_id_sel).addAttribute("ui.pie-values", copie);
                    g.getNode(node_id_sel).addAttribute("ui.style", fc);
                }

            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        info.add(okay,gbc);

        add(info);
        //</editor-fold>

        up1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                --im1;
                String h = cpa.message_at(im1);
                String g = cpa.message_at(im1+1);
                String i = cpa.message_at(im1-1);

                lm1.clear();
                lm1.add(0, i);
                lm1.add(1, h);
                lm1.add(2, g);
                lst1.setSelectedIndex(1);
                ej.ejecuta1(g, h);
                if (im1 == 0)
                {
                    up1.setEnabled(false);
                    top1.setEnabled(false);
                }

                if (im1 == cpa.num_mensajes()-2)
                {
                    down1.setEnabled(true);
                    bottom1.setEnabled(true);
                }
            }
        });

        down1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ++im1;
                String h = cpa.message_at(im1);
                String g = cpa.message_at(im1-1);
                String i = cpa.message_at(im1+1);
                lm1.clear();
                lm1.add(0, g);
                lm1.add(1, h);
                lm1.add(2, i);
                lst1.setSelectedIndex(1);
                ej.ejecuta2(g, h);
                if (im1 == cpa.num_mensajes() - 1) {
                    down1.setEnabled(false);
                    bottom1.setEnabled(false);
                }

                if (im1 == 1)
                {
                    up1.setEnabled(true);
                    top1.setEnabled(true);
                }
            }
        });

        top1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int k = im1; k > 0; k--)
                {
                    ej.ejecuta1(cpa.message_at(k),cpa.message_at(k-1));
                }
                im1 = 0;
                String h = cpa.message_at(im1);
                String g = "Fin";
                String i = "-";
                lm1.clear();
                lm1.add(0, i);
                lm1.add(1, h);
                lm1.add(2, cpa.message_at(im1 + 1));
                lst1.setSelectedIndex(1);
                ej.ejecuta1(g, h);
                up1.setEnabled(false);
                top1.setEnabled(false);
                bottom1.setEnabled(true);
                down1.setEnabled(true);
            }
        });

        bottom1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int k = im1; k < cpa.num_mensajes()-1; k++)
                {
                    ej.ejecuta1(cpa.message_at(k),cpa.message_at(k+1));
                }
                im1 = cpa.num_mensajes()-1;
                String h = cpa.message_at(im1);
                String g = "Inicio";
                String i = "-";
                lm1.clear();
                lm1.add(0, cpa.message_at(im1 - 1));
                lm1.add(1, h);
                lm1.add(2, i);
                lst1.setSelectedIndex(1);
                ej.ejecuta2(g, h);
                down1.setEnabled(false);
                bottom1.setEnabled(false);
                up1.setEnabled(true);
                top1.setEnabled(true);
            }
        });

        up2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                --im2;
                String h = cpa.message_at(im2);
                String g = cpa.message_at(im2+1);
                String i = cpa.message_at(im2-1);

                lm2.clear();
                lm2.add(0,i);
                lm2.add(1,h);
                lm2.add(2,g);
                lst2.setSelectedIndex(1);

                ej.ejecuta1(g, h);
                if (im2 == 0)
                {
                    up2.setEnabled(false);
                    top2.setEnabled(false);
                }

                if (im2 == cpa.num_mensajes()-2)
                {
                    down2.setEnabled(true);
                    bottom2.setEnabled(true);
                }
            }
        });

        down2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ++im2;
                String h = cpa.message_at(im2);
                String g = cpa.message_at(im2-1);
                String i = cpa.message_at(im2+1);
                lm2.clear();
                lm2.add(0, g);
                lm2.add(1, h);
                lm2.add(2, i);
                lst2.setSelectedIndex(1);

                ej.ejecuta2(g, h);
                if (im2 == cpa.num_mensajes() - 1) {
                    down2.setEnabled(false);
                    bottom2.setEnabled(false);
                }

                if (im2 == 1)
                {
                    up2.setEnabled(true);
                    top2.setEnabled(true);
                }
            }
        });

        top2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int k = im2; k > 0; k--)
                {
                    ej.ejecuta1(cpa.message_at(k),cpa.message_at(k-1));
                }
                im2 = 0;
                String h = cpa.message_at(im2);
                String i = "-";
                lm2.clear();
                lm2.add(0,i);
                lm2.add(1,h);
                lm2.add(2, cpa.message_at(im2+1));
                lst2.setSelectedIndex(1);
                //ej.ejecuta1(g, h);
                up2.setEnabled(false);
                top2.setEnabled(false);
                bottom2.setEnabled(true);
                down2.setEnabled(true);
            }
        });

        bottom2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int k = im2; k < cpa.num_mensajes()-1; k++)
                {
                    ej.ejecuta1(cpa.message_at(k),cpa.message_at(k+1));
                }
                im2 = cpa.num_mensajes()-1;
                String h = cpa.message_at(im2);
                String g = "Inicio";
                String i = "-";
                lm2.clear();
                lm2.add(0, i);
                lm2.add(1, h);
                lm2.add(2, cpa.message_at(im2+1));
                lst2.setSelectedIndex(1);
                ej.ejecuta2(g, h);

                down2.setEnabled(false);
                bottom2.setEnabled(false);
                up2.setEnabled(true);
                top2.setEnabled(true);
            }
        });

        up3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                --im3;
                String h = cpa.message_at(im3);
                String g = cpa.message_at(im3+1);
                String i = cpa.message_at(im3-1);

                lm3.clear();
                lm3.add(0, i);
                lm3.add(1, h);
                lm3.add(2, g);
                lst3.setSelectedIndex(1);

                ej.ejecuta1(g, h);

                if (im3 == 0)
                {
                    up3.setEnabled(false);
                    top3.setEnabled(false);
                }

                if (im3 == cpa.num_mensajes()-2)
                {
                    down3.setEnabled(true);
                    bottom3.setEnabled(true);
                }
            }
        });

        down3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ++im3;
                String h = cpa.message_at(im3);
                String g = cpa.message_at(im3-1);
                String i = cpa.message_at(im3+1);

                lm3.clear();
                lm3.add(0, g);
                lm3.add(1, h);
                lm3.add(2, i);
                lst3.setSelectedIndex(1);

                ej.ejecuta2(g, h);

                if (im3 == cpa.num_mensajes() - 1) {
                    down3.setEnabled(false);
                    bottom3.setEnabled(false);
                }

                if (im3 == 1)
                {
                    up3.setEnabled(true);
                    top3.setEnabled(true);
                }
            }
        });

        top3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int k = im3; k > 0; k--)
                {
                    ej.ejecuta1(cpa.message_at(k),cpa.message_at(k-1));
                }
                im3 = 0;
                String h = cpa.message_at(im3);
                String g = "Fin";
                String i = "-";

                lm3.clear();
                lm3.add(0, i);
                lm3.add(1, h);
                lm3.add(2, cpa.message_at(im3+1));
                lst3.setSelectedIndex(1);

                ej.ejecuta1(g, h);

                up3.setEnabled(false);
                top3.setEnabled(false);
                bottom3.setEnabled(true);
                down3.setEnabled(true);
            }
        });

        bottom3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int k = im3; k < cpa.num_mensajes()-1; k++)
                {
                    ej.ejecuta1(cpa.message_at(k),cpa.message_at(k+1));
                }
                im3 = cpa.num_mensajes()-1;
                String h = cpa.message_at(im3);
                String g = cpa.message_at(0);
                String i = "-";

                lm3.clear();
                lm3.add(0, cpa.message_at(im3-1));
                lm3.add(1, h);
                lm3.add(2, i);
                lst3.setSelectedIndex(1);

                ej.ejecuta2(g, h);

                down3.setEnabled(false);
                bottom3.setEnabled(false);
                up3.setEnabled(true);
                top3.setEnabled(true);
            }
        });

        //<editor-fold desc="Acción Ratón">
        view.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                node_id_sel = "";
                fromViewer.pump();
                //System.out.println("Click: "+node_id_sel);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (!node_id_sel.equals("")) {
                        String d = g.getNode(node_id_sel).getAttribute("ui.label");
                        String[] ico = cpa.obtCongresista(d).split("\\s");
                        name_data.setText(ico[1]+" "+ico[2]);
                        dni_data.setText(d);
                        String sc;
                        try {
                            if (g.getNode(node_id_sel).hasArray("nco"))
                            {
                                Object[] arr = g.getNode(node_id_sel).getAttribute("nco");
                                sc = (String) arr[0];
                                for (int i = 1; i < arr.length; ++i)
                                {
                                    sc += ", "+arr[i];
                                }
                            }
                            else {
                                String arr = g.getNode(node_id_sel).getAttribute("nco");
                                if (arr == null) sc = "Sin comunidad";
                                else sc = arr;
                            }
                            //System.out.println(sc);
                            community_data.setText(sc);
                            community_data.setCaretPosition(0);
                            okay.setEnabled(true);
                        }
                        catch (Exception exc){
                            exc.printStackTrace();
                        }

                        info.setVisible(true);
                    }
                    else
                    {
                        info.setVisible(false);
                    }


                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    System.out.println("PROP NIGHT");

                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    Point a = view.getLocationOnScreen();
                    Point b = e.getLocationOnScreen();
                    Point3 c = view.getCamera().transformPxToGu(b.getX()-a.getX(),b.getY()-a.getY());
                    view.getCamera().setViewCenter(c.x, c.y, c.z);

                }

            }

        });
        //</editor-fold>

        //<editor-fold desc="Acción RadioButton 1">
        //Acciones de los radiobuttons
        rb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pref.setEnabled(true);
                pref.setText("2");
                l_pref.setText("Nº comunidades:");
                pref2.setEnabled(false);
                pref2.setVisible(false);
                l_pref2.setVisible(false);
                lst1.setEnabled(true);
                lst2.setEnabled(false);
                lst3.setEnabled(false);

                pb1.setValue(0);
                pb2.setValue(0);
                pb3.setValue(0);

                exe.setEnabled(true);

                option = 1;
            }
        });
        //</editor-fold>

        //<editor-fold desc="Acción RadioButton 2">
        rb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pref.setEnabled(true);
                pref.setText("0.000001");
                l_pref.setText("Modularidad:");
                pref2.setEnabled(false);
                pref2.setVisible(false);
                l_pref2.setVisible(false);
                lst1.setEnabled(false);
                lst2.setEnabled(true);
                lst3.setEnabled(false);

                pb1.setValue(0);
                pb2.setValue(0);
                pb3.setValue(0);

                exe.setEnabled(true);

                option = 2;
            }
        });
        //</editor-fold>

        //<editor-fold desc="Acción RadioButton 3">
        rb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pref.setEnabled(true);
                pref.setText("3");
                l_pref.setText("K:");
                pref2.setEnabled(true);
                pref2.setVisible(true);
                l_pref2.setText("Threshold");
                l_pref2.setVisible(true);
                lst1.setEnabled(false);
                lst2.setEnabled(false);
                lst3.setEnabled(true);

                pb1.setValue(0);
                pb2.setValue(0);
                pb3.setValue(0);

                exe.setEnabled(true);

                option = 3;
            }
        });
        //</editor-fold>


        //<editor-fold desc="Acción Botón Ejecutar">
        exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                cpa.modParam1(pref.getText());
                cpa.modParam2(pref2.getText());

                SwingWorker<Void,Void> sw = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        long start = System.nanoTime();
                        if (option > 0 && option < 4) cpa.execute_algoritm(option);
                        long time = System.nanoTime()-start;
                        String lulz = time/1000+" microseconds";
                        time_data.setText(lulz);
                        return null;
                    }
                };
                sw.execute();

                if (option == 1) mostrar1.setEnabled(true);
                else if (option == 2) mostrar2.setEnabled(true);
                else if (option == 3) mostrar3.setEnabled(true);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        //</editor-fold>

        //<editor-fold desc="Botón Mostrar1 GN">
        mostrar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SwingWorker<Void, Void> sw1 = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        setProgress(0);
                        asignarGN();
                        setProgress(50);
                        mostrar1.setEnabled(false);
                        String r = cpa.message_at(0);
                        //System.out.println(r);
                        lm1.clear();
                        lm1.add(0, "-");
                        lm1.add(1, r);
                        lm1.add(2, cpa.message_at(1));
                        im1 = 0;
                        down1.setEnabled(true);
                        bottom1.setEnabled(true);
                        setProgress(100);
                        return null;
                    }
                };

                sw1.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("progress".equals(evt.getPropertyName())) {
                            int progress = (Integer) evt.getNewValue();
                            pb1.setIndeterminate(false);
                            pb1.setValue(progress);
                        }
                    }
                });

                sw1.execute();

                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        //</editor-fold>

        //<editor-fold desc="Botón Mostrar2 Lv">
        mostrar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                SwingWorker<Void, Void> sw2 = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        setProgress(0);
                        asignarLV();
                        setProgress(50);
                        mostrar2.setEnabled(false);
                        String r = cpa.message_at(0);
                        //System.out.println(r);
                        lm2.clear();
                        lm2.add(0, "-");
                        lm2.add(1, r);
                        lm2.add(2, cpa.message_at(1));
                        im2 = 0;
                        down2.setEnabled(true);
                        bottom2.setEnabled(true);
                        setProgress(100);
                        return null;
                    }
                };

                sw2.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("progress".equals(evt.getPropertyName())) {
                            int progress = (Integer) evt.getNewValue();
                            pb2.setIndeterminate(false);
                            pb2.setValue(progress);
                        }
                    }
                });

                sw2.execute();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
        //</editor-fold>

        //<editor-fold desc="Botón Mostrar3 CP">
        mostrar3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SwingWorker<Void, Void> sw3 = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        setProgress(0);
                        asignarCP();
                        setProgress(50);
                        mostrar3.setEnabled(false);

                        String r = cpa.message_at(0);
                        //System.out.println(r);
                        lm3.clear();
                        lm3.add(0, "-");
                        lm3.add(1, r);
                        lm3.add(2, cpa.message_at(1));
                        //tp3.setDocument(doc);
                        im3 = 0;
                        down3.setEnabled(true);
                        bottom3.setEnabled(true);
                        setProgress(100);
                        return null;
                    }
                };

                sw3.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("progress".equals(evt.getPropertyName())) {
                            int progress = (Integer) evt.getNewValue();
                            pb3.setIndeterminate(false);
                            pb3.setValue(progress);
                        }
                    }
                });

                sw3.execute();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        //</editor-fold>

        //<editor-fold desc="Distribución en pantalla">
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        gr.setHorizontalGroup(gr.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(view, (int) (width / 3), (int) (width / 2), (int) width)
                        .addGroup(gr.createSequentialGroup()
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb1)
                                                                //.addComponent(lb1, 100, 150, (int) (width / 4))
                                                        .addComponent(lst1, 100, 150, (int) (width / 4))
                                                        .addComponent(pb1, 100, 150, (int) (width / 4))
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(mostrar1)
                                                                        .addComponent(up1)
                                                                        .addComponent(down1)
                                                                        .addComponent(top1)
                                                                        .addComponent(bottom1)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb2)
                                                                //.addComponent(lb2)
                                                        .addComponent(lst2, 100, 150, (int) (width / 4))
                                                        .addComponent(pb2, 100, 150, (int) (width / 4))
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(mostrar2)
                                                                        .addComponent(up2)
                                                                        .addComponent(down2)
                                                                        .addComponent(top2)
                                                                        .addComponent(bottom2)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb3)
                                                                //.addComponent(lb3)
                                                        .addComponent(lst3, 100, 150, (int) (width / 4))
                                                        .addComponent(pb3, 100, 150, (int) (width / 4))
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(mostrar3)
                                                                        .addComponent(up3)
                                                                        .addComponent(down3)
                                                                        .addComponent(top3)
                                                                        .addComponent(bottom3)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(l_pref)
                                                        .addComponent(pref)
                                                        .addComponent(l_pref2)
                                                        .addComponent(pref2)
                                                        .addComponent(info, 100, 150, (int) (width / 4))
                                        )

                        )
                        .addGroup(gr.createSequentialGroup()
                                .addComponent(exe)
                                .addComponent(time_data))

        );

        gr.setVerticalGroup(gr.createSequentialGroup()
                        .addComponent(view, (int) (height / 3), (int) (height / 3), (int) (height / 2))
                        .addGroup(gr.createParallelGroup()
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb1)
                                                        .addComponent(lst1, (int) (height / 10), (int) (height / 9), (int) (height / 3))
                                                        .addComponent(pb1)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb2)
                                                        .addComponent(lst2, (int) (height / 10), (int) (height / 9), (int) (height / 3))
                                                        .addComponent(pb2)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb3)
                                                        .addComponent(lst3, (int) (height / 10), (int) (height / 9), (int) (height / 3))
                                                        .addComponent(pb3)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(l_pref)
                                                        .addComponent(pref)
                                                        .addComponent(l_pref2)
                                                        .addComponent(pref2)
                                                        .addComponent(info)
                                        )
                        )
                        .addGroup(gr.createParallelGroup()
                                        .addComponent(mostrar1)
                                        .addComponent(up1)
                                        .addComponent(down1)
                                        .addComponent(top1)
                                        .addComponent(bottom1)
                                        .addComponent(mostrar2)
                                        .addComponent(up2)
                                        .addComponent(down2)
                                        .addComponent(top2)
                                        .addComponent(bottom2)
                                        .addComponent(mostrar3)
                                        .addComponent(up3)
                                        .addComponent(down3)
                                        .addComponent(top3)
                                        .addComponent(bottom3)
                        )
                        .addGroup(gr.createParallelGroup()
                                .addComponent(exe)
                                .addComponent(time_data))

        );

        gr.setAutoCreateGaps(true);
        gr.setAutoCreateContainerGaps(true);
        //</editor-fold>
    }

    //Crea los nodos necesarios que existen en el grafo
    public void actualizar()
    {
        int nv = cpa.num_vertices();
        //int na = nv-nn;
        int n = nv/2;

        g.clear();
        g.addAttribute("ui.antialias");
        g.addAttribute(
                    "ui.stylesheet",
                    "node { shape: pie-chart; size: 17.5px; text-alignment: above; fill-color: #CCC; stroke-mode: plain; stroke-color: #999; }"+
                            "node:selected { stroke-width: 4px; stroke-color: black; }"+
                            "node:clicked { stroke-width: 4px; stroke-color: black; }"+
                            "edge { fill-color: #777; }");




        //<editor-fold desc="Creación de nodos">

        int l = (int) Math.sqrt(nv);
        for (int i = 0; i < nv; i++)
        {
            String ls = cpa.obtLabel(i);
            String is = Integer.toString(i);
            Node p = g.addNode(is);
            int x = i % l;
            int y = i/l;
            p.addAttribute("xyz", x, y, 0);
            //System.out.println(i+": "+x+", "+y);
            p.addAttribute("ui.label", ls);
            p.addAttribute("comm", Integer.toString(0));
            p.addAttribute("ui.pie-values", Double.toString(1.0));
            //System.out.println("U ("+i+") » " + is+" : "+ls);
        }

        //</editor-fold>

        cpa.reset_pointers();
        for (int s = 0; s < nv; s++) {
            String q = cpa.next_vertex();
            //System.out.println("Creando aristas → "+j+"; "+q);
            String compare = "";
            while (!compare.equals("-")) {
                String r = cpa.next_arista();
                String[] rr = r.split("\\s");
                compare = rr[1];
                if (!compare.equals("-")) {
                    if (g.getEdge(rr[0] + "~" + rr[1]) == null && g.getEdge(rr[1] + "~" + rr[0]) == null)
                        g.addEdge(rr[0] + "~" + rr[1], rr[0], rr[1]);
                }
            }
        }

    }

    private void cargarGrafo()
    {
        final int nv = cpa.num_vertices();

        cpa.reset_pointers();
        for (int j = 0; j < nv; j++) {
            String q = cpa.next_vertex();
            //System.out.println("Creando aristas → "+j+"; "+q);
            Node n = g.getNode(j);
            n.changeAttribute("comm", Integer.toString(0));
            n.changeAttribute("ui.pie-values", Double.toString(1.0));
            n.removeAttribute("nco");
        }



        int nc = cpa.num_comunidades();
        char chargen = 'A';
        for (int k = 0; k < nc; k++)
        {
            String colour = obtColorRandom();
            String c = cpa.next_community();
            //System.out.println("comunidad: "+k);
            String[] cc = c.split(",\\s");
            if (!cc[0].equals("-"))
            {
                //System.out.println(cc.length);
                for (String aCc : cc) {
                    //System.out.println(cc[l]);
                    String co = g.getNode(aCc).getAttribute("comm");
                    int nodo_com = Integer.parseInt(co);

                    g.getNode(aCc).addAttribute("comm", Integer.toString(nodo_com + 1));

                    Double corel = 1.0 / (nodo_com + 1);

                    Object[] copie = new Object[nodo_com + 1];

                    Object[] ncopie = new Object[nodo_com + 1];
                    Object[] scopie;
                    if (g.getNode(aCc).hasArray("nco")) scopie = g.getNode(aCc).getAttribute("nco");
                    else {
                        scopie = new String[1];
                        scopie[0] = g.getNode(aCc).getAttribute("nco");
                    }

                    String fc = "fill-color: ";
                    for (int i = 0; i <= nodo_com; i++) {
                        copie[i] = Double.toString(corel);
                        if (i < nodo_com) {
                            ncopie[i] = scopie[i];
                            int prop = Integer.parseInt((String) scopie[i]);
                            if (prop < colors.length) fc += colors[prop] + ", ";
                            else fc += obtColorRandom() + ", ";
                        } else {
                            ncopie[i] = Integer.toString(k);
                            if (k < colors.length) fc += colors[k];
                            else fc += colour; // añade el color creado para esta comunidad

                        }
                    }
                    fc += ";";

                    //System.out.println(fc);
                    g.getNode(aCc).addAttribute("ui.pie-values", copie);
                    g.getNode(aCc).addAttribute("ui.style", fc);
                    g.getNode(aCc).addAttribute("nco", ncopie);

                }
            }
        }

    }

    private void asignarGN()
    {
        cpa.reset_pointers();
        int nc = cpa.num_comunidades();
        for (int k = 0; k < nc; k++)
        {
           // String colour = obtColorRandom();
            String c = cpa.next_community();
            //System.out.println("comunidad: "+k);
            String[] cc = c.split(",\\s");
            if (!cc[0].equals("-"))
            {
                //System.out.println(cc.length);
                for (String aCc : cc) {
                    //System.out.println(cc[l]);
                    Node ac = g.getNode(aCc);

                    ac.addAttribute("nco", Integer.toString(k));

                    /*if (k < colors.length) ac.addAttribute("color", colors[k]);
                    else ac.addAttribute("color", colour);*/

                    ac.addAttribute("ui.style", "fill-color: #CCC;");

                }
            }
        }
    }

    private void asignarLV()
    {
        cpa.reset_pointers();
        int nc = cpa.num_comunidades();
        int i = 0;
        for (int k = 0; k < nc; k++)
        {

            String c = cpa.next_community();
            //System.out.println("comunidad: "+k);
            String[] cc = c.split(",\\s");
            if (!cc[0].equals("-"))
            {
                //System.out.println(cc.length);
                for (String aCc : cc) {
                    //System.out.println(cc[l]);
                    Node ac = g.getNode(aCc);

                    String colour = obtColorRandom();

                    ac.addAttribute("nco", Integer.toString(k));

                    if (i < colors.length)
                    {
                        ac.addAttribute("ui.style", "fill-color: "+colors[i]+";");
                        ac.addAttribute("color", colors[i]);
                    }
                    else
                    {
                        ac.addAttribute("ui.style", "fill-color: "+colour+";");
                        ac.addAttribute("color", colour);
                    }

                    i++;

                }
            }
        }
    }

    private void asignarCP()
    {
        final int nv = cpa.num_vertices();

        cpa.reset_pointers();
        for (int j = 0; j < nv; j++) {
            String q = cpa.next_vertex();
            //System.out.println("Creando aristas → "+j+"; "+q);
            Node n = g.getNode(j);
            n.changeAttribute("comm", Integer.toString(0));
            n.changeAttribute("ui.pie-values", Double.toString(1.0));
            n.removeAttribute("nco");
            n.addAttribute("ui.style", "fill-color: #CCC;");
        }

/*
        int nc = cpa.num_comunidades();
        char chargen = 'A';
        for (int k = 0; k < nc; k++)
        {
            String colour = obtColorRandom();
            String c = cpa.next_community();
            //System.out.println("comunidad: "+k);
            String[] cc = c.split(",\\s");
            if (!cc[0].equals("-"))
            {
                //System.out.println(cc.length);
                for (String aCc : cc) {
                    //System.out.println(cc[l]);
                    String co = g.getNode(aCc).getAttribute("comm");
                    int nodo_com = Integer.parseInt(co);

                    g.getNode(aCc).addAttribute("comm", Integer.toString(nodo_com + 1));

                    Double corel = 1.0 / (nodo_com + 1);

                    Object[] copie = new Object[nodo_com + 1];

                    Object[] ncopie = new Object[nodo_com + 1];
                    Object[] scopie;
                    if (g.getNode(aCc).hasArray("nco")) scopie = g.getNode(aCc).getAttribute("nco");
                    else {
                        scopie = new String[1];
                        scopie[0] = g.getNode(aCc).getAttribute("nco");
                    }

                    String fc = "fill-color: ";
                    for (int i = 0; i <= nodo_com; i++) {
                        copie[i] = Double.toString(corel);
                        if (i < nodo_com) {
                            ncopie[i] = scopie[i];
                            int prop = Integer.parseInt((String) scopie[i]);
                            if (prop < colors.length) fc += colors[prop] + ", ";
                            else fc += obtColorRandom() + ", ";
                        } else {
                            ncopie[i] = Integer.toString(k);
                            if (k < colors.length) fc += colors[k];
                            else fc += colour; // añade el color creado para esta comunidad

                        }
                    }
                    fc += ";";

                    //System.out.println(fc);
                    g.getNode(aCc).addAttribute("ui.pie-values", copie);
                    g.getNode(aCc).addAttribute("ui.style", fc);
                    g.getNode(aCc).addAttribute("nco", ncopie);

                }
            }
        }*/
    }


    private String obtColorRandom()
    {
        String values = "0123456789ABCDEF";
        String[] letras = values.split("");
        String color = "#";
        Random gen = new Random();
        for (int i = 0; i < 6; ++i)
        {
            int rand = gen.nextInt(16)+1;
            //System.out.println(rand+" "+letras[rand]);

            color += letras[rand];
        }
        return color;
    }

    public void nuevo()
    {
        g.clear();
        lm1.clear();
        lm2.clear();
        lm3.clear();

        pb1.setValue(0);
        pb2.setValue(0);
        pb3.setValue(0);

        name_data.setText("");
        dni_data.setText("");
        community_data.setText("");
        time_data.setText("");

    }

}

