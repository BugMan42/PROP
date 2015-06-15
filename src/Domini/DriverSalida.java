package Domini;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class DriverSalida {
    final static String menu = "Bienvenido al driver de salida";
    final static String opcion1 = "1 Salida()";
    final static String opcion2 = "2 eliminarSalida()";
    final static String opcion3 = "3 agregarMensaje(String s)";
    final static String opcion4 = "4 mostrarMensaje()";
    final static String opcion5 = "5 mostrarHistorial()";
    final static String opcion6 = "6 agregarComunidad(Set<Integer> s)";
    final static String opcion7 = "7 comunidad()";
    final static String opcion8 = "8 comunidad_at(int i)";
    final static String opcion9 = "9 numComunidades()";
    final static String msg = "Introduzca un numero del 1 al 9. 10 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    final static String dem = "Demasiados Argumentos";
    final static String ins = "Argumentos Insuficientes";
    final static String noExiste = "La salida no ha sido creada";

    private static Salida sa = null;

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
        if (s.length() == 0) throw new Exception(ins);
        switch (Integer.parseInt(aux[0])) {
            case 1:
                if (aux.length > 1) throw new Exception(dem);
                sa = new Salida();
                break;
            case 2:
                if (aux.length > 1) throw new Exception(dem);
                if (sa != null) sa.eliminarSalida();
                else throw new Exception(noExiste);
                break;
            case 3:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                if (sa != null) sa.agregarMensaje(aux[1]);
                else throw new Exception(noExiste);
                break;
            case 4:
                if (aux.length > 1) throw new Exception(dem);
                if (sa != null) System.out.println(sa.mostrarMensaje());
                else throw new Exception(noExiste);
                break;
            case 5:
                if (aux.length > 1) throw new Exception(dem);
                if (sa != null) System.out.println(sa.mostrarHistorial());
                else throw new Exception(noExiste);
                break;
            case 6:
                if (aux.length > 1) throw new Exception(dem);
                if (sa != null) {
                    Set<Integer> conj = new HashSet<Integer>();
                    sa.agregarComunidad(conj);
                }
                else throw new Exception(noExiste);
                break;
            case 7:
                if (aux.length > 1) throw new Exception(dem);
                if (sa != null) System.out.println(sa.comunidad());
                else throw new Exception(noExiste);
                break;
            case 8:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                if (sa != null) System.out.println(sa.comunidad_at(Integer.parseInt(aux[1])));
                else throw new Exception(noExiste);
                break;
            case 9:
                if (aux.length > 1) throw new Exception(dem);
                if (sa != null) System.out.println(sa.numComunidades());
                else throw new Exception(noExiste);
                break;
            case 10:
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
        System.out.println(opcion8);
        System.out.println(opcion9);
        System.out.println(msg);
    }
}
