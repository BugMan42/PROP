package Domini;

public class grafoDebug {
    public static void main(String[] args) throws Exception {
        Grafo  g = new Grafo();
        Congreso C = new Congreso();


        /*C.agregarCon gresista(new Dni("20901724b"), "a", "b",12,"a","b","c");
        C.agregarCongresista(new Dni("20901724c"), "a", "b",12,"a","b","c");
        C.agregarCongresista(new Dni("20901724d"), "a", "b",12,"a","b","c");
        C.agregarCongresista(new Dni("20901724f"), "a", "b",12,"a","b","c");*/

        g.agregarVertice("a");
        g.agregarVertice("b");
        g.agregarVertice("c");
        print(g.consultarVertices() + "");
        g.agregarArista("a", "b", 1.0);
        g.agregarArista("a", "b", 2.0);
        g.agregarArista("a", "b", 3.0);
        print("1 " + g.existeArista("a", "b"));
        print("2 " + g.existeAristaPeso("a", "b", 1.0));
        print("3 "+g.existeAristaPeso("a", "b", 4.0));
        print("4 " + g.obtenerListaPesos("a", "b"));
        g.modificarArista("a", "b", 1.0, 2.0);
        print("5 " + g.obtenerListaPesos("a", "b"));
        g.eliminarArista("a", "b", 2.0);
        print("6 " + g.obtenerListaPesos("a", "b"));
        g.eliminarArista("a", "b", 2.0);
        print("7 "+g.obtenerListaPesos("a","b"));
        g.eliminarArista("a", "b", 2.0);
        print("8 "+g.obtenerListaPesos("a","b"));
        g.eliminarArista("a", "b", 3.0);
        print("9 "+g.obtenerListaPesos("a","b"));
        print("10 "+g.nodosSalida("a"));
    }
    private static void print(String str) {
        System.out.println(str);
    }
}
