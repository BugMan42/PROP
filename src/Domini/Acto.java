package Domini;

/**
 * Created by falc on 20/03/15.
 */
public class Acto extends Evento {
    /**
     * Creadora de acto
     * Pre: nombre no puede estar vacia, fecha tiene que ser valida y no vacia
     * importancia tiene que ser mayor que 0
     * Post: Se ha creado una instancia de Acto con atributos nombre, fecha e importancia
     */
    public Acto(String nombre, String fecha, int importancia) throws Exception{
        super(nombre, fecha, importancia);
    }

    /**
     * Creadora de acto
     * Pre: nombre no puede estar vacia, fecha tiene que ser valida y no vacia
     * importancia tiene que ser mayor que 0
     * Post: Se ha creado una instancia de Acto con atributos nombre, fecha e importancia
     */
    public String toString() {
        return "Acto "+ nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }
}
