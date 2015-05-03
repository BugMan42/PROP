package Domini;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by usuario on 28/04/2015.
 */
public class DriverClique {
    /*final static String menu = "Bienvenido al driver de evento";
    final static String opcion1 = "1. Alta de un evento";
    final static String opcion2 = "2. Modificacion de un evento";
    final static String opcion3 = "3. Consulta de un evento";
    final static String opcion4 = "4. Ayuda";
    final static String opcion5 = "5. Salir";
    final static String error = "Introduzca un numero del 1 al 5";
    final static String nueva = "Introduzca una nueva opcion del 1 al 4. 5 para salir";
    final static String fin = "Gracias por usar este driver. THE END";*/
    private static Clique c;
    private static Entrada en;
    private static Salida sa;


    public static void main(String[] args) throws Exception {
        Grafo g = crearGrafo();
        ArrayList<Integer> gv = g.consultarVertices();
        for (int i = 0; i < gv.size(); ++i) {
            List<Integer> ady = g.nodosSalida(gv.get(i));
            for (int j = 0; j < ady.size(); ++j) System.out.println("nodo "+Integer.toString(i)+" adyacente a "+Integer.toString(ady.get(j)));
        }
        en = new Entrada(g, 4);
        sa = new Salida();
        c = new Clique(en, sa);
        c.ejecutar_algoritmo();
        ArrayList<String> hist = sa.mostrarHistorial();
        for (String aHist : hist) {
            System.out.println(aHist);
        }
        for (Set<Integer> i : sa.comunidad()) {
            System.out.println("Comunidad");
            for (Integer j : i) {
                System.out.println(Integer.toString(j));
            }
        }
    }
        /*String op = entrada.next();
        while (!numero(op)) {
            System.out.println("Tiene que ser un numero");
            op = entrada.next();
        }*/
            /*int opcion = Integer.parseInt(entrada.next());
            while (opcion != 5) {
                switch (opcion) {
                    case 1:
                        alta(entrada);
                        break;
                    /*case 2:
                        modificar(entrada);
                        break;
                    case 3:
                        consulta(entrada);
                        break;
                    case 4:
                    ayuda(entrada);
                    break;
                    default:
                        System.out.println(error);
                }
                System.out.println(nueva);
                opcion = entrada.nextInt();
            }
            System.out.println(fin);
        }*/

    private static Grafo crearGrafo() throws Exception {
        Grafo g = new Grafo();
        g.agregarVertice("1");
        g.agregarVertice("2");
        g.agregarVertice("3");
        g.agregarVertice("4");
        g.agregarVertice("5");
        g.agregarVertice("6");
        g.agregarVertice("7");

        /*g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("4", "5", 1);
        g.agregarArista("4", "6", 1);
        g.agregarArista("5", "6", 1);
        g.agregarArista("6", "7", 1);

        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);
        g.agregarArista("4", "3", 1);
        g.agregarArista("5", "4", 1);
        g.agregarArista("6", "4", 1);
        g.agregarArista("6", "5", 1);
        g.agregarArista("7", "6", 1);*/

        /*g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "5", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("3", "5", 1);
        g.agregarArista("4", "5", 1);

        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("5", "2", 1);
        g.agregarArista("4", "3", 1);
        g.agregarArista("5", "3", 1);
        g.agregarArista("5", "4", 1);*/

        /*g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);
        g.agregarArista("3", "4", 1);

        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);
        g.agregarArista("4", "3", 1);*/

        g.agregarArista("1", "2", 1);
        g.agregarArista("1", "3", 1);
        g.agregarArista("1", "4", 1);
        g.agregarArista("1", "5", 1);
        g.agregarArista("2", "3", 1);
        g.agregarArista("2", "4", 1);
        g.agregarArista("2", "5", 1);
        g.agregarArista("3", "4", 1);
        g.agregarArista("3", "5", 1);
        g.agregarArista("4", "5", 1);

        g.agregarArista("2", "1", 1);
        g.agregarArista("3", "1", 1);
        g.agregarArista("4", "1", 1);
        g.agregarArista("5", "1", 1);
        g.agregarArista("3", "2", 1);
        g.agregarArista("4", "2", 1);
        g.agregarArista("5", "2", 1);
        g.agregarArista("4", "3", 1);
        g.agregarArista("5", "3", 1);
        g.agregarArista("5", "4", 1);
        return g;
    }

