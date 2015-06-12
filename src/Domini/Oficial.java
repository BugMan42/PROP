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

    public String toStringF() {
        return "ActoOficial "+ obt_fecha() + " " + obt_nombre() + " " + Integer.toString(obt_importancia());
    }

    public String toStringI() {
        return "ActoOficial "+ Integer.toString(obt_importancia()) + " " + obt_nombre() + " " + obt_fecha();
    }

    public String tipo(){
        return "ActoOficial";
    }
}
