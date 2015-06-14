package Domini;

import java.util.ArrayList;

public class RCNot extends RelacionCompuesta {

    private RelacionCompuesta rc;
    private ControladorCongreso cc;

    public RCNot(RelacionCompuesta rel, ControladorCongreso c) throws Exception {
        rc = rel;
        cc = c;
    }

    public boolean esNot(){
        return true;
    }

    public ArrayList<Congresista> obtConjunto(){
        ArrayList<Congresista> res = new ArrayList<Congresista>(cc.obtenerCongreso());
        res.removeAll(rc.obtConjunto());
        return res;
    }

    public RelacionCompuesta obtHijo(){
        return rc;
    }

    public boolean contieneConjunto(int id) throws Exception {
        boolean rh;
        if (rc.esConjunto()) rh = rc.obtId()==id;
        else rh = rc.contieneConjunto(id);
        return rh;
    }

    public String descripcion(){
        return "( not "+rc.descripcion()+" )";
    }

    public String toString() {
        return obtId()+" - "+"[ not "+rc.obtId()+" ]";
    }

}
