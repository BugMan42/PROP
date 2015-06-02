package Presentacio;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private boolean demo_activated = false;
    private String[] colors = {"blue", "green", "red", "yellow", "#B280B2", "sienna", "tan", "turquoise", "pink", "khaki", "orange", "seagreen", "darkmagenta", "#574926"};

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
                        "node:selected { stroke-width: 4px; }"+
                        "edge { fill-color: #777; }");

        viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        view = viewer.addDefaultView(false);
        //view.setMinimumSize(new Dimension(getWidth()/2, getHeight()/2));


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
        final JProgressBar pb1 = new JProgressBar();


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

        JRadioButton rb4 = new JRadioButton("Todos los algoritmos");
        JButton exe = new JButton("Ejecutar");

        ButtonGroup buttongroup = new ButtonGroup();
        buttongroup.add(rb1);
        buttongroup.add(rb2);
        buttongroup.add(rb3);
        buttongroup.add(rb4);

        JLabel l_pref = new JLabel("Parámetro 1:");
        final JTextField pref = new JTextField("0");
        pref.setPreferredSize(new Dimension(100,25));
        pref.setMaximumSize(new Dimension(200,25));
        pref.setEnabled(false);

        JLabel l_pref2 = new JLabel("Parámetro 2:");
        final JTextField pref2 = new JTextField("0");
        pref2.setPreferredSize(new Dimension(100,25));
        pref2.setMaximumSize(new Dimension(200,25));
        pref2.setEnabled(false);

        JButton demo = new JButton("DEMO");
        demo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demo_activated = true;
                cpa.createDemoGraph();
            }
        });

        //Acciones de los radiobuttons
        rb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pref.setEnabled(true);
                tp1.setEnabled(true);
                tp2.setEnabled(false);
                tp3.setEnabled(false);

                option = 1;
            }
        });

        rb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pref.setEnabled(true);
                tp1.setEnabled(false);
                tp2.setEnabled(true);
                tp3.setEnabled(false);

                option = 2;
            }
        });

        rb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pref.setEnabled(true);
                tp1.setEnabled(false);
                tp2.setEnabled(false);
                tp3.setEnabled(true);
                option = 3;
            }
        });

        rb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tp1.setEnabled(true);
                tp2.setEnabled(true);
                tp3.setEnabled(true);
                option = 4;
            }
        });

        exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpa.modParam1(pref.getText());

                System.out.println("demo: "+demo_activated);
                if (!demo_activated) {
                    try {
                        cpa.crearGrafo();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                if (option > 0 && option < 4) cpa.execute_algoritm(option);
                else if (option == 4)
                {

                }

                if (option == 1) mostrar1.setEnabled(true);
                else if (option == 2) mostrar2.setEnabled(true);
                else if (option == 3) mostrar3.setEnabled(true);
                else if (option == 4)
                {
                    mostrar1.setEnabled(true);
                    mostrar2.setEnabled(true);
                    mostrar3.setEnabled(true);
                }
            }
        });

        mostrar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cargarGrafo();
                mostrar1.setEnabled(false);
                Document doc = new DefaultStyledDocument();
                try {
                    String r = cpa.next_mensaje();
                    while (!r.equals("-")) {
                        System.out.println(r);
                        doc.insertString(doc.getLength(), r+"\n", null);
                        r = cpa.next_mensaje();

                    }
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                tp1.setDocument(doc);

            }
        });

        mostrar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarGrafo();
                mostrar2.setEnabled(false);
                Document doc = new DefaultStyledDocument();
                try {
                    String r = cpa.next_mensaje();
                    while (!r.equals("-")) {
                        doc.insertString(0, r, null);
                        doc.insertString(0, "\n", null);
                        r = cpa.next_mensaje();
                        System.out.println(r);
                    }
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                tp2.setDocument(doc);
            }
        });

        mostrar3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarGrafo();
                mostrar3.setEnabled(false);

                Document doc = new DefaultStyledDocument();
                try {
                    String r = cpa.next_mensaje();
                    while (!r.equals("-")) {
                        doc.insertString(doc.getLength(), r+"\n", null);
                        r = cpa.next_mensaje();

                    }
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                tp3.setDocument(doc);
            }
        });

        gr.setHorizontalGroup(gr.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(view)
                        .addGroup(gr.createSequentialGroup()
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb1)
                                                        .addComponent(sp1)
                                                        .addComponent(mostrar1)
                                                //.addComponent(pb1)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb2)
                                                        .addComponent(sp2)
                                                        .addComponent(mostrar2)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb3)
                                                        .addComponent(sp3)
                                                        .addComponent(mostrar3)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(l_pref)
                                                        .addComponent(pref)
                                                        .addComponent(l_pref2)
                                                        .addComponent(pref2)
                                        )

                        )
                        .addComponent(rb4)
                        .addComponent(demo)
                        .addComponent(exe)

        );

        gr.setVerticalGroup(gr.createSequentialGroup()
                        .addComponent(view)
                        .addGroup(gr.createParallelGroup()
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb1)
                                                        .addComponent(sp1, 100, 150, 150)
                                                        .addComponent(mostrar1)
                                                //.addComponent(pb1)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb2)
                                                        .addComponent(sp2, 100, 150, 150)
                                                        .addComponent(mostrar2)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb3)
                                                        .addComponent(sp3, 100, 150, 150)
                                                        .addComponent(mostrar3)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(l_pref)
                                                        .addComponent(pref)
                                                        .addComponent(l_pref2)
                                                        .addComponent(pref2)
                                        )
                        )
                        .addComponent(rb4)
                        .addComponent(demo)
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
                "node { shape: pie-chart; text-alignment: above; fill-color: #CCC; stroke-mode: plain; stroke-color: #999; }"+
                        "node:selected { stroke-width: 4px; }"+
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
            System.out.println(k);
            String[] cc = c.split(",\\s");
            if (!cc[0].equals("-"))
            {
                //System.out.println(cc.length);
                for (int l = 0; l < cc.length; l++)
                {

                    String co = g.getNode(cc[l]).getAttribute("comm");
                    int nodo_com = Integer.parseInt(co);
                    g.getNode(cc[l]).addAttribute("comm", Integer.toString(nodo_com+1));

                    Double corel =  1.0 / (nodo_com + 1);

                    Object[] copie = new Object[nodo_com+1];

                    Object[] ncopie = new Object[nodo_com+1];
                    Object[] scopie;
                    if (g.getNode(cc[l]).hasArray("nco")) scopie = g.getNode(cc[l]).getAttribute("nco");
                    else {
                        scopie = new String[1];
                        scopie[0] = g.getNode(cc[l]).getAttribute("nco");
                    }

                    String fc = "fill-color: ";
                    for (int i = 0; i <= nodo_com; i++)
                    {
                        copie[i] = Double.toString(corel);
                        if (i < nodo_com)
                        {
                            ncopie[i] = scopie[i];
                            int prop = Integer.parseInt((String) scopie[i]);
                            fc += colors[prop]+", ";
                        }
                        else
                        {
                            ncopie[i] = Integer.toString(k);
                            if (i < colors.length) fc += colors[k];
                            else fc += obtColorRandom();
                        }
                    }
                    fc += ";";

                    System.out.println(fc);
                    g.getNode(cc[l]).addAttribute("ui.pie-values", copie);
                    g.getNode(cc[l]).addAttribute("ui.style", fc);
                    g.getNode(cc[l]).addAttribute("nco", ncopie);

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
            System.out.println(rand+" "+letras[rand]);

            color += letras[rand];
        }
        return color;
    }

}

