package Domini;

import java.util.Scanner;

public class DriverCjtEvento {
    final static String menu = "Bienvenido/a al driver de conjunto de eventos";
    final static String opcion1 = "1 CjtEvento()";
    final static String opcion2 = "2 EliminarCjtEvento()";
    final static String opcion3 = "3 EliminarEvento(String nombre, Fecha fecha, int imp)";
    final static String opcion4 = "4 AgregarEvento(Evento e). Sin argumentos";
    final static String opcion5 = "5 ModificarNombreEvento(String nomViejo, Fecha fecha, String nomNuevo, int imp)";
    final static String opcion6 = "6 ModificarFechaEvento(String nombre, Fecha fechaVieja, Fecha fechaNueva, int imp)";
    final static String opcion7 = "7 ModificarImpEvento(String nombre, Fecha fecha, int OldImp, int NewImp)";
    final static String opcion8 = "8 obtEventos(int bloq, int tam)";
    final static String opcion9 = "9 obtEventosF(int bloq, int tam)";
    final static String opcion10 = "10 obtEventosI(int bloq, int tam)";
    final static String opcion11 = "11 AgregarEventoRandom(int n)";
    final static String opcion12 = "12 ConsultarTodosEventos()";
    final static String opcion13 = "13 ConsultarEvento(String nombre, Fecha fecha)";
    final static String opcion14 = "14 ExisteEvento(String nombre, String fecha)";
    final static String opcion15 = "15 size()";
    final static String opcion16 = "16 busquedaNombre(String prefijo)";
    final static String opcion17 = "17 busquedaFecha(String prefijo)";
    final static String opcion18 = "18 busquedaImp(String prefijo)";
    final static String msg = "Introduzca un numero del 1 al 18. 19 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    final static String dem = "Demasiados Argumentos";
    final static String ins = "Argumentos Insuficientes";
    final static String noexiste = "El conjunto no existe";
    final static String eventoNoExiste = "El evento no existe";
    private static CjtEvento cjt = null;
    private static Evento e;

    public static void main(String[] args) throws Exception {
        System.out.println(menu);
        ImprimirMenu();
        Scanner entrada = new Scanner(System.in);
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
                cjt = new CjtEvento();
                break;
            case 2:
                if (aux.length > 1) throw new Exception(dem);
                if (cjt != null) cjt.EliminarCjtEvento();
                else throw new Exception(noexiste);
                break;
            case 3:
                if (aux.length < 4) throw new Exception(ins);
                if (aux.length > 4) throw new Exception(dem);
                if (cjt != null) cjt.EliminarEvento(aux[1], new Fecha(aux[2]), Integer.parseInt(aux[3]));
                else throw new Exception(noexiste);
                break;
            case 4:
                if (aux.length > 1) throw new Exception(dem);
                e = new Evento1("Golf", new Fecha("5/5/2015"), 1);
                if (cjt != null) cjt.AgregarEvento(e);
                else throw new Exception(noexiste);
                break;
            case 5:
                if (aux.length < 5) throw new Exception(ins);
                if (aux.length > 5) throw new Exception(dem);
                if (cjt != null) cjt.ModificarNombreEvento(aux[1], new Fecha(aux[2]), aux[3], Integer.parseInt(aux[4]));
                else throw new Exception(noexiste);
                break;
            case 6:
                if (aux.length < 5) throw new Exception(ins);
                if (aux.length > 5) throw new Exception(dem);
                if (cjt != null)cjt.ModificarFechaEvento(aux[1], new Fecha(aux[2]), new Fecha(aux[3]), Integer.parseInt(aux[4]));
                else throw new Exception(noexiste);
                break;
            case 7:
                if (aux.length < 5) throw new Exception(ins);
                if (aux.length > 5) throw new Exception(dem);
                if (cjt != null)cjt.ModificarImpEvento(aux[1], new Fecha(aux[2]), Integer.parseInt(aux[3]), Integer.parseInt(aux[4]));
                else throw new Exception(noexiste);
                break;
            case 8:
                if (aux.length < 3) throw new Exception(ins);
                if (aux.length > 3) throw new Exception(dem);
                if (cjt != null) System.out.println(cjt.obtEventos(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                else throw new Exception(noexiste);
                break;
            case 9:
                if (aux.length < 3) throw new Exception(ins);
                if (aux.length > 3) throw new Exception(dem);
                if (cjt != null) System.out.println(cjt.obtEventosF(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                else throw new Exception(noexiste);
                break;
            case 10:
                if (aux.length < 3) throw new Exception(ins);
                if (aux.length > 3) throw new Exception(dem);
                if (cjt != null) System.out.println(cjt.obtEventosI(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                else throw new Exception(noexiste);
                break;
            case 11:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                if (cjt != null) cjt.AgregarEventoRandom(Integer.parseInt(aux[1]));
                else throw new Exception(noexiste);
                break;
            case 12:
                if (aux.length > 1) throw new Exception(dem);
                if (cjt != null) System.out.println(cjt.ConsultarTodosEventos());
                else throw new Exception(noexiste);
                break;
            case 13:
                if (aux.length < 3) throw new Exception(ins);
                if (aux.length > 3) throw new Exception(dem);
                if (cjt != null) System.out.println(cjt.ConsultarEvento(aux[1], new Fecha(aux[2])).toString());
                else throw new Exception(noexiste);
                break;
            case 14:
                if (aux.length < 3) throw new Exception(ins);
                if (aux.length > 3) throw new Exception(dem);
                if (cjt != null) {
                    if (cjt.ExisteEvento(aux[1], new Fecha(aux[2]))) System.out.println(eventoNoExiste);
                    else System.out.println(eventoNoExiste);
                }
                else throw new Exception(noexiste);
                break;
            case 15:
                if (aux.length > 1) throw new Exception(dem);
                if (cjt != null) System.out.println(cjt.size());
                else throw new Exception(noexiste);
                break;
            case 16:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                if (cjt != null) System.out.println(cjt.busquedaNombre(aux[1]));
                else throw new Exception(noexiste);
                break;
            case 17:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                if (cjt != null) System.out.println(cjt.busquedaFecha(aux[1]));
                else throw new Exception(noexiste);
                break;
            case 18:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                if (cjt != null) System.out.println(cjt.busquedaImp(aux[1]));
                else throw new Exception(noexiste);
                break;
            case 19:
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
        System.out.println(opcion17);
        System.out.println(opcion18);
        System.out.println(msg);
    }
}