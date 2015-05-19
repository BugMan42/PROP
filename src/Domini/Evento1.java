package Domini;

/**
 * Created by usuario on 25/04/2015.
 */
public class Evento1 extends Evento {

    public Evento1(String name, String fecha, int importancia) throws Exception{
        super(name,fecha, importancia);
    }
    public String toString() {
        return "Evento " + nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }

    public String tipo() {
        return "Evento";
    }
}
