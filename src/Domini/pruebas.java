package Domini;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by bug on 3/05/15.
 */
public class pruebas {

    /*public abstract class RelacionSimple1 extends Relacion {
        Congresista C;
        Evento E;
        boolean voto;
        public RelacionSimple1(boolean simple, Congresista a, Evento e) {
            super(true);
            C = a;
            E = e;
            voto = simple;
        }
        abstract Voto obtenerVoto() throws Exception;
        public Congresista obtCongresista() {
            return C;
        }
        public Evento obtEvento() {
            return E;
        }
        public String toString() {
            return C.obtDni()+" "+E.obt_nombre()+" "+E.obt_fecha();
        }
    }
    public class RelacionSimpleConVoto1 extends RelacionSimple1 {
        Voto V;
        public RelacionSimpleConVoto1(Congresista a, Evento e, Voto v) {
            super(true, a, e);
            V = v;
        }
        Voto obtenerVoto()throws Exception {
            return V;
        }
    }
    public class RelacionSimpleSinVoto1 extends RelacionSimple1 {
        public RelacionSimpleSinVoto1(Congresista a, Evento e) {
            super(false, a, e);
        }
        Voto obtenerVoto()throws Exception {
            throw new Exception("No hay voto");
        }
    }

    /*public class RelacionSimple2 extends Relacion {
        Congresista C;
        Evento E;
        boolean voto;
        public RelacionSimple2(boolean simple, Congresista a, Evento e) {
            super(true);
            C = a;
            E = e;
            voto = simple;
        }
        public Congresista obtCongresista() {
            return C;
        }
        public Evento obtEvento() {
            return E;
        }
        public String toString() {
            return C.obtDni()+" "+E.ID();
        }
    }
    /*
    public class RelacionSimpleConVoto2 extends RelacionSimple2 {
        Voto V;
        RelacionSimpleConVoto2(Congresista a, Evento e, Voto v) {
            super(true,a,e);
            V = v;
        }
    }*/

    public static void main(String[] args) throws Exception {
        Congresista C1 = new Congresista(new Dni("20901724z"), "a", "b", 12, "a", "b", "c");
        Congresista C2 = new Congresista(new Dni("20901724a"), "a", "b", 12, "a", "b", "c");
        Evento E1 = new Profesional("Golf","12/12/2012",10);
        Evento E2 = new Profesional("Golf2","12/12/2012",10);
        Evento E3 = new Votacion("votos","12/12/2012",11);
        Voto a = new Positivo();
        Voto b = new Negativo();
        ArrayList<RelacionSimple2> Array2 = new ArrayList<RelacionSimple2>();
        RelacionSimple2 R2_1 = new RelacionSimpleConVoto2(C1,E1,a);
        RelacionSimple2 R2_2 = new RelacionSimple2(false,C2,E2);
        Array2.add(R2_1);
        Array2.add(R2_2);
        print("0: " + Array2.get(0).tieneVoto());
        print("1: "+Array2.get(1).tieneVoto());
        print(Array2.get(0).toString());
        print(Array2.get(1).toString());
        print("Prueba R1");
        ArrayList<RelacionSimple1> Array1 = new ArrayList<RelacionSimple1>();
        RelacionSimple1 R1_1 = new RelacionSimpleConVoto1(C1,E1,a);
        RelacionSimple1 R1_2 = new RelacionSimpleSinVoto1(C1,E2);
        Array1.add(R1_1);
        Array1.add(R1_2);
        print("0: " + Array1.get(0).tieneVoto());
        print("1: " + Array1.get(1).tieneVoto());
        print(Array1.get(0).toString());
        print(Array1.get(1).toString());
        print("fin");
        if (Array1.get(0).tieneVoto()) print(Array1.get(0).obtenerVoto() + "");
        if (Array1.get(1).tieneVoto()) print(Array1.get(1).obtenerVoto()+"");
        if (Array2.get(0).tieneVoto()) {
            RelacionSimpleConVoto2 aux = (RelacionSimpleConVoto2) Array2.get(0);
            print(aux.obtVoto()+"");
        }

    }
    private static void print(String str) {
        System.out.println(str);
    }
}
