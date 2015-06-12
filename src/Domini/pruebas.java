package Domini;

import Presentacio.CPCongreso;

import java.util.ArrayList;
import java.util.List;

public class pruebas {
    private static void testBloques1() {
        Congreso a = new Congreso();
        for (int i = 0; i < 100; ++i) a.agregarCongresistaRandom();
        List<Congresista> auxx = a.obtCongresoDni(0,20);
        List<Congresista> aux = a.obtenerCongreso();
        for (int i = 0; i < auxx.size(); ++i) {
            print(aux.get(i).obtID() +" ---> "+auxx.get(i).obtDni());
        }
        print(a.obtenerListaID()+"");
    }
    private static void testCong() throws Exception {
        Congresista C1 = new Congresista(new Dni("00000000A"), "a", "b", 1, "c", "d", "f");
        Congresista C2 = new Congresista(new Dni("00000000h"), "b", "b", 1, "c", "d", "f");
        Congresista C11 = new Congresista(new Dni("00000000g"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C22 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
        Congresista C3 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
        Congresista C4 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
        Congresista C5 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");
        Congresista C6 = new Congresista(new Dni("00000000F"), "Jon-Von", "Neumann", 53, "Budapest", "AU", "Pirata");
        Congresista C7 = new Congresista(new Dni("00000000G"), "Alan", "Turing", 41, "London", "LO", "Liberal");
        Congreso a = new Congreso();
        a.agregarCongresista(C1);
        a.agregarCongresista(C2);
        a.agregarCongresista(C3);
        a.agregarCongresista(C4);
        a.agregarCongresista(C5);
        //a.eliminarCongresista(C1);
        //a.modCongresista(C2,C3);
        a.eliminarCongresista(C4);
        a.eliminarCongresista(C5);
        a.eliminarCongresista(C3);
        for (Congresista b:a.obtenerCongreso()) print(b.toString() + "");
        print("");
        for (Congresista b:a.obtenerListaApellido()) print(b.toString() + "");
        print("");
        for (Congresista b:a.obtCongresoEstado(0,100)) print(b.toString()+"");// + "");
       // print(a.obtenerListaNombre1() + "");
        //print(a.obtCongresoNombre(0,1).toString());
        //print(a.obtenerListaApellido1() + "");
        //print(a.obtenerListaEdad1() + "");
        //print(a.obtenerListaCiudad1() + "");
        //print(a.obtenerListaEstado1() + "");
        //print(a.obtenerListaPartido1() + "");

    }
    private static void testn() throws Exception {
        Grafo a = new Grafo();
        a.agregarVertice("0");
        a.agregarVertice("1");
        a.agregarVertice("2");
        a.agregarArista("0", "1", 1);
        a.agregarArista("1","0",1);
        a.agregarArista("0","1",1);
        a.agregarArista("1","2",1);
        a.agregarArista("2","1",1);
        a.agregarArista("2","0",1);
        a.agregarArista("0","2",1);
        //Entrada
        //Louvain b = new Louvain();

    }
    private static void testNuevTst() {
        Congreso congreso = new Congreso();
        for (int i = 0; i < 60000; ++i) congreso.agregarCongresistaRandom();
        TST<Congresista> tst = congreso.obtenerTSTNombre();
        //print(congreso.obtenerListaNombre() + "");
        List<String> b = tst.clavesPrefijoConStop("C",'-',20);
        if (b != null) print(b+"");
        else print("null2");
    }

    public static void main(String[] args) throws Exception {

        try {
            testNuevTst();
            //testsCaches();
            //testCong();
            //testsCaches();
            //testTST2();
            //testCongresista();
            //testPrefix();
            //testGrafo2();
            //testit();
            //testTST();
            //testRandomCongreso(1000000);
            //testDNI(100000000);
            //testcc();
            //testBloques();
            //testGrafo();;
            //testCP();
            /*
            TST<Integer> tst = new TST<Integer>();
            tst.insertar("abc",1);
            tst.modificar("abc","b",2);
            print(tst.size()+"");

            //tst.insertar("b",2);
            // tst.insertar("c",3);
            //   tst.insertar("d",4);
            //tst.insertar("e", 5);
           // print(tst.consultarObjetos().toString());

            Congresista C1 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
            Congresista C2 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
            Congresista C3 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
            Congresista C4 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
            Congresista C5 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");
            Congresista C6 = new Congresista(new Dni("00000000F"), "Jon-Von", "Neumann", 53, "Budapest", "AU", "Pirata");
            Congresista C7 = new Congresista(new Dni("00000000G"), "Alan", "Turing", 41, "London", "LO", "Liberal");
            */
        }
        catch (Exception a) {
            print(a.getMessage());
        }
    }
    private static void testGrafo2() throws Exception {
        Grafo g = new Grafo();
        g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");

        g.agregarArista("0", "1", 1);
        g.agregarArista("0", "2", 1);
        g.agregarArista("0", "3", 1);
        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("1", "0", 1);
        g.agregarArista("2", "0", 1);
        g.agregarArista("2", "1", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("3", "0", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("4", "3", 1);
        print("sal0: " + g.degreeSalida(0));
        print("sal1: " + g.degreeSalida(1));
        print("sal2: " + g.degreeSalida(2));
        print("sal3: " + g.degreeSalida(3));
        print("sal4: " + g.degreeSalida(4));
        print("ent0: " + g.degreeEntrada(0));
        print("ent1: " + g.degreeEntrada(1));
        print("ent2: " + g.degreeEntrada(2));
        print("ent3: " + g.degreeEntrada(3));
        print("ent4: " + g.degreeEntrada(4));



        print(g.consultarVertices()+"");
    }
    private static void testPrefix() {
        Congreso aux = new Congreso();
        for (int i = 0; i < 100; ++i) aux.agregarCongresistaRandom();
        print(aux.obtenerListaID()+"");
        //List<Congresista> ex = aux.searchPrefix("15");
        /*for (int i = 0; i < ex.size(); ++i) {
            Congresista a = (Congresista) ex.get(i);
            print(a.obtID());
        }*/
    }
    private static void testCongresista() throws Exception {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        TST<ArrayList<Integer> > tst = new TST<ArrayList<Integer>>();
        tst.insertarSinExc("a", a);
        ArrayList<Integer> algo = new ArrayList<Integer>();
        algo.add(2);
        TST<Integer> tst1 = new TST<Integer>();
        //tst1.insertarSinExc("a",1);
        //print(tst1.obtener("a")+"");
        print(""+tst1.insertarSinExc("a",2));
    }

    private static void testsCaches() throws Exception {
        Congresista C1 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C11 = new Congresista(new Dni("00000001A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C111 = new Congresista(new Dni("00000011A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        //Congresista C11 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 86, "SanFrancisco", "NT", "Berkeley");
        Congresista C2 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "SanFrancisco", "NT", "Berkeley");
        Congresista C3 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
        Congresista C4 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
        Congresista C5 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");
        Congresista C6 = new Congresista(new Dni("00000000F"), "Jon-Von", "Neumann", 53, "Budapest", "AU", "Pirata");
        Congresista C7 = new Congresista(new Dni("00000000G"), "Alan", "Turing", 41, "London", "LO", "Liberal");
        Congreso a = new Congreso();
        //for (int i = 0; i < 10; ++i) a.agregarCongresistaRandom();
        a.agregarCongresista(C1);
        a.agregarCongresista(C11);
        a.agregarCongresista(C111);
        a.agregarCongresista(C2);
        a.agregarCongresista(C3);
        a.agregarCongresista(C4);
        a.agregarCongresista(C5);
        a.agregarCongresista(C6);
        a.agregarCongresista(C7);
        ControladorCongreso con = new ControladorCongreso();
        con.agregarCongresista("00000000a","a","a",12,"a","a","a");
        con.agregarCongresista("10000000a","c","a",12,"a","a", "a");
        con.agregarCongresista("20000000a","a","a",12,"a","a","a");
        con.agregarCongresista("00000000d","c","a",12,"a","a","a");
        con.agregarCongresista("00000000b","c","a",12,"a","a","a");

        //print(con.size()+"");
        con.searchByDni("1");
        print(con.obtCache().toString());
        //print(con.);

        /*print(a.obtenerListaID().size() + "");
        print(a.obtenerListaNombre().size() + "");
        print(a.obtenerListaApellido().size() + "");
        print(a.obtenerListaEdad().size() + "");
        print(a.obtenerListaCiudad().size() + "");
        print(a.obtenerListaEstado().size() + "");
        print(a.obtenerListaPartido().size() + "");*/

    }

    private static void testBloques() {
        Congreso a = new Congreso();
        for (int i = 0; i < 20; ++i) a.agregarCongresistaRandom();
        //print(a.obtenerListaNombre()+"");
        List<Congresista> aux = a.obtenerCongreso();
        List<Congresista> auxx = a.obtCongresoNombre(0, 20);
        for (int i = 0; i < auxx.size(); ++i) {
            print(aux.get(i).obtNombre() +" ---> "+auxx.get(i).obtNombre());
        }
        print(a.obtenerListaID()+"");
    }
    private static void testBloques2() {
        Congreso a = new Congreso();
        for (int i = 0; i < 100; ++i) a.agregarCongresistaRandom();
        List<Congresista> auxx = a.obtCongresoDni(0, 20);
        List<Congresista> aux = a.obtenerCongreso();
        for (int i = 0; i < 20; ++i) {
            print(aux.get(i).obtID() +" ---> "+auxx.get(i).obtDni());
        }
        print(a.obtenerListaID()+"");
    }
    private static void testTST() throws Exception {
        TST<Integer> tst = new TST<Integer>();
        //tst.insertar("cff", 3);
        tst.insertar("12", 5);
        tst.insertar("03", 2);
       // tst.insertar("abcd", 4);
       // tst.insertar("abc", 3);
       // tst.insertar("caalshd",7);
       // tst.insertar("a",  1);
       // tst.insertar("adb",6);
        //print("a: "+tst.obtener("ab"));
        //tst.insertar("ab",13);
        print(tst.consultarClaves() + "");
        //tst.modificar("ab", "a",14);
        //tst.insertar("ab",12);
        TSTIterator a = new TSTIterator(tst);
        String aux = "[";
        boolean capullos = true;
        while (a.hasNext()) {
            if (capullos) {
                capullos = false;
            }
            else aux+=", ";
            aux += a.next();
        }
        aux+="]";
        print(aux);
        //print(tst.consultarClaves() + "");
        //print(tst.consultarObjetos()+ "");
        //print("a: "+tst.obtener("a"));
    }
    private static void testGrafo() throws Exception {
       // try {
        Congresista C1 = new Congresista(new Dni("00000000A"), "Edsger", "Dijkstra", 72, "Rotterdam", "NT", "Pirata");
        Congresista C2 = new Congresista(new Dni("00000000B"), "Gordon", "Moore", 86, "San Francisco", "CA", "Berkeley");
        Congresista C3 = new Congresista(new Dni("00000000C"), "Richard", "Hamming", 82, "Monterey", "CA", "Pirata");
        Congresista C4 = new Congresista(new Dni("00000000D"), "Max", "Newman", 87, "Chealsea", "LO", "Democrata");
        Congresista C5 = new Congresista(new Dni("00000000E"), "Allen", "Newll", 65, "San Francisco", "SF", "Pirata");
        Congresista C6 = new Congresista(new Dni("00000000F"), "Jon-Von", "Neumann", 53, "Budapest", "AU", "Pirata");
        Congresista C7 = new Congresista(new Dni("00000000G"), "Alan", "Turing", 41, "London", "LO", "Liberal");
        Edge e1 = new Edge(C1,C2,3);
        Edge e2 = new Edge(C2,C1,1);
        Edge e3 = new Edge(C2,C3,3);
        Edge e4 = new Edge(C4,C3,2);
        Edge e5 = new Edge(C4,C5,3);
        Edge e6 = new Edge(C1,C2,5);
        Edge e7 = new Edge(C7,C2,3);

        GrafoNodoArista<Congresista,Edge> a = new GrafoNodoArista();
        a.agregarVertice(C1);
        a.agregarVertice(C2);
        a.agregarVertice(C3);
        a.agregarVertice(C4);
        a.agregarVertice(C5);
        print("size: " + a.V());
        a.agregarArista(e1);
        a.agregarArista(e2);
        a.agregarArista(e3);
        a.agregarArista(e4);
        a.consultarVertices();
        //print("degree " + a.existeAristaPeso(e1));



        //a.eliminarVertice(C1);
        print("size: " + a.V());
        print(a.consultarVertices()+"");


        //} catch (Exception)
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
    private static void testit() throws Exception {
        TST<Integer> tst = new TST<Integer>();
        tst.insertar("a", 1);
        tst.insertar("b", 2);
        tst.insertar("c", 3);
        tst.insertar("d", 4);
        TSTIterator it = new TSTIterator(tst);
        TSTIterator it2 = new TSTIterator(it);
        print("ite0: "+it.next());
        print("ite1: " + it.next());
        print("ite0: "+it2.next());
        print("ite1: " + it2.next());
    }
    private static void testCP() throws Exception {
        CPCongreso aux = new CPCongreso();
        aux.agregarCongresistaRandom(100);
        for (int i = 0; i < 100; ++i) {
            //print(aux.obtCongresista(i));
        }
    }
    private static void testDNI(int n) {
        Dni a;
        long tini = System.nanoTime();
        for (int i = 0; i < n; ++i) {
            a = new Dni();
            //print(a.toString());
        }
        print(String.valueOf("Tiempo en s: "+(System.nanoTime()-tini)/1000000000.0));
    }
    private static void testRandomCongreso(int n) {
        ControladorCongreso a = new ControladorCongreso();
        long tini = System.nanoTime();
        a.agregarCongresistaRandom(n);
        print(String.valueOf("Tiempo en s: " + (System.nanoTime() - tini) / 1000000000.0)+" tamaño: "+a.size());
    }
    private static void testTST2() throws Exception{
        TST<Integer> tst = new TST<Integer>();
        tst.insertar("b",2);
        tst.insertar("abc", 1);
        tst.insertar("a", 2);
        tst.insertar("abcd", 1);
        tst.insertar("abcder", 1);
        tst.insertar("aadsfcasdv", 1);

        List<Integer> aux = tst.prefijo("ab");
        print(aux+"");
        /*for (int i = 0; i < aux.size(); ++i) {
            print(aux.get(i)+"");
        }*/

    }
    private static void print(String str) {
        System.out.println(str);
    }
}
