package Domini;

import java.util.Hashtable;


/**
 * Created by falc on 20/03/15.
 * Clase Votación
 */
public class Votacion extends Evento {

    private TST<Voto> votos = new TST<Voto>();

    public Votacion(String name, String date, int importance) throws Exception{
        super(name, date, importance);
    }

    // Métodos de inserción de votos
    public void añadir_voto(String id, Voto v) throws Exception {
        votos.insertar(id, v);
    }

    public void añadir_voto(Voto v) throws Exception {
        votos.insertar(v.toString(), v);
    }

    public void modificar_voto(String id, Voto v) throws Exception {
        votos.borrar(id);
        votos.insertar(id, v);
    }

    public void eliminar_voto(String id) throws Exception {
        votos.borrar(id);
    }

    public String consultar_voto(String id) throws Exception {
        return votos.obtener(id).mostrarInfo();
    }

    public Voto obt_voto(String id) throws Exception {
        return votos.obtener(id);
    }

    public String toString() {
        return "Votacion " + nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }

    public String tipo(){
        return "Votacion";
    }
}
