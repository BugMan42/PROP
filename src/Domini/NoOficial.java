package Domini;

/**
 * Created by usuario on 04/05/2015.
 */
public class NoOficial extends Acto{
    public NoOficial(String nombre, String data, int importance) throws Exception{
        super(nombre, data, importance);
    }
    public String toString() {
        return "ActoNoOficial "+ obt_nombre() + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }

    public String tipo(){
        return "ActoNoOficial";
    }

}
