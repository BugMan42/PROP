package Domini;

import java.util.ArrayList;

public class RCAnd extends RelacionCompuesta1 {

    private RelacionCompuesta1 hi,hd;

    public RCAnd(RelacionCompuesta1 i, RelacionCompuesta1 d) throws Exception {
        hi = i;
        hd = d;
    }

    public boolean esAnd(){
        return true;
    }

    public ArrayList<Congresista> obtConjunto(){
        ArrayList<Congresista> res = new ArrayList<Congresista>(hi.obtConjunto());
        res.retainAll(hd.obtConjunto());
        return res;
    }

    public RelacionCompuesta1 obtHijoIzq(){
        return hi;
    }

    public RelacionCompuesta1 obtHijoDer(){
        return hd;
    }

    public boolean contieneConjunto(int id) throws Exception {
        boolean rhi, rhd;
        if (hi.esConjunto()) rhi = hi.obtId()==id;
        else rhi = hi.contieneConjunto(id);
        if (hd.esConjunto()) rhd = hd.obtId()==id;
        else rhd = hd.contieneConjunto(id);
        return rhi || rhd;
    }

    public String descripcion(){
        return "( "+hi.descripcion()+" and "+hd.descripcion()+" )";
    }

    public String toString() {
        return obtId()+" - "+"[ "+hi.obtId()+" and "+hd.obtId()+" ]";
    }

}
