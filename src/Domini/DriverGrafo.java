package Domini;

import java.util.ArrayList;
import java.util.*;

public class DriverGrafo {
    private static void print(String str) {
        System.out.println(str);
    }
    public static void main(String[] args)throws Exception {

        Grafo a = new Grafo();
        a.agregarVertice("abc");
        a.agregarVertice("abd");
        print(a.consultarVertices().toString());
        print(a.consultarVerticesID().toString());
        //a.agregarArista(0,1,2.0);

       /* Congresista c1 = new Congresista("12345678A","Paco","Sobaco",8,"Springfield","Massachusets","Republicano");
        Congresista c2 = new Congresista("12345678B", "Barack","Obama",53,"Springfield","Massachusets", "Republicano");
        Nodo n1 = new Nodo(c1);
        Nodo n2 = new Nodo(c2);*/
        /*Set<Double> a = new TreeSet<Double>();
        a.add(2.0);
        print(a.toString());
        a.add(1.0);
        a.add(1.0);
        print(a.toString());
        a.add(0.0);
        print(a.toString());*/


        //Arista a = new Arista(n1, n2, 3);*/
        /*String a = "00";
        String b = "11";
        String c = "22";
        String d = "33";
        Grafo g = new Grafo();
        try {
            g.añadirVertice(a);
            g.añadirVertice(b);
            g.añadirVertice(c);
            g.añadirVertice(d);
            g.eliminarVertice(a);
        }
        catch (Exception e) {
            print(e.getMessage());
        }
        ArrayList<Integer> aux;
        //aux = g.consultarVertices();
        print("Lista Enteros:");
        print(g.consultarVertices().toString());
        print("Lista Strings");
        print("");*/
        //print(g.consultarVerticesID().toString());

        //g.add_nodo(n2);
        //g.add_arista(a);
        //g.printNodos();
        //g.printAristas();*/
    }
}