        /*private static void ImprimirMenu() {
            System.out.println(menu);
            System.out.println(opcion1);
            System.out.println(opcion2);
            System.out.println(opcion3);
            System.out.println(opcion4);
            System.out.println(opcion5);
        }

        private static void alta(Scanner entrada) {
        /*System.out.println("Bienvenido a alta de un evento");
        System.out.println("Para dar de alta un evento se tienen que introducir");
        System.out.println("El tipo, nombre, fecha, subtipo e importancia");
        System.out.println("Introduzca el tipo del evento(Votacion, acto, reunion");*/
        /*while (!tipo.equals("Votacion") && !tipo.equals("Acto") && !tipo.equals("Reunion")) {
            System.out.println("Tiene que ser o Votacion o Acto o Reunion");
            tipo = entrada.next();
        }
            System.out.println("Introduzca el nombre del evento");
            String nombre = entrada.next();
            System.out.println("Introduzca la fecha del evento en formato dd/MM/yyyy");
            String data = entrada.next();
            //System.out.println("Introduzca el subtipo del evento");
            //String subtipo = entrada.next();
            System.out.println("Introduzca la importancia del evento");
            int importance = entrada.nextInt();
            try {
                e = new Evento1(nombre, data, importance);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            switch (Integer.parseInt(e.getMessage())) {
                case 1:
                    System.out.println("Nombre no puede ser vacÃ­o");
                    break;
                case 2:
                    System.out.println("Fecha no puede ser null");
                    break;
                case 3:
                    System.out.println("Subtipo no puede ser vacio");
                    break;
                case 4:
                    System.out.println("Importancia tiene que ser mayor que 0");
                    break;
                case 5:
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    break;
            }
            }*/

        }

    /*private static void baja(Scanner entrada) {
        System.out.println("Bienvenido a baja de un evento");
        try {
            e = null;
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            switch (Integer.parseInt(e.getMessage())) {
                case 1:
                    System.out.println("Nombre no puede ser vacio");
                    break;
                case 2:
                    System.out.println("Fecha no puede ser null");
                    break;
                case 3:
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    break;
            }
        }
    }*/

        /*private static void modificar(Scanner entrada) {
            System.out.println("Bienvenido a modificacion de un evento");
            System.out.println("Elige el atributo que deseas modificar");
            System.out.println("1-Nombre, 2-fecha, 3-importancia, 4- salir");
            int opcion = Integer.parseInt(entrada.next());
            try {
                while (opcion != 4) {
                    switch (opcion) {
                        case 1:
                            System.out.println("Introduzca el nuevo nombre");
                            e.ModNombre(entrada.next());
                            break;
                        case 2:
                            System.out.println("Introduzca la nueva fecha");
                            e.ModFecha(entrada.next());
                            break;
                        case 3:
                            System.out.println("Introduzca la nueva importancia");
                            e.ModImportancia(Integer.parseInt(entrada.next()));
                            break;
                        default:
                            System.out.println("Introduzca un numero del 1 al 4");
                            break;
                    }
                    System.out.println("Introduzca una nueva opcion del 1 al 3. 4 para salir");
                    opcion = entrada.nextInt();
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            /*switch (Integer.parseInt(e.getMessage())) {
                case 1:
                    System.out.println("Nombre no puede ser vacio");
                    break;
                case 2:
                    System.out.println("Fecha no puede ser null");
                    break;
                case 3:
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    break;
            }
            }
        }*/

        /*private static void consulta(Scanner entrada) {
            System.out.println("Bienvenido a consulta de un evento");
            try {
                System.out.println(e.obt_nombre() + " " + e.obt_fecha() + " " + Integer.toString(e.obt_importancia()));
            }
            catch (Exception e) {
                System.out.println("El evento no existe");
            }
        }
    }

}*/
