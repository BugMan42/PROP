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

    public Node obtOrigen() {
        return origen;
    }
    public Node obtDestino() {
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
}
