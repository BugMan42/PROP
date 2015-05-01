package Domini;

/**
 * Created by falc on 20/03/15.
 */
public class Abstencion extends Voto {

    public Abstencion() {
    }

    public Abstencion(String dni) throws Exception {
        super(dni);
    }

    @Override
    public String obt_tipo() {
        return "Abstención";
    }

    public String mostrarInfo() {
        return super.mostrarInfo() + "se ha abstenido.";
    }

}
