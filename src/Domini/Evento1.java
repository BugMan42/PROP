package Domini;

/**
 * Created by usuario on 25/04/2015.
 */
public class Evento1 extends Evento {

    public Evento1(String name, Fecha fecha, int importancia) throws Exception{
        super(name,fecha, importancia);
    }
    public String toString() {
        return "Evento " + obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String tipo() {
        return "Evento";
    }
}
