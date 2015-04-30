package Domini;

import java.util.ArrayList;

/**
 * Created by falc on 29/04/15.
 */
public class DriverGirvanNewman {
    private static Girvan_Newman gn;
    private static Entrada in;
    private static Salida out;

    public static void main(String[] args) throws Exception {
        Grafo golf = crearGrafo();
        in = new Entrada(golf, 2);
        out = new Salida();
        gn = new Girvan_Newman(in, out);
        gn.ejecutar_iteración();
        gn.ejecutar_iteración();
        gn.ejecutar_iteración();
        gn.ejecutar_iteración();
        ArrayList<String> hist = out.mostrarHistorial();
        for(int i = 0; i < hist.size(); ++i)
        {
            System.out.println(hist.get(i));
        }


    }

    private static Grafo crearGrafo() throws Exception {
        Grafo g = new Grafo();
        System.out.println(g.V());
        g.añadirVertice("a");

        System.out.println("'a' added");
        g.añadirVertice("b");
        g.añadirVertice("c");
        g.añadirVertice("d");
        g.añadirVertice("e");
        g.añadirVertice("f");
        g.añadirVertice("g");

        g.añadirArista("a", "b", 1);
        g.añadirArista("b", "a", 1);
        g.añadirArista("a", "c", 3);
        g.añadirArista("c", "a", 3);
        g.añadirArista("b","d",3);
        g.añadirArista("d","b",3);
        g.añadirArista("c","d",1);
        g.añadirArista("d","c",1);
        g.añadirArista("d","f",10);
        g.añadirArista("f","d",10);
        g.añadirArista("c","e",7);
        g.añadirArista("e","c",7);
        g.añadirArista("e","f",2);
        g.añadirArista("f","e",2);
        g.añadirArista("e","g",8);
        g.añadirArista("g","e",8);

        return g;
    }
}
