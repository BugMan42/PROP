package Domini;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by usuario on 28/04/2015.
 */
public class DriverClique {
    final static String menu = "Bienvenido/a al driver de clique";
    final static String opcion1 = "1 Clique(Entrada in, Salida out). Usando GrafoPrueba1 y k = 3 si no se cambia.";
    final static String opcion2 = "2 Elegir k";
    final static String opcion3 = "3 ejecutar_algoritmo()";
    final static String opcion4 = "4 CrearGrafoPrueba(). Sin argumentos";
    final static String opcion5 = "5 SeleccionarGrafoPrueba(int i). 1 <= i <= 7";
    final static String opcion6 = "6 Mostrar comunidades";
    final static String msg = "Introduzca un numero del 1 al 6. 7 para salir";
    final static String fin = "Gracias por usar este driver. THE END";
    final static String noExiste = "Clique no existe";
    final static String dem = "Demasiados Argumentos";
    final static String ins = "Argumentos Insuficientes";
    private static Clique c;
    private static Entrada en;
    private static Salida sa;
    private static int k;
    private static Grafo g;

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
                crearGrafo(1);
                en = new Entrada(g, k);
                sa = new Salida();
                c = new Clique(en, sa);
                break;
            case 2:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                k = Integer.parseInt(aux[1]);
                break;
            case 3:
                if (aux.length > 1) throw new Exception(dem);
                if (c != null) c.ejecutar_algoritmo();
                else throw new Exception(noExiste);
                break;
            case 4:
                if (aux.length > 1) throw new Exception(dem);
                crearGrafoPersonalizado(entrada);
                break;
            case 5:
                if (aux.length < 2) throw new Exception(ins);
                if (aux.length > 2) throw new Exception(dem);
                crearGrafo(Integer.parseInt(aux[1]));
                break;
            case 6:
                if (aux.length > 1) throw new Exception(dem);
                if (c != null) mostrarCom();
                else throw new Exception(noExiste);
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
        for (Set<Integer> i : sa.comunidad()) {
            System.out.println("Comunidad");
            for (Integer j : i) {
                System.out.println(Integer.toString(j));
            }
        }
    }

    private static void crearGrafoPersonalizado(Scanner entrada) throws Exception{
        System.out.println("Introduzca los vertices que desee utilizar separados por linea");
        String s = entrada.nextLine();
        String aux[] = s.split("\\s");
        if (s.length() == 0) throw new Exception(ins);
        for (int i = 0; i < aux.length; ++i) g.agregarVertice(aux[i]);
        System.out.println("Introduzca nodo origen, nodo destino y peso separados por espacios. Ex: u v 1");
        s = entrada.nextLine();
        aux = s.split("\\s");
        if (aux.length%3 != 0) throw new Exception(ins);
        for (int i = 0; i < aux.length; i+=3) g.agregarArista(aux[i], aux[i+1], Integer.parseInt(aux[i+2]));
    }

    private static void crearGrafo(int i) throws Exception {
        g = new Grafo();
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
            case 5:
                grafo5();
                break;
            case 6:
                grafo6();
                break;
            case 7:
                grafo7();
                break;
            default:
                System.out.println("La i tiene que estar entre 1 y 7");
        }
    }

    private static void grafo1() throws Exception {
        g.agregarVertice("0");
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
        g.agregarArista("6", "5", 1);
    }

    private static void grafo2() throws Exception {
        g.agregarVertice("0");
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
        g.agregarArista("4", "3", 1);
    }

    private static void grafo3() throws Exception {
        g.agregarVertice("0");
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
        g.agregarArista("3", "2", 1);
    }

    private static void grafo4() throws Exception {
        g.agregarVertice("0");
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
        g.agregarArista("4", "3", 1);
    }

    private static void grafo5() throws Exception {
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
