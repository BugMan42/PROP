package Domini;

import scala.Array;

import java.util.*;


public class GrafoNodoArista<V extends Node, E extends Edge> {





    private class Edges {
        LinkedList<E> aristas;
        int mapOrigen;
        int mapFin;
        public Edges(E e,int maporigen, int mapfin) {
            aristas = new LinkedList<E>();
            aristas.add(e);
            mapOrigen = mapOrigen;
            mapFin = mapFin;
        }
        public int obtMapOrigen() {
            return mapOrigen;
        }
        public int obtMapFin() {
            return mapFin;
        }
        public Edges(Edges e) {
            aristas = (LinkedList) e.aristas.clone();
        }
        public boolean esVacio() {
            return aristas.isEmpty();
        }
        /*public int obtenerAdy() {
            return ady;
        }*/
        public void agregarArista(E e) {
            aristas.add(e);
        }
        // funciona???????
        public void eliminarArista(E e) {
            aristas.remove(e);
        }
        public List<Double> listaPesos() {
            LinkedList<Double> r =  new LinkedList<Double>();
            for (E e:aristas) {
                r.add(e.peso);
            }
            return r;
        }
        public List<E> listaAristas() {
            LinkedList<E> r =  (LinkedList) aristas.clone();
            return r;
        }
        public double total() {
            double a = 0.0;
            for (E e:aristas) {
                a+=e.peso;
                }
                return a;
            }
        public boolean existePeso(double peso) {
            for (E a:aristas) {
                if (a.peso == peso) return true;
            }
            return false;
        }
        public boolean existeArista(E e) {
            for (E a:aristas) {
                if (a.equals(e)) return true;
            }
            return false;
        }
        public void modificarPeso(double oldPeso, double newPeso) {
            for (E a:aristas) {
                if (a.peso == oldPeso) a.modPeso(newPeso);
            }
        }

        public V obtOrigen() {
            if (aristas.isEmpty()) return null;
            else return (V) aristas.get(0).obtOrigen();
        }
        public V obtFin() {
            if (aristas.isEmpty()) return null;
            else return (V) aristas.get(0).obtDestino();
        }
        }
    // entrada = antecesores(las aristas que entran al nodo) ---> Nodo
    // salida = sucesores(las aristas que salen del nodo) Nodo ---->
    private class EdgesVertex {
        VertexTst map;            // puntero tst
        List<Edges> entrada; // aristas entrada
        List<Edges> salida;  // aristas salida

