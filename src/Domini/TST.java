package Domini;
import java.util.*;


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
        public void modificar(X x) {
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
        root = (TSTNodoChar) insertar(root, key, x, 0);

    }
    public void insertar(X x) {
        String k = x.toString();
        root = (TSTNodoChar) insertar(root,k,x,0);
    }
    private TSTNodo insertar(TSTNodo t, String key, X x, int l) {
        //if (key.length() == 0) throw new Exception("key vacia");
        char c;
        if (l < key.length()) c = key.charAt(l);
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
        return obtener(root,key,0);
    }
    public int size() {
        return quantity(root);
    }
    private int quantity(TSTNodo t) {
        if(t != null) {
            TSTNodoChar tChar = (TSTNodoChar)t;
            //System.out.print("letra: " + tChar.data + "\n");
            if(tChar.valor == '$') {
                int fl = quantity(t.left);
                int fr = quantity(t.right);
                return fl+fr+1;
            }
            else {
                int f1 = quantity(t.left);
                //System.out.print("f1: " + f1 + "\n");
                int f2 = quantity(t.middle);
                //System.out.print("f2: " + f2 + "\n");
                int f3 = quantity(t.right);
                //System.out.print("f3: " + f3 + "\n");
                return f1+f2+f3;
            }
        }
        return 0;
    }

    private X obtener(TSTNodo t,String key,int d) {
        if (t == null) return null;

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (tChar.valor > c) return obtener(t.left,key,d);
        else if (tChar.valor < c) return obtener(t.right,key,d);
        else {
            if (key.length() > d) return obtener(t.middle,key,d+1);
            else if (tChar.valor==c) {
                TSTNodoFinal f = (TSTNodoFinal) t.middle;
                return f.valor;
            }
            else return null;
        }
    }

    public boolean existe(String key) {
        return existe(root,key,0);
    }

    private boolean existe(TSTNodo t,String key,int d) {
        if (t == null) return false;

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (tChar.valor > c) return existe(t.left,key,d);
        else if (tChar.valor < c) return existe(t.right,key,d);
        else {
            if (key.length() > d) return existe(t.middle,key,d+1);
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
    public void eliminar(String key) {
        root = (TSTNodoChar) borrar(root, null, key, 0);

    }

    private TSTNodo borrar(TSTNodo t,TSTNodo padre,String key,int d) {
        if (t == null) {
            print("no esta");
            return null;
        }

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;
        //print(String.valueOf(c));

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (c < tChar.valor) t.left =  borrar(t.left,t,key,d);
        else if (c > tChar.valor ) t.right = borrar(t.right,t,key,d);
        else {
            if (d < key.length()) t.middle = borrar(t.middle,t,key,d+1);
            else if (tChar.valor==c) {
                //Eliminar
                print(String.valueOf(c));
                //TSTNodoFinal f = (TSTNodoFinal) t.middle;
                //f.modificar(x);
                //t.middle = f;
            }
            else {
                print("No esta");
                //NO esta//return null;
            }
        }
        return t;
    }
    //Modificación Simple
    public void modificar(String key, X x) {
        root = (TSTNodoChar) modificar(root,x,key,0);
    }
    public void modificar(X x) {
        String k = x.toString();
        root = (TSTNodoChar) modificar(root,null,x,k,0);
    }
    //Modificación comp --> Modificamos el key pero mantenemos el objeto
    public void modificar(String OldKey, String NewKey) {
        root = (TSTNodoChar) modificar(root,OldKey,null,NewKey,0);
    }
    //Modificación comp ---> Modificamos el key y canviamos el objeto
    public void modificar(String OldKey, String NewKey, X x) {
        root = (TSTNodoChar) modificar(root,NewKey,x,OldKey,0);
    }

    //Modificación Simple
    private TSTNodo modificar(TSTNodo t, X x,String key, int d) {
        if (t == null) {
            print("no esta");
            return null;
        }

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;
        //print(String.valueOf(c));

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (c < tChar.valor) t.left =  modificar(t.left,x,key,d);
        else if (c > tChar.valor ) t.right = modificar(t.right,x,key,d);
        else {
            if (d < key.length()) t.middle = modificar(t.middle,x,key,d+1);
            else if (tChar.valor==c) {
                TSTNodoFinal f = (TSTNodoFinal) t.middle;
                f.modificar(x);
                t.middle = f;
            }
            else {
                print("No esta");
                //NO esta//return null;
            }
        }
        return t;
    }
    //Modificación Compuesta
    private TSTNodo modificar(TSTNodo t, String OldKey, X x, String NewKey,int d) {
        return new TSTNodo();
    }
    private void print(String str) {
        System.out.println(str);
    }
}
    /*
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
