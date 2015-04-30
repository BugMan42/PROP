package Domini;

import java.util.ArrayList;

/**
 * Created by jose on 2/04/15.
 */
public class DriverLouvain {
    public static void main(String[] args) throws Exception{
        /*Dni a = new Dni(),
        Congresista c1 = new Congresista(Dni,"Paco","Sobaco",8,"Springfield","Massachusets","Republicano");
        Congresista c2 = new Congresista(Dni, "Barack","Obama",53,"Springfield","Massachusets", "Republicano");
        Nodo n1 = new Nodo(c1);
        Nodo n2 = new Nodo(c2);
        Arista a = new Arista(n1, n2, 3);

        Grafo g = new Grafo();

        Louvain l = new Louvain();
        l.ejecutar_algoritmo(g);*/

        DriverLouvain dl = new DriverLouvain();

        Grafo g = new Grafo();
        g.agregarVertice("a");
        g.agregarVertice("b");
        g.agregarVertice("c");
        /*
        int a = g.consultarVertice("a");
        int b = g.consultarVertice("b");
        int c = g.consultarVertice("c");
        */
        g.agregarArista("a","b",1);
        g.agregarArista("b","c",1);
        g.agregarArista("c","a",1);

        //g.agregarArista("a","a",1);

        ArrayList<Integer> v = g.consultarVertices();
        for (int i : v) dl.print(String.valueOf(i));


        //dl.print(String.valueOf(g.V()));

        double Q = 0.000001;
        Entrada in = new Entrada(g,Q);
        Salida out = new Salida();
        Louvain l = new Louvain(in,out);
        Grafo s = l.ejecutar_algoritmo();
    }

    private void print(String s){
        System.out.println(s);
    }
}
