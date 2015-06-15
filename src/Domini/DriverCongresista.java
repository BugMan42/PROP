package Domini;

import java.text.ParseException;
import java.util.Scanner;

public class DriverCongresista {

    private static class CongresistaNoInicializado extends Exception {
        CongresistaNoInicializado() {
            super("CongresistaNoInicializado");
        }
    }
    public static class ArgumentosInsuficientes extends Exception {
        public ArgumentosInsuficientes() {
            super("Argumentos Insuficientes");
        }
    }

    private static Congresista C;

    public static void main(String[] args) throws ParseException {
        Scanner userInput = new Scanner(System.in);
        boolean imprimir = true;
        if (args.length > 0) {
            if (args[0].equals("0")) imprimir = false;
        }
        if (imprimir) PresentaMenu();
        do {
            try {
                ProcesarLinea(LeerLinea(userInput),imprimir);
            }
            catch (Exception a) {
                print(a.getMessage());
            }
            if (imprimir) PresentaMenu();
        } while (userInput.hasNextLine());
    }

    private static void PresentaMenu() {
        print("\nDRIVER DE CONGRESISTA");

        print("0 Congresista (String dni, String nombre,\n" +
                "String apellido, int edad, String ciudad,\n " +
                "String estado, String partido)");
        print("1 modDni(String dni)");
        print("2 modNombre(String nombre)");
        print("3 modApellido(String apellido)");
        print("4 modEdad(int edad) ");
        print("5 modCiudad(String ciudad)");
        print("6 modEstado(String estado) ");
        print("7 modPartido(String partido)");
        print("8 mod(Dni dni,String nombre, String apellido,\n" +
                " int edad, String ciudad, String estado, String partido)");
        print("9 mod(String nombre, String apellido,\n" +
                " int edad, String ciudad, String estado, String partido)");
        print("10 obtDni():Dni");
        print("11 obtNombre():String");
        print("12 obtApellido():String");
        print("13 obtEdad():int");
        print("14 obtCiudad():String");
        print("15 obtEstado():String");
        print("16 obtPartido():String");
        print("17 ID():String");
        print("18 toString:String");
        print("19 toStringByName:String");
        print("20 toStringBySurname:String");
        print("21 toStringByAge:String");
        print("22 toStringByCity:String");
        print("23 toStringByState:String");
        print("24 toStringByParty:String");
        print("25 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }

    private static void ProcesarLinea(String str, boolean imprimir) throws Exception {
        if (!imprimir) print("E: "+str);
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new ArgumentosInsuficientes();
        if (Integer.parseInt(aux[0]) != 18) System.out.print("S: ");
        switch (Integer.parseInt(aux[0])) {
            case 0:
                if (aux.length < 8) throw new ArgumentosInsuficientes();
                Dni a = new Dni(aux[1]);
                C = new Congresista(a, aux[2], aux[3], Integer.parseInt(aux[4]), aux[5], aux[6], aux[7]);
                break;
            case 1:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (C != null) {
                    Dni ab = new Dni(aux[1]);
                    C.modDni(ab);
                }
                else throw new CongresistaNoInicializado();
                break;
            case 2:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (C != null) {
                    C.modNombre(aux[1]);
                }
                else throw new CongresistaNoInicializado();
                break;
            case 3:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (C != null) {
                    C.modApellido(aux[1]);
                }
                else throw new CongresistaNoInicializado();
                break;
            case 4:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (C != null) {
                    C.modEdad(Integer.parseInt(aux[1]));
                }
                else throw new CongresistaNoInicializado();
                break;
            case 5:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (C != null) {
                    C.modCiudad(aux[1]);
                }
                else throw new CongresistaNoInicializado();
                break;
            case 6:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (C != null) {
                    C.modEstado(aux[1]);
                }
                else throw new CongresistaNoInicializado();
                break;
            case 7:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (C != null) {
                    C.modPartido(aux[1]);
                }
                else throw new CongresistaNoInicializado();
                break;
            case 8:
                if (aux.length < 8) throw new ArgumentosInsuficientes();
                if (C != null) {
                    Dni b = new Dni(aux[1]);
                    C.mod(b, aux[2], aux[3], Integer.parseInt(aux[4]), aux[5], aux[6], aux[7]);
                }
                else throw new CongresistaNoInicializado();
                break;
            case 9:
                if (aux.length < 8) throw new ArgumentosInsuficientes();
                if (C != null) {
                    C.mod(aux[1], aux[2], Integer.parseInt(aux[3]), aux[4], aux[5], aux[6]);
                }
                else throw new CongresistaNoInicializado();
                break;
            case 10:
                if (C != null) {
                    print(C.obtDni() + "");
                }
                else throw new CongresistaNoInicializado();
                break;
            case 11:
                if (C != null) {
                    print(C.obtNombre());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 12:
                if (C != null) {
                    print(C.obtApellido());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 13:
                if (C != null) {
                    print(C.obtEdad()+"");
                }
                else throw new CongresistaNoInicializado();
                break;
            case 14:
                if (C != null) {
                    print(C.obtCiudad());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 15:
                if (C != null) {
                    print(C.obtEstado());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 16:
                if (C != null) {
                    print(C.obtPartido());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 17:
                if (C != null) {
                    print(C.obtID());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 18:
                if (C != null) {
                    print(C.toString());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 19:
                if (C != null) {
                    print(C.toStringByName());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 20:
                if (C != null) {
                    print(C.toStringBySurname());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 21:
                if (C != null) {
                    print(C.toStringByAge());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 22:
                if (C != null) {
                    print(C.toStringByCity());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 23:
                if (C != null) {
                    print(C.toStringByState());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 24:
                if (C != null) {
                    print(C.toStringByParty());
                }
                else throw new CongresistaNoInicializado();
                break;
            case 25:
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




