package Domini;

/**
 * Created by bug on 20/03/15.
 */
public class Congresista {
    //Atributos para congresistas
    private String Dni;
    private String Nombre;
    private String Apellido;
    private int Edad;
    private String Ciudad;
    private String Estado;
    private String Partido;

    //Creadoras
    public Congresista() { }
    public Congresista(String S) {
        if (!S.equals("")) {
            String[] aux = S.split("\\s");
            if (validar(aux)) {
                Dni = aux[0];
                Nombre = aux[1];
                Apellido = aux[2];
                Edad = Integer.valueOf(aux[3]);
                Ciudad = aux[4];
                Estado = aux[5];
                Partido = aux[6];
            }
        }
        else throw new IllegalArgumentException("8"); //String pasada es null("")
    }
    public Congresista (String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        if (!valido_dni(dni)) throw new IllegalArgumentException("0");
        if (!valido(nombre)) throw new IllegalArgumentException("1");
        if (!valido(apellido)) throw new IllegalArgumentException("2");
        if (!valido_num(String.valueOf(edad))) throw new IllegalArgumentException("3");
        if (!valido(ciudad)) throw new IllegalArgumentException("4");
        if (!valido(estado)) throw new IllegalArgumentException("5");
        if (!valido(partido)) throw new IllegalArgumentException("6");
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
        if (valido_dni(dni)) {
            Dni = dni;
        }
        else throw new IllegalArgumentException("0");
    }
    public void mod_nombre(String nombre) {
        if (valido(nombre)) {
            Nombre = nombre;
        }
        else throw new IllegalArgumentException("1");
    }
    public void mod_apellido(String apellido) {
        if (valido(apellido)) {
            Apellido = apellido;
        }
        else throw new IllegalArgumentException("2");
    }
    public void mod_edad(int edad) {
        if (valido_num(String.valueOf(edad))) {
            Edad = edad;
        }
        else throw new IllegalArgumentException("3");
    }
    public void mod_ciudad(String ciudad) {
        if (valido(ciudad)) {
            Ciudad = ciudad;
        }
        else throw new IllegalArgumentException("4");
    }
    public void mod_estado(String estado) {
        if (valido(estado)) {
            Estado = estado;
        }
        else throw new IllegalArgumentException("5");
    }
    public void mod_partido(String partido) {
        if (valido(partido)) {
            Partido = partido;
        }
        else throw new IllegalArgumentException("6");
    }

    //Consultoras
    public String obt_dni() {
        return Dni;
    }
    public String obt_nombre() {
        return Nombre;
    }
    public String obt_apellido() {
        return Apellido;
    }
    public int obt_edad() {
        return Edad;
    }
    public String obt_ciudad() {
        return Ciudad;
    }
    public String obt_estado() {
        return Estado;
    }
    public String obt_partido() {
        return Partido;
    }
    public String toString() {
        return Dni+" "+Nombre+" "+Apellido+" "+Integer.toString(Edad)+" "+Ciudad+" "+Estado+" "+Partido;
    }

    //destructora ---> eliminar?
    public void clean() {

    }

    /** TODO: dni tiene que ser de un formato especifico?**/
    //Funciones estáticas de la clase
    private static boolean validar (String[] aux) {
        //if (aux.equals(null)) throw new IllegalArgumentException(Integer.toString(0));
        if (aux.length == 0) throw new IllegalArgumentException(Integer.toString(7));
        if (aux.length != 7) throw new IllegalArgumentException(Integer.toString(8));
        for (int i = 0; i < aux.length; ++i) {
            if (i == 0) {
                if (!valido_dni(aux[i]))  throw new IllegalArgumentException(Integer.toString(0));
            }
            else if (i == 3) {
                if (!valido_num(aux[i])) throw new IllegalArgumentException(Integer.toString(3));
            }
            else {
                if (!valido(aux[i]))  throw new IllegalArgumentException(Integer.toString(i));
            }
        }
        return true;
    }
    //validar si contiene solo letras, no caracteres especiales
    private static boolean valido(String string) {
        for (char c : string.toCharArray()) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    //validar si contiene solo letras y numeros
    //dni es valido si contiene letras y/o numeros
    private static boolean valido_dni(String string) {
        for (char c : string.toCharArray()) {
            if(!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }
    //valida si es numero
    private static boolean valido_num(String string) {
        for (char c : string.toCharArray()) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
