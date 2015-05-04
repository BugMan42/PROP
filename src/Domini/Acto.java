package Domini;

/**
 * Created by falc on 20/03/15.
 */
public class Acto extends Evento {
    /**
     * Creadora de acto
     * Pre: nombre no puede estar vacio, fecha tiene que ser valida e
     * importancia > 0
     * Post: Se ha creado una instancia de Acto con atributos nombre, fecha e importancia
     */
    public Acto(String nombre, String fecha, int importancia) throws Exception{
        super(nombre, fecha, importancia);
    }

    /**
     * Pasa un acto a string. Útil a la hora de guardar una acto.
     * Pre: Cierto
     * Post: Devuelve un string que contiene la palabra Acto, el nombre del acto, su fecha y su importancia
     * separados por espacios.
     */
    public String toString() {
        return "Acto "+ nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }

    public String tipo(){
        return "Acto";
    }
}
