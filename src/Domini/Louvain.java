package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Jose on 02/04/15.
 */
public class Louvain extends Algoritmo {

    private Grafo g;
    private int[] n2c;
    private double[] in, tot;

    public Louvain(){

    }

    public Grafo ejecutar_algoritmo(Grafo graf){
        g = new Grafo(graf);

        return g;
    }

    // METER ESTA FUNCIÓN EN GRAFO? (Por lo que dijo Rosa)
    private boolean primera_fase(){
        // Calcular modularidad grafo.
        // Mover nodos a la mejor comunidad. (1)
        // Elegir nodo (mejor en orden aleatorio).
        // Consultar comunidades adyacentes al nodo.
        // Quitar nodo de su comunidad.
        // Para cada comunidad comprobar la ganancia de modularidad al añadir el nodo.
        // Añadir el nodo a la comunidad con mayor ganancia.
        // Recalcular modularidad.
        // Repetir desde (1) mientras "modularidad nueva - modularidad antigua > minimo establecido"
        // y se haya movido algún nodo a otra comunidad en la iteración.
        // Devuelve true si se ha realizado alguna mejora.
        return true;
    }

    // METER ESTA FUNCIÓN EN GRAFO? (Por lo que dijo Rosa)
    private void segunda_fase(){
        // Crear nuevo grafo:
        // Comunidades pasan a ser nodos.
        // Los pesos de las aristas entre los nuevos nodos se calculan mediante la suma
        // de los pesos de las aristas entre nodos de las dos comunidades correspondientes.
        // Las aristas entre nodos de la misma comunidad generan bucles (aristas que relacionan
        // un nodo consigo mismo) para la comunidad en el nuevo grafo.
    }

    private double modularidad(){
        double mod = .0;
        /*
        double m2 = g.peso_total();
        for (int i=0; i<g.V(); ++i){
            if (tot[i] > 0)
                mod += in[i]/m2 - (tot[i]/m2)*(tot[i]/m2);
        }
        */
        return mod;
    }

    private double ganancia_modularidad(){
        double gmod = .0;
        return gmod;
    }

    private int[] com_adyacentes(){
        int[] com_ady = {};
        return com_ady;
    }

    private void insertar_nodo_comunidad(int nodo, int com){

    }

    private void quitar_nodo_comunidad(int nodo){

    }

    private void mostrar_hora(String s){
        Date d = new Date();
        s += ": " + d.toString();
        System.out.println(s);
    }
}
