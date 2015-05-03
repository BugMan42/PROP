package Domini;

/**
 * Created by falc on 23/03/15.
 * Clase Algoritmo
 */
public abstract class Algoritmo {

    // Punteros a las clases inicializadas en el Controlador del Algoritmo
    protected Entrada in;
    protected Salida out;

    public Algoritmo() throws Exception {}

    public Algoritmo(Entrada i, Salida o) throws Exception {
        in = i;
        out = o;
    }

    public void ejecutar_algoritmo() throws Exception {
    };

    public void ejecutar_iteración(Grafo g) throws Exception {

    };

}
