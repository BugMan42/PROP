package Domini;

/**
 * Created by usuario on 04/05/2015.
 */
public class NoOficial extends Acto{
    public NoOficial(String nombre, Fecha data, int importance) throws Exception{
        super(nombre, data, importance);
    }
    public String toString() {
        return "ActoNoOficial "+ obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String tipo(){
        return "ActoNoOficial";
    }

}
