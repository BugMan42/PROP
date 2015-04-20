package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by falc on 2/04/15.
 */
public class Girvan_Newman extends Algoritmo{

    class Node {
        boolean visited;
        boolean queued;
        boolean leaf;
        int parent[];
        double weight;
        double distance;
        double down_total;
    }

    ArrayList<Node> node;

    @Override
    public Grafo ejecutar_algoritmo(Grafo g) {

        return super.ejecutar_algoritmo(g);
    }


    @Override
    public Grafo ejecutar_iteración(Grafo g) {
        int N = g.V();
        Grafo gaux = new Grafo(g);

        node = new ArrayList<Node> (N);
        Arista victim; // La arista que se irá

        //BFS por cada nodo del grafo
        for (int i = 0; i < N; ++i)
        {
            LinkedList<Integer> route = new LinkedList<Integer>();
            LinkedList<Integer> q = new LinkedList<Integer>();
            LinkedList<Integer> leaf_node = new LinkedList<Integer>();

            //Inicializar los nodos
            for (Node n : node) {
                n.visited = false;
                n.leaf = false;
                n.distance = Double.POSITIVE_INFINITY;
                n.parent[0] = -1;
                n.weight = 0;
            }

            q.addLast(i);
            node.get(i).distance = 0;
            node.get(i).weight = 1;

            while(!q.isEmpty())
            {
                int v = q.removeFirst();
                route.add(v);
                Node ref_v = node.get(v);
                ref_v.visited = true;
                int leaf_index = 0;
                /*for (int aux : g.nodosAdyacentes(v)) {

                    Node ref_aux = node.get(aux);

                    if (!ref_aux.visited)
                    {
                        if (ref_aux.distance == 0)
                        {
                            ref_aux.distance = ref_v.distance + 1;
                            ref_aux.weight = ref_v.weight;
                        }
                        else if (ref_aux.distance == ref_v.distance+1)
                        {
                            ref_aux.weight += ref_v.weight;
                        }

                        leaf_index += 1;
                        int parent_length = ref_aux.parent.length;
                        ref_aux.parent[parent_length] = v;

                        if (!ref_aux.queued) {
                            q.addLast(aux);
                            ref_aux.queued = true;
                        }
                    }

                }*/

                if (leaf_index == 0) {
                    ref_v.leaf = true;
                    ref_v.down_total = 0;
                    leaf_node.add(v);
                }
            }

            //Pesos en grafo
            for (int p : route)
            {
                Node golf = node.get(p);
                System.out.println(p+": "+ Arrays.toString(golf.parent));
                for (int inode : golf.parent) {

                    //double multiplier = V(inode).weigth/V(golf).weigth
                    //double myWeight = (1 + inode.down_total) * multiplier;
                    // A(inode,golf) += myWeight;
                    // down_total += myWeight;

                    //Si victim.peso < a myWeight, victim = A(inode, golf);

                }

            }


        }

        //eliminar victim


        return super.ejecutar_iteración(g);
    }
}
