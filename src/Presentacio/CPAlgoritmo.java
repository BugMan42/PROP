package Presentacio;


import Domini.ControladorAlgoritmo;

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


    public int num_vertices()
    {
        return CA.num_vertex_entrada();
    }

    public String next_vertex()
    {
        return CA.next_vertex();
    }

    public String next_arista()
    {
        return CA.next_arista();
    }

    public int num_comunidades()
    {
        return CA.num_comunidades();
    }

    public String next_community()
    {
        return CA.next_community();
    }

    public void modParam1(String x)
    {
        CA.modParamEntrada(x);
    }
    public void modParam2(String x)
    {
        CA.modParam2Entrada(x);
    }

    public String next_mensaje()
    {
        return CA.next_message();
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

}
