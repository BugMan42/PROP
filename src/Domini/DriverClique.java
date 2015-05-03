package Domini;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by usuario on 28/04/2015.
 */
public class DriverClique {
    /*final static String menu = "Bienvenido al driver de evento";
    final static String opcion1 = "1. Alta de un evento";
    final static String opcion2 = "2. Modificacion de un evento";
    final static String opcion3 = "3. Consulta de un evento";
    final static String opcion4 = "4. Ayuda";
    final static String opcion5 = "5. Salir";
    final static String error = "Introduzca un numero del 1 al 5";
    final static String nueva = "Introduzca una nueva opcion del 1 al 4. 5 para salir";
    final static String fin = "Gracias por usar este driver. THE END";*/
    private static Clique c;
    private static Entrada en;
    private static Salida sa;


    public static void main(String[] args) throws Exception {
        Grafo g = crearGrafo();
        ArrayList<Integer> gv = g.consultarVertices();
        /*for (int i = 0; i < gv.size(); ++i) {
            List<Integer> ady = g.nodosSalida(gv.get(i));
            for (int j = 0; j < ady.size(); ++j) System.out.println("nodo "+Integer.toString(i)+" adyacente a "+Integer.toString(ady.get(j)));
        }*/
        en = new Entrada(g, 5);
        sa = new Salida();
        c = new Clique(en, sa);
        c.ejecutar_algoritmo();
        ArrayList<String> hist = sa.mostrarHistorial();
        for (String aHist : hist) {
            System.out.println(aHist);
        }
        for (Set<Integer> i : sa.comunidad()) {
            System.out.println("Comunidad");
            for (Integer j : i) {
                System.out.println(Integer.toString(j));
            }
        }
    }

    private static Grafo crearGrafo() throws Exception {
        Grafo g = new Grafo();
        g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");
        g.agregarVertice("5");
        g.agregarVertice("6");
        g.agregarVertice("7");
        g.agregarVertice("8");
        g.agregarVertice("9");
        g.agregarVertice("10");
        g.agregarVertice("11");
        g.agregarVertice("12");

        //Grafo 1
        /*g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("3", "5", 1);
        g.agregarArista("4", "5", 1);
        g.agregarArista("5", "6", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "3", 1);
        g.agregarArista("5", "3", 1);
        g.agregarArista("5", "4", 1);
        g.agregarArista("6", "5", 1);*/

        //Grafo 2
        /*g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);
        g.agregarArista("3", "4", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);
        g.agregarArista("4", "3", 1);*/

        //Grafo 3
        /*g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("2", "3", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("3", "2", 1);*/

        //Grafo 4
        /*g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("0", "4", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);
        g.agregarArista("3", "4", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("4", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);
        g.agregarArista("4", "3", 1);*/

        //Grafo 5
        /*g.agregarArista("0", "1", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("4", "5", 1);
        g.agregarArista("4", "9", 1);
        g.agregarArista("5", "6", 1);
        g.agregarArista("6", "7", 1);
        g.agregarArista("7", "8", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("4", "3", 1);
        g.agregarArista("5", "4", 1);
        g.agregarArista("9", "4", 1);
        g.agregarArista("6", "5", 1);
        g.agregarArista("7", "6", 1);
        g.agregarArista("8", "7", 1);*/

        //Grafo 6
        /*g.agregarArista("0", "1", 1);
        g.agregarArista("0", "4", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "7", 1);
        g.agregarArista("2", "8", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("5", "7", 1);
        g.agregarArista("6", "7", 1);
        g.agregarArista("7", "8", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("4", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("7", "2", 1);
        g.agregarArista("8", "2", 1);
        g.agregarArista("4", "3", 1);
        g.agregarArista("7", "5", 1);
        g.agregarArista("7", "6", 1);
        g.agregarArista("8", "7", 1);*/

        //Grafo 7

        /*g.agregarArista("0", "1", 1);
        g.agregarArista("0", "4", 1);
        g.agregarArista("0", "5", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("1", "6", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);
        g.agregarArista("3", "6", 1);
        g.agregarArista("4", "8", 1);
        g.agregarArista("5", "9", 1);
        g.agregarArista("5", "11", 1);
        g.agregarArista("6", "7", 1);
        g.agregarArista("6", "10", 1);
        g.agregarArista("7", "11", 1);
        g.agregarArista("7", "12", 1);
        g.agregarArista("8", "9", 1);
        g.agregarArista("9", "10", 1);
        g.agregarArista("10", "11", 1);
        g.agregarArista("11", "12", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("4", "0", 1);
        g.agregarArista("5", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("6", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);
        g.agregarArista("6", "3", 1);
        g.agregarArista("8", "4", 1);
        g.agregarArista("9", "5", 1);
        g.agregarArista("11", "5", 1);
        g.agregarArista("7", "6", 1);
        g.agregarArista("10", "6", 1);
        g.agregarArista("11", "7", 1);
        g.agregarArista("12", "7", 1);
        g.agregarArista("9", "8", 1);
        g.agregarArista("10", "9", 1);
        g.agregarArista("11", "10", 1);
        g.agregarArista("12", "11", 1);*/
        return g;
    }
}
