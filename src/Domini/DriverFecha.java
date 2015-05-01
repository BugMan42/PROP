package Domini;

import java.util.Scanner;

/**
 * Created by usuario on 25/04/2015.
 */
public class DriverFecha {
    final static String menu = "Bienvenido al driver de fecha";
    final static String opcion1 = "1. Alta de una fecha";
    final static String opcion2 = "2. Consulta de una fecha";
    final static String opcion3 = "3. Ayuda";
    final static String opcion4 = "4. Salir";
    final static String error = "Introduzca un numero del 1 al 4";
    final static String nueva = "Introduzca una nueva opcion del 1 al 3. 4 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    private static Fecha f;

    public static void main(String[] args) {
        ImprimirMenu();
        Scanner entrada = new Scanner(System.in);
        int opcion = Integer.parseInt(entrada.next());
        while (opcion != 4) {
            switch (opcion) {
                case 1:
                    alta(entrada);
                    break;
                case 2:
                    consulta(entrada);
                    break;
                default:
                    System.out.println(error);
                    break;
            }
            System.out.println(nueva);
            opcion = entrada.nextInt();
        }
        System.out.println(fin);
    }

    private static void ImprimirMenu() {
        System.out.println(menu);
        System.out.println(opcion1);
        System.out.println(opcion2);
        System.out.println(opcion3);
        System.out.println(opcion4);
    }

    private static void alta(Scanner entrada) {
        System.out.println("Introduzca la fecha del evento en formato dd/MM/yyyy");
        String data = entrada.next();
        try {
            f = new Fecha(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consulta(Scanner entrada) {
        System.out.println("Bienvenido a consulta de un evento");
        try {
            System.out.println(f.ToString());
        } catch (Exception e) {
            System.out.println("El evento no existe");
        }
    }
}

