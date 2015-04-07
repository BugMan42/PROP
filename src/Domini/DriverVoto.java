package Domini;

import java.util.Scanner;

/**
 * Created by falc on 27/03/15.
 */
public class DriverVoto {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Scanner user_input = new Scanner( System.in );
        System.out.println("Escribe el tipo de voto a introducir:");
        System.out.println("(Nulo, Blanco, Abstencion, Positivo o Negativo)");
        String s1 = user_input.next();
        Class c;
        Voto v;
        try {
            c = Class.forName("Domini." + s1);
            v = (Voto) c.newInstance();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Tipo de voto no válido, operación cancelada.");
            return;
        }
        System.out.println("Escribe el DNI del congresista votante:");
        v.mod_dni(user_input.next());
        System.out.println(v.toString());
    }

}
