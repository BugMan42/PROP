package Domini;

/**
 * Created by usuario on 21/04/2015.
 */
public class NoValido extends Exception {
    final static String error1 = "Dia no puede ser inferior a 1 ni mayor que 31";
    final static String error2 = "Mes no puede ser inferior a 1 ni mayor que 12";
    final static String error3 = "Año no puede ser inferior a 1";
    final static String error4 = "Febrero no tiene dia 30 ni 31";
    final static String error5 = "Dia 29 de febrero solo en años bisiestos";
    final static String error6 = "El mes no tiene dia 31";
    final static String error7 = "La fecha no puede ser vacia";
    final static String error8 = "El formato es dd/mm/yyyy";
    final static String error9 = "La k minima es 3";
    final static String error10 = "La fecha no puede ser vacia";
    final static String error11 = "La fecha no puede ser vacia";


    public NoValido() {
        super();
    }

    public NoValido(String s, int op) {
        super(s + " no es valido/a");
        if (op > 0) {
            switch (op) {
                case 1:
                    System.out.println(error1);
                    break;
                case 2:
                    System.out.println(error2);
                    break;
                case 3:
                    System.out.println(error3);
                    break;
                case 4:
                    System.out.println(error4);
                    break;
                case 5:
                    System.out.println(error5);
                    break;
                case 6:
                    System.out.println(error6);
                    break;
                case 7:
                    System.out.println(error7);
                    break;
                case 8:
                    System.out.println(error8);
                    break;
                case 9:
                    System.out.println(error9);
                    break;
                case 10:
                    System.out.println(error10);
                    break;
                case 11:
                    System.out.println(error11);
                    break;
            }
        }
    }
}
