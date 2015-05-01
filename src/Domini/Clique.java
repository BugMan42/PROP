package Domini;

import java.util.*;

/**
 * Created by usuario on 04/04/2015.
 */
public class Clique extends Algoritmo {

    private Grafo g;
    private int k;
    private int num;
    public Clique() throws Exception {}
    public Clique(Entrada in, Salida out) throws Exception {
        super(in, out);
        g = in.obtGrafo();
        k = (int)in.obtParam1();
        if (k <= 2) throw new NoValido("k", 9);
        num = 0;
    }

    class comunidades {
        private ArrayList<k_clique> com;
        comunidades() {
            com = new ArrayList<k_clique>();
        }
        int size() {return com.size();}
        k_clique obt_clique(int i) {
            return com.get(i);
        }
        void agregar_clique(k_clique k) {
            com.add(k);
        }
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
        Integer obt_vertice(int i) {return c.get(i);}
        void agregar(int u) {
             c.add(u);
         }
        void eliminar() {
             c.clear();
         }
    }

    private void cliqueOneNode(k_clique kc,int k, int u, List<Integer> lista) throws Exception {
        if (lista.size() + 1 < k) {
            kc.eliminar();
            return;       //Si no tiene suficiente grado para ser candidato no tiene sentido seguir
        }
        if (k == 1) {
            kc.agregar(lista.get(0));
            return;
        }
        if (k == 2) {
            int v = lista.get(1);
            if (g.existeArista(u, v)) {
                kc.agregar(u);
                kc.agregar(v);
            }
            else kc.eliminar();
            return;
        }
        else {
            int v = lista.get(0);
            kc.agregar(v);
            Iterator itv = g.nodosSalida(v).listIterator();
            ArrayList<Integer> candidatos = new ArrayList<Integer>();
            for (Iterator it = lista.listIterator(1); it.hasNext(); ) {
                int w = (Integer) it.next();
                if (w > u) {
                    int x = -1;
                    while (itv.hasNext() && (x = (Integer) itv.next()) < w) ;
                    if (x == w) candidatos.add(w);
                }
            }
            if (candidatos.size() > 0) cliqueOneNode(kc, k - 2, candidatos.get(0), candidatos);
        }
    }

    private int index_sublista(int i) throws Exception{
        int contador = 0;
        for (Iterator it = g.nodosSalida(i).listIterator(); it.hasNext() && (Integer)it.next() < i;) ++contador;
        return contador;
    }

    public Grafo ejecutar_algoritmo() throws Exception {
        //Primera version ineficiente coste n^2
        int n = g.V();
        comunidades c = new comunidades();
        for (int i = 0; i < n; ++i) {
            k_clique kc = new k_clique();
            int m = g.degreeSalida(i);
            if (m + 1 >= k) {
                System.out.println(Integer.toString(index_sublista(i)));
                List<Integer> candidatos = g.nodosSalida(i).subList(index_sublista(i), m);
                kc.agregar(i);
                cliqueOneNode(kc, k, i, candidatos);
                if (kc.size() > 0) c.agregar_clique(kc);
            }
        }
        for (int i = 0; i < c.size();++i) {
            out.agregarMensaje("clique" + Integer.toString(i) + ": ");
            k_clique kc = c.obt_clique(i);
            for (int j = 0; j < kc.size(); ++j) out.agregarMensaje("vertice " + Integer.toString(kc.obt_vertice(j)));
        }

        /*for (Iterator it = )
        for (int i = 0; i < n; ++i) {
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
