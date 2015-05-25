package Domini;

import java.util.Scanner;

/**
 * Created by falc on 22/04/15.
 */
public class DriverControladorAlgoritmo {

    private static ControladorAlgoritmo c_alg;
    private static ControladorRelaciones c_rel;
    private static ControladorCjtEvento cce;
    private static ControladorCongreso cco;
    private static Scanner ui;


    public static void main(String[] args) {
        cco = new ControladorCongreso();
        cce = new ControladorCjtEvento();
        c_rel = new ControladorRelaciones(cco, cce);
        c_alg = new ControladorAlgoritmo(c_rel);
        ui = new Scanner(System.in);

        System.out.println("Driver del Controlador de Algoritmo");
        int opt;
        do {
            printMenu();
            opt = ui.nextInt();
            switch(opt)
            {
                case 1:
                    crearGrafo();
                    break;
                case 2:
                    selectAlgorithm();
                    break;
                case 3:
                    crearEntrada();
                    break;
                case 4:
                    consultarSalida();
                default:
                    break;
            }

        } while (opt != 5);
    }

    private static void printMenu()
    {
        System.out.println("1. Crear grafo");
        System.out.println("2. Elegir algoritmo y ejecutar");
        System.out.println("3. Crear entrada");
        System.out.println("4. Consultar salida");
        System.out.println("5. Salida");

    }

    private static void selectAlgorithm()
    {
        try {
            System.out.println("Escoja un algoritmo");
            System.out.println("1. Girvan-Newman");
            System.out.println("2. Louvain");
            System.out.println("3. Clique Percolation");
            int op = ui.nextInt();
            switch (op) {
                case 1:
                    c_alg.seleccionAlgoritmo(1);
                    break;
                case 2:
                    c_alg.seleccionAlgoritmo(2);
                    break;
                case 3:
                    c_alg.seleccionAlgoritmo(3);
                    break;
                default:
                    break;
            }
        }
        catch(Exception a ) {
            System.out.println(a.getMessage());
        }
    }

    private static void crearEntrada()
    {
        System.out.println("Introduzca el parámetro (número entero):");
        String p = ui.next();
        c_alg.modParamEntrada(p);
    }

    private static void crearGrafo()
    {
        try {
            cco.agregarCongresista("14141414R", "abc", "def", 34, "bcn", "CAT", "Demo");
            cco.agregarCongresista("15151515G", "rand", "om", 54, "bcn", "CAT", "Demo");
            cce.AgregarActoOficial("Campo de Golf", "11/4/2013", 5);
            c_rel.agregarRelacion("14141414R", "Campo de Golf", "11/4/2013");
            c_rel.agregarRelacion("15151515G", "Campo de Golf", "11/4/2013");
            c_alg.crearGrafo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consultarSalida()
    {
        System.out.println("HISTORIAL");
        System.out.println(c_alg.obtSalida().mostrarHistorial());
        System.out.println("\n");
        System.out.println("COMUNIDADES");
        System.out.println(c_alg.obtSalida().comunidad());
    }

}