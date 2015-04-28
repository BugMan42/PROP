package Domini;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class DriverTST {

    private static Scanner user_input = new Scanner(System.in);
    private static TST<Integer> tst;

    private static void print(String str) {
        System.out.println(str);
    }

    private static void PresentaMenu() {
        print("\nDRIVER DE TST<X>  X:Integer");
        print("0 insertar(String key, X x)");
        print("1 X obtener(String key)");
        print("2 boolean existe(String key)");
        print("3 borrar(String key)");
        print("4 modificar(String key, X x) ");
        print("5 modificar(String OldKey, String NewKey");
        print("7 String toString()");
        print("8 ArrayList<String> consultarClaves()");
        print("9 Boolean esVacio()");
        print("10 vaciar");
        print("11 int size()");
        print("12 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        Scanner userInput = new Scanner(System.in);
        boolean seguir = true;
        do {
            PresentaMenu();
            try {
                ProcesarLinea(LeerLinea(userInput));
            } catch (Exception a) {
                print(a.getMessage());
            }
        } while(userInput.hasNextLine());
    }

    //TODO error si no argumentos o null
    private static void ProcesarLinea(String str) throws Exception {
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new IllegalArgumentException("Pocs arguments pre");
        switch (Integer.parseInt(aux[0])) {
            case 1:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                if (aux.length == 8) {

                }
                else throw new IllegalArgumentException("Pocos argumentos");
                break;
            case 2:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                break;
            case 3:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                break;
            case 4:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                break;
            case 5:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                break;
            case 6:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                break;
            case 7:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                break;
            case 8:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                System.exit(0);
            default:
                //respuesta no valida Print()?
                break;
        }
    }
}

