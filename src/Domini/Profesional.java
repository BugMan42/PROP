package Domini;


public class Profesional extends Reunion {

    public Profesional(String nombre, Fecha fecha, int importancia) throws Exception{
        super(nombre, fecha, importancia);
    }

    public String toString() {
        return "ReunionProfesional " + obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String toStringF() {
        return "ReunionProfesional " + obt_fecha() + " " + obt_nombre() + " " + Integer.toString(obt_importancia());
    }

    public String toStringI() {
        return "ReunionProfesional " + Integer.toString(obt_importancia()) + " " + obt_nombre() + " " + obt_fecha();
    }

    public String tipo(){
        return "ReunionProfesional";
    }
}
