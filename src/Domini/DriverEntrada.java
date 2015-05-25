package Domini;

import java.util.Scanner;

/**
 * Created by falc on 4/05/15.
 */
public class DriverEntrada {

    public static void main(String[] args){
        Scanner user_input = new Scanner( System.in );
        int opt;
        Entrada v = new Entrada();
        do {
            System.out.println("\nDRIVER DE ENTRADA");
            System.out.println("0. Modificar parámetro");
            System.out.println("1. Consultar parámetro");
            System.out.println("2. Ayuda");
            System.out.println("3. Salir");
            opt = user_input.nextInt();
            switch (opt)
            {
                case 0:
                    System.out.println("Introduzca el número:");
                    Double p = Double.parseDouble(user_input.next());
                    v.modParam1(p);
                    break;
                case 1:
                    System.out.println(v.obtParam1());
                    break;
                case 2:
                    System.out.println("AYUDA: Introduce el número de la opción que prefieras.");
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Vuelva a introducir un número.");
                    break;
            }

        } while (opt != 3);

    }

}
