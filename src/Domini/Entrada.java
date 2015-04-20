package Domini;

/**
 * Created on 15/04/15.
 */
public class Entrada {

    private Grafo graph;
    private int param;

    public Entrada(Grafo g)
    {
        graph = g;
    }

    public void modParam1(String s)
    {
        param = Integer.parseInt(s);
    }

    public int obtParam1()
    {
        return param;
    }

    public Grafo obtGrafo()
    {
        return graph;
    }
}