        public VertexTst obtenerMap() {
            return map;
        }
        public EdgesVertex(VertexTst aux) {
            map = aux;
            entrada = new LinkedList<Edges>();
            salida = new LinkedList<Edges>();
        }
        public EdgesVertex(EdgesVertex aux,VertexTst aux2) {
            map = aux2;
            entrada = new ArrayList<Edges>();
            salida = new ArrayList<Edges>();
            for (int i = 0; i < aux.entrada.size(); ++i) {
                entrada.add(i,new Edges(aux.entrada.get(i)));
            }
            for (int i = 0; i < aux.salida.size(); ++i) {
                salida.add(i, new Edges(aux.salida.get(i)));
            }
        }
        public void addAristaEntrada(E e, int indexOrigen) {
            if (entrada.isEmpty()) entrada.add(new Edges(e,indexOrigen,map.obtMap()));
            else {
                int i;
                for (i = 0; i < entrada.size(); ++i) {
                    if (entrada.get(i).obtMapOrigen() == indexOrigen ) {
                        entrada.get(i).agregarArista(e);
                        return;
                    }
                    else if (entrada.get(i).obtMapOrigen() > indexOrigen) {
                        entrada.add(i,new Edges(e,indexOrigen,map.obtMap()));
                        return;
                    }
                }
                entrada.add(i,new Edges(e,indexOrigen,map.obtMap()));
            }
        }
        public void addAristaSalida(E e, int indexSalida) {
            if (salida.isEmpty()) salida.add(new Edges(e,indexSalida,map.obtMap()));
            else {
                int i;
                for (i = 0; i < salida.size(); ++i) {
                    if (salida.get(i).obtMapOrigen() == indexSalida ) {
                        salida.get(i).agregarArista(e);
                        return;
                    }
                    else if (salida.get(i).obtMapOrigen() > indexSalida) {
                        salida.add(i,new Edges(e,indexSalida,map.obtMap()));
                        return;
                    }
                }
                salida.add(i,new Edges(e,indexSalida,map.obtMap()));
            }
        }
        void eliminarAristasEntrada(int origen) throws Exception {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).obtMapOrigen() == origen) {
                    entrada.remove(i);
                    return;
                }
            }
            throw new Exception("Arista Entrada no existe");
        }
        void eliminarAristasSalida(int dest) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).obtMapFin() == dest) {
                    salida.remove(i);
                    return;
                }
            }
            throw new Exception("Arista Salida no existe");
        }
        void eliminarAristaEntrada(int origen,E e) throws Exception {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).obtMapOrigen() == origen) {
                    entrada.get(i).eliminarArista(e);
                    if (entrada.get(i).esVacio()) entrada.remove(i);
                    return;
                }
            }
            throw new Exception("Arista Entrada "+origen+" no existe");
        }
        void eliminarAristaSalida(int dest,E e) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).obtMapFin() == dest) {
                    salida.get(i).eliminarArista(e);
                    if (salida.get(i).esVacio()) salida.remove(i);
                    return;
                }
            }
            throw new Exception("Arista Salida"+dest+"no existe");
        }
        void modificarEntrada(E e, double newPeso) throws Exception {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).obtOrigen().equals((V)e.obtOrigen())) {
                    entrada.get(i).modificarPeso(e.peso, newPeso);
                    return;
                }
            }
            throw new Exception("Arista Entrada no existe");
        }
        void modificarSalida(E e, double newPeso) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals((V)e.obtDestino())) {
                    salida.get(i).modificarPeso(e.peso, newPeso);
                    return;
                }
            }
            throw new Exception("Arista Salida no existe");
        }
        /** CONSULTORAS */
        int degreeSalida() {
            return salida.size();
        }
        int degreeEntrada() {
            return entrada.size();
        }
        String obtenerClave() {
            return map.obtV().obtID();
        }
        boolean entradaVacia() {
            return entrada.isEmpty();
        }
        boolean salidaVacia() {
            return salida.isEmpty();
        }

        boolean existeAristaEntrada(V v) {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).obtOrigen().equals(v)) {
                    return true;
                }
            }
            return false;
        }
        boolean existeAristaEntrada(E e) {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).obtOrigen().equals((V) e.obtOrigen())) {
                    return entrada.get(i).existeArista(e);
                }
            }
            return false;
        }
        boolean existeAristaSalida(V v) {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).obtFin().equals(v)) {
                    return true;
                }
            }
            return false;
        }
        boolean existeAristaSalida(E e) {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).obtFin().equals((V) e.obtDestino())) {
                    return salida.get(i).existeArista(e);
                }
            }
            return false;
        }

        double pesoAristasVertice(V v) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                //print(salida.get(i).total()+" "+i);
                if(salida.get(i).obtFin().equals(v)) {
                    return salida.get(i).total();
                }
            }
            throw new Exception("No existe ninguna arista con este destino"+v.obtID());
        }
        double totalPesoEntrada () {
            double a = 0.0;
            for(int i = 0; i < entrada.size(); ++i) {
                a+=entrada.get(i).total();
            }
            return a;
        }
        double totalPesoSalida() {
            double a = 0.0;
            for(int i = 0; i < salida.size(); ++i) {
                a+=salida.get(i).total();
            }
            return a;
        }
        //revisar
        List<V> obtenerNodosSalida(){
            ArrayList<V> aux = new ArrayList<V>();
            for(int i = 0; i < salida.size(); ++i) {
                aux.add(salida.get(i).obtFin());
            }
            return aux;
        }
        List<Integer> obtenerNodosSalidaI(){
            ArrayList<Integer> aux = new ArrayList<Integer>();
            for(int i = 0; i < salida.size(); ++i) {
                aux.add(salida.get(i).obtMapFin());
            }
            return aux;
        }
        List<Integer> obtenerNodosEntradaI(){
            ArrayList<Integer> aux = new ArrayList<Integer>();
            for(int i = 0; i < entrada.size(); ++i) {
                aux.add(entrada.get(i).obtMapOrigen());
            }
            return aux;
        }
        List<V> obtenerNodosEntrada(){
            ArrayList<V> aux = new ArrayList<V>();
            for(int i = 0; i < entrada.size(); ++i) {
                aux.add(entrada.get(i).obtOrigen());
            }
            return aux;
        }
        List<E> obtenerPesosEntrada(int origen) throws Exception {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(origen)) {
                    return entrada.get(i).listaAristas();
                }
            }
            return new ArrayList<E>();
        }
        List<E> obtenerPesosSalida(int dest) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    return salida.get(i).listaAristas();
                }
            }
            return new ArrayList<E>();
        }


    }
    private class VertexTst {
        V v;
        int map;
        public VertexTst(V v1, int map1) {
            v = v1;
            map = map1;
        }
        public VertexTst clone() {
            return new VertexTst((V)v.clone(),map);
        }
        public void modV(V v1) {
            v = v1;
        }
        public void modMap(int map1) {
            map = map1;
        }
        public V obtV() {
            return v;
        }
        public int obtMap() {
            return map;
        }

    }
