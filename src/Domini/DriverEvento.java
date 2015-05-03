package Domini;

import java.util.Scanner;

/**
 * Created by usuario on 04/04/2015.
 */
public class DriverEvento {
    final static String menu = "Bienvenido al driver de evento";
    final static String opcion1 = "1 Evento(String name, String date, int importance)";
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

    /*private static void alta(Scanner entrada) {
        System.out.println("Bienvenido a alta de un evento");
        System.out.println("Para dar de alta un evento se tienen que introducir");
        System.out.println("El tipo, nombre, fecha, subtipo e importancia");
        System.out.println("Introduzca el tipo del evento(Votacion, acto, reunion");*/
        /*while (!tipo.equals("Votacion") && !tipo.equals("Acto") && !tipo.equals("Reunion")) {
            System.out.println("Tiene que ser o Votacion o Acto o Reunion");
            tipo = entrada.next();
        }
        System.out.println("Introduzca el nombre del evento");
        String nombre = entrada.next();
        System.out.println("Introduzca la fecha del evento en formato dd/MM/yyyy");
        String data = entrada.next();
        //System.out.println("Introduzca el subtipo del evento");
        //String subtipo = entrada.next();
        System.out.println("Introduzca la importancia del evento");
        int importance = entrada.nextInt();
        try {
            e = new Evento1(nombre, data, importance);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            /*switch (Integer.parseInt(e.getMessage())) {
                case 1:
                    System.out.println("Nombre no puede ser vacÃ­o");
                    break;
                case 2:
                    System.out.println("Fecha no puede ser null");
                    break;
                case 3:
                    System.out.println("Subtipo no puede ser vacio");
                    break;
                case 4:
                    System.out.println("Importancia tiene que ser mayor que 0");
                    break;
                case 5:
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    break;
            }
        }
    }*/

    /*private static void baja(Scanner entrada) {
        System.out.println("Bienvenido a baja de un evento");
        try {
            e = null;
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            switch (Integer.parseInt(e.getMessage())) {
                case 1:
                    System.out.println("Nombre no puede ser vacio");
                    break;
                case 2:
                    System.out.println("Fecha no puede ser null");
                    break;
                case 3:
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    break;
            }
        }
    }

    private static void modificar(Scanner entrada) {
        System.out.println("Bienvenido a modificacion de un evento");
        System.out.println("Elige el atributo que deseas modificar");
        System.out.println("1-Nombre, 2-fecha, 3-importancia, 4- salir");
        int opcion = Integer.parseInt(entrada.next());
        try {
            while (opcion != 4) {
                switch (opcion) {
                    case 1:
                        System.out.println("Introduzca el nuevo nombre");
                        e.ModNombre(entrada.next());
                        break;
                    case 2:
                        System.out.println("Introduzca la nueva fecha");
                        e.ModFecha(entrada.next());
                        break;
                    case 3:
                        System.out.println("Introduzca la nueva importancia");
                        e.ModImportancia(Integer.parseInt(entrada.next()));
                        break;
                    default:
                        System.out.println("Introduzca un numero del 1 al 4");
                        break;
                }
                System.out.println("Introduzca una nueva opcion del 1 al 3. 4 para salir");
                opcion = entrada.nextInt();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            /*switch (Integer.parseInt(e.getMessage())) {
                case 1:
                    System.out.println("Nombre no puede ser vacio");
                    break;
                case 2:
                    System.out.println("Fecha no puede ser null");
                    break;
                case 3:
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    break;
            }
        }
    }

    private static void consulta(Scanner entrada) {
        System.out.println("Bienvenido a consulta de un evento");
        try {
            System.out.println(e.obt_nombre() + " " + e.obt_fecha() + " " + Integer.toString(e.obt_importancia()));
        }
        catch (Exception e) {
            System.out.println("El evento no existe");
        }
    }

    private static void ayuda(Scanner entrada) {

    }*/
}
