package Domini;

import java.util.*;


//Grafo algoritmo
public class Grafo<V, E extends Arista> {

    class NodoInterno {
        private LinkedList<Double> pesos;
        public int ady;
        public NodoInterno(int nodoDest,double peso) {
            pesos = new LinkedList<Double>();
            pesos.add(peso);
            ady = nodoDest;
        }
        public void añadirPeso(double peso) {
            pesos.add(peso);
        }
        public void eliminarArista(double peso) {
            pesos.remove(peso);
        }
        public List<Double> listaPesos() {
            LinkedList<Double> r =  new LinkedList<Double>();
            r = (LinkedList) pesos.clone();
            return r;
        }
        public double total() {
            double a = 0.0;
            ListIterator<Double> i = pesos.listIterator();
            while (i.hasNext()) {
                a += i.next();
            }
            return a;
        }
        public boolean existePeso(double peso) {
            ListIterator<Double> i = pesos.listIterator();
            while (i.hasNext()) {
                if (i.next() == peso) return true;
            }
            return false;
        }
        public boolean equals(int d) {
            return (d == ady);
        }

        public void modificarPeso(double oldPeso, double newPeso) {
            ListIterator<Double> i = pesos.listIterator();
            while (i.hasNext()) {
                if (i.next() == oldPeso) i.set(newPeso);

            }
        }

    }

    class AristasNodo {
        //predecesores
        //sucesores
        private ArrayList<NodoInterno> antecesores; //Set?????
        private ArrayList<NodoInterno> sucesores;

        AristasNodo() {
            antecesores = new ArrayList<NodoInterno>();
            sucesores = new ArrayList<NodoInterno>();
        }
        boolean existeAristaEntrada(int origen) {
            for(int i = 0; i < antecesores.size(); ++i) {
                if(antecesores.get(i).equals(origen)) {
                    return true;
                }
            }
            return false;
        }
        boolean existeAristaEntrada(int origen, double peso) {
            for(int i = 0; i < antecesores.size(); ++i) {
                if(antecesores.get(i).equals(origen)) {
                    return antecesores.get(i).existePeso(peso);
                }
            }
            return false;
        }
        boolean existeAristaSalida(int dest) {
            for(int i = 0; i < sucesores.size(); ++i) {
                if(sucesores.get(i).equals(dest)) {
                    return true;
                }
            }
            return false;
        }
        boolean existeAristaSalida(int dest, double peso) {
            for(int i = 0; i < sucesores.size(); ++i) {
                if(sucesores.get(i).equals(dest)) {
                    return sucesores.get(i).existePeso(peso);
                }
            }
            return false;
        }
        boolean entradaVacia() {
            return antecesores.isEmpty();
        }
        boolean salidaVacia() {
            return sucesores.isEmpty();
        }
        //falta eficiencia
        void añadirEntrada(int dest,double peso) {

            antecesores.add(new NodoInterno(dest,peso));
        }
        //falta eficiencia
        void añadirSalida(int dest,double peso) {
            sucesores.add(new NodoInterno(dest,peso));
        }
        void añadirAristaEntrada(int dest,double peso) {
            for(int i = 0; i < antecesores.size(); ++i) {
                if(antecesores.get(i).equals(dest)) {
                    antecesores.remove(i);
                    return;
                }
            }
        }
        void añadirAristaSalida(int dest,double peso) {
            for(int i = 0; i < sucesores.size(); ++i) {
                if(sucesores.get(i).equals(dest)) {
                    sucesores.remove(i);
                    return;
                }
            }
        }
        void eliminarAristasPredecesoras(int dest) {
            for(int i = 0; i < antecesores.size(); ++i) {
                if(antecesores.get(i).equals(dest)) {
                    antecesores.remove(i);
                    return;
                }
            }

        }
        void eliminarAristasSucesoras(int dest) {
            for(int i = 0; i < sucesores.size(); ++i) {
                if(sucesores.get(i).equals(dest)) {
                    sucesores.remove(i);
                    return;
                }
            }
        }
        void eliminarAristaPredecesora(int dest,double peso) {
            for(int i = 0; i < antecesores.size(); ++i) {
                if(antecesores.get(i).equals(dest)) {
                    antecesores.get(i).eliminarArista(peso);
                    return;
                }
            }

        }
        void eliminarAristaSucesora(int dest,double peso) {
            for(int i = 0; i < sucesores.size(); ++i) {
                if(sucesores.get(i).equals(dest)) {
                    sucesores.get(i).eliminarArista(peso);
                    return;
                }
            }
        }
        double totalPesoSucesores() {
            double a = 0.0;
            for(int i = 0; i < sucesores.size(); ++i) {
                a+=sucesores.get(i).total();
            }
            return a;
        }
        double totalPesoAntecesores() {
            double a = 0.0;
            for(int i = 0; i < antecesores.size(); ++i) {
                a+=antecesores.get(i).total();
            }
            return a;
        }
        ArrayList<Integer> obtenerNodosSucesores(){
            ArrayList<Integer> aux = new ArrayList<Integer>();
            for(int i = 0; i < sucesores.size(); ++i) {
                aux.add(sucesores.get(i).ady);
            }
            return aux;
        }
        ArrayList<Integer> obtenerNodosAntecesores(){
            ArrayList<Integer> aux = new ArrayList<Integer>();
            for(int i = 0; i < sucesores.size(); ++i) {
                aux.add(sucesores.get(i).ady);
            }
            return aux;
        }
        List<Double> obtenerPesosAdyacente(int dest) {
            List<Double> aux = new LinkedList<Double>();
            for(int i = 0; i < sucesores.size(); ++i) {
                if(sucesores.get(i).equals(dest)) {
                    return sucesores.get(i).listaPesos();
                }
            }
            return aux;
        }



