package Domini;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by falc on 20/03/15.
 * Clase Votación
 */
public class Votacion extends Evento {

    private ArrayList<Voto> votos;

    public Votacion(String nombre, Date fecha) {
        super(nombre, fecha);
        votos = new ArrayList<Voto>();
    }

    public Votacion() {}

    public void añadir_voto(Voto v) {
        votos.add(v);
    }

    public void eliminar_voto(int id) {
        votos.remove(id);
    }

    public String consultar_voto(int id) {
        return votos.get(id).getClass().getSimpleName();
    }
}
