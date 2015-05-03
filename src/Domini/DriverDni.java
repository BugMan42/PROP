package Domini;

import java.util.Scanner;

public class DriverDni {

    private static class DNINoInicializado extends Exception {
        DNINoInicializado() {
            super("DniNoInicializado");
        }
    }
    private static Dni DNI;

    private static void print(String str) {
        System.out.println(str);
    }

    private static void PresentaMenu() {
        print("\nDRIVER DNI");
        print("0 Dni(String d)");
        print("1 modDni(String d)");
        print("2 valido(String d):boolean");
        print("3 equals(Dni D):boolean");
        print("4 toString():String");
        print("5 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        boolean imprimir = false;
        if (args.length > 0) {
            if (args[0].equals("1")) imprimir = true;
        }
        Scanner userInput = new Scanner(System.in);
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
        if (!imprimir) print("> "+str);
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new InsuficientesArgumentos();
        switch (Integer.parseInt(aux[0])) {
            case 0:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                DNI = new Dni(aux[1]);
                break;
            case 1:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                if (DNI != null) {
                    DNI.modDni(aux[1]);
                } else throw new DNINoInicializado();
                break;
            case 2:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print("" + Dni.valido(aux[1]));
                break;
            case 3:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                if (DNI != null) {
                    print("" + DNI.equals(new Dni(aux[1])));
                } else throw new DNINoInicializado();
                break;
            case 4:
                if (DNI != null) {
                    print(DNI.toString());
                } else throw new DNINoInicializado();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                print("Fuera de rango");
                break;
        }
    }

}
