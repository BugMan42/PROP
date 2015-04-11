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
    final static String opcion6 = "***6.-SALIR";
    final static String Usage = "###USAGE###\n" +
                                "###LOS CAMPOS NO PUEDEN ESTAR VACIOS\n" +
                                "###EDAD TIENE QUE SER >= 18 AÑOS##";
    final static String[] aux = new String[]{"Dni", "Nombre", "Apellido", "Edad", "Ciudad", "Estado", "Partido", " NO VALIDO"};
    final static String fin = "\"FIN DEL DRIVER\"";
    final static String inc = "\nOpción incorrecta, vuelve a introducir un número.\n";
    private static Congresista C;

    public static void main(String[] args) throws ParseException {
        Scanner user_input = new Scanner(System.in);
        boolean seguir = true;
        do {
            PresentaMenu();
            try {
                seguir = ProcesarLinea(LeerLinea(user_input));
            }catch (Exception a) {
                print(a.getMessage());
            }
        } while (seguir);
    }

    private static void PresentaMenu() {
        print("\nDRIVER DE CONGRESISTA");
        print("0 Salir");
        print("1 Congresista (String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido)");
        print("2 modDni(String dni)");
        print("3 modNombre(String nombre)");
        print("4 modApellido(String apellido)");
        print("5 modEdad(int edad) ");
        print("6 modCiudad(String ciudad)");
        print("7 modEstado(String estado) ");
        print("8 modPartido(String partido)");
        //print("9 mod(String nombre, String apellido, int edad, String ciudad, String estado, String partido)");
        print("9 Consultar");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    //TODO error si no argumentos o null
    private static boolean ProcesarLinea(String str) {
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new IllegalArgumentException("Pocs arguments pre");
        switch (str.charAt(0)) {
            case '1':
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                if (aux.length == 8) {
                    C = new Congresista(aux[1], aux[2], aux[3], Integer.parseInt(aux[4]), aux[5], aux[6], aux[7]);
                }
                else throw new IllegalArgumentException("Pocos argumentos");
                break;
            case '2':
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                if (C != null) {
                    C.modDni(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case '3':
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                if (C != null) {
                    C.modNombre(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case '4':
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                if (C != null) {
                    C.modApellido(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case '5':
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                if (C != null) {
                    C.modEdad(Integer.parseInt(aux[1]));
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case '6':
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                if (C != null) {
                    C.modCiudad(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case '7':
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                if (C != null) {
                    C.modEstado(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case '8':
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                if (C != null) {
                    C.modPartido(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case '9':
                if (C != null) {
                    print(C.toString());
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case '0':
                return false;
            default:
                //respuesta no valida Print()?
                break;
        }
        return true;
    }
    private static void print(String S) {
        System.out.println(S);
    }
}






/*
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
                //opcion = Integer.parseInt(user_input.next());
        //decide(opcion,args);
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
 */
