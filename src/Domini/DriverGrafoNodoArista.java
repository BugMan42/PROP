package Domini;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bug on 17/06/15.
 */
public class DriverGrafoNodoArista {
    private static  class Nodo extends Node {
        private String id;

        public Nodo(String id2) {
            id = id2;
        }

        @Override
        public String obtID() {
            return id;
        }

        @Override
        public Node clone() {
            return new Nodo(id);
        }
    }
    private static GrafoNodoArista<Nodo,Edge<Nodo>> g;

    public static class ArgumentosInsuficientes extends Exception {
        public ArgumentosInsuficientes() {
            super("Argumentos Insuficientes");
        }
    }

    private static void print(String str) {
        System.out.println(str);
    }

    private static void print(List<Nodo> aux) {
        for (Nodo a:aux) {
            print(a.obtID());
        }
    }
    private static void print2(List<Edge<Nodo>> aux) {
        for (Edge a:aux) {
            print(a.obtOrigen()+" "+a.obtDestino()+" "+a.obtPeso());
        }
    }

    private static void PresentaMenu() {
        print("\nDRIVER DE GRAFO ");
        print("0 Grafo()");
        print("1 V():int");
        print("2 vacio():boolean");
        print("3 agregarVertice(Nodo a)");
        print("4 eliminarVertice(Nodo b)");
        print("5 ModificarVetice(Nodo new, Nodo old)");
        print("6 ConsultarVertices():List<Nodo>");
        print("7 existeVertice(Nodo a):boolean");
        print("8 agregarArista(E e(Nodo orig, Nodo fin, double peso))");
        print("9 eliminarAristas(V origen, V fin)");
        print("10 eliminarArista(E e(Nodo orig, Nodo fin, double peso)");
        print("11 modificarArista(E e, double newPeso)");
        print("12 obtenerAristas(V origen, V fin):List<Edges>");
        print("13 obtenerPesoSalida(V origen):double");
        print("14 obtenerPesoEntrada(V fin):double");
        print("15 pesoAristasVertice(V origen1, V fin1):double ");
        print("16 existeAlgunaArista(V A, V B):boolean");
        print("17 existeArista(E e):boolean");
        print("18 degreeEntrada(V v):int");
        print("19 degreeSalida(V v):int");
        print("20 nodosSalida(V v):List<Nodo>");
        print("21 nodosEntrada(V v):List<Nodo>");
        print("22 obtenerAristas():List<E>");
        print("23 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        print("Para ser más fácil, Nodo es una clase de tipo Node \nCuando aparezca Nodo--> hay que poner la string y \ncuando aparezca E string string y peso");
        boolean imprimir = true;
        if (args.length > 0) {
            if (args[0].equals("0")) imprimir = false;
        }
        g = new GrafoNodoArista<Nodo, Edge<Nodo>>();
        Scanner userInput = new Scanner(System.in);
        if (imprimir) PresentaMenu();
        do {
            //PresentaMenu();
            try {
                ProcesarLinea(LeerLinea(userInput),imprimir);
            } catch (Exception a) {
                print(a.getMessage());
            }
            if (imprimir) PresentaMenu();
        } while(userInput.hasNextLine());
    }

    private static void ProcesarLinea(String str, boolean imprimir) throws Exception {
        if (!imprimir) print("E: "+str);
        String aux[] = str.split("\\s");
        System.out.print("S: ");
        if (str.length() == 0) throw new ArgumentosInsuficientes();
        switch (Integer.parseInt(aux[0])) {
            case 0:
                g = new GrafoNodoArista<Nodo, Edge<Nodo>>();
                break;
            case 1:
                print(""+g.V());
                break;
            case 2:
                print(""+g.vacio());
                break;
            case 3:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                g.agregarVertice(new Nodo(aux[1]));
                break;
            case 4:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                g.eliminarVertice(new Nodo(aux[1]));
                break;
            case 5:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                g.modificarVertice(new Nodo(aux[1]),new Nodo(aux[2]));
                break;
            case 6:
                print(g.consultarVertices());
                break;
            case 7:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(""+g.existeVertice(new Nodo(aux[1])));
                break;
            case 8:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                g.agregarArista(new Edge<Nodo>(new Nodo(aux[1]),new Nodo(aux[2]),Double.parseDouble(aux[3])));
                break;
            case 9:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                g.eliminarAristas(new Nodo(aux[1]), new Nodo(aux[2]));
                break;
            case 10:
                if (aux.length < 4) throw new ArgumentosInsuficientes();
                g.eliminarArista(new Edge<Nodo>(new Nodo(aux[1]), new Nodo(aux[2]), Double.parseDouble(aux[3])));
                break;
            case 11:
                if (aux.length < 5) throw new ArgumentosInsuficientes();
                g.modificarArista(new Edge<Nodo>(new Nodo(aux[1]), new Nodo(aux[2]), Double.parseDouble(aux[3])),Double.parseDouble(aux[4]));
                break;
            case 12:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print2(g.obtenerAristas(new Nodo(aux[1]), new Nodo(aux[2])));
                break;
            case 13:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(""+g.obtenerPesoSalida(new Nodo(aux[1])));
                break;
            case 14:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(""+g.obtenerPesoEntrada(new Nodo(aux[1])));
                break;
            case 15:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print(""+g.pesoAristasVertice(new Nodo(aux[1]), new Nodo(aux[2])));
                break;
            case 16:
                if (aux.length < 3) throw new ArgumentosInsuficientes();
                print(""+g.existeAlgunaArista(new Nodo(aux[1]), new Nodo(aux[2])));
                break;
            case 17:
                if (aux.length < 5) throw new ArgumentosInsuficientes();
                print(""+g.existeArista(new Edge<Nodo>(new Nodo(aux[1]), new Nodo(aux[2]), Double.parseDouble(aux[3]))));
                break;
            case 18:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(g.degreeEntrada(new Nodo(aux[1]))+"");
                break;
            case 19:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(g.degreeSalida(new Nodo(aux[1]))+"");
                break;
            case 20:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(g.nodosSalida(new Nodo(aux[1])));
                break;
            case 21:
                if (aux.length < 2) throw new ArgumentosInsuficientes();
                print(g.nodosEntrada(new Nodo(aux[1])));
                break;
            case 22:
               print2(g.obtenerAristas());
                break;
            case 23:
                System.exit(0);
            default:
                print("Fuera de rango");
                break;
        }
    }

}
