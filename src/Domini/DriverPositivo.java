package Domini;

import java.util.Scanner;

/**
 * Created by falc on 13/04/15.
 */
public class DriverPositivo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Scanner user_input = new Scanner( System.in );
        Positivo v = new Positivo();
        System.out.println("Escribe el DNI del congresista votante:");
        v.mod_dni(user_input.next());
        System.out.println(v.toString());
    }
}