package Domini;


import java.util.Scanner;
import java.util.Set;

/**
 * Created by jose on 2/04/15.
 */
public class DriverLouvainGrafoNuevo {
    // Mensajes excepciones.
    static final String E1 = "Número de parámetros incorrecto.";


    public static void main(String[] args) {
        boolean imp = true;
        if(args.length > 0 && args[0].equals("0")) imp = false;

        DriverLouvainGrafoNuevo dl = new DriverLouvainGrafoNuevo();
        GrafoNodoArista g = new GrafoNodoArista();
        Entrada2 in = new Entrada2(g,0.000001, 0.000001);
        Salida2 out = new Salida2();
        Scanner ui = new Scanner(System.in);
        int op;
        do {
            if(imp) dl.menu();
            String linea = ui.nextLine();
            String[] params = linea.split("\\s");
            try {
                op = Integer.parseInt(params[0]);
                switch (op) {
                    case 0:
                        if (params.length != 2) throw new Exception(E1);
                        //g.agregarVertice(params[1]);
                        break;
                    case 1:
                        if (params.length != 4) throw new Exception(E1);
                        //g.agregarArista(params[1],params[2],Double.parseDouble(params[3]));
                        break;
                    case 2:
                        if (params.length != 2) throw new Exception(E1);
                        in.modParam1(Double.parseDouble(params[1]));
                        break;
                    case 3:
                        if (params.length != 1) throw new Exception(E1);
                        dl.crear_grafo2(g);
                        LouvainGrafoNuevo l = new LouvainGrafoNuevo(in,out);
                        for (String a : out.mostrarHistorial()) dl.print(a);
                        for (int i=0; i<out.comunidad().size(); ++i) {
                            Set<Congresista> com = out.comunidad().get(i);
                            dl.print("Comunidad "+i);
                            for(Congresista c : com) dl.print(c.toString());
                        }
                        break;
                }
            } catch (Exception e) {
                dl.print(e.getMessage());
                op = -1;
            }
        }
        while (op != 4);
    }

    private void menu() {
        print("\nDRIVER DE LOUVAIN");
        print("0 agregarVertice(String v)");
        print("1 agregarArista(String origen, String fin, double peso)");
        print("2 fijarLimiteIncrementoModularidad(double l)");
        print("3 ejecutar_algoritmo()");
        print("4 Salir\n");
    }

    private void crear_grafo(GrafoNodoArista g) throws Exception {
        Dni dni1 = new Dni("00000001A");
        Dni dni2 = new Dni("00000002A");
        Dni dni3 = new Dni("00000003A");
        Dni dni4 = new Dni("00000004A");
        Dni dni5 = new Dni("00000005A");
        Dni dni6 = new Dni("00000006A");
        Congresista c1 = new Congresista(dni1,"a","a",22,"b","b","b");
        Congresista c2 = new Congresista(dni2,"a","a",22,"b","b","b");
        Congresista c3 = new Congresista(dni3,"a","a",22,"b","b","b");
        Congresista c4 = new Congresista(dni4,"a","a",22,"b","b","b");
        Congresista c5 = new Congresista(dni5,"a","a",22,"b","b","b");
        Congresista c6 = new Congresista(dni6,"a","a",22,"b","b","b");
        g.agregarVertice(c1);
        g.agregarVertice(c2);
        g.agregarVertice(c3);
        g.agregarVertice(c4);
        g.agregarVertice(c5);
        g.agregarVertice(c6);
        g.agregarArista(new Edge(c1, c2, 1));
        g.agregarArista(new Edge(c2,c1,1));
        g.agregarArista(new Edge(c2,c3,1));
        g.agregarArista(new Edge(c3,c2,1));
        g.agregarArista(new Edge(c3,c1,1));
        g.agregarArista(new Edge(c1,c3,1));
        g.agregarArista(new Edge(c3,c4,1));
        g.agregarArista(new Edge(c4,c3,1));
        g.agregarArista(new Edge(c4,c5,1));
        g.agregarArista(new Edge(c5,c4,1));
        g.agregarArista(new Edge(c5,c6,1));
        g.agregarArista(new Edge(c6,c5,1));
        g.agregarArista(new Edge(c6,c4,1));
        g.agregarArista(new Edge(c4,c6,1));
    }

