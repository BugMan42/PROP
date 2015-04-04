package Domini;

import java.util.Date;

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
    private Date fecha;
    private String subtipo;
    private int importancia;

    //Constructoras

    public Evento() {}

    /**Creadora con atributos obligatorios
     * Pre: name tiene que ser un string no vacio y date debe contener una fecha no vacia
     * Post: Se crea un evento solo con los atributos nombre y fecha
     */
    public Evento(String name, Date date) {
        nombre = name;
        fecha = date;
    }

    /**Creadora completa
     * Pre: name, subtype tienen que ser strings no vacíos, ¿¿¿¿date tiene que ser válida???? importance tiene que ser mayor que 0
     *Post: Se crea un evento con todos que serán name, date, subtype y importance.
     */
    public Evento(String name, Date date, String subtype, int importance) {
        if (name.equals("")) throw new IllegalArgumentException(error1);
        if (date == null) throw new IllegalArgumentException(error2);
        if (subtype.equals("")) throw new IllegalArgumentException(error3);
        if (importance <= 0) throw new IllegalArgumentException(error4);
        nombre = name;
        fecha = date;
        subtipo = subtype;
        importancia = importance;
    }

    //Modificadoras

    /**Modificadora de nombre
     * Pre: name tiene que ser un string no vacío
     * Post: Al nombre del evento se le ha asignado name
     */
    public void ModNombre(String name) {
        if (name.equals("")) throw new IllegalArgumentException(error1);
        this.nombre = name;
    }

    /**Modificadora de fecha
     * Pre: date tiene que ser válido
     * Post: A la fecha del evento se le ha asignado date
     */
    public void ModFecha (Date date) {
        if (date == null) throw new IllegalArgumentException(error2);
        fecha = date;
    }

    /**Modificadora de subtipo
     * Pre: subtype tiene que ser un string no vacío
     * Post: Al subtipo del evento se le ha asignado subtype
     */
    public void mod_subtipo(String subtype) {
        if (subtype.equals("")) throw new IllegalArgumentException(error3);
        subtipo = subtype;
    }
    /**Modificadora de importancia
     * Pre: importancia > 0
     * Post: A la importancia del evento se le ha asignado importance
     */
    public void mod_importancia(int importance) {
        if (importance <= 0) throw new IllegalArgumentException(error4);
        importancia = importance;
    }
    //Consultoras

    boolean igual(String name, Date date) {
        if (name.equals("")) throw new IllegalArgumentException(error1);
        if (date == null) throw new IllegalArgumentException(error2);
        return name.equals(nombre) && date.equals(fecha);
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
    public Date obt_fecha() {
        return fecha;
    }

    /**Consultora de subtipo
     * Pre: Cierto
     * Post: Devuelve el subtipo del evento
     */
    public String obt_subtipo() {
        return this.subtipo;
    }

    /**Consultora de importancia
     * Pre: Cierto
     * Post: Devuelve la importancia del evento
     */
    public int obt_importancia() {
        return this.importancia;
    }
}
