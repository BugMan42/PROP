package Domini;

/**
 * Created by falc on 20/03/15.
 */
public class Nulo extends Voto {
    public Nulo (String dni) {
        super(dni);
    }

    @Override
    public String obt_tipo() {
        return "Nulo";
    }
}
