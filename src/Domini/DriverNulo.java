package Domini;

import java.util.Scanner;

/**
 * Created by falc on 29/04/15.
 */
public class DriverNulo {

    public static void main(String[] args){
        Scanner user_input = new Scanner( System.in );
        Nulo v = new Nulo();
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
