package Domini;

/**
 * Created by bug on 2/05/15.
 */
public class KeyAlreadyExistsTST extends Exception {
    public KeyAlreadyExistsTST(String str) {
        super("Clave "+str+" ya existe");
    }
}
