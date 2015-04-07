package Domini;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by bug on 23/03/15.
 */
public class Grafo {

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


}
