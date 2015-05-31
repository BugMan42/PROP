package Domini;

import Presentacio.CPCongreso;

import java.util.ArrayList;
import java.util.Iterator;

public class pruebas {



    public static void main(String[] args) throws Exception {

        try {
            //testcc();
            testCP();
            /*
            TST<Integer> tst = new TST<Integer>();
            tst.insertar("a",1);
            tst.insertar("b",2);
            tst.insertar("c",3);
            tst.insertar("d",4);
            tst.insertar("e", 5);
            print(tst.consultarClaves().toString());
            print(tst.consultarObjetos().toString());*/







            /*Congresista C1 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
            Congresista C2 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
            Congresista C3 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
            Congresista C4 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
            Congresista C5 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");
            Congresista C6 = new Congresista(new Dni("00000000F"), "Jon-Von", "Neumann", 53, "Budapest", "AU", "Pirata");
            Congresista C7 = new Congresista(new Dni("00000000G"), "Alan", "Turing", 41, "London", "LO", "Liberal");

            Congreso c = new Congreso();
            //c.agregarCongresista(C1);
            //c.modCongresista(new Dni("00000000a"), new Dni("00000000a"), "e", "d", 12, "c", "b", "a");
            ControladorCongreso d = new ControladorCongreso();
            d.agregarCongresista( "00000000a", "a", "a", 12, "a", "a", "a");
            print(d.obtenerCongreso().toString());
            Reunion E1 = new Profesional("A", new Fecha("12/12/2012"), 10);
            Reunion E2 = new Profesional("B", new Fecha("12/12/2012"), 10);
            Votacion E3 = new Votacion("C", new Fecha("12/12/2012"), 11);
            Reunion E4 = new Profesional("D", new Fecha("12/12/2012"), 10);
            Reunion E5 = new Profesional("E", new Fecha("12/12/2012"), 10);
            Reunion E6 = new Profesional("F", new Fecha("12/12/2012"), 10);
            Voto a = new Positivo();
            Voto b = new Negativo();
            //Congreso c = new Congreso();
           // print(c.size()+"");
           // c.agregarCongresistaRandom();
           // print(c.obtenerCongreso().toString());
            ArrayList<RelacionSimple> Array1 = new ArrayList<RelacionSimple>();
            //RelacionSimple R1 = new RelacionSimpleConVoto(C1, E3, a);
            RelacionSimple R2 = new RelacionSimpleSinVoto(C1, E1);
            RelacionSimple R3 = new RelacionSimpleSinVoto(C1, E2);
            //RelacionSimple R4 = new RelacionSimpleSinVoto(C1, E3);
            RelacionSimple R5 = new RelacionSimpleSinVoto(C3, E1);
            RelacionSimple R6 = new RelacionSimpleSinVoto(C4, E1);
            RelacionSimple R7 = new RelacionSimpleSinVoto(C5, E1);
            RelacionSimple R8 = new RelacionSimpleSinVoto(C2, E1);
            RelacionSimple R9 = new RelacionSimpleSinVoto(C2, E2);
            //RelacionSimple R10 = new RelacionSimpleSinVoto(C2, E3);
            RelacionSimple R11 = new RelacionSimpleSinVoto(C2, E4);
            RelacionSimple R12 = new RelacionSimpleSinVoto(C2, E5);
            //RelacionCompuestaAnd R = new RelacionCompuestaAnd(R1, R2);
            Relaciones RR = new Relaciones();
            RR.agregarRelacion(R2);
            RR.agregarRelacion(R2);
            RR.agregarRelacion(R3);
            //RR.agregarRelacion(R4);
            RR.agregarRelacion(R5);
            RR.agregarRelacion(R6);
            RR.agregarRelacion(R7);
            RR.agregarRelacion(R8);
            RR.agregarRelacion(R9);
           // RR.agregarRelacion(R10);
            RR.agregarRelacion(R11);
            RR.agregarRelacion(R12);
            //RR.agregarRelacion(R1);
            /*print("Todas" + RR.obtRelaciones(C1));
            print("Todas" + RR.obtRelaciones(C2));
            print("Todas" + RR.obtRelaciones(E1));
            print("Todas" + RR.obtRelaciones(E2));
            print("Todas" + RR.obtRelaciones(E3));*/
            /*long tini = System.nanoTime();
            CjtEvento aa = new CjtEvento();
            aa.AgregarEventoRandom();
            print(aa.ConsultarTodosEventos().toString());
            //Congreso aa = new Congreso();
            //aa.agregarCongresistaRandom();
            /*print(aa.obtenerCongreso().toString());
            for (int i = 0; i < 8000000; ++i) {
                //print(""+i);
                //aa.agregarCongresistaRandom();
            }
            //print(aa.size()+"");
            //print("Tiempo de ejecución(ms): " + String.valueOf((System.nanoTime() - tini) / 1000000.0));
            //print(aa.obtenerCongreso()+"");*/


        }
        catch (Exception a) {
            print(a.getMessage());
        }



    }
    private static void testcc() throws Exception {
        ControladorCongreso cr = new ControladorCongreso();
        cr.agregarCongresistaRandom(500);
        String aux = cr.obtBloque(0);
        String[] aux2 = aux.split(cr.obtSep());
        for (String r:aux2) {
            print(r);
        }


    }
    private static void testCP() throws Exception {
        CPCongreso aux = new CPCongreso();
        aux.agregarCongresistaRandom(100);
        for (int i = 0; i < 100; ++i) {
            print(aux.obtCongresista(i));
        }
    }
    private static void print(String str) {
        System.out.println(str);
    }
}
