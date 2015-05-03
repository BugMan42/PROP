package Domini;

import java.util.Scanner;

/**
 * Created by jose on 1/05/15.
 */
public class DriverControladorCjtEvento {
    // Mensajes excepciones.
    private static final String E1 = "Número de parámetros incorrecto.";

    private ControladorCjtEvento ce;

    public static void main(String[] args) {
        DriverControladorCjtEvento dce = new DriverControladorCjtEvento();
        dce.ce = new ControladorCjtEvento();
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            dce.menu();
            String linea = ui.nextLine();
            String[] params = linea.split("\\s");
            try {
                op = Integer.parseInt(params[0]);
                switch (op) {
                    case 0:
                        if(params.length!=3) throw new Exception(E1);
                        dce.ce.EliminarEvento(params[1],params[2]);
                        break;
                    case 1:
                        if(params.length!=1) throw new Exception(E1);
                        dce.ce.EliminarCjtEvento();
                        break;
                    case 2:
                        if(params.length!=4) throw new Exception(E1);
                        dce.ce.AgregarVotacion(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 3:
                        if(params.length!=4) throw new Exception(E1);
                        dce.ce.AgregarReunionPersonal(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 4:
                        if(params.length!=4) throw new Exception(E1);
                        dce.ce.AgregarReunionProfesional(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 5:
                        if(params.length!=4) throw new Exception(E1);
                        dce.ce.AgregarActo(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 6:
                        if(params.length!=4) throw new Exception(E1);
                        dce.ce.ModificarNombreEvento(params[1],params[2],params[3]);
                        break;
                    case 7:
                        if(params.length!=4) throw new Exception(E1);
                        dce.ce.ModificarFechaEvento(params[1], params[2], params[3]);
                        break;
                    case 8:
                        if(params.length!=4) throw new Exception(E1);
                        dce.ce.ModificarImpEvento(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 9:
                        if(params.length!=1) throw new Exception(E1);
                        dce.print(dce.ce.ConsultarTodosEventos().toString());
                        break;
                    case 10:
                        if(params.length!=3) throw new Exception(E1);
                        dce.print(dce.ce.ConsultarEvento(params[1], params[2]).toString());
                        break;
                    case 11:
                        if(params.length!=3) throw new Exception(E1);
                        dce.print(String.valueOf(dce.ce.ExisteEvento(params[1], params[2])));
                        break;
                    case 12:
                        if(params.length!=1) throw new Exception(E1);
                        dce.print(String.valueOf(dce.ce.size()));
                        break;
                    case 13:
                        if(params.length!=2) throw new Exception(E1);
                        dce.ce.guardar(params[1]);
                        break;
                    case 14:
                        if(params.length!=2) throw new Exception(E1);
                        dce.ce.cargar(params[1]);
                        break;
                }
            }
            catch (Exception e) {
                dce.print(e.getMessage());
                op = -1;
            }
        }
        while(op != 15);
    }

    private void menu(){
        print("\nDRIVER DE CONTROLADOR CONJUNTO EVENTO");
        print("0 EliminarEvento(String nombre, String fecha)");
        print("1 EliminarCjtEvento()");
        print("2 AgregarVotacion(String nombre, String fecha, int importancia)");
        print("3 AgregarReunionPersonal(String nombre, String fecha, int importancia)");
        print("4 AgregarReunionProfesional(String nombre, String fecha, int importancia)");
        print("5 AgregarActo(String nombre, String fecha, int importancia)");
        print("6 ModificarNombreEvento(String nomViejo, String fecha, String nomNuevo)");
        print("7 ModificarFechaEvento(String nombre, String fechaVieja, String fechaNueva)");
        print("8 ModificarImpEvento(String nombre, String fecha, int importance)");
        print("9 ConsultarTodosEventos()");
        print("10 ConsultarEvento(String nombre, String fecha)");
        print("11 ExisteEvento(String nombre, String fecha)");
        print("12 size()");
        print("13 guardar(String ruta)");
        print("14 cargar(String ruta)");
        print("15 Salir\n");
    }

    private void print(String s){
        System.out.println(s);
    }

}
