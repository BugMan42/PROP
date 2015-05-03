package Persistencia;

import java.util.Scanner;

/**
 * Created by Jose on 28/03/2015.
 */
public class DriverControladorPersistencia {
    // Mensajes excepciones.
    static final String E1 = "Número de parámetros incorrecto.";

    private ControladorPersistencia cp;

    public static void main(String[] args) {
        DriverControladorPersistencia dcp = new DriverControladorPersistencia();
        dcp.cp = new ControladorPersistencia("./pruebaPersistencia.txt");
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            dcp.menu();
            String linea = ui.nextLine();
            String[] params = linea.split("\\s");
            try {
                op = Integer.parseInt(params[0]);
                switch (op) {
                    case 0:
                        if (params.length != 1) throw new Exception(E1);
                        dcp.cp.abrirLectura();
                        break;
                    case 1:
                        if (params.length != 1) throw new Exception(E1);
                        dcp.cp.abrirEscritura();
                        break;
                    case 2:
                        if (params.length != 2) throw new Exception(E1);
                        dcp.print(dcp.cp.leer(Integer.parseInt(params[1])));
                        break;
                    case 3:
                        if (params.length != 2) throw new Exception(E1);
                        dcp.cp.escribir(params[1].replace("/","\n"));
                        break;
                    case 4:
                        if (params.length != 1) throw new Exception(E1);
                        dcp.cp.cerrarFichero();
                        break;
                }
            }
            catch (Exception e) {
                dcp.print(e.getMessage());
                op = -1;
            }
        }
        while(op != 5);
    }

    private void menu(){
        print("\nDRIVER DE CONTROLADOR PERSISTENCIA");
        print("0 abrirLectura()");
        print("1 abrirEscritura()");
        print("2 leer(int num_lineas)");
        print("3 escribir(String datos)");
        print("4 cerrarFichero()");
        print("5 Salir\n");
    }

    private void print(String s){
        System.out.println(s);
    }

}
