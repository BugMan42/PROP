package Domini;

public class TSTNodo {
    public TSTNodo left, middle, right;

    public TSTNodo() {
        left = null;
        middle = null;
        right = null;
    }
    public TSTNodo(TSTNodo aux) {
        if (aux.left != null) left = new TSTNodo(aux.left);
        if (aux.middle != null) middle = new TSTNodo(aux.middle);
        if (aux.right != null) right = new TSTNodo(aux.right);
    }
}
