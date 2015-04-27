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
            printMenu();
            opt = Integer.parseInt(ui.next());
            switch(opt)
            {
                case 2:
                    selectAlgorithm();
                    break;

                default:
                    break;
            }

        }
        while (opt != 9);
    }

    private static void printMenu()
    {
        System.out.println("1. Crear grafo");
        System.out.println("2. Elegir algoritmo");
        System.out.println("3. Crear entrada");

    }

    private static void selectAlgorithm()
    {
        System.out.println("Escoja un algoritmo");
        System.out.println("1. Girvan-Newman");
        System.out.println("2. Louvain");
        System.out.println("3. Clique Percolation");
        int op = ui.nextInt();
        switch (op)
        {
            case 1:
                c_alg.seleccionAlgoritmo("gn");
                break;
            case 2:
                c_alg.seleccionAlgoritmo("lv");
                break;
            case 3:
                c_alg.seleccionAlgoritmo("cl");
                break;
            default:
                break;
        }
    }



}