    private void crear_grafo2(GrafoNodoArista g) throws Exception {
        Dni dni1 = new Dni("00000001A");
        Dni dni2 = new Dni("00000002A");
        Dni dni3 = new Dni("00000003A");
        Dni dni4 = new Dni("00000004A");
        Dni dni5 = new Dni("00000005A");
        Dni dni6 = new Dni("00000006A");
        Dni dni7 = new Dni("00000007A");
        Dni dni8 = new Dni("00000008A");
        Dni dni9 = new Dni("00000009A");
        Dni dni10 = new Dni("00000010A");
        Dni dni11 = new Dni("00000011A");
        Dni dni12 = new Dni("00000012A");
        Dni dni13 = new Dni("00000013A");
        Congresista c1 = new Congresista(dni1,"a","a",22,"b","b","b");
        Congresista c2 = new Congresista(dni2,"a","a",22,"b","b","b");
        Congresista c3 = new Congresista(dni3,"a","a",22,"b","b","b");
        Congresista c4 = new Congresista(dni4,"a","a",22,"b","b","b");
        Congresista c5 = new Congresista(dni5,"a","a",22,"b","b","b");
        Congresista c6 = new Congresista(dni6,"a","a",22,"b","b","b");
        Congresista c7 = new Congresista(dni7,"a","a",22,"b","b","b");
        Congresista c8 = new Congresista(dni8,"a","a",22,"b","b","b");
        Congresista c9 = new Congresista(dni9,"a","a",22,"b","b","b");
        Congresista c10 = new Congresista(dni10,"a","a",22,"b","b","b");
        Congresista c11 = new Congresista(dni11,"a","a",22,"b","b","b");
        Congresista c12 = new Congresista(dni12,"a","a",22,"b","b","b");
        Congresista c13 = new Congresista(dni13,"a","a",22,"b","b","b");
        g.agregarVertice(c1);
        g.agregarVertice(c2);
        g.agregarVertice(c3);
        g.agregarVertice(c4);
        g.agregarVertice(c5);
        g.agregarVertice(c6);
        g.agregarVertice(c7);
        g.agregarVertice(c8);
        g.agregarVertice(c9);
        g.agregarVertice(c10);
        g.agregarVertice(c11);
        g.agregarVertice(c12);
        g.agregarVertice(c13);
        g.agregarArista(new Edge(c1, c2, 1));
        g.agregarArista(new Edge(c2, c1, 1));
        g.agregarArista(new Edge(c2, c3, 1));
        g.agregarArista(new Edge(c3, c2, 1));
        g.agregarArista(new Edge(c3,c1,1));
        g.agregarArista(new Edge(c1,c3,1));
        g.agregarArista(new Edge(c4,c5,1));
        g.agregarArista(new Edge(c5,c4,1));
        g.agregarArista(new Edge(c5,c6,1));
        g.agregarArista(new Edge(c6,c5,1));
        g.agregarArista(new Edge(c6,c4,1));
        g.agregarArista(new Edge(c4,c6,1));
        g.agregarArista(new Edge(c7,c8,1));
        g.agregarArista(new Edge(c8,c7,1));
        g.agregarArista(new Edge(c8, c9, 1));
        g.agregarArista(new Edge(c9,c8,1));
        g.agregarArista(new Edge(c9,c7,1));
        g.agregarArista(new Edge(c7,c9,1));
        g.agregarArista(new Edge(c10,c11,1));
        g.agregarArista(new Edge(c11,c10,1));
        g.agregarArista(new Edge(c11,c12,1));
        g.agregarArista(new Edge(c12,c11,1));
        g.agregarArista(new Edge(c12,c10,1));
        g.agregarArista(new Edge(c10,c12,1));
        g.agregarArista(new Edge(c1,c13,1));
        g.agregarArista(new Edge(c13,c1,1));
        g.agregarArista(new Edge(c4,c13,1));
        g.agregarArista(new Edge(c13,c4,1));
        g.agregarArista(new Edge(c7,c13,1));
        g.agregarArista(new Edge(c13,c7,1));
        g.agregarArista(new Edge(c10,c13,1));
        g.agregarArista(new Edge(c13,c10,1));
    }

    private void print(String s) {
        System.out.println(s);
    }

}
