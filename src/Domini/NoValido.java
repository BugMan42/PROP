package Domini;

/**
 * Created by usuario on 21/04/2015.
 */
public class NoValido extends Exception {
    public NoValido() {
        super();
    }
    public NoValido(String s) {
        super(s + " no es valido/a");
    }
}
