package Domini;

import java.util.ArrayList;

public abstract class RelacionCompuesta1 extends Relacion {
    private static final String E1 = "La relación debe contener como mínimo un congresista.";

    private Evento ev;
    private ArrayList<Congresista> lcon;

    public RelacionCompuesta1(ArrayList<Congresista> lc, Evento e) throws Exception {
        super();
        if (lc.size()==0) throw new Exception(E1);
        lcon = lc;
        ev = e;
    }

    public void modRelacion(ArrayList<Congresista> lc, Evento e) throws Exception {
        if (lc.size()==0) throw new Exception(E1);
        lcon = lc;
        ev = e;
    }

    public void modRelacion(ArrayList<Congresista> lc) throws Exception {
        if (lc.size()==0) throw new Exception(E1);
        lcon = lc;
    }

    public void modRelacion(Evento e) {
        ev = e;
    }

    public abstract void modVoto(Voto v) throws Exception;

    public ArrayList<Congresista> obtCongresistas() {
        return lcon;
    }

    public Evento obtEvento() {
        return ev;
    }

    public abstract boolean tieneVoto();

    public abstract Voto obtVoto() throws Exception;

    public boolean esSimple() {
        return false;
    }

    public String toString() {
        String res = ev.obt_nombre()+" "+ev.obt_fecha()+"\n";
        for (Congresista c : lcon) res += c.obtDni()+"\n";
        return res;
    }

}
