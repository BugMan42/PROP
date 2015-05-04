package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Voto
 */
public abstract class Voto {
    //Clave externa de Voto
    private Dni dni;

    //Creadora vacía de Voto
    public Voto() {
        dni = null;
    }

    //Creadora con parámetros
    public Voto (String d) throws Exception {
        dni = new Dni(d);
    }

    /* Obtener DNI
     * post: Devuelve el DNI que identifica el voto
    */
    public String obt_dni() {
        return dni.toString();
    }

    public void mod_dni(String s) throws Exception {
        //Si no hay un DNI inicializado, se crea uno nuevo con String s como identificador
        if (dni == null) dni = new Dni(s);
        //Si no, sólo modificamos el string del DNI
        else dni.modDni(s);
    }

    //Devuelve un string con la subclase a la que pertence la instancia
    public abstract String obt_tipo();

    /* Mostrar Información
    * Pre:
    * Post: Devuelve un string con la información del voto
    */
    public String mostrarInfo() {
        return "El congresista con DNI "+dni;
    }

    public abstract String toString();


}
