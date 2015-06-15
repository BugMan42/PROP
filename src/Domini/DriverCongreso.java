package Domini;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bug on 23/03/15.
 */
public class DriverCongreso {
    private static Scanner user_input = new Scanner(System.in);
    private static Congreso C;

    public static class ArgumentosInsuficientes extends Exception {
        public ArgumentosInsuficientes() {
            super("Argumentos Insuficientes");
        }
    }

    private static void print(String str) {
        System.out.println(str);
    }

    private static void PresentaMenu() {
        print("\nDRIVER CONGRESO DIPUTADOS");
        print("0 Congreso()");
        print("1 agregarCongresista(Dni dni, String nombre, String apellido," +
                " int edad, String ciudad, String estado, String partido)");
        print("2 agregarCongresistaRandom()");
        print("3 eliminarCongresista(Dni dni)");
        print("4 eliminarCongreso()");
        print("5 modCongresista(Dni dni,Dni dniNuevo ," +
                " String nombre, String apellido,int edad," +
                " String ciudad, String estado, String partido) ");
        print("6 consultarCongresista(Dni dni):Congresista ");
        print("7 copiaConsultarCongresista(Dni dni)");
        print("8 size():int");
        print("9 esVacio():boolean");
        print("10 obtenerCongreso():List<Congresista>");
        print("11 contieneCongresista(Dni dni):boolean");
        print("12 toString():String ");
        print("13 obtenerListaID():ArrayList<String>");
        print("14 obtCongresoDni(int bloq, int tam): Arraylist<Congresista> ");
        print("15 obtCongresoNombre(int bloq, int tam): Arraylist<Congresista> ");
        print("16 obtCongresoApellido(int bloq, int tam): Arraylist<Congresista> ");
        print("17 obtCongresoEdad(int bloq, int tam): Arraylist<Congresista> ");
        print("18 obtCongresoCiudad(int bloq, int tam): Arraylist<Congresista> ");
        print("19 obtCongresoEstado(int bloq, int tam): Arraylist<Congresista> ");
        print("20 obtCongresoPartido(int bloq, int tam): Arraylist<Congresista> ");
        print("21 searchPrefixDni(String prefix): List<Congresista> ");
        print("22 searchPrefixNombre(String prefix): List<Congresista> ");
        print("23 searchPrefixApellido(String prefix): List<Congresista> ");
        print("24 searchPrefixEdad(String prefix): List<Congresista> ");
        print("25 searchPrefixCiudad(String prefix): List<Congresista> ");
        print("26 searchPrefixEstado(String prefix): List<Congresista> ");
        print("27 searchPrefixPartido(String prefix): List<Congresista> ");
        print("28 searchPrefixNameDni(String prefix, char stop, int fin):List<String>");
        print("29 searchPrefixNameNombre(String prefix, char stop, int fin):List<String>");
        print("30 searchPrefixNameApellido(String prefix, char stop, int fin):List<String>");
        print("31 searchPrefixNameEdad(String prefix, char stop, int fin):List<String>");
        print("32 searchPrefixNameCiudad(String prefix, char stop, int fin):List<String>");
        print("33 searchPrefixNameEstado(String prefix, char stop, int fin):List<String>");
        print("34 searchPrefixNamePartido(String prefix, char stop, int fin):List<String>");
        print("35 Salir");
        print("Escribe la opcion");
    }

    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }

    public static void main(String[] args) throws Exception {
        C = new Congreso();
        Scanner userInput = new Scanner(System.in);
        boolean imprimir = true;
        if (args.length > 0) {
            if (args[0].equals("0")) imprimir = false;
        }
        if (imprimir) PresentaMenu();
        do {
            //PresentaMenu();
            try {
                ProcesarLinea(LeerLinea(userInput), imprimir);
            } catch (Exception a) {
                print(a.getMessage());
            }
            if (imprimir) PresentaMenu();
        } while (userInput.hasNextLine());
    }

    private static void ProcesarLinea(String str, boolean imprimir) throws Exception {
        if (!imprimir) print("> " + str);
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new ArgumentosInsuficientes();
        if (Integer.parseInt(aux[0]) != 20) System.out.print("S: ");
        switch (Integer.parseInt(aux[0])) {
            case 0:
                C = new Congreso();
                break;
            case 1:
                if (aux.length < 8) throw new ArgumentosInsuficientes();
                Congresista con = new Congresista(new Dni(aux[1]), aux[2], aux[3], Integer.parseInt(aux[4]),
                        aux[5], aux[6], aux[7]);
                C.agregarCongresista(con);
                break;
            case 2:
                C.agregarCongresistaRandom();
                break;
            case 3:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                Congresista aux1 = C.consultarCongresista(new Dni(aux[1]));
                C.eliminarCongresista(aux1);
                break;
            case 4:
                C.eliminarCongreso();
                break;
            case 5:
                if (aux.length < 9) throw new ArgumentosInsuficientes();
                C.modCongresista(new Dni(aux[1]), new Dni(aux[2]), aux[3],
                        aux[4], Integer.parseInt(aux[5]), aux[6], aux[7], aux[8]);
                break;
            case 6:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.consultarCongresista(new Dni(aux[1])).toString());
                break;
            case 7:
                print("test copia");
                C.eliminarCongreso();
                C.agregarCongresista(new Congresista(new Dni("20901724z"), "James", "Stuart", 12, "BCN", "BCN", "Podemos"));
                Congresista a = C.copiaConsultarCongresista(new Dni("20901724z"));
                print(a.toString());
                print(C.consultarCongresista(new Dni("20901724z")) + "");
                break;
            case 8:
                print(C.size() + "");
                break;
            case 9:
                print(C.esVacio() + "");
                break;
            case 10:
                print(C.obtenerCongreso() + "");
                break;
            case 11:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.contieneCongresista(new Dni(aux[1])) + "");
                break;
            case 12:
                print(C.toString());
                break;
            case 13:
                print(C.obtenerListaID() + "");
                break;
            case 14:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print(C.obtCongresoDni(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                break;
            case 15:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print(C.obtCongresoNombre(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                break;
            case 16:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print(C.obtCongresoApellido(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                break;
            case 17:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print(C.obtCongresoEdad(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                break;
            case 18:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print(C.obtCongresoCiudad(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                break;
            case 19:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print(C.obtCongresoEstado(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                break;
            case 20:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print(C.obtCongresoPartido(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                break;
            case 21:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.searchPrefixDni(aux[1]));
                break;
            case 22:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.searchPrefixNombre(aux[1]));
                break;
            case 23:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.searchPrefixApellido(aux[1]));
                break;
            case 24:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.searchPrefixEdad(aux[1]));
                break;
            case 25:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.searchPrefixCiudad(aux[1]));
                break;
            case 26:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.searchPrefixEstado(aux[1]));
                break;
            case 27:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.searchPrefixPartido(aux[1]));
                break;
            case 28:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                prints(C.searchPrefixNameDni(aux[1], aux[2].charAt(0), Integer.parseInt(aux[3])));
                break;
            case 29:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                prints(C.searchPrefixNameNombre(aux[1], aux[2].charAt(0), Integer.parseInt(aux[3])));
                break;
            case 30:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                prints(C.searchPrefixNameApellido(aux[1], aux[2].charAt(0), Integer.parseInt(aux[3])));
                break;
            case 31:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                prints(C.searchPrefixNameEdad(aux[1], aux[2].charAt(0), Integer.parseInt(aux[3])));
                break;
            case 32:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                prints(C.searchPrefixNameCiudad(aux[1], aux[2].charAt(0), Integer.parseInt(aux[3])));
                break;
            case 33:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                prints(C.searchPrefixNameEstado(aux[1], aux[2].charAt(0), Integer.parseInt(aux[3])));
                break;
            case 34:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                prints(C.searchPrefixNamePartido(aux[1], aux[2].charAt(0), Integer.parseInt(aux[3])));
                break;
            case 35:
                System.exit(0);
                break;
            default:
                print("Fuera de rango");
                break;
        }
    }

    private static void print(ArrayList<Congresista> aux) {
        for (int i = 0; i < aux.size(); ++i) {
            print(aux.get(i).toString());
        }
    }
    private static void print(List<Congresista> aux) {
        for (int i = 0; i < aux.size(); ++i) {
            print(aux.get(i).toString());
        }
    }
    private static void prints(List<String> aux) {
        for (int i = 0; i < aux.size(); ++i) {
            print(aux.get(i));
        }
    }
}
