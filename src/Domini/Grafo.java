package Domini;

import java.util.*;

/**
 * Created by bug on 23/03/15.
 */

//Grafo algoritmo
public class Grafo<V, E extends Arista> {

    class AristasNodo {
        private ArrayList<E> entrada;
        private ArrayList<E> salida;

        AristasNodo() {
            entrada = new ArrayList<E>();
            salida = new ArrayList<E>();
        }
    }

    private int Vt;
    private int Et;
    private double total;
    private ArrayList<AristasNodo> ady;
    private TST<Integer> T;      //String to number
    private ArrayList<String> A; //Int to String
    private ArrayList<Integer> vacios; //espacios vacios eficiencia aumentar

    public Grafo() {
        ady = new ArrayList<AristasNodo>();
        T = new TST<Integer>();       //String to number
        A = new ArrayList<String>();  //Int to String
        vacios = new ArrayList<Integer>(); //espacios vacios eficiencia aumentar
        Vt = 0;
        Et = 0;
        total = 0.0;
    }

    public Grafo(Grafo g) {

    }

    //retorna num de vertices
    public int V() {
        return Vt;
    }

    //retorna num de aristas
    public int E() {
        return Et;
    }

    public double total() {
        return total;
    }

    public int degree(int v) {
        //return ady[v].size();
        return 1;
    }

    public ArrayList<Integer> nodosAdyacentes(int a) {
        return new ArrayList<Integer>();
    }
}