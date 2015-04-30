package Domini;

import java.util.*;


//Grafo algoritmo
//String--->Clave
public class Grafo {

//#########################################################################
/**##############################NodoInterno##############################*/
//#########################################################################

    class NodoInterno {
        private LinkedList<Double> pesos;
        public int ady;
        public NodoInterno(int nodoDest,double peso) {
            pesos = new LinkedList<Double>();
            pesos.add(peso);
            ady = nodoDest;
        }
        public int obtenerAdy() {
            return ady;
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
        public void modificarPeso(double oldPeso, double newPeso) {
            ListIterator<Double> i = pesos.listIterator();
            while (i.hasNext()) {
                if (i.next() == oldPeso) i.set(newPeso);

            }
        }
        public int hashCode() {
            return ady;
        }
        public boolean equals(int d) {
            return (d == ady);
        }
        public boolean equals(NodoInterno n) {
            return n.ady == ady;
        }
    }
//#########################################################################
/**##############################AristasNodo##############################*/
//#########################################################################

    //predecesores=Antecesores=entrada
    //sucesores=salida
    private class AristasNodo {
        private List<NodoInterno> entrada;
        private List<NodoInterno> salida;
        String clave;

        AristasNodo(String key) {
            entrada = new ArrayList<NodoInterno>();
            salida = new ArrayList<NodoInterno>();
            clave = key;
        }

        /** Modificadoras */
        void agregarEntrada(int origen,double peso) {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(origen)) {
                    entrada.get(i).añadirPeso(peso);
                    return;
                }
                else if (entrada.get(i).obtenerAdy() > origen) {
                    entrada.add(i,new NodoInterno(origen,peso));
                    return;
                }
            }
        }
        void agregarSalida(int dest,double peso) {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    salida.get(i).añadirPeso(peso);
                    return;
                }
                else if (salida.get(i).obtenerAdy() > dest) {
                    salida.add(i,new NodoInterno(dest,peso));
                    return;
                }
            }
        }

        void eliminarAristasEntrada(int origen) throws Exception {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(origen)) {
                    entrada.remove(i);
                    return;
                }
            }
            throw new Exception("Arista Entrada no existe");
        }
        void eliminarAristasSalida(int dest) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    salida.remove(i);
                    return;
                }
            }
            throw new Exception("Arista Salida no existe");
        }
        void eliminarAristaEntrada(int dest,double peso) throws Exception {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(dest)) {
                    entrada.get(i).eliminarArista(peso);
                    return;
                }
            }
            throw new Exception("Arista Entrada no existe");
        }
        void eliminarAristaSalida(int dest,double peso) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    salida.get(i).eliminarArista(peso);
                    return;
                }
            }
            throw new Exception("Arista Salida no existe");
        }

        void modificarEntrada(int origen,double oldPeso, double newPeso) throws Exception {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(origen)) {
                    entrada.get(i).modificarPeso(oldPeso, newPeso);
                    return;
                }
            }
            throw new Exception("Arista Entrada no existe");
        }
        void modificarSalida(int dest,double oldPeso, double newPeso) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    salida.get(i);
                    return;
                }
            }
            throw new Exception("Arista Salida no existe");
        }
        void modificarClave(String key) {
            clave = key;
        }


        /** CONSULTORAS */
        int degreeSalida() {
            return salida.size();
        }
        int degreeEntrada() {
            return entrada.size();
        }
        String obtenerClave() {
            return clave;
        }
        boolean entradaVacia() {
            return entrada.isEmpty();
        }
        boolean salidaVacia() {
            return salida.isEmpty();
        }

        boolean existeAristaEntrada(int origen) {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(origen)) {
                    return true;
                }
            }
            return false;
        }
        boolean existeAristaEntrada(int origen, double peso) {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(origen)) {
                    return entrada.get(i).existePeso(peso);
                }
            }
            return false;
        }
        boolean existeAristaSalida(int dest) {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    return true;
                }
            }
            return false;
        }
        boolean existeAristaSalida(int dest, double peso) {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    return salida.get(i).existePeso(peso);
                }
            }
            return false;
        }

        double pesoAristasVertice(int dest) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    return salida.get(i).total();
                }
            }
            throw new Exception("Vertice dest no valido");
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

        List<Integer> obtenerNodosSalida(){
            ArrayList<Integer> aux = new ArrayList<Integer>();
            for(int i = 0; i < salida.size(); ++i) {
                aux.add(salida.get(i).ady);
            }
            return aux;
        }
        List<Integer> obtenerNodosEntrada(){
            ArrayList<Integer> aux = new ArrayList<Integer>();
            for(int i = 0; i < entrada.size(); ++i) {
                aux.add(entrada.get(i).ady);
            }
            return aux;
        }
        List<Double> obtenerPesosEntrada(int origen) throws Exception {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(origen)) {
                    return entrada.get(i).listaPesos();
                }
            }
            throw new Exception("Arista Entrada no existe");
        }
        List<Double> obtenerPesosSalida(int dest) throws Exception {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    return salida.get(i).listaPesos();
                }
            }
            throw new Exception("Arista salida no existe");
        }
        boolean equals(AristasNodo A) {
            return A.clave == clave;
        }
    }

