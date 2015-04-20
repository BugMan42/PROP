package Domini;

/**
 * Created by falc on 23/03/15.
 * Clase Algoritmo
 */
public abstract class Algoritmo {

    public Algoritmo() {}

    public Grafo ejecutar_algoritmo(Entrada in, Salida out) {

         //PD: No
        return in.obtGrafo();
    };

    public Grafo ejecutar_iteración(Grafo g) {
        return g; //PD: No.
    };

}
