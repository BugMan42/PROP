package Domini;

/**
 * Created by bug on 8/04/15.
 */
public class ControladorCongreso {

    private Congreso c;

    public ControladorCongreso(){
        c = new Congreso();
    }

    public void add_congresista(String s){
        String[] aux = s.split("\\s");
        //for (int i=0; i<7; ++i) System.out.println(aux[i]);
        //Congresista con = new Congresista(aux[0],aux[1],aux[2],Integer.valueOf(aux[3]),aux[4],aux[5],aux[6]);
        /*if (c.contieneCongresista(con)) System.out.println("ya esta en el congreso");
        else System.out.println("no esta en el congreso");
        if (c.contieneCongresista(aux[0])) System.out.println("ya esta en el congreso");
        else System.out.println("no esta en el congreso");*/
        //c.addCongresista(con);
        //System.out.println(String.valueOf(c.obtener_lista().size()));
    }
}
