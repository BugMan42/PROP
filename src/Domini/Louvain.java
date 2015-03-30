package Domini;

import java.util.ArrayList;

/**
 * Created by falc on 30/03/15.
 */
public class Louvain extends Algoritmo {

    //Clase Comunidad, solo para el algoritmo Louvain
    class Community {
        public int in_weigth; // Suma de los pesos internos
    }

    ArrayList<Integer> vc; //Integer será sustituido por Vertice. Indica la comunidad a la que pertenece el vértice
    ArrayList<Community> comm;

    public Louvain () {

    }

    @Override
    public Grafo ejecutar_algoritmo(Grafo g) {
        //Primera fase

        //community = new ArrayList<Integer> (g.obt_num_vertices());
        for (int i = 0; i < vc.size(); ++i) vc.set(i, i);

        //x = g.obt_lista_adj();
        int max_Q = 0;
        int max_id = -1;

        /*
        for (Vertice vrt : x) {
            diffQ = comm.get(vrt.comm).in_weigth;
            sum_ic =
        }
        */
        return super.ejecutar_algoritmo(g);
    }
}
