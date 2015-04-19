package Domini;

public class Arista {
    private int fin;
    private double peso;

    public Arista(int f, int p) {
        fin = f;
        peso = p;
    }

    //Consultoras
    public int fin() {
        return fin;
    }
    public double peso() {
        return peso;
    }
    //Modificadoras
    public void modFin(int f) {
        fin = f;
    }
    public void modPeso(double p) {
        peso = p;
    }
    public String toString() {
        return fin+":"+peso;
    }



}
    /*
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
}*/
