package Domini;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by Jose on 15/04/2015.
 */
public class DriverControladorCongreso {

    private static ControladorCongreso cc;

    public static void main(String[] args) throws ParseException {
        cc = new ControladorCongreso();
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            menu();
            String linea = ui.nextLine();
            String[] params = linea.split("\\s");
            op = Integer.parseInt(params[0]);
            switch (op){
                case 1: add_congresista(params);
                        break;
            }
        }
        while(op != 0);
    }

    private static void menu(){
        print("DRIVER DE CONTROLADOR CONGRESO");
        print("1 add_congresista(String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido)");
        print("0 Salir");
    }

    private static void add_congresista(String[] params){
        String aux = params[1];
        for (int i=2; i<8; ++i) aux += " " + params[i];
        cc.add_congresista(aux);
        //print("Añadido correctamente");
    }

    private static void print(String s){
        System.out.println(s);
    }
}
