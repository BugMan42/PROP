package Domini;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.ArrayList;


public class TST<X>  {

    private X X_clone(X x) throws Exception, InvocationTargetException {
        for (Class i : x.getClass().getInterfaces())
            if (i.getName().equals("java.lang.Cloneable")) // Be sure E implements Cloneable.
                for (Method m : x.getClass().getMethods())
                    if (m.getName().equals("clone") && // Be sure E has an appropriate clone(),
                            m.getParameterTypes().length == 0 && // takes no parameters, anx returns
                            m.getReturnType().getName().equals("java.lang.Object")) // type Object.
                        return (X) m.invoke(x); // Invoke x.clone(), typecast, and return it.
        return x;
    }


    //Nodos
    private class TSTNodo {
        TSTNodo left, middle, right;

        public TSTNodo() {
            left = null;
            middle = null;
            right = null;
        }
    }

    private class TSTNodoChar extends TSTNodo {
        char valor;
        public TSTNodoChar(char Valor) {
            super();
            valor = Valor;
        }
    }

    private class TSTNodoFinal extends TSTNodo {
        X valor;
        public TSTNodoFinal(X x) {
            super();
            valor = x;
        }
        public void modificar(X x) {
            valor = x;
        }
    }
//#######################################################################################
//#######################################################################################
//#######################################################################################

    //Atributos clase
    private static char fin = '#'; // marca
    private TSTNodoChar root;
    private int N;

    public TST() {
        root = null;
        N = 0;
    }
    public boolean esVacio() {
        return root == null;
    }
    public void vaciar() {
        N = 0;
        root = null;
    }
    public int size() {
        return N;
    }

//#######################################################################################
/**#################################CREADORA COPIADORA##################################*/
//#######################################################################################
    public TST(TST t) throws Exception {
        root = (TSTNodoChar) clonar(t.root, root);
    }
    private TSTNodo clonar(TSTNodo t, TSTNodo c) throws Exception {
        if (t == null) return null;
        TSTNodoChar auxt = (TSTNodoChar) t;
        c =  new TSTNodoChar(auxt.valor);
        c.left = clonar(t.left, c.left);
        if (auxt.valor == fin) {
            TSTNodoFinal auxt2 = (TSTNodoFinal) t.middle;
            c.middle = new TSTNodoFinal(X_clone(auxt2.valor));
        }
        else c.middle = clonar(t.middle, c.middle);
        c.right = clonar(t.right, c.right);
        return c;
    }



//#######################################################################################
/**###################################INSERTAR##########################################*/
//#######################################################################################

    public void insertar(String key, X x) throws Exception {
        root = (TSTNodoChar) insertar(root, key, x, 0);
    }

    private TSTNodo insertar(TSTNodo t, String key, X x, int l) throws Exception {
        //if (key.length() == 0) throw new Exception("key vacia");
        char c;
        if (l < key.length()) c = key.charAt(l);
        else c = fin;

        if (t == null) {
            t = new TSTNodoChar(c);
            if (c == fin) {
                t.middle = new TSTNodoFinal(x);
                ++N;
            }
            else t.middle = insertar(t.middle,key,x,l+1);
        }
        else {
            TSTNodoChar tChar = (TSTNodoChar) t;
            if (c == fin && tChar.valor == fin) throw new Exception("CLAVE REPETIDA");
            if (c < tChar.valor) t.left = insertar(t.left,key,x,l);
            else if (c > tChar.valor) t.right = insertar(t.right,key,x,l);
            else t.middle = insertar(t.middle,key,x,l+1);
        }
        return t;
    }
//#######################################################################################
/**###################################OBTENER###########################################*/
//#######################################################################################


    public X obtener(String key) throws Exception{
        return obtener(root, key, 0);
    }

    private X obtener(TSTNodo t,String key,int d) throws Exception{
        if (t == null) throw new Exception("no existe la clave");

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (tChar.valor > c) return obtener(t.left, key, d);
        else if (tChar.valor < c) return obtener(t.right, key, d);
        else {
            if (key.length() > d) return obtener(t.middle, key, d + 1);
            else if (tChar.valor==c) {
                TSTNodoFinal f = (TSTNodoFinal) t.middle;
                return f.valor;
            }
            else return null;
        }
    }

//#######################################################################################
/**###############################CONTIENE/EXISTE######################################*/
//#######################################################################################

    public boolean existe(String key) {
        return existe(root, key, 0);
    }

    private boolean existe(TSTNodo t,String key,int d) {
        if (t == null) return false;

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (tChar.valor > c) return existe(t.left, key, d);
        else if (tChar.valor < c) return existe(t.right, key, d);
        else {
            if (key.length() > d) return existe(t.middle, key, d + 1);
            else return tChar.valor==c;
        }
    }


//#######################################################################################
/**##################################BORRAR##############################################*/
//#######################################################################################

