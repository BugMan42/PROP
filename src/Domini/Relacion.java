package Domini;

/**
 * Created by bug on 20/03/15.
 */
public class Relacion {

    private Congresista C;
    private Evento E;
    //TODO: creadora no puede petar devolver error
    Relacion(Congresista C1, Evento E1) {
        C = C1;
        E = E1;
    }
    //Modificadoras
    //TODO: igual que en creadora
    public void mod_relacion(Congresista Congr, Evento Ev) {
        C = Congr;
        E = Ev;
    }
    public void mod_relacion(Congresista Congr) {
        C = Congr;
    }
    public void mod_relacion(Evento Ev) {
        E = Ev;
    }
    //Consultoras
    //TODO: siempre que no sea null
    public Congresista obt_congresista() {
        return C;
    }
    public Evento obt_Evento() {
        return E;
    }
    public String toString() {
        return C.obt_dni()+" "+E.obt_nombre();
    }

    //destructora o almenos cleaner
    public void clean() {
        E = null;
        C = null;
    }
}
