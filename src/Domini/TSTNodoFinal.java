package Domini;

public class TSTNodoFinal<X> extends TSTNodo {
    public X valor;
    public TSTNodoFinal(X x) {
        super();
        valor = x;
    }
    public void modificar(X x) {
        valor = x;
    }
}
