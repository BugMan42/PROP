package Domini;

import java.util.*;

/**
 * Created by usuario on 04/04/2015.
 */
public class Clique extends Algoritmo {
    final static String error1 = "k no valida. La k minima es 3";
    final static String error2 = "El nodo no pertenece al grafo";
    final static String error3 = "El numero de comunidad debe estar entre [0 y numcomunidades - 1]";
    private Grafo g;
    private int k;

    public Clique(Entrada in, Salida out) throws Exception {
        super(in, out);
        g = in.obtGrafo();
        k = (int)in.obtParam1();
        if (k <= 2) throw new Exception(error1);
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
        Iterator<k_clique> iterator(int i) {
            return com.listIterator();
        }
    }

    class k_clique {
        private ArrayList<Integer> c;
        private boolean com;
        private int num;
        k_clique() {
            c = new ArrayList<Integer>();
            com = false;
            num = 0;
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
        boolean obt_num() {return com;}
        void mod_num() {com = true;}
        int obtNum() {return num;}
        void modNum(int i) {num = i;}
    }

    public void insertar_nodo(int u, int i) throws Exception {
        if (!g.existeVertice(u)) throw new Exception(error2);
        if (i < 0 || i >= obtOut().numComunidades()) throw new Exception(error3);
        Set<Integer> s = obtOut().comunidad_at(i);
        s.add(u);
    }

    public void ejecutar_algoritmo() throws Exception {
        comunidades c = new comunidades();
        fase1(c);
        fase2(c);
    }

    private void fase1(comunidades c) throws Exception{
        for (Iterator it = g.consultarVertices().iterator(); it.hasNext(); ) {
            int i = (Integer) it.next();
            obtOut().agregarMensaje("Tratando nodo num: " + Integer.toString(i));
            int m = g.degreeSalida(i);
            obtOut().agregarMensaje("Grado de: " + Integer.toString(i) + ": " + Integer.toString(m));
            if (m + 1 >= k) {
                List<Integer> candidatos = g.nodosSalida(i).subList(index_sublista(i), m);
                obtOut().agregarMensaje("Candidatos");
                for (int j = 0; j < candidatos.size(); ++j) obtOut().agregarMensaje("Soy el candidato numero " + Integer.toString(candidatos.get(j)));
                for (Iterator it2 = candidatos.iterator(); it2.hasNext(); ) {
                    k_clique kc = new k_clique();
                    kc.agregar(i);
                    int v = (Integer) it2.next();
                    kc.agregar(v);
                    it2.remove();
                    List<Integer> l = new ArrayList<Integer>(candidatos);
                    l.retainAll(g.nodosSalida(v));
                    obtOut().agregarMensaje("Lista recursividad");
                    for (int j = 0; j < l.size(); ++j) obtOut().agregarMensaje("Soy el candidato numero " + Integer.toString(candidatos.get(j)));
                    cliqueOneNode(kc, k - 2, l);
                    if (kc.size() > 0) {
                        obtOut().agregarMensaje("En el nodo num: " + Integer.toString(i) + " se ha creado una clique");
                        c.agregar_clique(kc);
                    }
                    obtOut().agregarMensaje("Acabo de quitar el vertice " + Integer.toString(v));
                }
            }
        }
    }

    private int index_sublista(int i) throws Exception {
        int contador = 0;
        for (Iterator it = g.nodosSalida(i).iterator(); it.hasNext() && (Integer)it.next() < i;) ++contador;
        return contador;
    }

    private void cliqueOneNode(k_clique kc,int k, List<Integer> lista) throws Exception {
        //System.obtOut().println("Mi k es " + Integer.toString(k) + " la lista tiene este numero de elementos " + Integer.toString(lista.size()));
        if (lista.isEmpty() || lista.size() < k) {
            kc.eliminar();
            return;
        }
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
            //System.obtOut().println("Primer nodo " + Integer.toString(u));
            int v = lista.get(1);
            //System.obtOut().println("Segundo nodo " + Integer.toString(u));
            List<Integer> candidatos = listarecursividad(u, v, kc);
            if (candidatos.size() > 0) cliqueOneNode(kc, k - 2, candidatos);
        }
    }

    private List<Integer> listarecursividad(int u, int v, k_clique kc) throws Exception{
        kc.agregar(u);
        kc.agregar(v);
        List<Integer> candidatos = g.nodosSalida(u);
        candidatos.retainAll(g.nodosSalida(v));
        Iterator it = candidatos.listIterator();
        while (it.hasNext() && ((Integer)it.next()) < v) it.remove();
        return candidatos;
    }

    private void fase2(comunidades c) {
        agregarMensajes(c);
        for (int i = 0; i < c.size(); ++i) {
            Set<Integer> s = new HashSet<Integer>();
            obtOut().agregarMensaje("Tratando clique num: " + Integer.toString(i));
            k_clique kc = c.obt_clique(i);
            if (!kc.obt_num())buscarCom(c,kc,s,i);
        }
    }

    private void agregarMensajes(comunidades c) {
        int com = 1;
        for (int i = 0; i < c.size(); ++i,++com) {
            obtOut().agregarMensaje("clique" + Integer.toString(i) + ": ");
            k_clique kc = c.obt_clique(i);
            if (kc.obtNum() == 0) kc.modNum(com);
            for (int j = 0; j < kc.size(); ++j) obtOut().agregarMensaje("vertice " + Integer.toString(kc.obt_vertice(j)));
        }
    }


    private void buscarCom(comunidades c, k_clique kc, Set<Integer> s, int i) {
        obtOut().agregarMensaje("El clique num: " + Integer.toString(i) + " no tiene comunidad aun");
        kc.mod_num();
        boolean agregado = false;
        for (int j = 0; j < c.size(); ++j) {
            if (i != j) {
                k_clique kc1 = c.obt_clique(j);
                obtOut().agregarMensaje("Mirando clique num: " + Integer.toString(j) + " para relacionarlo con " + Integer.toString(i));
                if (kc1.obtNum() == 0) {
                    obtOut().agregarMensaje("El clique num: " + Integer.toString(j) + " no tiene comunidad aun");
                    ArrayList<Integer> aux = new ArrayList<Integer>(kc.lista());
                    aux.retainAll(kc1.lista());
                    obtOut().agregarMensaje("El clique num: " + Integer.toString(j) + " tiene con clique num: " + Integer.toString(i) + " exactamente estos nodos en comun " + Integer.toString(aux.size()));
                    if (aux.size() == k - 1) {
                        obtOut().agregarMensaje("El clique num: " + Integer.toString(j) + " esta en la misma comunidad que " + Integer.toString(i));
                        kc1.modNum(kc.obtNum());
                        if (i < j) {
                            s.addAll(kc.lista());
                            agregado = true;
                            s.addAll(kc1.lista());
                        } else {
                            s.addAll(kc1.lista());
                            if (!agregado) s.addAll(kc.lista());
                        }
                    }
                }
            }
        }
        if (!s.isEmpty()) obtOut().agregarComunidad(s);
        else {
            s.addAll(kc.lista());
            obtOut().agregarComunidad(s);
        }
    }
}
