package Domini;

/**
 * Created by usuario on 26/04/2015.
 */
public class Personal extends Reunion{
    /**Creadora de Personal
     * Pre: nombre no puede ser vacio, fecha tiene que ser valida
     * e importancia > 0
     * Post: Se ha creado un nuevo objeto personal que tiene como atributos
     * nombre, fecha e importancia
     */
    public Personal (String nombre, String fecha, int importancia) throws Exception {
        super(nombre, fecha, importancia);
    }

    /**
     * Pasa un personal a string. Útil a la hora de guardar el objeto.
     * Pre: Cierto
     * Post: Devuelve un string que contiene la palabra ReunionPersonal, el nombre del personal, su fecha y su importancia
     * separados por espacios.
     */
    public String toString() {
        return "ReunionPersonal " + nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }
}
