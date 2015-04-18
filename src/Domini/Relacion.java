package Domini;

/**
 * Created by bug on 20/03/15.
 * Relacion simple TODO hacer relacion simple y compuesta (or  not)
 */
public class Relacion {
    //atributos no pueden ser null
    private Congresista C;
    private Evento E;
    Relacion(Congresista C1, Evento E1) {
        C = C1;
        E = E1;
    }
    //Modificadoras
    public void mod_relacion(Congresista C1, Evento Ev) {
        C = C1;
        E = Ev;
    }
    public void mod_relacion(Congresista C1) {
        C = C1;
    }
    public void mod_relacion(Evento Ev) {
        E = Ev;
    }
    //Consultoras
    public Congresista obt_congresista() {
        return C;
    }
    public Evento obt_Evento() {
        return E;
    }
    public String toString() {
        return C.obtDni()+" "+E.obt_nombre()+" "+E.obt_fecha();
    }
}