        /** TODO FALTA MODIFICAR BIEN*/
        public void modificarPredecesores(int nodoDest,double oldPeso, double newPeso) {
            for(int i = 0; i < antecesores.size(); ++i) {
                if(antecesores.get(i).equals(nodoDest)) {
                    //antecesores.set(i)
                    return;
                }
            }
        }

        public void modificarSucesores(int nodoDest,double oldPeso, double newPeso) {
            for(int i = 0; i < sucesores.size(); ++i) {
                if(sucesores.get(i).equals(nodoDest)) {
                    //sucesores.set(i,e);
                    return;
                }
            }
        }
    }

    private ArrayList<AristasNodo> aristas;//
    private TST<Integer> vertices;            //String to number
    private ArrayList<String> fPrima;       //Int to String
    private PriorityQueue<Integer> vacios; //espacios vacios eficiencia aumentar

    public Grafo() {
        aristas = new ArrayList<AristasNodo>();
        vertices = new TST<Integer>();       //String to number
        fPrima = new ArrayList<String>();  //Int to String
        vacios = new PriorityQueue<Integer>(1 ,Collections.reverseOrder()); //espacios vacios eficiencia aumentar
    }
    public Grafo(Grafo g) {

    }

//##############################################################
//##############################################################
//##############################################################
    public List<Double> pesosAdyacentes(String A, String B) throws Exception{
        return pesosAdyacentes(f(A),f(B));
    }
    public List<Double> pesosAdyacentes(int A, int B) {
        return aristas.get(A).obtenerPesosAdyacente(B);
    }
    public double totalPesoSucesores(String A) throws Exception{
        return totalPesoSucesores(f(A));
    }
    public double totalPesoSucesores(int A) {
        return aristas.get(A).totalPesoSucesores();
    }
    public double totalPesoAntecesores(String A) throws Exception {
        return totalPesoAntecesores(f(A));
    }
    public double totalPesoAntecesores(int A) {
        return aristas.get(A).totalPesoSucesores();
    }
    public ArrayList<Integer> nodosSucesores(String A) throws Exception{
        return nodosSucesores(f(A));
    }
    public ArrayList<Integer> nodosSucesores(int A) {
        ArrayList<Integer> aux = aristas.get(A).obtenerNodosSucesores();
        return aux;
    }
    public ArrayList<Integer> nodosAntecesores(String A) throws Exception {
        return nodosAntecesores(f(A));
    }
    public ArrayList<Integer> nodosAntecesores(int A) {
        ArrayList<Integer> aux = aristas.get(A).obtenerNodosAntecesores();
        return aux;
    }
    public ArrayList<Integer> nodosAdyacentes(int A) {
        ArrayList<Integer> aux = aristas.get(A).obtenerNodosAntecesores();
        return aux;
    }
//##############################################################
//##############################################################
//##############################################################
    public boolean vacio() {
        return aristas.size() > 0;
    }
    //retorna num de aristas
    public int E() {
        return 0;
    }
    public double total() {
        //calcular total
        return 0.0;
    }
    public double pesoAristasVertice(int nodo) {
        return 0.0;
    }
    public double pesoAristaVertices(int origen, int fin) {
        return 0.0;
    }
    public void modPesoAristaVertices(int origen, int fin, double peso) {}
    public int degree(int v) {
        //return aristas[v].size();
        return 1;
    }


//##################################################################
//############################PRIVADAs##############################
//##################################################################
    private int nextIndice() {
        if(vacios.isEmpty()) return aristas.size();
        else return vacios.peek();
    }
    public int traducir(String v)throws Exception {
        return f(v);
    }
    private int f(String v) throws Exception {
        return vertices.obtener(v);
    }
    private String traducir(int index) {
        return fPrima.get(index);
    }
//##################################################################
//############################VERTICES##############################
//##################################################################
    /** añadir vertice*/
    public void añadirVertice(String v) throws Exception{
        int aux = nextIndice();
        vertices.insertar(v, aux);
        if (vertices.obtener(v) == aux) {
            fPrima.add(aux,v);
            aristas.add(aux, new AristasNodo());
            if (!vacios.isEmpty()) vacios.poll();
        }
    }
    public void eliminarVertice(String v) throws Exception{
        //print("first"+" "+v);
       // printStrings();
        int aux = vertices.obtener(v);
        vacios.add(aux);
        //vertices.eliminar(v);
        fPrima.set(aux, null);
        eliminarvertices(aux);
        //printStrings();

    }
    void printVertices() {
        //for (i = 0; i < f)
    }
    void printStrings() {
        print(fPrima.toString());
    }
    private void eliminarvertices(int index) {
        aristas.set(index, new AristasNodo());
    }
    private void print(String v) {
        System.out.println(v);
    }
    public  ArrayList<Integer> consultarVertices() {

        ArrayList<Integer> Array = new ArrayList<Integer>();
        for(int i = 0; i < fPrima.size(); ++i) {
            //print(fPrima.get(i));
            if (fPrima.get(i) != null) Array.add(i);
        }
        return Array;
    }
    public ArrayList<String> consultarVerticesID() {
        ArrayList<String> Array = new ArrayList<String>();
        for(int i = 0; i < fPrima.size(); ++i) {
            String aux = fPrima.get(i);
            if (aux != null) Array.add(aux);
        }
        return Array;
    }
    public int V() {
        return aristas.size()-vacios.size();
    }
    public boolean existeVertice(String v) {
        return vertices.existe(v);
    }
    public String consultarVertice(int index) {
        return traducir(index);
    }
    public Integer consultarVertice(String v) throws Exception {
        return f(v);
    }
    public void modificarVertice(String idVieja, String idNueva) throws Exception {
        vertices.modificar(idVieja,idNueva);
    }






//##################################################################
//#############################ARISTAS##############################
//##################################################################
/**Falta poner añadir arista sin sentido */
    public void añadirArista(String origen,String fin, double peso) throws Exception{
        añadirArista(f(origen),f(fin), peso);
    }

