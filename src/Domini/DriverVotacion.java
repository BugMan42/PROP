package Domini;

import java.util.Scanner;

/**
 * Created by falc on 27/03/15.
 */
public class DriverVotacion {

    final static String text1 = "***DRIVER DE VOTACIÓN***";
    final static String opcion1 = "***1.-ALTA DE VOTO";
    final static String opcion2 = "***2.-CONSULTA DE VOTO";
    final static String opcion3 = "***3.-MODIFICACIÓN DE VOTO";
    final static String opcion4 = "***4.-BAJA DE VOTO";
    final static String opcion5 = "***5.-AYUDA";
    final static String opcion6 = "***6.-SALIR";

    private static Scanner user_input = new Scanner( System.in );
    private static Votacion vt;

    public static void main(String[] args) throws ReflectiveOperationException {
        vt = new Votacion();
        int opcion;
        do {
            print_menu();
            opcion = Integer.valueOf(user_input.next());
            decide(opcion);
        }
        while (opcion != 6);
    }

    public static void alta() throws IllegalAccessException, InstantiationException {
        System.out.println("Escribe el tipo de voto a introducir:");
        String s1 = user_input.next();
        Class c;
        try {
            c = Class.forName("Domini." + s1);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Tipo de voto no válido, operación cancelada.");
            return;
        }
        Voto v = (Voto) c.newInstance();
        System.out.println("Escribe el DNI del congresista votante:");
        v.mod_dni(user_input.next());
        vt.añadir_voto(v);
    }

    public static void consulta()
    {
        System.out.println("Introduce el DNI del voto a buscar:");
        String v2 = vt.consultar_voto(user_input.next());
        System.out.println(v2);
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
                alta();
                break;
            case 2:
                consulta();
                break;

            default:
                break;
        }
    }
}
