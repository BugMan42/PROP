package Domini;

import java.util.Date;

/**
 * Created by falc on 20/03/15.
 */
public abstract class Reunion extends Evento {
    /**
     * Creadora de reunion
     * Pre: nombre no puede estar vacio, fecha tiene que ser valida e
     * importancia > 0
     * Post: Se ha creado una instancia de reunion con atributos nombre, fecha e importancia
     */
    public Reunion(String nombre, String fecha, int importancia) throws Exception {
        super(nombre, fecha, importancia);
    }
}
