package Domini;


import java.util.ArrayList;
import java.util.Date;
import Domini.Evento;

public class ControladorEvento {
    private ArrayList<Evento> Listado;
    static final String error1 = "El evento no exite";
    static final String error2 = "Nombre no puede ser vacio";
    static final String error3 = "Fecha no puede ser null";
    static final String error4 = "El nuevo nombre no puede ser vacio";
    static final String error5 = "Ya existe un evento con ese nombre y esa fecha";

    /**Comprueba si los parámetros son válidos
     * Pre: Cierto
     *Post: Devuleve true en caso de que nombre no sea vacío
     * y fecha no sea null
     */
    private boolean Valido(String nombre, Date fecha) {
        if (nombre.equals("")) throw new IllegalArgumentException(error2);
        if (fecha == null) throw new IllegalArgumentException(error3);
        return true;
    }
    /**Buscadora de eventos
     * Pre: nombre no puede ser vacio y date no puede ser null
     * Post: Devuelve el indice del evento especificado por el nombre
     * y por la fecha si existe. En caso contrario devuelve -1
     */
    private int BuscarIndice(String nombre, Date fecha) {
        int n = Listado.size();
        boolean found = false;
        int i = 0;
        while (!found && i < n) {
            if (Listado.get(i).igual(nombre, fecha)) found = true;
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

    //Modificadoras

    /**Añade un evento al conjunto de eventos
     * Pre: Evento e no puede ser nulo
     * Post: El evento e ha sido añadido al conjunto de eventos
     */
    public void AgregarEvento(Evento e) {
        if (e == null) throw new IllegalArgumentException(error1);
        Listado.add(e);
    }

    public void CrearEvento(String nombre, Date fecha, String subtipo, int importancia) {
        /////HACER

        /////////
    }

    /** Modificadora del nombre de un evento
     * Pre: nomViejo y nomNuevo no pueden ser vacíos, fecha no puede ser null,
     * el evento específicado por nomViejo y fecha tiene que existir y el
     * evento identificado por nomNuevo y fecha no puede existir
     * Post: Al evento específicado por nomViejo y fecha se le ha cambiado el nombre por nomNuevo
     */
    public void ModificarNombreEvento(String nomViejo, Date fecha, String nomNuevo) {
        if (Valido(nomViejo, fecha)) {
            if (!nomNuevo.equals("")) {
                int i = BuscarIndice(nomViejo, fecha);
                if (i != -1) {
                    if (BuscarIndice(nomNuevo, fecha) != -1) Listado.get(i).ModNombre(nomNuevo);
                    else throw new IllegalArgumentException(error5);
                }
                else throw new IllegalArgumentException(error1);
            }
            else throw new IllegalArgumentException(error4);
        }
    }

    /** Modificadora de la fecha de un evento
     * Pre: nombre no puede ser vacío, fechaVieja y fechaNueva no pueden ser nulls,
     * el evento específicado por nombre y fechaVieja tiene que existir y el
     * evento identificado por nombre y fechaNueva no puede existir
     * Post: Al evento específicado por nombre y fechaVieja se le ha cambiado la fecha por fechaNueva
     */
    public void ModificarFechaEvento(String nombre, Date fechaVieja, Date fechaNueva) {
        if (Valido(nombre, fechaVieja)) {
            if (fechaNueva != null) {
                int i = BuscarIndice(nombre, fechaVieja);
                if (i != -1) {
                    if (BuscarIndice(nombre, fechaNueva) != -1) Listado.get(i).ModFecha(fechaNueva);
                    else throw new IllegalArgumentException(error5);
                }
                else throw new IllegalArgumentException(error1);
            }
            else throw new IllegalArgumentException(error4);
        }
    }

    /**Elimina un evento del conjunto de eventos
     * Pre: nombre no puede ser vacío y fecha no puede ser null
     * Post: El evento e ha sido añadido al conjunto de eventos
     */
    public void EliminarEvento(String nombre, Date fecha) {
        if (Valido(nombre, fecha)) {
            int i = BuscarIndice(nombre, fecha);
            if (i != -1) Listado.remove(i);
            else throw new IllegalArgumentException(error1);
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
     * Pre: nombre no puede ser vacio, fecha no puede ser null y
     * el evento específicado por nombre y fecha tiene que existir
     * Post: Devuelve el evento específicado por nombre y fecha
     */
    public Evento ConsultarEvento(String nombre, Date fecha) {
        if (Valido(nombre, fecha)) {
            int i = BuscarIndice(nombre, fecha);
            if (i != -1) return Listado.get(i);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException();
    }


    /*: Evento
    ConsultarEventosAsociadosCongresista(nombre : string)
    ModificarEvento(nom : string, fecha : DateTime)
    EliminarCjtEventos()  */

}