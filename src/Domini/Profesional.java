package Domini;

/**
 * Created by usuario on 26/04/2015.
 */
public class Profesional extends Reunion {
    /**
     * Creadora de profesional
     * Pre: nombre no puede estar vacio, fecha tiene que ser valida e
     * importancia > 0
     * Post: Se ha creado una instancia de profesional con atributos nombre, fecha e importancia
     */
    public Profesional(String nombre, Fecha fecha, int importancia) throws Exception{
        super(nombre, fecha, importancia);
    }
/**
 * Pasa un acto a string. Útil a la hora de guardar una acto.
 * Pre: Cierto
 * Post: Devuelve un string que contiene la palabra ReunionProfesional, el nombre de profesional, su fecha y su importancia
 * separados por espacios.
 * */
    public String toString() {
        return "ReunionProfesional " + obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String tipo(){
        return "ReunionProfesional";
    }
}
