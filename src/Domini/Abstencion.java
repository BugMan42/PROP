package Domini;

/**
 * Created by falc on 20/03/15.
 */
public class Abstencion extends Voto {

    //Creadora vacía de Abstención
    public Abstencion() {
    }

    //Creadora con parámetros de Abstención
    public Abstencion(String dni) throws Exception {
        super(dni);
    }

    @Override
    //Devuelve en un string el nombre de la subclase a la que pertence la instancia
    public String obt_tipo() {
        return "Abstención";
    }


    @Override
    //Devuelve un string que cotiene información del Voto
    public String mostrarInfo() {
        return super.mostrarInfo() + " se ha abstenido.";
    }

    public String toString() {
        return "Abstencion";
    }

}
