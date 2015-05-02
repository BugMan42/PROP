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
        print(" 0 Grafo()");
        print(" 1 Grafo(Grafo g)");
        print(" 2 V():int");
        print(" 3 vacio():boolean");
        print(" 4 f(String clave):int");
        print(" 5 fPrima(int nodo):String");
        print(" 6 agregarVertice(String v)");
        print(" 7 eliminarVertice(String v)");
        print(" 8 eliminarVertice(int v)");
        print(" 9 consultarVertices():ArrayList<Integer>");
        print("10 consultarVerticesID():ArrayList<String>");
        print("11 existeVertice(String v):boolean");
        print("12 existeVertice(int v):boolean");
        print("13 modificarClaveVertice(String idVieja, String idNueva)");
        print("14 agregarArista(String origen,String fin, double peso)");
        print("15 agregarArista(int origen,int fin, double peso)");
        print("16 modificarArista(String origen, String fin,double oldPeso," +
                " double newPeso)");
        print("17 modificarArista(int origen, int fin,double oldPeso," +
                " double newPeso)");
        print("18 eliminarArista(String origen, String fin, double peso)");
        print("19 eliminarArista(int origen,int fin,double peso)");
        print("20 eliminarAristas(String origen,String fin)");
        print("21 eliminarAristas(int origen,int fin)");
        print("22 degreeEntrada(String v):int");
        print("23 degreeEntrada(int v):int");
        print("24 degreeSalida(String v):int");
        print("25 degreeSalida(int v):int");
        print("26 existeArista(String A, String B):boolean");
        print("27 existeArista(int origen, int fin):boolean");
        print("28 existeAristaPeso(String origen, String fin, double peso):boolean");
        print("29 existeAristaPeso(int origen, int fin, double peso):boolean");
        print("30 totalPesoEntrada(String A):double");
        print("31 totalPesoEntrada(int A):double");
        print("32 totalPesoSalida(String A):double");
        print("33 totalPesoSalida(int A):double");
        print("34 pesoAristasVertices(String origen, String fin):double");
        print("35 pesoAristasVertices(int origen, int fin):double");
        print("36 obtenerListaPesos(String A, String B):List<Double>");
        print("37 obtenerListaPesos(int A, int B):List<Double>");
        print("38 nodosEntrada(String A):List<Integer> ");
        print("39 nodosEntrada(int A):List<Integer> ");
        print("40 nodosSalida(String A):List<Integer>");
        print("41 nodosSalida(int A):List<Integer>");
        print("42 Salir");
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
                print(""+g.vacio());
                break;
            case 4:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.f(aux[1]));
                break;
            case 5:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(g.fPrima(Integer.parseInt(aux[1])));
                break;
            case 6:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                g.agregarVertice(aux[1]);
                break;
            case 7:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                g.eliminarVertice(aux[1]);
                break;
            case 8:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                g.eliminarVertice(Integer.parseInt(aux[1]));
                break;
            case 9:
                print(g.consultarVertices().toString());
                break;
            case 10:
                print(g.consultarVerticesID().toString());
                break;
            case 11:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(g.existeVertice(aux[1])+"");
                break;
            case 12:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(g.existeVertice(Integer.parseInt(aux[1]))+"");
                break;
            case 13:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                g.modificarClaveVertice(aux[1], aux[2]);
                break;
            case 14:
                if (aux.length < 4) throw new InsuficientesArgumentos();
                g.agregarArista(aux[1], aux[2], Double.parseDouble(aux[3]));
                break;
            case 15:
                if (aux.length < 4) throw new InsuficientesArgumentos();
                g.agregarArista(Integer.parseInt(aux[1]), Integer.parseInt(aux[2]), Double.parseDouble(aux[3]));
                break;
            case 16:
                if (aux.length < 5) throw new InsuficientesArgumentos();
                g.modificarArista(aux[1], aux[2], Double.parseDouble(aux[3]), Double.parseDouble(aux[4]));
                break;
            case 17:
                if (aux.length < 5) throw new InsuficientesArgumentos();
                g.modificarArista(Integer.parseInt(aux[1]), Integer.parseInt(aux[2]), Double.parseDouble(aux[3]), Double.parseDouble(aux[4]));
                break;
            case 18:
                if (aux.length < 4) throw new InsuficientesArgumentos();
                g.eliminarArista(aux[1], aux[2], Double.parseDouble(aux[3]));
                break;
            case 19:
                if (aux.length < 4) throw new InsuficientesArgumentos();
                g.eliminarArista(Integer.parseInt(aux[1]), Integer.parseInt(aux[2]), Double.parseDouble(aux[3]));
                break;
            case 20:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                g.eliminarAristas(aux[1], aux[2]);
                break;
            case 21:
                if (aux.length < 4) throw new InsuficientesArgumentos();
                g.eliminarAristas(Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));
                break;
            case 22:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print("" + g.degreeEntrada(aux[1]));
                break;
            case 23:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print("" + g.degreeEntrada(Integer.parseInt(aux[1])));
                break;
            case 24:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.degreeSalida(aux[1]));
                break;
            case 25:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.degreeSalida(Integer.parseInt(aux[1])));
                break;
            case 26:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                print(g.existeArista(aux[1], aux[2]) + "");
                break;
            case 27:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                print(g.existeArista(Integer.parseInt(aux[1]), Integer.parseInt(aux[2]))+"");
                break;
            case 28:
                if (aux.length < 4) throw new InsuficientesArgumentos();
                print(""+g.existeAristaPeso(aux[1],aux[2],Double.parseDouble(aux[3])));
                break;
            case 29:
                if (aux.length < 4) throw new InsuficientesArgumentos();
                print("" + g.existeAristaPeso(Integer.parseInt(aux[1]), Integer.parseInt(aux[2]), Double.parseDouble(aux[3])));
                break;
            case 30:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.totalPesoEntrada(aux[1]));
                break;
            case 31:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.totalPesoEntrada(Integer.parseInt(aux[1])));
                break;
            case 32:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.totalPesoSalida(aux[1]));
                break;
            case 33:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.totalPesoSalida(Integer.parseInt(aux[1])));
                break;
            case 34:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                print(""+g.pesoAristasVertices(aux[1], aux[2]));
                break;
            case 35:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                print("" + g.pesoAristasVertices(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
                break;
            case 36:
                if (aux.length < 3 ) throw new InsuficientesArgumentos();
                print(g.obtenerListaPesos(aux[1],aux[2]).toString());
                break;
            case 37:
                if (aux.length < 3) throw new InsuficientesArgumentos();
                print(g.obtenerListaPesos(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])).toString());
                break;
            case 38:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.nodosEntrada(aux[1]));
                break;
            case 39:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.nodosEntrada(Integer.parseInt(aux[1])));
                break;
            case 40:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.nodosSalida(aux[1]));
                break;
            case 41:
                if (aux.length < 2) throw new InsuficientesArgumentos();
                print(""+g.nodosSalida(Integer.parseInt(aux[1])));
                break;
            case 42:
                System.exit(0);
            default:
                print("Fuera de rango");
                break;
        }
    }
}
