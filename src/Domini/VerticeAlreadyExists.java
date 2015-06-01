package Domini;

public class VerticeAlreadyExists extends Exception {
    public VerticeAlreadyExists(String v) {
        super("Ya existe el Vertice: "+v);
    }
}
