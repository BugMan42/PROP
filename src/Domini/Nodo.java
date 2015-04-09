package Domini;

/**
 * Created by bug on 1/04/15.
 */
public class Nodo {
    private Congresista C;
    //Algo mas????

    public Nodo(Congresista A) {
        C = A;
    }
    public Congresista obt_Congresista() {
        return C;
    }
    public void mod_Congresista(Congresista A) {
        C = A;
    }

    public String toString() {
        return C.obtDni();
    }
}