//##############################################################################
    private TST<VertexTst> vertices;
    private ArrayList<EdgesVertex> aristas;
    private PriorityQueue<Integer> vacios;

    public GrafoNodoArista() {
        vertices = new TST<VertexTst>();              // almacena los objetos v
        aristas = new ArrayList<EdgesVertex>(); // lista de adyacencia
        vacios = new PriorityQueue<Integer>();   // para reaprovechar
    }
    public GrafoNodoArista(GrafoNodoArista g) throws Exception {
        aristas = new ArrayList<EdgesVertex>();
        vacios = new PriorityQueue<Integer>();
        vertices = new TST<VertexTst>();
        vertices = new TST(g.vertices);
        for (int i = 0; i < g.aristas.size(); ++i) {
            EdgesVertex a = (EdgesVertex) g.aristas.get(i);
            aristas.add(i, new EdgesVertex(a, vertices.obtener(a.obtenerClave())));
        }
        if (!g.vacios.isEmpty()) vacios = new PriorityQueue<Integer>(g.vacios);
    }
    public int size() {
        return aristas.size()-vacios.size();
    }
    public int f(V v) throws Exception {
        try {
            return vertices.obtener(v.obtID()).map;
        }
        catch (Exception a) {
            throw new VerticeNotFound(v.obtID());
        }
    }
    private boolean indexValido(int index) {
        if (aristas.isEmpty()) return false;
        if (aristas.size()-1 < index) return false;
        return !(aristas.get(index) == null);
    }

    public String fPrima(int v) throws Exception {
        if (!indexValido(v)) throw new VerticeNotFound(v);
        return aristas.get(v).obtenerClave();
    }
    public V fprima(int v) throws Exception {
        if (!indexValido(v)) throw new VerticeNotFound(v);
        return aristas.get(v).obtenerMap().obtV();
    }

    public int obtNext() {
        ArrayList<V> a = new ArrayList<V>();
        ArrayList<V> b = (ArrayList<V>)a.clone();
        return 1;
    }
    private int nextIndice() {
        if(vacios.isEmpty()) return aristas.size();
        else return vacios.peek();
    }

    private static void print(String v) {
        System.out.println(v);
    }
    public boolean vacio() {
        return vertices.size() == 0;
    }

    public int degreeEntrada(V v) throws Exception {
        return degreeEntrada(f(v));
    }
    private int degreeEntrada(int v) throws Exception {
        if (!indexValido(v)) throw new VerticeNotFound(v);
        return aristas.get(v).degreeEntrada();
    }
    public int degreeSalida(V v) throws Exception {
        return degreeSalida(f(v));
    }
    private int degreeSalida(int v) throws Exception {
        if (!indexValido(v)) throw new VerticeNotFound(v);
        return aristas.get(v).degreeSalida();
    }
