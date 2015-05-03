package Domini;

import java.util.Scanner;

/**
 * Created by jose on 3/05/15.
 */
public class DriverControladorRelaciones {
    // Mensajes excepciones.
    static final String E1 = "Número de parámetros incorrecto.";

    private static ControladorCongreso cc;
    private static ControladorCjtEvento ce;
    private static ControladorRelaciones cr;

    public static void main(String[] args) {
        DriverControladorRelaciones dr = new DriverControladorRelaciones();
        cc = new ControladorCongreso();
        ce = new ControladorCjtEvento();
        cr = new ControladorRelaciones(cc,ce);
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            dr.menu();
            String linea = ui.nextLine();
            String[] params = linea.split("\\s");
            try {
                op = Integer.parseInt(params[0]);
                switch (op) {
                    case 0:
                        if(params.length!=8) throw new Exception(E1);
                        cc.agregarCongresista(params[1], params[2], params[3], Integer.parseInt(params[4]), params[5],
                                params[6], params[7]);
                        break;
                    case 1:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(cc.obtenerCongreso().toString());
                        break;
                    case 2:
                        if(params.length!=2) throw new Exception(E1);
                        cc.eliminarCongresista(params[1]);
                        break;
                    case 3:
                        if(params.length!=1) throw new Exception(E1);
                        cc.eliminarCongreso();
                        break;
                    case 4:
                        if(params.length!=2) throw new Exception(E1);
                        cc.guardar(params[1]);
                        break;
                    case 5:
                        if(params.length!=2) throw new Exception(E1);
                        cc.cargar(params[1]);
                        break;
                    case 6:
                        if(params.length!=4) throw new Exception(E1);
                        ce.AgregarVotacion(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 7:
                        if(params.length!=4) throw new Exception(E1);
                        ce.AgregarReunionPersonal(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 8:
                        if(params.length!=4) throw new Exception(E1);
                        ce.AgregarReunionProfesional(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 9:
                        if(params.length!=4) throw new Exception(E1);
                        ce.AgregarActo(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 10:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(ce.ConsultarTodosEventos().toString());
                        break;
                    case 11:
                        if(params.length!=3) throw new Exception(E1);
                        ce.EliminarEvento(params[1], params[2]);
                        break;
                    case 12:
                        if(params.length!=1) throw new Exception(E1);
                        ce.EliminarCjtEvento();
                        break;
                    case 13:
                        if(params.length!=2) throw new Exception(E1);
                        ce.guardar(params[1]);
                        break;
                    case 14:
                        if(params.length!=2) throw new Exception(E1);
                        ce.cargar(params[1]);
                        break;
                    case 15:
                        if(params.length!=4) throw new Exception(E1);
                        cr.agregarRelacion(params[1], params[2], params[3]);
                        break;
                    case 16:
                        if(params.length!=4) throw new Exception(E1);
                        cr.eliminarRelacion(params[1], params[2], params[3]);
                        break;
                    case 17:
                        if(params.length!=2) throw new Exception(E1);
                        dr.print(cr.obtEventos(params[1]).toString());
                        break;
                    case 18:
                        if(params.length!=3) throw new Exception(E1);
                        dr.print(cr.obtCongresistas(params[1], params[2]).toString());
                        break;
                    case 19:
                        if(params.length!=2) throw new Exception(E1);
                        dr.print(cr.obtRelaciones(params[1]).toString());
                        break;
                    case 20:
                        if(params.length!=3) throw new Exception(E1);
                        dr.print(cr.obtRelaciones(params[1],params[2]).toString());
                        break;
                    /*
                    case 21:
                        if(params.length!=2) throw new Exception(E1);
                        cr.guardar(params[1]);
                        break;
                    case 22:
                        if(params.length!=2) throw new Exception(E1);
                        cr.cargar(params[1]);
                        break;*/

                }
            }
            catch (Exception e) {
                dr.print(e.getMessage());
                op = -1;
            }
        }
        while(op != 23);
    }

    private void menu(){
        print("\nDRIVER DE CONTROLADOR RELACIONES");
        print("//////// Congreso /////////////////////////////");
        print("0 agregarCongresista(String dni, String nombre, String apellido, int edad, " +
                "String ciudad, String estado, String partido)");
        print("1 obtenerCongreso()");
        print("2 eliminarCongresista(String dni)");
        print("3 eliminarCongreso()");
        print("4 guardarCongreso(String ruta)");
        print("5 cargarCongreso(String ruta)");
        print("//////// Eventos //////////////////////////////");
        print("6 AgregarVotacion(String nombre, String fecha, int importancia)");
        print("7 AgregarReunionPersonal(String nombre, String fecha, int importancia)");
        print("8 AgregarReunionProfesional(String nombre, String fecha, int importancia)");
        print("9 AgregarActo(String nombre, String fecha, int importancia)");
        print("10 ConsultarTodosEventos()");
        print("11 EliminarEvento(String nombre, String fecha)");
        print("12 EliminarCjtEvento()");
        print("13 guardarEventos(String ruta)");
        print("14 cargarEventos(String ruta)");
        print("//////// Relaciones ///////////////////////////");
        print("15 agregarRelacion(String dni, String nombre, String fecha)");
        print("16 eliminarRelacion(String dni, String nombre, String fecha)");
        print("17 obtEventos(String dni)");
        print("18 obtCongresistas(String nombre, String fecha)");
        print("19 obtRelaciones(String dni)");
        print("20 obtRelaciones(String nombre, String fecha)");
        print("21 guardarRelaciones(String ruta)");
        print("22 cargarRelaciones(String ruta)");
        print("///////////////////////////////////////////////");
        print("23 Salir\n");
    }

    private void print(String s){
        System.out.println(s);
    }

}