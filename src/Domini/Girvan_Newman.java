package Domini;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.*;

/**
 * Created by falc on 2/04/15.
 */
public class Girvan_Newman extends Algoritmo{


    //protected Entrada in
    //protected Salida out
    private Grafo data_graph;
    private Grafo alg_graph;
    private int alg_cc;

    private class Node {
        boolean visited;
        int component;
        boolean leaf;
        ArrayList<Integer> parent;
        double weight;
        double distance;
        double down_total;

        Node() {
            visited = false;
            component = -1;
            leaf = false;
            parent = new ArrayList<Integer>(1);
            distance = Double.POSITIVE_INFINITY;
            weight = 0;
            down_total = 0;
        }
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

    public Girvan_Newman(Entrada i, Salida o) throws Exception
    {
        super(i, o);
        data_graph = in.obtGrafo();
        alg_graph = new Grafo(data_graph);
    }

    @Override
    public Grafo ejecutar_algoritmo() throws Exception {

        int limit = (int) in.obtParam1();
        while(alg_cc < limit) alg_graph = ejecutar_iteración(alg_graph);
        return alg_graph;
    }


    public void ejecutar_iteración() throws Exception {
        alg_graph = ejecutar_iteración(alg_graph);
    }

    @Override
    public Grafo ejecutar_iteración(Grafo g) throws Exception {
        int N = g.V(); //Número de vértices del grafo
        int cc = 0; //Número componentes conexos
        int cc2 = 0; //Número de componentes conexos en la segunda iteración
        node = new ArrayList<Node> (N);
        for (int ii = 0; ii < N; ++ii) {
            Node n = new Node();
            n.visited = false;
            n.component = -1;
            n.leaf = false;
            n.distance = Double.POSITIVE_INFINITY;
            n.parent.clear();
            n.weight = 0;
            n.down_total = 0;
            node.add(n);
        }


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
            //node.clear();
            for (int j = 0; j < N; ++j) {
                Node n = node.get(j);
                n.visited = false;
                //n.component = -1;
                n.leaf = false;
                n.distance = Double.POSITIVE_INFINITY;
                n.parent.clear();
                n.weight = 0;
                n.down_total = 0;
            }

            Q.add(new Arista<Integer>(i, 0));

            System.out.println("node source: " + data_graph.fPrima(i));
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
                System.out.println("    node: " + data_graph.fPrima(v));

                Node ref_v = node.get(v);
                if (!ref_v.visited) {
                    ref_v.visited = true;
                    ref_v.component = cc;
                    route.add(v);
                    int leaf_index = 0;
                    List<Integer> al = g.nodosSalida(v);
                    System.out.print("      adj:");
                    for (int aux : al) { //Para todos los nodos adyacentes a v
                        Node ref_aux = node.get(aux);
                        double dist = data_graph.pesoAristasVertices(v, aux); //dist es el peso de v -> aux
                        if (i == 0 && dist == alg_graph.pesoAristasVertices(v, aux) ) alg_graph.modificarArista(v, aux, dist, 0);
                        if (!ref_aux.visited) {

                            if (ref_aux.distance > ref_v.distance + dist){
                                ref_aux.distance = ref_v.distance + dist;
                                ref_aux.weight = ref_v.weight;
                            } else if (ref_aux.distance == ref_v.distance + dist) {
                                ref_aux.weight += ref_v.weight;
                            }

                            leaf_index += 1;
                            ref_aux.parent.add(v);

                            System.out.print(" "+data_graph.fPrima(aux)+" "+ref_aux.parent+" ("+dist+") {"+ref_aux.distance+"} <"+ref_aux.weight+">");

                            Q.add(new Arista<Integer>(aux, dist));

                        }

                    }

                    System.out.print("\n");

                    if (leaf_index == 0) {
                        ref_v.leaf = true;
                        ref_v.down_total = 0;
                    }
                }

            }

