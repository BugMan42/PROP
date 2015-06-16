package Domini;

import java.util.*;

/**
 * Created by Jose on 02/04/15.
 */

public class LouvainGrafoNuevo extends Algoritmo2 {

    private GrafoNodoArista g;
    private int num_n; // Num. nodos grafo.
    private double lim_Q; // Incremento mínimo de modularidad.

    private ArrayList<Congresista> nodos;
    private TST<Integer> c2i;
    private int pasada;
    private int[] n2c; // Comunidad de cada nodo.
    private double m2; // Peso de todas las aristas del grafo.
    private double[] k; // Suma de los pesos de las aristas adyacentes a cada nodo.
    private double[] ins, tot; // Suma de pesos interiores y total de las comunidades.
    private ArrayList<ArrayList<Congresista>> nodos_com; // Nodos que contiene cada comunidad.


    public LouvainGrafoNuevo(Entrada2 i, Salida2 o) throws Exception {
        super(i, o);
        g = i.obtGrafo();
        lim_Q = i.obtParam1();
        ejecutar_algoritmo();
    }

    public void ejecutar_algoritmo() throws Exception {
        // Consultar nodos grafo
        nodos = g.consultarVertices();

        // Guardar peso total del grafo.
        m2 = .0;
        for(Congresista c : nodos) m2 += g.obtenerPesoSalida(c);

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

        // Consultar nodos grafo
        nodos = g.consultarVertices();

        // Guardar posición de cada congresista.
        c2i = new TST<Integer>();
        int i = 0;
        for(Congresista c : nodos) c2i.insertar(c.obtID(), i++);

        // Inicializar comunidades.
        n2c = new int[num_n];
        for(i=0; i<num_n; ++i) n2c[i] = i;

        // Calcular pesos aristas comunidades.
        calcular_pesos_comunidades();

        // k[i] = Suma de pesos de las aristas adyacentes a i.
        k = new double[num_n];
        for (Congresista c : nodos) k[c2i.obtener(c.obtID())] = g.obtenerPesoSalida(c);

        // Calcular modularidad grafo.
        double old_Q = modularidad();
        double new_Q = old_Q;

        // Preparar orden aleatorio.
        Random r = new Random();
        int[] rand = new int[num_n];
        for (i = 0; i < num_n; ++i) rand[i] = i;
        for (i = 0; i < num_n; ++i) {
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
            for (i=0; i<num_n; ++i){
                int nodo = rand[i];
                Congresista n = nodos.get(nodo);
                int com_nodo = n2c[nodo];

                // Consultar comunidades adyacentes al nodo.
                // Calcular pesos adyacencias del nodo a comunidades.
                double[] p_nc = new double[num_n];
                ArrayList<Integer> coms = comunidades_ady(nodo,n,p_nc);

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
        nodos_com = new ArrayList<ArrayList<Congresista>>();
        for(int i=0; i<num_nuevas_com; ++i) nodos_com.add(new ArrayList<Congresista>());
        for(int i=0; i<num_n; ++i) nodos_com.get(renumerar[n2c[i]]).add(nodos.get(i));

        // Construir grafo.
        GrafoNodoArista g2 = new GrafoNodoArista();
        for(int i=0; i<num_nuevas_com; ++i){
            Dni dni = new Dni();
            Congresista c = new Congresista(dni,"a","a",22,"b", "b","b");
            g2.agregarVertice(c);
        }

        ArrayList<Congresista> nc = g2.consultarVertices();
        for(int i=0; i<num_nuevas_com; ++i){
            // Calcular aristas comunidad i.
            Map<Integer,Double> aristas = new HashMap<Integer, Double>();
            for(Congresista n : nodos_com.get(i)){
                List<Congresista> nodos_ady = g.nodosSalida(n);
                for(Congresista n_ady : nodos_ady){
                    int com = renumerar[n2c[c2i.obtener(n_ady.obtID())]];
                    if(!aristas.containsKey(com)) aristas.put(com,0.0);
                    double p = aristas.get(com) + g.pesoAristasVertice(n, n_ady);
                    aristas.put(com,p);
                }
            }
            // Añadir aristas comunidad i.
            for(Map.Entry<Integer,Double> p : aristas.entrySet())
                g2.agregarArista(new Edge(nc.get(i),nc.get(p.getKey()), p.getValue()));
        }

        g = g2;
    }

    private void actualizar_salida() throws Exception {
        if (pasada > 1) {
            ArrayList<Set<Congresista>> nueva_com = new ArrayList<Set<Congresista>>();
            for(ArrayList<Congresista> ns : nodos_com){
                Set<Congresista> com = new HashSet<Congresista>();
                for(Congresista n : ns)
                    for(Congresista x : obtOut().comunidad().get(c2i.obtener(n.obtID())))
                        com.add(x);
                nueva_com.add(com);
            }
            obtOut().comunidad().clear();
            obtOut().comunidad().addAll(nueva_com);
        }
        else {
            obtOut().comunidad().clear();
            for(ArrayList<Congresista> ns : nodos_com){
                Set<Congresista> com = new HashSet<Congresista>();
                for(Congresista n : ns) com.add(n);
                obtOut().comunidad().add(com);
            }
        }
    }


    // Modularidad, versión anterior.
    /*
    private double modularidad() throws Exception {
        double sum = .0;
        for (Congresista i : nodos){
            int ind_i = c2i.get(i);
            List<Congresista> n_ady = g.nodosSalida(i);
            for (Congresista j : nodos){ // Para cada nodo i,j.
                int ind_j = c2i.get(j);
                if (n2c[ind_i] == n2c[ind_j]){ // Si son de la misma comunidad.
                    double Aij = .0;
                    if(n_ady.contains(j)) // Si existe arista entre i y j.
                        Aij = g.pesoAristasVertice(i, j);
                    sum += Aij - ((k[ind_i]*k[ind_j])/m2);
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

    private ArrayList<Integer> comunidades_ady(int nodo, Congresista n, double[] p_nc) throws Exception {
        List<Congresista> n_ady = g.nodosSalida(n);
        List<Integer> ind_ady = new ArrayList<Integer>();
        for(Congresista c : n_ady) ind_ady.add(c2i.obtener(c.obtID()));
        Collections.sort(ind_ady);
        ArrayList<Integer> coms = new ArrayList<Integer>();
        coms.add(n2c[nodo]);
        for(Integer x : ind_ady){
            if (!coms.contains(n2c[x])) coms.add(n2c[x]);
            if(x==nodo) p_nc[n2c[x]] += g.pesoAristasVertice(n, nodos.get(x))/2;
            else p_nc[n2c[x]] += g.pesoAristasVertice(n, nodos.get(x));
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
        for(Congresista i : nodos){
            int com_i = n2c[c2i.obtener(i.obtID())];
            List<Congresista> n = g.nodosSalida(i);
            for(Congresista j : n){
                double p = g.pesoAristasVertice(i,j);
                int com_j = n2c[c2i.obtener(j.obtID())];
                tot[com_j] += p;
                if(com_i == com_j) ins[com_i] += p;
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
        Congresista c = nodos.get(nodo);
        List<Congresista> n = g.nodosSalida(c);
        for(Congresista i : n)
            if (n2c[c2i.obtener(i.obtID())] == com)
                p += g.pesoAristasVertice(c,i);
        return p;
    }

}

