package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Evento
 */
public abstract class Evento {
    protected String nombre;
    protected Fecha fecha;
    protected int importancia;

    //Constructoras

    public Evento(String name, String date, int importance) throws NoValido {
        if (name.equals("")) throw new NoValido("nombre", 0);
        if (date.equals("")) throw new NoValido("fecha", 0);
        if (importance <= 0) throw new NoValido("importancia", 0);
        fecha = new Fecha(date);
        nombre = name.toUpperCase();
        importancia = importance;
    }

    //Modificadoras

    public void ModNombre(String name) throws NoValido{
        if (name.equals("")) throw new NoValido("nombre", 0);
        if (!nombre.equals(name)) nombre = name.toUpperCase();
    }

    public void ModFecha (String date) throws NoValido {
        if (fecha.ConsultarFecha().equals(date)) fecha = new Fecha(date);
    }

    public void ModImportancia(int importance) throws NoValido {
        if (importance <= 0) throw new NoValido("Importancia", 0);
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
        return fecha.ToString();
    }

    public String ID() {
        return nombre+fecha.ToString();
    }

    public abstract String toString();

    public int obt_importancia() {
        return importancia;
    }

    public String tipo(){
        return "";
    }
}
