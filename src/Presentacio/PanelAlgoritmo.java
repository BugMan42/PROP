package Presentacio;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;

/**
 * Clase PanelAlgoritmo
 * Created by falc on 20/05/15.
 */
public class PanelAlgoritmo extends Panel{

    CPAlgoritmo cpa;

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

        Graph g = new SingleGraph("GOLF");
        char a = 'a';
        for (int i = 0; i < 100; ++i)
        {
            g.addNode(Character.toString(a));
            Node n = g.getNode(Character.toString(a));
            n.addAttribute("ui.label", Character.toString(a));

            ++a;
        }

        g.addEdge("Troll", "a", "b");
        g.addEdge("GG EASY", "c", "z");
        g.addEdge("LOL", "a", "z");

        g.addAttribute("ui.antialias");
        Viewer viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        ViewPanel view = viewer.addDefaultView(false);


        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane[Enabled].backgroundPainter", Color.black);

        JLabel l1 = new JLabel("Louvain");
        JTextPane tp1 = new JTextPane();
        tp1.setForeground(Color.green);
        tp1.setText("REKT");
        tp1.putClientProperty("Nimbus.Overrides", defaults);
        tp1.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp1.setBackground(Color.black);
        tp1.setEnabled(false);

        JLabel l2 = new JLabel("Girvan-Newman");
        JTextPane tp2 = new JTextPane();
        tp2.setForeground(Color.orange);
        tp2.setText("REKT");
        tp2.putClientProperty("Nimbus.Overrides", defaults);
        tp2.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp2.setBackground(Color.blue);
        tp2.setEnabled(false);

        JLabel l3 = new JLabel("Clique Percolation");
        JTextPane tp3 = new JTextPane();
        tp3.setForeground(Color.yellow);
        tp3.setText("REKT");
        tp3.putClientProperty("Nimbus.Overrides", defaults);
        tp3.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp3.setBackground(Color.magenta);
        tp3.setEnabled(false);

        gr.setHorizontalGroup(gr.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(view)
                        .addGroup(gr.createSequentialGroup()
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(l1)
                                                        .addComponent(tp1)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(l2)
                                                        .addComponent(tp2)
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addComponent(l3)
                                                        .addComponent(tp3)
                                        )

                        )
        );

        gr.setVerticalGroup(gr.createSequentialGroup()
                        .addComponent(view)
                        .addGroup(gr.createParallelGroup()
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(l1)
                                                        .addComponent(tp1)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(l2)
                                                        .addComponent(tp2)
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(l3)
                                                        .addComponent(tp3)
                                        )
                        )
        );

        gr.setAutoCreateGaps(true);
        gr.setAutoCreateContainerGaps(true);
    }

}
