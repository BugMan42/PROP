package Domini;

/**
 * Created by bug on 4/05/15.
 */
public abstract class RelacionCompuesta extends Relacion {
    boolean esNot;
    Relacion A;
    //Relacion B;
    public RelacionCompuesta(boolean EsNot,Relacion a) {
        super(false);
        esNot = EsNot;
        A = a;
        //B = b;
    }
    public Relacion obtRelacionA() {
        return A;
    }
    public abstract Relacion obtRelacionB() throws Exception;
    public abstract boolean esOr();
    public boolean esNot() {
        return esNot;
    }

}
