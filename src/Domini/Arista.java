package Domini;

/**
 * Created by bug on 1/04/15.
 */
public class Arista {
    private Nodo origen;
    private Nodo fin;
    private double peso;

    public Arista(Nodo A, Nodo B, double p) {
        origen = A;
        fin = B;
        peso = p;
    }

    //Consultoras
    public Nodo origen() {
        return origen;
    }
    public Nodo fin() {
        return fin;
    }
    public double peso() {
        return peso;
    }

    //Modificadoras
    public void modOrigen(Nodo A) {
        origen = A;
    }
    public void modFin(Nodo A) {
        fin = A;
    }
    public void modPeso(double p) {
        peso = p;
    }

    public String toString() {
        return origen + " ==> " + fin;
    }
}