/**#########################################################################
//###############################GRAFO######################################
//#########################################################################*/

    private List<AristasNodo> aristas;//
    private TST<Integer> vertices;         //String to number
    private PriorityQueue<Integer> vacios; //espacios vacios eficiencia aumentar

    public Grafo() {
        aristas = new ArrayList<AristasNodo>();
        vertices = new TST<Integer>();       //String to number
        vacios = new PriorityQueue<Integer>(1 ,Collections.reverseOrder()); //espacios vacios eficiencia aumentar
    }
    //TODO COMO SE HACE
    public Grafo(Grafo g) {
        //aristas = (List) g.aristas.clone();

    }
    public int V() {
        return aristas.size()-vacios.size();
    }
    public String fPrima(int nodo) throws Exception {
        if (aristas.get(nodo).equals(null)) throw new Exception("No existe Vertice");
        return aristas.get(nodo).clave;
    }
    public Integer f(String clave) throws Exception {
        return vertices.obtener(clave);
    }
/**##################################################################
//#############################VERTICES##############################
//##################################################################*/
    /** añadir vertice*/
    public void añadirVertice(String v) throws Exception{
        int aux = nextIndice();
        vertices.insertar(v, aux); // Si esta petara
        aristas.add(aux, new AristasNodo(v));
        if (aux == vacios.peek()) vacios.poll();
    }
    //TODO ELIMINAR vertice complejo
    public void eliminarVertice(String v) throws Exception{
        int aux = vertices.obtener(v);
        vacios.add(aux);
        eliminarvertices(aux);
    }
    private void eliminarvertices(int index) {
        aristas.set(index, null);
    }

    public  ArrayList<Integer> consultarVertices() {
        ArrayList<Integer> Array = new ArrayList<Integer>();
        for(int i = 0; i < aristas.size(); ++i) {
            if (!aristas.get(i).equals(null)) {
                Array.add(i);
            }
        }
        return Array;
    }
    public boolean existeVertice(String v) {
        return vertices.existe(v);
    }
    public void modificarVertice(String idVieja, String idNueva) throws Exception {
        vertices.modificar(idVieja,idNueva);
    }

//##############################################################
//##############################################################
//##############################################################
    public List<Double> obtenerListaPesos(String A, String B) throws Exception {
        return obtenerListaPesos(f(A), f(B));
    }
    public List<Double> obtenerListaPesos(int A, int B) throws Exception {
        return aristas.get(A).obtenerPesosSalida(B);
    }
    public double totalPesoSalida(String A) throws Exception{
        return totalPesoSalida(f(A));
    }
    public double totalPesoSalida(int A) throws Exception {
        return aristas.get(A).totalPesoSalida();
    }
    public double totalPesoEntrada(String A) throws Exception {
        return totalPesoEntrada(f(A));
    }
    public double totalPesoEntrada(int A) throws Exception {
        return aristas.get(A).totalPesoEntrada();
    }
    public List<Integer> nodosSalida(String A) throws Exception{
        return nodosSalida(f(A));
    }
    public List<Integer> nodosSalida(int A) throws Exception {
        List<Integer> aux = aristas.get(A).obtenerNodosSalida();
        return aux;
    }
    public List<Integer> nodosEntrada(String A) throws Exception {
        return nodosEntrada(f(A));
    }
    public List<Integer> nodosEntrada(int A) throws Exception{
        List<Integer> aux = aristas.get(A).obtenerNodosEntrada();
        return aux;
    }
    public double pesoAristasVertices(int origen, int fin) throws Exception {
        if (!indexValido(origen)) throw new Exception("Index No valido");
        if (!indexValido(fin)) throw new Exception("Index No Valido");
        return aristas.get(origen).pesoAristasVertice(fin);

    }
