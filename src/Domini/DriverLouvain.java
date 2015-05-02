package Domini;


/**
 * Created by jose on 2/04/15.
 */
public class DriverLouvain {
    public static void main(String[] args) throws Exception{
        DriverLouvain dl = new DriverLouvain();
        Grafo g = new Grafo();

        // EJEMPLO 1
        /*
        g.agregarVertice("a");
        g.agregarVertice("b");
        g.agregarVertice("c");

        g.agregarArista("a", "b", 1);
        g.agregarArista("b", "a", 1);
        g.agregarArista("b", "c", 1);
        g.agregarArista("c","b",1);
        g.agregarArista("c","a",1);
        g.agregarArista("a","c",1);
        */

        // EJEMPLO 2
        /*
        g.agregarVertice("a");
        g.agregarVertice("b");
        g.agregarVertice("c");
        g.agregarVertice("d");
        g.agregarVertice("e");
        g.agregarVertice("f");

        g.agregarArista("a","b",1);
        g.agregarArista("b","a",1);
        g.agregarArista("b","c",1);
        g.agregarArista("c","b",1);
        g.agregarArista("c","a",1);
        g.agregarArista("a","c",1);
        g.agregarArista("c","d",1);
        g.agregarArista("d","c",1);
        g.agregarArista("d","e",1);
        g.agregarArista("e","d",1);
        g.agregarArista("e","f",1);
        g.agregarArista("f","e",1);
        g.agregarArista("f","d",1);
        g.agregarArista("d","f",1);
        */

        // EJEMPLO 3
        g.agregarVertice("0");
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");
        g.agregarVertice("5");
        g.agregarVertice("6");
        g.agregarVertice("7");
        g.agregarVertice("8");
        g.agregarVertice("9");
        g.agregarVertice("10");
        g.agregarVertice("11");
        g.agregarVertice("12");

        g.agregarArista("0","1",1);
        g.agregarArista("1","0",1);
        g.agregarArista("1","2",1);
        g.agregarArista("2","1",1);
        g.agregarArista("2","3",1);
        g.agregarArista("3","2",1);
        g.agregarArista("3","1",1);
        g.agregarArista("1","3",1);
        g.agregarArista("0","4",1);
        g.agregarArista("4","0",1);
        g.agregarArista("4","5",1);
        g.agregarArista("5","4",1);
        g.agregarArista("5","6",1);
        g.agregarArista("6","5",1);
        g.agregarArista("6","4",1);
        g.agregarArista("4","6",1);
        g.agregarArista("0","7",1);
        g.agregarArista("7","0",1);
        g.agregarArista("7","8",1);
        g.agregarArista("8","7",1);
        g.agregarArista("8","9",1);
        g.agregarArista("9","8",1);
        g.agregarArista("9","7",1);
        g.agregarArista("7","9",1);
        g.agregarArista("0","10",1);
        g.agregarArista("10","0",1);
        g.agregarArista("10","11",1);
        g.agregarArista("11","10",1);
        g.agregarArista("11","12",1);
        g.agregarArista("12","11",1);
        g.agregarArista("12","10",1);
        g.agregarArista("10","12",1);



        double Q = 0.000001;
        Entrada in = new Entrada(g,Q);
        Salida out = new Salida();
        Louvain l = new Louvain(in,out);
        Grafo s = l.ejecutar_algoritmo();

        for(String a : out.mostrarHistorial()) dl.print(a);
    }

    private void print(String s){
        System.out.println(s);
    }
}
