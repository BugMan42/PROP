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

    private static void PresentaMenu() {
        print("\nDRIVER CONGRESO DIPUTADOS");
        print("0 Congreso()");
        print("1 agregarCongresista(Dni dni, String nombre, String apellido," +
                " int edad, String ciudad, String estado, String partido)");
        print("2 eliminarCongresista(Dni dni)");
        print("3 eliminarCongreso()");
        print("4 modDniCongresista(Dni dni, Dni dniNuevo)");
        print("5 modNombreCongresista(Dni dni,String nombre)");
        print("6 modApellidoCongresista(Dni dni,String apellido)");
        print("7 modEdadCongresista(Dni dni,int edad)");
        print("8 modCiudadCongresista(Dni dni,String ciudad)");
        print("9 modEstadoCongresista(Dni dni,String estado)");
        print("10 modPartidoCongresista(Dni dni,String partido)");
        print("11 modCongresista(Dni dni,Dni dniNuevo ," +
                " String nombre, String apellido,int edad," +
                " String ciudad, String estado, String partido) ");
        print("12 contieneCongresista(Dni dni):boolean");
        print("13 esVacio():boolean");
        print("14 size():int");
        print("15 consultarCongresista(Dni dni):Congresista ");
        print("16 copiaConsultarCongresista(Dni dni)");
        print("17 obtenerListaID():ArrayList<String>");
        print("18 obtenerCongreso():List<Congresista>");
        print("19 toString():String ");
        print("20 Salir");
        print("21 agregarCongresistaRandom()");
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
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                C.eliminarCongresista(new Dni(aux[1]));
                break;
            case 3:
                C.eliminarCongreso();
                break;
            case 4:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                C.modDniCongresista(new Dni(aux[1]), new Dni(aux[2]));
                break;
            case 5:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                C.modNombreCongresista(new Dni(aux[1]), aux[2]);
                break;
            case 6:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                C.modApellidoCongresista(new Dni(aux[1]), aux[2]);
                break;
            case 7:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                C.modEdadCongresista(new Dni(aux[1]), Integer.parseInt(aux[2]));
                break;
            case 8:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                C.modCiudadCongresista(new Dni(aux[1]), aux[2]);
                break;
            case 9:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                C.modEstadoCongresista(new Dni(aux[1]), aux[2]);
                break;
            case 10:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                C.modPartidoCongresista(new Dni(aux[1]), aux[2]);
                break;
            case 11:
                if (aux.length < 9) throw new ArgumentosInsuficientes();
                C.modCongresista(new Dni(aux[1]), new Dni(aux[2]), aux[3],
                        aux[4], Integer.parseInt(aux[5]), aux[6], aux[7], aux[8]);
                break;
            case 12:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.contieneCongresista(new Dni(aux[1])) + "");
                break;
            case 13:
                print(C.esVacio()+"");
                break;
            case 14:
                print(C.size()+"");
                break;
            case 15:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(C.consultarCongresista(new Dni(aux[1])).toString());
                break;
            case 16:
                print("test copia");
                C.eliminarCongreso();
                C.agregarCongresista(new Congresista(new Dni("20901724z"), "James", "Stuart", 12, "BCN", "BCN", "Podemos"));
                Congresista a = C.copiaConsultarCongresista(new Dni("20901724z"));
                C.modApellidoCongresista(new Dni("20901724z"),"PERE");
                print(a.toString());
                print(C.consultarCongresista(new Dni("20901724z"))+"");
                break;
            case 17:
                print(C.obtenerListaID()+"");
                break;
            case 18:
                print(C.obtenerCongreso()+"");
                break;
            case 19:
                print(C.toString());
                break;

            case 20:
                System.exit(0);
                break;
            case 21:
                C.agregarCongresistaRandom();
                break;
            default:
                print("Fuera de rango");
                break;
        }
    }
}
