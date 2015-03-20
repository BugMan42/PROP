import java.util.ArrayList;

/**
 * Created by bug on 20/03/15.
 */
public class Congreso {
    ArrayList<Congresista> Lista;
    public Congreso() {
        Lista = new ArrayList<Congresista>();
    }
    public void add_Congresista(Congresista C) {
        Lista.add(C);
    }
    public void eliminar_Congresista(Congresista C) {
        Lista.remove(C);
    }
    
}
