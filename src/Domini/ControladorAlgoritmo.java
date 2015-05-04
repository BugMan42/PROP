package Domini;

/**
 * Created on 15/04/15.
 */
public class ControladorAlgoritmo {

    private Entrada in;
    private Algoritmo alg;
    private Salida out;
    //Referencia a un ControladorRelacioes creado previamente
    private ControladorRelaciones crel;

    public ControladorAlgoritmo(ControladorRelaciones cr)
    {
        out = new Salida();
        in = new Entrada();
        crel = cr;
    }

    // Dado el controlador de relaciones que pasas como parametro, se crea el grafo correspondiente
    public void crearGrafo()
    {
        //in.modGrafo(crel.generar_grafo_relaciones());
    }

    //Modifica el parámetro de la entrada
    public void modParamEntrada(String p) // Serán Strings
    {
        in.modParam1(Double.parseDouble(p));
    }

    /*
    * Selección de algoritmo
    * Pre:  s contiene una de las 3 opciones disponibles.
    *       Suponemos que los controladores se encargan de enviar la información correcta
    * Post: alg es el tipo de algoritmo
    */
    public void seleccionAlgoritmo(String s) throws Exception
    {
        if (s.equals("g")) alg = new Girvan_Newman(in, out);
        //else if (s.equals("l")) alg = new Louvain(in, out); #falta descomentar en Louvain
        else if (s.equals("c")) alg = new Clique(in, out);
    }

    //Ejecuta el algoritmo seleccionado según la entrada creada(in), y escribe resultados en la salida (out)
    public void ejecutar() throws Exception
    {
        alg.ejecutar_algoritmo();
    }

    /*
    * Ejecutar una iteración del algoritmo
    * Pre:  alg tiene que tener una entrada inicializada y un tipo seleccionado
    * Post: Devuelve un grafo resultado de una iteración del algoritmo del tipo seleccionado
     */
    public void ejecutar_iteracion()throws Exception
    {
        alg.ejecutar_iteración(in.obtGrafo());
    }

    //Devuelve una refernecia de la Salida
    public Salida obtSalida()
    {
        return out;
    }

}
