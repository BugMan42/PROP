package Domini;

import java.util.Scanner;


public class DriverTST {

    private static TST<Integer> tst;

    public static class ArgumentosInsuficientes extends Exception {
        public ArgumentosInsuficientes() {
            super("Argumentos Insuficientes");
        }
    }

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
        print("13 prefijo(String prefix):");
        print("14 clavesPrefijoConStop(String prefix, char stop, int threshold):");
        print("15 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        tst = new TST<Integer>();
        Scanner userInput = new Scanner(System.in);
        boolean imprimir = true;
        if (args.length > 0) {
            if (args[0].equals("0")) imprimir = false;
        }
        if (imprimir) PresentaMenu();
        do {
            //PresentaMenu();
            try {
                ProcesarLinea(LeerLinea(userInput),imprimir);
            } catch (Exception a) {
                print(a.getMessage());
            }
            if (imprimir) PresentaMenu();
        } while(userInput.hasNextLine());
    }

    private static void ProcesarLinea(String str, boolean imprimir) throws Exception {
        if (!imprimir) print("E: "+str);
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new ArgumentosInsuficientes();
        switch (Integer.parseInt(aux[0])) {
            case 0:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                tst.insertar(aux[1],Integer.parseInt(aux[2]));
                break;
            case 1:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print("S: "+tst.obtener(aux[1]));
                break;
            case 2:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print("S: "+tst.existe(aux[1]));
                break;
            case 3:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                tst.borrar(aux[1]);
                break;
            case 4:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                tst.modificar(aux[1], Integer.parseInt(aux[2]));
                break;
            case 5:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                tst.modificar(aux[1], aux[2]);
                break;
            case 6:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                tst.modificar(aux[1], aux[2],Integer.parseInt(aux[3]));
                break;
            case 7:
                print("S: "+tst.toString());
                break;
            case 8:
                print("S: "+tst.consultarClaves().toString());
                break;
            case 9:
                print("S: "+tst.consultarObjetos().toString());
                break;
            case 10:
                print("S: "+tst.esVacio());
                break;
            case 11:
                tst.vaciar();
                break;
            case 12:
                print("S: "+tst.size());
                break;
            case 13:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(tst.prefijo(aux[1]).toString());
                break;
            case 14:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                print(tst.clavesPrefijoConStop(aux[1],aux[2].charAt(0),Integer.parseInt(aux[3])).toString());
                break;
            case 15:
                System.exit(0);
            default:
                print("Fuera de rango");
                break;
        }
    }
}
