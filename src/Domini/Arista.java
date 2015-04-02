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
    public Nodo obt_origen() {
        return origen;
    }
    public Nodo obt_fin() {
        return fin;
    }
    public double obt_peso() {
        return peso;
    }

    //Modificadoras
    public void mod_origen(Nodo A) {
        origen = A;
    }
    public void mod_fin(Nodo A) {
        fin = A;
    }
    public void mod_peso(double p) {
        peso = p;
    }

    public String toString() {
        return origen + " ==> " + fin;
    }
}
