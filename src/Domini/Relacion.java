package Domini;

/**
 * Created by bug on 20/03/15.
 */
public class Relacion {

    private Congresista C;
    private Evento E;

    Relacion(Congresista C, Evento E) {
        this.C = C;
        this.E = E;
    }
    public void mod_relacion(Congresista C, Evento E) {
        this.C = C;
        this.E = E;
    }
    public void mod_relacion(Congresista C) {
        this.C = C;
    }
    public void mod_relacion(Evento E) {
        this.E = E;
    }
    public Congresista obt_congresista() {
        return C;
    }
    public Evento obt_Evento() {
        return E;
    }
    public String toString() {
        return C.obt_dni()+E.obt_nombre();
    }
    //destructora o almenos cleaner
    public void clean() {
        E = null;
        C = null;
    }
}
