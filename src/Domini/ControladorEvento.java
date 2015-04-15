package Domini;


import java.text.*;
import java.util.ArrayList;

public class ControladorEvento {
    private ArrayList<Evento> Listado;
    /*static final String error1 = "El evento no exite";
    static final String error2 = "Nombre no puede ser vacio";
    static final String error3 = "Fecha no puede ser null";
    static final String error4 = "El nuevo nombre no puede ser vacío";
    static final String error5 = "Ya existe un evento con ese nombre y esa fecha";
    static final String error6 = "Subtipo no puede ser vacio";
    static final String error7 = "La importancia no puede ser igual o menor que 0";
*/
    /**Comprueba si los parámetros son válidos
     * Pre: Cierto
     *Post: Devuleve true en caso de que nombre no sea vacío
     * y fecha no sea null
     */
    private boolean Valido(String nombre, String fecha) {
        if (nombre.equals("")) throw new IllegalArgumentException();
        if (!Fecha.valido(fecha)) throw new IllegalArgumentException();
        return true;
    }

    /**Buscadora de eventos
     * Pre: nombre y fecha no pueden ser vacios
     * Post: Devuelve el índice del evento especificado por el nombre
     * y por la fecha si existe. En caso contrario devuelve -1
     */
    private int BuscarIndice(String nombre,String fecha) {
        int n = Listado.size();
        boolean found = false;
        int i = 0;
        while (!found && i < n) {
            if (Listado.get(i).obt_nombre().equals(nombre) && Listado.get(i).obt_fecha().equals(fecha)) found = true;
            else ++i;
        }
        if (!found) i = -1;
        return i;
    }

    /**Creadora de controlador de evento
     * Pre: Cierto
     * Post: Crea un nuevo controlador de evento
     */
    public ControladorEvento() {
        Listado = new ArrayList<Evento>();
    }

    /**Elimina un evento del conjunto de eventos
     * Pre: nombre y fecha no pueden ser vacios
     * Post: El evento e ha sido añadido al conjunto de eventos
     */
    public void EliminarEvento(String nombre, String fecha) {
        if (Valido(nombre, fecha)) {
            int i = BuscarIndice(nombre, fecha);
            if (i != -1) Listado.remove(i);
            else throw new IllegalArgumentException();
        }
    }

    /**Elinina todos los eventos
     * Pre: Cierto
     * Post: Todos los eventos serán eliminados
     */
    public void EliminarCjtEventos() {
        Listado.clear();
    }

    //Modificadoras

    /**Añade un evento al conjunto de eventos
     * Pre: Evento e no puede ser nulo
     * Post: El evento e ha sido añadido al conjunto de eventos
     */
    public void AgregarEvento(Evento e) {
        if (e == null) throw new IllegalArgumentException();
        Listado.add(e);
    }

    public void CrearEvento(String nombre, String fecha, String subtipo, int importancia) throws ParseException {

    }

    /** Modificadora del nombre de un evento
     * Pre: nomViejo, fecha y nomNuevo no pueden ser vacíos,
     * el evento especificado por nomViejo y fecha tiene que existir y el
     * evento identificado por nomNuevo y fecha no puede existir
     * Post: Al evento especificado por nomViejo y fecha se le ha cambiado el nombre por nomNuevo
     */
    public void ModificarNombreEvento(String nomViejo, String fecha, String nomNuevo) {
        if (Valido(nomViejo, fecha)) {
            if (!nomNuevo.equals("")) {
                int i = BuscarIndice(nomViejo, fecha);
                if (i != -1) {
                    if (BuscarIndice(nomNuevo, fecha) != -1) Listado.get(i).ModNombre(nomNuevo);
                    else throw new IllegalArgumentException();
                }
                else throw new IllegalArgumentException();
            }
            else throw new IllegalArgumentException();
        }
    }

    /** Modificadora de la fecha de un evento
     * Pre: nombre, fechaVieja y fechaNueva no puede ser vacíos, el evento especificado
     *  por nombre y fechaVieja tiene que existir y el evento identificado
     *  por nombre y fechaNueva no puede existir
     * Post: Al evento especificado por nombre y fechaVieja se le ha cambiado la fecha por fechaNueva
     */
    public void ModificarFechaEvento(String nombre, String fechaVieja, String fechaNueva) {
        if (Valido(nombre, fechaVieja)) {
            if (Fecha.valido(fechaNueva)) {
                int i = BuscarIndice(nombre, fechaVieja);
                if (i != -1) {
                    if (BuscarIndice(nombre, fechaNueva) != -1) Listado.get(i).ModFecha(fechaNueva);
                    else throw new IllegalArgumentException();
                }
                else throw new IllegalArgumentException();
            }
            else throw new IllegalArgumentException();
        }
    }

    /** Modificadora del subtipo de un evento
     * Pre: nombre, fecha y subtipo no pueden ser vacíos,
     * el evento especificado por nombre y fecha tiene que existir
     * Post: Al evento especificado por nombre y fecha se le ha cambiado el subtipo por subtype
     */
    public void ModificarSubtipoEvento(String nombre, String fecha, String subtype) {
        if (Valido(nombre, fecha)) {
            if (!subtype.equals("")) {
                int i = BuscarIndice(nombre, fecha);
                if (i != -1) Listado.get(i).ModSubtipo(subtype);
                else throw new IllegalArgumentException();
            }
            else throw new IllegalArgumentException();
        }
    }

    /** Modificadora de la importancia de un evento
     * Pre: nombre y fecha no pueden ser vacios, importance > 0
     * el evento especificado por nombre y fecha tiene que existir
     * Post: Al evento especificado por nombre y fecha se le ha cambiado la importancia por importance
     */
    public void ModificarImpEvento(String nombre, String fecha, int importance) {
        if (Valido(nombre, fecha)) {
            if (importance > 0) {
                int i = BuscarIndice(nombre, fecha);
                if (i != -1) Listado.get(i).ModImportancia(importance);
                else throw new IllegalArgumentException();
            }
            else throw new IllegalArgumentException();
        }
    }

    /**Agrega un evento con parámetros random
     * Pre: Cierto
     * Post: Se ha agregado al conjunto un evento nuevo con
     * parámetros random
     */
    public void AgregarEventoRandom() {}

    //Consultoras

    /**Consultora de todos los eventos
     * Pre: Cierto
     * Post: Devuelve una lista con todos los eventos
     */
    public ArrayList<Evento> ConsultarTodosEventos() {
        return Listado;
    }

    /**Consultora de un evento
     * Pre: nombre y fecha no pueden ser vacíos y
     * el evento especificado por nombre y fecha tiene que existir
     * Post: Devuelve el evento especificado por nombre y fecha
     */
    public Evento ConsultarEvento(String nombre, String fecha) {
        if (Valido(nombre, fecha)) {
            int i = BuscarIndice(nombre, fecha);
            if (i != -1) return Listado.get(i);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();
    }

    /**
     * Pre: nombre y fecha no pueden ser vacíos
     * Post: Devuelve si el evento especificado por nombre y fecha existe
     */
    public boolean ExisteEvento(String nombre, String fecha) {
        boolean existe = false;
        if (Valido(nombre, fecha)) {
            int i = BuscarIndice(nombre, fecha);
            if (i != -1) existe = true;
        }
        return existe;
    }
}