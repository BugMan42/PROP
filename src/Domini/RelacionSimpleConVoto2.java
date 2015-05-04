package Domini;

/**
 * Created by bug on 4/05/15.
 */
public class RelacionSimpleConVoto2 extends RelacionSimple2{
    Voto V;
    RelacionSimpleConVoto2(Congresista a, Evento e, Voto v) {
        super(true,a,e);
        V = v;
    }
    public String toString() {
        return super.toString()+" "+V;
    }
}
