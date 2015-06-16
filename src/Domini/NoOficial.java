package Domini;

public class NoOficial extends Acto{
    public NoOficial(String nombre, Fecha data, int importance) throws Exception{
        super(nombre, data, importance);
    }
    public String toString() {
        return "ActoNoOficial "+ obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String toStringF() {
        return "ActoNoOficial "+ obt_fecha() + " " + obt_nombre() + " " + Integer.toString(obt_importancia());
    }

    public String toStringI() {
        return "ActoNoOficial "+ Integer.toString(obt_importancia()) + " " + obt_nombre() + " " + obt_fecha();
    }

    public String tipo(){
        return "ActoNoOficial";
    }

}
