package Domini;

/**
 * Created on 15/04/15.
 */
public class Entrada {

    private Grafo graph;
    private double param;
    private double param2;
    private double param3;

    public Entrada() {}

    public Entrada(Grafo g, double p)
    {
        graph = g;
        param = p;
    }

    public Entrada(Grafo g, double p, double p2)
    {
        graph = g;
        param = p;
        param2 = p2;
    }

    public void modParam1(double p)
    {
        param = p;
    }

    public double obtParam1()
    {
        return param;
    }

    public void modParam2(double p)
    {
        param2 = p;
    }

    public double obtParam2()
    {
        return param2;
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
