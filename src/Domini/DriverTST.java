package Domini;

import java.util.Scanner;

/**
 * Created by falc on 14/04/15.
 */
public class DriverTST {

    private static Scanner user_input = new Scanner( System.in );
    private static TST<Integer> tst;

    public static void main(String[] args)
    {
        tst = new TST<Integer>();
        System.out.println("Escriba la clave:");
        String key = user_input.next();
        System.out.println("Escriba el valor numérico:");
        Integer entero = Integer.parseInt(user_input.next());
        tst.insertar(key, entero);

        String outp = "El valor con la clave " + key + " es " + tst.obtObjeto(key);
        System.out.println(outp);
    }
}
