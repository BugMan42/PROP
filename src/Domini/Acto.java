package Domini;

/**
 * Created by falc on 20/03/15.
 */
public abstract class Acto extends Evento {

    public Acto(String nombre, Fecha fecha, int importancia) throws Exception{
        super(nombre, fecha, importancia);
    }
}
