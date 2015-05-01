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

        // EJEMPLO 1
        /*
        g.añadirVertice("a");
        g.añadirVertice("b");
        g.añadirVertice("c");

        g.añadirArista("a", "b", 1);
        g.añadirArista("b", "a", 1);
        g.añadirArista("b", "c", 1);
        g.añadirArista("c","b",1);
        g.añadirArista("c","a",1);
        g.añadirArista("a","c",1);
        */

        // EJEMPLO 2

        g.añadirVertice("a");
        g.añadirVertice("b");
        g.añadirVertice("c");
        g.añadirVertice("d");
        g.añadirVertice("e");
        g.añadirVertice("f");

        g.añadirArista("a","b",1);
        g.añadirArista("b","a",1);
        g.añadirArista("b","c",1);
        g.añadirArista("c","b",1);
        g.añadirArista("c","a",1);
        g.añadirArista("a","c",1);
        g.añadirArista("c","d",1);
        g.añadirArista("d","c",1);
        g.añadirArista("d","e",1);
        g.añadirArista("e","d",1);
        g.añadirArista("e","f",1);
        g.añadirArista("f","e",1);
        g.añadirArista("f","d",1);
        g.añadirArista("d","f",1);


        //g.añadirArista("a","a",1);

        ArrayList<Integer> v = g.consultarVertices();
        //for (int i : v) dl.print(String.valueOf(i));


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
