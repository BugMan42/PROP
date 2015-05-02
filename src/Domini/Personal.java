package Domini;

/**
 * Created by usuario on 26/04/2015.
 */
public class Personal extends Reunion{
    public Personal (String nombre, String fecha, int importancia) throws Exception {
        super(nombre, fecha, importancia);
    }

    public String ToString() {
        return "ReunionPersonal " + nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }
}
