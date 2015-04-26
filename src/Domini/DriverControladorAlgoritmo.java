package Domini;

import java.util.Scanner;

/**
 * Created by falc on 22/04/15.
 */
public class DriverControladorAlgoritmo {

    private static ControladorAlgoritmo c_alg;
    private static Scanner ui;

    public DriverControladorAlgoritmo()
    {
        c_alg = new ControladorAlgoritmo();
        ui = new Scanner(System.in);
    }

    public static void main(String[] args) {
        System.out.println("Driver del Controlador de Algoritmo");
        int opt;
        do {
            opt = Integer.parseInt(ui.next());

        }
        while (opt != 9);
    }

    public static void printMenu()
    {
        System.out.println("1. Crear grafo");
        System.out.println("2. Elegir algoritmo");
    }


}
