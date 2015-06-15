package Domini;

import java.util.*;


public class Clique extends Algoritmo {
    final static String error1 = "k no valida. La k minima es 3";
    final static String error2 = "El nodo no pertenece al grafo";
    final static String error3 = "El numero de comunidad debe estar entre [0 y numcomunidades - 1]";
    final static String error4 = "El valor del limite no puede ser inferior a 0";
    private Grafo g;
    private int k;
    private int th;
    public Clique(Entrada in, Salida out) throws Exception {
        super(in, out);
        g = in.obtGrafo();
        k = (int)in.obtParam1();
        th = (int)in.obtParam2();

        if (k <= 2) throw new Exception(error1);
        if (th < 0) throw new Exception(error4);
        ejecutar_algoritmo();
        //System.out.println(obtOut().comunidad());
    }

    class comunidades {
        private ArrayList<k_clique> com;
        private int ultimo;
        comunidades() {
            com = new ArrayList<k_clique>();
            ultimo = 0;
        }
        int size() {return com.size();}
        k_clique obt_clique(int i) {
            return com.get(i);
        }
        void agregar_clique(k_clique k) {
            com.add(k);
        }
        int obtUltimo() {return ultimo;}
        void incUltimo() {++ultimo;}
    }

    class k_clique {
        private ArrayList<Integer> c;
        private int num;
        private double peso;
        k_clique() {
            c = new ArrayList<Integer>();
            num = -1;
            peso = 0;
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
        int obtNum() {return num;}
        void modNum(int i) {num = i;}
        double obtPeso() {return peso;}
        void sumaPeso(double s) {peso += s;}
    }

    private void ejecutar_algoritmo() throws Exception {
        comunidades c = new comunidades();
        /*for (Iterator it = g.consultarVertices().iterator(); it.hasNext(); ) {
            int i = (Integer) it.next();
            int m = g.degreeSalida(i);
            if (m + 1 >= k) todo(c, i, m);

        }*/
        fase1(c);
        //fase2(c);
    }

    private void todo(comunidades c, int u, int m) throws Exception{
        List<Integer> candidatos = new ArrayList<Integer>(g.nodosSalida(u)).subList(index_sublista(u), m);
        candidatos.add(0, u);
        //System.out.println(candidatos);
        cliques(c, k, candidatos, null);
    }

