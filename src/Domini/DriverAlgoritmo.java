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
    private static Algoritmo alg;

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
        graf = new Grafo(); //TODO: Obtener un grafo

    }

    public static void consulta()
    {
        System.out.println("Introduce el DNI del voto a buscar:");
        String v2 = null;
        try {
            //v2 = vt.consultar_voto(user_input.next());
            System.out.println(v2);
        } catch (Exception e) {
            System.out.println("DNI introducido es incorrecto, operación cancelada.");
        }
    }

    public static void baja()
    {
        System.out.println("Introduce el DNI del voto a borrar:");
        try {
            //vt.eliminar_voto(user_input.next());
        } catch (Exception e) {
            System.out.println("DNI introducido es incorrecto, operación cancelada.");
        }
    }

    public static void modificacion()
    {
        System.out.println("Introduce el DNI del voto a modificar:");
        String s1 = user_input.next();
        try {
            //vt.eliminar_voto(s1);
        } catch (Exception e) {
            System.out.println("DNI introducido es incorrecto, operación cancelada.");
            return;
        }

        System.out.println("Introduce el tipo de voto:");
        System.out.println("(Nulo, Blanco, Abstencion, Positivo o Negativo)");
        String s2 = user_input.next();
        Class c;
        Voto v;
        try {
            c = Class.forName("Domini." + s2);
            v = (Voto) c.newInstance();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Tipo de voto no válido, operación cancelada.");
            return;
        }
        //vt.añadir_voto(s1,v);

    }

    public static void ayuda() {
        System.out.println("Driver del sistema de votaciones");
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

    public static void decide(int opt) throws InstantiationException, IllegalAccessException {
        switch (opt)
        {
            case 1:
                crear_grafo();
                break;
            case 2:
                consulta();
                break;
            case 3:
                modificacion();
                break;
            case 4:
                baja();
                break;
            case 5:
                ayuda();
                break;
            default:
                break;
        }
    }
}
