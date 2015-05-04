package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Blanco
 */
public class Blanco extends Voto {

    //Creadora vacía de Blanco
    public Blanco() { super(); }

    @Override
    //Devuelve en un string el nombre de la subclase a la que pertence la instancia
    public String obt_tipo() {
        return "Blanco";
    }

    @Override
    //Devuelve un string que contiene información del Voto
    public String mostrarInfo() {
        return super.mostrarInfo()+" ha votado en blanco.";
    }

    public String toString() {
        return "Blanco";
    }

}
