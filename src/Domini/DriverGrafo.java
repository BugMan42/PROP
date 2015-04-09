package Domini;

/**
 * Created by jose on 2/04/15.
 */
public class DriverGrafo {
    public static void main(String[] args) {
        Congresista c1 = new Congresista("12345678A","Paco","Sobaco",8,"Springfield","Massachusets","Republicano");
        Congresista c2 = new Congresista("12345678B", "Barack","Obama",53,"Springfield","Massachusets", "Republicano");
        Nodo n1 = new Nodo(c1);
        Nodo n2 = new Nodo(c2);
        Arista a = new Arista(n1, n2, 3);

        Grafo g = new Grafo();
        g.add_nodo(n1);
        g.add_nodo(n2);
        g.add_arista(a);

        g.printNodos();
        g.printAristas();
    }

    public void main() {
    }
}
