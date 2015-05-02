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

    private void cliqueOneNode(k_clique kc,int k, List<Integer> lista) throws Exception {
        if (k == 1) {
            kc.agregar(lista.get(0));
            return;
        }
        if (k == 2) {
            int u = lista.get(0);
            int v = lista.get(1);
            if (g.existeArista(u, v)) {
                kc.agregar(u);
                kc.agregar(v);
            }
            else kc.eliminar();
            return;
        }
        else {
            int u = lista.get(0);
            System.out.println("Primer nodo " + Integer.toString(u));
            int v = lista.get(1);
            System.out.println("Segundo nodo " + Integer.toString(u));
            kc.agregar(u);
            kc.agregar(v);
            List<Integer> candidatos = g.nodosSalida(u);
            candidatos.retainAll(g.nodosSalida(v));
            Iterator it = candidatos.listIterator();
            int x = -1;
            while (it.hasNext() && (x = (Integer)it.next()) < v) it.remove();
            if (candidatos.size() > 0) cliqueOneNode(kc, k - 2, candidatos);
        }
    }

    private int index_sublista(int i) throws Exception {
        int contador = 0;
        for (Iterator it = g.nodosSalida(i).iterator(); it.hasNext() && (Integer)it.next() < i;) ++contador;
        return contador;
    }

    public Grafo ejecutar_algoritmo() throws Exception {
        //Primera version ineficiente coste n^2
        //int n = g.V();
        comunidades c = new comunidades();
        for (Iterator it = g.consultarVertices().iterator(); it.hasNext();) {
            int i = (Integer)it.next();
            System.out.println("Tratando nodo num: "+ Integer.toString(i));
            int m = g.degreeSalida(i);
            System.out.println("Grado de: "+ Integer.toString(i) + ": "+ Integer.toString(m));
            if (m + 1 >= k) {
                System.out.println(Integer.toString(index_sublista(i)));
                int j = index_sublista(i);
                List<Integer> candidatos = g.nodosSalida(i).subList(j, m);
                for (Iterator it2 = candidatos.iterator(); it2.hasNext();) {
                    k_clique kc = new k_clique();
                    kc.agregar(i);
                    int v = (Integer)it2.next();
                    kc.agregar(v);
                    //List<Integer> aux = (ArrayList<Integer>) candidatos.clone();
                    cliqueOneNode(kc, k - 2, candidatos);
                    if (kc.size() > 0) {
                        System.out.println("En el nodo num: " + Integer.toString(i) + " se ha creado una clique");
                        c.agregar_clique(kc);
                    }
                    ++j;
                }
            }
        }
        for (int i = 0; i < c.size();++i) {
            sout.agregarMensaje("clique" + Integer.toString(i) + ": ");
            k_clique kc = c.obt_clique(i);
            for (int j = 0; j < kc.size(); ++j) sout.agregarMensaje("vertice " + Integer.toString(kc.obt_vertice(j)));
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
