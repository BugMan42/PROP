package Domini;

/**
 * Created by bug on 20/03/15.
 * Relacion simple TODO hacer relacion simple y compuesta (or  not)
 */
public class Relacion {
    //atributos no pueden ser null
    private Congresista C;
    private Evento E;
    //TODO: creadora no puede petar devolver error
    Relacion(Congresista C1, Evento E1) {
        if (!CongresistaValido(C1)) throw new IllegalArgumentException("Congresista null");
        if (!EventoValido(E1)) throw new IllegalArgumentException("Evento null");
        C = C1;
        E = E1;
    }
    //Modificadoras
    //TODO: igual que en creadora
    public void mod_relacion(Congresista C1, Evento Ev) {
        if (!CongresistaValido(C1)) throw new IllegalArgumentException("Congresista null");
        if (!EventoValido(Ev)) throw new IllegalArgumentException("Evento null");
        C = C1;
        E = Ev;
    }
    public void mod_relacion(Congresista C1) {
        if (!CongresistaValido(C1)) throw new IllegalArgumentException("Congresista null");
        C = C1;
    }
    public void mod_relacion(Evento Ev) {
        if (!EventoValido(E)) throw new IllegalArgumentException("Evento null");
        E = Ev;
    }
    //Consultoras
    //TODO: siempre que no sea null ----> de momento no no puede ser null
    public Congresista obt_congresista() {
        return C;
    }
    public Evento obt_Evento() {
        return E;
    }
    public String toString() {
        return C.obtDni()+" "+E.obt_nombre()+" "+E.obt_fecha();
    }
    private static boolean CongresistaValido(Congresista C) {
        return (C != null);//TODO hacer funciona congresista valido en Congresista
    }
    private static boolean EventoValido(Evento E) {
        return (E != null);//TODO hacer funcion valido en evento
    }
}
