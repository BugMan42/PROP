package Domini;

import java.util.Scanner;

public class DriverNoOficial {
    final static String menu = "Bienvenido/a al driver de ActoNoOficial";
    final static String opcion1 = "1 NoOficial (String nombre, String fecha, int importancia)";
    final static String opcion2 = "2 toString()";
    final static String opcion3 = "3 toStringF()";
    final static String opcion4 = "4 toStringI()";
    final static String opcion5 = "5 tipo()";
    final static String msg = "Introduzca un numero del 1 al 5. 6 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    final static String dem = "Demasiados Argumentos";
    final static String ins = "Argumentos Insuficientes";
    final static String noExiste = "El acto NoOficial no ha sido creado";

    private static NoOficial n = null;

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
                if (aux.length < 4) throw new Exception(ins);
                if (aux.length > 4) throw new Exception(dem);
                n = new NoOficial(aux[1], new Fecha(aux[2]), Integer.parseInt(aux[3]));
                break;
            case 2:
                if (aux.length > 1) throw new Exception(dem);
                if (n != null) System.out.println(n.toString());
                else throw new Exception(noExiste);
                break;
            case 3:
                if (aux.length > 1) throw new Exception(dem);
                if (n != null) System.out.println(n.toStringF());
                else throw new Exception(noExiste);
                break;
            case 4:
                if (aux.length > 1) throw new Exception(dem);
                if (n != null) System.out.println(n.toStringI());
                else throw new Exception(noExiste);
                break;
            case 5:
                if (aux.length > 1) throw new Exception(dem);
                if (n != null) System.out.println(n.tipo());
                else throw new Exception(noExiste);
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
