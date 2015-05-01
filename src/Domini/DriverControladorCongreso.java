package Domini;

import java.util.Scanner;

/**
 * Created by Jose on 15/04/2015.
 */
public class DriverControladorCongreso {
    // Mensajes excepciones.
    static final String E1 = "Número de parámetros incorrecto.";

    private static ControladorCongreso cc;

    public static void main(String[] args) {
        DriverControladorCongreso dcc = new DriverControladorCongreso();
        cc = new ControladorCongreso();
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            dcc.menu();
            String linea = ui.nextLine();
            String[] params = linea.split("\\s");
            try {
                op = Integer.parseInt(params[0]);
                switch (op) {
                    case 0:
                        dcc.validar_num_params(params, 1);
                        dcc.print(String.valueOf(cc.size()));
                        break;
                    case 1:
                        dcc.validar_num_params(params, 1);
                        dcc.print(String.valueOf(cc.esVacio()));
                        break;
                    case 2:
                        dcc.validar_num_params(params,8);
                        cc.agregarCongresista(params[1], params[2], params[3], Integer.parseInt(params[4]), params[5],
                                params[6], params[7]);
                        break;
                    case 3:
                        dcc.validar_num_params(params, 1);
                        dcc.print(cc.obtenerListaID().toString());
                        break;
                    case 4:
                        dcc.validar_num_params(params, 2);
                        dcc.print(Boolean.toString(cc.contieneCongresista(params[1])));
                        break;
                    case 5:
                        dcc.validar_num_params(params, 2);
                        cc.eliminarCongresista(params[1]);
                        break;
                    case 6:
                        dcc.validar_num_params(params, 1);
                        cc.eliminarCongreso();
                        break;
                    case 7:
                        dcc.validar_num_params(params,3);
                        cc.modNombreCongresista(params[1], params[2]);
                        break;
                    case 8:
                        dcc.validar_num_params(params,3);
                        cc.modApellidoCongresista(params[1], params[2]);
                        break;
                    case 9:
                        dcc.validar_num_params(params,3);
                        cc.modEdadCongresista(params[1], Integer.parseInt(params[2]));
                        break;
                    case 10:
                        dcc.validar_num_params(params,3);
                        cc.modCiudadCongresista(params[1], params[2]);
                        break;
                    case 11:
                        dcc.validar_num_params(params,3);
                        cc.modEstadoCongresista(params[1], params[2]);
                        break;
                    case 12:
                        dcc.validar_num_params(params,3);
                        cc.modPartidoCongresista(params[1], params[2]);
                        break;
                    case 13:
                        dcc.validar_num_params(params,3);
                        cc.modDniCongresista(params[1], params[2]);
                        break;
                    case 14:
                        dcc.validar_num_params(params,9);
                        cc.modCongresista(params[1], params[2], params[3], params[4], Integer.parseInt(params[5]),
                                params[6], params[7], params[8]);
                        break;
                    case 15:
                        dcc.validar_num_params(params,2);
                        dcc.print(cc.consultarCongresista(params[1]).toString());
                        break;
                    case 16:
                        dcc.validar_num_params(params,1);
                        dcc.print(cc.toString());
                        break;
                }
            }
            catch (Exception e) {
                dcc.print(e.getMessage());
                op = -1;
            }
        }
        while(op != 17);
    }

    private void menu(){
        print("\nDRIVER DE CONTROLADOR CONGRESO");
        print("0 size()");
        print("1 esVacio()");
        print("2 agregarCongresista(String dni, String nombre, String apellido, int edad, " +
                "String ciudad, String estado, String partido)");
        print("3 obtenerListaID()");
        print("4 contieneCongresista(String dni)");
        print("5 eliminarCongresista(String dni)");
        print("6 eliminarCongreso()");
        print("7 modNombreCongresista(String dni,String nombre)");
        print("8 modApellidoCongresista(String dni,String apellido)");
        print("9 modEdadCongresista(String dni,int edad)");
        print("10 modCiudadCongresista(String dni,String ciudad)");
        print("11 modEstadoCongresista(String dni,String estado)");
        print("12 modPartidoCongresista(String dni,String partido)");
        print("13 modDniCongresista(String dni, String dni_nuevo)");
        print("14 modCongresista(String dni, String dni_nuevo, String nombre, String apellido," +
                " int edad, String ciudad, String estado, String partido)");
        print("15 consultarCongresista(Dni dni)");
        print("16 toString()");
        print("17 Salir\n");
    }

    private void print(String s){
        System.out.println(s);
    }

    public void validar_num_params(String[] params, int num) throws Exception {
        if (params.length != num) throw new Exception(E1);
    }

}
