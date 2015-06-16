package Presentacio;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.Random;

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
        else if (r[0].equals("MarcarArista"))
        {
            String a1 = r[1];
            String a2 = r[2];
            Edge e = g.getEdge(a1+"~"+a2);
            if (e == null) e = g.getEdge(a2+"~"+a1);

            e.changeAttribute("ui.style", "fill-color: #777; size: 1px; ");

        }
        else if (r[0].equals("EliminarArista"))
        {
            String a1 = r[1];
            String a2 = r[2];
            g.addEdge(a1+"~"+a2,a1,a2);

        }
        else if (r[0].equals("Clique"))
        {
            for (int i = 1; i < r.length; i++)
            {
                Node n = g.getNode(r[i]);
                n.addAttribute("ui.style", "stroke-width: 1px; stroke-mode: plain; stroke-color: #999;");
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
        else if (r[0].equals("MoverNodoComunidad"))
        {
            String col = g.getNode(r[1]).getAttribute("color");
            g.getNode(r[1]).addAttribute("ui.style", "fill-color: "+col+";");
        }
        else if (r[0].equals("CComunidad"))
        {
            int cit = Integer.parseInt(r[1]);

            String col = obtColorRandom();


            for (int z = 2; z < r.length; z++) {
                //System.out.println(cc[l]);
                String co = g.getNode(r[z]).getAttribute("comm");
                int nodo_com = Integer.parseInt(co);
                Node Z = g.getNode(r[z]);
                Z.addAttribute("comm", Integer.toString(nodo_com - 1));


                Double corel = 1.0 / (nodo_com - 1);

                Object[] copie = new Object[nodo_com - 1];

                Object[] ncopie = new Object[nodo_com - 1];
                Object[] scopie;
                if (g.getNode(r[z]).hasArray("nco")) scopie = Z.getAttribute("nco");
                else {
                    scopie = new String[1];
                    scopie[0] = Z.getAttribute("nco");
                }

                String fc = "fill-color: ";
                for (int i = 0; i < nodo_com-1; i++) {
                    copie[i] = Double.toString(corel);
                    ncopie[i] = scopie[i];
                    int prop = Integer.parseInt((String) scopie[i]);
                    if (prop < colors.length) fc += colors[prop] + ", ";
                    else fc += obtColorRandom() + ", ";
                }
                fc += obtColorRandom()+";";

                //System.out.println(fc);
                Z.addAttribute("ui.pie-values", copie);
                Z.addAttribute("ui.style", fc);
                Z.addAttribute("nco", ncopie);

            }
        }
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
        else if (r[0].equals("MarcarArista"))
        {
            String a1 = r[1];
            String a2 = r[2];
            Edge e = g.getEdge(a1+"~"+a2);
            if (e == null) e = g.getEdge(a2+"~"+a1);
            e.addAttribute("ui.style", "fill-color: red; size: 3px;");
        }
        else if (r[0].equals("EliminarArista"))
        {
            String a1 = r[1];
            String a2 = r[2];
            Edge e = g.getEdge(a1+"~"+a2);
            if (e == null) e = g.getEdge(a2+"~"+a1);
            g.removeEdge(e);

        }
        else if (r[0].equals("Clique"))
        {
            for (int i = 1; i < r.length; i++)
            {
                Node n = g.getNode(r[i]);
                n.addAttribute("ui.style", "stroke-width: 3px; stroke-mode: dots; stroke-color: black;");
            }
        }
        else if (r[0].equals("Comunidad"))
        {
            String c = null;
            String rc = obtColorRandom();
            for (int u = 1; u < r.length; u++)
            {

                Node v = g.getNode(r[u]);
                //c = v.getAttribute("color");
                if (i_com < colors.length) v.addAttribute("ui.style", "fill-color: "+colors[i_com]+";");
                else v.addAttribute("ui.style", "fill-color: "+rc+";");
            }
            ++i_com;
            //System.out.println("Color: "+c);
        }
        else if (r[0].equals("Reset"))
        {
            i_com = 0;
        }
        else if (r[0].equals("MoverNodoComunidad"))
        {
            String col = g.getNode(r[2]).getAttribute("color");
            g.getNode(r[1]).addAttribute("ui.style", "fill-color: "+col+";");
        }
        else if (r[0].equals("CComunidad"))
        {
            int cit = Integer.parseInt(r[1]);

            String col = obtColorRandom();

            for (int z = 2; z < r.length; z++) {
                //System.out.println(cc[l]);
                String co = g.getNode(r[z]).getAttribute("comm");
                int nodo_com = Integer.parseInt(co);
                Node Z = g.getNode(r[z]);

                Z.addAttribute("comm", Integer.toString(nodo_com + 1));

                Double corel = 1.0 / (nodo_com + 1);

                Object[] copie = new Object[nodo_com + 1];

                Object[] ncopie = new Object[nodo_com + 1];
                Object[] scopie;
                if (g.getNode(r[z]).hasArray("nco")) scopie = Z.getAttribute("nco");
                else {
                    scopie = new String[1];
                    scopie[0] = Z.getAttribute("nco");
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
                        ncopie[i] = Integer.toString(cit);
                        if (cit < colors.length) fc += colors[cit];
                        else fc += col; // añade el color creado para esta comunidad

                    }
                }
                fc += ";";

                //System.out.println(fc);
                Z.addAttribute("ui.pie-values", copie);
                Z.addAttribute("ui.style", fc);
                Z.addAttribute("nco", ncopie);

            }
        }

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
                n.addAttribute("ui.style", "stroke-width: 1px; stroke-mode: plain; stroke-color: #999;");
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
