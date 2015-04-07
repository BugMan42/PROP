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

            double[][] bfsmatrix = new double[N][N];

            for (int i = 0; i < N; ++i)
            {

                boolean[] visited = new boolean[N];
                double[] node_parent = new double[N];
                double[] node_weigth = new double[N];
                double[] node_distance = new double[N];
                LinkedList<Integer> q = new LinkedList<Integer>();

                for(int j = 0; j < N; ++j)
                {
                    visited[j] = false;
                    node_distance[j] = Double.POSITIVE_INFINITY;
                    node_parent[j] = Double.parseDouble(null);
                }

                q.addLast(i);
                visited[i] = true;
                node_distance[i] = 0;
                node_weigth[i] = 1;

                while(!q.isEmpty())
                {
                    int v = q.removeFirst();
                    for (int aux : g.nodosAdyacentes(v)) {
                        if (!visited[aux])
                        {
                            visited[aux] = true;
                            node_distance[aux] = node_distance[v] + 1;
                            node_weigth[aux] = node_weigth[v];
                            node_parent[aux] = v;
                            q.addLast(aux);
                        }
                    }


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
