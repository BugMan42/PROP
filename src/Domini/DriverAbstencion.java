package Domini;

import java.util.Scanner;

/**
 * Created by falc on 13/04/15.
 */
public class DriverAbstencion {

    public static void main(String[] args){
        Scanner user_input = new Scanner( System.in );
        int opt;
        Abstencion v = new Abstencion();
        do {
            System.out.println("\nDRIVER DE ABSTENCIÓN");
            System.out.println("0. Consulta de tipo");
            System.out.println("1. Ayuda");
            System.out.println("2. Salir");
            System.out.println("Introduzca el número de la opción que desee.");
            opt = user_input.nextInt();
            switch (opt)
            {
                case 0:
                    System.out.println(v.mostrarInfo());
                    break;
                case 1:
                    System.out.println("AYUDA: Introduce el número de la opción que prefieras.");
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Vuelva a introducir un número.");
            }

        } while (opt != 2);

    }
}