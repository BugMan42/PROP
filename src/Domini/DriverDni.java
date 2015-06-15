package Domini;

import java.util.Scanner;

public class DriverDni {

    private static class DNINoInicializado extends Exception {
        DNINoInicializado() {
            super("DniNoInicializado");
        }
    }
    public static class ArgumentosInsuficientes extends Exception {
        public ArgumentosInsuficientes() {
            super("Argumentos Insuficientes");
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
        print("3 equals(Dni d):boolean");
        print("4 toString():String");
        print("5 Dni()");
        print("6 modDni(char l)");
        print("7 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        boolean imprimir = true;
        if (args.length > 0) {
            if (args[0].equals("0")) imprimir = false;
        }
        Scanner userInput = new Scanner(System.in);
        if (imprimir) PresentaMenu();
        do {
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
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                DNI = new Dni(aux[1]);
                break;
            case 1:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (DNI != null) {
                    DNI.modDni(aux[1]);
                } else throw new DNINoInicializado();
                break;
            case 2:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print("S: " + Dni.valido(aux[1]));
                break;
            case 3:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (DNI != null) {
                    print("S: " + DNI.equals(new Dni(aux[1])));
                } else throw new DNINoInicializado();
                break;
            case 4:
                if (DNI != null) {
                    print("S: "+DNI.toString());
                } else throw new DNINoInicializado();
                break;
            case 5:
                DNI = new Dni();
                break;
            case 6:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (DNI != null) {
                    DNI.cambiarLetra(aux[1].charAt(0));
                } else throw new DNINoInicializado();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                print("Fuera de rango");
                break;
        }
    }

}
