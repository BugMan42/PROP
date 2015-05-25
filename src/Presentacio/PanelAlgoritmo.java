package Presentacio;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

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
        Graph<String, Integer> g = new SparseGraph<String, Integer>();

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");

        g.addEdge(2, "A", "B");
        g.addEdge(1, "B", "C");
        g.addEdge(3, "C", "A");

        Layout<String,Integer> l = new CircleLayout<String, Integer>(g);
        l.setSize(new Dimension(300, 300));

        VisualizationViewer<String, Integer> vv = new VisualizationViewer<String, Integer>(l);
        vv.setPreferredSize(new Dimension(350, 350));
        vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
        vv.addGraphMouseListener(new GraphMouseListener<String>() {
            @Override
            public void graphClicked(String s, MouseEvent mouseEvent) {
                //if ()
            }

            @Override
            public void graphPressed(String s, MouseEvent mouseEvent) {

            }

            @Override
            public void graphReleased(String s, MouseEvent mouseEvent) {

            }
        });

        /*
        String[] initString =
                { "LOL" };

        String[] initStyles =
                { "regular" };

        */

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
                        .addComponent(vv)
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
                        .addComponent(vv)
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
