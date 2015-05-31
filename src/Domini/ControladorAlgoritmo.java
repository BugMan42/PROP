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
    private int i_comm = 0;
    private int real_i = 0;
    private int i_message = 0;

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
        else if (s == 4) {}
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
        String result = null;
        try {
            if (i_vertex < in.obtGrafo().V()) {
                real_i = in.obtGrafo().consultarVertices().get(i_vertex);
                //System.out.println("Vertex: "+real_i);
                //System.out.println(in.obtGrafo().nodosSalida(real_i).size());
                result = Integer.toString(real_i);
                result = result + " " + in.obtGrafo().fPrima(real_i);
            }
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
        //System.out.println("arista nº: "+i_arista);
        try {
            //System.out.println(in.obtGrafo().nodosSalida(real_i).size()+" >= "+i_arista+"?");
            if (in.obtGrafo().nodosSalida(real_i).size() <= i_arista) result = result+" -";
            else {
                int ady = in.obtGrafo().nodosSalida(real_i).get(i_arista);
                //System.out.println("real vertex nº: "+ady);
                result = result+" "+ady;
                double pes = in.obtGrafo().pesoAristasVertices(real_i, ady);
                result = result+" "+pes;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = result+" -";
        }

        ++i_arista;
        return result;

    }

    public int num_comunidades()
    {
        return out.comunidad().size();
    }

    public String next_community()
    {
        String r = null;

        try {
            r = out.comunidad_at(i_comm).toString();
            r = r.substring(1,r.length()-1);
            ++i_comm;
        } catch (Exception e) {
            r = "-";
        }
        return r;
    }

    public String next_message()
    {
        String r;
        if (i_message < out.mostrarHistorial().size()) r = out.mostrarHistorial().get(i_message);
        else r = "-";
        i_message++;
        return r;
    }

    public void reset_pointers()
    {
        i_arista = 0;
        i_vertex = 0;
        real_i = 0;
        i_comm = 0;
        i_message = 0;
    }

    public void createDemoGraph()
    {
        Grafo g = new Grafo();
        for (int i = 0; i < 10; ++i)
        {
            try {
                g.agregarVertice(Integer.toString(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            g.agregarArista("0", "1", 1);
            g.agregarArista("1", "0", 1);
            g.agregarArista("0", "2", 3);
            g.agregarArista("2", "0", 3);
            g.agregarArista("1", "3", 3);
            g.agregarArista("3", "1", 3);
            g.agregarArista("2", "3", 1);
            g.agregarArista("3", "2", 1);
            g.agregarArista("3", "5", 10);
            g.agregarArista("5", "3", 10);
            g.agregarArista("2", "4", 7);
            g.agregarArista("4", "2", 7);
            g.agregarArista("4", "5", 2);
            g.agregarArista("5", "4", 2);
            g.agregarArista("4", "6", 8);
            g.agregarArista("6", "4", 8);
            g.agregarArista("2","5", 10);
            g.agregarArista("5","2", 10);

        } catch (Exception e) {
            e.printStackTrace();
        }

        in.modGrafo(g);
    }

}
