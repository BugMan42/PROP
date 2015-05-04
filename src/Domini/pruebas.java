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
            Evento E1 = new Profesional("Golf", "12/12/2012", 10);
            Evento E2 = new Profesional("votos", "12/12/2012", 10);
            Evento E3 = new Votacion("votos", "12/12/2012", 11);
            Voto a = new Positivo();
            Voto b = new Negativo();
            ArrayList<RelacionSimple> Array1 = new ArrayList<RelacionSimple>();
            RelacionSimple R1 = new RelacionSimpleConVoto(C1, E1, a);
            RelacionSimple R2 = new RelacionSimpleSinVoto(C1, E2);
            RelacionCompuestaAnd R = new RelacionCompuestaAnd(R1, R2);
            Relaciones RR = new Relaciones();
            RR.agregarRelacion(R1);
            RR.agregarRelacion(R1);
            print("Todas" + RR.obtRelaciones(C1));

        }
        catch (Exception a) {
            print(a.getMessage());
        }



    }
    private static void print(String str) {
        System.out.println(str);
    }
}
