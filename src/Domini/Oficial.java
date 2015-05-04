package Domini;

/**
 * Created by usuario on 04/05/2015.
 */
public class Oficial extends Acto {
    public Oficial(String nombre, String data, int importance) throws Exception{
        super(nombre, data, importance);
    }
    public String toString() {
        return "ActoOficial "+ nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }

    public String tipo(){
        return "ActoOficial";
    }
}
