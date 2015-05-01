package Domini;

import java.text.ParseException;
import java.util.Scanner;

public class DriverCongresista {
    private static class CongresistaNoInicializado extends Exception {
        CongresistaNoInicializado() {
            super("CongresistaNoInicializado");
        }
    }
    final static String Usage = "###USAGE###\n" +
                                "###LOS CAMPOS NO PUEDEN ESTAR VACIOS\n" +
                                "###EDAD TIENE QUE SER >= 18 AÑOS##";
    final static String fin = "\"FIN DEL DRIVER\"";
    final static String inc = "\nOpción incorrecta, vuelve a introducir un número.\n";
    private static Congresista C;

    public static void main(String[] args) throws ParseException {
        Scanner userInput = new Scanner(System.in);
        PresentaMenu();
        do {
            try {
                ProcesarLinea(LeerLinea(userInput));
            }
            catch (Exception a) {
                print(a.getMessage());
            }
            PresentaMenu();
        } while (userInput.hasNextLine());
    }

    private static void PresentaMenu() {
        print("\nDRIVER DE CONGRESISTA");

        print("0 Congresista (String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido)");
        print("1 modDni(String dni)");
        print("2 modNombre(String nombre)");
        print("3 modApellido(String apellido)");
        print("4 modEdad(int edad) ");
        print("5 modCiudad(String ciudad)");
        print("6 modEstado(String estado) ");
        print("7 modPartido(String partido)");
        //print("9 mod(String nombre, String apellido, int edad, String ciudad, String estado, String partido)");
        print("8 Consultar");
        print("9 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    //TODO error si no argumentos o null
    private static void ProcesarLinea(String str) throws Exception {
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new InsuficientesArgumentos();
        switch (Integer.parseInt(aux[0])) {
            case 0:
                if (aux.length < 8) throw new InsuficientesArgumentos();
                if (aux.length == 8) {
                    Dni a = new Dni(aux[1]);
                    C = new Congresista(a, aux[2], aux[3], Integer.parseInt(aux[4]), aux[5], aux[6], aux[7]);
                }
                else throw new DemasiadosArgumentos();
                break;
            case 1:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                if (C != null) {
                    Dni a = new  Dni(aux[1]);
                    C.modDni(a);
                }
                else throw new CongresistaNoInicializado();
                break;
            case 2:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                if (C != null) {
                    C.modNombre(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case 3:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                if (C != null) {
                    C.modApellido(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case 4:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                if (C != null) {
                    C.modEdad(Integer.parseInt(aux[1]));
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case 5:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                if (C != null) {
                    C.modCiudad(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case 6:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                if (C != null) {
                    C.modEstado(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case 7:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                if (C != null) {
                    C.modPartido(aux[1]);
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case 8:
                if (C != null) {
                    print(C.toString());
                }
                else throw new IllegalArgumentException("Congresista no ini");
                break;
            case 9:
                System.exit(0);
                break;
            default:
                print("Fuera de rango");
                break;
        }
    }
    private static void print(String S) {
        System.out.println(S);
    }
}




