package Domini;

/**
 * Created by usuario on 04/05/2015.
 */
public class Acto1 extends Acto{
    public Acto1(String nombre, String data, int importance) throws Exception{
        super(nombre, data, importance);
    }
    public String toString() {
        return "Acto "+ nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }

    public String tipo(){
        return "Acto";
    }
}
