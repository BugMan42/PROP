package Domini;

public class grafoDebug {
    public static void main(String[] args) throws Exception {
        Grafo   g = new Grafo();
        g.agregarVertice("a");
        g.agregarVertice("b");
        g.agregarVertice("c");
        print(g.consultarVertices() + "");
        print(g.consultarVerticesID() + "");
        /*print("Nodos Salida a "+g.nodosSalida("a"));
        print("Nodos Salida b "+g.nodosSalida("b"));
        print("Nodos Salida c " + g.nodosSalida("c"));
        print("Nodos Entrada a "+g.nodosEntrada("a"));
        print("Nodos Entrada b "+g.nodosEntrada("b"));
        print("Nodos Entrada c "+g.nodosEntrada("c"));*/
        print("Degree Salida 0: "+g.degreeSalida("b"));
        print("Degree Salida 0: "+g.degreeSalida(1));
    }
    private static void print(String str) {
        System.out.println(str);
    }
}
