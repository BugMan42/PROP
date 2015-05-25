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
        in = new Entrada(new Grafo(), 2);
        out = new Salida();
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
        Grafo g = new Grafo();
        System.out.println("Introduzca el número de vértices del grafo a crear:");
        int cantidad = Integer.parseInt(user_input.next());
        char ch = 'a';
        for (int i = 0; i < cantidad; ++i)
        {
            try {
                g.agregarVertice(Character.toString(ch));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ++ch;
        }

        System.out.println("Introduzca el número de aristas del grafo a crear:");
        int n_aristas = Integer.parseInt(user_input.next());
        System.out.println("Introduzca las aristas con el siguiente formato (Nº arista_origen, Nº arista_destino, peso):");
        for (int j = 0; j < n_aristas; ++j)
        {

            int v1 = Integer.parseInt(user_input.next());
            int v2 = Integer.parseInt(user_input.next());
            double p = Double.parseDouble(user_input.next());

            if ((v1 < cantidad && v1 >= 0) && (v2 < cantidad && v2 >= 0)) {
                try {
                    g.agregarArista(v1, v2, p);
                    g.agregarArista(v2, v1, p);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Vértices incorrecto, operación cancelada");
                }

            }
        }

        in.modGrafo(g);

    }

    public static void clique()
    {
        try {
            alg = new Clique(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(out.mostrarHistorial());
    }

    public static void louvain()
    {
        try {
            alg = new Louvain(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(out.mostrarHistorial());
    }

    public static void girvan_newman()
    {
        try {
            alg = new Girvan_Newman(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void ayuda() {
        System.out.println("Driver de los algoritmos");
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
