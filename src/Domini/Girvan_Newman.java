package Domini;

import java.util.ArrayList;

/**
 * Created by falc on 2/04/15.
 */
public class Girvan_Newman extends Algoritmo{

    @Override
    public Grafo ejecutar_algoritmo(Grafo g) {
        ArrayList<Nodo> a = g.obt_lista_vert();
        boolean done = false;
        while (!done) {
            for (Nodo s : a) {
                //BFS + 'betweenness'
            }

            //eliminar nodo con más 'betweenness'

            for (Nodo s : a) {
                //BFS + 'betweenness'
            }
        }

        return super.ejecutar_algoritmo(g);
    }
}
