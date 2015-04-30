package Domini;

import java.util.*;

/**
 * Created by Jose on 02/04/15.
 */

public class Louvain extends Algoritmo {

    private Grafo g;
    private double lim_Q; // Incremento mínimo de modularidad.

    private int[] n2c; // Comunidad de cada nodo.
    private double m2; // Peso de todas las aristas del grafo.
    private double[] k; // Suma de los pesos de las aristas adyacentes a cada nodo.
    private double[] ins, tot;
    private int ncoms;

    public Louvain(Entrada i, Salida o) throws Exception {
        super(i, o);
    }

    public Grafo ejecutar_algoritmo() throws Exception {
        g = new Grafo(in.obtGrafo());
        lim_Q = in.obtParam1();
        ncoms = g.V();

        // Inicializar comunidades.
        n2c = new int[g.V()];
        for(int i=0; i<g.V(); ++i) n2c[i] = i;

        ins = new double[g.V()];
        tot = new double[g.V()];
        // Calcular pesos aristas comunidades.
        calcular_pesos_comunidades();

        /*
        boolean mejora;
        do {
            mejora = primera_fase();
            if (mejora) segunda_fase();
        }
        while (mejora);*/

        primera_fase();

        return g;
    }

    private boolean primera_fase() throws Exception {
        boolean mejora = false;
        int nodos_movidos;
        int pasadas = 0;

        // k[i] = Suma de pesos de las aristas adyacentes a i.
        k = new double[g.V()];
        for (int i=0; i<g.V(); ++i) k[i] = g.totalPesoSalida(i)*2;

        // Calcular modularidad grafo.
        double old_Q = modularidad();
        double new_Q = old_Q;

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

        do {
            old_Q = new_Q;
            nodos_movidos = 0;
            ++pasadas;

            // Mover nodos a la mejor comunidad.
            // Elegir nodo (mejor en orden aleatorio).
            for (int i=0; i<g.V(); ++i){
                int nodo = rand[i];
                int com_nodo = n2c[nodo];
                double peso_nodo = k[nodo];

                // Consultar comunidades adyacentes al nodo.
                Set<Integer> coms = comunidades_ady(nodo);

                // Quitar nodo de su comunidad.
                quitar_nodo_comunidad(nodo);

                // Para cada comunidad comprobar la ganancia de modularidad al añadir el nodo.
                // Añadir el nodo a la comunidad con mayor ganancia.
            }


            // Recalcular modularidad.
            new_Q = modularidad();

        }
        while (new_Q - old_Q > lim_Q && nodos_movidos > 0);
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

    private double modularidad() throws Exception{
        // Guardar peso total del grafo.
        m2 = .0;
        for(int i=0; i<g.V(); ++i) m2 += g.totalPesoSalida(i);
        m2 *= 2;
        //mostrar_hora("Peso total: "+String.valueOf(m2));

        double sum = .0;
        for (int i=0; i<g.V(); ++i){
            for (int j=0; j<g.V(); ++j){ // Para cada nodo i,j.
                if (n2c[i] == n2c[j]){ // Si son de la misma comunidad.
                    double Aij = .0;
                    List<Double> pesosAij = g.obtenerListaPesos(i, j);
                    if (!pesosAij.isEmpty()){ // Si existe arista entre i y j.
                        //if (pesosAij.size() > 1) throw new Exception("Más de una arista entre i y j.");
                        Aij = pesosAij.get(0);
                        //mostrar_hora("Aij: "+String.valueOf(Aij));
                    }
                    sum += Aij - ((k[i]*k[j])/m2);
                    //mostrar_hora("k[i]: "+String.valueOf(k[i]+" | k[j]: "+String.valueOf(k[j])));
                    //mostrar_hora("it sum: "+String.valueOf(Aij - ((k[i]*k[j])/m2)));
                    //mostrar_hora("total sum: "+String.valueOf(sum));
                }
            }
        }
        mostrar_hora("Modularidad: "+String.valueOf(sum/m2));
        return sum/m2;
    }

    private double ganancia_modularidad(){
        double gmod = .0;
        return gmod;
    }

    private Set<Integer> comunidades_ady(int nodo) throws Exception {
        List<Integer> nodos = g.nodosSalida(nodo);
        Set<Integer> coms = new HashSet<Integer>();
        for(Integer x : nodos) coms.add(n2c[x]);
        return coms;
    }

    private void calcular_pesos_comunidades() throws Exception {
        for(int i=0; i<ncoms; ++i){
            ins[i] = .0;
            tot[i] = .0;
        }
        for(int i=0; i<g.V(); ++i){
            List<Integer> n = g.nodosSalida(i);
            for(int j : n){
                List<Double> p = g.obtenerListaPesos(i, j);
                // p nunca vacío porque estamos consultando nodos adyacentes.
                if(n2c[i] == n2c[j]) ins[n2c[i]] += p.get(0)*2;
                else tot[n2c[j]] += p.get(0)*2;
            }
        }

        for(int i=0; i<ncoms; ++i)
            print("tot["+String.valueOf(i)+"] = "+String.valueOf(tot[i])+"\tins["+String.valueOf(i)+"] = "
                    +String.valueOf(ins[i]));

    }

    private void insertar_nodo_comunidad(int nodo, int com){

    }

    private void quitar_nodo_comunidad(int nodo) throws Exception {
        tot[n2c[nodo]] -= g.totalPesoSalida(nodo)*2;
    }

    private void mostrar_hora(String s){
        Date d = new Date();
        s += ": " + d.toString();
        System.out.println(s);
    }

    private void print(String s){
        System.out.println(s);
    }
}

