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
        String s1 = user_input.next();
        try {
            v.mod_dni(s1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DNI incorrecto");
        }
        System.out.println(v.toString());
    }
}
