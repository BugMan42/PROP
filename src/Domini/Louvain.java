package Domini;

import java.util.*;

/**
 * Created by Jose on 02/04/15.
 */

public class Louvain extends Algoritmo {

    private Grafo g;
    private int num_n; // Num. nodos grafo.
    private double lim_Q; // Incremento mínimo de modularidad.

    private int[] n2c; // Comunidad de cada nodo.
    private double m2; // Peso de todas las aristas del grafo.
    private double[] k; // Suma de los pesos de las aristas adyacentes a cada nodo.
    private double[] ins, tot; // Suma de pesos interiores y total de las comunidades.
    private ArrayList<ArrayList<Integer>> nodos_com; // Nodos que contiene cada comunidad.

    public Louvain(Entrada i, Salida o) throws Exception {
        super(i, o);
    }

    public Grafo ejecutar_algoritmo() throws Exception {
        long tini = System.nanoTime();

        g = new Grafo(in.obtGrafo());
        lim_Q = in.obtParam1();

        // Guardar peso total del grafo.
        m2 = .0;
        for(int i=0; i<g.V(); ++i) m2 += g.totalPesoSalida(i);

        sout.agregarMensaje(hora_sistema());
        sout.agregarMensaje("MÉTODO DE LOUVAIN.");
        sout.agregarMensaje("Inicio de la ejecución del algoritmo.");
        sout.agregarMensaje("Peso total del grafo: "+String.valueOf(m2));
        sout.agregarMensaje("Mínimo incremento de modularidad fijado: " + String.valueOf(lim_Q) + "\n");

        boolean mejora;
        int pasadas = 0;
        do {
            ++pasadas;
            sout.agregarMensaje("PASADA NUM. "+String.valueOf(pasadas));
            mejora = primera_fase();
            segunda_fase();
            actualizar_salida();
            sout.agregarMensaje("FIN PASADA NUM. " + String.valueOf(pasadas)+"\n");
        }
        while (mejora);


        sout.agregarMensaje(hora_sistema());
        sout.agregarMensaje("Fin de la ejecución del algoritmo.");
        sout.agregarMensaje("Tiempo de ejecución(ms): "+String.valueOf((System.nanoTime()-tini)/1000000.0));
        sout.agregarMensaje("Pasadas realizadas: "+String.valueOf(pasadas));
        String s = "Comunidades encontradas:\n";
        for(int i = 0; i<sout.comunidad().size(); ++i){
            s += "Comunidad "+String.valueOf(i)+": ";
            for(int j : sout.comunidad().get(i))
                s += String.valueOf(j)+" ";
            s += "\n";
        }
        sout.agregarMensaje(s);

        return g;
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

        sout.agregarMensaje("INICIO DE LA PRIMERA FASE");
        sout.agregarMensaje("Num. nodos del grafo: "+String.valueOf(num_n));

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


        sout.agregarMensaje("Inicio del bucle encargado de mover los nodos en cada iteración hacia las comunidades" +
                " que producen más ganancia de modularidad.\n");
        do {
            old_Q = new_Q;
            nodos_movidos = 0;

            sout.agregarMensaje("Inicio iteración.");
            sout.agregarMensaje("Modularidad: "+String.valueOf(old_Q));
            sout.agregarMensaje("Moviendo nodos a la mejor comunidad.\n");

            // Mover nodos a la mejor comunidad.
            // Elegir nodo (mejor en orden aleatorio).
            for (int i=0; i<num_n; ++i){
                int nodo = rand[i];
                int com_nodo = n2c[nodo];

                sout.agregarMensaje("Nodo "+String.valueOf(nodo)+" (Comunidad "+String.valueOf(com_nodo)+")");

                // Consultar comunidades adyacentes al nodo.
                // Calcular pesos adyacencias del nodo a comunidades.
                double[] p_nc = new double[num_n];
                ArrayList<Integer> coms = comunidades_ady(nodo, p_nc);

                // Comentarios Salida
                String s = "Comunidades adyacentes al nodo: ";
                for(Integer x : coms) s += String.valueOf(x)+" ";
                sout.agregarMensaje(s);

                // Quitar nodo de su comunidad.
                sout.agregarMensaje("Quitar nodo "+String.valueOf(nodo)+" de la comunidad "+String.valueOf(com_nodo));
                quitar_nodo_comunidad(nodo, com_nodo, p_nc[com_nodo]);

                // Para cada comunidad comprobar la ganancia de modularidad al añadir el nodo.
                sout.agregarMensaje("Comprobando ganancias al añadir el nodo a cada comunidad adyacente.");
                int mejor_com = com_nodo;
                double mejor_ganancia = 0.0;
                for(int j : coms){
                    double ganancia = ganancia_modularidad(nodo,j);
                    sout.agregarMensaje("Comunidad "+String.valueOf(j)+" => Ganancia "+String.valueOf(ganancia));
                    if (ganancia > mejor_ganancia){
                        mejor_com = j;
                        mejor_ganancia = ganancia;
                    }
                }
                sout.agregarMensaje("Fin de la comprobación.");
                sout.agregarMensaje("Mejor ganancia: " + String.valueOf(mejor_ganancia) + " (Comunidad " + String.valueOf(mejor_com) + ")");

                // Añadir el nodo a la comunidad con mayor ganancia.
                sout.agregarMensaje("Añadir nodo "+String.valueOf(nodo)+" a la comunidad "+String.valueOf(mejor_com)+"\n");
                insertar_nodo_comunidad(nodo, mejor_com, p_nc[mejor_com]);

                // Si hemos movido el nodo a otra comunidad incrementamos nodos_movidos.
                if(mejor_com != com_nodo) ++nodos_movidos;

            }
            sout.agregarMensaje("Nodos movidos a las mejores comunidades.");

            // Si se ha movido algún nodo entonces se ha mejorado la modularidad.
            if(nodos_movidos > 0) mejora = true;

            // Recalcular modularidad.
            new_Q = modularidad();
            sout.agregarMensaje("Modularidad anterior: "+String.valueOf(old_Q));
            sout.agregarMensaje("Nueva modularidad: "+String.valueOf(new_Q));
            sout.agregarMensaje("Incremento: "+String.valueOf(new_Q-old_Q));
            sout.agregarMensaje("Incremento mínimo fijado: "+String.valueOf(lim_Q));

            if(new_Q-old_Q>lim_Q) sout.agregarMensaje("Incremento > Mínimo incremento");
            else sout.agregarMensaje("Incremento <= Mínimo incremento");

            if(nodos_movidos>0) sout.agregarMensaje("Se han movido nodos a otras comunidades en la iteración.");
            else sout.agregarMensaje("No se ha movido ningún nodo a otra comunidad en la iteración.");

            sout.agregarMensaje("Fin iteración.\n");
        }
        while (new_Q - old_Q > lim_Q && nodos_movidos > 0);
        // Repetir mientras "modularidad nueva - modularidad antigua > minimo establecido"
        // y se haya movido algún nodo a otra comunidad en la iteración.

        sout.agregarMensaje("Fin del bucle.");
        sout.agregarMensaje("FIN DE LA PRIMERA FASE\n");

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

        sout.agregarMensaje("INICIO DE LA SEGUNDA FASE");
        sout.agregarMensaje("Calcular número de comunidades nuevas.");

        // Calcular num comunidades nuevas.
        int[] renumerar = new int[num_n];
        for(int i=0; i<num_n; ++i) ++renumerar[n2c[i]];
        int num_nuevas_com = 0;
        for(int i=0; i<num_n; ++i){
            if(renumerar[i] > 0) renumerar[i] = num_nuevas_com++;
        }

        sout.agregarMensaje("Num. comunidades nuevas: "+String.valueOf(num_nuevas_com));
        String s = "Listado de nodos y comunidad a la que pertenecen.\n";
        for(int i=0; i<num_n; ++i) s += String.valueOf(i)+" "+String.valueOf(renumerar[n2c[i]])+"\n";
        sout.agregarMensaje(s);


        sout.agregarMensaje("Construir matriz con los nodos que contiene cada comunidad para " +
                "la creación del nuevo grafo.");

        // Construir matriz con los nodos de cada comunidad.
        nodos_com = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<num_nuevas_com; ++i) nodos_com.add(new ArrayList<Integer>());
        for(int i=0; i<num_n; ++i) nodos_com.get(renumerar[n2c[i]]).add(i);