//################################VERTICES###############################################33

    public void addNodo(V v) throws Exception {
        int aux = obtNext();
        VertexTst a = new VertexTst(v,aux);
        vertices.insertar(v.obtID(), a);
        aristas.set(aux,new EdgesVertex(a));

    }
    public void agregarVertice(V v) throws Exception{
        int aux = nextIndice();
        insertarVerticeInterno(v, aux);
        aristas.add(aux, new EdgesVertex(vertices.obtener(v.obtID())));
        if (!vacios.isEmpty() && aux == vacios.peek()) vacios.poll();
    }
    private void insertarVerticeInterno(V v, int aux) throws Exception {
        try {
            vertices.insertar(v.obtID(), new VertexTst(v,aux)); // Si esta petara
        } catch (Exception a) {
            throw new VerticeAlreadyExists(v.obtID());
        }

    }

    public void eliminarVertice(V v) throws Exception{
        try {
            eliminarVertice(vertices.obtener(v.obtID()).obtMap());
        }
        catch (Exception a) {
            throw new VerticeNotFound(v.obtID());
        }
    }
    //TODO ELIMINAR vertice complejo
    public void eliminarVertice(int v) throws  Exception {
        if (indexValido(v)) {
            vertices.borrar(fPrima(v));
            vacios.add(v);
            eliminarAristas(v);
            aristas.set(v, null);
            if (vertices.esVacio()) {
                aristas.clear();
                vacios.clear();
            }
            //print(vacios+"");
        }
        else throw new VerticeNotFound(v);
    }
    private void eliminarAristas(int v) throws Exception{
        List<Integer> aux = aristas.get(v).obtenerNodosEntradaI();
        for (int i = 0; i < aux.size(); ++i) {
            aristas.get(aux.get(i)).eliminarAristasSalida(v);
        }
        aux = aristas.get(v).obtenerNodosSalidaI();
        for (int i = 0; i < aux.size(); ++i) {
            aristas.get(aux.get(i)).eliminarAristasEntrada(v);
        }
    }

    public ArrayList<Integer> consultarVertices() {
        ArrayList<Integer> Array = new ArrayList<Integer>();
        for(int i = 0; i < aristas.size(); ++i) {
            if (! (aristas.get(i) == null)) {
                Array.add(i);
            }
        }
        return Array;
    }
    public ArrayList<V> consultarVerticesID() {
        ArrayList<V> Array = new ArrayList<V>();
        for(int i = 0; i < aristas.size(); ++i) {
            if (! (aristas.get(i) == null)) {
                Array.add(aristas.get(i).obtenerMap().obtV());
            }
        }
        return Array;
    }
    public boolean existeVertice(V v) {
        return vertices.existe(v.obtID());
    }
    public boolean existeVertice(int v) {
        return indexValido(v);
    }
    public void modificarClaveVertice(String idVieja, V v) throws Exception {
        //if (!existeVertice(idVieja)) throw new VerticeAlreadyExists();
        try {
            vertices.modificar(idVieja,v.obtID());
            vertices.obtener(v.obtID()).modV(v);
            //aristas.get(f(idNueva)).modificarClave(idNueva);
        }
        catch(Exception a) {
            if (a.getClass().equals(KeyNotExistsTST.class)) throw new VerticeNotFound(idVieja);
            if (a.getClass().equals(KeyAlreadyExistsTST.class)) throw new VerticeAlreadyExists(v.obtID());
            //throw new VerticeAlreadyExists(idNueva);
        }
    }
    public void modificarClaveVertice(String idVieja, String idNueva) throws Exception {
        //if (!existeVertice(idVieja)) throw new VerticeAlreadyExists();
        try {
            vertices.modificar(idVieja, idNueva);
            //vertices.obtener(v.obtID()).modV(v);
            //aristas.get(f(idNueva)).modificarClave(idNueva);
        }
        catch(Exception a) {
            if (a.getClass().equals(KeyNotExistsTST.class)) throw new VerticeNotFound(idVieja);
            if (a.getClass().equals(KeyAlreadyExistsTST.class)) throw new VerticeAlreadyExists(idNueva);
            //throw new VerticeAlreadyExists(idNueva);
        }
    }
    /*public V obtVertice(int a) throws Exception {
        if (indexValido(a)) return aristas.get(a).obtenerMap().obtV();
        else
    }*/
