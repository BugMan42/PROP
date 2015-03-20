public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        DataHandler Data = new DataHandler();
        Congresista a = new Congresista();
        Evento e = new Votacion();
        Data.Crear(a);
        Data.print_congr(a);

    }
}
