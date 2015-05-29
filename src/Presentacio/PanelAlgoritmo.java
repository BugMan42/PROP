package Presentacio;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase PanelAlgoritmo
 * Created by falc on 20/05/15.
 */
public class PanelAlgoritmo extends Panel{

    CPAlgoritmo cpa;
    private Graph g;
    private int option;

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
        g.addAttribute("ui.stylesheet", "node { text-alignment: above; fill-color: blue; }");
        g.addAttribute("ui.stylesheet", "node.B { fill-color: green; }");
        g.addAttribute("ui.stylesheet", "node.C { fill-color: red; }");
        //g.addAttribute("ui.stylesheet", "url('file:///home/falc/IdeaProjects/PROP/src/images/style.css')");

        Viewer viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        ViewPanel view = viewer.addDefaultView(false);


        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane[Enabled].backgroundPainter", Color.black);

        final JTextPane tp1 = new JTextPane();
        tp1.setForeground(Color.green);
        tp1.setText("REKT");
        tp1.putClientProperty("Nimbus.Overrides", defaults);
        tp1.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp1.setBackground(Color.black);
        tp1.setEnabled(false);
        JRadioButton rb1 = new JRadioButton("Louvain");
        final JButton mostrar1 = new JButton("Mostrar grafo");
        mostrar1.setEnabled(false);

        final JTextPane tp2 = new JTextPane();
        tp2.setForeground(new Color(255, 102, 0));
        tp2.setText("REKT");
        tp2.putClientProperty("Nimbus.Overrides", defaults);
        tp2.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp2.setBackground(new Color(0,0,102));
        tp2.setEnabled(false);
        JRadioButton rb2 = new JRadioButton("Girvan-Newman");
        final JButton mostrar2 = new JButton("Mostrar grafo");
        mostrar2.setEnabled(false);

        final JTextPane tp3 = new JTextPane();
        tp3.setForeground(new Color(255, 255, 0));
        tp3.setText("REKT");
        tp3.putClientProperty("Nimbus.Overrides", defaults);
        tp3.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp3.setBackground(new Color(127, 0, 255));
        tp3.setEnabled(false);
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

        JLabel f_pref = new JLabel("Preferencia:");
        JTextField pref = new JTextField("0");

        JButton demo = new JButton("DEMO");
        demo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpa.createDemoGraph();
            }
        });

        //Acciones de los radiobuttons
        rb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tp1.setEnabled(true);
                tp2.setEnabled(false);
                tp3.setEnabled(false);

                option = 1;
            }
        });

        rb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tp1.setEnabled(false);
                tp2.setEnabled(true);
                tp3.setEnabled(false);

                option = 2;
            }
        });

        rb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                cpa.execute_algoritm(option);
                if (option == 1) mostrar1.setEnabled(true);
                else if (option == 2) mostrar2.setEnabled(true);
                else if (option == 3) mostrar3.setEnabled(true);
            }
        });

        mostrar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarGrafo();
                mostrar2.setEnabled(false);
            }
        });

        gr.setHorizontalGroup(gr.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(view)
                        .addGroup(gr.createSequentialGroup()
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb1)
                                                        .addComponent(tp1)
                                                        .addComponent(mostrar1)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb2)
                                                        .addComponent(tp2)
                                                        .addComponent(mostrar2)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(rb3)
                                                        .addComponent(tp3)
                                                        .addComponent(mostrar3)
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
                                                        .addComponent(tp1)
                                                        .addComponent(mostrar1)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb2)
                                                        .addComponent(tp2)
                                                        .addComponent(mostrar2)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(rb3)
                                                        .addComponent(tp3)
                                                        .addComponent(mostrar3)
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
        int na = cpa.num_aristas();
        for (int i = 0; i < na; i++)
        {
            String is = Integer.toString(i);
            g.addNode(is);
            g.getNode(is).addAttribute("ui.label", is);
        }

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
            //System.out.println(c);
            String[] cc = c.split(",\\s");
            if (!cc[0].equals("-"))
            {
                System.out.println(cc.length);
                for (int l = 0; l < cc.length; l++)
                {
                    System.out.println("Node: "+cc[l]+" → "+Character.toString(chargen));
                    g.getNode(cc[l]).addAttribute("ui.class", Character.toString(chargen));
                }
                chargen++;
            }
        }


    }

}

