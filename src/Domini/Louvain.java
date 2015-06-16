package Domini;

import java.util.*;

/**
 * Created by Jose on 02/04/15.
 */

public class Louvain extends Algoritmo {

    private Grafo g;
    private int num_n; // Num. nodos grafo.
    private double lim_Q; // Incremento mínimo de modularidad.

    private int pasada;
    private int[] n2c; // Comunidad de cada nodo.
    private double m2; // Peso de todas las aristas del grafo.
    private double[] k; // Suma de los pesos de las aristas adyacentes a cada nodo.
    private double[] ins, tot; // Suma de pesos interiores y total de las comunidades.
    private ArrayList<ArrayList<Integer>> nodos_com; // Nodos que contiene cada comunidad.

    public Louvain(Entrada i, Salida o) throws Exception {
        super(i, o);

        g = new Grafo(i.obtGrafo());
        lim_Q = i.obtParam1();
        ejecutar_algoritmo();
    }

    public void ejecutar_algoritmo() throws Exception {
        // Guardar peso total del grafo.
        m2 = .0;
        for(int i=0; i<g.V(); ++i) m2 += g.totalPesoSalida(i);

        obtOut().mostrarHistorial().clear();

        boolean mejora;
        pasada = 0;
        do {
            ++pasada;
            obtOut().agregarMensaje("Iteración "+pasada);
            mejora = primera_fase();
            segunda_fase();
            actualizar_salida();
        }
        while (mejora);

    }

    private boolean primera_fase() throws Exception {
        num_n = g.V();
        boolean mejora = false;
        int nodos_movidos;

        // Inicializar comunidades.
        n2c = new int[num_n];
        for(int i=0; i<num_n; ++i) n2c[i] = i;

        // Calcular pesos aristas comunidades.
        calcular_pesos_comunidades();

        // k[i] = Suma de pesos de las aristas adyacentes a i.
        k = new double[num_n];
        for (int i=0; i<num_n; ++i) k[i] = g.totalPesoSalida(i);

        // Calcular modularidad grafo.
        double old_Q = modularidad();
        double new_Q = old_Q;

        // Preparar orden aleatorio.
        Random r = new Random();
        int[] rand = new int[num_n];
        for (int i = 0; i < num_n; ++i) rand[i] = i;
        for (int i = 0; i < num_n; ++i) {
            int x = r.nextInt(num_n);
            int aux = rand[x];
            rand[x] = rand[i];
            rand[i] = aux;
        }


        do {
            old_Q = new_Q;
            nodos_movidos = 0;

            // Mover nodos a la mejor comunidad.
            // Elegir nodo (mejor en orden aleatorio).
            for (int i=0; i<num_n; ++i){
                int nodo = rand[i];
                int com_nodo = n2c[nodo];

                // Consultar comunidades adyacentes al nodo.
                // Calcular pesos adyacencias del nodo a comunidades.
                double[] p_nc = new double[num_n];
                ArrayList<Integer> coms = comunidades_ady(nodo, p_nc);

                // Quitar nodo de su comunidad.
                quitar_nodo_comunidad(nodo, com_nodo, p_nc[com_nodo]);

                // Para cada comunidad comprobar la ganancia de modularidad al añadir el nodo.
                int mejor_com = com_nodo;
                double mejor_ganancia = 0.0;
                for(int j : coms){
                    double ganancia = ganancia_modularidad(nodo,j);
                    if (ganancia > mejor_ganancia){
                        mejor_com = j;
                        mejor_ganancia = ganancia;
                    }
                }

                // Añadir el nodo a la comunidad con mayor ganancia.
                insertar_nodo_comunidad(nodo, mejor_com, p_nc[mejor_com]);

                if(pasada==1){
                    obtOut().agregarMensaje("Visita "+nodo);
                    obtOut().agregarMensaje("MoverNodoComunidad "+nodo+" "+mejor_com);
                }

                // Si hemos movido el nodo a otra comunidad incrementamos nodos_movidos.
                if(mejor_com != com_nodo) ++nodos_movidos;

            }

            // Si se ha movido algún nodo entonces se ha mejorado la modularidad.
            if(nodos_movidos > 0) mejora = true;

            // Recalcular modularidad.
            new_Q = modularidad();
        }
        while (new_Q - old_Q > lim_Q && nodos_movidos > 0);
        // Repetir mientras "modularidad nueva - modularidad antigua > minimo establecido"
        // y se haya movido algún nodo a otra comunidad en la iteración.

        // Devuelve true si se ha realizado alguna mejora.
        return mejora;
    }


    private void segunda_fase() throws Exception {
        // Crear nuevo grafo:
        // Comunidades pasan a ser nodos.
        // Los pesos de las aristas entre los nuevos nodos se calculan mediante la suma
        // de los pesos de las aristas entre nodos de las dos comunidades correspondientes.
        // Las aristas entre nodos de la misma comunidad generan bucles (aristas que relacionan
        // un nodo consigo mismo) para la comunidad en el nuevo grafo.

        // Calcular num comunidades nuevas.
        int[] renumerar = new int[num_n];
        for(int i=0; i<num_n; ++i) ++renumerar[n2c[i]];
        int num_nuevas_com = 0;
        for(int i=0; i<num_n; ++i){
            if(renumerar[i] > 0) renumerar[i] = num_nuevas_com++;
        }

        // Construir matriz con los nodos de cada comunidad.
        nodos_com = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<num_nuevas_com; ++i) nodos_com.add(new ArrayList<Integer>());
        for(int i=0; i<num_n; ++i) nodos_com.get(renumerar[n2c[i]]).add(i);

