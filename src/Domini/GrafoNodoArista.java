package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class GrafoNodoArista<V extends Node, E extends Edge> {

    private class Edges {

       /* public V obtOrigen() {
        }
        public V obtFin() {
        }*/
    }
    // entrada = antecesores(las aristas que entran al nodo) ---> Nodo
    // salida = sucesores(las aristas que salen del nodo) Nodo ---->
    private class EdgesVertex {
        VertexTst map;
        ArrayList<Edges> entrada; // aristas entrada
        ArrayList<Edges> salida;  // aristas salida
        public EdgesVertex(VertexTst aux) {
            map = aux;
            entrada = new ArrayList<Edges>();
            salida = new ArrayList<Edges>();
        }
        public void addAristaEntrada(Edge e) {
            for (int i = 0; i < entrada.size(); ++i) {
                /*entrada.get
                if (entrada.get(i).obtOrigen().obtID().equals(e.obtOrigen().obtID()) {
                    entrada.get(i)
                }*/
            }
        }
        public void addAristaSalida(Edge e) {

        }

    }
    private class VertexTst {
        V v;
        int map;
        public VertexTst(V v1, int map1) {
            v = v1;
            map = map1;
        }
        public void modV(V v1) {
            v = v1;
        }
        public void modMap(int map1) {
            map = map1;
        }
        public V obtV() {
            return v;
        }
        public int obtMap() {
            return map;
        }

    }

    TST<VertexTst> tst;
    ArrayList<EdgesVertex> listaAdy;

    public GrafoNodoArista() {
        tst = new TST<VertexTst>();
    }
    public void addNodo(V v) throws Exception {
        int aux = obtNext();
        VertexTst a = new VertexTst(v,aux);
        tst.insertar(v.obtID(), a);
        listaAdy.set(aux,new EdgesVertex(a));

    }
    public int obtNext() {
        ArrayList<V> a = new ArrayList<V>();
        ArrayList<V> b = (ArrayList<V>)a.clone();
        return 1;
    }


    //Pruebas
    public void printNodos() {
        print(tst.consultarClaves()+"");
    }
    public void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) throws Exception{
        GrafoNodoArista<Congresista,Edge> a = new GrafoNodoArista<Congresista,Edge>();
        Congresista C1 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        a.addNodo(C1);
        a.printNodos();
    }
}
