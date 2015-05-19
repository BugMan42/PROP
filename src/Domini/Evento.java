package Domini;

import java.util.Random;

/**
 * Created by falc on 20/03/15.
 * Clase Evento
 */
public abstract class Evento {
    static final String error1 = "El nombre no es valido";
    static final String error2 = "La fecha no es valida";
    static final String error3 = "La importancia no es valida";

    protected String nombre;
    protected Fecha fecha;
    protected int importancia;

    //Constructoras

    public Evento(String name, String date, int importance) throws Exception {
        if (name.equals("")) throw new Exception(error1);
        if (date.equals("")) throw new Exception(error2);
        if (importance <= 0) throw new Exception(error3);
        fecha = new Fecha(date);
        nombre = name.toUpperCase();
        importancia = importance;
    }

    //Modificadoras

    public void ModNombre(String name) throws Exception{
        if (name.equals("")) throw new Exception(error1);
        if (!nombre.equals(name)) nombre = name.toUpperCase();
    }

    public void ModFecha (String date) throws Exception {
        fecha = new Fecha(date);
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
