package Domini;


import java.util.Scanner;

/**
 * Created by jose on 2/04/15.
 */
public class DriverLouvain {
    // Mensajes excepciones.
    static final String E1 = "Número de parámetros incorrecto.";


    public static void main(String[] args) {
        boolean imp = true;
        if(args.length > 0 && args[0].equals("0")) imp = false;

        DriverLouvain dl = new DriverLouvain();
        Grafo g = new Grafo();
        Entrada in = new Entrada(g,0.000001);
        Salida out = new Salida();
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            if(imp) dl.menu();
            String linea = ui.nextLine();
            String[] params = linea.split("\\s");
            try {
                op = Integer.parseInt(params[0]);
                switch (op) {
                    case 0:
                        if (params.length != 2) throw new Exception(E1);
                        g.agregarVertice(params[1]);
                        break;
                    case 1:
                        if (params.length != 4) throw new Exception(E1);
                        g.agregarArista(params[1],params[2],Double.parseDouble(params[3]));
                        break;
                    case 2:
                        if (params.length != 2) throw new Exception(E1);
                        in.modParam1(Double.parseDouble(params[1]));
                        break;
                    case 3:
                        if (params.length != 1) throw new Exception(E1);
                        Louvain l = new Louvain(in,out);
                        l.ejecutar_algoritmo();
                        for (String a : out.mostrarHistorial()) dl.print(a);
                        break;
                }
            } catch (Exception e) {
                dl.print(e.getMessage());
                op = -1;
            }
        }
        while (op != 4);
    }

    private void menu() {
        print("\nDRIVER DE LOUVAIN");
        print("0 agregarVertice(String v)");
        print("1 agregarArista(String origen, String fin, double peso)");
        print("2 fijarLimiteIncrementoModularidad(double l)");
        print("3 ejecutar_algoritmo()");
        print("4 Salir\n");
    }

    private void print(String s) {
        System.out.println(s);
    }

}
