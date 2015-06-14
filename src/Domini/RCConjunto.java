package Domini;

import java.util.ArrayList;

/**
 * Created by jose on 13/06/15.
 */
public abstract class RCConjunto extends RelacionCompuesta {
    private static final String E1 = "El conjunto debe contener como mínimo un congresista.";

    private Evento ev;
    private ArrayList<Congresista> lcon;

    public RCConjunto(ArrayList<Congresista> lc, Evento e) throws Exception {
        super();
        if (lc.size()==0) throw new Exception(E1);
        lcon = lc;
        ev = e;
    }

    public ArrayList<Congresista> obtCongresistas() {
        return lcon;
    }

    public boolean esConjunto(){
        return true;
    }

    public ArrayList<Congresista> obtConjunto(){
        return lcon;
    }

    public Evento obtEvento() {
        return ev;
    }

    public abstract boolean tieneVoto();

    public abstract Voto obtVoto() throws Exception;

}