        // Comentarios Salida
        s = "";
        for(int i=0; i<num_nuevas_com; ++i){
            s += "Comunidad "+String.valueOf(i)+": ";
            for (int j : nodos_com.get(i)) s += String.valueOf(j)+" ";
            s += "\n";
        }
        sout.agregarMensaje(s);

        // Construir grafo.
        sout.agregarMensaje("Construir nuevo grafo.");
        Grafo g2 = new Grafo();
        for(int i=0; i<num_nuevas_com; ++i) g2.agregarVertice(String.valueOf(i));
        sout.agregarMensaje("Num. nodos del nuevo grafo: "+String.valueOf(g2.V()));

        for(int i=0; i<num_nuevas_com; ++i){
            // Calcular aristas comunidad i.
            sout.agregarMensaje("Creando aristas del nodo "+String.valueOf(i));
            Map<Integer,Double> aristas = new HashMap<Integer, Double>();
            for(int n : nodos_com.get(i)){
                List<Integer> nodos_ady = g.nodosSalida(n);
                for(int n_ady : nodos_ady){
                    int com = renumerar[n2c[n_ady]];
                    if(!aristas.containsKey(com)) aristas.put(com,0.0);
                    List<Double> peso = g.obtenerListaPesos(n, n_ady);
                    double p = aristas.get(com) + peso.get(0);
                    aristas.put(com,p);
                }
            }
            // Añadir aristas comunidad i.
            for(Map.Entry<Integer,Double> p : aristas.entrySet())
                g2.agregarArista(String.valueOf(i), String.valueOf(p.getKey()), p.getValue());

            // Comentarios Salida
            s = "";
            for(Map.Entry<Integer,Double> p : aristas.entrySet())
                s += "hacia nodo "+String.valueOf(p.getKey())+" con peso "+p.getValue()+"\n";
            sout.agregarMensaje(s);
        }
        sout.agregarMensaje("FIN DE LA SEGUNDA FASE\n");

