package Domini;

public abstract class RelacionCompuesta extends Relacion {
    private Relacion A;
    //RelacionB estara solo en relacion compuesta and y or

    public RelacionCompuesta(Relacion a) {
        super();
        A = a;
    }

    public void modRelacionA(Relacion a) {
        A = a;
    }
    public abstract void modRelacionB(Relacion b) throws Exception;

    public Relacion obtRelacionA() {
        return A;
    }
    public abstract Relacion obtRelacionB() throws Exception;

    public abstract boolean esOr();
    public abstract boolean esNot();

    public boolean esSimple() {
        return false;
    }

    public String toString() {
        return A.toString();
    }

}
