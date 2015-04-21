package Domini;

import java.util.Scanner;

/**
 * Created by falc on 7/04/15.
 */
public class DriverAlgoritmo {
    final static String text1 = "***Driver de Algoritmo***";
    final static String opcion1 = "***1.-Crear Grafo";
    final static String opcion2 = "***2.-Algoritmo Clique Percolation";
    final static String opcion3 = "***3.-Algoritmo Louvain";
    final static String opcion4 = "***4.-Algoritmo Girvan-Newman";
    final static String opcion5 = "***5.-Ayuda";
    final static String opcion6 = "***6.-Salir";

    private static Scanner user_input = new Scanner( System.in );

    private static Grafo graf;
    private static Entrada in;
    private static Algoritmo alg;
    private static Salida out;

    public static void main(String[] args) throws ReflectiveOperationException {
        int opcion;
        do {
            print_menu();
            opcion = Integer.valueOf(user_input.next());
            decide(opcion);
        }
        while (opcion != 6);
    }

    public static void crear_grafo()
    {
        /*graf = new Grafo(7);
        Arista a = new Arista(1,2,0);
        graf.addEdge(a);
         */



    }

    public static void clique()
    {
        alg = new Clique();
        alg.ejecutar_algoritmo(in, out);
        //Mostrar grafo resultante
    }

    public static void louvain()
    {
        alg = new Louvain();
        alg.ejecutar_algoritmo(in, out);
        //Mostrar grafo resultante
    }

    public static void girvan_newman()
    {
        alg = new Girvan_Newman();
        alg.ejecutar_algoritmo(in, out);
        //Mostrar grafo resultante

    }

    public static void ayuda() {
        System.out.println("Driver de los algorismos");
        System.out.println("En el menú, seleccione la opción que desee. Para salir, utilice la opción 6.");
    }

    public static void print_menu()
    {
        System.out.println("\n"+text1);
        System.out.println(opcion1);
        System.out.println(opcion2);
        System.out.println(opcion3);
        System.out.println(opcion4);
        System.out.println(opcion5);
        System.out.println(opcion6);
        System.out.println("\nEscribe el número de la opción que quieras:");
    }

    public static void decide(int opt) {
        switch (opt)
        {
            case 1:
                crear_grafo();
                break;
            case 2:
                clique();
                break;
            case 3:
                louvain();
                break;
            case 4:
                girvan_newman();
                break;
            case 5:
                ayuda();
                break;
            default:
                break;
        }
    }
}
