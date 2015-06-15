package Presentacio;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * Created by falc on 14/06/15.
 */
public class Ejecutor {

    private Graph g;

    public Ejecutor (Graph gr)
    {
        g = gr;
    }


    public void ejecuta(String a, String b, String c)
    {
        anterior(a);
        actual(b);
        posterior(c);
    }

    private void anterior(String a)
    {
        String r[] = a.split("\\s");
        if (r[0].equals("Visita"))
        {
            String s = r[1];
            Node n = g.getNode(s);
            n.changeAttribute("ui.style", "stroke-width: 1px; stroke-color: #999; ");

        }
        else if (r[0].equals("EliminarArista"))
        {
            String a1 = r[1];
            String a2 = r[2];
            Edge e = g.getEdge(a1+"~"+a2);
            if (e == null) e = g.getEdge(a2+"~"+a1);

            e.changeAttribute("ui.style", "fill-color: #777; size: 1px; ");

        }

        /*else if (r[0].equals("AñadirArista"))
        {
            String a1 = r[1];
            String a2 = r[2];

            Edge e = g.getEdge(a1+"~"+a2);
            if (e == null) e = g.getEdge(a2+"~"+a1);

            e.changeAttribute("ui.style", "shape: line; fill-color: #777; size: 1px; ");
            if (a1.equals(a2)) g.removeEdge(a1, a2);
        }*/
    }

    private void actual(String b)
    {
        String r[] = b.split("\\s");
        if (r[0].equals("Visita"))
        {
            String s = r[1];
            Node n = g.getNode(s);
            n.addAttribute("ui.style", "stroke-width: 4px; stroke-color: gold; ");
        }
        else if (r[0].equals("EliminarArista"))
        {
            String a1 = r[1];
            String a2 = r[2];
            Edge e = g.getEdge(a1+"~"+a2);
            if (e == null) e = g.getEdge(a2+"~"+a1);
            e.addAttribute("ui.style", "fill-color: red; size: 3px;");
        }
        /*else if (r[0].equals("AñadirArista"))
        {
            String a1 = r[1];
            String a2 = r[2];
            if (a1.equals(a2)) g.addEdge(a1+"~"+a2, a1, a2);
            Edge e = g.getEdge(a1+"~"+a2);
            if (e == null) e = g.getEdge(a2+"~"+a1);
            e.addAttribute("ui.style", "shape: blob; fill-color: black; size: 3px;");
        }*/
    }

    private void posterior (String c)
    {

    }

}
