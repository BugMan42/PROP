package domini;

import java.util.ArrayList;

/**
 * Created by bug on 20/03/15.
 */


public class CjtRelacion {
    private ArrayList<Relacion> Lista;

    public CjtRelacion() {
        Lista = new ArrayList<Relacion>();
    }
    public void add_Congresista(Relacion R) {
        Lista.add(R);
    }
    public void print_relaciones() {

    }

}
