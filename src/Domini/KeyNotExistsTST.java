package Domini;

/**
 * Created by bug on 2/05/15.
 */
public class KeyNotExistsTST extends Exception {
    public KeyNotExistsTST(String str) {
        super("Clave "+str+" no existe");
    }
}