        // Construir grafo.
        Grafo g2 = new Grafo();
        for(int i=0; i<num_nuevas_com; ++i) {
            g2.agregarVertice(String.valueOf(i));
        }

        for(int i=0; i<num_nuevas_com; ++i){
            // Calcular aristas comunidad i.
            Map<Integer,Double> aristas = new HashMap<Integer, Double>();
            for(int n : nodos_com.get(i)){
                List<Integer> nodos_ady = g.nodosSalida(n);
                for(int n_ady : nodos_ady){
                    int com = renumerar[n2c[n_ady]];
                    if(!aristas.containsKey(com)) aristas.put(com,0.0);
                    double p = aristas.get(com) + g.pesoAristasVertices(n, n_ady);
                    aristas.put(com,p);
                }
            }
            // Añadir aristas comunidad i.
            for(Map.Entry<Integer,Double> p : aristas.entrySet())
                g2.agregarArista(String.valueOf(i), String.valueOf(p.getKey()), p.getValue());
        }

        g = g2;
    }

    private void actualizar_salida(){
        if (pasada > 1) {
            ArrayList<Set<Integer>> nueva_com = new ArrayList<Set<Integer>>();
            for(ArrayList<Integer> nodos : nodos_com){
                Set<Integer> com = new HashSet<Integer>();
                for(Integer n : nodos)
                    for(Integer x : obtOut().comunidad().get(n))
                        com.add(x);
                nueva_com.add(com);
            }
            obtOut().comunidad().clear();
            obtOut().comunidad().addAll(nueva_com);

            for(int i=0; i<obtOut().comunidad().size(); ++i)
                for(int j : obtOut().comunidad().get(i))
                    obtOut().agregarMensaje("MoverNodoComunidad "+j+" "+i);

        }
        else {
            obtOut().comunidad().clear();
            for(ArrayList<Integer> nodos : nodos_com){
                Set<Integer> com = new HashSet<Integer>();
                for(Integer n : nodos) com.add(n);
                obtOut().comunidad().add(com);
            }
        }
    }

    // Modularidad, versión anterior.
    /*private double modularidad() throws Exception {
        double sum = .0;
        for (int i=0; i<num_n; ++i){
            List<Integer> n_ady = g.nodosSalida(i);
            for (int j=0; j<num_n; ++j){ // Para cada nodo i,j.
                if (n2c[i] == n2c[j]){ // Si son de la misma comunidad.
                    double Aij = .0;
                    if(n_ady.contains(j)) // Si existe arista entre i y j.
                        Aij = g.pesoAristasVertices(i,j);
                    sum += Aij - ((k[i]*k[j])/m2);
                }
            }
        }
        return sum/m2;
    }*/

    private double modularidad(){
        double q = .0;
        for (int i=0; i<num_n; ++i){
            if(tot[i]>0) q += ins[i]/m2 - (tot[i]/m2)*(tot[i]/m2);
        }
        return q;
    }

    private double ganancia_modularidad(int nodo, int com) throws Exception {
        double kiin = suma_peso_nodos_adyacentes_com(nodo, com);
        //double gmod = ((ins[com]+kiin*2)/m2)-(((tot[com]+k[nodo])/m2)*((tot[com]+k[nodo])/m2));
        //gmod -= (ins[com]/m2 - ((tot[com]/m2)*(tot[com]/m2)) - ((k[nodo]/m2)*(k[nodo]/m2)));
        double gmod = kiin - tot[com]*k[nodo]/m2;
        return gmod;
    }

    private ArrayList<Integer> comunidades_ady(int nodo, double[] p_nc) throws Exception {
        List<Integer> nodos = g.nodosSalida(nodo);
        Collections.sort(nodos);
        ArrayList<Integer> coms = new ArrayList<Integer>();
        coms.add(n2c[nodo]);
        for(Integer x : nodos){
            if (!coms.contains(n2c[x])) coms.add(n2c[x]);
            if(x==nodo) p_nc[n2c[x]] += g.pesoAristasVertices(nodo,x)/2;
            else p_nc[n2c[x]] += g.pesoAristasVertices(nodo, x);
        }
        return coms;
    }

    private void calcular_pesos_comunidades() throws Exception {
        ins = new double[num_n];
        tot = new double[num_n];
        for(int i=0; i<num_n; ++i){
            ins[i] = .0;
            tot[i] = .0;
        }
        for(int i=0; i<num_n; ++i){
            List<Integer> n = g.nodosSalida(i);
            for(int j : n){
                double p = g.pesoAristasVertices(i,j);
                tot[n2c[j]] += p;
                if(n2c[i] == n2c[j]) ins[n2c[i]] += p;
            }
        }
    }

    private void insertar_nodo_comunidad(int nodo, int com, double p_nc){
        tot[com] += k[nodo];
        ins[com] += p_nc*2;
        n2c[nodo] = com;
    }

    private void quitar_nodo_comunidad(int nodo, int com, double p_nc){
        tot[com] -= k[nodo];
        ins[com] -= p_nc*2;
        n2c[nodo] = -1;
    }

    private double suma_peso_nodos_adyacentes_com(int nodo, int com) throws Exception {
        double p = 0.0;
        List<Integer> n = g.nodosSalida(nodo);
        for(int i : n)
            if (n2c[i] == com)
                p += g.pesoAristasVertices(nodo,i);
        return p;
    }
}

