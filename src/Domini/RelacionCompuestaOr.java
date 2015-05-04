package Domini;

public class RelacionCompuestaOr extends RelacionCompuesta{

    private Relacion B;

    public RelacionCompuestaOr(Relacion a,Relacion b) {
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
        return true;
    }
    public boolean esNot() {
        return false;
    }

    public String toString() {
        return super.toString()+" OR "+B.toString();
    }
}
