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
    public Congresista (String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        if (!DniValido(dni)) throw new IllegalArgumentException(error1);
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
    public void modDni(String dni) {
        if (DniValido(dni)) {
            Dni = dni;
        }
        else throw new IllegalArgumentException(error1);
    }
    public void modNombre(String nombre) {
        if (valido(nombre)) {
            Nombre = nombre;
        }
        else throw new IllegalArgumentException(error2);
    }
    public void modApellido(String apellido) {
        if (valido(apellido)) {
            Apellido = apellido;
        }
        else throw new IllegalArgumentException(error3);
    }
    public void modEdad(int edad) {
        if (EdadValida(edad)) {
            Edad = edad;
        }
        else throw new IllegalArgumentException(error4);
    }
    public void modCiudad(String ciudad) {
        if (valido(ciudad)) {
            Ciudad = ciudad;
        }
        else throw new IllegalArgumentException(error5);
    }
    public void modEstado(String estado) {
        if (valido(estado)) {
            Estado = estado;
        }
        else throw new IllegalArgumentException(error6);
    }
    public void modPartido(String partido) {
        if (valido(partido)) {
            Partido = partido;
        }
        else throw new IllegalArgumentException(error7);
    }
    public void mod(String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        //this.modDni();
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
    private static boolean valido(String str) {

        return (str != null && str.length() > 0);
    }
    //validar si el dni es válido
    //TODO publica????
    public static boolean DniValido(String aux) {
        if (aux == null) return false;
        if (aux.length() != 9) return false;
        for (int i = 0; i < aux.length(); ++i) {
            //print(aux.charAt(i)+"");
            if (i == 8) {
                return Character.isLetter(aux.charAt(i));//puedo utilizarlo?
            }
            else {
                if (!Character.isDigit((aux.charAt(i)))) return false;
            }
            //print(String.valueOf(i) +aux.charAt(i));
        }
        return true;

    }
    //valida si edad es valida
    // se considera valida edad > 0
    private static boolean EdadValida(int n) {
        return n > 0;
    }
    private static void print(String S) {
        System.out.println(S);
    }
}
