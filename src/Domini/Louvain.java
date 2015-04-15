package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Jose on 02/04/15.
 */
public class Louvain extends Algoritmo {

    private double[] peso_ady;
    private int[] pos_ady;
    private int ultimo_ady;
    private Grafo gc;
    private int tam;
    private int[] nc;
    private double[] in,tot;
    private int pasos;
    private double min_modularidad;

    public Louvain(){

    }

    private boolean one_level(){
        boolean improvement = true;
        return improvement;
    }

    public Grafo ejecutar_algoritmo(Grafo g){
        Date begin, end;
        begin = new Date();
        display_time("Begin");

        // -----------------------------
        gc = new Grafo(g);
        tam = gc.V();

        peso_ady = new double[tam];
        Arrays.fill(peso_ady, -1);
        pos_ady = new int[tam];
        ultimo_ady = 0;

        nc = new int[tam];
        in = new double[tam];
        tot = new double[tam];

        for(int i=0; i<tam; ++i){
            nc[i] = i;

        }

        // -----------------------------

        Grafo gf = new Grafo();//no me la lieis, cuidado que esto no estara, esta constructuras se eliminara :)
        boolean improvement = true;
        double mod, new_mod;
        int level=0;

        return gf;
    }

    private void display_time(String s){
        Date d = new Date();
        s += ": " + d.toString();
        System.out.println(s);
    }
}
