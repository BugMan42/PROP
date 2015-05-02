package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Voto
 */
public abstract class Voto {
    private Dni dni;

    public Voto() {
        dni = null;
    }

    public Voto (String d) throws Exception {
        dni = new Dni(d);
    }

    /* Obtener DNI
     * pre:
     * post: Devuelve el DNI que identifica el voto
    */
    public String obt_dni() {
        return dni.toString();
    }

    public void mod_dni(String s) throws Exception {
        if (dni == null) dni = new Dni(s);
        else dni.modDni(s);
    }

    public abstract String obt_tipo();

    /* Mostrar Información
    * Pre:
    * Post: Devuelve un string con la información del voto
    */
    public String mostrarInfo() {
        return "El congresista con DNI "+dni;
    }

    public String toString() {
        return dni.toString();
    }

}
