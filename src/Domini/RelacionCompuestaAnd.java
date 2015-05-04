package Domini;

/**
 * Created by bug on 4/05/15.
 */
public class RelacionCompuestaAnd extends RelacionCompuesta {
    Relacion B;
    public RelacionCompuestaAnd(Relacion A,Relacion b) {
        super(false,A);
        B = b;
    }
    public Relacion obtRelacionB() {
        return B;
    }
    public boolean esOr() {
        return false;
    }
}
