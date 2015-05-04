package Domini;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by usuario on 03/05/2015.
 */
public class DriverSalida {
    final static String menu = "Bienvenido al driver de salida";
    final static String opcion1 = "1 Salida()";
    final static String opcion2 = "2 agregarMensaje(String s)";
    final static String opcion3 = "3 mostrarMensaje()";
    final static String opcion4 = "4 mostrarHistorial()";
    final static String opcion5 = "5 agregarComunidad(Set<Integer> s)";
    final static String opcion6 = "6 comunidad()";
    final static String opcion7 = "7 comunidad_at(int i)";
    final static String msg = "Introduzca un numero del 1 al 7. 8 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    private static Salida sa;

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
        while (entrada.hasNext());
    }

    public static void Proceso(Scanner entrada) throws Exception {
        String s = entrada.nextLine();
        String aux[] = s.split("\\s");
        if (s.length() == 0) throw new ArgumentosInsuficientes();
        switch (Integer.parseInt(aux[0])) {
            case 1:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                sa = new Salida();
                break;
            case 2:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (aux.length > 2) throw new DemasiadosArgumentos();
                if (sa != null) sa.agregarMensaje(aux[1]);
                else throw new Exception("Salida no existe");
                break;
            case 3:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                if (sa != null) System.out.println(sa.mostrarMensaje());
                else throw new Exception("Salida no existe");
                break;
            case 4:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                if (sa != null) System.out.println(sa.mostrarHistorial());
                else throw new Exception("Salida no existe");
                break;
            case 5:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                if (sa != null) {
                    Set<Integer> conj = new HashSet<Integer>();
                    sa.agregarComunidad(conj);
                }
                else throw new Exception("Salida no existe");
                break;
            case 6:
                if (aux.length > 1) throw new DemasiadosArgumentos();
                if (sa != null) System.out.println(sa.comunidad());
                else throw new Exception("Salida no existe");
                break;
            case 7:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                if (aux.length > 2) throw new DemasiadosArgumentos();
                if (sa != null) System.out.println(sa.comunidad_at(Integer.parseInt(aux[1])));
                else throw new Exception("Salida no existe");
                break;
            case 8:
                System.out.println(fin);
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
        System.out.println(msg);
    }
}
