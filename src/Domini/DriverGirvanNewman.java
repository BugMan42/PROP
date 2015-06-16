package Domini;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by falc on 29/04/15.
 */
public class DriverGirvanNewman {
    private static Girvan_Newman gn;
    private static Entrada in;
    private static Salida out;
    private static Scanner ui;

    public static void main(String[] args) throws Exception {
        ui = new Scanner(System.in);
        Grafo golf = crearGrafo();
        in = new Entrada(golf, 2, 0);
        out = new Salida();
        gn = new Girvan_Newman(in, out);
        ArrayList<String> hist = out.mostrarHistorial1();
        for (String aHist : hist) {
            System.out.println(aHist);
        }
        System.out.println(out.comunidad1());


    }

    private static Grafo crearGrafo() throws Exception {
        Grafo g = new Grafo();
        System.out.println("Introduzca el número de vértices del grafo a crear:");
        int cantidad = Integer.parseInt(ui.next());
        char ch = 'a';
        for (int i = 0; i < cantidad; ++i)
        {
            g.agregarVertice(Character.toString(ch));
            ++ch;
        }

        System.out.println("Introduzca el número de aristas del grafo a crear:");
        int n_aristas = Integer.parseInt(ui.next());
        System.out.println("Introduzca las aristas con el siguiente formato (Nº arista_origen, Nº arista_destino, peso):");
        for (int j = 0; j < n_aristas; ++j)
        {

            int v1 = Integer.parseInt(ui.next());
            int v2 = Integer.parseInt(ui.next());
            double p = Double.parseDouble(ui.next());

            if ((v1 < cantidad && v1 >= 0) && (v2 < cantidad && v2 >= 0)) {
                g.agregarArista(v1, v2, p);
                g.agregarArista(v2, v1, p);
            }
            else
            {
                System.out.println("Vértice incorrecto, operación cancelada");
            }

        }

        System.out.println("Introduzca el número máximo de comunidades para el algoritmo:");
        int limit = ui.nextInt();

        return g;
    }
}
