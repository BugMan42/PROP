package Domini;

import java.util.Scanner;

/**
 * Created by falc on 27/03/15.
 * Clase DriverVotación
 */
public class DriverVotacion {

    final static String text1 = "***DRIVER DE VOTACIÓN***";
    final static String opcion1 = "***1.-CONSULTA DE VOTACIÓN";
    final static String opcion5 = "***2.-AYUDA";
    final static String opcion6 = "***3.-SALIR";

    private static Scanner user_input = new Scanner( System.in );
    private static Votacion vt;

    public static void main(String[] args) throws ReflectiveOperationException {

        try {
            vt = new Votacion("Test", "11/04/07", 5);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error, argumento no válido (aunque no debería)");
        }
        int opcion;
        do {
            print_menu();
            opcion = Integer.valueOf(user_input.next());
            decide(opcion);
        }
        while (opcion != 3);
    }

    public static void consulta()
    {
        vt.toString();
    }

    public static void ayuda() {
        System.out.println("Driver del sistema de votaciones");
        System.out.println("En el menú, seleccione la opción que desee. Para salir, utilice la opción 3.");
    }

    public static void print_menu()
    {
        System.out.println("\n"+text1);
        System.out.println(opcion1);
        System.out.println(opcion5);
        System.out.println(opcion6);
        System.out.println("\nEscribe el número de la opción que quieras:");
    }

    public static void decide(int opt) throws InstantiationException, IllegalAccessException {
        switch (opt)
        {
            case 1:
                consulta();
                break;
            case 2:
                ayuda();
                break;
            default:
                System.out.println("Opción incorrecta, vuelve a introducir un número");
                break;
        }
    }
}
