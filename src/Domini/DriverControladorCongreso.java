package Domini;

import java.util.Scanner;

/**
 * Created by Jose on 15/04/2015.
 */
public class DriverControladorCongreso {
    // Mensajes excepciones.
    private static final String E1 = "Número de parámetros incorrecto.";

    private ControladorCongreso cc;

    public static void main(String[] args) {
        DriverControladorCongreso dcc = new DriverControladorCongreso();
        dcc.cc = new ControladorCongreso();
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
                        dcc.print(String.valueOf(dcc.cc.size()));
                        break;
                    case 1:
                        dcc.validar_num_params(params, 1);
                        dcc.print(String.valueOf(dcc.cc.esVacio()));
                        break;
                    case 2:
                        dcc.validar_num_params(params,8);
                        dcc.cc.agregarCongresista(params[1], params[2], params[3], Integer.parseInt(params[4]), params[5],
                                params[6], params[7]);
                        break;
                    case 3:
                        dcc.validar_num_params(params, 1);
                        dcc.print(dcc.cc.obtenerListaID().toString());
                        break;
                    case 4:
                        dcc.validar_num_params(params, 1);
                        dcc.print(dcc.cc.obtenerCongreso().toString());
                        break;
                    case 5:
                        dcc.validar_num_params(params, 2);
                        dcc.print(Boolean.toString(dcc.cc.contieneCongresista(params[1])));
                        break;
                    case 6:
                        dcc.validar_num_params(params, 2);
                        dcc.cc.eliminarCongresista(params[1]);
                        break;
                    case 7:
                        dcc.validar_num_params(params, 1);
                        dcc.cc.eliminarCongreso();
                        break;
                    case 8:
                        dcc.validar_num_params(params,3);
                        dcc.cc.modNombreCongresista(params[1], params[2]);
                        break;
                    case 9:
                        dcc.validar_num_params(params,3);
                        dcc.cc.modApellidoCongresista(params[1], params[2]);
                        break;
                    case 10:
                        dcc.validar_num_params(params,3);
                        dcc.cc.modEdadCongresista(params[1], Integer.parseInt(params[2]));
                        break;
                    case 11:
                        dcc.validar_num_params(params,3);
                        dcc.cc.modCiudadCongresista(params[1], params[2]);
                        break;
                    case 12:
                        dcc.validar_num_params(params,3);
                        dcc.cc.modEstadoCongresista(params[1], params[2]);
                        break;
                    case 13:
                        dcc.validar_num_params(params,3);
                        dcc.cc.modPartidoCongresista(params[1], params[2]);
                        break;
                    case 14:
                        dcc.validar_num_params(params,3);
                        dcc.cc.modDniCongresista(params[1], params[2]);
                        break;
                    case 15:
                        dcc.validar_num_params(params,9);
                        dcc.cc.modCongresista(params[1], params[2], params[3], params[4], Integer.parseInt(params[5]),
                                params[6], params[7], params[8]);
                        break;
                    case 16:
                        dcc.validar_num_params(params,2);
                        dcc.print(dcc.cc.consultarCongresista(params[1]).toString());
                        break;
                    case 17:
                        dcc.validar_num_params(params,1);
                        dcc.print(dcc.cc.toString());
                        break;
                    case 18:
                        dcc.validar_num_params(params, 2);
                        dcc.cc.guardar(params[1]);
                        break;
                    case 19:
                        dcc.validar_num_params(params, 2);
                        dcc.cc.cargar(params[1]);
                        break;
                }
            }
            catch (Exception e) {
                dcc.print(e.getMessage());
                op = -1;
            }
        }
        while(op != 20);
    }

    private void menu(){
        print("\nDRIVER DE CONTROLADOR CONGRESO");
        print("0 size()");
        print("1 esVacio()");
        print("2 agregarCongresista(String dni, String nombre, String apellido, int edad, " +
                "String ciudad, String estado, String partido)");
        print("3 obtenerListaID()");
        print("4 obtenerCongreso()");
        print("5 contieneCongresista(String dni)");
        print("6 eliminarCongresista(String dni)");
        print("7 eliminarCongreso()");
        print("8 modNombreCongresista(String dni,String nombre)");
        print("9 modApellidoCongresista(String dni,String apellido)");
        print("10 modEdadCongresista(String dni,int edad)");
        print("11 modCiudadCongresista(String dni,String ciudad)");
        print("12 modEstadoCongresista(String dni,String estado)");
        print("13 modPartidoCongresista(String dni,String partido)");
        print("14 modDniCongresista(String dni, String dni_nuevo)");
        print("15 modCongresista(String dni, String dni_nuevo, String nombre, String apellido," +
                " int edad, String ciudad, String estado, String partido)");
        print("16 consultarCongresista(Dni dni)");
        print("17 toString()");
        print("18 guardar(String ruta)");
        print("19 cargar(String ruta)");
        print("20 Salir\n");
    }

    private void print(String s){
        System.out.println(s);
    }

    public void validar_num_params(String[] params, int num) throws Exception {
        if (params.length != num) throw new Exception(E1);
    }

}
