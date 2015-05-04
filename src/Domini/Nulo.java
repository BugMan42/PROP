package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Nulo
 */
public class Nulo extends Voto {
    //Creadora vacía de Nulo
    public Nulo () {
        super();
    }

    @Override
    //Devuelve en un string el nombre de la subclase a la que pertence la instancia
    public String obt_tipo() {
        return "Nulo";
    }

    //Devuelve un string que contiene información del Voto
    public String mostrarInfo() {
        return super.mostrarInfo() + " ha votado en nulo";
    }

    public String toString() {
        return "Nulo";
    }
}
