package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Voto
 */
public abstract class Voto {
    private Dni dni;

    public Voto() {}

    public Voto (String d) throws Exception {
        dni = new Dni(d);
    }

    public String obt_dni() {
        return dni.DNI();
    }

    public void mod_dni(String s) throws Exception {
        dni.modDni(s);
    }

    public abstract String obt_tipo();

    public String mostrarInfo() {
        return "El congresista con DNI "+dni;
    }

    //Modificable a necesidad
    public String toString() {
        return dni.toString();
    }

}
