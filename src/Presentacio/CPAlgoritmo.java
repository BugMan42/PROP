package Presentacio;


import Domini.ControladorAlgoritmo;
import Domini.Salida;

public class CPAlgoritmo {

    private PanelAlgoritmo PA;
    private ControladorAlgoritmo CA;
    private CPRelaciones CPR;

    public CPAlgoritmo(CPRelaciones cpr) {

        CA = new ControladorAlgoritmo(cpr.obtCR());
        CPR = cpr;
    }

    public PanelAlgoritmo obtPanel() {
        if (PA == null) PA = new PanelAlgoritmo(this);
        return PA;
    }

    public void createDemoGraph()
    {
        CA.createDemoGraph();
    }

    public void execute_algoritm(int i)
    {
        try {
            CA.seleccionAlgoritmo(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int num_aristas() {
        return 100;//CA.num_aristas();
    }

    public int num_vertices()
    {
        return CA.num_vertex_entrada();
    }

    public String next_vertex()
    {
        return CA.next_vertex();
    }

    public void mod_i_vertex (int i)
    {
        CA.mod_i_vertex(i);
    }

    public String next_arista()
    {
        return CA.next_arista();
    }

    public void mod_i_arista (int i)
    {
        CA.mod_i_arista(i);
    }

    public int num_aristas_salida(int x)
    {
        return CA.num_aristas_salida(x);
    }

    public int num_comunidades1()
    {
        return CA.num_comunidades1();
    }

    public int num_comunidades2()
    {
        return CA.num_comunidades2();
    }

    public int num_comunidades3()
    {
        return CA.num_comunidades3();
    }

    public String next_community1()
    {
        return CA.next_community1();
    }
    public String next_community2()
    {
        return CA.next_community2();
    }
    public String next_community3()
    {
        return CA.next_community3();
    }

    public void modParam1(String x)
    {
        CA.modParamEntrada(x);
    }
    public void modParam2(String x)
    {
        CA.modParam2Entrada(x);
    }

    public String next_mensaje1()
    {
        return CA.next_message1();
    }
    public String next_mensaje2()
    {
        return CA.next_message2();
    }
    public String next_mensaje3()
    {
        return CA.next_message3();
    }

    public String message_at1(int i) {
        return CA.message_at1(i);
    }
    public String message_at2(int i) {
        return CA.message_at2(i);
    }
    public String message_at3(int i) {
        return CA.message_at3(i);
    }

    public void crearGrafo() throws Exception {
        CA.crearGrafo();
    }

    public void reset_pointers()
    {
        CA.reset_pointers();
    }

    public String obtLabel(int x)
    {
        String r;
        try
        {
            r = CA.obtLabel(x);
        }
        catch (Exception e) {
            e.printStackTrace();
            r = "-";
        }
        return r;
    }

    public int num_mensajes1()
    {
        return CA.num_mensajes1();
    }
    public int num_mensajes2()
    {
        return CA.num_mensajes2();
    }
    public int num_mensajes3()
    {
        return CA.num_mensajes3();
    }

    public String obtCongresista(String x)
    {
        return CPR.obtCongresista(x);
    }

    public void nuevo()
    {
        PA.nuevo();
    }

    public void actualizarPanel(){
        PA.actualizar();
    }

    public void limpiar_salida()
    {
        CA.limpiar_salida();
    }
}
