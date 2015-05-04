package Domini;

/**
 * Created by bug on 4/05/15.
 */
public class RelacionCompuestaNot extends RelacionCompuesta {
    public RelacionCompuestaNot(Relacion A) {
        super(true,A);
    }
    public Relacion obtRelacionB() throws Exception {
        throw new Exception("Relacion Not no contiene Relacion B");
    }
    public boolean esOr() {
        return false;
    }
}
