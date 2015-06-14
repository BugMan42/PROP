package Domini;


import java.util.ArrayList;

public class RCConjuntoConVoto extends RCConjunto {
    private Voto v;

    public RCConjuntoConVoto(ArrayList<Congresista> lc, Votacion e, Voto v1) throws Exception {
        super(lc, e);
        v = v1;
    }

    public Voto obtVoto() {
        return v;
    }

    public boolean tieneVoto() {
        return true;
    }

    public String descripcion(){
        return obtId()+"";
    }

    public String toString() {
        ArrayList<Congresista> lcon = obtCongresistas();
        Evento ev = obtEvento();
        return obtId()+" "+ev.obt_nombre()+" "+ev.obt_fecha()+" "+v;
    }
}