    private void cliques(comunidades c, int k, List<Integer> lista, k_clique kc) throws Exception {
        //System.out.println("Mi k es " + Integer.toString(k) + " la lista tiene este numero de elementos " + Integer.toString(lista.size()));
        if (lista.isEmpty() || lista.size() < k) {
            if (kc != null) kc.eliminar();
            return;
        }
        if (k == 1) {
            //System.out.println("Agrego a kc el nodo num " + Integer.toString(lista.get(0)));
            kc.agregar(lista.get(0));
            return;
        }
        if (k == 2) {
            int u = lista.get(0);
            int v = lista.get(1);
            if (g.existeArista(u, v)) {
                kc.agregar(u);
                //System.out.println("Agrego a kc el nodo num " + Integer.toString(u));
                kc.agregar(v);
                //System.out.println("Agrego a kc el nodo num " + Integer.toString(v));
                try {
                    kc.sumaPeso(g.pesoAristasVertices(u, v));
                }
                catch (Exception ex) {}
            }
            else kc.eliminar();
        }
        else {
            int u = lista.get(0);
            //System.out.println("Primer nodo " + Integer.toString(u));
            lista.remove(0);
            //int v = lista.get(1);
            //System.out.println("Segundo nodo " + Integer.toString(u));
            //List<Integer> candidatos = listarecursividad(u, v, kc);
            //System.out.println("Fusion:");
            //System.out.println(fusion(g.nodosSalida(u), g.nodosSalida(v), u));
            //List<Integer> candidatos = fusion(g.nodosSalida(u), g.nodosSalida(v), u);
            //System.out.println("listaRecursividad:");
            //System.out.println(candidatos);
            for (Iterator it2 = lista.iterator(); it2.hasNext(); ) {
                int v = (Integer) it2.next();
                //System.out.println("Mirando nodo num: " + Integer.toString(v));
                it2.remove();
                //ArrayList<Integer> l = new ArrayList<Integer>(candidatos);
                //l.retainAll(g.nodosSalida(v));
                List<Integer> l = fusion(lista, g.nodosSalida(v),-1);
                //System.out.println("Lista recursividad");
                //System.out.println(l);
                //System.out.println("Lista Fusion");
                //System.out.println(listaF);
                //int contador = 0;
                for (Iterator it3 = l.iterator(); it3.hasNext();) {
                    //++contador;
                    //System.out.println("Pasada numero " + Integer.toString(contador));
                    kc = new k_clique();
                    kc.agregar(u);
                    //System.out.println("Agrego a kc el nodo num " + Integer.toString(u));
                    kc.agregar(v);
                    //System.out.println("Agrego a kc el nodo num " + Integer.toString(v));
                    cliques(c, k - 2, l, kc);
                    //for (int w = 0; w < l.size(); ++w) System.out.println("Soy el  numero " + Integer.toString(lista.get(w)) + " de l");
                    //System.out.println("kc");
                    //System.out.println(kc.lista());
                    it3.next();
                    it3.remove();
                    //for (int w = 0; w < l.size(); ++w) System.out.println("Soy el  numero " + Integer.toString(lista.get(w)) + " de l despues");
                    if (kc.size() > 0) {
                        try {
                            kc.sumaPeso(g.pesoAristasVertices(u, v));
                            for (Iterator it4 = kc.lista().iterator(); it4.hasNext(); ) {
                                int x = (Integer) it4.next();
                                if (x != u && x != v) {
                                    kc.sumaPeso(g.pesoAristasVertices(u, x));
                                    kc.sumaPeso(g.pesoAristasVertices(v, x));
                                }
                            }
                        }
                        catch (Exception ex) {

                        }
                        //obtOut().agregarMensaje("La clique tiene peso: " + kc.obtPeso());
                        if (kc.obtPeso() > th) {
                            String s = "Clique";
                            agregarVertices(s, kc.lista());
                            asignarCliqueCom(kc, c);
                            c.agregar_clique(kc);
                        }
                    }
                }
            }
            /*kc.agregar(u);
            kc.agregar(v);
            if (candidatos.size() > 0) {
                cliqueOneNode(kc, k - 2, candidatos);
                if (kc.lista().size() > 0) {
                    try {
                        kc.sumaPeso(g.pesoAristasVertices(u, v));
                        for (Iterator it = kc.lista().iterator(); it.hasNext(); ) {
                            int x = (Integer) it.next();
                            if (x != u && x != v) {
                                kc.sumaPeso(g.pesoAristasVertices(u, x));
                                kc.sumaPeso(g.pesoAristasVertices(v, x));
                            }
                        }
                    }
                    catch (Exception ex) {}
                }
            }*/
        }
    }

