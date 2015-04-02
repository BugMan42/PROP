package Domini;

import java.util.Vector;

/**
 * Created by bug on 23/03/15.
 */
public class Grafo {

    private Vector<Nodo> nodos = new Vector<Nodo>();
    private Vector<Arista> aristas = new Vector<Arista>();

    public Grafo() {}

    public Grafo(Grafo G) {} //CrearGrafo()

    public double[][] obt_lista_adj() {
        double[][] lista_adj = new double[nodos.size()][nodos.size()];

        for(int i = 0; i < nodos.size(); i++)
            for(int j = 0; j < nodos.size(); j++)
                if(i == j)
                    lista_adj[i][j] = 0;
                else
                    lista_adj[i][j] = Double.POSITIVE_INFINITY;

        for(int i = 0; i < nodos.size(); i++) {
            Nodo nodo = nodos.elementAt(i);

            for(int j = 0; j < aristas.size(); j++) {
                Arista arista = aristas.elementAt(j);

                if(arista.obt_origen() == nodo) {
                    int indexFin = nodos.indexOf(arista.obt_fin());

                    lista_adj[i][indexFin] = arista.obt_peso();
                }
            }
        }

        return lista_adj;
    }

    public void add_vertice() {}//Añadirvertice(vértice a) /*aqui falta el vertice si es generico etc*/
    public void add_arista() {}//AñadirArista(vértice a, vértice b, int peso)
    public void mod_arista() {}//ModificarArista(vértice a, vértice b, int nuevo_peso)
    public void add_peso() {}//Añadirpeso(vértice a, vértice b, int peso)
    public void eliminar_arista() {} //Eliminararista(vértice a, vértice b)
    public void eliminar_vertice() {}//(vértice a)
    public void eliminar() {}


}
