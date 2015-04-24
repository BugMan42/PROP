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

    }

    class AristasNodo {
        //predecesores
        //sucesores
        private ArrayList<NodoInterno> entrada;
        private ArrayList<NodoInterno> salida;

        AristasNodo() {
            entrada = new ArrayList<NodoInterno>();
            salida = new ArrayList<NodoInterno>();
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
        boolean entradaVacia() {
            return entrada.isEmpty();
        }
        boolean salidaVacia() {
            return salida.isEmpty();
        }
        //falta eficiencia
        void añadirEntrada(int dest,double peso) {
            entrada.add(new NodoInterno(dest,peso));
        }
        //falta eficiencia
        void añadirSalida(int dest,double peso) {
            entrada.add(new NodoInterno(dest,peso));
        }
        void añadirAristaEntrada(int dest,double peso) {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(dest)) {
                    entrada.remove(i);
                    return;
                }
            }
        }
        void añadirAristaSalida(int dest,double peso) {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    salida.remove(i);
                    return;
                }
            }
        }
        void eliminarEntrada(int dest) {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(dest)) {
                    entrada.remove(i);
                    return;
                }
            }

        }
        void eliminarSalida(int dest) {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    salida.remove(i);
                    return;
                }
            }
        }
        void eliminarAristaEntrada(int dest,double peso) {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(dest)) {
                    entrada.get(i).eliminarArista(peso);
                    return;
                }
            }

        }
        void eliminarAristaSalida(int dest,double peso) {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(dest)) {
                    salida.get(i).eliminarArista(peso);
                    return;
                }
            }
        }

        /*
        public void modificarEntrada(int nodoDest,double peso) {
            for(int i = 0; i < entrada.size(); ++i) {
                if(entrada.get(i).equals(nodoDest)) {
                    entrada.set(i,peso());
                    return;
                }
            }
        }

        public void modificarSalida(E e) {
            for(int i = 0; i < salida.size(); ++i) {
                if(salida.get(i).equals(e)) {
                    salida.set(i,e);
                    return;
                }
            }
        }*/



    }

    private int Vt; // ---- > No fa falta
    private int Et;
    private double total;
    private ArrayList<AristasNodo> ady;
    private TST<Integer> T;      //String to number
    private ArrayList<String> A; //Int to String
    private ArrayList<Integer> vacios; //espacios vacios eficiencia aumentar

    public Grafo() {
        ady = new ArrayList<AristasNodo>();
        T = new TST<Integer>();       //String to number
        A = new ArrayList<String>();  //Int to String
        vacios = new ArrayList<Integer>(); //espacios vacios eficiencia aumentar
        Vt = 0;
        Et = 0;
    }

    public Grafo(Grafo g) {

    }
    public void añadirVertice(V v) {
        ++Vt;
    }
    public void eliminarVertice() {

    }
    public ArrayList<E> consultarVertices() {
        return new ArrayList<E>();
    }
    /*public ArrayList<String> consultarVerticesID() {

    }
    public E consultarVertice(String id) {
        //return ady.get(id);
    }*/
    public boolean vacio() {
        return ady.size() > 0;
    }
    public int numVertices() {
        return ady.size();
    }

    //retorna num de vertices
    public int V() {
        return Vt;
    }

    //retorna num de aristas
    public int E() {
        return Et;
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
        //return ady[v].size();
        return 1;
    }

    public ArrayList<Integer> nodosAdyacentes(int a) {
        return new ArrayList<Integer>();
    }

    private int trad(V v) {
        return T.obtener(v.toString());
    }




    //////////////////////////////////////////////////////////////////////
    //////////////////////AAAAristassss///////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    private void añadirArista(V origen,E e) {
        añadirArista(trad(origen), e);

    }
    private void añadirArista(int origen,E e) {
        int aux2 = trad((V)e.fin());
        AristasNodo ent = ady.get(origen);
        ent.añadirAristaEntrada(aux2, e.peso());
        ady.set(origen, ent);
        AristasNodo sal = ady.get(aux2);
        sal.añadirSalida(origen,e.peso());
        ady.set(aux2, sal);
    }

    /*private void modificarArista(E2 e, int in, int out) {
        AristasNodo ent = aristas.get(in);
        ent.modificarEntrada(e);
        aristas.set(in, ent);
        AristasNodo sal = aristas.get(out);
        sal.modificarSalida(e);
        aristas.set(out, sal);
    }*/

    //eliminara todas las aristas desde origen a fin y fin a a origen
    public void eliminarAristas(V origen,E e) {
        eliminarAristas(trad(origen), e);
    }
    public void eliminarAristas(int origen,E e) {
        int fin = trad((V)e.fin());
        AristasNodo ent = ady.get(origen);
        ent.eliminarEntrada(fin);
        ady.set(origen, ent);
        AristasNodo sal = ady.get(fin);
        sal.eliminarSalida(origen);
        ady.set(fin, sal);
    }

    //elimina una sola arista con peso especifico
    public void eliminarArista(V origen, E e) {
        eliminarArista(trad(origen), e);
    }
    public void eliminarArista(int origen, E e) {
        int fin = trad((V)e.fin());
        AristasNodo ent = ady.get(origen);
        ent.eliminarAristaEntrada(fin, e.peso());
        ady.set(origen, ent);
        AristasNodo sal = ady.get(fin);
        sal.eliminarAristaSalida(origen, e.peso());
        ady.set(fin, sal);
    }
    public void eliminarAristaEntrada(V origen,E e) {
        eliminarAristaEntrada(trad(origen), e);
    }

    public void eliminarAristaEntrada(int origen,E e) {
        int fin = trad((V)e.fin());
        AristasNodo ent = ady.get(origen);
        ent.eliminarAristaEntrada(fin,e.peso());
        ady.set(origen, ent);
    }
    public void eliminarAristaSalida(V origen, E e) {
        eliminarAristaSalida(trad(origen),e);
    }

    public void eliminarAristaSalida(int origen,E e) {
        int fin = trad((V)e.fin());
        AristasNodo sal = ady.get(origen);
        sal.eliminarAristaSalida(fin,e.peso());
        ady.set(origen, sal);
    }
    public boolean existeArista(V origen, E e) {
        return existeArista(trad(origen),e);
    }

    public boolean existeArista(int origen, E e) {
        int aux = trad((V)e.fin());
        AristasNodo ent = ady.get(aux);
        AristasNodo sal = ady.get(origen);
        return ent.existeAristaEntrada(origen,e.peso()) && sal.existeAristaSalida(aux,e.peso());
    }
    public boolean existeAristas(V A, V B) {
        return existeAristas(trad(A),trad(B));
    }
    public boolean existeAristas(int A, int B) {
        AristasNodo ent = ady.get(A);
        AristasNodo sal = ady.get(B);
        return ent.existeAristaEntrada(B) && sal.existeAristaSalida(A);
    }
    /*public ArrayList<E> consultarAristasSalida(int indice){
        return ady.get(indice).salida;
    }
    public ArrayList<E> consultarAristasEntrada(int indice){
        return ady.get(indice).entrada;
    }*/

}