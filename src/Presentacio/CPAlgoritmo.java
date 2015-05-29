package Presentacio;


import Domini.ControladorAlgoritmo;

public class CPAlgoritmo {

    private PanelAlgoritmo PA;
    private ControladorAlgoritmo CA;


    public CPAlgoritmo(CPRelaciones cpr) {
        CA = new ControladorAlgoritmo(cpr.CR);
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


    public int num_aristas()
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

}