        g = g2;
    }

    private void actualizar_salida(){
        sout.agregarMensaje("INICIO ACTUALIZACIÓN SALIDA");
        sout.agregarMensaje("Guardar cuáles son los nodos iniciales que pertenecen a cada nueva comunidad.");

        if (sout.comunidad().size()>0) {
            ArrayList<Set<Integer>> nueva_com = new ArrayList<Set<Integer>>();
            for(ArrayList<Integer> nodos : nodos_com){
                Set<Integer> com = new HashSet<Integer>();
                for(Integer n : nodos)
                    for(Integer x : sout.comunidad().get(n))
                        com.add(x);
                nueva_com.add(com);
            }
            sout.comunidad().clear();
            sout.comunidad().addAll(nueva_com);
        }
        else {
            for(ArrayList<Integer> nodos : nodos_com){
                Set<Integer> com = new HashSet<Integer>();
                for(Integer n : nodos) com.add(n);
                sout.comunidad().add(com);
            }
        }


        String s = "";
        for(int i=0; i<sout.comunidad().size(); ++i){
            s += "COMUNIDAD "+String.valueOf(i)+": ";
            for(Integer x : sout.comunidad().get(i))
                s += String.valueOf(x)+" ";
            s += "\n";
        }
        sout.agregarMensaje(s);
        sout.agregarMensaje("FIN ACTUALIZACIÓN SALIDA\n");
    }

    // Modularidad, versión anterior.
    /*private double modularidad() throws Exception {
        double sum = .0;
        for (int i=0; i<num_n; ++i){
            List<Integer> n_ady = g.nodosSalida(i);
            for (int j=0; j<num_n; ++j){ // Para cada nodo i,j.
                if (n2c[i] == n2c[j]){ // Si son de la misma comunidad.
                    double Aij = .0;
                    if(n_ady.contains(j)){ // Si existe arista entre i y j.
                        List<Double> pesosAij = g.obtenerListaPesos(i,j);
                        Aij = pesosAij.get(0);
                    }
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
        double kiin = suma_peso_nodos_adyacentes_com(nodo,com);
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
            List<Double> p = g.obtenerListaPesos(nodo, x);
            if(x==nodo) p_nc[n2c[x]] += p.get(0)/2;
            else p_nc[n2c[x]] += p.get(0);
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
                List<Double> p = g.obtenerListaPesos(i, j);
                // p nunca vacío porque estamos consultando nodos adyacentes.
                tot[n2c[j]] += p.get(0);
                if(n2c[i] == n2c[j]) ins[n2c[i]] += p.get(0);
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
        for(int i : n){
            if (n2c[i] == com){
                List<Double> lp = g.obtenerListaPesos(nodo, i);
                p += lp.get(0);
            }
        }
        return p;
    }

    private String hora_sistema(){
        Date d = new Date();
        return d.toString();
    }
}

