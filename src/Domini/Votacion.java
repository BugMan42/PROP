package Domini;

import java.util.Date;
import java.util.Hashtable;


/**
 * Created by falc on 20/03/15.
 * Clase Votación
 */
public class Votacion extends Evento {

    private Hashtable<String, Voto> votos = new Hashtable<String, Voto>();

    public Votacion(String nombre, Date fecha) {
        super(nombre, fecha);
    }

    public Votacion(String name, Date date, String subtype, int importance) {
        super(name, date, subtype, importance);
    }

    public Votacion() {}


    // Métodos de inserción de votos
    public void añadir_voto(String id, Voto v) {
        votos.put(id, v);
    }

    public void añadir_voto(Voto v) {
        votos.put(v.obt_dni(), v);
    }

    public void eliminar_voto(String id) {
        votos.remove(id);
    }

    public String consultar_voto(String id) {
        return votos.get(id).getClass().getSimpleName();
    }

    public Voto obt_voto(String id) {
        return votos.get(id);
    }
}
