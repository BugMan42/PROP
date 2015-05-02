package Domini;

/**
 * Created by falc on 7/04/15.
 * Clase Positivo
 */
public class Positivo extends Voto {
    public Positivo () {
    }

    public Positivo (String dni) throws Exception {
        super(dni);
    }

    @Override
    public String obt_tipo() {
        return "Positivo";
    }

    public String mostrarInfo() {
        return super.mostrarInfo() + " ha votado a favor.";
    }
}
