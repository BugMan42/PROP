package Domini;

/**
 * Created by usuario on 04/05/2015.
 */
public class Reunion1 extends Reunion{
    public Reunion1 (String nombre, String fecha, int importancia) throws Exception {
        super(nombre, fecha, importancia);
    }

    public String toString() {
        return "Reunion " + nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }

    public String tipo(){
        return "Reunion";
    }
}
