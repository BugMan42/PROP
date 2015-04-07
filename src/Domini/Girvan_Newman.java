package Domini;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by falc on 2/04/15.
 */
public class Girvan_Newman extends Algoritmo{

    @Override
    public Grafo ejecutar_algoritmo(Grafo g) {
        int N = g.obt_num_nodos();
        double[][] a = g.obt_lista_ady();
        boolean done = false;
        while (!done) {

            for (int i = 0; i < N; ++i) {

                boolean[] visited = new boolean[N];
                double[][] bfsmatrix = new double[N][N];
                double[] node_weigth = new double[N];
                double[] node_distance = new double[N];
                LinkedList<Integer> q = new LinkedList<Integer>();

                q.addLast(i);
                node_distance[i] = 0;
                node_weigth[i] = 1;

                while(!q.isEmpty())
                {
                    int v = q.removeFirst();
                    //para nodos adyacentes a v
                }

            }

            //eliminar nodo con más 'betweenness'

            /*for (Nodo s : a) {
                //BFS + 'betweenness'
            }*/
        }

        return super.ejecutar_algoritmo(g);
    }
}
