package Domini;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by usuario on 04/04/2015.
 */
public class Clique extends Algoritmo {

    public Clique() {}
    public Clique(Entrada in, Salida out) {
        super(in, out);
    }

    class comunidades {
        private ArrayList<k_clique> com;
        comunidades() {
            com = new ArrayList<k_clique>();
        }
        void agregar_clique(k_clique k) {
            com.add(k);
        }
        /*void fusionar(int i, int j) {
            com.get(i).addAll(com.get(j));
        }*/
    }

    class k_clique {
        private ArrayList<Integer> c;
        k_clique() {
            c = new ArrayList<Integer>();
        }
        int size() {
            return c.size();
        }
        ArrayList<Integer> lista() {
              return c;
        }
         void agregar(int u) {
             c.add(u);
         }
         void eliminar() {
             c.clear();
         }
    }

    private void Trabajo() {

    }

    private void cliqueOneNode(Grafo g, comunidades c,int k, int u) {
        if (g.degree(u) + 1 < k) return;//Si no tiene suficiente grado para ser candidato no tiene sentido seguir
        ArrayList<Integer> ady = g.nodosAdyacentes(u);
        k_clique kc = new k_clique();
        kc.agregar(u);
        ArrayList<Integer> candidatos = new ArrayList<Integer>();
        ArrayList<Integer> descartados = new ArrayList<Integer>();
        for (Iterator it = ady.iterator(); it.hasNext();) {
            int v = (Integer)it.next();
            if (v < u) descartados.add(v);
            else candidatos.add(v); //Nunca un nodo puede ser adyacente a el mismo por lo tanto, iran los mayores a esta lista
        }
    }

    /*private void recursiva(Grafo g, int u, k_clique k, int v) {
        ArrayList<Integer>
        k.agregar(u);
        if (!g.es_ady(u, ))
    }*/
    public Grafo ejecutar_iteración(Grafo g) {
        //Primera version ineficiente coste n^2
        int n = g.V();
        int k = 3;
        comunidades c = new comunidades();
        for (int i = 0; i < n; ++i)
            cliqueOneNode(g, c, k, i);
        /*for (int i = 0; i < n; ++i) {
            ArrayList<Integer> ady = g.ady_copia(i);
            if (ady.size() >= 3 && g.pesoAristasVertice(i) >= 4) { //3 sera k cuando este listo y 4 sera el valor de threshold
                k_clique k = new k_clique();
                k.agregar(i);
                while (k.size() < 3 && ady.size() > 0){
                    int v = ady.get(0);
                    k.agregar(v);
                    ady.remove(0);
                    for (int j = 0; j < ady.size(); ++j) {
                        if (!g.es_ady(v, ady.get(j))) ady.remove(j);
                    }
                }
                if (k.size() < 3) k.eliminar();
            }
        }
         */return g;
    }
}
