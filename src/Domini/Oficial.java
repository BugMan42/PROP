package Domini;

/**
 * Created by usuario on 04/05/2015.
 */
public class Oficial extends Acto {
    public Oficial(String nombre, Fecha data, int importance) throws Exception{
        super(nombre, data, importance);
    }
    public String toString() {
        return "ActoOficial "+ obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String tipo(){
        return "ActoOficial";
    }
}
