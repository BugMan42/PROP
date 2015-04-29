package Domini;

import java.text.ParseException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {
        //System.out.println("Hello World!");
        DataHandler Data = new DataHandler();
        //Congresista a = new Congresista();
        Date data = new Date();

        //Evento e = new Votacion("Anteproyecto Golf", data);
        Evento e2 = new Votacion();
        /*Fecha.Crear(a);
        Fecha.print_congr(a);
        Fecha.Crear(e2);
        Fecha.print_evento(e);
        Fecha.print_evento(e2);*/

        /*Voto v1 = new Nulo("12");
        System.out.println(v1.getClass().getSimpleName());
        System.out.println(v1.obt_tipo());*/
    }
}
