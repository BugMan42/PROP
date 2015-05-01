package Domini;

import java.util.Scanner;

/**
 * Created by bug on 23/03/15.
 */
public class DriverCongreso {
    private static Scanner user_input = new Scanner(System.in);
    private static Congreso C;

    private static void print(String str) {
        System.out.println(str);
    }
    /*
    private static void PresentaMenu() {
        print("\nDRIVER CONGRESO DIPUTADOS");
        print("0 Congreso()");
        print("1 agregarCongresista()");
        print("1 size():int");
        print("2 ");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        C = new Congreso();
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
        if (str.length() == 0) throw new InsuficientesArgumentos();
        switch (Integer.parseInt(aux[0])) {
            case 0:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                tst.insertar(aux[1],Integer.parseInt(aux[2]));
                break;
            case 1:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+tst.obtener(aux[1]));
                break;
            case 2:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+tst.existe(aux[1]));
                break;
            case 3:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                tst.borrar(aux[1]);
                break;
            case 4:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                tst.modificar(aux[1], Integer.parseInt(aux[2]));
                break;
            case 5:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                tst.modificar(aux[1], aux[2]);
                break;
            case 6:
                if (aux.length < 4) throw new InsuficientesArgumentos();
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
}*/
}
