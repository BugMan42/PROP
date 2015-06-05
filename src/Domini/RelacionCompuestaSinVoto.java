package Domini;

import java.util.ArrayList;

public class RelacionCompuestaSinVoto extends RelacionCompuesta1 {

    public RelacionCompuestaSinVoto(ArrayList<Congresista> lc, Acto e) throws Exception {
        super(lc, e);
    }

    public RelacionCompuestaSinVoto(ArrayList<Congresista> lc, Reunion e) throws Exception {
        super(lc, e);
    }

    public void modVoto(Voto v1) throws Exception {
        throw new Exception("Relacion Compuesta Sin Voto No tiene voto");
    }

    public Voto obtVoto()throws Exception {
        throw new Exception("Relacion Compuesta Sin Voto No tiene voto");
    }

    public boolean tieneVoto() {
        return false;
    }

}
