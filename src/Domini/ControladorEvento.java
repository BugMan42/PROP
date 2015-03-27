package Domini;

import java.util.ArrayList;

/**
 * Created by bug on 24/03/15.
 */
public class ControladorEvento {
    private ArrayList<Evento> Listado;

    public ControladorEvento() {
        Listado = new ArrayList<Evento>();
    }
    public ArrayList<Evento> obt_lista() {
        return Listado;
    }
    public void add_Congresista(Evento e) {
        Listado.add(e);
    }
    public void eliminar_relacion(Evento e) {
        Listado.remove(e);
    }
}
