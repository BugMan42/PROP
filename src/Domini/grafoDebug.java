package Domini;

public class grafoDebug {
    public static void main(String[] args) throws Exception {
        Grafo   g = new Grafo();
        print(g.nodosEntrada(0)+"");
        /*g.agregarVertice("a");
        g.agregarVertice("b");
        g.agregarVertice("c");
        g.agregarVertice("d");
        g.agregarVertice("e");
        print(g.consultarVertices() + "");
        print(g.consultarVerticesID() + "");
        g.agregarArista("a", "b", 2.0);
        g.agregarArista("a", "b", 3.0);
        g.agregarArista("a", "c", 3.0);
        g.agregarArista("a", "b", 2.0);
        g.agregarArista("b", "c", 1.5);
        g.agregarArista("c", "b", 4.0);
        g.agregarArista("b", "d", 1.0);
        g.agregarArista("d", "a", 2.5);
        g.agregarArista("d", "a", 2.0);
        g.agregarArista("d", "a", 1.0);
        g.agregarArista("a", "a", 2.0);
        print("Nodos Salida a " + g.nodosSalida("a"));
        print("Nodos Salida b " + g.nodosSalida("b"));
        print("Nodos Salida c " + g.nodosSalida("c"));
        print("Nodos Salida d " + g.nodosSalida("d"));
        print("Nodos Salida e " + g.nodosSalida("e"));
        print("Nodos Entrada a "+ g.nodosEntrada("a"));
        print("Nodos Entrada b "+ g.nodosEntrada("b"));
        print("Nodos Entrada c "+ g.nodosEntrada("c"));
        print("Nodos Entrada d "+ g.nodosEntrada("d"));
        print("Nodos Entrada e "+ g.nodosEntrada("e"));
        print("Nodos Entrada e "+ g.nodosEntrada("f"));
        print("Nodos Entrada e "+ g.nodosSalida("f"));
        print("a "+g.degreeEntrada("a"));
        print("b "+g.degreeEntrada("b"));
        print("c "+g.degreeEntrada("c"));
        print("d "+g.degreeEntrada("d"));
        print("e "+g.degreeEntrada("e"));
        print("a "+g.degreeSalida("a"));
        print("b "+g.degreeSalida("b"));
        print("c "+g.degreeSalida("c"));
        print("d "+g.degreeSalida("d"));
        print("e "+g.degreeSalida("e"));
        print("f "+g.degreeSalida("f"));

        //print(""+);*/


    }
    private static void print(String str) {
        System.out.println(str);
    }
}
