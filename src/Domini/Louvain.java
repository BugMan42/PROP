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
    }

    public void ejecutar_algoritmo() throws Exception {
        long tini = System.nanoTime();

        g = new Grafo(in.obtGrafo());
        lim_Q = in.obtParam1();

        // Guardar peso total del grafo.
        m2 = .0;
        for(int i=0; i<g.V(); ++i) m2 += g.totalPesoSalida(i);

        out.mostrarHistorial().clear();
        out.agregarMensaje(hora_sistema());
        out.agregarMensaje("MÉTODO DE LOUVAIN.");
        out.agregarMensaje("Inicio de la ejecución del algoritmo.");
        out.agregarMensaje("Peso total del grafo: "+String.valueOf(m2));
        out.agregarMensaje("Mínimo incremento de modularidad fijado: " + String.valueOf(lim_Q) + "\n");

        boolean mejora;
        pasada = 0;
        do {
            ++pasada;
            out.agregarMensaje("PASADA NUM. "+String.valueOf(pasada));
            mejora = primera_fase();
            segunda_fase();
            actualizar_salida();
            out.agregarMensaje("FIN PASADA NUM. " + String.valueOf(pasada)+"\n");
        }
        while (mejora);


        out.agregarMensaje(hora_sistema());
        out.agregarMensaje("Fin de la ejecución del algoritmo.");
        out.agregarMensaje("Tiempo de ejecución(ms): "+String.valueOf((System.nanoTime()-tini)/1000000.0));
        out.agregarMensaje("Pasadas realizadas: "+String.valueOf(pasada));
        String s = "Comunidades encontradas:\n";
        for(int i = 0; i< out.comunidad().size(); ++i){
            s += "Comunidad "+String.valueOf(i)+": ";
            for(int j : out.comunidad().get(i))
                s += String.valueOf(j)+" ";
            s += "\n";
        }
        out.agregarMensaje(s);
        
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

        out.agregarMensaje("INICIO DE LA PRIMERA FASE");
        out.agregarMensaje("Num. nodos del grafo: "+String.valueOf(num_n));

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


        out.agregarMensaje("Inicio del bucle encargado de mover los nodos en cada iteración hacia las comunidades" +
                " que producen más ganancia de modularidad.\n");
        do {
            old_Q = new_Q;
            nodos_movidos = 0;

            out.agregarMensaje("Inicio iteración.");
            out.agregarMensaje("Modularidad: "+String.valueOf(old_Q));
            out.agregarMensaje("Moviendo nodos a la mejor comunidad.\n");

            // Mover nodos a la mejor comunidad.
            // Elegir nodo (mejor en orden aleatorio).
            for (int i=0; i<num_n; ++i){
                int nodo = rand[i];
                int com_nodo = n2c[nodo];

                out.agregarMensaje("Nodo "+String.valueOf(nodo)+" (Comunidad "+String.valueOf(com_nodo)+")");

                // Consultar comunidades adyacentes al nodo.
                // Calcular pesos adyacencias del nodo a comunidades.
                double[] p_nc = new double[num_n];
                ArrayList<Integer> coms = comunidades_ady(nodo, p_nc);

                // Comentarios Salida
                String s = "Comunidades adyacentes al nodo: ";
                for(Integer x : coms) s += String.valueOf(x)+" ";
                out.agregarMensaje(s);

                // Quitar nodo de su comunidad.
                out.agregarMensaje("Quitar nodo "+String.valueOf(nodo)+" de la comunidad "+String.valueOf(com_nodo));
                quitar_nodo_comunidad(nodo, com_nodo, p_nc[com_nodo]);

                // Para cada comunidad comprobar la ganancia de modularidad al añadir el nodo.
                out.agregarMensaje("Comprobando ganancias al añadir el nodo a cada comunidad adyacente.");
                int mejor_com = com_nodo;
                double mejor_ganancia = 0.0;
                for(int j : coms){
                    double ganancia = ganancia_modularidad(nodo,j);
                    out.agregarMensaje("Comunidad "+String.valueOf(j)+" => Ganancia "+String.valueOf(ganancia));
                    if (ganancia > mejor_ganancia){
                        mejor_com = j;
                        mejor_ganancia = ganancia;
                    }
                }
                out.agregarMensaje("Fin de la comprobación.");
                out.agregarMensaje("Mejor ganancia: " + String.valueOf(mejor_ganancia) + " (Comunidad " + String.valueOf(mejor_com) + ")");

                // Añadir el nodo a la comunidad con mayor ganancia.
                out.agregarMensaje("Añadir nodo "+String.valueOf(nodo)+" a la comunidad "+String.valueOf(mejor_com)+"\n");
                insertar_nodo_comunidad(nodo, mejor_com, p_nc[mejor_com]);

                // Si hemos movido el nodo a otra comunidad incrementamos nodos_movidos.
                if(mejor_com != com_nodo) ++nodos_movidos;

            }
            out.agregarMensaje("Nodos movidos a las mejores comunidades.");

            // Si se ha movido algún nodo entonces se ha mejorado la modularidad.
            if(nodos_movidos > 0) mejora = true;

            // Recalcular modularidad.
            new_Q = modularidad();
            out.agregarMensaje("Modularidad anterior: "+String.valueOf(old_Q));
            out.agregarMensaje("Nueva modularidad: "+String.valueOf(new_Q));
            out.agregarMensaje("Incremento: "+String.valueOf(new_Q-old_Q));
            out.agregarMensaje("Incremento mínimo fijado: "+String.valueOf(lim_Q));

            if(new_Q-old_Q>lim_Q) out.agregarMensaje("Incremento > Mínimo incremento");
            else out.agregarMensaje("Incremento <= Mínimo incremento");

            if(nodos_movidos>0) out.agregarMensaje("Se han movido nodos a otras comunidades en la iteración.");
            else out.agregarMensaje("No se ha movido ningún nodo a otra comunidad en la iteración.");

            out.agregarMensaje("Fin iteración.\n");
        }
        while (new_Q - old_Q > lim_Q && nodos_movidos > 0);
        // Repetir mientras "modularidad nueva - modularidad antigua > minimo establecido"
        // y se haya movido algún nodo a otra comunidad en la iteración.

        out.agregarMensaje("Fin del bucle.");
        out.agregarMensaje("FIN DE LA PRIMERA FASE\n");

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

        out.agregarMensaje("INICIO DE LA SEGUNDA FASE");
        out.agregarMensaje("Calcular número de comunidades nuevas.");

        // Calcular num comunidades nuevas.
        int[] renumerar = new int[num_n];
        for(int i=0; i<num_n; ++i) ++renumerar[n2c[i]];
        int num_nuevas_com = 0;
        for(int i=0; i<num_n; ++i){
            if(renumerar[i] > 0) renumerar[i] = num_nuevas_com++;
        }

        out.agregarMensaje("Num. comunidades nuevas: "+String.valueOf(num_nuevas_com));
        String s = "Listado de nodos y comunidad a la que pertenecen.\n";
        for(int i=0; i<num_n; ++i) s += String.valueOf(i)+" "+String.valueOf(renumerar[n2c[i]])+"\n";
        out.agregarMensaje(s);


        out.agregarMensaje("Construir matriz con los nodos que contiene cada comunidad para " +
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
        out.agregarMensaje(s);

        // Construir grafo.
        out.agregarMensaje("Construir nuevo grafo.");
        Grafo g2 = new Grafo();
        for(int i=0; i<num_nuevas_com; ++i) g2.agregarVertice(String.valueOf(i));
        out.agregarMensaje("Num. nodos del nuevo grafo: "+String.valueOf(g2.V()));

        for(int i=0; i<num_nuevas_com; ++i){
            // Calcular aristas comunidad i.
            out.agregarMensaje("Creando aristas del nodo "+String.valueOf(i));
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

            // Comentarios Salida
            s = "";
            for(Map.Entry<Integer,Double> p : aristas.entrySet())
                s += "hacia nodo "+String.valueOf(p.getKey())+" con peso "+p.getValue()+"\n";
            out.agregarMensaje(s);
        }
        out.agregarMensaje("FIN DE LA SEGUNDA FASE\n");

        g = g2;
    }

    private void actualizar_salida(){
        out.agregarMensaje("INICIO ACTUALIZACIÓN SALIDA");
        out.agregarMensaje("Guardar cuáles son los nodos iniciales que pertenecen a cada nueva comunidad.");

        if (pasada > 1) {
            ArrayList<Set<Integer>> nueva_com = new ArrayList<Set<Integer>>();
            for(ArrayList<Integer> nodos : nodos_com){
                Set<Integer> com = new HashSet<Integer>();
                for(Integer n : nodos)
                    for(Integer x : out.comunidad().get(n))
                        com.add(x);
                nueva_com.add(com);
            }
            out.comunidad().clear();
            out.comunidad().addAll(nueva_com);
        }
        else {
            out.comunidad().clear();
            for(ArrayList<Integer> nodos : nodos_com){
                Set<Integer> com = new HashSet<Integer>();
                for(Integer n : nodos) com.add(n);
                out.comunidad().add(com);
            }
        }


        String s = "";
        for(int i=0; i< out.comunidad().size(); ++i){
            s += "COMUNIDAD "+String.valueOf(i)+": ";
            for(Integer x : out.comunidad().get(i))
                s += String.valueOf(x)+" ";
            s += "\n";
        }
        out.agregarMensaje(s);
        out.agregarMensaje("FIN ACTUALIZACIÓN SALIDA\n");
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

    private String hora_sistema(){
        Date d = new Date();
        return d.toString();
    }
}

