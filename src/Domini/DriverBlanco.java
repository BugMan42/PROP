package Domini;

import java.util.Scanner;

/**
 * Created by falc on 13/04/15.
 */
public class DriverBlanco {

    public static void main(String[] args){
        Scanner user_input = new Scanner( System.in );
        Blanco v = new Blanco();
        System.out.println("Escribe el DNI del congresista votante:");
        v.mod_dni(user_input.next());
        System.out.println(v.toString());
    }
}
