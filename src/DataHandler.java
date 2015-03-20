/**
 * Created by bug on 20/03/15.
 */
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
        if (dni.equals(null)) {
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
    void print_congr(Congresista c) {
        String salida = c.obt_dni() + " " + c.obt_apellido() + " " + c.obt_edad() + " " + c.obt_ciudad() + " " + c.obt_estado() + " " + c.obt_partido();
        System.out.println(salida);
    }
}
