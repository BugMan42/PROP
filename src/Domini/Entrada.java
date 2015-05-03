package Domini;

/**
 * Created on 15/04/15.
 */
public class Entrada {

    private Grafo graph;
    private double param;

    public Entrada() {}

    public Entrada(Grafo g, double p)
    {
        graph = g;
        param = p;
    }

    public void modParam1(double p)
    {
        param = p;
    }

    public double obtParam1()
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
