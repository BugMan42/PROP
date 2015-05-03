package Domini;
/**
 * Clase Congresista
 */
public class Congresista {

    private class EdadNoValida extends Exception {
        public EdadNoValida(int edad) {
            super("Edad "+edad+" no Valida");
        }
    }

    /**
     * Dni es un Dni valido
     * Edad es un entero > 0
     * Los otros parametros son Strings
     *
     */
    private Dni Dni;
    private String Nombre;
    private String Apellido;
    private int Edad;
    private String Ciudad;
    private String Estado;

    private String Partido;
    //Creadoras
    /** Pre: edad valida
     *  Post: Se crea un Congresista con sus atributos
     *  Si edad no valida Excepcion
     */
    public Congresista (Dni dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        if (!EdadValida(edad)) throw new EdadNoValida(edad);
        Dni = dni;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Ciudad = ciudad;
        Estado = estado;
        Partido = partido;
    }

    /**
     * Creadora copiadora de un Congresista
     * Se crea un Congresista con los atributos
     * del Congresista c
     * es una copia en profundidad(deep copy)
     */
    public Congresista(Congresista c) {
        Dni = c.obtDni();
        Nombre = c.obtNombre();
        Apellido = c.obtApellido();
        Edad = c.obtEdad();
        Ciudad = c.obtCiudad();
        Estado = c.obtEstado();
        Partido = c.obtPartido();
    }
    //Modificadoras
    /**
     * Modifica el Dni
     */
    public void modDni(Dni dni) {
        Dni = dni;
    }
    /**
     * Modifica el Nombre
     */
    public void modNombre(String nombre) {
        Nombre = nombre;
    }
    /**
     * Modifica el Apellido
     */
    public void modApellido(String apellido) {
        Apellido = apellido;
    }
    /**
     * Pre: edad valida
     * Post: Modifica la edad
     * Si no excepcion
     */
    public void modEdad(int edad) throws Exception {
        if (!EdadValida(edad)) throw new EdadNoValida(edad);
        else Edad = edad;
    }
    /**
     * Modifica la Ciudad
     */
    public void modCiudad(String ciudad) {
        Ciudad = ciudad;
    }
    /**
     * Modifica el Estado
     */
    public void modEstado(String estado) {
        Estado = estado;
    }
    /**
     * Modifica el Partido
     */
    public void modPartido(String partido) {
        Partido = partido;
    }
    /**
     * Modifica todos sus atributos
     * Si edad no es valida lanza una excepcion
     */
    public void mod(Dni dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        if (!EdadValida(edad)) throw new EdadNoValida(edad);
        Dni = dni;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Ciudad = ciudad;
        Estado = estado;
        Partido = partido;
    }

    //Consultoras

    /**
     * Retorna el Dni del Congresista
     */
    public Dni obtDni() {
        return Dni;
    }
    /**
     * Retorna el Nombre del Congresista
     */
    public String obtNombre() {
        return Nombre;
    }
    /**
     * Retorna el Apellido del Congresista
     */
    public String obtApellido() {
        return Apellido;
    }
    /**
     * Retorna la Edad del Congresista
     */
    public int obtEdad() {
        return Edad;
    }
    /**
     * Retorna la Ciudad del Congresista
     */
    public String obtCiudad() {
        return Ciudad;
    }
    /**
     * Retorna el Estado del Congresista
     */
    public String obtEstado() {
        return Estado;
    }
    /**
     * Retorna el Partido del Congresista
     */
    public String obtPartido() {
        return Partido;
    }
    /**
     * Retorna el Identificador del Congresista
     * retorna el dni en string
     */
    public String ID() {
        return Dni.toString();
    }
    /**
     * Retorna un String conteniendo todos sus
     * atributos
     */
    public String toString() {
        return Dni+" "+Nombre+" "+Apellido+" "+Edad+" "+Ciudad+" "+Estado+" "+Partido;
    }

    /**
     * Valida la edad: Comprueba si
     * el parametro n es un entero > 0
     */
    private static boolean EdadValida(int n) {
        return n > 0;
    }
}
