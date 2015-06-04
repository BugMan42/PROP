package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Evento
 */
public abstract class Evento {
    static final String error1 = "El nombre no es valido";
    static final String error2 = "La fecha no es valida";
    static final String error3 = "Importancia no es valida";
    static final String error4 = "Tiene que ser mayor que 0";
    static final char separador = '-';

    private String nombre;
    private Fecha fecha;
    private int importancia;

    //Constructoras

    public Evento(String name, Fecha date, int importance) throws Exception {
        if (importance <= 0) throw new Exception(error3 + " : " + error4);
        nombre = name.toUpperCase();
        fecha = date;
        importancia = importance;
    }

    //Modificadoras

    public void ModNombre(String name) throws Exception{
        nombre = name.toUpperCase();
    }

    public void ModFecha (Fecha date) throws Exception {
        fecha = date;
    }

    public void ModImportancia(int importance) throws Exception {
        if (importance <= 0) throw new Exception(error3 + " : " + error4);
        importancia = importance;
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
        //return nombre+fecha.toString();
        return  nombre+separador+fecha.ConsultarFecha();
    }

    public String IDFecha() {
        return fecha.ConsultarFecha()+separador+nombre;
        //return fecha.toString()+nombre;
    }

    public String IDImp() {
        //return Integer.toString(importancia)+nombre+fecha.ConsultarFecha();
        return Integer.toString(importancia)+separador+nombre+fecha.ConsultarFecha();
    }

    public abstract String toString();

    public int obt_importancia() {
        return importancia;
    }

    public abstract String tipo();
}
