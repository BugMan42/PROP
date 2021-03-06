package Domini;

public abstract class Evento {
    static final String error3 = "Importancia no es valida";
    static final String error4 = "Tiene que ser mayor que 0";
    static final char separador = '-';

    private String nombre;
    private Fecha fecha;
    private int importancia;

    //Constructoras

    public Evento(String name, Fecha date, int importance) throws Exception {
        if (importance <= 0 || importance > 9999) throw new Exception(error3 + " : " + error4);
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
        return  nombre+fecha.toString();
    }

    public String IDN() {
        return nombre+separador+fecha.alReves();
    }

    public String IDFecha() {
        return fecha.alReves()+separador+nombre;
        //return fecha.toString()+nombre;
    }

    public String IDImp() {
        String imp = Integer.toString(importancia);
        if (imp.length() < 4) {
            int n = 4 - imp.length();
            for (int i = 0; i < n;++i) {
                imp = "0"+imp;
            }
        }
        return imp+separador+nombre+separador+fecha.alReves();
    }

    public abstract String toString();

    public abstract String toStringF();

    public abstract String toStringI();

    public int obt_importancia() {
        return importancia;
    }

    public abstract String tipo();
}
