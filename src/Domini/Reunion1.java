package Domini;

/**
 * Created by usuario on 04/05/2015.
 */
public class Reunion1 extends Reunion{
    public Reunion1 (String nombre, Fecha fecha, int importancia) throws Exception {
        super(nombre, fecha, importancia);
    }

    public String toString() {
        return "Reunion " + obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String toStringF() {
        return "Reunion " + obt_fecha() + " " + obt_nombre() + " " + Integer.toString(obt_importancia());
    }

    public String toStringI() {
        return "Reunion " + Integer.toString(obt_importancia()) + " " + obt_nombre() + " " + obt_fecha();
    }

    public String tipo(){
        return "Reunion";
    }
}
