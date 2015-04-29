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
        in = new Entrada();
    }

    public void modEntrada(Grafo g, String p) // Serán Strings
    {
        in.modGrafo(g);
        in.modParam1(p);
    }

    /*
    * Selección de algoritmo
    * Pre:  s contiene una de las 3 opciones disponibles.
    *       Suponemos que los controladores se encargan de enviar la información correcta
    * Post: alg es el tipo de algoritmo
    */
    public void seleccionAlgoritmo(String s)
    {
        if (s.equals("g")) alg = new Girvan_Newman(in, out);
        //else if (s.equals("l")) alg = new Louvain(in, out); #falta descomentar en Louvain
        else if (s.equals("c")) alg = new Clique(in, out);
    }


    public Grafo ejecutar()
    {
        return alg.ejecutar_algoritmo();
    }

    /*
    * Ejecutar una iteración del algoritmo
    * Pre:  alg tiene que tener una entrada inicializada y un tipo seleccionado
    * Post: Devuelve un grafo resultado de una iteración del algoritmo del tipo seleccionado
     */
    public Grafo ejecutar_iteracion()
    {
        return alg.ejecutar_iteración(in.obtGrafo());
    }


}
