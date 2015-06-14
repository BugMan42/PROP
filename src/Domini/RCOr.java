package Domini;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class RCOr extends RelacionCompuesta1 {

    private RelacionCompuesta1 hi,hd;

    public RCOr(RelacionCompuesta1 i, RelacionCompuesta1 d) throws Exception {
        hi = i;
        hd = d;
    }

    public boolean esOr(){
        return true;
    }

    public ArrayList<Congresista> obtConjunto(){
        ArrayList<Congresista> res = new ArrayList<Congresista>(hi.obtConjunto());
        res.addAll(hd.obtConjunto());
        // Quitar congresistas repetidos
        Collections.sort(res,Congresista.DNI);
        Iterator<Congresista> it = res.iterator();
        Congresista c = null;
        if(it.hasNext()) c = it.next();
        while(it.hasNext()) {
            Congresista c2 = it.next();
            if (c.equals(c2)) it.remove();
            else c = c2;
        }
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
        return "( "+hi.descripcion()+" or "+hd.descripcion()+" )";
    }

    public String toString() {
        return obtId()+" - "+"[ "+hi.obtId()+" or "+hd.obtId()+" ]";
    }

}
