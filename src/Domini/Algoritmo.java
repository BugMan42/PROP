package Domini;

/**
 * Created by falc on 23/03/15.
 * Clase Algoritmo
 */
public abstract class Algoritmo {

    // Punteros a las clases inicializadas en el Controlador del Algoritmo
    protected Entrada in;
    protected Salida out;

    public Algoritmo() {}

    public Algoritmo(Entrada i, Salida o) {
        in = i;
        out = o;
    }

    public Grafo ejecutar_algoritmo() {

         //PD: No
        return in.obtGrafo();
    };

    public Grafo ejecutar_iteración(Grafo g) {
        return g; //PD: No.
    };

}
