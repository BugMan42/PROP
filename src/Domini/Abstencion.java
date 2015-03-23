package Domini;

/**
 * Created by falc on 20/03/15.
 */
public class Abstencion extends Voto {

    public Abstencion (String dni) {
        super(dni);
    }

    @Override
    public String obt_tipo() {
        return "Abstención";
    }
}
