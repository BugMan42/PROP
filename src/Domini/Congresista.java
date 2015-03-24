package Domini;

/**
 * Created by bug on 20/03/15.
 */
public class Congresista {
    //Atributos para congresistas
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String ciudad;
    private String estado;
    private String partido;

    public Congresista() { }
    public Congresista(String S) {
        String[] aux = S.split("\\s");
        this.dni = aux[0];
        this.nombre = aux[1];
        this.apellido = aux[2];
        this.edad = Integer.valueOf(aux[3]);
        this.ciudad = aux[4];
        this.estado = aux[5];
        this.partido = aux[6];
    }

    public Congresista (String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido ) {
        //dni no modificable
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.estado = estado;
        this.partido = partido;
    }

    public String obt_dni() {
        return this.dni;
    }
    public String obt_nombre() {
        return this.nombre;
    }
    public String obt_apellido() {
        return this.apellido;
    }
    public int obt_edad() {
        return this.edad;
    }
    public String obt_ciudad() {
        return this.ciudad;
    }
    public String obt_estado() {
        return this.estado;
    }
    public String obt_partido() {
        return this .partido;
    }
    public String toString() {
        return dni+nombre+apellido+Integer.toString(edad)+ciudad+estado+partido;
    }

    public void mod_dni(String dni) {
        this.dni = dni;
    }
    public void mod_nombre(String nombre) {
        this.nombre = nombre;
    }
    public void mod_apellido(String apellido) {
        this.apellido = apellido;
    }
    public void mod_edad(int edad) {
        this.edad = edad;
    }
    public void mod_ciudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public void mod_estado(String estado) {
        this.estado = estado;
    }
    public void mod_partido(String partido) {
        this.partido = partido;
    }

    //destructora ---> eliminar?
}
