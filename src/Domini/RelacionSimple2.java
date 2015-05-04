package Domini;

/**
 * Created by bug on 4/05/15.
 */
public class RelacionSimple2 extends Relacion{
    Congresista C;
    Evento E;
    boolean voto;
    public RelacionSimple2(boolean simple, Congresista a, Evento e) {
        super(true);
        C = a;
        E = e;
        voto = simple;
    }
    public boolean tieneVoto() {
        return voto;
    }
    public Congresista obtCongresista() {
        return C;
    }
    public Evento obtEvento() {
        return E;
    }
    public String toString() {
        return C.obtDni()+" "+E.ID();
    }
}