/**########################################################################3 */


    public void agregarArista(E e) throws Exception {
        int origen = f((V)e.obtOrigen());
        int fin = f((V)e.obtDestino());
        if (!indexValido(origen)) throw new VerticeNotFound(origen);
        if (!indexValido(fin)) throw new VerticeNotFound(fin);
        aristas.get(fin).addAristaEntrada(e,origen);
        aristas.get(origen).addAristaSalida(e,fin);

       // agregarArista(f((V)e.obtOrigen()), f((V)e.obtDestino()),e.obtPeso());
    }

    /*private void agregarArista(int origen,int fin, double peso) throws Exception {
        if (!indexValido(origen)) throw new VerticeNotFound(origen);
        if (!indexValido(fin)) throw new VerticeNotFound(fin);
        aristas.get(origen).agregarSalida(fin,peso);
        aristas.get(fin).agregarEntrada(origen, peso);
    }*/
    /*public void modificarArista(String origen, String fin,double oldPeso, double newPeso) throws Exception {
        modificarArista(f(origen), f(fin), oldPeso, newPeso);
    }

    public void modificarArista(int origen, int fin,double oldPeso, double newPeso) throws Exception {
        if (!indexValido(origen)) throw new VerticeNotFound(origen);
        if (!indexValido(fin)) throw new VerticeNotFound(fin);
        aristas.get(origen).modificarSalida(fin, oldPeso, newPeso);
        aristas.get(fin).modificarEntrada(origen, oldPeso, newPeso);
    }*/
    //eliminara todas las aristas desde origen a fin y fin a a origen
    public void eliminarAristas(V origen, V fin) throws Exception{
        eliminarAristas(f(origen), f(fin));
    }
    private void eliminarAristas(int origen,int fin) throws Exception {
        if (!indexValido(origen)) throw new VerticeNotFound(origen);
        if (!indexValido(fin)) throw new VerticeNotFound(fin);
        aristas.get(origen).eliminarAristasSalida(fin);
        aristas.get(fin).eliminarAristasEntrada(origen);
    }
    //elimina una sola arista con peso especifico
    public void eliminarArista(E e) throws Exception{
        int origen = f((V)e.obtOrigen());
        int fin = f((V)e.obtDestino());
        if (!indexValido(origen)) throw new VerticeNotFound(origen);
        if (!indexValido(fin)) throw new VerticeNotFound(fin);
        aristas.get(fin).eliminarAristaEntrada(origen,e);
        aristas.get(origen).eliminarAristaSalida(fin, e);
        //eliminarArista(f((V)e.obtOrigen()), f(fin), peso);

    }
    /*
    public void eliminarArista(int origen,int fin,double peso) throws Exception {
        if (!indexValido(origen)) throw new VerticeNotFound(origen);
        if (!indexValido(fin)) throw new VerticeNotFound(fin);
        aristas.get(fin).eliminarAristaEntrada(origen, peso);
        aristas.get(origen).eliminarAristaSalida(fin, peso);
    }*/
    public boolean existeArista(V A, V B) throws Exception {
        //if (!indexValido(origen)) throw new VerticeNotFound(origen);
        //if (!indexValido(fin)) throw new VerticeNotFound(fin);
        return aristas.get(f(A)).existeAristaSalida(B) && aristas.get(f(B)).existeAristaEntrada(A);

        //return existeArista(f(A), f(B));
    }
   /* public boolean existeArista(int origen, int fin) throws Exception {
        if (!indexValido(origen)) throw new VerticeNotFound(origen);
        if (!indexValido(fin)) throw new VerticeNotFound(fin);
        return aristas.get(origen).existeAristaSalida(fin) && aristas.get(fin).existeAristaEntrada(origen);
    }*/
    public boolean existeAristaPeso(E e) throws Exception {
        return aristas.get(f((V)e.obtOrigen())).existeAristaSalida(e) && aristas.get(f((V)e.obtDestino())).existeAristaEntrada(e);
        //return existeAristaPeso(f(origen),f(fin),peso);
    }
    /*public boolean existeAristaPeso(int origen, int fin, double peso) throws Exception {
        if (!indexValido(origen)) throw new VerticeNotFound(origen);
        if (!indexValido(fin)) throw new VerticeNotFound(fin);
        return aristas.get(origen).existeAristaSalida(fin,peso) && aristas.get(fin).existeAristaEntrada(origen,peso);
    }*/

