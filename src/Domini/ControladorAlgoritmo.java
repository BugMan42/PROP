package Domini;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15/04/15.
 */
public class ControladorAlgoritmo {

    private Entrada in;
    private Algoritmo alg;
    private Salida out;


    //Punteros internos para exportar grafo
    private int i_vertex = 0;
    private int i_arista = 0;
    private int real_i = 0;

    //Referencia a un ControladorRelaciones creado previamente
    private ControladorRelaciones crel;

    public ControladorAlgoritmo(ControladorRelaciones cr)
    {
        out = new Salida();
        in = new Entrada();
        crel = cr;
    }

    // Dado el controlador de relaciones que pasas como parametro, se crea el grafo correspondiente
    public void crearGrafo() throws Exception {
        crel.crearGrafoRelaciones();
        in.modGrafo(crel.crearGrafoAlgoritmo());
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
    public void seleccionAlgoritmo(int s) throws Exception
    {
        if (s == 1) alg = new Girvan_Newman(in, out);
        else if (s == 2) alg = new Louvain(in, out);
        else if (s == 3) alg = new Clique(in, out);
    }


    //Devuelve una referencia de la Salida
    public Salida obtSalida()
    {
        return out;
    }


    public void guardar()
    {

    }

    public void cargar()
    {

    }

    //Funciones para exportar el grafo a Capa de Presentación

    public Integer num_vertex_entrada()
    {
        return in.obtGrafo().V();
    }

    // El string seguirá el formato ("indice"+"dni"), o ("indice"+"-"), si incorrecto.
    public String next_vertex()
    {
        real_i = in.obtGrafo().consultarVertices().get(i_vertex);
        String result = Integer.toString(real_i);
        try {
            result = result+" "+in.obtGrafo().fPrima(real_i);
        } catch (Exception e) {
            e.printStackTrace();
            result = result+" -";
        }

        ++i_vertex;
        i_arista = 0;
        return result;
    }

    public String next_arista()
    {
        String result = Integer.toString(real_i);
        try {
            if (in.obtGrafo().nodosSalida(real_i).size() >= i_arista) result = result+" -";
            else {
                int ady = in.obtGrafo().nodosSalida(real_i).get(i_arista);
                result = result+" "+ady;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = result+" -";
        }
        ++i_arista;
        return result;

    }

    public void reset_pointers()
    {
        i_arista = 0;
        i_vertex = 0;
        real_i = 0;
    }

}
