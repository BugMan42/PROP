package Domini;

import java.util.LinkedList;

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

            //BFS por cada nodo del grafo
            for (int i = 0; i < N; ++i)
            {
                LinkedList<Integer> route = new LinkedList<Integer>();
                boolean[] visited = new boolean[N];
                boolean[] leaf = new boolean[N];
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
                    route.add(v);
                    int num_ady = 0;
                    for (int aux : g.nodosAdyacentes(v)) {

                        ++num_ady;

                        if (!visited[aux])
                        {
                            visited[aux] = true;
                            node_distance[aux] = node_distance[v] + 1;
                            node_weigth[aux] = node_weigth[v];
                            node_parent[aux] = v;
                            q.addLast(aux);
                        }

                    }

                    if (num_ady == 0) leaf[v] = true;
                }

                //Pesos en grafo
                for (int p : route)
                {
                    System.out.println(p);
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
