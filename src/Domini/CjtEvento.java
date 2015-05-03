package Domini;

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

    //////////////////////////////////////CREADORA////////////////////////

    /**Creadora de conjunto de evento
     * Pre: Cierto
     * Post: Crea un nuevo conjunto de eventos
     */
    public CjtEvento() {
        cjt = new TST<Evento>();
    }

    /////////////////////////////////////ELIMINADORAS/////////////////////

    /**Elimina un evento del conjunto de eventos
     * Pre: nombre no puede ser vacío, fecha tiene que ser valida y
     * el evento especificado por nombre y fecha tiene que existir
     * Post: El evento espcificado por nombre y fecha ha sido eliminado del conjunto de eventos
     */
    public void EliminarEvento(String nombre, String fecha) throws Exception{
        Valido(nombre, fecha);
        //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque sino no se encontrara el objeto
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        cjt.borrar(nombre + fecha);
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
     * Post: El evento e ha sido añadido al conjunto de eventos
     */
    public void AgregarEvento(Evento e) throws Exception{
        if (e == null) throw new NoValido("Evento", 0);
        cjt.insertar(e.ID(), e);
    }

    /** Modificadora del nombre de un evento
     * Pre: nomViejo y nomNuevo no pueden ser vacíos, fecha tiene que ser valida
     * el evento especificado por nomViejo y fecha tiene que existir y el
     * evento identificado por nomNuevo y fecha no puede existir
     * Post: Al evento especificado por nomViejo y fecha se le ha cambiado el nombre por nomNuevo
     */
    public void ModificarNombreEvento(String nomViejo, String fecha, String nomNuevo) throws Exception{
        Valido(nomViejo, fecha);
        if (!nomNuevo.equals("")) {
        //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque sino no se encontrara el objeto
            String s[] = fecha.split("/");
            fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
            //Como obtener pasa la referencia al objeto lo modifico y lo pongo correctamente en el conjunto de acuerdo a su nueva clave
            Evento aux = cjt.obtener(nomViejo + fecha);
            aux.ModNombre(nomNuevo);
            cjt.modificar(nomViejo+fecha, nomNuevo+fecha, aux);
        }
        else throw new NoValido("NombreNuevo", 0);
    }

    /** Modificadora de la fecha de un evento
     * Pre: nombreno puede ser vacio, fechaVieja y fechaNueva tienen que ser validas,
     * el evento especificado por nombre y fechaVieja tiene que existir y
     * el evento identificado por nombre y fechaNueva no puede existir
     * Post: Al evento especificado por nombre y fechaVieja se le ha cambiado la fecha por fechaNueva
     */
    public void ModificarFechaEvento(String nombre, String fechaVieja, String fechaNueva) throws Exception {
        Valido(nombre, fechaVieja);
        if (Fecha.valido(fechaNueva)) {
            //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque sino no se encontrara el objeto
            String f[] = fechaVieja.split("/");
            fechaVieja = Integer.toString(Integer.parseInt(f[0]))+Integer.toString(Integer.parseInt(f[1]))+Integer.toString(Integer.parseInt(f[2]));
            //Como obtener pasa la referencia al objeto lo modifico y lo pongo correctamente en el conjunto de acuerdo a su nueva clave
            Evento aux = cjt.obtener(nombre + fechaVieja);
            aux.ModFecha(fechaNueva);
            String s[] = fechaNueva.split("/");
            fechaNueva = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
            cjt.modificar(nombre+fechaVieja, nombre+fechaNueva, aux);
        }
        else throw new NoValido("FechaNueva", 0);
    }

    /** Modificadora de la importancia de un evento
     * Pre: nombre no puede ser vacio, fecha tiene que ser valido, importance > 0
     * el evento especificado por nombre y fecha tiene que existir
     * Post: Al evento especificado por nombre y fecha se le ha cambiado la importancia por importance
     */
    public void ModificarImpEvento(String nombre, String fecha, int importance) throws Exception {
        Valido(nombre, fecha);
        //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque sino no se encontrara el objeto
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        //Como el tst devuelve la refencia al objeto directamente puedo cambiarle los atributos
        cjt.obtener(nombre+fecha).ModImportancia(importance);
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
     * Pre: nombre no puede ser vacío y fecha tiene que ser valida
     * el evento especificado por nombre y fecha tiene que existir
     * Post: Devuelve el evento especificado por nombre y fecha
     */
    public Evento ConsultarEvento(String nombre, String fecha) throws Exception{
        Valido(nombre, fecha);
        //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque sino no se encontrara el objeto
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
        //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque sino no se encontrara el objeto
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        return cjt.existe(nombre+fecha);
    }

    /**Consultora del tamaño del conjunto
     * Pre: Cierto
     * Post: Devuelve el numero de elementos que se encuentran en el conjunto.
     */
    public int size() {
        return cjt.size();
    }
}
