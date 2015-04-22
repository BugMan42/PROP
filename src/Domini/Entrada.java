package Domini;

/**
 * Created on 15/04/15.
 */
public class Entrada {

    private Grafo graph;
    private float param;

    public Entrada(Grafo g, float p)
    {
        graph = g;
        param = p;
    }

    public void modParam1(String s)
    {
        param = Integer.parseInt(s);
    }

    public float obtParam1()
    {
        return param;
    }

    public Grafo obtGrafo()
    {
        return graph;
    }

    public void modGrafo(Grafo g)
    {
        graph = g;
    }
}