            System.out.println("BRANDES");
            System.out.println(route.size()+" "+route);
            //Pesos en grafo
            int endRoute = route.size();
            for (int z = 0; z < endRoute; ++z)
            {
                System.out.println(z);
                int p = route.pollLast();
                Node golf = node.get(p);
                System.out.println("    "+data_graph.fPrima(p)+"#"+golf.parent.size()+golf.parent);
                if (golf.parent.size() > 0) {
                    System.out.println("    "+data_graph.fPrima(p) + ":");
                    for (int inode : golf.parent) {
                        System.out.println("      "+data_graph.fPrima(inode));
                        Node up = node.get(inode);
                        double multiplier = up.weight/golf.weight;
                        double myWeight = (1 + golf.down_total) * multiplier / data_graph.pesoAristasVertices(inode, p);
                        double dependency = alg_graph.pesoAristasVertices(inode, p);
                        double rel = dependency + myWeight;
                        alg_graph.modificarArista(inode, p, dependency, rel);
                        System.out.println("        "+alg_graph.fPrima(inode)+"--"+alg_graph.fPrima(p)+": "+rel+" | m="+up.weight+"/"+golf.weight+"="+multiplier+" |");
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

        out.agregarMensaje("Arista eliminada: " + data_graph.fPrima(victim.orig) + "--" + data_graph.fPrima(victim.dest));
        alg_graph.eliminarAristas(victim.orig, victim.dest);//, data_graph.pesoAristaVertices(victim.orig, victim.dest));
        alg_graph.eliminarAristas(victim.dest, victim.orig);//, data_graph.pesoAristaVertices(victim.dest, victim.orig));

        for (int ii = 0; ii < N; ++ii) {
            Node n = node.get(ii);
            n.visited = false;
            n.component = -1;
            n.leaf = false;
            n.distance = Double.POSITIVE_INFINITY;
            n.parent.clear();
            n.weight = 0;
            n.down_total = 0;
            node.add(n);
        }

        out.comunidad().clear();
        TreeSet<Integer> comm = null;
        //Segundo pase del algoritmo de Girvan-Newman
        for (int iii = 0; iii < N; ++iii)
        {
            //Lista para mantener guardada la ruta realizada durante Dijkstra
            LinkedList<Integer> route = new LinkedList<Integer>();
            //Cola de prioridades para aristas
            PriorityQueue<Arista<Integer>> Q = new PriorityQueue<Arista<Integer>>(N, comp);

            //Inicializar los nodos
            for (int k = 0; k < N; ++k) {
                Node n = node.get(k);
                n.visited = false;
                n.leaf = false;
                n.distance = Double.POSITIVE_INFINITY;
                n.parent.clear();
                n.weight = 0;
                n.down_total = 0;
            }

            Q.add(new Arista<Integer>(iii, 0));

            Node uno = node.get(iii);
            uno.distance = 0;
            uno.weight = 1;

            //Si no pertanece el nodo a un componente conexo, sumamos 1 a cc y le asignamos un componente al nodo
            if (uno.component == -1)
            {
                ++cc2;
                uno.component = cc2;
                comm = new TreeSet<Integer>();
                comm.add(iii);
                out.comunidad().add(comm);
            }

            //Mientras la cola no esté vacía
            while(!Q.isEmpty())
            {
                Arista<Integer> a = Q.poll();
                int v = a.fin();

                Node ref_v = node.get(v);
                if (!ref_v.visited) {
                    ref_v.visited = true;
                    if (ref_v.component == -1)
                    {
                        ref_v.component = cc2;
                        comm.add(v);
                    }

                    route.add(v);
                    int leaf_index = 0;
                    List<Integer> al = alg_graph.nodosSalida(v);
                    for (int aux : al) { //Para todos los nodos adyacentes a V[v]

                        Node ref_aux = node.get(aux);
                        double dist = data_graph.pesoAristasVertices(v, aux); //dist es el peso de v -> aux
                        if (!ref_aux.visited) {

                            if (ref_aux.distance > ref_v.distance + dist){
                                ref_aux.distance = ref_v.distance + dist;
                                ref_aux.weight = ref_v.weight;
                            } else if (ref_aux.distance == ref_v.distance + dist) {
                                ref_aux.weight += ref_v.weight;
                            }

                            leaf_index += 1;
                            ref_aux.parent.add(v);


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
            int endRoute = route.size();
            for (int z = 0; z < endRoute; ++z)
            {
                int p = route.pollLast();
                Node golf = node.get(p);
                if (golf.parent.size() > 0) {
                    for (int inode : golf.parent) {

                        Node up = node.get(inode);
                        double multiplier = up.weight/golf.weight;
                        double myWeight = (1 + golf.down_total) * multiplier / data_graph.pesoAristasVertices(inode, p);
                        double dependency = alg_graph.pesoAristasVertices(inode, p);
                        double rel = dependency + myWeight;
                        alg_graph.modificarArista(inode, p, dependency, rel);
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

        //Mensajes de salida
        if (cc2 > cc)
        {
            int d_cc = cc2 - cc;
            if (d_cc == 1 && alg_cc != 0) out.agregarMensaje("Ha surgido una nueva comunidad.");
        }

        alg_cc = cc2;

        return super.ejecutar_iteración(g);
    }



}

