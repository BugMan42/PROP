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
        public NodoInterno(NodoInterno N) {
            pesos = (LinkedList) N.pesos.clone();
            ady = N.ady;
        }
        public int obtenerAdy() {
            return ady;
        }
        public void agregarPeso(double peso) {
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
        AristasNodo(AristasNodo A) {
            clave = A.clave;
            entrada = new ArrayList<NodoInterno>();
            salida = new ArrayList<NodoInterno>();
            for (int i = 0; i < A.entrada.size(); ++i) {
                entrada.add(i,new NodoInterno(A.entrada.get(i)));
            }
            for (int i = 0; i < A.salida.size(); ++i) {
                salida.add(i, new NodoInterno(A.salida.get(i)));
            }
        }

        /** Modificadoras */
        void agregarEntrada(int origen,double peso) {
            //print("Agregar Entrada "+origen);
            if (entrada.isEmpty()) entrada.add(0,new NodoInterno(origen,peso));
            else {
                int i;
                for(i = 0; i < entrada.size(); ++i) {
                    if(entrada.get(i).equals(origen)) {
                        entrada.get(i).agregarPeso(peso);
                        return;
                    }
                    else if (entrada.get(i).obtenerAdy() > origen) {
                        entrada.add(i,new NodoInterno(origen,peso));
                        return;
                    }
                }
                entrada.add(i,new NodoInterno(origen,peso));
            }
        }
        void agregarSalida(int dest,double peso) {
            //print("Agregar Salida "+dest);
            if (salida.isEmpty()) salida.add(0,new NodoInterno(dest,peso));
            else {
                int i;
                for(i = 0; i < salida.size(); ++i) {
                    if(salida.get(i).equals(dest)) {
                        salida.get(i).agregarPeso(peso);
                        return;
                    }
                    else if (salida.get(i).obtenerAdy() > dest) {
                        salida.add(i,new NodoInterno(dest,peso));
                        return;
                    }
                }
                salida.add(i,new NodoInterno(dest,peso));
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
                    salida.get(i).modificarPeso(oldPeso,newPeso);
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
                //print(salida.get(i).total()+" "+i);
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
        vacios = new PriorityQueue<Integer>(); //espacios vacios eficiencia aumentar
    }
    //TODO COMO SE HACE
        public Grafo(Grafo g) throws Exception {
        aristas = new ArrayList<AristasNodo>();
        vacios = new PriorityQueue<Integer>();
        vertices = new TST<Integer>();
        for (int i = 0; i < g.aristas.size(); ++i) {
            aristas.add(i, new AristasNodo(g.aristas.get(i)));
        }
        vertices = new TST(g.vertices);
        if (!g.vacios.isEmpty()) vacios = new PriorityQueue<Integer>(g.vacios);
    }
    public int V() {
        return aristas.size()-vacios.size();
    }
    public int f(String clave) throws Exception {
        try {
            return vertices.obtener(clave);
        }
        catch (Exception a) { }
        throw new Exception("No existe Vertice");
    }
    public String fPrima(int v) throws Exception {
        if (!indexValido(v)) throw new Exception("No existe Vertice");
        return aristas.get(v).clave;
    }


/**##################################################################
//#############################VERTICES##############################
//##################################################################*/

    /** agregar vertice*/
    public void agregarVertice(String v) throws Exception{
        int aux = nextIndice();
        vertices.insertar(v, aux); // Si esta petara
        aristas.add(aux, new AristasNodo(v));
        if (!vacios.isEmpty() && aux == vacios.peek()) vacios.poll();
    }
    public void eliminarVertice(String v) throws Exception{
        eliminarVertice(vertices.obtener(v));
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
       else throw new Exception("vertice No Valido");
    }
    private void eliminarAristas(int v) throws Exception{
        List<Integer> aux = aristas.get(v).obtenerNodosEntrada();
        for (int i = 0; i < aux.size(); ++i) {
            aristas.get(aux.get(i)).eliminarAristasSalida(v);
        }
        aux = aristas.get(v).obtenerNodosSalida();
        for (int i = 0; i < aux.size(); ++i) {
            aristas.get(aux.get(i)).eliminarAristasEntrada(v);
        }
    }

    public  ArrayList<Integer> consultarVertices() {
        ArrayList<Integer> Array = new ArrayList<Integer>();
        for(int i = 0; i < aristas.size(); ++i) {
            if (! (aristas.get(i) == null)) {
                Array.add(i);
            }
        }
        return Array;
    }
    public ArrayList<String> consultarVerticesID() {
        ArrayList<String> Array = new ArrayList<String>();
        for(int i = 0; i < aristas.size(); ++i) {
            if (! (aristas.get(i) == null)) {
                Array.add(aristas.get(i).clave);
            }
        }
        return Array;
    }
    public boolean existeVertice(String v) {
        return vertices.existe(v);
    }
    public boolean existeVertice(int v) {
        return indexValido(v);
    }
    public void modificarVertice(String idVieja, String idNueva) throws Exception {
        //if (vertices.existe(idNueva)) throw new Exception("Clave repetida");
        vertices.modificar(idVieja,idNueva);
    }

//######################################################################
//######################################################################
    private boolean indexValido(int index) {
        if (aristas.isEmpty()) return false;
        if (aristas.size()-1 < index) return false;
        return !(aristas.get(index) == null);
    }

/**##################################################################
//#############################ARISTAS##############################
//##################################################################*/
    /**Falta poner agregar arista sin sentido */
    public void agregarArista(String origen,String fin, double peso) throws Exception{
        agregarArista(f(origen), f(fin), peso);
    }

    public void agregarArista(int origen,int fin, double peso) throws Exception {
        if (!indexValido(origen)) throw new Exception("Index No valido");
        if (!indexValido(fin)) throw new Exception("Index No Valido");
        aristas.get(origen).agregarSalida(fin,peso);
        aristas.get(fin).agregarEntrada(origen,peso);
    }

    public void modificarArista(int origen, int fin,double oldPeso, double newPeso) throws Exception {
        if (!indexValido(origen)) throw new Exception("Index No valido");
        if (!indexValido(fin)) throw new Exception("Index No Valido");
        aristas.get(origen).modificarSalida(fin, oldPeso, newPeso);
        aristas.get(fin).modificarEntrada(origen, oldPeso, newPeso);
    }
    //eliminara todas las aristas desde origen a fin y fin a a origen
    public void eliminarAristas(String origen,String fin) throws Exception{
        eliminarAristas(f(origen), f(fin));
    }
    public void eliminarAristas(int origen,int fin) throws Exception {
        if (!indexValido(origen)) throw new Exception("Index No valido");
        if (!indexValido(fin)) throw new Exception("Index No Valido");
        aristas.get(origen).eliminarAristasSalida(fin);
        aristas.get(fin).eliminarAristasEntrada(origen);
    }
    //elimina una sola arista con peso especifico
    public void eliminarArista(String origen, String fin, double peso) throws Exception{
        eliminarArista(f(origen), f(fin), peso);
    }
    public void eliminarArista(int origen,int fin,double peso) throws Exception {
        if (!indexValido(origen)) throw new Exception("Index No valido");
        if (!indexValido(fin)) throw new Exception("Index No Valido");
        aristas.get(fin).eliminarAristaEntrada(origen, peso);
        aristas.get(origen).eliminarAristaSalida(fin, peso);
    }
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

//##############################################################
//##############################################################
//##############################################################
    public List<Double> obtenerListaPesos(String A, String B) throws Exception {
        return obtenerListaPesos(f(A), f(B));
    }
    public List<Double> obtenerListaPesos(int A, int B) throws Exception {
        if (!indexValido(A)) throw new Exception("Index no valido");
        if (!indexValido(B)) throw new Exception("Index no valido");
        return aristas.get(A).obtenerPesosSalida(B);
    }
    public double totalPesoSalida(String A) throws Exception{
        return totalPesoSalida(f(A));
    }
    public double totalPesoSalida(int A) throws Exception {
        if (!indexValido(A)) throw new Exception("Index no valido");
        return aristas.get(A).totalPesoSalida();
    }
    public double totalPesoEntrada(String A) throws Exception {
        return totalPesoEntrada(f(A));
    }
    public double totalPesoEntrada(int A) throws Exception {
        if (!indexValido(A)) throw new Exception("Index no valido");
        return aristas.get(A).totalPesoEntrada();
    }
    public List<Integer> nodosSalida(String A) throws Exception{
        return nodosSalida(f(A));
    }
    public List<Integer> nodosSalida(int A) throws Exception {
        if (!indexValido(A)) throw new Exception("Index no valido");
        List<Integer> aux = aristas.get(A).obtenerNodosSalida();
        return aux;
    }
    public List<Integer> nodosEntrada(String A) throws Exception {
        return nodosEntrada(f(A));
    }
    public List<Integer> nodosEntrada(int A) throws Exception{
        if (!indexValido(A)) throw new Exception("Index no valido");
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
        return vertices.size() == 0;
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

    private void print(String v) {
        System.out.println(v);
    }



//########################################################################
/**##########################CONSULTORAS################################*/
//########################################################################


}