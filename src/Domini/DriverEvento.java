package Domini;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by usuario on 04/04/2015.
 */
public class DriverEvento {
    final static String menu = "Bienvenido al driver de evento";
    final static String opcion1 = "1. Alta de un evento";
    final static String opcion2 = "2. Baja de un evento";
    final static String opcion3 = "3. Modificacion de un evento";
    final static String opcion4 = "4. Consulta de un evento";
    final static String opcion5 = "5. Ayuda";
    final static String opcion6 = "6. Salir";
    final static String error = "Introduzca un numero del 1 al 6";
    final static String nueva = "Introduzca una nueva opcion del 1 al 5. 6 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    private static Evento e;

    public static void main(String[] args) throws ParseException {
        ImprimirMenu();
        Scanner entrada = new Scanner(System.in);
        int opcion = entrada.nextInt();
        while (opcion != 6) {
            switch(opcion) {
                case 1:
                    alta(entrada);
                    break;
               case 2:
                    baja(entrada);
                    break;
                 case 3:
                    modificar(entrada);
                    break;
                case 4:
                    consulta(entrada);
                    break;
                /*case 5:
                    ayuda(entrada);
                    break;*/
                default:
                    System.out.println(error);
            }
            System.out.println(nueva);
            opcion = entrada.nextInt();
        }
        System.out.println(fin);
    }

    private static void ImprimirMenu() {
        System.out.println(menu);
        System.out.println(opcion1);
        System.out.println(opcion2);
        System.out.println(opcion3);
        System.out.println(opcion4);
        System.out.println(opcion5);
        System.out.println(opcion6);
    }

    private static void alta(Scanner entrada) throws ParseException {
        System.out.println("Bienvenido a alta de un evento");
        System.out.println("Para dar de alta un evento se tienen que introducir");
        System.out.println("El tipo, nombre, fecha, subtipo e importancia");
        System.out.println("Introduzca el tipo del evento(Votacion, acto, reunion");
        String tipo = entrada.next();
        while (!tipo.equals("Votacion") && !tipo.equals("Acto") && !tipo.equals("Reunion")) {
            System.out.println("Tiene que ser o Votacion o Acto o Reunion");
            tipo = entrada.next();
        }
        System.out.println("Introduzca el nombre del evento");
        String nombre = entrada.next();
        System.out.println("Introduzca la fecha del evento en formato dd/MM/yyyy");
        String data = entrada.next();
        System.out.println("Introduzca el subtipo del evento");
        String subtipo = entrada.next();
        System.out.println("Introduzca la importancia del evento");
        int importance = entrada.nextInt();
        try {
            if (tipo.equals("Votacion")) e = new Votacion(nombre, data, subtipo, importance);
            else if (tipo.equals("Acto")) e = new Acto(nombre, data, subtipo, importance);
            else e = new Reunion(nombre, data, subtipo, importance);
        }
        catch (IllegalArgumentException e) {
            switch (Integer.parseInt(e.getMessage())) {
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
    }

    private static void baja(Scanner entrada) {
        System.out.println("Bienvenido a baja de un evento");
        try {
            e = null;
        }
        catch (IllegalArgumentException e) {
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

    private static void modificar(Scanner entrada) throws ParseException {
        System.out.println("Bienvenido a modificacion de un evento");
        System.out.println("Elige el atributo que deseas modificar");
        System.out.println("1-Nombre, 2-fecha, 3-subtipo, 4-importancia, 5- salir");
        int opcion = Integer.parseInt(entrada.next());
        try {
            while (opcion != 5) {
                switch (opcion) {
                    case 1:
                        System.out.println("Introduzca el nuevo nombre");
                        e.ModNombre(entrada.next());
                        break;
                    case 2:
                        System.out.println("Introduzca la nueva fecha");
                        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        e.ModFecha(entrada.next());
                        break;
                    case 3:
                        System.out.println("Introduzca el nuevo subtipo");
                        e.ModSubtipo(entrada.next());
                        break;
                    case 4:
                        System.out.println("Introduzca la nueva importancia");
                        e.ModImportancia(Integer.parseInt(entrada.next()));
                        break;
                    default:
                        System.out.println("Introduzca un numero del 1 al 5");
                        break;
                }
            }
        }
        catch (IllegalArgumentException e) {
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

    private static void consulta(Scanner entrada) {
        System.out.println("Bienvenido a consulta de un evento");
        System.out.println(e.obt_nombre() + " " + e.obt_fecha() + " " + e.obt_subtipo() + " " + Integer.toString(e.obt_importancia()));
    }
}
