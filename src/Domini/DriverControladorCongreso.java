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
                String aux = "";
                switch (op) {
                    case 0:
                        dcc.print(String.valueOf(cc.size()));
                        break;
                    case 1:
                        aux = dcc.validar_num_params(params,8);
                        cc.agregarCongresista(aux);
                        break;
                    case 2:
                        dcc.print(cc.obtenerLista().toString());
                        break;
                    case 3:
                        aux = dcc.validar_num_params(params, 2);
                        dcc.print(Boolean.toString(cc.contieneCongresista(aux)));
                        break;
                    case 4:
                        aux = dcc.validar_num_params(params,2);
                        cc.eliminarCongresista(aux);
                        break;
                    case 5:
                        cc.eliminarCongreso();
                        break;
                    case 6:
                        aux = dcc.validar_num_params(params,3);
                        cc.modificarNombreCongresista(aux);
                        break;
                    case 7:
                        aux = dcc.validar_num_params(params,3);
                        cc.modificarApellidoCongresista(aux);
                        break;
                    case 8:
                        aux = dcc.validar_num_params(params,3);
                        cc.modificarEdadCongresista(aux);
                        break;
                    case 9:
                        aux = dcc.validar_num_params(params,3);
                        cc.modificarCiudadCongresista(aux);
                        break;
                    case 10:
                        aux = dcc.validar_num_params(params,3);
                        cc.modificarEstadoCongresista(aux);
                        break;
                    case 11:
                        aux = dcc.validar_num_params(params,3);
                        cc.modificarPartidoCongresista(aux);
                        break;
                    case 12:
                        aux = dcc.validar_num_params(params,3);
                        cc.modificarDni(aux);
                        break;
                    case 13:
                        aux = dcc.validar_num_params(params,9);
                        cc.modificarCongresista(aux);
                        break;
                    case 14:
                        aux = dcc.validar_num_params(params,2);
                        dcc.print(cc.consultarCongresista(aux).toString());
                        break;
                    case 15:
                        dcc.print(cc.toString());
                        break;
                }
            }
            catch (Exception e) {
                dcc.print(e.getMessage());
                op = -1;
            }
        }
        while(op != 16);
    }

    private void menu(){
        print("\nDRIVER DE CONTROLADOR CONGRESO");
        print("0 size()");
        print("1 agregarCongresista(String dni, String nombre, String apellido, int edad, " +
                "String ciudad, String estado, String partido)");
        print("2 obtenerLista()");
        print("3 contieneCongresista(String dni)");
        print("4 eliminarCongresista(String dni)");
        print("5 eliminarCongreso()");
        print("6 modificarNombreCongresista(Dni dni,String nombre)");
        print("7 modificarApellidoCongresista(Dni dni,String apellido)");
        print("8 modificarEdadCongresista(Dni dni,int edad)");
        print("9 modificarCiudadCongresista(Dni dni,String ciudad)");
        print("10 modificarEstadoCongresista(Dni dni,String estado)");
        print("11 modificarPartidoCongresista(Dni dni,String partido)");
        print("12 modificarDni(Dni dni, Dni dniNuevo)");
        print("13 modificarCongresista(Dni dni,Dni dniNuevo , String nombre, String apellido," +
                " int edad, String ciudad, String estado, String partido)");
        print("14 consultarCongresista(Dni dni)");
        print("15 toString()");
        print("16 Salir\n");
    }

    private void print(String s){
        System.out.println(s);
    }

    public String validar_num_params(String[] params, int num) throws Exception {
        String aux = "";
        if (params.length == num){
            aux = params[1];
            for (int i = 2; i < num; ++i) aux += " " + params[i];
            return aux;
        }
        else throw new Exception(E1);
    }

}
