package Domini;


public class DriverRelacion {
    public static void main(String[] args) {
        Relacion r = new RelacionDriver();
        print("Driver Relacion");
        print("Relacion es Simple: "+r.esSimple());
    }
    public static void print(String str) {
        System.out.println(str);
    }
}
