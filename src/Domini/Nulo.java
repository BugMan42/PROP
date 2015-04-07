package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Nulo
 */
public class Nulo extends Voto {

    public Nulo () {
    }

    public Nulo (String dni) {
        super(dni);
    }

    @Override
    public String obt_tipo() {
        return "Nulo";
    }

    public String toString() {
        return super.toString()+"nulo";
    }
}