//#####################################################################################33



    //Pruebas
    public void printNodos() {
        print(vertices.consultarClaves()+"");
    }

    public static void main(String[] args) throws Exception{
        GrafoNodoArista<Congresista,Edge> a = new GrafoNodoArista<Congresista,Edge>();
        Congresista C1 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C2 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
        Congresista C3 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
        Congresista C4 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
        Congresista C5 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");
        Congresista C6 = new Congresista(new Dni("00000000F"), "Jon-Von", "Neumann", 53, "Budapest", "AU", "Pirata");
        Congresista C7 = new Congresista(new Dni("00000000G"), "Alan", "Turing", 41, "London", "LO", "Liberal");
        a.agregarVertice(C1);
        a.agregarVertice(C2);
        a.agregarVertice(C3);
        a.agregarVertice(C4);
        //print();
        a.printNodos();
    }

}


/*
/*private class Edges {
        LinkedList<E> aristas;
        public Edges(E e) {
            aristas = new LinkedList<E>();
            aristas.add(e);
        }
        public Edges(Edges e) {
            aristas = (LinkedList) e.aristas.clone();
        }
        public boolean esVacio() {
            return aristas.isEmpty();
        }
        /*public int obtenerAdy() {
            return ady;
        }
        public void agregarArista(E e) {
            aristas.add(e);
        }
// funciona???????
        public void eliminarArista(E e) {
            aristas.remove(e);
       // }
        /*public List<Double> listaPesos() {
            LinkedList<Double> r =  new LinkedList<Double>();
            for (E e:aristas) {
                r.add(e.peso);
            }
            return r;
        }
        public List<E> listaAristas() {
            LinkedList<E> r =  (LinkedList) aristas.clone();
            return r;
        }
        public double total() {
            double a = 0.0;
            for (E e:aristas) {
                a+=e.peso;
            }
            return a;
        }
        /*public boolean existePeso(double peso) {
            for (E a:aristas) {
                if (a.peso == peso) return true;
            }
            return false;
        }
        public boolean existeArista(E e) {
            for (E a:aristas) {
                if (a.equals(e)) return true;
            }
            return false;
        }
        /*public void modificarPeso(double oldPeso, double newPeso) {
            for (E a:aristas) {
                if (a.peso == oldPeso) a.modPeso(newPeso);
            }
        }*/
        /*
        public V obtOrigen() {
            if (aristas.isEmpty()) return null;
            else return (V) aristas.get(0).obtOrigen();
        }
        public V obtFin() {
            if (aristas.isEmpty()) return null;
            else return (V) aristas.get(0).obtDestino();
        }
    }
 */
