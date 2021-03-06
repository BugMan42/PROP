package Domini;

import java.util.Random;

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

    public void modParam2Entrada(String p) // Serán Strings
    {
        in.modParam2(Double.parseDouble(p));
    }

    /*
    * Selección de algoritmo
    * Pre:  s contiene una de las 3 opciones disponibles.
    *       Suponemos que los controladores se encargan de enviar la información correcta
    * Post: alg es el tipo de algoritmo
    */
    public void seleccionAlgoritmo(int s) throws Exception
    {
        out = new Salida();
        if (s == 1)
        {
            alg = new Girvan_Newman(in, out);
            out.mostrarHistorial1().add(0, "Inicio");
            out.agregarMensaje1("Fin");

        }
        else if (s == 2)
        {
            alg = new Louvain(in, out);
            out.mostrarHistorial2().add(0, "Inicio");
            out.agregarMensaje2("Fin");
        }
        else if (s == 3)
        {
            alg = new Clique(in, out);
            out.mostrarHistorial3().add(0, "Inicio");
            out.agregarMensaje3("Fin");
        }

    }


    //Devuelve una referencia de la Salida
    public Salida obtSalida()
    {
        return out;
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

    public void mod_i_vertex(int i)
    {
        if (i > 0 && i < in.obtGrafo().V())
        {
            i_vertex = i;
            real_i = in.obtGrafo().consultarVertices().get(i_vertex);
            i_arista = 0;
        }
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

    public void mod_i_arista (int i)
    {
        int lim = -1;
        try {
            lim = in.obtGrafo().nodosSalida(real_i).size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0 && i < lim)
        {
            i_arista = i;
        }
    }

    public int num_aristas_salida(int x)
    {
        int r_i = in.obtGrafo().consultarVertices().get(x);
        int r = -1;
        try {
            r = in.obtGrafo().nodosSalida(r_i).size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public int num_comunidades1()
    {
        return out.comunidad1().size();
    }
    public int num_comunidades2()
    {
        return out.comunidad2().size();
    }
    public int num_comunidades3()
    {
        return out.comunidad3().size();
    }

    public String next_community1()
    {
        String r;

        try {
            r = out.comunidad1_at(i_comm).toString();
            r = r.substring(1,r.length()-1);
            ++i_comm;
        } catch (Exception e) {
            r = "-";
        }
        return r;
    }

    public String next_community2()
    {
        String r;

        try {
            r = out.comunidad2_at(i_comm).toString();
            r = r.substring(1,r.length()-1);
            ++i_comm;
        } catch (Exception e) {
            r = "-";
        }
        return r;
    }

    public String next_community3()
    {
        String r;

        try {
            r = out.comunidad3_at(i_comm).toString();
            r = r.substring(1,r.length()-1);
            ++i_comm;
        } catch (Exception e) {
            r = "-";
        }
        return r;
    }

    public int num_mensajes1()
    {
        return out.mostrarHistorial1().size();
    }
    public int num_mensajes2()
    {
        return out.mostrarHistorial2().size();
    }
    public int num_mensajes3()
    {
        return out.mostrarHistorial3().size();
    }

    public String next_message1()
    {
        String r;
        if (i_message < out.mostrarHistorial1().size()) r = out.mostrarHistorial1().get(i_message);
        else r = "-";
        i_message++;
        return r;
    }

    public String next_message2()
    {
        String r;
        if (i_message < out.mostrarHistorial2().size()) r = out.mostrarHistorial2().get(i_message);
        else r = "-";
        i_message++;
        return r;
    }

    public String next_message3()
    {
        String r;
        if (i_message < out.mostrarHistorial3().size()) r = out.mostrarHistorial3().get(i_message);
        else r = "-";
        i_message++;
        return r;
    }

    public String message_at1(int i)
    {
        String r;
        if (i >= 0 && i < out.mostrarHistorial1().size()) r = out.mostrarHistorial1().get(i);
        else r = "-";
        return r;
    }

    public String message_at2(int i)
    {
        String r;
        if (i >= 0 && i < out.mostrarHistorial2().size()) r = out.mostrarHistorial2().get(i);
        else r = "-";
        return r;
    }

    public String message_at3(int i)
    {
        String r;
        if (i >= 0 && i < out.mostrarHistorial3().size()) r = out.mostrarHistorial3().get(i);
        else r = "-";
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
        Random generator = new Random();
        int n = generator.nextInt(500);
        Grafo g = new Grafo();
        for (int i = 0; i < 11; ++i)
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

            g.agregarArista("7", "8", 3);
            g.agregarArista("8", "7", 3);

            g.agregarArista("7", "9", 5);
            g.agregarArista("9", "7", 5);

            g.agregarArista("7", "10", 4);
            g.agregarArista("10", "7", 4);

            g.agregarArista("8", "9", 6);
            g.agregarArista("9", "8", 6);

            g.agregarArista("9", "10", 7);
            g.agregarArista("10", "9", 7);

            g.agregarArista("8", "10", 8);
            g.agregarArista("10", "8", 8);



        } catch (Exception e) {
            e.printStackTrace();
        }

        in.modGrafo(g);
    }

    public String obtLabel(int x) throws Exception {
        return in.obtGrafo().fPrima(x);
    }

    public void guardar_salida(String ruta)
    {

    }

    public void limpiar_salida()
    {
        out.eliminarSalida();
    }

}
