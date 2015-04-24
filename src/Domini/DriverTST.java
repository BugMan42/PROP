package Domini;

import java.util.ArrayList;
import java.util.Scanner;


public class DriverTST {

    private static Scanner user_input = new Scanner(System.in);
    private static TST<Integer> tst;

    private static void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        tst = new TST<Integer>();
        //System.out.println("Escriba la clave:");
        //String key = user_input.next();
        //System.out.println("Escriba el valor numérico:");
        // Integer entero = Integer.parseInt(user_input.next());
        //tst.insertar("1234", 12);
        tst.insertar("12", 12);
        tst.eliminar("12");
        //tst.insertar("12345", 12);
        //print(String.valueOf(tst.obtener("12")));
        //tst.("13", 13);
        //print(String.valueOf(tst.obtener("12")));
        //System.out.println(tst.existe("1"));
        //System.out.println(tst.existe("1234"));
        //System.out.println(tst.existe("12"));
        //print(String.valueOf(tst.obtener("12")));
        //tst.print();
        // ArrayList<String> st = tst.consultar();
       /* System.out.println("Elementos:");
        for(int i = 0; i < st.size(); ++i) {
            System.out.println(st.get(i));
        }
       // String outp = "El valor con la clave " + key + " es " + tst.obtObjeto(key);
        //System.out.println(outp);
    }*/


    }
}
