package Domini;

public class Evento1 extends Evento {

    public Evento1(String name, Fecha fecha, int importancia) throws Exception{
        super(name,fecha, importancia);
    }
    public String toString() {
        return "Evento " + obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String toStringF() {
        return "Evento " + obt_fecha() + " " + obt_nombre() + " " + Integer.toString(obt_importancia());
    }

    public String toStringI() {
        return "Evento " + Integer.toString(obt_importancia()) + " " + obt_nombre() + " " + obt_fecha() ;
    }

    public String tipo() {
        return "Evento";
    }
}
