package Domini;

import java.util.*;

/**
 * Created by usuario on 04/04/2015.
 */
public class Clique extends Algoritmo {
    private Grafo g;
    private int k;

    /**Creadora de Clique
     * Pre: in y out no pueden ser nulos
     * Post: Se ha creado un clique en caso de que la k de entrada sea mayor que 2
     */
    public Clique(Entrada in, Salida out) throws Exception {
        super(in, out);
        g = in.obtGrafo();
        k = (int)in.obtParam1();
        if (k <= 2) throw new NoValido("k", 9);
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
        k_clique() {
            c = new ArrayList<Integer>();
            com = false;
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
    }

    /**
     * Pre:
     */
    public void insertar_nodo(int u, int i) throws NoValido {
        if (!g.existeVertice(u)) throw new NoValido("Nodo", 10);
        if (i < 0 || i >= out.numComunidades()) throw new NoValido("Indice", 11);
        Set<Integer> s =out.comunidad_at(i);
        s.add(u);
    }
    /**
     * Pre: Cierto
     * Post: En salida se encuentran almacenadas las comunidades encontradas en el grafo
     */
    public void ejecutar_algoritmo() throws Exception {
        //Primera version ineficiente coste n^2
        comunidades c = new comunidades();
        fase1(c);
        fase2(c);
    }

    /**Obtiene las k-cliques
     *Pre: c no es null
     *Post: c contiene los cliques que se encuentran en el grafo
     */
    private void fase1(comunidades c) throws Exception{
        for (Iterator it = g.consultarVertices().iterator(); it.hasNext(); ) {
            int i = (Integer) it.next();
            //System.out.println("Tratando nodo num: " + Integer.toString(i));
            int m = g.degreeSalida(i);
            //System.out.println("Grado de: " + Integer.toString(i) + ": " + Integer.toString(m));
            if (m + 1 >= k) {
                //System.out.println(Integer.toString(index_sublista(i)));
                List<Integer> candidatos = g.nodosSalida(i).subList(index_sublista(i), m);
                //System.out.println("Candidatos");
                /*for (int j = 0; j < candidatos.size(); ++j) {
                    System.out.println("Soy el candidato numero " + Integer.toString(candidatos.get(j)));
                }*/
                for (Iterator it2 = candidatos.iterator(); it2.hasNext(); ) {
                    k_clique kc = new k_clique();
                    kc.agregar(i);
                    int v = (Integer) it2.next();
                    kc.agregar(v);
                    it2.remove();
                    List<Integer> l = new ArrayList<Integer>(candidatos);
                    l.retainAll(g.nodosSalida(v));
                    //System.out.println("Lista recursividad");
                    /*for (int j = 0; j < l.size(); ++j) {
                        System.out.println("Soy el candidato numero " + Integer.toString(candidatos.get(j)));
                    }*/
                    cliqueOneNode(kc, k - 2, l);
                    if (kc.size() > 0) {
                        //System.out.println("En el nodo num: " + Integer.toString(i) + " se ha creado una clique");
                        c.agregar_clique(kc);
                    }
                    //System.out.println("Acabo de quitar el vertice " + Integer.toString(v));
                }
            }
        }
    }

    /**
     * Pre: 0 <= i < g.size()
     * Post: Devuelve el numero de nodos adyacentes a i en el grafo
     * que son mas pequeños a i
     */
    private int index_sublista(int i) throws Exception {
        int contador = 0;
        for (Iterator it = g.nodosSalida(i).iterator(); it.hasNext() && (Integer)it.next() < i;) ++contador;
        return contador;
    }

    /**
     * Mira si hay k_cliques en una lista
     * Pre: lista contiene enteros ordenados
     * Post: Si lista contiene un clique de tamaño k entonces esta en kc
     * sino kc estara vacio
     */
    private void cliqueOneNode(k_clique kc,int k, List<Integer> lista) throws Exception {
        //System.out.println("Mi k es " + Integer.toString(k) + " la lista tiene este numero de elementos " + Integer.toString(lista.size()));
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
            //System.out.println("Primer nodo " + Integer.toString(u));
            int v = lista.get(1);
            //System.out.println("Segundo nodo " + Integer.toString(u));
            List<Integer> candidatos = listarecursividad(u, v, kc);
            if (candidatos.size() > 0) cliqueOneNode(kc, k - 2, candidatos);
        }
    }

    /**Prepara la lista que se pasara a la siguiente vuelta recursiva
     *Pre: 0<=u, v < g.size()
     * Post: Devuelve una lista con los elementos adyacentes a u y v y ademas mayores que v
     */
    private List<Integer> listarecursividad(int u, int v, k_clique kc) throws Exception{
        kc.agregar(u);
        kc.agregar(v);
        List<Integer> candidatos = g.nodosSalida(u);
        candidatos.retainAll(g.nodosSalida(v));
        Iterator it = candidatos.listIterator();
        int x = -1;
        while (it.hasNext() && (x = (Integer)it.next()) < v) it.remove();
        return candidatos;
    }

    /**Determina las comunidades a partir de las cliques que estan en c
     * Pre: c no puede ser nulo
    * Post: Se han enviado los mensajes de las k-cliques encontradas
    * y de las comunidades encontradas
    */
    private void fase2(comunidades c) {
        agregarMensajes(c);
        for (int i = 0; i < c.size(); ++i) {
            Set<Integer> s = new HashSet<Integer>();
            System.out.println("Tratando clique num: " + Integer.toString(i));
            k_clique kc = c.obt_clique(i);
            if (!kc.obt_num())buscarCom(c,kc,s,i);
        }
    }

    /**Almacena las k-cliques en salida
     * Pre: c no puede ser nulo
     * Post: salida ha almacenado las k-cliques con sus respectivos elementos
     */
    private void agregarMensajes(comunidades c) {
        for (int i = 0; i < c.size(); ++i) {
            out.agregarMensaje("clique" + Integer.toString(i) + ": ");
            k_clique kc = c.obt_clique(i);
            for (int j = 0; j < kc.size(); ++j) out.agregarMensaje("vertice " + Integer.toString(kc.obt_vertice(j)));
        }
    }


    /**
     *Pre: c, kc y s no son nulos
     * Post: s contiene los k_cliques que forman una comunidad con kc incluyendolo
     */
    private void buscarCom(comunidades c, k_clique kc, Set<Integer> s, int i) {
        System.out.println("El clique num: " + Integer.toString(i) + " no tiene comunidad aun");
        kc.mod_num();
        boolean agregado = false;
        for (int j = 0; j < c.size(); ++j) {
            if (i != j) {
                k_clique kc1 = c.obt_clique(j);
                System.out.println("Mirando clique num: " + Integer.toString(j) + " para relacionarlo con " + Integer.toString(i));
                if (!kc1.obt_num()) {
                    System.out.println("El clique num: " + Integer.toString(j) + " no tiene comunidad aun");
                    ArrayList<Integer> aux = new ArrayList<Integer>(kc.lista());
                    aux.retainAll(kc1.lista());
                    System.out.println("El clique num: " + Integer.toString(j) + " tiene con clique num: " + Integer.toString(i) + " exactamente estos nodos en comun " + Integer.toString(aux.size()));
                    if (aux.size() == k - 1) {
                        System.out.println("El clique num: " + Integer.toString(j) + " esta en la misma comunidad que " + Integer.toString(i));
                        kc1.mod_num();
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
        if (!s.isEmpty()) out.agregarComunidad(s);
        else {
            s.addAll(kc.lista());
            out.agregarComunidad(s);
        }
    }
}
