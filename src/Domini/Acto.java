package Domini;

/**
 * Created by falc on 20/03/15.
 */
public class Acto extends Evento {
    public Acto(String nombre, String fecha, int importancia) throws Exception{
        super(nombre, fecha, importancia);
    }
    public String toString() {
        return "Acto "+ nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }
}
