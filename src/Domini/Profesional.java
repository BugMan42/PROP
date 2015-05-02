package Domini;

/**
 * Created by usuario on 26/04/2015.
 */
public class Profesional extends Reunion {
    public Profesional(String nombre, String fecha, int importancia) throws Exception{
        super(nombre, fecha, importancia);
    }
    public String toString() {
        return "ReunionProfesional " + nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }
}
