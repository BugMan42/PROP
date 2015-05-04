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

    //Modificadoras
    public void modRelacion(Congresista C1, Evento Ev) {
        C = C1;
        E = Ev;
    }
    public void modRelacion(Congresista C1) {
        C = C1;
    }
    public void modRelacion(Evento Ev) {
        E = Ev;
    }
    public abstract void modVoto(Voto v) throws Exception;

    //Consultoras
    public Congresista obtCongresista() {
        return C;
    }
    public Evento obtEvento() {
        return E;
    }
    public abstract Voto obtVoto() throws Exception;

    public String toString() {
        return C.obtDni()+" "+E.obt_nombre()+" "+E.obt_fecha();
    }
}
