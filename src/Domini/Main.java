package Domini;

import java.text.ParseException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {
        //System.out.println("Hello World!");
        DataHandler Data = new DataHandler();
        Congresista a = new Congresista();
        Date data = new Date();

        Evento e = new Votacion("Anteproyecto Golf", data);
        Evento e2 = new Votacion();
        Data.Crear(a);
        Data.print_congr(a);
        Data.Crear(e2);
        Data.print_evento(e);
        Data.print_evento(e2);

        Voto v1 = new Nulo("12");
        System.out.println(v1.getClass().getSimpleName());
        System.out.println(v1.obt_tipo());
    }
}
