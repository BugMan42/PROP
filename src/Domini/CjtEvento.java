package Domini;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 26/04/2015.
 */
public class CjtEvento {
    private TST<Evento> cjt;
    /**Comprueba si los parámetros son válidos
     * Pre: Cierto
     *Post: Devuelve true en caso de que nombre no sea vacio
     * y fecha sea valida
     */
    private void Valido(String nombre, String fecha) throws NoValido {
        if (nombre.equals("")) throw new NoValido("Nombre", 0);
        if (!Fecha.valido(fecha)) throw new NoValido("Fecha", 0);
    }

    /**Creadora de conjunto de evento
     * Pre: Cierto
     * Post: Crea un nuevo conjunto de eventos
     */
    public CjtEvento() {
        cjt = new TST<Evento>();
    }

    /**Elimina un evento del conjunto de eventos
     * Pre: nombre no puede ser vacío, fecha tiene que ser valida y
     * el evento especificado por nombre y fecha tiene que existir
     * Post: El evento espcificado por nombre y fecha ha sido eliminado del conjunto de eventos
     */
    public void EliminarEvento(String nombre, String fecha) throws Exception{
        Valido(nombre, fecha);
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        cjt.borrar(nombre+fecha);
    }

    /**Elinina todos los eventos
     * Pre: Cierto
     * Post: Todos los eventos han sido eliminados
     */
    public void EliminarCjtEvento() {
        cjt.vaciar();
    }

    //Modificadoras

    /**Añade un evento al conjunto de eventos
     * Pre: Evento e no puede ser nulo
     * Post: El evento e ha sido añaadido al conjunto de eventos
     */
    public void AgregarEvento(Evento e) throws Exception{
        if (e == null) throw new NoValido("Evento", 0);
        cjt.insertar(e.ID(), e);
    }

    /** Modificadora del nombre de un evento
     * Pre: nomViejo, fecha y nomNuevo no pueden ser vacíos,
     * el evento especificado por nomViejo y fecha tiene que existir y el
     * evento identificado por nomNuevo y fecha no puede existir
     * Post: Al evento especificado por nomViejo y fecha se le ha cambiado el nombre por nomNuevo
     */
    public void ModificarNombreEvento(String nomViejo, String fecha, String nomNuevo) throws Exception{
        Valido(nomViejo, fecha);
        if (!nomNuevo.equals("")) {
            String s[] = fecha.split("/");
            fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
            Evento aux = cjt.obtener(nomViejo+fecha);
            aux.ModNombre(nomNuevo);
            cjt.modificar(nomViejo+fecha, nomNuevo+fecha, aux);
        }
        else throw new NoValido("NombreNuevo", 0);
    }

    /** Modificadora de la fecha de un evento
     * Pre: nombre, fechaVieja y fechaNueva no puede ser vacios, el evento especificado
     *  por nombre y fechaVieja tiene que existir y el evento identificado
     *  por nombre y fechaNueva no puede existir
     * Post: Al evento especificado por nombre y fechaVieja se le ha cambiado la fecha por fechaNueva
     */
    public void ModificarFechaEvento(String nombre, String fechaVieja, String fechaNueva) throws Exception{
        Valido(nombre, fechaVieja);
        if (Fecha.valido(fechaNueva)) {
            String s[] = fechaNueva.split("/");
            fechaNueva = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
            Evento aux = cjt.obtener(nombre+fechaVieja);
            aux.ModFecha(fechaNueva);
            cjt.modificar(nombre+fechaVieja, nombre+fechaNueva, aux);
        }
        else throw new NoValido("FechaNueva", 0);
    }

    /** Modificadora de la importancia de un evento
     * Pre: nombre y fecha no pueden ser vacios, importance > 0
     * el evento especificado por nombre y fecha tiene que existir
     * Post: Al evento especificado por nombre y fecha se le ha cambiado la importancia por importance
     */
    public void ModificarImpEvento(String nombre, String fecha, int importance) throws Exception {
        Valido(nombre, fecha);
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        Evento aux = cjt.obtener(nombre+fecha);
        aux.ModImportancia(importance);
        cjt.modificar(nombre+fecha, aux);
    }

    /**Agrega un evento con parámetros random
     * Pre: Cierto
     * Post: Se ha agregado al conjunto un evento nuevo con
     * parametros random
     */
    public void AgregarEventoRandom() {}

    //Consultoras

    /**Consultora de todos los eventos
     * Pre: Cierto
     * Post: Devuelve una lista con todos los eventos
     */
    public List<Evento> ConsultarTodosEventos() {
        return cjt.consultarObjetos();
    }

    /**Consultora de un evento
     * Pre: nombre y fecha no pueden ser vacíos y
     * el evento especificado por nombre y fecha tiene que existir
     * Post: Devuelve el evento especificado por nombre y fecha
     */
    public Evento ConsultarEvento(String nombre, String fecha) throws Exception{
        Valido(nombre, fecha);
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        return cjt.obtener(nombre+fecha);
    }

    /**
     * Pre: nombre y fecha no pueden ser vacíos
     * Post: Devuelve si el evento especificado por nombre y fecha existe
     */
    public boolean ExisteEvento(String nombre, String fecha) throws NoValido {
        Valido(nombre, fecha);
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        return cjt.existe(nombre+fecha);
    }

    public int size() {
        return cjt.size();
    }
}
