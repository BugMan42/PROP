package Domini;

public class Acto1 extends Acto{
    public Acto1(String nombre, Fecha data, int importance) throws Exception{
        super(nombre, data, importance);
    }
    public String toString() {
        return "Acto "+ obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String toStringF() {
        return "Acto "+  obt_fecha() +" " + obt_nombre() + " " + Integer.toString(obt_importancia());
    }

    public String toStringI() {
        return "Acto "+ Integer.toString(obt_importancia()) + " " + obt_nombre() + " " + obt_fecha();
    }

    public String tipo(){
        return "Acto";
    }
}
