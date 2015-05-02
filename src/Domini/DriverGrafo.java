package Domini;

import java.util.ArrayList;
import java.util.*;

public class DriverGrafo {
    private static Scanner user_input = new Scanner(System.in);
    private static Grafo g;

    private static void print(String str) {
        System.out.println(str);
    }

    private static void PresentaMenu() {
        print("\nDRIVER DE GRAFO ");
        print("0 Grafo()");
        print("1 Grafo(Grafo g)");
        print("2 V():int");
        print("3 f(String clave):int");
        print("4 fPrima(int nodo):String");
        print("5 agregarVertice(String v)");
        print("6 eliminarVertice(String v)");
        print("7 eliminarVertice(int v)");
        print("8 consultarVertices():ArrayList<Integer>");
        print("9 consultarVerticesID():ArrayList<String>");
        print("10 existeVertice(String v):boolean");
        print("11 existeVertice(int v):boolean");
        print("12 modificarVertice(String idVieja, String idNueva)");
        print("13 agregarArista(String origen,String fin, double peso)");
        print("14 agregarArista(int origen,int fin, double peso)");
        print("15 modificarArista(int origen, int fin,double oldPeso," +
                " double newPeso)");
        print("16 eliminarAristas(String origen,String fin)");
        print("17 eliminarAristas(int origen,int fin)");
        print("18 eliminarArista(String origen, String fin, double peso)");
        print("19 eliminarArista(int origen,int fin,double peso)");
        print("20 existeArista(String A, String B):boolean");
        print("21 existeArista(int origen, int fin):boolean");
        print("22 existeAristaPeso(int origen, int fin, double peso):boolean");
        print("23 obtenerListaPesos(String A, String B):List<Double>");
        print("24 obtenerListaPesos(int A, int B):List<Double>");
        print("25 totalPesoSalida(String A):double");
        print("26 totalPesoSalida(int A):double");
        print("27 totalPesoEntrada(String A):double");
        print("28 totalPesoEntrada(int A):double");
        print("39 pesoAristasVertices(int origen, int fin):double");
        print("30 nodosSalida(String A):List<Integer>");
        print("31 nodosSalida(int A):List<Integer>");
        print("32 nodosEntrada(String A):List<Integer> ");
        print("33 nodosEntrada(int A):List<Integer> ");
        print("34 vacio():boolean");
        print("35 degreeEntrada(String v):int");
        print("36 degreeEntrada(int v)");
        print("37 degreeSalida(String v)");
        print("38 degreeSalida(int v)");
        print("39 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        g = new Grafo();
        Scanner userInput = new Scanner(System.in);
        boolean seguir = true;
        PresentaMenu();
        do {
            //PresentaMenu();
            try {
                ProcesarLinea(LeerLinea(userInput));
            } catch (Exception a) {
                print(a.getMessage());
            }
            PresentaMenu();
        } while(userInput.hasNextLine());
    }

    private static void ProcesarLinea(String str) throws Exception {
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new InsuficientesArgumentos();
        switch (Integer.parseInt(aux[0])) {
            case 0:
                g = new Grafo();
                break;
            case 1:
                //TODO
                break;
            case 2:
                print(""+g.V());
                break;
            case 3:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.f(aux[1]));
                break;
            case 4:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(g.fPrima(Integer.parseInt(aux[1])));
                break;
            case 5:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                g.agregarVertice(aux[1]);
                break;
            case 6:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                g.eliminarVertice(aux[1]);
                break;
            case 7:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                g.eliminarVertice(Integer.parseInt(aux[1]));
                break;
            case 8:
                print(g.consultarVertices().toString());
                break;
            case 9:
                print(g.consultarVerticesID().toString());
                break;
            case 10:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(g.existeVertice(aux[1])+"");
                break;
            case 11:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(g.existeVertice(Integer.parseInt(aux[1]))+"");
                break;
            case 12:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 13:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 14:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 15:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 16:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 17:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 18:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 19:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 20:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 21:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 22:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 23:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 24:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 25:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 26:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 27:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 28:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 29:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 30:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 31:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 32:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 33:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 34:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 35:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 36:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 37:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                break;
            case 38:
                System.exit(0);
                break;
            default:
                print("Fuera de rango");
                break;
        }
    }
}
