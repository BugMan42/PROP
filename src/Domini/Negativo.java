package Domini;

/**
 * Created by falc on 7/04/15.
 */
public class Negativo extends Voto{
    public Negativo () {
    }

    public Negativo (String dni) throws Exception {
        super(dni);
    }

    @Override
    public String obt_tipo() {
        return "Negativo";
    }

    public String mostrarInfo() {
        return super.mostrarInfo()+" ha votado en contra.";
    }
}
