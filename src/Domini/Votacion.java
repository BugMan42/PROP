package Domini;


/**
 * Created by falc on 20/03/15.
 * Clase Votación
 */
public class Votacion extends Evento {

    //Estructura de datos que ordena los votos según el DNI que los identifica
    private TST<Voto> votos = new TST<Voto>();

    //Creadora con pará,etros de Votación
    public Votacion(String name, String date, int importance) throws Exception{
        //Llamada a la creadora con parámetros de Evento
        super(name, date, importance);
    }

    // Método de inserción de votos mediante un String diferente al DNI del Voto
    public void añadir_voto(String id, Voto v) throws Exception {
        votos.insertar(id, v);
    }

    //Método de inserción de votos teniendo como clave para insertar el DNI del Voto
    public void añadir_voto(Voto v) throws Exception {
        votos.insertar(v.obt_dni(), v);
    }

    //Siendo id el DNI de v, este método permite canviar el tipo de Voto de un Congresista para una votación
    public void modificar_voto(String id, Voto v) throws Exception {
        votos.borrar(id);
        votos.insertar(id, v);
    }

    //Elimina el Voto cuyo identificador es igual a id
    public void eliminar_voto(String id) throws Exception {
        votos.borrar(id);
    }

    //Devuelve un string con la información del Voto cuyo DNI es igual a id
    public String consultar_voto(String id) throws Exception {
        return votos.obtener(id).mostrarInfo();
    }

    //Devuelve el Voto que se identifica con id
    public Voto obt_voto(String id) throws Exception {
        return votos.obtener(id);
    }

    //Conversor de Votación a String
    public String toString() {
        return "Votacion " + nombre + " " + fecha.ConsultarFecha() + " " + Integer.toString(importancia);
    }

    public String tipo(){
        return "Votacion";
    }
}
