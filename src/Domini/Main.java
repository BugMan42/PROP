package Domini;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        DataHandler Data = new DataHandler();
        Congresista a = new Congresista();
        Date data = new Date();

        Evento e = new Votacion("anteproyecto golf", data);
        Data.Crear(a);
        Data.print_congr(a);

    }
}
