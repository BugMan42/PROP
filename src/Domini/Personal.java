package Domini;

/**
 * Created by usuario on 26/04/2015.
 */
public class Personal extends Reunion{

    public Personal (String nombre, Fecha fecha, int importancia) throws Exception {
        super(nombre, fecha, importancia);
    }

    public String toString() {
        return "ReunionPersonal " + obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String toStringF() {
        return "ReunionPersonal " + obt_fecha() + " " + obt_nombre() + " " + Integer.toString(obt_importancia());
    }

    public String toStringI() {
        return "ReunionPersonal " + Integer.toString(obt_importancia()) + " " + obt_nombre() + " " + obt_fecha();
    }

    public String tipo(){
        return "ReunionPersonal";
    }
}
