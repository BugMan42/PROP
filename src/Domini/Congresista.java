package Domini;

/**
 * Created by bug on 20/03/15.
 */
public class Congresista {
    //Atributos para congresistas: ninguno puede ser no validos o null
    //formato: 9 caracteres (8 numeros y 1 letra)
    private String Dni;
    private String Nombre;
    private String Apellido;
    //edad es un entero mayor que cero
    private int Edad;
    private String Ciudad;
    private String Estado;
    private String Partido;

    //Creadoras
    //public Congresista() { }
    // Creadora de un Congresista -> se validará :
    // dni es un string de 9 caracteres y 8 son numeros y el último carácter es una letra
    // los otros parámetros no se validan ----???? hay que validar si son vacios?
    public Congresista (String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        if (!validoDni(dni)) throw new IllegalArgumentException("0");
        if (!validoEdad(edad)) throw new IllegalArgumentException("3");
        Dni = dni;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Ciudad = ciudad;
        Estado = estado;
        Partido = partido;
    }

    //Modificadoras
    public void mod_dni(String dni) {
        if (validoDni(dni)) {
            Dni = dni;
        }
        else throw new IllegalArgumentException("0");
    }
    public void modNombre(String nombre) {
        if (valido(nombre)) {
            Nombre = nombre;
        }
        else throw new IllegalArgumentException("1");
    }
    public void modApellido(String apellido) {
        if (valido(apellido)) {
            Apellido = apellido;
        }
        else throw new IllegalArgumentException("2");
    }
    public void modEdad(int edad) {
        if (validoEdad(edad)) {
            Edad = edad;
        }
        else throw new IllegalArgumentException("3");
    }
    public void modCiudad(String ciudad) {
        if (valido(ciudad)) {
            Ciudad = ciudad;
        }
        else throw new IllegalArgumentException("4");
    }
    public void modEstado(String estado) {
        if (valido(estado)) {
            Estado = estado;
        }
        else throw new IllegalArgumentException("5");
    }
    public void modPartido(String partido) {
        if (valido(partido)) {
            Partido = partido;
        }
        else throw new IllegalArgumentException("6");
    }

    //Consultoras
    public String obtDni() {
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

    //Funciones estáticas de la clase
    //validar si contiene solo letras, no caracteres especiales
    /*private static boolean es_letra(String string) {
        for (char c : string.toCharArray()) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }*/
    private static boolean valido(String string) {
        return (string.length() > 0);
    }
    //validar si el dni es válido
    private static boolean validoDni(String aux) {
        if (aux.length() != 9) return false;
        for (int i = 0; i < aux.length(); ++i) {
            if (i == 8 && !Character.isLetter(aux.charAt(i))) return false;//puedo utilizarlo?
            else if (!Character.isDigit((aux.charAt(i)))) return false;
        }
        return true;

    }
    //valida si edad es valida
    // se considera valida edad > 0
    private static boolean validoEdad(int n) {
        return n > 0;
    }
}
