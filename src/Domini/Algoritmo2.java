package Domini;

public abstract class Algoritmo2 {

    // Punteros a las clases inicializadas en el Controlador del Algoritmo
    private Entrada2 in;
    private Salida2 obtOut;


    //Constructor de algoritmo con parámetros
    /*
    *   Nos permite utilizar una entrada y una salida previamente inicializadas
    */
    public Algoritmo2(Entrada2 i, Salida2 o) throws Exception {
        in = i;
        obtOut = o;
    }

    public Entrada2 obtIn() {
        return in;
    }

    public Salida2 obtOut() {
        return obtOut;
    }
}