    private void fase1(comunidades c) throws Exception {
        for (Iterator it = g.consultarVertices().iterator(); it.hasNext(); ) {
            int i = (Integer) it.next();
            obtOut().agregarMensaje("Visita " + Integer.toString(i));
            int m = g.degreeSalida(i);
            System.out.println("Grado de: " + Integer.toString(i) + ": " + Integer.toString(m));
            if (m + 1 >= k) {
                List<Integer> candidatos = new ArrayList<Integer>(g.nodosSalida(i)).subList(index_sublista(i), m);
                System.out.println("Candidatos");
                for (int j = 0; j < candidatos.size(); ++j) System.out.println("Soy el candidato numero " + Integer.toString(candidatos.get(j)));
                for (Iterator it2 = candidatos.iterator(); it2.hasNext(); ) {
                    int v = (Integer) it2.next();
                    System.out.println("Mirando nodo num: " + Integer.toString(v));
                    it2.remove();
                    /*ArrayList<Integer> l = new ArrayList<Integer>(candidatos);
                    l.retainAll(g.nodosSalida(v));*/
                    List<Integer> l = fusion(candidatos, g.nodosSalida(v),-1);
                    System.out.println("Lista recursividad");
                    System.out.println(l);
                    /*System.out.println("Lista Fusion");
                    /System.out.println(listaF);*/
                    int contador = 0;
                    for (Iterator it3 = l.iterator(); it3.hasNext();) {
                        ++contador;
                        System.out.println("Pasada numero " + Integer.toString(contador));
                        k_clique kc = new k_clique();
                        kc.agregar(i);
                        System.out.println("Agrego a kc el nodo num " + Integer.toString(i));
                        kc.agregar(v);
                        System.out.println("Agrego a kc el nodo num " + Integer.toString(v));
                        cliqueOneNode(kc, k - 2, l);
                        //for (int w = 0; w < l.size(); ++w) System.out.println("Soy el  numero " + Integer.toString(candidatos.get(w)) + " de l");
                        System.out.println("kc");
                        System.out.println(kc.lista());
                        it3.next();
                        it3.remove();
                        //for (int w = 0; w < l.size(); ++w) System.out.println("Soy el  numero " + Integer.toString(candidatos.get(w)) + " de l despues");
                        if (kc.size() > 0) {
                            try {
                                kc.sumaPeso(g.pesoAristasVertices(i, v));
                                for (Iterator it4 = kc.lista().iterator(); it4.hasNext(); ) {
                                    int x = (Integer) it4.next();
                                    if (x != i && x != v) {
                                        kc.sumaPeso(g.pesoAristasVertices(i, x));
                                        kc.sumaPeso(g.pesoAristasVertices(v, x));
                                    }
                                }
                            }
                            catch (Exception ex) {

                            }
                            //obtOut().agregarMensaje("La clique tiene peso: " + kc.obtPeso());
                            if (kc.obtPeso() > th) {
                                String s = "Clique";
                                agregarVertices(s, kc.lista());
                                asignarCliqueCom(kc, c);
                                c.agregar_clique(kc);
                            }
                        }
                    }
                }
            }
        }
        //obtOut().agregarMensaje("Num cliques " + Integer.toString(c.size()));
    }


    private void asignarCliqueCom(k_clique kc, comunidades c) throws Exception{
        int n = c.size();
        //siempre existira una clique sino no estuviera aqui
        //en caso de que hayan mas
        if (n > 0) {
            boolean agregado = false;
            for (int i = 0; i < n && !agregado; ++i) {
                k_clique kc1 = c.obt_clique(i);
                //obtOut().agregarMensaje("Mirando clique num: " + Integer.toString(j) + " para relacionarlo con " + Integer.toString(i));
                int inter = numFusion(kc.lista(), kc1.lista());
                //ArrayList<Integer> aux = new ArrayList<Integer>(kc.lista());
                //aux.retainAll(kc1.lista());
                //System.out.println("inter: " + Integer.toString(inter) + " aux:" + Integer.toString(aux.size()));
                //obtOut().agregarMensaje("El clique num: " + Integer.toString(j) + " tiene con clique num: " + Integer.toString(i) + " exactamente estos nodos en comun " + Integer.toString(aux.size()));
                if (inter == k - 1) {
                    agregado = true;
                    kc.modNum(kc1.obtNum());
                    obtOut().comunidad_at(kc.obtNum()).addAll(kc.lista());
                    String s = "Comunidad " + Integer.toString(kc.obtNum());
                    ArrayList<Integer> w = new ArrayList<Integer>(obtOut().comunidad_at(kc.obtNum()));
                    agregarVertices(s, w);
                }
            }
            if (!agregado) {
                kc.modNum(c.obtUltimo());
                c.incUltimo();
                Set<Integer>   s = new HashSet<Integer>();
                s.addAll(kc.lista());
                String str = "Comunidad " + Integer.toString(kc.obtNum());
                agregarVertices(str, kc.lista());
                obtOut().agregarComunidad(s);
            }
        }
        //En caso de que sea la primera
        else {
            kc.modNum(c.obtUltimo());
            c.incUltimo();
            Set<Integer> s = new HashSet<Integer>();
            s.addAll(kc.lista());
            String str = "Comunidad " + Integer.toString(kc.obtNum());
            agregarVertices(str, kc.lista());
            obtOut().agregarComunidad(s);
        }
    }

    private void agregarVertices(String s, ArrayList<Integer> kc) {
        for (int j = 0; j < kc.size(); ++j)  s = s + " " + Integer.toString(kc.get(j));
        obtOut().agregarMensaje(s);
    }

