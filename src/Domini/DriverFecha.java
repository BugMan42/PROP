package Domini;

import java.util.Scanner;

/**
 * Created by usuario on 25/04/2015.
 */
public class DriverFecha {
    final static String menu = "Bienvenido al driver de fecha";
    final static String opcion1 = "1 Fecha(String data)";
    final static String opcion2 = "2 valido(String data)";
    final static String opcion3 = "3 ConsultarFecha()";
    final static String opcion4 = "4 ToString()";
    final static String opcion5 = "5 equals(Fecha f)";
    final static String msg = "Introduzca un numero del 1 al 5. 6 para salir";
    final static String fin = "Gracias por usar este driver. THE END";

    private static Fecha f;

    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        System.out.println(menu);
        do {
            try {
                ImprimirMenu();
                Proceso(entrada);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while (entrada.hasNext());
    }

    public static void Proceso(Scanner entrada) throws Exception {
        String s = entrada.nextLine();
        String aux[] = s.split("\\s");
        if (s.length() == 0) throw new ArgumentosInsuficientes();
        switch (Integer.parseInt(aux[0])) {
            case 1:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (aux.length > 2) throw new DemasiadosArgumentos();
                f = new Fecha(aux[1]);
                break;
            case 2:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (aux.length > 2) throw new DemasiadosArgumentos();
                if (Fecha.valido(aux[1])) System.out.println("La fecha es valida");
                else System.out.println("La fecha no es valida");
                break;
            case 3:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                if (f != null) System.out.println(f.ConsultarFecha());
                else throw new Exception("La fecha no ha sido creada");
                break;
            case 4:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                if (f != null) System.out.println(f.ToString());
                else throw new Exception("La fecha no ha sido creada");
                break;
            case 5:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (aux.length > 2) throw new DemasiadosArgumentos();
                Fecha f1 = new Fecha("1/1/2000");
                if (f != null) {
                    if (f.equals(f1)) System.out.println("La fecha es igual a la fecha 1/1/2000");
                    else System.out.println("La fecha es igual a la fecha 1/1/2000");
                }
                else throw new Exception("La fecha no ha sido creada");
                break;
            case 6:
                System.out.println(fin);
                System.exit(0);
                break;
            default:
                System.out.println(msg);
                break;
        }
    }

    private static void ImprimirMenu() {
        System.out.println(opcion1);
        System.out.println(opcion2);
        System.out.println(opcion3);
        System.out.println(opcion4);
        System.out.println(opcion5);
        System.out.println(msg);
    }
}