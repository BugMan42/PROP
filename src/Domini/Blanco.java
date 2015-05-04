package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Blanco
 */
public class Blanco extends Voto {

    public Blanco() {}
    public Blanco(String dni) throws Exception {
        super(dni);
    }

    @Override
    public String obt_tipo() {
        return "Blanco";
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo()+" ha votado en blanco.";
    }

    public String toString() {
        return "Blanco";
    }

}
