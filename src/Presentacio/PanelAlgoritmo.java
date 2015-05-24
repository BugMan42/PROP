package Presentacio;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
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
        Graph<String, Integer> g = new SparseGraph<String, Integer>();

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");

        g.addEdge(2, "A", "B");
        g.addEdge(1, "B", "C");
        g.addEdge(3, "C", "A");

        Layout<String,Integer> l = new CircleLayout<String, Integer>(g);
        l.setSize(new Dimension(300, 300));

        BasicVisualizationServer<String, Integer> vv = new BasicVisualizationServer<String, Integer>(l);
        vv.setPreferredSize(new Dimension(350, 350));

        add(vv);

        String[] initString =
                { "LOL" };

        String[] initStyles =
                { "regular" };



        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane[Enabled].backgroundPainter", Color.black);

        JTextPane tp1 = new JTextPane();
        tp1.setForeground(Color.green);
        tp1.setText("REKT");
        tp1.putClientProperty("Nimbus.Overrides", defaults);
        tp1.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp1.setBackground(Color.black);

        JTextPane tp2 = new JTextPane();
        tp2.setForeground(Color.orange);
        tp2.setText("REKT");
        tp2.putClientProperty("Nimbus.Overrides", defaults);
        tp2.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp2.setBackground(Color.blue);

        JTextPane tp3 = new JTextPane();
        tp3.setForeground(Color.magenta);
        tp3.setText("REKT");
        tp3.putClientProperty("Nimbus.Overrides", defaults);
        tp3.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        tp3.setBackground(Color.lightGray);

        add(tp1);
        add(tp2);
        add(tp3);
    }

}
