package Domini;

import java.util.*;

/**
 * Created by Jose on 02/04/15.
 */

public class Louvain extends Algoritmo {
/*
    private Grafo g;
    private double lim_Q;

    private int[] n2c;
    private double[] in, tot;
    private double m2; // Peso de todas las aristas del grafo.
    private double[] k; // Suma de los pesos de las aristas adyacentes a cada nodo.

    public Louvain(){

    }

    public Grafo ejecutar_algoritmo(Entrada in, Salida out){
        g = new Grafo(in.obtGrafo());
        lim_Q = in.obtParam1();

        // Inicializar comunidades.
        n2c = new int[g.V()];
        for(int i=0; i<g.V(); ++i) n2c[i] = i;

        // Guardar peso total del grafo.
        m2 = g.total();





        return g;
    }

    private boolean primera_fase(){
        // k[i] = Suma de pesos de las aristas adyacentes a i.
        for (int i=0; i<g.V(); ++i) k[i] = g.pesoAristasVertice(i);

        // Calcular modularidad grafo.
        double old_Q = modularidad();
        double new_Q = old_Q;

        do {
            // Preparar orden aleatorio.
            Random r = new Random();
            int[] rand = new int[g.V()];
            for (int i = 0; i < g.V(); ++i) rand[i] = i;
            for (int i = 0; i < g.V(); ++i) {
                int x = r.nextInt(g.V());
                int aux = rand[x];
                rand[x] = rand[i];
                rand[i] = aux;
            }

            // Mover nodos a la mejor comunidad.
            // Elegir nodo (mejor en orden aleatorio).
            for (int i=0; i<g.V(); ++i){
                // Consultar comunidades adyacentes al nodo.
                Set<Integer> coms = comunidades_ady(rand[i]);

                // Quitar nodo de su comunidad.


                // Para cada comunidad comprobar la ganancia de modularidad al añadir el nodo.
                // Añadir el nodo a la comunidad con mayor ganancia.
            }


            // Recalcular modularidad.
            new_Q = modularidad();

        }
        while (new_Q - old_Q > lim_Q);
        // Repetir mientras "modularidad nueva - modularidad antigua > minimo establecido"
        // y se haya movido algún nodo a otra comunidad en la iteración.

        // Devuelve true si se ha realizado alguna mejora.
        return true;
    }


    private void segunda_fase(){
        // Crear nuevo grafo:
        // Comunidades pasan a ser nodos.
        // Los pesos de las aristas entre los nuevos nodos se calculan mediante la suma
        // de los pesos de las aristas entre nodos de las dos comunidades correspondientes.
        // Las aristas entre nodos de la misma comunidad generan bucles (aristas que relacionan
        // un nodo consigo mismo) para la comunidad en el nuevo grafo.
    }

    private double modularidad(){
        double sum = .0;
        for (int i=0; i<g.V(); ++i){
            ArrayList<Arista> ady = g.aristasAdyacentes(i);
            int l = 0;
            for (int j=0; j<g.V(); ++j){ // Para cada nodo i,j.
                if (n2c[i] == n2c[j]) { // Si son de la misma comunidad.
                    double Aij = .0;
                    if (!ady.isEmpty() && ady.get(l).fin() == j){ // Si existe arista entre i y j.
                        Aij = ady.get(l).peso();
                        ++l;
                    }
                    sum += Aij - ((k[i]*k[j])/m2);
                }
            }
        }
        return sum/m2;
    }

    private double ganancia_modularidad(){
        double gmod = .0;
        return gmod;
    }

    private Set<Integer> comunidades_ady(int nodo){
        ArrayList<Arista> nodos = g.aristasAdyacentes(nodo);
        Set<Integer> coms = new HashSet<Integer>();
        for(Arista a : nodos) coms.add(n2c[a.fin()]);
        return coms;
    }

    private void insertar_nodo_comunidad(int nodo, int com){

    }

    private void quitar_nodo_comunidad(int nodo){

    }

    private void mostrar_hora(String s){
        Date d = new Date();
        s += ": " + d.toString();
        System.out.println(s);
    }
    */
}