    public void borrar(String key) throws Exception {
        root = (TSTNodoChar) borrar(root, null, key, 0);

    }
    private TSTNodo borrar(TSTNodo t,TSTNodo padre,String key,int d) throws Exception {
        if (t == null) throw new Exception("no existe la clave");

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;
        TSTNodoChar tChar = (TSTNodoChar) t;
        //print(String.valueOf(tChar.valor));
        if (c < tChar.valor) {
            t.left =  borrar(t.left, t, key, d);
            //if (t.left == null) { }
        }
        else if (c > tChar.valor ) {
            t.right = borrar(t.right, t, key, d);
        }
        else {
            if (d < key.length()) {
                t.middle = borrar(t.middle, t, key, d + 1);
                //if (t.middle == null) print("null");
            }
            else if (tChar.valor==fin) {
                //FUNCION QUE ELIMINARA EL NODO Y ARREGLARA EL PANORAMA
                t = t.right;//  eliminarNodo(t);
                --N;
            }
            else {
                throw new Exception("no existe la clave");
            }
        }
        if (t != null && t.left == null && t.right == null && t.middle == null) {
            //print("null final");
            t = null;
        }
        return t;
    }

//#######################################################################################
/**#################################MODIFICAR############################################*/
//#######################################################################################
    //Modificación Simple
    public void modificar(String key, X x) throws Exception {
        root = (TSTNodoChar) modificar(root, x, key, 0);
    }
    //Modificación Simple
    private TSTNodo modificar(TSTNodo t, X x,String key, int d) throws Exception {
        if (t == null) throw new Exception("no existe la clave");

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (c < tChar.valor) t.left =  modificar(t.left, x, key, d);
        else if (c > tChar.valor ) t.right = modificar(t.right, x, key, d);
        else {
            if (d < key.length()) t.middle = modificar(t.middle, x, key, d + 1);
            else if (tChar.valor==c) {
                TSTNodoFinal f = (TSTNodoFinal) t.middle;
                f.modificar(x);
                t.middle = f;
            }
            else {
                throw new Exception("no existe la clave");

            }
        }
        return t;
    }
//#######################################################################################
/**#############################__FALTA EFICIENCIA__#####################################*/
//#######################################################################################

    //Modificación comp --> Modificamos el key pero mantenemos el objeto
    public void modificar(String oldKey, String newKey) throws Exception{
        //root = (TSTNodoChar) modificar(root,oldKey,null,newKey,0);
        modificar(root,oldKey,null,newKey,0);
    }
    //Modificación comp ---> Modificamos el key y canviamos el objeto
    public void modificar(String oldKey, String newKey, X x) throws Exception{
        //root = (TSTNodoChar) modificar(root,oldKey,x,newKey,0);
        modificar(root,oldKey,x,newKey,0);
    }

    //Modificación Compuesta
    /** FALTA HACER EFICIENTE*/
    private void modificar(TSTNodo t, String oldKey, X x, String newKey,int d) throws Exception{
        if (x == null) {
            x = obtener(oldKey);
        }
        if (existe(newKey)) throw new Exception("ya existe la clave");
        borrar(oldKey);
        insertar(newKey,x);
        //return t;
    }

    private void print(String str) {
        System.out.println(str);
    }

/**##################################################################
#############################CONSULTAR################################
#####################################################################*/
    public String toString() {
        ArrayList<String> v = new ArrayList<String>();
        imprimir(root, "", v);
        return v+"";
    }
    /*public String ConsultarClavesString() {
        ArrayList<String> v = new ArrayList<String>();
        imprimir(root, "",v);
        return "TST_KEYS: "+v;
    }*/
    public ArrayList<String> consultarClaves() {
        ArrayList<String> v = new ArrayList<String>();
        imprimir(root, "",v);
        return v;
    }
    private void imprimir(TSTNodo r, String word, ArrayList<String> v) {
        if (r != null) {
            imprimir(r.left, word,v);
            TSTNodoChar rChar = (TSTNodoChar)r;
            if (rChar.valor == fin) v.add(word);
            else imprimir(r.middle, word+rChar.valor,v);
            imprimir(r.right, word, v);
        }
    }

    public List<X> consultarObjetos() {
        List<X> v = new ArrayList<X>();
        imprimir2(root, "", v);
        List<X> aux = Collections.unmodifiableList(v);
        return aux;
    }

    private void imprimir2(TSTNodo t, String word, List<X> v) {
        if (t != null) {
            imprimir2(t.left, word, v);
            TSTNodoChar rChar = (TSTNodoChar)t;
            if (rChar.valor == fin) {
                TSTNodoFinal f = (TSTNodoFinal) t.middle;
                v.add(f.valor);
            }
            else imprimir2(t.middle, word+rChar.valor, v);
            imprimir2(t.right, word,v);
        }
    }



}
