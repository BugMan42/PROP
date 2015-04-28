package Domini;

public class Arista<T> {

    private T vfin;
    private double peso;

    public Arista(T f, double p) {
        vfin = f;
        peso = p;
    }

    //Consultoras
    public T fin() {
        return vfin;
    }
    public double peso() {
        return peso;
    }

    //Modificadoras
    public void modFin(T f) {
        vfin = f;
    }
    public void modPeso(double p) {
        peso = p;
    }
    public String toString() {
        return vfin+":"+peso;
    }
}
/**     public List<Double> pesos() {
 LinkedList<Double> lista;
 lista = (LinkedList) pesos.clone();
 return lista;
 }*/