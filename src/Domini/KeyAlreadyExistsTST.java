package Domini;

public class KeyAlreadyExistsTST extends Exception {
    public KeyAlreadyExistsTST(String str) {
        super("Clave "+str+" ya existe");
    }
}
