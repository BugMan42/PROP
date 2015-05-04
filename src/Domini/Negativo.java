package Domini;

/**
 * Created by falc on 7/04/15.
 */
public class Negativo extends Voto{
    //Creadora vacía de Negativo
    public Negativo () {
    }

    //Creadora con parámetros de Negativo
    public Negativo (String dni) throws Exception {
        super(dni);
    }

    @Override
    //Devuelve en un string el nombre de la subclase a la que pertence la instancia
    public String obt_tipo() {
        return "Negativo";
    }

    //Devuelve un string que contiene información del Voto
    public String mostrarInfo() {
        return super.mostrarInfo()+" ha votado en contra.";
    }

    public String toString() {
        return "Negativo";
    }
}
