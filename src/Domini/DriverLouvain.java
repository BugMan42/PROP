package Domini;


import java.util.Scanner;

/**
 * Created by jose on 2/04/15.
 */
public class DriverLouvain {
    // Mensajes excepciones.
    static final String E1 = "Número de parámetros incorrecto.";


    public static void main(String[] args) {
        DriverLouvain dl = new DriverLouvain();
        Grafo g = new Grafo();
        Entrada in = new Entrada(g,0.000001);
        Salida out = new Salida();
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            dl.menu();
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
                    case 4:
                        if (params.length != 1) throw new Exception(E1);
                        dl.ejecutar_ejemplo1();
                        break;
                    case 5:
                        if (params.length != 1) throw new Exception(E1);
                        dl.ejecutar_ejemplo2();
                        break;
                    case 6:
                        if (params.length != 1) throw new Exception(E1);
                        dl.ejecutar_ejemplo3();
                        break;
                }
            } catch (Exception e) {
                dl.print(e.getMessage());
                op = -1;
            }
        }
        while (op != 7);
    }

    private void menu() {
        print("\nDRIVER DE LOUVAIN");
        print("//////// CREAR GRAFO PERSONALIZADO ///////////");
        print("0 agregarVertice(String v)");
        print("1 agregarArista(String origen, String fin, double peso)");
        print("2 fijarLimiteIncrementoModularidad(double l)");
        print("3 ejecutar_algoritmo()");
        print("//////// GRAFOS PREDEFINIDOS /////////////////");
        print("4 ejecutar_ejemplo1()");
        print("5 ejecutar_ejemplo2()");
        print("6 ejecutar_ejemplo3()");
        print("//////////////////////////////////////////////");
        print("7 Salir\n");
    }

    private void print(String s) {
        System.out.println(s);
    }

    private void ejecutar_ejemplo1() throws Exception {
        Grafo g = new Grafo();

        g.agregarVertice("a");
        g.agregarVertice("b");
        g.agregarVertice("c");

        g.agregarArista("a", "b", 1);
        g.agregarArista("b", "a", 1);
        g.agregarArista("b", "c", 1);
        g.agregarArista("c","b",1);
        g.agregarArista("c","a",1);
        g.agregarArista("a","c",1);

        double Q = 0.000001;
        Entrada in = new Entrada(g, Q);
        Salida out = new Salida();
        Louvain l = new Louvain(in, out);
        l.ejecutar_algoritmo();

        for (String a : out.mostrarHistorial()) print(a);
    }

    private void ejecutar_ejemplo2() throws Exception {
        Grafo g = new Grafo();

        g.agregarVertice("a");
        g.agregarVertice("b");
        g.agregarVertice("c");
        g.agregarVertice("d");
        g.agregarVertice("e");
        g.agregarVertice("f");

        g.agregarArista("a","b",1);
        g.agregarArista("b","a",1);
        g.agregarArista("b","c",1);
        g.agregarArista("c","b",1);
        g.agregarArista("c","a",1);
        g.agregarArista("a","c",1);
        g.agregarArista("c","d",1);
        g.agregarArista("d","c",1);
        g.agregarArista("d","e",1);
        g.agregarArista("e","d",1);
        g.agregarArista("e","f",1);
        g.agregarArista("f","e",1);
        g.agregarArista("f","d",1);
        g.agregarArista("d","f",1);

        double Q = 0.000001;
        Entrada in = new Entrada(g, Q);
        Salida out = new Salida();
        Louvain l = new Louvain(in, out);
        l.ejecutar_algoritmo();

        for (String a : out.mostrarHistorial()) print(a);
    }

    private void ejecutar_ejemplo3() throws Exception {
        Grafo g = new Grafo();

        g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");
        g.agregarVertice("5");
        g.agregarVertice("6");
        g.agregarVertice("7");
        g.agregarVertice("8");
        g.agregarVertice("9");
        g.agregarVertice("10");
        g.agregarVertice("11");
        g.agregarVertice("12");

        g.agregarArista("0", "1", 1);
        g.agregarArista("1", "0", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("0", "4", 1);
        g.agregarArista("4", "0", 1);
        g.agregarArista("4", "5", 1);
        g.agregarArista("5", "4", 1);
        g.agregarArista("5", "6", 1);
        g.agregarArista("6", "5", 1);
        g.agregarArista("6", "4", 1);
        g.agregarArista("4", "6", 1);
        g.agregarArista("0", "7", 1);
        g.agregarArista("7", "0", 1);
        g.agregarArista("7", "8", 1);
        g.agregarArista("8", "7", 1);
        g.agregarArista("8", "9", 1);
        g.agregarArista("9", "8", 1);
        g.agregarArista("9", "7", 1);
        g.agregarArista("7", "9", 1);
        g.agregarArista("0", "10", 1);
        g.agregarArista("10", "0", 1);
        g.agregarArista("10", "11", 1);
        g.agregarArista("11", "10", 1);
        g.agregarArista("11", "12", 1);
        g.agregarArista("12", "11", 1);
        g.agregarArista("12", "10", 1);
        g.agregarArista("10", "12", 1);

        double Q = 0.000001;
        Entrada in = new Entrada(g, Q);
        Salida out = new Salida();
        Louvain l = new Louvain(in, out);
        l.ejecutar_algoritmo();

        for (String a : out.mostrarHistorial()) print(a);
    }

}
