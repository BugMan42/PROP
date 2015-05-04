package Domini;

/**
 * Created by bug on 23/03/15.
 */
public class DriverCjtRelacion {
    public static void main(String[] args) throws Exception {
        Congresista C1 = new Congresista(new Dni("20901724z"), "a", "b", 12, "a", "b", "c");
        Congresista C2 = new Congresista(new Dni("20901724a"), "a", "b", 12, "a", "b", "c");
        Evento E1 = new Profesional("Golf","12/12/2012",10);
        Evento E2 = new Profesional("Golf2","12/12/2012",10);
        Relaciones R = new Relaciones();
        RelacionSimple1 R1 = new RelacionSimpleSinVoto1(C1,E1);
        RelacionSimple1 R2 = new RelacionSimpleSinVoto1(C2,E2);
        R.agregarRelacion(R1);
        R.agregarRelacion(R2);
        R.eliminarRelacion(R1);
        print(R.obtTodasLasRelaciones()+"");

    }
    private static void print(String str) {
        System.out.println(str);
    }
}
