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
        print("5 modificar(String oldKey, String newKey)");
        print("6 modificar(String oldKey, String newKey, X x)");
        print("7 String toString()");
        print("8 ArrayList<String> consultarClaves()");
        print("9 ArrayList<X> consultarObjetos()");
        print("10 Boolean esVacio()");
        print("11 vaciar()");
        print("12 int size()");
        print("13 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        tst = new TST<Integer>();
        Scanner userInput = new Scanner(System.in);
        boolean seguir = true;
        PresentaMenu();
        do {
            //PresentaMenu();
            try {
                ProcesarLinea(LeerLinea(userInput));
            } catch (Exception a) {
                print(a.getMessage());
            }
            PresentaMenu();
        } while(userInput.hasNextLine());
    }

    private static void ProcesarLinea(String str) throws Exception {
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new IllegalArgumentException("Pocs arguments pre");
        switch (Integer.parseInt(aux[0])) {
            case 0:
                if (aux.length < 3) throw new IllegalArgumentException("Pocos argumentos");
                tst.insertar(aux[1],Integer.parseInt(aux[2]));
                break;
            case 1:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                print(""+tst.obtener(aux[1]));
                break;
            case 2:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                print(""+tst.existe(aux[1]));
                break;
            case 3:
                if (aux.length < 2) throw new IllegalArgumentException("Pocos argumentos");
                tst.borrar(aux[1]);
                break;
            case 4:
                if (aux.length < 3) throw new IllegalArgumentException("Pocos argumentos");
                tst.modificar(aux[1],Integer.parseInt(aux[2]));
                break;
            case 5:
                if (aux.length < 3) throw new IllegalArgumentException("Pocos argumentos");
                tst.modificar(aux[1], aux[2]);
                break;
            case 6:
                if (aux.length < 4) throw new IllegalArgumentException("Pocos argumentos");
                tst.modificar(aux[1], aux[2],Integer.parseInt(aux[3]));
            case 7:
                print(tst.toString());
                break;
            case 8:
                print(tst.consultarClaves().toString());
                break;
            case 9:
                print(tst.consultarObjetos().toString());
                break;
            case 10:
                print(tst.esVacio()+"");
                break;
            case 11:
                tst.vaciar();
                break;
            case 12:
                print(tst.size()+"");
                break;
            case 13:
                System.exit(0);
            default:
                print("Fuera de rango");
                break;
        }
    }
}

