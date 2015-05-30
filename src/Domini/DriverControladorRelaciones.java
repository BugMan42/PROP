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
        boolean imp = true;
        if(args.length > 0 && args[0].equals("0")) imp = false;

        DriverControladorRelaciones dr = new DriverControladorRelaciones();
        dr.cc = new ControladorCongreso();
        dr.ce = new ControladorCjtEvento();
        dr.cr = new ControladorRelaciones(dr.cc,dr.ce);
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            if(imp) dr.menu();
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
                        if(params.length!=3) throw new Exception(E1);
                        dr.cc.modDniCongresista(params[1], params[2], dr.cr);
                        break;
                    case 3:
                        if(params.length!=9) throw new Exception(E1);
                        dr.cc.modCongresista(params[1], params[2], params[3], params[4], Integer.parseInt(params[5]),
                                params[6], params[7], params[8], dr.cr);
                        break;
                    case 4:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cc.eliminarCongresista(params[1], dr.cr);
                        break;
                    case 5:
                        if(params.length!=1) throw new Exception(E1);
                        dr.cc.eliminarCongreso(dr.cr);
                        break;
                    case 6:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cc.guardar(params[1]);
                        break;
                    case 7:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cc.cargar(params[1], dr.cr);
                        break;
                    case 8:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarVotacion(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 9:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarReunionPersonal(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 10:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarReunionProfesional(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 11:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarActoOficial(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 12:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.AgregarActoNoOficial(params[1], params[2], Integer.parseInt(params[3]));
                        break;
                    case 13:
                        if(params.length!=2) throw new Exception(E1);
                        dr.ce.AgregarEventoRandom(Integer.parseInt(params[1]));
                        break;
                    case 14:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(dr.ce.ConsultarTodosEventos().toString());
                        break;
                    case 15:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.ModificarNombreEvento(params[1], params[2], params[3], dr.cr);
                        break;
                    case 16:
                        if(params.length!=4) throw new Exception(E1);
                        dr.ce.ModificarFechaEvento(params[1], params[2], params[3], dr.cr);
                        break;
                    case 17:
                        if(params.length!=3) throw new Exception(E1);
                        dr.ce.EliminarEvento(params[1], params[2], dr.cr);
                        break;
                    case 18:
                        if(params.length!=1) throw new Exception(E1);
                        dr.ce.EliminarCjtEvento(dr.cr);
                        break;
                    case 19:
                        if(params.length!=2) throw new Exception(E1);
                        dr.ce.guardar(params[1]);
                        break;
                    case 20:
                        if(params.length!=2) throw new Exception(E1);
                        dr.ce.cargar(params[1], dr.cr);
                        break;
                    case 21:
                        if(params.length!=4) throw new Exception(E1);
                        dr.cr.agregarRelacion(params[1], params[2], params[3]);
                        break;
                    case 22:
                        if(params.length!=5) throw new Exception(E1);
                        dr.cr.agregarVoto(params[1], params[2], params[3], params[4]);
                        break;
                    case 23:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cr.agregarRelacionRandom(Integer.parseInt(params[1]));
                        break;
                    case 24:
                        if(params.length!=4) throw new Exception(E1);
                        dr.print(String.valueOf(dr.cr.existeRelacion(params[1], params[2], params[3])));
                        break;
                    case 25:
                        if(params.length!=5) throw new Exception(E1);
                        dr.print(String.valueOf(dr.cr.existeVoto(params[1], params[2], params[3], params[4])));
                        break;
                    case 26:
                        if(params.length!=2) throw new Exception(E1);
                        dr.print(String.valueOf(dr.cr.tieneRelaciones(params[1])));
                        break;
                    case 27:
                        if(params.length!=3) throw new Exception(E1);
                        dr.print(String.valueOf(dr.cr.tieneRelaciones(params[1], params[2])));
                        break;
                    case 28:
                        if(params.length!=4) throw new Exception(E1);
                        dr.cr.eliminarRelacion(params[1], params[2], params[3]);
                        break;
                    case 29:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cr.eliminarRelaciones(params[1]);
                        break;
                    case 30:
                        if(params.length!=3) throw new Exception(E1);
                        dr.cr.eliminarRelaciones(params[1], params[2]);
                        break;
                    case 31:
                        if(params.length!=1) throw new Exception(E1);
                        dr.cr.eliminarRelaciones();
                        break;
                    case 32:
                        if(params.length!=2) throw new Exception(E1);
                        dr.print(dr.cr.obtEventos(params[1]).toString());
                        break;
                    case 33:
                        if(params.length!=3) throw new Exception(E1);
                        dr.print(dr.cr.obtCongresistas(params[1], params[2]).toString());
                        break;
                    case 34:
                        if(params.length!=2) throw new Exception(E1);
                        dr.print(dr.cr.obtRelaciones(params[1]).toString());
                        break;
                    case 35:
                        if(params.length!=3) throw new Exception(E1);
                        dr.print(dr.cr.obtRelaciones(params[1], params[2]).toString());
                        break;
                    case 36:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(dr.cr.obtEventos().toString());
                        break;
                    case 37:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(dr.cr.obtCongresistas().toString());
                        break;
                    case 38:
                        if(params.length!=1) throw new Exception(E1);
                        dr.print(dr.cr.obtTodasLasRelaciones().toString());
                        break;
                    case 39:
                        if(params.length!=3) throw new Exception(E1);
                        dr.cr.modEvento(params[1], params[2]);
                        break;
                    case 40:
                        if(params.length!=3) throw new Exception(E1);
                        dr.cr.modCongresista(params[1], params[2]);
                        break;
                    case 41:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cr.guardar(params[1]);
                        break;
                    case 42:
                        if(params.length!=2) throw new Exception(E1);
                        dr.cr.cargar(params[1]);
                        break;
                    case 43:
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
        while(op != 44);
    }

    private void menu(){
        print("\nDRIVER DE CONTROLADOR RELACIONES");
        print("//////// Congreso /////////////////////////////");
        print("0 agregarCongresista(String dni, String nombre, String apellido, int edad, " +
                "String ciudad, String estado, String partido)");
        print("1 obtenerCongreso()");
        print("2 modDniCongresista(String dni, String dni_nuevo)");
        print("3 modCongresista(String dni, String dni_nuevo, String nombre, String apellido," +
                " int edad, String ciudad, String estado, String partido)");
        print("4 eliminarCongresista(String dni)");
        print("5 eliminarCongreso()");
        print("6 guardarCongreso(String ruta)");
        print("7 cargarCongreso(String ruta)");
        print("//////// Eventos //////////////////////////////");
        print("8 AgregarVotacion(String nombre, String fecha, int importancia)");
        print("9 AgregarReunionPersonal(String nombre, String fecha, int importancia)");
        print("10 AgregarReunionProfesional(String nombre, String fecha, int importancia)");
        print("11 AgregarActoOficial(String nombre, String fecha, int importancia)");
        print("12 AgregarActoNoOficial(String nombre, String fecha, int importancia)");
        print("13 AgregarEventoRandom(int n)");
        print("14 ConsultarTodosEventos()");
        print("15 ModificarNombreEvento(String nomViejo, String fecha, String nomNuevo)");
        print("16 ModificarFechaEvento(String nombre, String fechaVieja, String fechaNueva)");
        print("17 EliminarEvento(String nombre, String fecha)");
        print("18 EliminarCjtEvento()");
        print("19 guardarEventos(String ruta)");
        print("20 cargarEventos(String ruta)");
        print("//////// Relaciones ///////////////////////////");
        print("21 agregarRelacion(String dni, String nombre, String fecha)");
        print("22 agregarVoto(String dni, String nombre, String fecha, String voto)");
        print("23 agregarRelacionRandom(int n)");
        print("24 existeRelacion(String dni, String nombre, String fecha)");
        print("25 existeVoto(String dni, String nombre, String fecha, String voto)");
        print("26 tieneRelaciones(String dni)");
        print("27 tieneRelaciones(String nombre, String fecha)");
        print("28 eliminarRelacion(String dni, String nombre, String fecha)");
        print("29 eliminarRelaciones(String dni)");
        print("30 eliminarRelaciones(String nombre, String fecha)");
        print("31 eliminarRelaciones()");
        print("32 obtEventos(String dni)");
        print("33 obtCongresistas(String nombre, String fecha)");
        print("34 obtRelaciones(String dni)");
        print("35 obtRelaciones(String nombre, String fecha)");
        print("36 obtEventos()");
        print("37 obtCongresistas()");
        print("38 obtTodasLasRelaciones()");
        print("39 modEvento(String id, String new_id)");
        print("40 modCongresista(String id, String new_id)");
        print("41 guardarRelaciones(String ruta)");
        print("42 cargarRelaciones(String ruta)");
        print("43 prueba grafo");
        print("///////////////////////////////////////////////");

        print("44 Salir\n");
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