package Domini;

import java.util.ArrayList;

/**
 * Created by bug on 20/03/15.
 */


public class CjtRelacion {
    private ArrayList<Relacion> Lista;

    public CjtRelacion() {
        Lista = new ArrayList<Relacion>();
    }
    public ArrayList<Relacion> obt_lista() {
        return Lista;
    }
    public void add_Congresista(Relacion R) {
        Lista.add(R);
    }
    public void eliminar_relacion(Relacion R) {
        Lista.remove(R);
    }
    public boolean contiene(Relacion R) {
        return Lista.contains(R);
    }
    public void print_relaciones() {

    }

}
