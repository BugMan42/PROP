package Domini;

import java.util.ArrayList;

/**
 * Created by jose on 15/04/15.
 */
public class ControladorCjtRelacion {

    private CjtRelacion c;

    public ControladorCjtRelacion(){
        c = new CjtRelacion();
    }

    private ArrayList<Relacion> obt_lista(){
        return c.obt_lista();
    }

    public Grafo generar_grafo_relaciones(){
        Grafo g = new Grafo();
        return g;
    }

}
