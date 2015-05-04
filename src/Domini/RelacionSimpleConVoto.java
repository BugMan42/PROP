package Domini;

/**
 * Created by bug on 4/05/15.
 */
public class RelacionSimpleConVoto extends RelacionSimple {
    Voto V;
    public RelacionSimpleConVoto(Congresista a, Evento e, Voto v) {
        super(true, a, e);
        V = v;
    }
    public void modVoto(Voto v1) {
        V = v1;
    }
    public Voto obtVoto() {
        return V;
    }

    public String toString() {
        return super.toString()+" "+V;
    }
}
