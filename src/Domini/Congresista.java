package Domini;

import java.util.Comparator;

public class Congresista implements Node {

    public class EdadNoValida extends Exception {
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
        Nombre = nombre.toUpperCase();
        Apellido = apellido.toUpperCase();
        Edad = edad;
        Ciudad = ciudad.toUpperCase();
        Estado = estado.toUpperCase();
        Partido = partido.toUpperCase();
    }
    public Congresista(Congresista c) {
        Dni = new Dni(c.Dni);
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
        Nombre = nombre.toUpperCase();
    }
    public void modApellido(String apellido) {
        Apellido = apellido.toUpperCase();
    }
    public void modEdad(int edad) throws Exception {
        if (!EdadValida(edad)) throw new EdadNoValida(edad);
        else Edad = edad;
    }
    public void modCiudad(String ciudad) {
        Ciudad = ciudad.toUpperCase();
    }
    public void modEstado(String estado) {
        Estado = estado.toUpperCase();
    }
    public void modPartido(String partido) {
        Partido = partido.toUpperCase();
    }
    public void mod(Congresista a) {
        Dni = a.Dni;
        Nombre = a.Nombre;
        Apellido = a.Apellido;
        Edad = a.Edad;
        Ciudad = a.Ciudad;
        Estado = a.Estado;
        Partido = a.Partido;
    }
    public void mod(Dni dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        if (!EdadValida(edad)) throw new EdadNoValida(edad);
        Dni = dni;
        Nombre = nombre.toUpperCase();
        Apellido = apellido.toUpperCase();
        Edad = edad;
        Ciudad = ciudad.toUpperCase();
        Estado = estado.toUpperCase();
        Partido = partido.toUpperCase();
    }
    public void mod(String nombre, String apellido, int edad, String ciudad, String estado, String partido) throws Exception {
        if (!EdadValida(edad)) throw new EdadNoValida(edad);
        Nombre = nombre.toUpperCase();
        Apellido = apellido.toUpperCase();
        Edad = edad;
        Ciudad = ciudad.toUpperCase();
        Estado = estado.toUpperCase();
        Partido = partido.toUpperCase();
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

    //funciones para tst ID
    public String obtID() {
        return Dni.toString();
    }
    public String obtIDName() {
        return Nombre+"-"+Dni.toString();
    }
    public String obtIDSurname() {
        return Apellido+"-"+Dni.toString();
    }
    public String obtIDAge() {
        return String.valueOf(Edad)+"-"+Dni.toString();
    }
    public String obtIDCity() {
        return Ciudad+"-"+Dni.toString();
    }
    public String obtIDState() {
        return Estado+"-"+Dni.toString();
    }
    public String obtIDParty() {
        return Partido+"-"+Dni.toString();
    }

    public String toString() {
        return Dni+" "+Nombre+" "+Apellido+" "+Edad+" "+Ciudad+" "+Estado+" "+Partido;
    }
    public String toStringByName() {
        return Nombre+" "+Apellido+" "+Dni+" "+Edad+" "+Ciudad+" "+Estado+" "+Partido;
    }
    public String toStringBySurname() {
        return Apellido+" "+Nombre+" "+Dni+" "+Edad+" "+Ciudad+" "+Estado+" "+Partido;
    }
    public String toStringByAge() {
        return Edad+" "+Dni+" "+Nombre+" "+Apellido+" "+Ciudad+" "+Estado+" "+Partido;
    }
    public String toStringByCity() {
        return Ciudad+" "+Dni+" "+Nombre+" "+Apellido+" "+Edad+" "+Estado+" "+Partido;
    }
    public String toStringByState() {
        return Estado+" "+Dni+" "+Nombre+" "+Apellido+" "+Edad+" "+Ciudad+" "+Partido;
    }
    public String toStringByParty() {
        return Partido+" "+Dni+" "+Nombre+" "+Apellido+" "+Edad+" "+Ciudad+" "+Estado;
    }

    //Valida la edad: Comprueba si
    //el parametro n es un entero > 0
    private static boolean EdadValida(int n) {
        return n > 0;
    }
    public boolean equals(Congresista a) {
        return a.Dni.toString().equals(Dni.toString());
    }
    public Congresista clone() {
        return new Congresista(this);
    }





    /** COMPARATOR */
    public static Comparator<Congresista> DNI = new Comparator<Congresista>() {
        @Override
        public int compare(Congresista o1, Congresista o2) {return o1.Dni.toString().compareTo(o2.Dni.toString());
        }
    };
    public static Comparator<Congresista> NAME = new Comparator<Congresista>() {
        @Override
        public int compare(Congresista o1, Congresista o2) {
            return o1.Nombre.compareTo(o2.Nombre);
        }
    };
    public static Comparator<Congresista> SURNAME = new Comparator<Congresista>() {
        @Override
        public int compare(Congresista o1, Congresista o2) {return o1.Apellido.compareTo(o2.Apellido);
        }
    };
    public static Comparator<Congresista> AGE = new Comparator<Congresista>() {
        @Override
        public int compare(Congresista o1, Congresista o2) {return Integer.valueOf(o1.Edad).compareTo(o2.Edad);}
    };
    public static Comparator<Congresista> CITY = new Comparator<Congresista>() {
        @Override
        public int compare(Congresista o1, Congresista o2) {
            return o1.Ciudad.compareTo(o2.Ciudad);
        }
    };
    public static Comparator<Congresista> STATE = new Comparator<Congresista>() {
        @Override
        public int compare(Congresista o1, Congresista o2) {
            return o1.Estado.compareTo(o2.Estado);
        }
    };
    public static Comparator<Congresista> PARTY = new Comparator<Congresista>() {
        @Override
        public int compare(Congresista o1, Congresista o2) {
            return o1.Partido.compareTo(o2.Partido);
        }
    };
}
