package Persistencia;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by Jose on 28/03/2015.
 */
public class DriverControladorPersistencia {

    public static void main(String[] args) throws ParseException {
        print("***DRIVER DE CONTROLADOR PERSISTENCIA***");
        Scanner user_input = new Scanner(System.in);
        int op;
        do {
            menu();
            op = Integer.parseInt(user_input.next());
            switch(op){
                case 1: guardar(user_input);
                    break;
                case 2: ayuda();
                    break;
            }
        } while (op != 3);
    }

    private static void menu() {
        print("***1.-GUARDAR");
        print("***2.-AYUDA");
        print("***3.-SALIR");
    }

    private static void guardar(Scanner ui){
        String path = ui.next();
        String data = ui.next();
        ControladorPersistencia cp = new ControladorPersistencia();
        try {
            cp.guardar(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ayuda(){

    }

    private static void print(String s){ System.out.println(s); }
}
