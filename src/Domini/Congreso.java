package Domini;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bug on 20/03/15.
 */
public class Congreso {
    private ArrayList<Congresista> Lista;
    public Congreso() {
        Lista = new ArrayList<Congresista>();
    }
    private int BuscarIndice(String dni) {
        //si no se encuentra retorna -1
        int n = Lista.size();
        boolean found = false;
        int i = 0;
        while (!found && i < n) {
            if (Lista.get(i).obt_dni().equals(dni)) found = true;
            else ++i;
        }
        if (!found) i = -1;
        return i;
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
    public boolean contiene(String dni) {
        //post: retorna verdadero si el congresista esta en el congreso
        return Lista.contains(dni);
    }

}
