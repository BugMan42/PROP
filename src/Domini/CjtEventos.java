package Domini;

import java.util.ArrayList;

/**
 * Created by usuario on 26/04/2015.
 */
public class CjtEventos {
    private TST<Evento> cjt;
    private ArrayList<Evento> Listado;

    /**Comprueba si los parámetros son válidos
     * Pre: Cierto
     *Post: Devuelve true en caso de que nombre no sea vac�o
     * y fecha no sea null
     */
    private boolean Valido(String nombre, String fecha) throws NoValido {
        if (nombre.equals("")) throw new NoValido("Nombre");
        if (!Fecha.valido(fecha)) throw new NoValido("Fecha");
        return true;
    }

    /**Buscadora de eventos
     * Pre: nombre y fecha no pueden ser vacios
     * Post: Devuelve el índice del evento especificado por el nombre
     * y por la fecha si existe. En caso contrario devuelve -1
     */
    /*private int BuscarIndice(String nombre,String fecha) {
        int n = Listado.size();
        boolean found = false;
        int i = 0;
        while (!found && i < n) {
            if (Listado.get(i).obt_nombre().equals(nombre) && Listado.get(i).obt_fecha().equals(fecha)) found = true;
            else ++i;
        }
        if (!found) i = -1;
        return i;
    }*/

    /**Creadora de controlador de evento
     * Pre: Cierto
     * Post: Crea un nuevo controlador de evento
     */
    public CjtEventos() {
        Listado = new ArrayList<Evento>();
        cjt = new TST<Evento>();
    }

    /**Elimina un evento del conjunto de eventos
     * Pre: nombre y fecha no pueden ser vacios
     * Post: El evento e ha sido a�adido al conjunto de eventos
     */
    public void EliminarEvento(String nombre, String fecha) throws NoValido{
        if (Valido(nombre, fecha)) {
            cjt.borrar(nombre+fecha);
        }
    }

    /**Elinina todos los eventos
     * Pre: Cierto
     * Post: Todos los eventos ser�n eliminados
     */
    public void EliminarCjtEventos() {
        cjt = null;
    }

    //Modificadoras

    /**A�ade un evento al conjunto de eventos
     * Pre: Evento e no puede ser nulo
     * Post: El evento e ha sido a�adido al conjunto de eventos
     */
    public void AgregarEvento(Evento e) throws NoValido{
        if (e == null) throw new NoValido("Evento");
        cjt.insertar(e.obt_nombre()+e.obt_fecha(), e);
    }

    /** Modificadora del nombre de un evento
     * Pre: nomViejo, fecha y nomNuevo no pueden ser vacíos,
     * el evento especificado por nomViejo y fecha tiene que existir y el
     * evento identificado por nomNuevo y fecha no puede existir
     * Post: Al evento especificado por nomViejo y fecha se le ha cambiado el nombre por nomNuevo
     */
    public void ModificarNombreEvento(String nomViejo, String fecha, String nomNuevo) throws NoValido{
        ////////////////////////////////HACER/////////////////////////
        /*if (Valido(nomViejo, fecha)) {
            if (!nomNuevo.equals("")) {
                if (cjt.existe(nomViejo+fecha)) {
                    if (!cjt.existe(nomNuevo+fecha)) {
                        ///////////////////HACER////////////
                    }
                    else throw new IllegalArgumentException();
                }
                else throw new IllegalArgumentException();
            }
            else throw new IllegalArgumentException();
        }*/
    }

    /** Modificadora de la fecha de un evento
     * Pre: nombre, fechaVieja y fechaNueva no puede ser vac�os, el evento especificado
     *  por nombre y fechaVieja tiene que existir y el evento identificado
     *  por nombre y fechaNueva no puede existir
     * Post: Al evento especificado por nombre y fechaVieja se le ha cambiado la fecha por fechaNueva
     */
    public void ModificarFechaEvento(String nombre, String fechaVieja, String fechaNueva) throws Exception{
        //////////////////HACER//////////////////////////////
        /*if (Valido(nombre, fechaVieja)) {
            if (!fechaNueva.equals("")) {
                if (cjt.existe(nombre+fechaVieja)) {
                    if (!cjt.existe(nombre+fechaVieja)) {
                        //////////////////HACER////////////
                    }
                    else throw new IllegalArgumentException();
                }
                else throw new IllegalArgumentException();
            }
            else throw new IllegalArgumentException();
        }*/
    }

    /** Modificadora del subtipo de un evento
     * Pre: nombre, fecha y subtipo no pueden ser vacíos,
     * el evento especificado por nombre y fecha tiene que existir
     * Post: Al evento especificado por nombre y fecha se le ha cambiado el subtipo por subtype
     */
    /*public void ModificarSubtipoEvento(String nombre, String fecha, String subtype) {
        if (Valido(nombre, fecha)) {
            if (!subtype.equals("")) {
                int i = BuscarIndice(nombre, fecha);
                if (i != -1) Listado.get(i).ModSubtipo(subtype);
                else throw new IllegalArgumentException();
            }
            else throw new IllegalArgumentException();
        }
    }*/

    /** Modificadora de la importancia de un evento
     * Pre: nombre y fecha no pueden ser vacios, importance > 0
     * el evento especificado por nombre y fecha tiene que existir
     * Post: Al evento especificado por nombre y fecha se le ha cambiado la importancia por importance
     */
    public void ModificarImpEvento(String nombre, String fecha, int importance) throws NoValido{
        ///////////////////////////////////HACER///////////////////////////////////
        /*if (Valido(nombre, fecha)) {
            if (importance > 0) {
                int i = BuscarIndice(nombre, fecha);
                if (i != -1) Listado.get(i).ModImportancia(importance);
                else throw new IllegalArgumentException();
            }
            else throw new IllegalArgumentException();
        }*/
    }

    /**Agrega un evento con parámetros random
     * Pre: Cierto
     * Post: Se ha agregado al conjunto un evento nuevo con
     * par�metros random
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
    public Evento ConsultarEvento(String nombre, String fecha) throws NoValido{
        if (Valido(nombre, fecha)) return cjt.obtener(nombre+fecha);
        else throw new IllegalArgumentException();
    }

    /**
     * Pre: nombre y fecha no pueden ser vacíos
     * Post: Devuelve si el evento especificado por nombre y fecha existe
     */
    public boolean ExisteEvento(String nombre, String fecha) throws NoValido {
        Valido(nombre, fecha);
        return cjt.existe(nombre+fecha);
    }
}
