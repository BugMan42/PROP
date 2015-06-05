package Domini;


import java.util.ArrayList;

public class RelacionCompuestaConVoto extends RelacionCompuesta1 {
    private Voto v;

    public RelacionCompuestaConVoto(ArrayList<Congresista> lc, Votacion e, Voto v1) throws Exception {
        super(lc, e);
        v = v1;
    }

    public void modVoto(Voto v1) {
        v = v1;
    }

    public Voto obtVoto() {
        return v;
    }

    public boolean tieneVoto() {
        return true;
    }

    public String toString() {
        ArrayList<Congresista> lcon = obtCongresistas();
        Evento ev = obtEvento();
        String res = ev.obt_nombre()+" "+ev.obt_fecha()+"\n"+v.toString()+"\n";
        for (Congresista c : lcon) res += c.obtDni()+"\n";
        return res;
    }
}
