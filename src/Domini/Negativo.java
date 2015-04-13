package Domini;

/**
 * Created by falc on 7/04/15.
 */
public class Negativo extends Voto{
    public Negativo () {
    }

    public Negativo (String dni) {
        super(dni);
    }

    @Override
    public String obt_tipo() {
        return "Negativo";
    }

    public String toString() {
        return super.toString()+"en contra.";
    }
}
