package Domini;

import java.util.*;

/**
 * Created by bug on 23/03/15.
 */

//Grafo algoritmo
public class Grafo {
    private final int V;
    private int E;
    private double total;
    private List<Congresista> C;
    private List<Arista>[] ady;

    public Grafo() {
        V = 100;
    }

    public Grafo(int Vertices) {
        //if (V < 0) throw new IllegalArgumentException("Numero de vertices");
        V = Vertices;
        E = 0;
        total = 0;
        ady = (List<Arista>[]) new List[V];
        for (int v = 0; v < V; v++) {
            ady[v] = new LinkedList<Arista>();
        }
    }

    //TODO
    public Grafo(Grafo G) {
        V = G.V();
        E = G.E();
        total = G.total();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adyacency list is in same order as original
            Stack<Arista> reverse = new Stack<Arista>();
            for (Arista w : G.ady[v]) {
                reverse.push(w);
            }
            for (Arista w : reverse) {
                ady[v].add(w);
            }
        }
    }

    //retorna num de vertices
    public int V() {
        return V;
    }

    //retorna num de aristas
    public int E() {
        return E;
    }

    public double total() {
        return total;
    }

    public void addEdge(Arista A) {
        E++;
        total += A.peso();
        //ady[A.obt_origen()].add(A);
        //ady[A.obt_fin()].add(A);
    }



    //retorna lista de ady de un vettice con numero v
    public List<Arista> ady(int v) {
        return ady[v];
    }

    public int degree(int v) {
        return ady[v].size();
    }
    public ArrayList<Integer> nodosAdyacentes(int a) {
        return new ArrayList<Integer>();
    }
}
    /*public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : ady[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }*/


/*
    private Vector<Nodo> nodos = new Vector<Nodo>();
    private Vector<Arista> aristas = new Vector<Arista>();

    public Grafo() {}

    public Grafo(Grafo G) {} //CrearGrafo()

    public double[][] obt_lista_ady() {
        double[][] lista_ady = new double[nodos.size()][nodos.size()];

        for(int i = 0; i < nodos.size(); i++)
            for(int j = 0; j < nodos.size(); j++)
                if(i == j)
                    lista_ady[i][j] = 0;
                else
                    lista_ady[i][j] = Double.POSITIVE_INFINITY;

        for(int i = 0; i < nodos.size(); i++) {
            Nodo nodo = nodos.elementAt(i);

            for(int j = 0; j < aristas.size(); j++) {
                Arista arista = aristas.elementAt(j);

                if(arista.obt_origen() == nodo) {
                    int indexFin = nodos.indexOf(arista.obt_fin());

                    lista_ady[i][indexFin] = arista.obt_peso();
                }
            }
        }

        return lista_ady;
    }

    public ArrayList<Integer> nodosAdyacentes(int a) {
        return new ArrayList<Integer>();
    } //Devuelve los nodos (o aristas) adyacentes a un determinado nodo

    // Devuelve el número de nodos después de añadir.
    public int add_nodo(Nodo a){
        nodos.add(a);
        return nodos.size()-1;
    }

    public int obt_num_nodos()
    {
        return nodos.size();
    }

    public void add_arista(Arista a){
        aristas.add(a);
        // Esto tal vez hay que cambiarlo.
        // Cuando añades una arista tambien se añade la de vuelta (de fin a origen)
        // ya que no es un grafo dirigido.
        aristas.add(new Arista(a.obt_fin(), a.obt_origen(), a.obt_peso()));
    }

    public void mod_arista() {}//ModificarArista(vértice a, vértice b, int nuevo_peso)
    public void add_peso() {}//Añadirpeso(vértice a, vértice b, int peso)
    public void eliminar_arista() {} //Eliminararista(vértice a, vértice b)
    public void eliminar_vertice() {}//(vértice a)
    public void eliminar() {}

    public void printNodos() {
        System.out.println(nodos);
    }

    public void printAristas() {
        System.out.println(aristas);
    }


 */