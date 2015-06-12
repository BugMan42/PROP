package Domini;


/**
 * Created by falc on 20/03/15.
 * Clase Votación
 */
public class Votacion extends Evento {

    //Creadora con pará,etros de Votación
    public Votacion(String name, Fecha date, int importance) throws Exception{
        //Llamada a la creadora con parámetros de Evento
        super(name, date, importance);
    }

/*
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
*/
    //Conversor de Votación a String
    public String toString() {
        return "Votacion " + obt_nombre() + " " + obt_fecha() + " " + Integer.toString(obt_importancia());
    }

    public String toStringF() {
        return "Votacion " + obt_fecha() + " " + obt_nombre() + " " + Integer.toString(obt_importancia());
    }

    public String toStringI() {
        return "Votacion " + Integer.toString(obt_importancia()) + " " + obt_nombre() + " " + obt_fecha();
    }

    public String tipo(){
        return "Votacion";
    }
}
