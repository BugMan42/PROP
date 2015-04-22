package Domini;

/**
 * Created on 15/04/15.
 */
public class ControladorAlgoritmo {
    private Entrada in;
    private Algoritmo alg;
    private Salida out;

    public ControladorAlgoritmo()
    {
        out = new Salida();
    }

    public void crearEntrada(Grafo g, float p)
    {
        in = new Entrada(g, p);
    }

    public void seleccionAlgoritmo(String s)
    {
        if (s.equals("gn")) alg = new Girvan_Newman();
        else if (s.equals("lv")) alg = new Louvain();
        else if (s.equals("cl")) alg = new Clique();
    }


    public Grafo ejecutar()
    {
        return alg.ejecutar_algoritmo(in, out);

    }
}
