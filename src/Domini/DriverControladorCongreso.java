package Domini;

import java.util.Scanner;

/**
 * Created by Jose on 15/04/2015.
 */
public class DriverControladorCongreso {
    // Mensajes excepciones.
    private static final String E1 = "Número de parámetros incorrecto.";

    private ControladorCongreso cc;
    private ControladorCjtEvento ce;
    private ControladorRelaciones cr;

    public static void main(String[] args) {
        boolean imp = true;
        if(args.length > 0 && args[0].equals("0")) imp = false;

        DriverControladorCongreso dcc = new DriverControladorCongreso();
        dcc.cc = new ControladorCongreso();
        dcc.ce = new ControladorCjtEvento();
        dcc.cr = new ControladorRelaciones(dcc.cc,dcc.ce);
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            if(imp) dcc.menu();
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
                        dcc.print(String.valueOf(dcc.cc.obtSep()));
                        break;
                    case 2:
                        dcc.validar_num_params(params, 1);
                        dcc.print("" + dcc.cc.esVacio());
                        break;
                    case 3:
                        dcc.validar_num_params(params,8);
                        dcc.cc.agregarCongresista(params[1], params[2], params[3], Integer.parseInt(params[4]), params[5],
                                params[6], params[7]);
                        break;
                    case 4:
                        dcc.validar_num_params(params,2);
                        dcc.cc.agregarCongresistaRandom(Long.parseLong(params[1]));
                        break;
                    case 5:
                        dcc.validar_num_params(params, 1);
                        dcc.print(dcc.cc.obtenerListaID().toString());
                        break;
                    case 6:
                        dcc.validar_num_params(params, 1);
                        dcc.print(dcc.cc.obtenerCongreso().toString());
                        break;
                    case 7:
                        dcc.validar_num_params(params, 2);
                        dcc.print(Boolean.toString(dcc.cc.contieneCongresista(params[1])));
                        break;
                    case 8:
                        dcc.validar_num_params(params, 8);
                        dcc.cc.eliminarCongresista(params[1], params[2], params[3], Integer.parseInt(params[4]), params[5],
                                params[6], params[7], dcc.cr);
                        break;
                    case 9:
                        dcc.validar_num_params(params, 1);
                        dcc.cc.eliminarCongreso(dcc.cr);
                        break;
                    case 10:
                        dcc.validar_num_params(params, 15);
                        dcc.cc.modCongresista(params[1], params[2], params[3], params[4], params[5], params[6], Integer.parseInt(params[7]),
                                Integer.parseInt(params[8]), params[9], params[10], params[11], params[12], params[13], params[14], dcc.cr);
                        break;
                    case 11:
                        dcc.validar_num_params(params, 2);
                        dcc.print("" + dcc.cc.consultarCongresista(params[1]));
                        break;
                    case 12:
                        dcc.validar_num_params(params, 2);
                        dcc.print(dcc.cc.consultarStringCongresista(params[1]));
                        break;
                    case 13:
                        dcc.validar_num_params(params, 1);
                        dcc.print(dcc.cc.toString());
                        break;
                    case 14:
                        dcc.validar_num_params(params, 2);
                        dcc.cc.guardar(params[1]);
                        break;
                    case 15:
                        dcc.validar_num_params(params, 2);
                        dcc.cc.cargar(params[1], dcc.cr);
                        break;
                    case 16:
                        dcc.validar_num_params(params, 1);
                        dcc.print(dcc.cc.sizeBusqueda() + "");
                        break;
                    case 17:
                        dcc.validar_num_params(params, 1);
                        dcc.print(dcc.cc.obtCache().toString());
                        break;
                }
            }
            catch (Exception e) {
                dcc.print(e.getMessage());
                op = -1;
            }
        }
        while(op != 18);
    }

    private void menu(){
        print("\nDRIVER DE CONTROLADOR CONGRESO");
        print("0 size()");
        print("1 obtSep()");
        print("2 esVacio()");
        print("3 agregarCongresista(String dni, String nombre, String apellido, int edad, " +
                "String ciudad, String estado, String partido)");
        print("4 agregarCongresistaRandom(Long n)");
        print("5 obtenerListaID()");
        print("6 obtenerCongreso()");
        print("7 contieneCongresista(String dni)");
        print("8 eliminarCongresista(String dni, String nombre, String apellido, int edad, String ciudad, String estado, String partido)");
        print("9 eliminarCongreso()");
        print("10 modCongresista(String dni, String dni_nuevo, String nombre, String nombre_nuevo, String apellido,\n" +
                "                               String apellido_nuevo, int edad, int edad_nuevo, String ciudad, String ciudad_nuevo,\n" +
                "                               String estado, String estado_nuevo, String partido, String partido_nuevo)");
        print("11 consultarCongresista(Dni dni)");
        print("12 consultarStringCongresista(Dni dni)");
        print("13 toString()");
        print("14 guardar(String ruta)");
        print("15 cargar(String ruta)");
        print("16 sizeBusqueda()");
        print("17 obtCache()");
        print("18 Salir\n");
    }

    private void print(String s){
        System.out.println(s);
    }

    public void validar_num_params(String[] params, int num) throws Exception {
        if (params.length != num) throw new Exception(E1);
    }

}
