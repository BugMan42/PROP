package Domini;

import java.util.Scanner;

/**
 * Created by falc on 27/03/15.
 */
public class DriverVoto {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Scanner user_input = new Scanner( System.in );
        System.out.println("Escribe el tipo de voto");
        String s1 = user_input.next();
        Class c = Class.forName("Domini." + s1);
        Voto v = (Voto) c.newInstance();
        System.out.println("Introduce el DNI");
        v.mod_dni(user_input.next());
        System.out.println(v.toString());
    }
}
