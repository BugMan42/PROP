package Domini;

public class TSTNodoChar extends TSTNodo {
    char valor;
    public TSTNodoChar(TSTNodoChar aux) {
        valor = aux.valor;
        left = aux.left;
        middle = aux.middle;
        right = aux.right;
    }
    public TSTNodoChar(char Valor) {
        super();
        valor = Valor;
    }

}
