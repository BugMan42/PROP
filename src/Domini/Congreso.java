package Domini;

import java.util.ArrayList;

/**
 * Created by bug on 20/03/15.
 */
public class Congreso {
    private ArrayList<Congresista> Lista;
    public Congreso() {
        Lista = new ArrayList<Congresista>();
    }
    public void add_Congresista(Congresista C) {
        Lista.add(C);
    }
    public void eliminar_Congresista(Congresista C) {
        Lista.remove(C);
    }
    public ArrayList<Congresista> obtener_lista() {
        return Lista;
    }
    public boolean contiene(Congresista C) {
        //post: retorna verdadero si el congresista esta en el congreso
        return Lista.contains(C);
    }

}
