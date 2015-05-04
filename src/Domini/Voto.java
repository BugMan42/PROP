package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Voto
 */
public abstract class Voto {


    //Creadora vacía de Voto
    public Voto() {}

    //Devuelve un string con la subclase a la que pertence la instancia
    public abstract String obt_tipo();

    /* Mostrar Información
    * Pre:
    * Post: Devuelve un string con la información del voto
    */
    public String mostrarInfo() {
        return "El congresista";
    }

    public abstract String toString();


}
