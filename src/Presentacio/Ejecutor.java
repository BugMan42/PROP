package Presentacio;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * Created by falc on 14/06/15.
 */
public class Ejecutor {

    private Graph g;
    private String[] colors;

    private int i_com = 0;

    public Ejecutor (Graph gr, String[] c)
    {
        g = gr;
        colors = c;
    }


    public void ejecuta1(String a, String b)
    {
        anterior1(a);
        actual(b);
    }

    public void ejecuta2(String a, String b)
    {
        anterior2(a);
        actual(b);
    }

    private void anterior1(String a)
    {
        String r[] = a.split("\\s");
        if (r[0].equals("Visita"))
        {
            String s = r[1];
            Node n = g.getNode(s);
            n.changeAttribute("ui.style", "stroke-mode: plain; stroke-width: 1px; stroke-color: #999; ");

        }
        else if (r[0].equals("EliminarArista"))
        {
            String a1 = r[1];
            String a2 = r[2];
            Edge e = g.getEdge(a1+"~"+a2);
            if (e == null) e = g.getEdge(a2+"~"+a1);

            e.changeAttribute("ui.style", "fill-color: #777; size: 1px; ");

        }
        else if (r[0].equals("AñadirVertice"))
        {
            /*String d = "A"+r[1];
            g.removeNode(d);*/
        }
        else if (r[0].equals("Clique"))
        {
            for (int i = 1; i < r.length; i++)
            {
                Node n = g.getNode(r[i]);
                n.addAttribute("ui.style", "stroke-width: 1px; shape: pie-chart; ");
            }
        }
        else if (r[0].equals("Comunidad"))
        {
            for (int u = 1; u < r.length; u++)
            {
                Node v = g.getNode(r[u]);
                v.addAttribute("ui.style", "fill-color: #CCC;");
            }
            if (i_com > 0) --i_com;
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
            n.addAttribute("ui.style", "stroke-mode: dots; stroke-width: 4px; stroke-color: black; ");
        }
        else if (r[0].equals("EliminarArista"))
        {
            String a1 = r[1];
            String a2 = r[2];
            Edge e = g.getEdge(a1+"~"+a2);
            if (e == null) e = g.getEdge(a2+"~"+a1);
            e.addAttribute("ui.style", "fill-color: red; size: 3px;");
        }
        else if (r[0].equals("AñadirVertice"))
        {

        }
        else if (r[0].equals("Clique"))
        {
            for (int i = 1; i < r.length; i++)
            {
                Node n = g.getNode(r[i]);
                n.addAttribute("ui.style", "stroke-width: 3px; shape: diamond; ");
            }
        }
        else if (r[0].equals("Comunidad"))
        {
            String c = null;
            for (int u = 1; u < r.length; u++)
            {
                Node v = g.getNode(r[u]);
                //c = v.getAttribute("color");
                v.addAttribute("ui.style", "fill-color: "+colors[i_com]+";");
            }
            ++i_com;
            //System.out.println("Color: "+c);
        }
        else if (r[0].equals("Reset"))
        {
            i_com = 0;
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

    private void anterior2 (String c)
    {
        String r[] = c.split("\\s");
        if (r[0].equals("Visita"))
        {
            String s = r[1];
            Node n = g.getNode(s);
            n.changeAttribute("ui.style", "stroke-mode: plain; stroke-width: 1px; stroke-color: #999; ");

        }
        else if (r[0].equals("Clique"))
        {
            for (int i = 1; i < r.length; i++)
            {
                Node n = g.getNode(r[i]);
                n.addAttribute("ui.style", "stroke-width: 1px; shape: pie-chart; ");
            }
        }
    }

}
