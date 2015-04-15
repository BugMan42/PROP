package Domini;

import java.util.regex.PatternSyntaxException;

/**
 * Created by usuario on 11/04/2015.
 */
public class Fecha {
    private String[] fecha;

    private static boolean Correcto(int dia, int mes, int any) {
        if (dia < 1 || dia > 31) throw new IllegalArgumentException("Dia no puede ser inferior a 1 ni mayor que 31");
        if (mes <= 0 || mes >= 13) throw new IllegalArgumentException("Mes no puede ser inferior a 1 ni mayor que 31");
        if (any < 1) throw new IllegalArgumentException("Año no puede ser inferior a 1");
        if (mes == 2) {
            if ((dia == 30 || dia == 31)) throw new IllegalArgumentException("Febrero no tiene dia 30 ni 31");
            if (dia == 29 && ((any % 4 != 0 || any % 100 == 0) && any % 400 != 0))
                throw new IllegalArgumentException("Dia 29 de febrero solo en a�os bisiestos");
        }
        else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia == 31 ) throw new IllegalArgumentException("El mes no tiene dia 31");
        return true;
    }

    public static boolean valido(String data) {
        if (data.equals("")) return false;
        String[] aux = data.split("/");
        if (aux.length > 3) return false;
        return Correcto(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));
    }

    public Fecha(String data) throws PatternSyntaxException {
        if (data.equals("")) throw new IllegalArgumentException("La fecha no puede ser vac�a");
        String[] aux = data.split("/");
        if (aux.length > 3) throw new IllegalArgumentException("El formato es dd/mm/yyyy");
        Correcto(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));
        fecha = aux;
    }

    //Consultora
    public String ConsultarFecha() {
        return fecha[0] + "/" + fecha[1] + "/" + fecha[2];
    }

    public boolean equals(Fecha f) {
        return fecha[0].equals(f.fecha[0]) && fecha[1].equals(f.fecha[1]) && fecha[2].equals(f.fecha[2]);
    }

    /*traer string de la fecha y comparar
    public boolean igual(String date) {
        if (date.equals("")) throw new IllegalArgumentException("La fecha no puede ser vac�a");
        String[] aux = date.split("/");
        if (aux.length > 3) throw new IllegalArgumentException("El formato es dd/mm/yyyy");
        Correcto(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));
        return fecha[0].equals(aux[0]) && fecha[1].equals(aux[1]) && fecha[2].equals(aux[2]);
    }
    */
}
