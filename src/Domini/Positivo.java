package Domini;

/**
 * Created by falc on 7/04/15.
 * Clase Positivo
 */
public class Positivo extends Voto {
    //Creadora vacía de Positivo
    public Positivo () {
    }

    //Creadora con parámetros de Positivo
    public Positivo (String dni) throws Exception {
        super(dni);
    }

    @Override
    //Devuelve en un string el nombre de la subclase a la que pertence la instancia
    public String obt_tipo() {
        return "Positivo";
    }

    //Devuelve un string que contiene información del Voto
    public String mostrarInfo() {
        return super.mostrarInfo() + " ha votado a favor.";
    }

    public String toString() {
        return "Positivo";
    }
}
