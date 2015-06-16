package Domini;

import java.util.Scanner;

/**
 * Created by jose on 1/05/15.
 */
public class DriverControladorCjtEvento {
    // Mensajes excepciones.
    private static final String E1 = "Número de parámetros incorrecto.";

    private ControladorCongreso cc;
    private ControladorCjtEvento ce;
    private ControladorRelaciones cr;

    public static void main(String[] args) {
        boolean imp = true;
        if(args.length > 0 && args[0].equals("0")) imp = false;

        DriverControladorCjtEvento dce = new DriverControladorCjtEvento();
        dce.cc = new ControladorCongreso();
        dce.ce = new ControladorCjtEvento();
        dce.cr = new ControladorRelaciones(dce.cc,dce.ce);
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            if(imp) dce.menu();
            String linea = ui.nextLine();
            String[] params = linea.split("\\s");
            try {
                op = Integer.parseInt(params[0]);
                switch (op) {
                    case 0:
                        if(params.length!=1) throw new Exception(E1);
                        dce.print(""+dce.ce.size());
                        break;
                    case 1:
                        if(params.length!=1) throw new Exception(E1);
                        dce.print("" + dce.ce.sizeB());
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
                        dce.ce.AgregarActoOficial(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 6:
                        if(params.length!=4) throw new Exception(E1);
                        dce.ce.AgregarActoNoOficial(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 7:
                        if(params.length!=2) throw new Exception(E1);
                        dce.ce.AgregarEventoRandom(Integer.parseInt(params[1]));
                        break;
                    case 8:
                        if(params.length!=1) throw new Exception(E1);
                        dce.print(dce.ce.ConsultarTodosEventos() + "");
                        break;
                    case 9:
                        if(params.length!=3) throw new Exception(E1);
                        dce.print(String.valueOf(dce.ce.ExisteEvento(params[1], params[2])));
                        break;
                    case 10:
                        if(params.length!=4) throw new Exception(E1);
                        dce.ce.EliminarEvento(params[1], params[2], Integer.parseInt(params[3]), dce.cr);
                        break;
                    case 11:
                        if(params.length!=1) throw new Exception(E1);
                        dce.ce.EliminarCjtEvento(dce.cr);
                        break;
                    case 12:
                        if(params.length!=5) throw new Exception(E1);
                        dce.ce.ModificarNombreEvento(params[1], params[2], params[3], Integer.parseInt(params[4]), dce.cr);
                        break;
                    case 13:
                        if(params.length!=5) throw new Exception(E1);
                        dce.ce.ModificarFechaEvento(params[1], params[2], params[3], Integer.parseInt(params[4]), dce.cr);
                        break;
                    case 14:
                        if(params.length!=5) throw new Exception(E1);
                        dce.ce.ModificarImpEvento(params[1], params[2], Integer.parseInt(params[3]), Integer.parseInt(params[4]));
                        break;
                    case 15:
                        if(params.length!=3) throw new Exception(E1);
                        dce.print(dce.ce.ConsultarEvento(params[1], params[2]).toString());
                        break;
                    case 16:
                        if(params.length!=2) throw new Exception(E1);
                        dce.ce.guardar(params[1]);
                        break;
                    case 17:
                        if(params.length!=2) throw new Exception(E1);
                        dce.ce.cargar(params[1], dce.cr);
                        break;
                    case 18:
                        if(params.length!=3) throw new Exception(E1);
                        dce.print(dce.ce.obtBloquePR(Integer.parseInt(params[1]), Integer.parseInt(params[2])));
                        break;
                    case 19:
                        if(params.length!=3) throw new Exception(E1);
                        dce.print(dce.ce.obtBloqueP(Integer.parseInt(params[1]), Integer.parseInt(params[2])));
                        break;
                    case 20:
                        if(params.length!=3) throw new Exception(E1);
                        dce.print(dce.ce.obtBloquePF(Integer.parseInt(params[1]), Integer.parseInt(params[2])));
                        break;
                    case 21:
                        if(params.length!=3) throw new Exception(E1);
                        dce.print(dce.ce.obtBloquePI(Integer.parseInt(params[1]), Integer.parseInt(params[2])));
                        break;
                    case 22:
                        if(params.length!=3) throw new Exception(E1);
                        dce.print(dce.ce.obtBloqueBN(Integer.parseInt(params[1]), Integer.parseInt(params[2])));
                        break;
                    case 23:
                        if(params.length!=2) throw new Exception(E1);
                        dce.ce.buscarBN(params[1]);
                        break;
                    case 24:
                        if(params.length!=2) throw new Exception(E1);
                        dce.ce.buscarBF(params[1]);
                        break;
                    case 25:
                        if(params.length!=2) throw new Exception(E1);
                        dce.ce.buscarBI(params[1]);
                        break;
                }
            }
            catch (Exception e) {
                dce.print(e.getMessage());
                op = -1;
            }
        }
        while(op != 26);
    }

    private void menu(){

        print("\nDRIVER DE CONTROLADOR CONJUNTO EVENTO");
        print("0 size()");
        print("1 sizeB()");
        print("2 AgregarVotacion(String nombre, String fecha, int importancia)");
        print("3 AgregarReunionPersonal(String nombre, String fecha, int importancia)");
        print("4 AgregarReunionProfesional(String nombre, String fecha, int importancia)");
        print("5 AgregarActoOficial(String nombre, String fecha, int importancia)");
        print("6 AgregarActoNoOficial(String nombre, String fecha, int importancia)");
        print("7 AgregarEventoRandom(int n)");
        print("8 ConsultarTodosEventos()");
        print("9 ExisteEvento(String nombre, String fecha)");
        print("10 EliminarEvento(String nombre, String fecha, int imp)");
        print("11 EliminarCjtEvento()");
        print("12 ModificarNombreEvento(String nomViejo, String fecha, String nomNuevo, int imp)");
        print("13 ModificarFechaEvento(String nombre, String fechaVieja, String fechaNueva, int imp)");
        print("14 ModificarImpEvento(String nombre, String fecha, int imp, int imp_nueva)");
        print("15 ConsultarEvento(String nombre, String fecha)");
        print("16 guardar(String ruta)");
        print("17 cargar(String ruta)");
        print("18 obtBloquePR(int bloque, int tam_bloque)");
        print("19 obtBloqueP(int bloque, int tamanio)");
        print("20 obtBloquePF(int bloque, int tamanio)");
        print("21 obtBloquePI(int bloque, int tamanio)");
        print("22 obtBloqueBN(int bloque, int tamanio)");
        print("23 buscarBN(String prefijo)");
        print("24 buscarBF(String prefijo)");
        print("25 buscarBI(String prefijo)");
        print("26 Salir\n");
    }

    private void print(String s){
        System.out.println(s);
    }

}
