package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Nulo
 */
public class Nulo extends Voto {

    public Nulo () {
    }

    public Nulo (String dni) throws Exception {
        super(dni);
    }

    @Override
    public String obt_tipo() {
        return "Nulo";
    }

    public String mostrarInfo() {
        return super.mostrarInfo() + " ha votado en nulo";
    }

    public String toString() {
        return "Nulo";
    }
}
