package Domini;

public class grafoDebug {
    public static void main(String[] args) throws Exception {
        Grafo  g = new Grafo();
        Congreso C = new Congreso();
        Congresista C1 = new Congresista(new Dni("20901724z"), "a", "b", 12, "a", "b", "c");
        Congresista c2 = new Congresista(new Dni("20901724a"), "a", "b", 12, "a", "b", "c");
        /*C.agregarCon gresista(new Dni("20901724b"), "a", "b",12,"a","b","c");
        C.agregarCongresista(new Dni("20901724c"), "a", "b",12,"a","b","c");
        C.agregarCongresista(new Dni("20901724d"), "a", "b",12,"a","b","c");
        C.agregarCongresista(new Dni("20901724f"), "a", "b",12,"a","b","c");*/



    }
    private static void print(String str) {
        System.out.println(str);
    }
}
