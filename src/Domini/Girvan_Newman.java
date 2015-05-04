package Domini;

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

    //Referencia al grafo que contiene datos originales del grafo de relaciones
    private Grafo data_graph;
    //Referencia al grafo que se usa internamente en este algoritmo
    private Grafo alg_graph;
    //Número de comunidades de alg_graph
    private int alg_cc;

    //Clase privada sólo usada por el algoritmo Girvan-Newman
    private class Node {
        boolean visited;    //Booleano que comprueba si ya se ha visitado el nodo
        int component;      //Componente conexa a la que pertenece el nodo
        ArrayList<Integer> parent;  //Guarda todos los nodos antecesores según el algoritmo de Dijkstra
        double weight;      //Número de caminos cortos que atraviesan el nodo. 'Peso' según el algoritmo de Newman
        double distance;    //Distancia, según algoritmo de Dijkstra
        double down_total;  //Guarda la intermediación de las aristas 'inferiores', según el árbol de caminos más cortos de algoritmo de Dijkstra

        Node() {
            visited = false;
            component = -1;
            parent = new ArrayList<Integer>(1);
            weight = 0;
            distance = Double.POSITIVE_INFINITY;
            down_total = 0;
        }
    }

    //Clase privada sólo usada por el algoritmo Girvan-Newman
    class Eix {
        int orig;   //Identificador del nodo origen
        double weight;  //Peso del eje
        int dest;   //Identificador del nodo origen
    }

    //Comprador entre aristas
    class AristaComparator implements Comparator<Arista> {
        @Override
        public int compare(Arista o1, Arista o2) {

            return (int) (o1.peso()-o2.peso());
        }
    }

    //Vector donde se guarda la información de los nodos
    ArrayList<Node> node;

    //Creadora con parámetros de Girvan-Newman
    public Girvan_Newman(Entrada i, Salida o) throws Exception
    {
        super(i, o);
        //Se obtiene el grafo original desde la entrada
        data_graph = in.obtGrafo();
        //El grafo que utilizará el algoritmo es una copia de data_graph
        alg_graph = new Grafo(data_graph);
    }

    @Override
    public void ejecutar_algoritmo() throws Exception {
        //El parámetro de finalización de Girvan-Newman es el número máximo de comunidades
        int limit = (int) in.obtParam1();
        while(alg_cc < limit) ejecutar_iteración(alg_graph);
    }


    public void ejecutar_iteración() throws Exception {
        ejecutar_iteración(alg_graph);
    }

    @Override
    public void ejecutar_iteración(Grafo g) throws Exception {
        int N = g.V(); //Número de vértices del grafo
        int cc = 0; //Número componentes conexos en la primera ejecución del algoritmo de Dijkstra
        int cc2 = 0; //Número de componentes conexos en la segunda ejecución del algoritmo de Dijkstra
        node = new ArrayList<Node> (N);

        //Inicialización del vector Node
        for (int ii = 0; ii < N; ++ii) {
            Node n = new Node();
            n.parent.clear();
            node.add(n);
        }


        Eix victim = new Eix(); // Estructura de datos para poder saber la arista que se irá
        victim.weight = Double.NEGATIVE_INFINITY;
        //Instanciación de un comparador para aristas
        AristaComparator comp = new AristaComparator();

        //PRIMERA EJECUCIÓN
        //Algoritmo de Dijkstra por cada nodo del grafo
        for (int i = 0; i < N; ++i)
        {
            //Lista para mantener guardada la ruta realizada durante Dijkstra
            LinkedList<Integer> route = new LinkedList<Integer>();
            //Cola de prioridades para aristas
            PriorityQueue<Arista<Integer>> Q = new PriorityQueue<Arista<Integer>>(N, comp);

            //Inicializar los nodos del vector para esta iteración
            //node.clear();
            for (int j = 0; j < N; ++j) {
                Node n = node.get(j);
                n.visited = false;
                n.distance = Double.POSITIVE_INFINITY;
                n.parent.clear();
                n.weight = 0;
                n.down_total = 0;
            }

            //Añadimos a la cola de prioridades la primera arista
            Q.add(new Arista<Integer>(i, 0));

            System.out.println("node source: " + data_graph.fPrima(i));

            //El nodo origen de cada iteración tiene distancia 0 y 'peso', 1;
            Node uno = node.get(i);
            uno.distance = 0;
            uno.weight = 1;

            //Si no pertanece el nodo a un componente conexo, sumamos 1 a cc y le asignamos un componente al nodo
            if (uno.component == -1)
            {
                ++cc;
                uno.component = cc;
            }

            //Mientras la cola no esté vacía...
            while(!Q.isEmpty())
            {
                //Obtenemos la arista con el mayor peso
                Arista<Integer> a = Q.poll();
                int v = a.fin();
                System.out.println("    node: " + data_graph.fPrima(v));

                Node ref_v = node.get(v);
                //Si no habíamos visitado el nodo...
                if (!ref_v.visited) {
                    ref_v.visited = true;
                    if (ref_v.component == -1) ref_v.component = cc;
                    route.add(v);
                    int leaf_index = 0;
                    List<Integer> al = g.nodosSalida(v);
                    System.out.print("      adyacentes:");
                    //Para todos los nodos adyacentes a v
                    for (int aux : al) {
                        Node ref_aux = node.get(aux);
                        double dist = data_graph.pesoAristasVertices(v, aux); //dist es el peso de la arista v -> aux
                        //Dado que alg_graph es una copia de data_graph, necesitamos que, al principio, todas las aristas de alg_graph tengan peso = 0
                        if (i == 0 && dist == alg_graph.pesoAristasVertices(v, aux) ) alg_graph.modificarArista(v, aux, dist, 0);
                        //Si no se ha visitado un nodo adyacente...
                        if (!ref_aux.visited) {
                            //Si la distancia del nodo adyacente es mayor a la distancia del nodo más la distancia entre ellos...
                            if (ref_aux.distance > ref_v.distance + dist){
                                ref_aux.distance = ref_v.distance + dist;
                                ref_aux.weight = ref_v.weight;
                            } else if (ref_aux.distance == ref_v.distance + dist) {
                                ref_aux.weight += ref_v.weight;
                            }

                            leaf_index += 1;
                            //Añadimos el nodo v a la lista de 'predecesores'
                            ref_aux.parent.add(v);

                            System.out.print("\t" + data_graph.fPrima(aux) + " " + ref_aux.parent + " (" + dist + ") {" + ref_aux.distance + "} <" + ref_aux.weight + ">\n\t\t\t");

                            //Añadimos la arista a la cola
                            Q.add(new Arista<Integer>(aux, dist));

                        }

                    }

                    System.out.print("\n");

                    if (leaf_index == 0) {
                        ref_v.down_total = 0;
                    }
                }

            }

            //Algoritmo de Brandes
            System.out.println("BRANDES");
            //System.out.println(route.size()+" "+route);
            //Pesos en grafo
            int endRoute = route.size();
            //Recorremos la lista route desde el final hasta el principio.
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
                        double myWeight = (1 + golf.down_total) * multiplier;
                        double dependency = alg_graph.pesoAristasVertices(inode, p);
                        double rel = dependency + myWeight / data_graph.pesoAristasVertices(inode, p);
                        alg_graph.modificarArista(inode, p, dependency, rel);
                        System.out.println("        "+alg_graph.fPrima(inode)+"--"+alg_graph.fPrima(p)+": "+rel+" | m="+up.weight+"/"+golf.weight+"="+multiplier+" |");
                        up.down_total += myWeight;

                        if (victim.weight < rel) {
                            victim.orig = inode;
                            victim.dest = p;
                            victim.weight = rel;
                        }

                    }
                }

            }


        }

        //Eliminamos la arista con mayor intermediación
        out.agregarMensaje("Arista eliminada: " + data_graph.fPrima(victim.orig) + "--" + data_graph.fPrima(victim.dest));
        alg_graph.eliminarAristas(victim.orig, victim.dest);
        alg_graph.eliminarAristas(victim.dest, victim.orig);


        //SEGUNDA EJECUCIÓN
        for (int ii = 0; ii < N; ++ii) {
            Node n = node.get(ii);
            n.visited = false;
            n.component = -1;
            n.distance = Double.POSITIVE_INFINITY;
            n.parent.clear();
            n.weight = 0;
            n.down_total = 0;
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

            //Mientras la cola no esté vacía..
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
                        double myWeight = (1 + golf.down_total) * multiplier;
                        double dependency = alg_graph.pesoAristasVertices(inode, p);
                        double rel = dependency + myWeight / data_graph.pesoAristasVertices(inode, p);
                        alg_graph.modificarArista(inode, p, dependency, rel);
                        up.down_total += myWeight;

                        if (victim.weight < rel) {
                            victim.orig = inode;
                            victim.dest = p;
                            victim.weight = rel;
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

    }



}

