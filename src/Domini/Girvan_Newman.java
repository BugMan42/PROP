package Domini;

import java.util.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by falc on 2/04/15.
 */
public class Girvan_Newman extends Algoritmo{


    //protected Entrada in
    //protected Salida out
    private Grafo data_graph;
    private Grafo alg_graph;
    private int alg_cc;

    class Node {
        boolean visited;
        int component;
        boolean leaf;
        int parent[];
        double weight;
        double distance;
        double down_total;
    }

    class Eix {
        int orig;
        double weight;
        int dest;
    }

    class AristaComparator implements Comparator<Arista> {
        @Override
        public int compare(Arista o1, Arista o2) {

            return (int) (o1.peso()-o2.peso());
        }
    }

    ArrayList<Node> node;

    public Girvan_Newman(Entrada i, Salida o)
    {
        super(i, o);
    }

    @Override
    public Grafo ejecutar_algoritmo() {
        data_graph = in.obtGrafo();
        int limit = (int) in.obtParam1();
        while(alg_cc < limit) alg_graph = ejecutar_iteración(alg_graph);
        return alg_graph;
    }


    @Override
    public Grafo ejecutar_iteración(Grafo g) {
        int N = g.V(); //Número de vértices del grafo
        int cc = 0; //Número componentes conexos

        node = new ArrayList<Node> (N);

        Eix victim = new Eix(); // La arista que se irá
        victim.weight = -1;
        //Comparador para aristas
        AristaComparator comp = new AristaComparator();

        //Dijkstra por cada nodo del grafo
        for (int i = 0; i < N; ++i)
        {
            //Lista para mantener guardada la ruta realizada durante Dijkstra
            LinkedList<Integer> route = new LinkedList<Integer>();
            //Cola de prioridades para aristas
            PriorityQueue<Arista<Integer>> Q = new PriorityQueue<Arista<Integer>>(N, comp);

            //Inicializar los nodos
            for (Node n : node) {
                n.visited = false;
                n.component = -1;
                n.leaf = false;
                n.distance = Double.POSITIVE_INFINITY;
                n.parent[0] = -1;
                n.weight = 0;
            }

            Q.add(new Arista<Integer>(i, 0));

            Node uno = node.get(i);
            uno.distance = 0;
            uno.weight = 1;
            //Si no pertanece el nodo a un componente conexo, sumamos 1 a cc y le asignamos un componente al nodo
            if (uno.component == -1)
            {
                ++cc;
                uno.component = cc;
            }

            //Mientras la cola no esté vacía
            while(!Q.isEmpty())
            {
                Arista<Integer> a = Q.poll();
                int v = a.fin();

                Node ref_v = node.get(v);
                if (!ref_v.visited) {
                    ref_v.visited = true;
                    ref_v.component = cc;
                    route.add(v);
                    int leaf_index = 0;
                    ArrayList<Integer> al = g.nodosAdyacentes(v);
                    for (int aux : al) { //Para todos los nodos adyacentes a V[v]

                        Node ref_aux = node.get(aux);
                        double dist = data_graph.pesoAristaVertices(v, aux); //dist es el peso de v -> aux
                        if (!ref_aux.visited) {
                            if (ref_aux.distance == 0) {
                                ref_aux.distance = ref_v.distance + dist;
                                ref_aux.weight = ref_v.weight;
                            } else if (ref_v.distance > ref_aux.distance + dist){
                                ref_v.distance = ref_aux.distance + dist;
                                int parent_length = ref_aux.parent.length;
                                ref_aux.parent[parent_length] = v;

                            } else if (ref_aux.distance == ref_v.distance + dist) {
                                ref_aux.weight += ref_v.weight;
                                int parent_length = ref_aux.parent.length;
                                ref_aux.parent[parent_length] = v;
                            }

                            leaf_index += 1;
                            int parent_length = ref_aux.parent.length;
                            ref_aux.parent[parent_length] = v;


                            Q.add(new Arista<Integer>(aux, dist));

                        }

                    }

                    if (leaf_index == 0) {
                        ref_v.leaf = true;
                        ref_v.down_total = 0;
                    }
                }

            }

            //Pesos en grafo
            for (int p : route)
            {
                Node golf = node.get(p);
                if (golf.parent[0] != -1) {
                    System.out.println(p + ": " + Arrays.toString(golf.parent));
                    for (int inode : golf.parent) {

                        Node up = node.get(inode);
                        double multiplier = up.weight/golf.weight;
                        double myWeight = (1 + golf.down_total) * multiplier / data_graph.pesoAristaVertices(inode, p);
                        double rel = g.pesoAristaVertices(inode, p) + myWeight;
                        g.modPesoAristaVertices(inode, p, rel);
                        up.down_total += myWeight;

                        if (victim.weight < myWeight) {
                            victim.orig = inode;
                            victim.dest = p;
                            victim.weight = myWeight;
                        }

                    }
                }

            }


        }

        //alg_graph.eliminarArista

        alg_cc = cc;
        return super.ejecutar_iteración(g);
    }



}