    private void añadirArista(int origen,int fin, double peso) {
        //int aux2 = f((V)e.fin());
        AristasNodo ent = aristas.get(origen);
        ent.añadirAristaEntrada(fin, peso);
        aristas.set(origen, ent);
        AristasNodo sal = aristas.get(fin);
        sal.añadirSalida(origen,peso);
        aristas.set(fin, sal);
    }

    private void modificarArista(int A, int B,double oldPeso, double newPeso) {
        aristas.get(A).modificarSucesores(B, oldPeso, newPeso);
        aristas.get(B).modificarPredecesores(A, oldPeso, newPeso);
    }

    //eliminara todas las aristas desde origen a fin y fin a a origen
    public void eliminarAristas(String origen,String fin) throws Exception{
        eliminarAristas(f(origen), f(fin));
    }
    public void eliminarAristas(int A,int B) {
        aristas.get(A).eliminarAristasSucesoras(B);
        aristas.get(B).eliminarAristasPredecesoras(A);
    }

    //elimina una sola arista con peso especifico
    public void eliminarArista(String origen, String fin, double peso) throws Exception{
        eliminarArista(f(origen),f(fin), peso);
    }
    public void eliminarArista(int origen,int fin,double peso) {
        aristas.get(fin).eliminarAristaPredecesora(origen, peso);
        aristas.get(origen).eliminarAristaSucesora(fin, peso);
    }

//########################################################################
//##########################CONSULTORAS###################################
//########################################################################

    /*TODO:::: COMPROBACION EXISTENCIA ARISTAS*/

    public int size() {
        return aristas.size();
    }

    /*public boolean existeArista(String origen, E e) {
        return existeArista(trad(origen),e);
    }*/

   /* public boolean existeArista(int origen, E e) {
        int aux = trad((V)e.fin());
        AristasNodo ent = aristas.get(aux);
        AristasNodo sal = aristas.get(origen);
        return ent.existeAristaEntrada(origen,e.peso()) && sal.existeAristaSalida(aux,e.peso());
    }
    public boolean existeAristas(String A, String B) {
        return existeAristas(trad(A),trad(B));
    }
    public boolean existeAristas(int A, int B) {
        AristasNodo ent = aristas.get(A);
        AristasNodo sal = aristas.get(B);
        return ent.existeAristaEntrada(B) && sal.existeAristaSalida(A);
    }
    /*public ArrayList<E> consultarAristasSalida(int indice){
        return ady.get(indice).salida;
    }
    public ArrayList<E> consultarAristasEntrada(int indice){
        return ady.get(indice).antecesores;
    }*/



}