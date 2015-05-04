package Domini;

import java.util.ArrayList;

public class pruebas {



    public static void main(String[] args) throws Exception {
        Congresista C1 = new Congresista(new Dni("00000000A"),"Edsger","Dijkstra",72,"Rotterdam","NT","Pirata");
        Congresista C2 = new Congresista(new Dni("00000000B"),"Gordon","Moore",86,"San Francisco","CA","Berkeley");
        Congresista C3 = new Congresista(new Dni("00000000C"),"Richard","Hamming",82,"Monterey","CA","Pirata");
        Congresista C4 = new Congresista(new Dni("00000000D"),"Max","Newman",87,"Chealsea","LO","Democrata");
        Congresista C5 = new Congresista(new Dni("00000000E"),"Allen","Newll",65,"San Francisco","SF","Pirata");
        Congresista C6 = new Congresista(new Dni("00000000F"),"Jon-Von","Neumann",53,"Budapest","AU","Pirata");
        Congresista C7 = new Congresista(new Dni("00000000G"),"Alan","Turing",41,"London","LO","Liberal");

        //Congresista C5 = new Congresista(new Dni("20901724z"), "a", "b", 12, "a", "b", "c");
        //Congresista C2 = new Congresista(new Dni("20901724a"), "a", "b", 12, "a", "b", "c");
        Evento E1 = new Profesional("Golf","12/12/2012",10);
        Evento E2 = new Profesional("Golf2","12/12/2012",10);
        Evento E3 = new Votacion("votos","12/12/2012",11);
        Voto a = new Positivo();
        Voto b = new Negativo();
        print("Prueba R1");
        ArrayList<RelacionSimple> Array1 = new ArrayList<RelacionSimple>();
        RelacionSimple R1_1 = new RelacionSimpleConVoto(C1,E1,a);
        RelacionSimple R1_2 = new RelacionSimpleSinVoto(C1,E2);
        Array1.add(R1_1);
        Array1.add(R1_2);
        print("0: " + Array1.get(0).tieneVoto());
        print("1: " + Array1.get(1).tieneVoto());
        print(Array1.get(0).toString());
        print(Array1.get(1).toString());
        print("fin");
        if (Array1.get(0).tieneVoto()) print(Array1.get(0).obtVoto() + "");
        if (Array1.get(1).tieneVoto()) print(Array1.get(1).obtVoto()+"");

    }
    private static void print(String str) {
        System.out.println(str);
    }
}
