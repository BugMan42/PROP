package Domini;

public class Congresista {

    private class EdadNoValida extends Exception {
        public EdadNoValida(int edad) {
            super("Edad "+edad+" no Valida");
        }
    }

    //Edad es un entero > 0
    private Dni Dni;
    private String Nombre;
    private String Apellido;
    private int Edad;
    private String Ciudad;
    private String Estado;
    private String Partido;

    //Creadoras
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
    public void modDni(Dni dni) {
        Dni = dni;
    }
    public void modNombre(String nombre) {
        Nombre = nombre;
    }
    public void modApellido(String apellido) {
        Apellido = apellido;
    }
    public void modEdad(int edad) throws Exception {
        if (!EdadValida(edad)) throw new EdadNoValida(edad);
        else Edad = edad;
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
    public String ID() {
        return Dni.toString();
    }
    public String toString() {
        return Dni+" "+Nombre+" "+Apellido+" "+Edad+" "+Ciudad+" "+Estado+" "+Partido;
    }

     //Valida la edad: Comprueba si
     //el parametro n es un entero > 0
    private static boolean EdadValida(int n) {
        return n > 0;
    }
}
