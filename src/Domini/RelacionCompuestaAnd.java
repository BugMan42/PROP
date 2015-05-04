package Domini;

public class RelacionCompuestaAnd extends RelacionCompuesta {

    private Relacion B;

    public RelacionCompuestaAnd(Relacion a,Relacion b) {
        super(a);
        B = b;
    }

    public void modRelacionB(Relacion b) {
        B = b;
    }
    public Relacion obtRelacionB() {
        return B;
    }

    public boolean esOr() {
        return false;
    }
    public boolean esNot() {
        return false;
    }

    public String toString() {
        return "["+super.toString()+"] AND ["+B.toString()+"]";
    }
}
