package Domini;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class DriverCliqueNuevo {
    final static String menu = "Bienvenido/a al driver de clique";
    final static String opcion1 = "1 Clique(Entrada in, Salida out). Usando GrafoPrueba1 y k = 3 si no se cambia. Sin argumentos";
    final static String opcion2 = "2 Elegir k";
    final static String opcion3 = "3 CrearGrafoPrueba(). Sin argumentos";
    final static String opcion4 = "4 SeleccionarGrafoPrueba(int i). 1 <= i <= 8";
    final static String opcion5 = "5 Mostrar comunidades. Sin argumentos";
    final static String opcion6 = "6 Borrar Grafo. Sin argumentos";
    final static String msg = "Introduzca un numero del 1 al 6. 7 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    final static String noExiste = "Clique no existe";
    final static String dem = "Demasiados Argumentos";
    final static String ins = "Argumentos Insuficientes";
    private static CliqueNuevo c = null;
    private static Entrada2 en;
    private static Salida2 sa;
    private static int k;
    private static GrafoNodoArista<Congresista, Edge> g = null;

    public static void main(String[] args) throws Exception {
        k = 3;
        Scanner entrada = new Scanner(System.in);
        System.out.println(menu);
        ImprimirMenu();
        do {
            try {
                Proceso(entrada);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            ImprimirMenu();
        }
        while (entrada.hasNextLine());
    }

    public static void Proceso(Scanner entrada) throws Exception {
        String s = entrada.nextLine();
        String aux[] = s.split("\\s");
        if (s.length() == 0) throw new Exception(ins);
        switch (Integer.parseInt(aux[0])) {
            case 1:
                if (aux.length > 1) throw new Exception(dem);
                if (g == null) crearGrafo(1);
                en = new Entrada2(g, k, 0);
                sa = new Salida2();
                long ini = System.nanoTime();
                c = new CliqueNuevo(en, sa);
                System.out.println("Tiempo de algoritmo");
                System.out.println((System.nanoTime() - ini)/1000000.0);
                break;
            case 2:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                k = Integer.parseInt(aux[1]);
                break;
            case 3:
                if (aux.length > 1) throw new Exception(dem);
                crearGrafoPersonalizado(entrada);
                break;
            case 4:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                crearGrafo(Integer.parseInt(aux[1]));
                break;
            case 5:
                if (aux.length > 1) throw new Exception(dem);
                if (c != null) mostrarCom();
                else throw new Exception(noExiste);
                break;
            case 6:
                if (aux.length > 1) throw new Exception(dem);
                g = null;
                System.gc();
                break;
            case 7:
                System.out.println(fin);
                System.exit(0);
                break;
            default:
                System.out.println(msg);
                break;
        }
    }

    private static void mostrarCom() {
        ArrayList<String> hist = sa.mostrarHistorial();
        for (String aHist : hist) {
            System.out.println(aHist);
        }
        for (Set<Congresista> i : sa.comunidad()) {
            System.out.println("Comunidad");
            for (Congresista j : i) {
                System.out.println(j.obtID());
            }
        }
    }

    private static void crearGrafoPersonalizado(Scanner entrada) throws Exception{
        System.out.println("Introduzca el numero de vertices que desee utilizar");
        String s = entrada.nextLine();
        String aux[] = s.split("\\s");
        if (s.length() < 1) throw new Exception(ins);
        if (s.length() > 1) throw new Exception(dem);
        if (g == null) g = new GrafoNodoArista();
        ArrayList<Congresista> lista = new ArrayList<Congresista>();
        for (int i = 0; i < Integer.parseInt(aux[0]); ++i) {
            Congresista con = new Congresista(new Dni(), "golf", "golf", 18, "golf", "golf", "golf");
            lista.add(con);
            g.agregarVertice(con);
        }
        System.out.println(g.consultarVertices());
        System.out.println("Introduzca dos indices del 0 al " + Integer.toString(g.consultarVertices().size())+ " y un peso");
        s = entrada.nextLine();
        aux = s.split("\\s");
        if (aux.length%3 != 0) throw new Exception(ins);
        for (int i = 0; i < aux.length; i+=3) {
            Edge e  = new Edge(lista.get(Integer.parseInt(aux[i])), lista.get(Integer.parseInt(aux[i + 1])), Double.parseDouble(aux[i + 2]));
            g.agregarArista(e);
            e = new Edge(lista.get(Integer.parseInt(aux[i + 1])), lista.get(Integer.parseInt(aux[i])), Double.parseDouble(aux[i + 2]));
            g.agregarArista(e);
        }
    }

   private static void crearGrafo(int i) throws Exception {
        g = new GrafoNodoArista();
        switch (i) {
            case 1:
                grafo1();
                break;
            case 2:
                grafo2();
                break;
            case 3:
                grafo3();
                break;
            case 4:
                grafo4();
                break;
            /*case 5:
                grafo5();
                break;
            case 6:
                grafo6();
                break;
            case 7:
                grafo7();
                break;*/
            case 8:
                grafo8();
                break;
            default:
                System.out.println("La i tiene que estar entre 1 y 8");
        }
    }

    private static void grafo1() throws Exception {
        Congresista C0 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C1 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
        Congresista C2 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
        Congresista C3 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
        Congresista C4 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");
        Congresista C5 = new Congresista(new Dni("00000000F"), "Jon-Von", "Neumann", 53, "Budapest", "AU", "Pirata");
        Congresista C6 = new Congresista(new Dni("00000000G"), "Alan", "Turing", 41, "London", "LO", "Liberal");
        g.agregarVertice(C0);
        g.agregarVertice(C1);
        g.agregarVertice(C2);
        g.agregarVertice(C3);
        g.agregarVertice(C4);
        g.agregarVertice(C5);
        g.agregarVertice(C6);

        Edge<Congresista> e1 = new Edge<Congresista>(C0,C1,1.0);
        Edge<Congresista> e2 = new Edge<Congresista>(C1,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C2,1.0);
        e2 = new Edge<Congresista>(C2,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C2,1.0);
        e2 = new Edge<Congresista>(C2,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C3,1.0);
        e2 = new Edge<Congresista>(C3,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C2,C3,1.0);
        e2 = new Edge<Congresista>(C3,C2,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C3,C4,1.0);
        e2 = new Edge<Congresista>(C4,C3,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C3,C5,1.0);
        e2 = new Edge<Congresista>(C5,C3,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C4,C5,1.0);
        e2 = new Edge<Congresista>(C5,C4,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C5,C6,1.0);
        e2 = new Edge<Congresista>(C6,C5,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        /*g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");
        g.agregarVertice("5");
        g.agregarVertice("6");

        g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("3", "5", 1);
        g.agregarArista("4", "5", 1);
        g.agregarArista("5", "6", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "3", 1);
        g.agregarArista("5", "3", 1);
        g.agregarArista("5", "4", 1);
        g.agregarArista("6", "5", 1);*/
    }

    private static void grafo2() throws Exception {
        Congresista C0 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C1 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
        Congresista C2 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
        Congresista C3 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
        Congresista C4 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");

        g.agregarVertice(C0);
        g.agregarVertice(C1);
        g.agregarVertice(C2);
        g.agregarVertice(C3);
        g.agregarVertice(C4);

        Edge<Congresista> e1 = new Edge<Congresista>(C0,C1,1.0);
        Edge<Congresista> e2 = new Edge<Congresista>(C1,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C2,1.0);
        e2 = new Edge<Congresista>(C2,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C3,1.0);
        e2 = new Edge<Congresista>(C3,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C2,1.0);
        e2 = new Edge<Congresista>(C2,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C3,1.0);
        e2 = new Edge<Congresista>(C3,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C4,1.0);
        e2 = new Edge<Congresista>(C4,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C2,C3,1.0);
        e2 = new Edge<Congresista>(C3,C2,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C2,C4,1.0);
        e2 = new Edge<Congresista>(C4,C2,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);
        /*g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");

        g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);*/
    }

    private static void grafo3() throws Exception {
        Congresista C0 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C1 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
        Congresista C2 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
        Congresista C3 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");

        g.agregarVertice(C0);
        g.agregarVertice(C1);
        g.agregarVertice(C2);
        g.agregarVertice(C3);

        Edge<Congresista> e1 = new Edge<Congresista>(C0,C1,1.0);
        Edge<Congresista> e2 = new Edge<Congresista>(C1,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C2,1.0);
        e2 = new Edge<Congresista>(C2,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C3,1.0);
        e2 = new Edge<Congresista>(C3,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C2,1.0);
        e2 = new Edge<Congresista>(C2,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C3,1.0);
        e2 = new Edge<Congresista>(C3,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C2,C3,1.0);
        e2 = new Edge<Congresista>(C3,C2,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        /*g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");

        g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("2", "3", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("3", "2", 1);*/
    }

    private static void grafo4() throws Exception {
        Congresista C0 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C1 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
        Congresista C2 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
        Congresista C3 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
        Congresista C4 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");

        g.agregarVertice(C0);
        g.agregarVertice(C1);
        g.agregarVertice(C2);
        g.agregarVertice(C3);
        g.agregarVertice(C4);

        Edge<Congresista> e1 = new Edge<Congresista>(C0,C1,1.0);
        Edge<Congresista> e2 = new Edge<Congresista>(C1,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C2,1.0);
        e2 = new Edge<Congresista>(C2,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C3,1.0);
        e2 = new Edge<Congresista>(C3,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C4,1.0);
        e2 = new Edge<Congresista>(C4,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C2,1.0);
        e2 = new Edge<Congresista>(C2,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C3,1.0);
        e2 = new Edge<Congresista>(C3,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C4,1.0);
        e2 = new Edge<Congresista>(C4,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C2,C3,1.0);
        e2 = new Edge<Congresista>(C3,C2,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C2,C4,1.0);
        e2 = new Edge<Congresista>(C4,C2,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C3,C4,1.0);
        e2 = new Edge<Congresista>(C4,C3,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        /*g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");

        g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("0", "4", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);
        g.agregarArista("3", "4", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("4", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);
        g.agregarArista("4", "3", 1);*/
    }

    /*private static void grafo5() throws Exception {
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

        g.agregarArista("0", "1", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("4", "5", 1);
        g.agregarArista("4", "9", 1);
        g.agregarArista("5", "6", 1);
        g.agregarArista("6", "7", 1);
        g.agregarArista("7", "8", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("4", "3", 1);
        g.agregarArista("5", "4", 1);
        g.agregarArista("9", "4", 1);
        g.agregarArista("6", "5", 1);
        g.agregarArista("7", "6", 1);
        g.agregarArista("8", "7", 1);
    }

    private static void grafo6() throws Exception {
        g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");
        g.agregarVertice("5");
        g.agregarVertice("6");
        g.agregarVertice("7");
        g.agregarVertice("8");

        g.agregarArista("0", "1", 1);
        g.agregarArista("0", "4", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "7", 1);
        g.agregarArista("2", "8", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("5", "7", 1);
        g.agregarArista("6", "7", 1);
        g.agregarArista("7", "8", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("4", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("7", "2", 1);
        g.agregarArista("8", "2", 1);
        g.agregarArista("4", "3", 1);
        g.agregarArista("7", "5", 1);
        g.agregarArista("7", "6", 1);
        g.agregarArista("8", "7", 1);
    }

    private static void grafo7() throws Exception {
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
        g.agregarArista("0", "4", 1);
        g.agregarArista("0", "5", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("1", "6", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);
        g.agregarArista("3", "6", 1);
        g.agregarArista("4", "8", 1);
        g.agregarArista("5", "9", 1);
        g.agregarArista("5", "11", 1);
        g.agregarArista("6", "7", 1);
        g.agregarArista("6", "10", 1);
        g.agregarArista("7", "11", 1);
        g.agregarArista("7", "12", 1);
        g.agregarArista("8", "9", 1);
        g.agregarArista("9", "10", 1);
        g.agregarArista("10", "11", 1);
        g.agregarArista("11", "12", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("4", "0", 1);
        g.agregarArista("5", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("6", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);
        g.agregarArista("6", "3", 1);
        g.agregarArista("8", "4", 1);
        g.agregarArista("9", "5", 1);
        g.agregarArista("11", "5", 1);
        g.agregarArista("7", "6", 1);
        g.agregarArista("10", "6", 1);
        g.agregarArista("11", "7", 1);
        g.agregarArista("12", "7", 1);
        g.agregarArista("9", "8", 1);
        g.agregarArista("10", "9", 1);
        g.agregarArista("11", "10", 1);
        g.agregarArista("12", "11", 1);
    }*/

    private static void grafo8() throws Exception {
        Congresista C0 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C1 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
        Congresista C2 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
        Congresista C3 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
        Congresista C4 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");

        g.agregarVertice(C0);
        g.agregarVertice(C1);
        g.agregarVertice(C2);
        g.agregarVertice(C3);
        g.agregarVertice(C4);

        Edge<Congresista> e1 = new Edge<Congresista>(C0,C1,1.0);
        Edge<Congresista> e2 = new Edge<Congresista>(C1,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C2,1.0);
        e2 = new Edge<Congresista>(C2,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C0,C3,1.0);
        e2 = new Edge<Congresista>(C3,C0,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C2,1.0);
        e2 = new Edge<Congresista>(C2,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C1,C4,1.0);
        e2 = new Edge<Congresista>(C4,C1,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C2,C3,1.0);
        e2 = new Edge<Congresista>(C3,C2,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C2,C4,1.0);
        e2 = new Edge<Congresista>(C4,C2,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        e1 = new Edge<Congresista>(C3,C4,1.0);
        e2 = new Edge<Congresista>(C4,C3,1.0);
        g.agregarArista(e1);
        g.agregarArista(e2);

        /*g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");

        g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);
        g.agregarArista("3", "4", 1);

        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);
        g.agregarArista("4", "3", 1);*/
    }

    private static void ImprimirMenu() {
        System.out.println(opcion1);
        System.out.println(opcion2);
        System.out.println(opcion3);
        System.out.println(opcion4);
        System.out.println(opcion5);
        System.out.println(opcion6);
        System.out.println(msg);
    }
}

