package Domini;

/**
 * Created by bug on 20/03/15.
 */
public class Congresista {
    //Atributos para congresistas: ninguno puede ser no validos o null
    //formato: 9 caracteres (8 numeros y 1 letra)
    private Dni Dni;
    private String Nombre;
    private String Apellido;
    //edad es un entero mayor que cero
    private int Edad;
    private String Ciudad;
    private String Estado;
    private String Partido;

    static final String error1 = "Dni no valido";
    static final String error2 = "Nombre no valido";
    static final String error3 = "Apellido no valido";
    static final String error4 = "Edad no valida";
    static final String error5 = "Ciudad no valida";
    static final String error6 = "Estado no valido";
    static final String error7 = "Partido no valido";

    //Creadoras
    //public Congresista() { }
    // Creadora de un Congresista -> se validará :
    // dni es un string de 9 caracteres y 8 son numeros y el último carácter es una letra
    // los otros parámetros no se validan ----???? hay que validar si son vacios?
    public Congresista (Dni dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        if (!EdadValida(edad)) throw new IllegalArgumentException(error3);
        Dni = dni;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Ciudad = ciudad;
        Estado = estado;
        Partido = partido;
    }

    //Modificadoras
    public void modDni(Dni dni) {
        Dni = dni;
    }
    public void modNombre(String nombre) {
        Nombre = nombre;
    }
    public void modApellido(String apellido) {
        Apellido = apellido;
    }
    public void modEdad(int edad) {
        if (EdadValida(edad)) {
            Edad = edad;
        }
        else throw new IllegalArgumentException(error4);
    }
    public void modCiudad(String ciudad) {
        Ciudad = ciudad;
    }
    public void modEstado(String estado) {
        Estado = estado;
    }
    public void modPartido(String partido) {
        Partido = partido;
    }
    //public void mod(String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        //this.modDni();
    //}

    //Consultoras
    public Dni obtDni() {
        return Dni;
    }
    public String obtNombre() {
        return Nombre;
    }
    public String obtApellido() {
        return Apellido;
    }
    public int obtEdad() {
        return Edad;
    }
    public String obtCiudad() {
        return Ciudad;
    }
    public String obtEstado() {
        return Estado;
    }
    public String obtPartido() {
        return Partido;
    }
    public String toString() {
        return Dni+" "+Nombre+" "+Apellido+" "+Edad+" "+Ciudad+" "+Estado+" "+Partido;
    }
    //valida si edad es valida
    // se considera valida edad > 0
    private static boolean EdadValida(int n) {
        return n > 0;
    }
}
