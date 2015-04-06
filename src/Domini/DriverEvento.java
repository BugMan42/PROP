package Domini;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by usuario on 04/04/2015.
 */
public class DriverEvento {
    final static String menu = "Bienvenido al driver de evento";
    final static String opcion1 = "1. Alta de un evento";
    final static String opcion2 = "2. Baja de un evento";
    final static String opcion3 = "3. Modificación de un evento";
    final static String opcion4 = "4. Consulta de un evento";
    final static String opcion5 = "5. Ayuda";
    final static String opcion6 = "6. Salir";
    final static String error = "Introduzca un número del 1 al 6";
    final static String fin = "Gracias por usar este driver. THE END";
    private Evento e;

    public static void main(String[] args) throws ParseException {
        ImprimirMenu();
        Scanner entrada = new Scanner(System.in);
        int opcion = Integer.parseInt(entrada.next());
        while (opcion != 6) {
            switch(opcion) {
                case 1:
                    alta(entrada);
                    break;
                /*case 2:
                    baja(entrada);
                    break;
                case 3:
                    modificar(entrada);
                    break;
                case 4:
                    consulta(entrada);
                    break;
                case 5:
                    ayuda(entrada);
                    break;*/
                default:
                    System.out.println(error);
            }
            opcion = Integer.parseInt(entrada.next());
        }
        System.out.println(fin);
    }

    private static void ImprimirMenu() {
        System.out.println(menu);
        System.out.println(opcion1);
        System.out.println(opcion2);
        System.out.println(opcion3);
        System.out.println(opcion4);
        System.out.println(opcion5);
        System.out.println(opcion6);
    }

    private static void alta(Scanner entrada) {
        System.out.println("Bienvenido a alta de un evento");
        System.out.println("Para dar de alta un evento se tienen que introducir");
        System.out.println("El nombre, fecha, subtipo e importancia");
        System.out.println("Introduzca el nombre del evento");

    }
}
