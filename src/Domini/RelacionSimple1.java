package Domini;

/**
 * Created by bug on 4/05/15.
 */
public abstract class RelacionSimple1 extends Relacion {
    Congresista C;
    Evento E;
    boolean voto;
    public RelacionSimple1(boolean simple, Congresista a, Evento e) {
        super(true);
        C = a;
        E = e;
        voto = simple;
    }
    public boolean tieneVoto() {
        return voto;
    }
    abstract Voto obtenerVoto() throws Exception;
    public Congresista obtCongresista() {
        return C;
    }
    public Evento obtEvento() {
        return E;
    }
    public String toString() {
        return C.obtDni()+" "+E.obt_nombre()+" "+E.obt_fecha();
    }
}