    private int index_sublista(int i) throws Exception {
        int contador = 0;
        for (Iterator it = g.nodosSalida(i).iterator(); it.hasNext() && (Integer)it.next() < i;) ++contador;
        return contador;

    }

    /////////////////////////////HACER//////////////////////////
    private List<Integer> fusion(List<Integer> l1, List<Integer> l2, int u) {
        int i,j;
        i = j = 0;
        int n = l1.size();
        int m = l2.size();
        List<Integer> aux = new ArrayList<Integer>();
        while (i < n && j < m) {
            int a = l1.get(i);
            int b = l2.get(j);
            if (a == b) {
                if (a > u) aux.add(a);
                ++i;
                ++j;
            }
            else if (a < b) ++i;
            else ++j;
        }
        return aux;
    }

    private int numFusion(List<Integer> l1, List<Integer> l2) {
        int i,j;
        i = j = 0;
        int n = l1.size();
        int m = l2.size();
        int contador = 0;
        while (i < n && j < m) {
            int a = l1.get(i);
            int b = l2.get(j);
            if (a == b) {
                ++i;
                ++j;
                ++contador;
            }
            else if (a < b) ++i;
            else ++j;
        }
        return contador;
    }

    //////////////////////////////HACER//////////////////////////////
    private void cliqueOneNode(k_clique kc, int k, List<Integer> lista) throws Exception {
        System.out.println("Mi k es " + Integer.toString(k) + " la lista tiene este numero de elementos " + Integer.toString(lista.size()));
        if (lista.isEmpty() || lista.size() < k) {
            kc.eliminar();
            return;
        }
        if (k == 1) {
            System.out.println("Agrego a kc el nodo num " + Integer.toString(lista.get(0)));
            kc.agregar(lista.get(0));
            return;
        }
        if (k == 2) {
            int u = lista.get(0);
            int v = lista.get(1);
            if (g.existeArista(u, v)) {
                kc.agregar(u);
                System.out.println("Agrego a kc el nodo num " + Integer.toString(u));
                kc.agregar(v);
                System.out.println("Agrego a kc el nodo num " + Integer.toString(v));
                try {
                    kc.sumaPeso(g.pesoAristasVertices(u, v));
                }
                catch (Exception ex) {}
            }
            else kc.eliminar();
        }
        else {
            int u = lista.get(0);
            System.out.println("Primer nodo " + Integer.toString(u));
            int v = lista.get(1);
            /*System.out.println("Segundo nodo " + Integer.toString(u));
            List<Integer> candidatos = listarecursividad(u, v, kc);
            //System.out.println("Fusion:");
            //System.out.println(fusion(g.nodosSalida(u), g.nodosSalida(v), u));*/
            List<Integer> candidatos = fusion(g.nodosSalida(u), g.nodosSalida(v), u);
            //System.out.println("listaRecursividad:");
            System.out.println(candidatos);
            kc.agregar(u);
            kc.agregar(v);
            if (candidatos.size() > 0) {
                cliqueOneNode(kc, k - 2, candidatos);
                if (kc.lista().size() > 0) {
                    try {
                        kc.sumaPeso(g.pesoAristasVertices(u, v));
                        for (Iterator it = kc.lista().iterator(); it.hasNext(); ) {
                            int x = (Integer) it.next();
                            if (x != u && x != v) {
                                kc.sumaPeso(g.pesoAristasVertices(u, x));
                                kc.sumaPeso(g.pesoAristasVertices(v, x));
                            }
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }
    }

    private List<Integer> listarecursividad(int u, int v, k_clique kc) throws Exception{
        kc.agregar(u);
        kc.agregar(v);
        List<Integer> candidatos = new ArrayList<Integer>(g.nodosSalida(u));
        candidatos.retainAll(g.nodosSalida(v));
        Iterator it = candidatos.listIterator();
        while (it.hasNext() && ((Integer)it.next()) < u) it.remove();
        return candidatos;
    }

    private void fase2(comunidades c) throws Exception{
        //agregarMensajes(c);
        int n = c.size();
        if (n > 0) {
            boolean probado[][] = new boolean[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) probado[i][j] = false;
            }
            for (int i = 0; i < n; ++i) {
                //Set<Integer> s = new HashSet<Integer>();
                //obtOut().agregarMensaje("Tratando clique num: " + Integer.toString(i));
                k_clique kc = c.obt_clique(i);
                if (kc.obtNum() == -1) buscarCom(c,kc,i, probado, n);
            }
        }
    }

    private void agregarMensajes(comunidades c) {
        for (int i = 0; i < c.size(); ++i) {
            obtOut().agregarMensaje("Clique" + Integer.toString(i) + ": ");
            k_clique kc = c.obt_clique(i);
            for (int j = 0; j < kc.size(); ++j) obtOut().agregarMensaje("vertice " + Integer.toString(kc.obt_vertice(j)));
        }
    }


    private void buscarCom(comunidades c, k_clique kc, int i, boolean[][] probado, int n) throws Exception {
        obtOut().agregarMensaje("La clique num: " + Integer.toString(i) + " no tiene comunidad aun");
        boolean agregado = false;
        for (int j = 0; j < n; ++j) {
            if (i != j && !probado[i][j]) {
                probado[i][j] = true;
                probado[j][i] = true;
                k_clique kc1 = c.obt_clique(j);
                //obtOut().agregarMensaje("Mirando clique num: " + Integer.toString(j) + " para relacionarlo con " + Integer.toString(i));
                ArrayList<Integer> aux = new ArrayList<Integer>(kc.lista());
                aux.retainAll(kc1.lista());
                //obtOut().agregarMensaje("El clique num: " + Integer.toString(j) + " tiene con clique num: " + Integer.toString(i) + " exactamente estos nodos en comun " + Integer.toString(aux.size()));
                if (aux.size() == k - 1) {
                    obtOut().agregarMensaje("La clique num: " + Integer.toString(j) + " esta en la misma comunidad que " + Integer.toString(i));
                    if (i < j) {
                        if (kc.obtNum() == -1) {
                            kc.modNum(c.obtUltimo());
                            c.incUltimo();
                        }
                        kc1.modNum(kc.obtNum());
                    }
                    else {
                        if (kc1.obtNum() == -1) {
                            kc1.modNum(c.obtUltimo());
                            c.incUltimo();
                        }
                        kc.modNum(kc1.obtNum());
                    }
                    Set<Integer> conj;
                    if (obtOut().numComunidades() <= kc.obtNum()) {
                        conj = new HashSet<Integer>();
                        if (i < j) {
                            if (!agregado) {
                                agregado = true;
                                conj.addAll(kc.lista());
                            }
                            conj.addAll(kc1.lista());
                        }
                        else {
                            conj.addAll(kc1.lista());
                            if (!agregado) {
                                agregado = true;
                                conj.addAll(kc.lista());
                            }
                        }
                        obtOut().agregarMensaje("La clique num: " + Integer.toString(j) + " y " + Integer.toString(i) + " estan en la comunidad " + Integer.toString(kc.obtNum()));
                        obtOut().agregarComunidad(conj);
                    }
                    else {
                        conj = obtOut().comunidad_at(kc.obtNum());
                        //Como se pasa por referencia actualizo la comunidad
                        if (i < j) {
                            obtOut().agregarMensaje("La clique num: " + Integer.toString(j) + " esta en la comunidad " + Integer.toString(kc.obtNum()));
                            conj.addAll(kc1.lista());

                        }
                        else {
                            obtOut().agregarMensaje("La clique num: " + Integer.toString(i) + " esta en la comunidad " + Integer.toString(kc.obtNum()));
                            conj.addAll(kc.lista());
                        }
                        agregado = true;
                    }
                }
            }
        }
        if (!agregado) {
            kc.modNum(c.obtUltimo());
            c.incUltimo();
            Set<Integer> s = new HashSet<Integer>();
            s.addAll(kc.lista());
            obtOut().agregarMensaje("La clique num: " + Integer.toString(i) + " esta en la comunidad " + Integer.toString(kc.obtNum()));
            obtOut().agregarComunidad(s);
        }
    }

    public void insertar_nodo(int u, int i) throws Exception {
        if (!g.existeVertice(u)) throw new Exception(error2);
        if (i < 0 || i >= obtOut().numComunidades()) throw new Exception(error3);
        Set<Integer> s = obtOut().comunidad_at(i);
        s.add(u);
    }
}
