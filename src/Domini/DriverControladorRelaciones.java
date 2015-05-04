package Domini;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by jose on 3/05/15.
 */
public class DriverControladorRelaciones {
    // Mensajes excepciones.
    private static final String E1 = "Número de parámetros incorrecto.";

    private ControladorCongreso cc;
    private ControladorCjtEvento ce;
    private ControladorRelaciones cr;

    public static void main(String[] args) {
        DriverControladorRelaciones dr = new DriverControladorRelaciones();
        dr.cc = new ControladorCongreso();
        dr.ce = new ControladorCjtEvento();
        dr.cr = new ControladorRelaciones(dr.cc,dr.ce);
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
                        dr.cc.agregarCongresista(params[1], params[2], params[3], Integer.parseInt(params[4]), params[5],
                                params[6], params[7]);
                        break;
                    case 1:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(dr.cc.obtenerCongreso().toString());
                        break;
                    case 2:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cc.eliminarCongresista(params[1]);
                        break;
                    case 3:
                        if(params.length!=1) throw new Exception(E1);
                        dr.cc.eliminarCongreso();
                        break;
                    case 4:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cc.guardar(params[1]);
                        break;
                    case 5:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cc.cargar(params[1]);
                        break;
                    case 6:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarVotacion(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 7:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarReunionPersonal(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 8:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarReunionProfesional(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 9:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarActoOficial(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 10:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarActoNoOficial(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 11:
                        if(params.length!=1) throw new Exception(E1);
                        dr.ce.AgregarEventoRandom();
                        break;
                    case 12:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(dr.ce.ConsultarTodosEventos().toString());
                        break;
                    case 13:
                        if(params.length!=3) throw new Exception(E1);
                        dr.ce.EliminarEvento(params[1], params[2]);
                        break;
                    case 14:
                        if(params.length!=1) throw new Exception(E1);
                        dr.ce.EliminarCjtEvento();
                        break;
                    case 15:
                        if(params.length!=2) throw new Exception(E1);
                        dr.ce.guardar(params[1]);
                        break;
                    case 16:
                        if(params.length!=2) throw new Exception(E1);
                        dr.ce.cargar(params[1]);
                        break;
                    case 17:
                        if(params.length!=4) throw new Exception(E1);
                        dr.cr.agregarRelacion(params[1], params[2], params[3]);
                        break;
                    case 18:
                        if(params.length!=5) throw new Exception(E1);
                        dr.cr.agregarVoto(params[1], params[2], params[3], params[4]);
                        break;
                    case 19:
                        if(params.length!=1) throw new Exception(E1);
                        dr.cr.agregarRelacionRandom();
                        break;
                    case 20:
                        if(params.length!=4) throw new Exception(E1);
                        dr.cr.eliminarRelacion(params[1], params[2], params[3]);
                        break;
                    case 21:
                        if(params.length!=1) throw new Exception(E1);
                        dr.cr.eliminarRelaciones();
                        break;
                    case 22:
                        if(params.length!=2) throw new Exception(E1);
                        dr.print(dr.cr.obtEventos(params[1]).toString());
                        break;
                    case 23:
                        if(params.length!=3) throw new Exception(E1);
                        dr.print(dr.cr.obtCongresistas(params[1], params[2]).toString());
                        break;
                    case 24:
                        if(params.length!=2) throw new Exception(E1);
                        dr.print(dr.cr.obtRelaciones(params[1]).toString());
                        break;
                    case 25:
                        if(params.length!=3) throw new Exception(E1);
                        dr.print(dr.cr.obtRelaciones(params[1],params[2]).toString());
                        break;
                    case 26:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(dr.cr.obtCongresistas().toString());
                        break;
                    case 27:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(dr.cr.obtTodasLasRelaciones().toString());
                        break;
                    case 28:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cr.guardar(params[1]);
                        break;
                    case 29:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cr.cargar(params[1]);
                        break;
                    case 30:
                        if(params.length!=1) throw new Exception(E1);
                        dr.prueba_grafo();
                        break;
                }
            }
            catch (Exception e) {
                dr.print(e.getMessage());
                op = -1;
            }
        }
        while(op != 31);
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
        print("9 AgregarActoOficial(String nombre, String fecha, int importancia)");
        print("10 AgregarActoNoOficial(String nombre, String fecha, int importancia)");
        print("11 AgregarEventoRandom()");
        print("12 ConsultarTodosEventos()");
        print("13 EliminarEvento(String nombre, String fecha)");
        print("14 EliminarCjtEvento()");
        print("15 guardarEventos(String ruta)");
        print("16 cargarEventos(String ruta)");
        print("//////// Relaciones ///////////////////////////");
        print("17 agregarRelacion(String dni, String nombre, String fecha)");
        print("18 agregarVoto(String dni, String nombre, String fecha, String voto)");
        print("19 agregarRelacionRandom()");
        print("20 eliminarRelacion(String dni, String nombre, String fecha)");
        print("21 eliminarRelaciones()");
        print("22 obtEventos(String dni)");
        print("23 obtCongresistas(String nombre, String fecha)");
        print("24 obtRelaciones(String dni)");
        print("25 obtRelaciones(String nombre, String fecha)");
        print("26 obtCongresistas()");
        print("27 obtTodasLasRelaciones()");
        print("28 guardarRelaciones(String ruta)");
        print("29 cargarRelaciones(String ruta)");
        print("30 prueba grafo");
        print("///////////////////////////////////////////////");
        print("31 Salir\n");
    }

    private void print(String s){
        System.out.println(s);
    }

    private void prueba_grafo() throws Exception {
        cr.crearGrafoRelaciones();
        Grafo g = cr.crearGrafoAlgoritmo();
        Entrada in = new Entrada(g,0.000001);
        Salida out = new Salida();
        Louvain l = new Louvain(in, out);
        l.ejecutar_algoritmo();
        //print(out.mostrarHistorial().toString());
        print(out.comunidad().toString());
        ArrayList<Set<String>> dnicom = new ArrayList<Set<String>>();
        for(Set<Integer> si : out.comunidad()){
            Set<String> sdni = new HashSet<String>();
            for(Integer x : si) sdni.add(g.fPrima(x));
            dnicom.add(sdni);
        }
        print(dnicom.toString());
    }

}