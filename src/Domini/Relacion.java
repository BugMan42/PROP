package Domini;

/**
 * Created by bug on 20/03/15.
 * Relacion simple TODO hacer relacion simple y compuesta (or  not)
 */
public class Relacion {
    //atributos no pueden ser null
    private Congresista C;
    private Evento E;
    Relacion(Congresista C1, Evento Ev) {
        C = C1;
        E = Ev;
    }
    //Modificadoras
    public void modRelacion(Congresista C1, Evento Ev) {
        C = C1;
        E = Ev;
    }
    public void modRelacion(Congresista C1) {
        C = C1;
    }
    public void modRelacion(Evento Ev) {
        E = Ev;
    }
    //Consultoras
    public Congresista obtCongresista() {
        return C;
    }
    public Evento obtEvento() {
        return E;
    }
    public String toString() {
        return C.obtDni()+" "+E.obt_nombre()+" "+E.obt_fecha();
    }
}
