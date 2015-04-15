package Domini;

/**
 * Created by falc on 20/03/15.
 * Clase Evento
 */
public abstract class Evento {
    static final String error1 = "Nombre no puede ser vacio";
    static final String error2 = "Fecha no puede ser null";
    static final String error3 = "Subtipo no puede ser vacio";
    static final String error4 = "Importancia tiene que ser mayor que 0";
    private String nombre;
    private Fecha fecha;
    private String subtipo;
    private int importancia;

    //Constructoras

    public Evento() {}

    /**Creadora con atributos obligatorios
     * Pre: name tiene que ser un string no vacio y Fecha debe contener una fecha no vacia
     * Post: Se crea un evento solo con los atributos nombre y fecha
     */
    public Evento(String name, String date) {
        if (name.equals("")) throw new IllegalArgumentException("1");
        if (date.equals("")) throw new IllegalArgumentException("2");
        nombre = name;
        fecha = new Fecha(date);//Se creará una excepción que tiene que subir hasta el driver en teoria
    }
    /**Creadora completa
     * Pre: name, subtype tienen que ser strings no vac�os, ����date tiene que ser v�lida???? importance tiene que ser mayor que 0
     *Post: Se crea un evento con todos que ser�n name, date, subtype y importance.
     */
    public Evento(String name, String date, String subtype, int importance) {
        if (name.equals("")) throw new IllegalArgumentException("1");
        if (date.equals("")) throw new IllegalArgumentException("2");
        if (subtype.equals("")) throw new IllegalArgumentException("3");
        if (importance <= 0) throw new IllegalArgumentException("4");
        fecha = new Fecha(date);
        nombre = name;
        subtipo = subtype;
        importancia = importance;
    }

    //Modificadoras

    /**Modificadora de nombre
     * Pre: name tiene que ser un string no vac�o
     * Post: Al nombre del evento se le ha asignado name
     */
    public void ModNombre(String name) {
        if (name.equals("")) throw new IllegalArgumentException("1");
        nombre = name;
    }

    /**Modificadora de fecha
     * Pre: date tiene que ser v�lido
     * Post: A la fecha del evento se le ha asignado date
     */
    public void ModFecha (String date) {
        try {
            fecha = new Fecha(date);
        }
        catch (IllegalArgumentException e) {
            ////////////////////////////HACER

            /////////////////////////////HACER
        };
    }

    /**Modificadora de subtipo
     * Pre: subtype tiene que ser un string no vac�o
     * Post: Al subtipo del evento se le ha asignado subtype
     */
    public void ModSubtipo(String subtype) {
        if (subtype.equals("")) throw new IllegalArgumentException("3");
        subtipo = subtype;
    }
    /**Modificadora de importancia
     * Pre: importancia > 0
     * Post: A la importancia del evento se le ha asignado importance
     */
    public void ModImportancia(int importance) {
        if (importance <= 0) throw new IllegalArgumentException("4");
        importancia = importance;
    }

    //Consultoras

    boolean igual(String name, String date) {
        if (name.equals("")) throw new IllegalArgumentException("1");
        return name.equals(nombre) && fecha.igual(date);
    }

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

    /**Consultora de subtipo
     * Pre: Cierto
     * Post: Devuelve el subtipo del evento
     */
    public String obt_subtipo() {
        return subtipo;
    }

    /**Consultora de importancia
     * Pre: Cierto
     * Post: Devuelve la importancia del evento
     */
    public int obt_importancia() {
        return importancia;
    }
}