//##############################################################
/**############################################################*/
//##############################################################
    public boolean vacio() {
        return aristas.size() > 0;
    }
    public int degreeEntrada(String v) throws Exception {
        return degreeEntrada(f(v));
    }
    public int degreeEntrada(int v) throws Exception {
        if (!indexValido(v)) throw new Exception("index no valido");
        return aristas.get(v).degreeEntrada();
    }
    public int degreeSalida(String v) throws Exception {
        return degreeSalida(f(v));
    }
    public int degreeSalida(int v) throws Exception {
        if (!indexValido(v)) throw new Exception("index no valido");
        return aristas.get(v).degreeSalida();
    }


//##################################################################
//############################PRIVADAs##############################
//##################################################################
    private int nextIndice() {
        if(vacios.isEmpty()) return aristas.size();
        else return vacios.peek();
    }
    private boolean indexValido(int index) {
        return aristas.get(index).equals(null);
    }
    private void print(String v) {
        System.out.println(v);
    }

//##################################################################
//#############################ARISTAS##############################
//##################################################################
/**Falta poner añadir arista sin sentido */
    public void añadirArista(String origen,String fin, double peso) throws Exception{
        añadirArista(f(origen), f(fin), peso);
    }

    public void añadirArista(int origen,int fin, double peso) {
        //int aux2 = f((V)e.fin());
        AristasNodo ent = aristas.get(origen);
        ent.agregarEntrada(fin, peso);
        aristas.set(origen, ent);
        AristasNodo sal = aristas.get(fin);
        sal.agregarSalida(origen, peso);
        aristas.set(fin, sal);
    }

    public void modificarArista(int A, int B,double oldPeso, double newPeso) throws Exception {
        aristas.get(A).modificarSalida(B, oldPeso, newPeso);
        aristas.get(B).modificarEntrada(A, oldPeso, newPeso);
    }
    //eliminara todas las aristas desde origen a fin y fin a a origen
    public void eliminarAristas(String origen,String fin) throws Exception{
        eliminarAristas(f(origen), f(fin));
    }
    public void eliminarAristas(int A,int B) throws Exception {
        aristas.get(A).eliminarAristasSalida(B);
        aristas.get(B).eliminarAristasEntrada(A);
    }
    //elimina una sola arista con peso especifico
    public void eliminarArista(String origen, String fin, double peso) throws Exception{
        eliminarArista(f(origen), f(fin), peso);
    }
    public void eliminarArista(int origen,int fin,double peso) throws Exception {
        aristas.get(fin).eliminarAristaEntrada(origen, peso);
        aristas.get(origen).eliminarAristaSalida(fin, peso);
    }

//########################################################################
/**##########################CONSULTORAS################################*/
//########################################################################

    public boolean existeArista(String A, String B) throws Exception {
        return existeArista(f(A), f(B));
    }
    public boolean existeArista(int origen, int fin) throws Exception {
        if (!indexValido(origen)) throw new Exception("Index No valido");
        if (!indexValido(fin)) throw new Exception("Index No Valido");
        return aristas.get(origen).existeAristaSalida(fin) && aristas.get(fin).existeAristaEntrada(origen);
    }
    public boolean existeAristaPeso(int origen, int fin, double peso) throws Exception {
        if (!indexValido(origen)) throw new Exception("Index No valido");
        if (!indexValido(fin)) throw new Exception("Index No Valido");
        return aristas.get(origen).existeAristaSalida(fin,peso) && aristas.get(fin).existeAristaEntrada(origen,peso);
    }
}