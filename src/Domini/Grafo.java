package Domini;

/**
 * Created by bug on 23/03/15.
 */
public class Grafo {
    int A;
    public Grafo() {
        A = 0;
    }
    public Grafo(Grafo G) {
        A = 1;
    } //CrearGrafo()
    public void obt_lista_adj() {}//Obtenerlistao():lista_adj
    public void add_vertice() {}//Añadirvertice(vértice a) /*aqui falta el vertice si es generico etc*/
    public void add_arista() {}//AñadirArista(vértice a, vértice b, int peso)
    public void mod_arista() {}//ModificarArista(vértice a, vértice b, int nuevo_peso)
    public void add_peso() {}//Añadirpeso(vértice a, vértice b, int peso)
    public void eliminar_arista() {} //Eliminararista(vértice a, vértice b)
    public void eliminar_vertice() {}//(vértice a)
    public void eliminar() {}


}
