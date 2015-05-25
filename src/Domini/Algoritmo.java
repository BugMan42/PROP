package Domini;

/**
 * Created by falc on 23/03/15.
 * Clase Algoritmo
 */
public abstract class Algoritmo {

    // Punteros a las clases inicializadas en el Controlador del Algoritmo
    private Entrada in;
    private Salida obtOut;


    //Constructor de algoritmo con parámetros
    /*
    *   Nos permite utilizar una entrada y una salida previamente inicializadas
    */
    public Algoritmo(Entrada i, Salida o) throws Exception {
        in = i;
        obtOut = o;
    }


    public Entrada obtIn() {
        return in;
    }

    public Salida obtOut() {
        return obtOut;
    }
}
