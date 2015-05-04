package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Evento
 */
public abstract class Evento {
    protected String nombre;
    protected Fecha fecha;
    protected int importancia;

    //Constructoras

    /**Creadora con atributos obligatorios
     * Pre: name tiene que ser un string no vacio y Fecha debe contener una fecha no vacia
     * Post: Se crea un evento solo con los atributos nombre y fecha
     */
    public Evento(String name, String date) throws NoValido {
        if (name.equals("")) throw new NoValido("nombre", 0);
        if (date.equals("")) throw new NoValido("fecha", 0);
        nombre = name;
        fecha = new Fecha(date);
    }
    /**Creadora completa
     * Pre: name tiene que ser no vacio, date tiene que ser valida, importance tiene que ser mayor que 0
     *Post: Se crea un evento con todos que seran name, date, subtype y importance.
     */
    public Evento(String name, String date, int importance) throws NoValido {
        if (name.equals("")) throw new NoValido("nombre", 0);
        if (date.equals("")) throw new NoValido("fecha", 0);
        if (importance <= 0) throw new NoValido("importancia", 0);
        fecha = new Fecha(date);
        nombre = name;
        importancia = importance;
    }

    //Modificadoras

    /**Modificadora de nombre
     * Pre: name tiene que ser un string no vacio y diferente del nombre actual
     * Post: Al nombre del evento se le ha asignado name
     */
    public void ModNombre(String name) throws NoValido{
        if (name.equals("")) throw new NoValido("nombre", 0);
        if (!nombre.equals(name)) nombre = name;
    }

    /**Modificadora de fecha
     * Pre: date tiene que ser valido y diferente de la fecha actual
     * Post: A la fecha del evento se le ha asignado date
     */
    public void ModFecha (String date) throws NoValido {
        if (fecha.ConsultarFecha().equals(date)) fecha = new Fecha(date);
    }

    /**Modificadora de importancia
     * Pre: importancia > 0 y diferente de la importancia actual
     * Post: A la importancia del evento se le ha asignado importance
     */
    public void ModImportancia(int importance) throws NoValido {
        if (importance <= 0) throw new NoValido("Importancia", 0);
        if (importancia != importance) importancia = importance;
    }

    //Consultoras

    /**Consultora de nombre
     * Pre: Cierto
     * Post: Devuelve el nombre del evento
     */
    public String obt_nombre() {
        return nombre;
    }

    /**Consultora de fecha
     * Pre: Cierto
     * Post: Devuelve la fecha del evento
     */
    public String obt_fecha() {
        return fecha.ConsultarFecha();
    }

    /**Consultora de fecha sin barras
     * Pre : Cierto
     * Post: Devuelve la fecha sin barras separadoras entre el dia, el mes y el año
     */
    public String obtFecha() {
        return fecha.ToString();
    }

    /**Consultora del Identificador de la clase
     * Pre: Cierto
     * Post: Devuelve el nombre y la fecha concatenados
     */
    public String ID() {
        return nombre+fecha.ToString();
    }

    public abstract String toString();

    /**Consultora de importancia
     * Pre: Cierto
     * Post: Devuelve la importancia del evento
     */
    public int obt_importancia() {
        return importancia;
    }
}
