package Domini;
import java.util.*;

/**
 * Created by falc on 14/04/15.
 */
public class TST<X> {
    //Nodos
    class TSTNodo {
        TSTNodo left, middle, right;

        public TSTNodo() {
            left = null;
            middle = null;
            right = null;
        }
    }

    class TSTNodoChar extends TSTNodo {
        char valor;
        public TSTNodoChar(char Valor) {
            super();
            valor = Valor;
        }
    }

    class TSTNodoFinal extends TSTNodo {
        X valor;
        public TSTNodoFinal(X x) {
            super();
            valor = x;
        }
        public void mod(X x) {
            valor = x;
        }
        //Puede faltar una modificadora ---> ya veremos
    }

    //Atributos clase
    private static char fin = '#'; // marca
    private TSTNodoChar root;
    private int N;

    public TST() {
        root = null;
    }

    public void insertar(String key, X x) {
        root = (TSTNodoChar) insertar(root, key.toCharArray(), x, 0);

    }
    public void insertar(X x) {
        String k = x.toString();
        root = (TSTNodoChar) insertar(root,k.toCharArray(),x,0);
    }
    private TSTNodo insertar(TSTNodo t, char[] key, X x, int l) {
        //if (key.length() == 0) throw new Exception("key vacia");
        char c;
        if (l < key.length) c = key[l];
        else c = fin;

        if (t == null) {
            t = new TSTNodoChar(c);
            if (c == fin) t.middle = new TSTNodoFinal(x);
            else t.middle = insertar(t.middle,key,x,l+1);
        }
        else {
            TSTNodoChar tChar = (TSTNodoChar) t;
            if (tChar.valor > c) t.left = insertar(t.left,key,x,l);
            else if (tChar.valor < c) t.right = insertar(t.right,key,x,l);
            else t.middle = insertar(t.middle,key,x,l+1);
        }
        return t;
    }

    public X obtener(String key) {
        return obtener(root,key.toCharArray(),0);
    }

    private X obtener(TSTNodo t,char[] key,int d) {
        if (t == null) return null;

        char c;
        if (d < key.length) c = key[d];
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (tChar.valor > c) return obtener(t.left,key,d);
        else if (tChar.valor < c) return obtener(t.right,key,d);
        else {
            if (key.length > d) return obtener(t.middle,key,d+1);
            else if (tChar.valor==c) {
                TSTNodoFinal f = (TSTNodoFinal) t.middle;
                return f.valor;
            }
            else return null;
        }
    }

    public boolean existe(String key) {
        return existe(root,key.toCharArray(),0);
    }
    private boolean existe(TSTNodo t,char[] key,int d) {
        if (t == null) return false;

        char c;
        if (d < key.length) c = key[d];
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (tChar.valor > c) return existe(t.left,key,d);
        else if (tChar.valor < c) return existe(t.right,key,d);
        else {
            if (key.length > d) return existe(t.middle,key,d+1);
            else return tChar.valor==c;
        }
    }
    void print() {
        print(root);
    }
    void print(TSTNodoChar t) {
        if (t.valor != fin) {
            System.out.println(t.valor);
            print((TSTNodoChar)t.middle);
        }
    }
    private void print(String str) {
        System.out.println(str);
    }

}
    /*
    private char key = '\0';
    private X objeto = null;


    private TST<X> low = null;
    private TST<X> equal = null;
    private TST<X> high = null;

    public TST()
    {

    }

    public void insertar(String k, X o)
    {
        char c_k = k.charAt(0);
        String aux_k = k.substring(1);

        if (key == '\0') //Si el carácter es nulo...
        {
            key = c_k;
            if (aux_k.equals("")) //Si no quedan más letras...
            {
                objeto = o;
                //System.out.println("Valor insertado en "+key);
                return;
            }
            else
            {
                equal = new TST<X>();
                equal.insertar(aux_k, o);
            }
        }
        else if (c_k < key)
        {
            if (low == null) low = new TST<X>();
            low.insertar(k, o);
        }
        else if (c_k == key)
        {
            if (equal == null) equal = new TST<X>();
            equal.insertar(aux_k, o);
        }
        else if (c_k > key)
        {
            if (high == null) high = new TST<X>();
            high.insertar(k, o);
        }
    }

    public X obtObjeto(String k)
    {
        X aux = null;
        char c_k = k.charAt(0);
        String aux_k = k.substring(1);
        //System.out.println("Nodo: "+key+", "+c_k+", "+aux_k);
        //if (aux_k.equals("")) System.out.println("aux_k is empty");

        if (c_k < key && low != null) aux = low.obtObjeto(k);
        else if (c_k > key && high != null) aux = high.obtObjeto(k);
        else if (c_k == key)
        {
            if (aux_k.equals("")) return objeto;
            else if (equal != null) aux = equal.obtObjeto(aux_k);
        }

        return aux;
    }

    public void borrar(String k)
    {
        char c_k = k.charAt(0);
        String aux_k = k.substring(1);
        if (c_k < key && low != null) low.borrar(k);
        else if (c_k > key && high != null) high.borrar(k);
        else if (c_k == key)
        {
            if (aux_k.equals(""))
            {
                objeto = null;
                return;
            }
            else if (equal != null) equal.borrar(aux_k);
        }
        else return;

        assert equal != null;
        if (equal.objeto == null && equal.equal == null && equal.low == null && equal.high == null)
        {
            equal = null;
        }

    }

}*/
