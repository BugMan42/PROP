package Domini;

import java.util.Random;

public class Fecha {
    static final String error9 = "Fecha no valida";
    final static String error1 = "Dia no puede ser inferior a 1 ni mayor que 31";
    final static String error2 = "Mes no puede ser inferior a 1 ni mayor que 12";
    final static String error3 = "Año no puede ser inferior a 1 ni mayor que 3000";
    final static String error4 = "Mes febrero no tiene dia 30 ni 31";
    final static String error5 = "Dia 29 de febrero solo en años bisiestos";
    final static String error6 = "Mes no tiene dia 31";
    final static String error8 = "El formato es dd/mm/yyyy";
    private int[] fecha;

    private static boolean Correcto(int dia, int mes, int any) throws Exception{
        if (dia < 1 || dia > 31) throw new Exception(error1);
        if (mes <= 0 || mes >= 13) throw new Exception(error2);
        if (any < 1 || any > 9999) throw new Exception(error3);
        if (mes == 2) {
            if ((dia == 30 || dia == 31)) throw new Exception(error4);
            if (dia == 29 && ((any % 4 != 0 || any % 100 == 0) && any % 400 != 0)) throw new Exception(error5);
        }
        else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia == 31 ) throw new Exception(error6);
        return true;
    }


    public Fecha(Random r) {
        //Uso numeros random del [1,27] para el dia.
        //[1,12] para mes y [1,9999] para el año
        fecha = new int[3];
        fecha[0] = r.nextInt(28)+1;
        fecha[1] = r.nextInt(12)+1;
        fecha[2] = r.nextInt(9999)+1;
    }

    public Fecha(String data) throws Exception {
        String[] aux = data.split("/");
        if (aux.length != 3) throw new Exception(error9 + " : " + error8);
        int dia = Integer.parseInt(aux[0]);
        int mes = Integer.parseInt(aux[1]);
        int año = Integer.parseInt(aux[2]);
        Correcto(dia, mes, año);
        fecha = new int[3];
        fecha[0] = dia;
        fecha[1] = mes;
        fecha[2] = año;
    }

    //Consultora
    public String ConsultarFecha() {
        return fecha[0] + "/" + fecha[1] + "/" + fecha[2];
    }

    public String toString() {return Integer.toString(fecha[0]) + Integer.toString(fecha[1]) + Integer.toString(fecha[2]);}

    public String alReves() {
        String any = Integer.toString(fecha[2]);
        String mes = Integer.toString(fecha[1]);
        String dia = Integer.toString(fecha[0]);
        if (any.length() < 4) {
            int n = 4 - any.length();
            for (int i = 0; i < n;++i) {
                any = "0"+any;
            }
        }
        if (mes.length() < 2) mes = "0"+mes;
        if (dia.length() < 2) dia = "0"+dia;
        return any + "/" + mes + "/" + dia;
    }

    public boolean equals(Fecha f) {
        return fecha[0] == f.fecha[0] && fecha[1] == f.fecha[1] && fecha[2] == f.fecha[2];

    }
}
