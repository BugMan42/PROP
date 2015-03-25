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
        dni = aux[0];
        nombre = aux[1];
        apellido = aux[2];
        edad = Integer.valueOf(aux[3]);
        ciudad = aux[4];
        estado = aux[5];
        partido = aux[6];
    }

    public Congresista (String d, String n, String a, int e, String c, String es, String p) {
        //dni no modificable
        dni = d;
        nombre = n;
        apellido = a;
        edad = e;
        ciudad = c;
        estado = es;
        partido = p;
    }

    public String obt_dni() {
        return dni;
    }
    public String obt_nombre() {
        return nombre;
    }
    public String obt_apellido() {
        return apellido;
    }
    public int obt_edad() {
        return edad;
    }
    public String obt_ciudad() {
        return ciudad;
    }
    public String obt_estado() {
        return estado;
    }
    public String obt_partido() {
        return partido;
    }
    public String toString() {
        return dni+nombre+apellido+Integer.toString(edad)+ciudad+estado+partido;
    }

    public void mod_dni(String dninuevo) {
        dni = dninuevo;
    }
    public void mod_nombre(String nombrenuevo) {
        nombre = nombrenuevo;
    }
    public void mod_apellido(String apellidonuevo) {
        apellido = apellidonuevo;
    }
    public void mod_edad(int edadnueva) {
        edad = edadnueva;
    }
    public void mod_ciudad(String ciudadnuevo) {
        ciudad = ciudadnuevo;
    }
    public void mod_estado(String estadonuevo) {
        estado = estadonuevo;
    }
    public void mod_partido(String partidonuevo) {
        partido = partidonuevo;
    }

    //destructora ---> eliminar?
}
