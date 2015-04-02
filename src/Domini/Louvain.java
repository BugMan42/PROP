package Domini;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jose on 02/04/15.
 */
public class Louvain extends Algoritmo {

    public Louvain(){

    }

    private boolean one_level(){
        boolean improvement = true;
        return improvement;
    }

    public void ejecutar_algoritmo(){
        Date begin, end;
        begin = new Date();
        display_time("Begin");
        boolean improvement = true;
        double mod, new_mod;
        int level=0;
    }

    private void display_time(String s){
        Date d = new Date();
        s += ": " + d.toString();
        System.out.println(s);
    }
}
