package Domini;


public class Edge<N extends Node> {
    N origen;
    N destino;
    double peso;

    public Edge(N a, N b, double c) {
        origen = a;
        destino = b;
        peso = c;
    }
    public Edge clone() {
        return new Edge(origen.clone(),destino.clone(),peso);
    }

    public N obtOrigen() {
        return origen;
    }
    public N obtDestino() {
        return destino;
    }
    public double obtPeso() {
        return peso;
    }
    public void modOrigen(N a) {
        origen = a;
    }
    public void modDestino(N b) {
        destino = b;
    }
    public void modPeso(double c) {
        peso = c;
    }
    public boolean equals(Edge aux) {
        return origen == aux.origen && destino == aux.destino && peso == aux.peso;
    }
    public String toString() {
        return origen.toString()+" "+destino.toString()+" "+peso;
    }
}
