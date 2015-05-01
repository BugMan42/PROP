import Domini.*;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by bug on 23/03/15.
 * Clase PROP
 */
public class Prop {
    final static String text1 = "***BIENVENIDO AL PROYECTO DE PROP***";
    final static String opcion1 = "***1.-GESTIÓN DE CONGRESISTAS";
    final static String opcion2 = "***2.-GESTIÓN DE EVENTOS";
    final static String opcion3 = "***3.-BÚSQUEDA COMUNIDADES";
    final static String opcion4 = "***4.-GESTIÓN COMUNIDADES";
    final static String opcion5 = "***5.-GESTIÓN DATOS";
    final static String opcion6 = "***6.-AYUDA";
    final static String opcion7 = "***7.-SALIR";

    public static void main(String[] args) throws ParseException {
        print(text1);
        Scanner user_input = new Scanner( System.in );
        int opcion;
        do {
            print_menu();
            opcion = Integer.parseInt(user_input.next());
            decide(opcion,args);
        } while (opcion != 7);
    }
    private static void decide(int opcion, String[] args) {
        switch (opcion) {
            case 1:
                //Domini.DriverCongreso.main(args);
                break;
            case 2:
                //Domini.DriverCongreso.main(args);
                break;
            case 3:
                //Domini.DriverCongreso.main(args);
                break;
            case 4:
                //Domini.DriverCongreso.main(args);
                break;
            case 5:
                //Domini.DriverCongreso.main(args);
                break;
            case 6:
                //Domini.DriverCongreso.main(args);
                break;

            case 7:
                print("FIN DEL PROGRAMA");
                break;

            default:
                print("\nOpción incorrecta, vuelve a introducir un número.\n");
                break;
        }

    }
    private static void print_menu() {
        print(opcion1);
        print(opcion2);
        print(opcion3);
        print(opcion4);
        print(opcion5);
        print(opcion6);
        print(opcion7);
        print("Escribe la opcion");
    }
    private static void print(String S) {
        System.out.println(S);
    }

}
