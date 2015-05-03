package Domini;

import java.util.Scanner;

/**
 * Created by usuario on 03/05/2015.
 */
public class DriverCjtEvento {
    final static String menu = "Bienvenido al driver de evento";
    final static String opcion1 = "1 CjtEvento()";
    final static String opcion2 = "2 ModNombre(String name)";
    final static String opcion3 = "3 ModFecha (String date)";
    final static String opcion4 = "4 ModImportancia(int importance)";
    final static String opcion5 = "5 obt_nombre()";
    final static String opcion6 = "6 obt_fecha()";
    final static String opcion7 = "7 obtFecha()";
    final static String opcion8 = "8 ID()";
    final static String opcion9 = "9 toString()";
    final static String opcion10 = "10 obt_importancia()";
    final static String msg = "Introduzca un numero del 1 al 10. 11 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    private static Evento1 e;

    public static void main(String[] args) throws Exception {
        ImprimirMenu();
        Scanner entrada = new Scanner(System.in);
        do {
            try {
                Proceso(entrada);
            }
            catch (Exception e) {
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
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                if (aux.length > 4) throw new DemasiadosArgumentos();
                e = new Evento1(aux[1], aux[2], Integer.parseInt(aux[3]));
                break;
            case 2:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (aux.length > 2) throw new DemasiadosArgumentos();
                if (e != null) {
                    e.ModNombre(aux[1]);
                } else throw new Exception("Evento no existe");
                break;
            case 3:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (aux.length > 2) throw new DemasiadosArgumentos();
                if (e != null) {
                    e.ModFecha(aux[1]);
                } else throw new Exception("Evento no existe");
                break;
            case 4:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (aux.length > 2) throw new DemasiadosArgumentos();
                if (e != null) {
                    e.ModImportancia(Integer.parseInt(aux[1]));
                } else throw new Exception("Evento no existe");
                break;
            case 5:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                System.out.println(e.obt_nombre());
                break;
            case 6:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                System.out.println(e.obt_fecha());
                break;
            case 7:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                System.out.println(e.obtFecha());
                break;
            case 8:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                System.out.println(e.ID());
                break;
            case 9:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                System.out.println(e.toString());
                break;
            case 10:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                System.out.println(Integer.toString(e.obt_importancia()));
                break;
            case 11:
                System.exit(0);
                break;
            default:
                System.out.println(msg);
                break;
        }
    }

    private static void ImprimirMenu() {
        System.out.println(menu);
        System.out.println(opcion1);
        System.out.println(opcion2);
        System.out.println(opcion3);
        System.out.println(opcion4);
        System.out.println(opcion5);
        System.out.println(opcion6);
        System.out.println(opcion7);
        System.out.println(opcion8);
        System.out.println(opcion9);
        System.out.println(opcion10);
        System.out.println(msg);
    }
}
