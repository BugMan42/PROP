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
        for (String aHist : hist) {
            System.out.println(aHist);
        }


    }

    private static Grafo crearGrafo() throws Exception {
        Grafo g = new Grafo();
        g.agregarVertice("a");
        g.agregarVertice("b");
        g.agregarVertice("c");
        g.agregarVertice("d");
        g.agregarVertice("e");
        g.agregarVertice("f");
        g.agregarVertice("g");

        g.agregarArista("a", "b", 1);
        g.agregarArista("b", "a", 1);
        g.agregarArista("a", "c", 3);
        g.agregarArista("c", "a", 3);
        g.agregarArista("b", "d", 3);
        g.agregarArista("d", "b", 3);
        g.agregarArista("c", "d", 1);
        g.agregarArista("d", "c", 1);
        g.agregarArista("d", "f", 10);
        g.agregarArista("f", "d", 10);
        g.agregarArista("c", "e", 7);
        g.agregarArista("e", "c", 7);
        g.agregarArista("e", "f", 2);
        g.agregarArista("f", "e", 2);
        g.agregarArista("e", "g", 8);
        g.agregarArista("g", "e", 8);

        return g;
    }
}
