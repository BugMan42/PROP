package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Voto
 */
public abstract class Voto {
    private String dni;

    public Voto() {}

    public Voto (String dni)
    {
        this.dni = dni;
    }

    public String obt_dni() {
        return this.dni;
    }

    public void mod_dni(String s) {
        dni = s;
    }

    public abstract String obt_tipo();

    //Modificable a necesidad
    public String toString() {
        return "El congresista con DNI "+dni+" ha votado ";
    }

}
