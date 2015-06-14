package Domini;

import java.util.ArrayList;

public class RCConjuntoSinVoto extends RCConjunto {

    public RCConjuntoSinVoto(ArrayList<Congresista> lc, Acto e) throws Exception {
        super(lc, e);
    }

    public RCConjuntoSinVoto(ArrayList<Congresista> lc, Reunion e) throws Exception {
        super(lc, e);
    }

    public Voto obtVoto()throws Exception {
        throw new Exception("RCConjuntoSinVoto no tiene voto");
    }

    public boolean tieneVoto() {
        return false;
    }

    public String descripcion(){
        return obtId()+"";
    }

    public String toString() {
        ArrayList<Congresista> lcon = obtCongresistas();
        Evento ev = obtEvento();
        return obtId()+" "+ev.obt_nombre()+" "+ev.obt_fecha();
    }

}
