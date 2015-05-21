package Domini;


public class KeyNotExistsTST extends Exception {
    public KeyNotExistsTST(String str) {
        super("Clave "+str+" no existe");
    }
}
