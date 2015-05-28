package Domini;

import java.util.Scanner;

/**
 * Created by usuario on 03/05/2015.
 */
public class DriverReunion {
    final static String menu = "Bienvenido/a al driver de reunion";
    final static String opcion1 = "1 Reunion(String name, String date, int importance)";
    final static String opcion2 = "2 toString()";
    final static String opcion3 = "3 tipo()";
    final static String msg = "Introduzca un numero del 1 al 3. 4 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    private static Reunion1 r = null;

    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        System.out.println(menu);
        ImprimirMenu();
        do {
            try {
                Proceso(entrada);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            ImprimirMenu();
        }
        while (entrada.hasNextLine());
    }

    public static void Proceso(Scanner entrada) throws Exception {
        String s = entrada.nextLine();
        String aux[] = s.split("\\s");
        if (s.length() == 0) throw new ArgumentosInsuficientes();
        switch (Integer.parseInt(aux[0])) {
            case 1:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                if (aux.length > 4) throw new DemasiadosArgumentos();
                r = new Reunion1(aux[1], new Fecha(aux[2]), Integer.parseInt(aux[3]));
                break;
            case 2:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                if (r != null) System.out.println(r.toString());
                else throw new Exception("El acto no ha sido creado");
                break;
            case 3:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                if (r != null) System.out.println(r.tipo());
                else throw new Exception("El acto no ha sido creado");
                break;
            case 4:
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
        System.out.println(msg);
    }
}
