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
        a.agregarVertice("a");
        a.agregarVertice("b");
        //print(a.consultarVertices().toString());
        //print(a.consultarVerticesID().toString());
        a.agregarArista(0, 1, 2.0);
        a.agregarArista(0, 1, 3.0);
        a.agregarArista(0, 1, 4.0);
        a.agregarArista(0, 3,1.0);
        a.agregarArista(0, 2, 1.0);
        print(a.nodosSalida(0) + "");
        print(a.pesoAristasVertices(0, 1) + "");
        a.modificarArista(0, 1, 2.0,3.0);
        print(a.pesoAristasVertices(0,1)+"");
        //a.agregarVertice("acd");

        /*Set<Double> a = new TreeSet<Double>();
        a.add(2.0);
        print(a.toString());
        a.add(1.0);
        a.add(1.0);
        print(a.toString());
        a.add(0.0);
        print(a.toString());*/


    }
}
