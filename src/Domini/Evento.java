package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Evento
 */
public abstract class Evento {
    static final String error1 = "El nombre no es valido";
    static final String error2 = "La fecha no es valida";
    static final String error3 = "La importancia no es valida";

    private String nombre;
    private Fecha fecha;
    private int importancia;

    //Constructoras

    public Evento(String name, Fecha date, int importance) throws Exception {
        if (importance <= 0) throw new Exception(error3);
        nombre = name.toUpperCase();
        fecha = date;
        importancia = importance;
    }

    //Modificadoras

    public void ModNombre(String name) throws Exception{
        if (!nombre.equals(name)) nombre = name.toUpperCase();
    }

    public void ModFecha (Fecha date) throws Exception {
        fecha = date;
    }

    public void ModImportancia(int importance) throws Exception {
        if (importance <= 0) throw new Exception(error3);
        if (importancia != importance) importancia = importance;
    }

    //Consultoras

    public String obt_nombre() {
        return nombre;
    }

    public String obt_fecha() {
        return fecha.ConsultarFecha();
    }

    public String obtFecha() {
        return fecha.toString();
    }

    public String ID() {
        return nombre+fecha.toString();
    }

    public abstract String toString();

    public int obt_importancia() {
        return importancia;
    }

    public abstract String tipo();
}
