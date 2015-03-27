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
        else throw new IllegalArgumentException("0"); //String pasada es null("")
    }
    public Congresista (String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        //dni no modificable
        if (!valido(dni)) throw new IllegalArgumentException("DNI NO VALIDO");
        Dni = dni;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Ciudad = ciudad;
        Estado = estado;
        Partido = partido;
    }
    private boolean validar (String[] aux) {
        //if (aux.equals(null)) throw new IllegalArgumentException(Integer.toString(0));
        if (aux.length == 0) throw new IllegalArgumentException(Integer.toString(0));
        for (int i = 0; i < aux.length; ++i) {
            if (!valido(aux[i]))  throw new IllegalArgumentException(Integer.toString(i+1));
        }
        return true;
    };
    private boolean valido(String string) {
        return (!string.equals(""));
    }
    //Modificadoras
    public void mod_dni(String dni) {
        Dni = dni;
    }

    public void mod_nombre(String nombre) {
        Nombre = nombre;
    }
    public void mod_apellido(String apellido) {
        Apellido = apellido;
    }
    public void mod_edad(int edad) {
        Edad = edad;
    }
    public void mod_ciudad(String ciudad) {
        Ciudad = ciudad;
    }
    public void mod_estado(String estado) {
        Estado = estado;
    }
    public void mod_partido(String partido) {
        Partido = partido;
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

    private static void print(String S) {
        System.out.println(S);
    }
}
