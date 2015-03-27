package Domini; /**
 * Created by bug on 20/03/15.
 * Clase DataHandler
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DataHandler {
    Scanner user_input = new Scanner( System.in );

    //Introduccion datos congresista
    //Pre: Se introduciran los parametros del congresista
    //Si no se introduce el dni (dni == null) Error, retorn -1
    // Si se introduce el dni bien se retorna 1
    //Post: Devuelve un Congresista
    int Crear(Congresista c) {
        String sal1 = "********Introduce los parametros del Congresista********";
        String sal2 = "Formato : dni nombre apellido edad ciudad estado partido";
        String sal3 = "FIN Introduccion datos";
        System.out.println(sal1);
        System.out.println(sal2);
        String dni = user_input.next();
        if (dni.equals("")) {
            System.out.println("Error: Dni no puede ser vacio");
            return -1;
        }
        c.mod_dni(dni);
        c.mod_nombre(user_input.next());
        c.mod_apellido(user_input.next());
        c.mod_edad(Integer.valueOf(user_input.next()));
        c.mod_ciudad(user_input.next());
        c.mod_estado(user_input.next());
        c.mod_partido(user_input.next());
        System.out.println(sal3);
        return 1;
    }

    int Crear(Evento e) throws ParseException {
        String sal1 = "********Introduce los parametros del Evento********";
        String sal2 = "Formato : nombre fecha(dd/mm/aaaa) subtipo importancia";
        String sal3 = "FIN Introducción datos";
        System.out.println(sal1);
        System.out.println(sal2);
        e.mod_nombre(user_input.next());
        String sdate = user_input.next();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        e.mod_fecha(format.parse(sdate));
        e.mod_subtipo(user_input.next());
        e.mod_importancia(Integer.valueOf(user_input.next()));
        System.out.println(sal3);
        return 1;
    }

    void print_congr(Congresista c) {
        //String salida = c.obt_dni() + " "+c.obt_nombre() " " + c.obt_apellido() + " " + c.obt_edad() + " " + c.obt_ciudad() + " " + c.obt_estado() + " " + c.obt_partido();
        System.out.println(c.toString());
    }

    void print_evento(Evento e) {
        String output = e.obt_nombre() + " " + e.obt_fecha() + " " + e.obt_subtipo() + " " + e.obt_importancia();
        System.out.println(output);
    }
}
