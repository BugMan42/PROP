package Domini;

/**
 * Created by falc on 2/04/15.
 */
public class Girvan_Newman extends Algoritmo{

    @Override
    public Grafo ejecutar_algoritmo(Grafo g) {
        int N = g.obt_num_nodos();
        double[][] a = g.obt_lista_adj();
        boolean done = false;
        while (!done) {
            for (int i = 0; i < N; ++i) {
                //BFS + 'betweenness'
            }

            //eliminar nodo con más 'betweenness'

            /*for (Nodo s : a) {
                //BFS + 'betweenness'
            }*/
        }

        return super.ejecutar_algoritmo(g);
    }
}
