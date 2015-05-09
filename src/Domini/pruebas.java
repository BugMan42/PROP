package Domini;

import java.util.ArrayList;

public class pruebas {



    public static void main(String[] args) throws Exception {
        try {
            Congresista C1 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
            Congresista C2 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
            Congresista C3 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
            Congresista C4 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
            Congresista C5 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");
            Congresista C6 = new Congresista(new Dni("00000000F"), "Jon-Von", "Neumann", 53, "Budapest", "AU", "Pirata");
            Congresista C7 = new Congresista(new Dni("00000000G"), "Alan", "Turing", 41, "London", "LO", "Liberal");
            Evento E1 = new Profesional("A", "12/12/2012", 10);
            Evento E2 = new Profesional("B", "12/12/2012", 10);
            Evento E3 = new Votacion("C", "12/12/2012", 11);
            Evento E4 = new Profesional("D", "12/12/2012", 10);
            Evento E5 = new Profesional("E", "12/12/2012", 10);
            Evento E6 = new Profesional("F", "12/12/2012", 10);
            Voto a = new Positivo();
            Voto b = new Negativo();
            ArrayList<RelacionSimple> Array1 = new ArrayList<RelacionSimple>();
            //RelacionSimple R1 = new RelacionSimpleConVoto(C1, E3, a);
            RelacionSimple R2 = new RelacionSimpleSinVoto(C1, E1);
            RelacionSimple R3 = new RelacionSimpleSinVoto(C1, E2);
            RelacionSimple R4 = new RelacionSimpleSinVoto(C1, E3);
            RelacionSimple R5 = new RelacionSimpleSinVoto(C3, E1);
            RelacionSimple R6 = new RelacionSimpleSinVoto(C4, E1);
            RelacionSimple R7 = new RelacionSimpleSinVoto(C5, E1);
            RelacionSimple R8 = new RelacionSimpleSinVoto(C2, E1);
            RelacionSimple R9 = new RelacionSimpleSinVoto(C2, E2);
            RelacionSimple R10 = new RelacionSimpleSinVoto(C2, E3);
            RelacionSimple R11 = new RelacionSimpleSinVoto(C2, E4);
            RelacionSimple R12 = new RelacionSimpleSinVoto(C2, E5);
            //RelacionCompuestaAnd R = new RelacionCompuestaAnd(R1, R2);
            Relaciones RR = new Relaciones();
            RR.agregarRelacion(R2);
            RR.agregarRelacion(R3);
            RR.agregarRelacion(R4);
            RR.agregarRelacion(R5);
            RR.agregarRelacion(R6);
            RR.agregarRelacion(R7);
            RR.agregarRelacion(R8);
            RR.agregarRelacion(R9);
            RR.agregarRelacion(R10);
            RR.agregarRelacion(R11);
            RR.agregarRelacion(R12);
            //RR.agregarRelacion(R1);
            /*print("Todas" + RR.obtRelaciones(C1));
            print("Todas" + RR.obtRelaciones(C2));
            print("Todas" + RR.obtRelaciones(E1));
            print("Todas" + RR.obtRelaciones(E2));
            print("Todas" + RR.obtRelaciones(E3));*/
            Congreso aa = new Congreso();
            aa.agregarCongresistaRandom();
            print(aa.obtenerCongreso()+"");
            aa.agregarCongresistaRandom();
            print(aa.obtenerCongreso()+"");
            aa.agregarCongresistaRandom();
            print(aa.obtenerCongreso()+"");
            aa.agregarCongresistaRandom();
            print(aa.obtenerCongreso()+"");
            aa.agregarCongresistaRandom();
            print(aa.obtenerCongreso()+"");

        }
        catch (Exception a) {
            print(a.getMessage());
        }



    }
    private static void print(String str) {
        System.out.println(str);
    }
}
