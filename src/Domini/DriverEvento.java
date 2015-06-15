package Domini;

import java.util.Scanner;

public class DriverEvento {
    final static String menu = "Bienvenido al driver de evento";
    final static String opcion1 = "1 Evento(String name, Fecha date, int importance)";
    final static String opcion2 = "2 ModNombre(String name)";
    final static String opcion3 = "3 ModFecha (Fecha date)";
    final static String opcion4 = "4 ModImportancia(int importance)";
    final static String opcion5 = "5 obt_nombre()";
    final static String opcion6 = "6 obt_fecha()";
    final static String opcion7 = "7 obtFecha()";
    final static String opcion8 = "8 ID()";
    final static String opcion9 = "9 IDN()";
    final static String opcion10 = "10 IDFecha()";
    final static String opcion11 = "11 IDImp()";
    final static String opcion12 = "12 toString()";
    final static String opcion13 = "13 toStringF()";
    final static String opcion14 = "14 toStringI()";
    final static String opcion15 = "15 obt_importancia()";
    final static String opcion16 = "16 tipo()";
    final static String msg = "Introduzca un numero del 1 al 16. 17 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    final static String dem = "Demasiados Argumentos";
    final static String ins = "Argumentos Insuficientes";
    final static String noExiste = "El evento no existe";
    private static Evento1 e = null;

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
                e = new Evento1(aux[1], new Fecha(aux[2]), Integer.parseInt(aux[3]));
                break;
            case 2:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                if (e != null) e.ModNombre(aux[1]);
                else throw new Exception(noExiste);
                break;
            case 3:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                if (e != null) e.ModFecha(new Fecha(aux[1]));
                else throw new Exception(noExiste);
                break;
            case 4:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                if (e != null) e.ModImportancia(Integer.parseInt(aux[1]));
                else throw new Exception(noExiste);
                break;
            case 5:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.obt_nombre());
                else throw new Exception(noExiste);
                break;
            case 6:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.obt_fecha());
                else throw new Exception(noExiste);
                break;
            case 7:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.obtFecha());
                else throw new Exception(noExiste);
                break;
            case 8:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.ID());
                else throw new Exception(noExiste);
                break;
            case 9:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.IDN());
                else throw new Exception(noExiste);
                break;
            case 10:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.IDFecha());
                else throw new Exception(noExiste);
                break;
            case 11:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.IDImp());
                else throw new Exception(noExiste);
                break;
            case 12:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.toString());
                else throw new Exception(noExiste);
                break;
            case 13:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.toStringF());
                else throw new Exception(noExiste);
                break;
            case 14:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.toStringI());
                else throw new Exception(noExiste);
                break;
            case 15:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(Integer.toString(e.obt_importancia()));
                else throw new Exception(noExiste);
                break;
            case 16:
                if (aux.length > 1) throw new Exception(dem);
                if (e != null) System.out.println(e.tipo());
                else throw new Exception(noExiste);
                break;
            case 17:
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
        System.out.println(opcion6);
        System.out.println(opcion7);
        System.out.println(opcion8);
        System.out.println(opcion9);
        System.out.println(opcion10);
        System.out.println(opcion11);
        System.out.println(opcion12);
        System.out.println(opcion13);
        System.out.println(opcion14);
        System.out.println(opcion15);
        System.out.println(opcion16);
        System.out.println(msg);
    }
}
