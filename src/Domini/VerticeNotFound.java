package Domini;

public class VerticeNotFound extends Exception {
    public VerticeNotFound(int a) {
        super("No existe el Vertice(i): "+a);
    }
    public VerticeNotFound(String a) {
        super("No existe el Vertice(Key): "+a);
    }
}
