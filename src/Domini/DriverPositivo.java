package Domini;

import java.util.Scanner;

/**
 * Created by falc on 13/04/15.
 */
public class DriverPositivo {

    public static void main(String[] args){
        Scanner user_input = new Scanner( System.in );
        int opt = user_input.nextInt();
        Positivo v = new Positivo();

        do {
            System.out.println("Driver de Positivo");
            System.out.println("0. Consulta de tipo");
            System.out.println("1. Ayuda");
            switch (opt)
            {
                case 0:
                    System.out.println(v.toString());
                    break;
                case 1:
                    System.out.println("AYUDA: Introduce el número de la opción que prefieras.");
                    break;
                default:
                    System.out.println("Vuelva a introducir un número.");
            }
            opt = user_input.nextInt();
        } while (opt != 2);

    }
}
