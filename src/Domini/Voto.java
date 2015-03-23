package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Voto
 */
public abstract class Voto {
    private String dni;

    public Voto (String dni)
    {
        this.dni = dni;
    }

    public String obt_dni() {
        return this.dni;
    }

    public abstract String obt_tipo();

}
