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
    private Evento e;

    public static void main(String[] args) throws ParseException {
        ImprimirMenu();
        Scanner entrada = new Scanner(System.in);
        int opcion = Integer.parseInt(entrada.next());
        while (opcion != 6) {
            switch(opcion) {
                case 1:
                    alta(entrada);
                    break;
               case 2:
                    baja(entrada);
                    break;
                 /*case 3:
                    modificar(entrada);
                    break;
                case 4:
                    consulta(entrada);
                    break;
                case 5:
                    ayuda(entrada);
                    break;*/
                default:
                    System.out.println(error);
            }
            System.out.println(nueva);
            opcion = Integer.parseInt(entrada.next());
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
        System.out.println("El nombre, fecha, subtipo e importancia");
        System.out.println("Introduzca los campos separados por espacios");
        String nombre = entrada.next();
        if (nombre.equals("")) throw new IllegalArgumentException("Nombre no puede ser vacío");
        System.out.println("Introduzca la fecha del evento en formato dd/MM/yyyy");
        String data = entrada.next();
        if (data.equals("") || data.equals("\n")) throw new IllegalArgumentException("Fecha no puede ser vacío");
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date d = formato.parse(data);
        System.out.println("Introduzca el subtipo del evento");
        String subtipo = entrada.next();
        if (data.equals("") || data.equals("\n")) throw new IllegalArgumentException("Subtipo no puede ser vacío");
        System.out.println("Introduzca la importancia del evento");
        String importancia = entrada.next();
        if (data.equals("") || data.equals("\n")) throw new IllegalArgumentException("Importancia no puede ser vacío");
        Integer importance = Integer.valueOf(importancia);
        if (importance <= 0) throw new IllegalArgumentException("Importancia tiene que ser mayor que 0");
        //Crear evento
        System.out.println("La creacion ha sido un exito :D");
    }

    private static void baja(Scanner entrada) {
        System.out.println("Bienvenido a baja de un evento");
        System.out.println("Para dar de baja un evento se tienen que introducir");
        System.out.println("El nombre y la fecha");
        System.out.println("Introduzca el nombre del evento");
        String datos = entrada.next();
    }
}
