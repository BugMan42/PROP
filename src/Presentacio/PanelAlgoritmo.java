package Presentacio;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
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
    private Graph g;
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

        g = new SingleGraph("GOLF");

        g.addAttribute("ui.antialias");

        g.addAttribute(
                "ui.stylesheet",
                "node { text-alignment: above; fill-color: #CCC; stroke-mode: plain; stroke-color: #999; }"+
                        "node:selected { stroke-width: 4px; }");

        viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
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

        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane[Enabled].backgroundPainter", Color.black);


        //Dimension panel_dim = new Dimension(200, 100);
        final JTextPane tp1 = new JTextPane();
        tp1.setForeground(Color.green);
        tp1.putClientProperty("Nimbus.Overrides", defaults);
        tp1.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp1.setBackground(Color.black);
        tp1.setEnabled(false);
        tp1.setEditable(false);
        //tp1.setMaximumSize(panel_dim);
        JScrollPane sp1 = new JScrollPane(tp1);
        JRadioButton rb1 = new JRadioButton("Girvan-Newman");
        final JButton mostrar1 = new JButton("Mostrar grafo");
        mostrar1.setEnabled(false);
        pb1 = new JProgressBar(0, 100);
        pb1.setValue(0);
        pb1.setStringPainted(true);


        final JTextPane tp2 = new JTextPane();
        tp2.setForeground(new Color(255, 102, 0));
        tp2.putClientProperty("Nimbus.Overrides", defaults);
        tp2.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp2.setBackground(new Color(0,0,102));
        tp2.setEnabled(false);
        tp2.setEditable(false);
        //tp2.setMaximumSize(panel_dim);
        JScrollPane sp2 = new JScrollPane(tp2);
        JRadioButton rb2 = new JRadioButton("Louvain");
        final JButton mostrar2 = new JButton("Mostrar grafo");
        mostrar2.setEnabled(false);
        pb2 = new JProgressBar(0, 100);
        pb2.setValue(0);
        pb2.setStringPainted(true);

        final JTextPane tp3 = new JTextPane();
        tp3.setForeground(new Color(255, 255, 0));
        tp3.putClientProperty("Nimbus.Overrides", defaults);
        tp3.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp3.setBackground(new Color(127, 0, 255));
        tp3.setEnabled(false);
        tp3.setEditable(false);
        //tp3.setMaximumSize(panel_dim);
        JScrollPane sp3 = new JScrollPane(tp3);
        JRadioButton rb3 = new JRadioButton("Clique Percolation");
        final JButton mostrar3 = new JButton("Mostrar grafo");
        mostrar3.setEnabled(false);
        pb3 = new JProgressBar(0, 100);
        pb3.setValue(0);
        pb3.setStringPainted(true);

        JButton exe = new JButton("Ejecutar");

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

        final JLabel name_data = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        info.add(name_data, gbc);

        JLabel dni = new JLabel("Dni: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        info.add(dni, gbc);

        final JLabel dni_data = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        info.add(dni_data, gbc);

        JLabel community = new JLabel("Comunidad/es:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        info.add(community, gbc);

        final JTextField community_data = new JTextField();
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
                    Point a = view.getLocationOnScreen();
                    Point b = e.getLocationOnScreen();
                    Point3 c = view.getCamera().transformPxToGu(b.getX()-a.getX(),b.getY()-a.getY());
                    view.getCamera().setViewCenter(c.x, c.y, c.z);
                } else if (e.getButton() == MouseEvent.BUTTON3) {

                    System.out.println("PROP NIGHT");
                }

            }

        });

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
                tp1.setEnabled(true);
                tp2.setEnabled(false);
                tp3.setEnabled(false);
                tp2.setText("");
                tp3.setText("");

                option = 1;
            }
        });

        rb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pref.setEnabled(true);
                pref.setText("0.000001");
                l_pref.setText("Modularidad:");
                pref2.setEnabled(false);
                pref2.setVisible(false);
                l_pref2.setVisible(false);
                tp1.setEnabled(false);
                tp2.setEnabled(true);
                tp3.setEnabled(false);
                tp1.setText("");
                tp3.setText("");

                option = 2;
            }
        });

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
                tp1.setEnabled(false);
                tp2.setEnabled(false);
                tp3.setEnabled(true);
                tp1.setText("");
                tp2.setText("");
                option = 3;
            }
        });


        exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                cpa.modParam1(pref.getText());
                cpa.modParam2(pref2.getText());

                try {
                    cpa.crearGrafo();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                if (option > 0 && option < 4) cpa.execute_algoritm(option);

                if (option == 1) mostrar1.setEnabled(true);
                else if (option == 2) mostrar2.setEnabled(true);
                else if (option == 3) mostrar3.setEnabled(true);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        mostrar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SwingWorker<Void, Void> sw1 = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        setProgress(0);
                        cargarGrafo();
                        setProgress(50);
                        mostrar1.setEnabled(false);
                        Document doc = new DefaultStyledDocument();
                        try {
                            String r = cpa.next_mensaje();
                            while (!r.equals("-")) {
                                //System.out.println(r);
                                doc.insertString(doc.getLength(), r+"\n", null);
                                r = cpa.next_mensaje();

                            }
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                        tp1.setDocument(doc);
                        setProgress(100);
                        return null;
                    }
                };

                sw1.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("progress" == evt.getPropertyName()) {
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

        mostrar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                SwingWorker<Void, Void> sw2 = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        setProgress(0);
                        cargarGrafo();
                        setProgress(50);
                        mostrar2.setEnabled(false);
                        Document doc = new DefaultStyledDocument();
                        try {
                            String r = cpa.next_mensaje();
                            while (!r.equals("-")) {
                                //System.out.println(r);
                                doc.insertString(doc.getLength(), r+"\n", null);
                                r = cpa.next_mensaje();

                            }
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                        tp2.setDocument(doc);
                        setProgress(100);
                        return null;
                    }
                };

                sw2.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("progress" == evt.getPropertyName()) {
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

        mostrar3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SwingWorker<Void, Void> sw3 = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        setProgress(0);
                        cargarGrafo();
                        setProgress(50);
                        mostrar3.setEnabled(false);
                        Document doc = new DefaultStyledDocument();
                        try {
                            String r = cpa.next_mensaje();
                            while (!r.equals("-")) {
                                //System.out.println(r);
                                doc.insertString(doc.getLength(), r+"\n", null);
                                r = cpa.next_mensaje();

                            }
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                        tp3.setDocument(doc);
                        setProgress(100);
                        return null;
                    }
                };

                sw3.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("progress" == evt.getPropertyName()) {
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

        // Dimensiones de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        gr.setHorizontalGroup(gr.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(view, (int) (width / 3), (int) (width / 2), (int) width)
                        .addGroup(gr.createSequentialGroup()
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb1)
                                                        .addComponent(sp1, 100, 150, (int) (width / 4))
                                                        .addComponent(pb1)
                                                        .addComponent(mostrar1)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb2)
                                                        .addComponent(sp2, 100, 150, (int) (width / 4))
                                                        .addComponent(pb2)
                                                        .addComponent(mostrar2)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb3)
                                                        .addComponent(sp3, 100, 150, (int) (width / 4))
                                                        .addComponent(pb3)
                                                        .addComponent(mostrar3)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(l_pref)
                                                        .addComponent(pref)
                                                        .addComponent(l_pref2)
                                                        .addComponent(pref2)
                                                        .addComponent(info, 100, 150, (int) (width / 4))
                                        )

                        )
                        .addComponent(exe)

        );

        gr.setVerticalGroup(gr.createSequentialGroup()
                        .addComponent(view, (int) (height / 3), (int) (height / 3), (int) (height / 2))
                        .addGroup(gr.createParallelGroup()
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb1)
                                                        .addComponent(sp1, (int) (height / 6), (int) (height / 5.5), (int) (height / 3))
                                                        .addComponent(pb1)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb2)
                                                        .addComponent(sp2, (int) (height / 6), (int) (height / 5.5), (int) (height / 3))
                                                        .addComponent(pb2)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb3)
                                                        .addComponent(sp3, (int) (height / 6), (int) (height / 5.5), (int) (height / 3))
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
                                        .addComponent(mostrar2)
                                        .addComponent(mostrar3)
                        )
                        .addComponent(exe)

        );

        gr.setAutoCreateGaps(true);
        gr.setAutoCreateContainerGaps(true);

    }

    private void cargarGrafo()
    {
        g.clear();
        g.addAttribute("ui.antialias");
        g.addAttribute(
                "ui.stylesheet",
                "node { shape: pie-chart; size: 17.5px; text-alignment: above; fill-color: #CCC; stroke-mode: plain; stroke-color: #999; }"+
                        "node:selected { stroke-width: 4px; }"+
                        "node:clicked { stroke-width: 4px; stroke-color: black; }"+
                        "edge { fill-color: #777; }");

        int na = cpa.num_vertices();
        for (int i = 0; i < na; i++)
        {
            String is = Integer.toString(i);
            g.addNode(is);
            g.getNode(i).addAttribute("ui.label", cpa.obtLabel(i));
            g.getNode(i).addAttribute("comm", Integer.toString(0));
            g.getNode(i).addAttribute("ui.pie-values", Double.toString(1.0));
        }

        cpa.reset_pointers();
        for (int j = 0; j < na; j++) {
            cpa.next_vertex();
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

        int nc = cpa.num_comunidades();
        char chargen = 'A';
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
                            else fc += obtColorRandom();

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

}

