package Domini;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by bug on 23/03/15.
 */
public class DriverCongresista {
    final static String text1 = "***DRIVER DE CONGRESISTA***";
    final static String opcion1 = "***1.-ALTA DE CONGRESISTA";
    final static String opcion2 = "***2.-CONSULTA CONGRESISTA";
    final static String opcion3 = "***3.-MODIFICACIÓN DE CONGRESISTA";
    final static String opcion4 = "***4.-BAJA DE CONGRESISTA";
    final static String opcion5 = "***5.-AYUDA";
    final static String opcion6 = "***6.-SALIR";
    final static String Usage = "###USAGE###\n" +
                                "###LOS CAMPOS NO PUEDEN ESTAR VACIOS\n" +
                                "###EDAD TIENE QUE SER >= 18 AÑOS##";
    final static String[] aux = new String[]{"Dni", "Nombre", "Apellido", "Edad", "Ciudad", "Estado", "Partido", " NO VALIDO"};
    final static String fin = "\"FIN DEL DRIVER\"";
    final static String inc = "\nOpción incorrecta, vuelve a introducir un número.\n";
    private static Congresista C;

    public static void main(String[] args) throws ParseException {
        //print(text1);
        Scanner user_input = new Scanner(System.in);
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
                alta();
                break;
            case 2:
                consulta();
                break;
            case 3:
                modificacion();
                break;
            case 4:
                //Domini.DriverCongreso.main(args);
                break;
            case 5:
                //Domini.DriverCongreso.main(args);
                break;

            case 6:
                print(fin);
                break;

            default:
                print(inc);
                break;
        }

    }
    private static void alta() {
        print("Has Seleccionado Alta de un Congresista");
        print("Rellene el formulario:");
        print("FORMATO: DNI NOMBRE APELLIDO EDAD CIUDAD ESTADO PARTIDO");
        print("TODOS LOS DATOS SON OBLIGATORIOS");
        Scanner user_input = new Scanner(System.in);
        String entrada = user_input.nextLine();
        try {
            //C = new Congresista(entrada);
            //print(C.toString());
        }
        catch (IllegalArgumentException e){
            print("\n#######--ERROR--#######");
            //print(e.getMessage());
            switch (Integer.parseInt(e.getMessage())) {
                case 0:
                    print("CAMPO "+aux[0]+aux[7]);
                    break;
                case 1:
                    print("CAMPO "+aux[1]+aux[7]);
                    break;
                case 2:
                    print("CAMPO "+aux[2]+aux[7]);
                    break;
                case 3:
                    print("CAMPO "+aux[3]+aux[7]);
                    break;
                case 4:
                    print("CAMPO "+aux[4]+aux[7]);
                    break;
                case 5:
                    print("CAMPO "+aux[5]+aux[7]);
                    break;
                case 6:
                    print("CAMPO "+aux[6]+aux[7]);
                    break;
                case 7:
                    print("FORMULARIO VACIO");
                    break;
                case 8:
                    print("FORMULARIO NO CONTIENE LOS 7 CAMPOS");
                    break;
                default:
                    break;
            }
            print(Usage+"\n");
           // print(e.getMessage());
            //print("DNI no puede ser vacío\n");
        }
    }
    private static void consulta() {
        print("Has Seleccionado: Consulta de un Congresista");
        print("FORMATO: dni nombre apellido edad ciudad estado partido");
        //print(aux[0]+": "+C.obt_dni());
        //print(aux[1]+": "+C.obt_nombre());
        //print(aux[2]+": "+C.obt_apellido());
        //print(aux[3]+": "+C.obt_edad());
        //print(aux[4]+": "+C.obt_ciudad());
        //print(aux[5]+": "+C.obt_estado());
        //print(aux[6]+": "+C.obt_partido());
        if (C != null) print(C.toString());
        else print("Congresista es null");
    }
    private static void modificacion() {
        print("Has Seleccionado Modificación de un Congresista");
        print("Rellene los nuevos valores en el siguiente formulario:");
        print("FORMATO: DNI NOMBRE APELLIDO EDAD CIUDAD ESTADO PARTIDO");
        print("TODOS LOS DATOS SON OBLIGATORIOS");
        Scanner user_input = new Scanner(System.in);
        String entrada = user_input.nextLine();
        try {
            //C = new Congresista(entrada);
            //print(C.toString());
        }
        catch (IllegalArgumentException e){
            print("\n#######--ERROR--#######");
            //print(e.getMessage());
            switch (Integer.parseInt(e.getMessage())) {
                case 0:
                    print("CAMPO "+aux[0]+aux[7]);
                    break;
                case 1:
                    print("CAMPO "+aux[1]+aux[7]);
                    break;
                case 2:
                    print("CAMPO "+aux[2]+aux[7]);
                    break;
                case 3:
                    print("CAMPO "+aux[3]+aux[7]);
                    break;
                case 4:
                    print("CAMPO "+aux[4]+aux[7]);
                    break;
                case 5:
                    print("CAMPO "+aux[5]+aux[7]);
                    break;
                case 6:
                    print("CAMPO "+aux[6]+aux[7]);
                    break;
                case 8:
                    print("Rellena algo");
                    break;
                default:
                    break;
            }
            print(Usage+"\n");
            // print(e.getMessage());
            //print("DNI no puede ser vacío\n");
        }
    }
    private static void print_menu() {
        print("\n"+text1);
        print(opcion1);
        print(opcion2);
        print(opcion3);
        print(opcion4);
        print(opcion5);
        print(opcion6);
        print("Escribe la opcion");
    }
    private static void print(String S) {
        System.out.println(S);
    }
}
