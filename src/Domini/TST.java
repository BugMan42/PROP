package Domini;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//String key tiene que ser [a---z]+[A----Z]+[0---9]
// y no puede ser vacia
public class TST<X>  {

    //Clase

    //Atributos clase
    protected static char fin = '#'; // marca
    protected TSTNodoChar root;
    private int N;

    public TST() {
        root = null;
        N = 0;
    }
    //Eliminamos el tst mediante la desreferenciacion
    //Java se ocupa.
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
    //Se intenta clonar el objeto X
    public TST(TST t) throws Exception {
        N = t.N;
        root = (TSTNodoChar) clonar(t.root, root);
    }
    private TSTNodo clonar(TSTNodo t, TSTNodo c) throws Exception {
        if (t == null) return null;

        TSTNodoChar auxt = (TSTNodoChar) t;
        c =  new TSTNodoChar(auxt.valor);
        c.left = clonar(t.left, c.left);

        if (auxt.valor == fin) {
            TSTNodoFinal auxt2 = (TSTNodoFinal) t.middle;
            c.middle = new TSTNodoFinal(X_clone((X)auxt2.valor));
        }
        else c.middle = clonar(t.middle, c.middle);
        c.right = clonar(t.right, c.right);
        return c;
    }



//#######################################################################################
/**###################################INSERTAR##########################################*/
//#######################################################################################
    //Al insertar recorremos el arbol y vamos agregando nodo
    //en el caso que este ya la clave---> Excepcion
    public void insertar(String key, X x) throws Exception {
        root = (TSTNodoChar) insertar(root, key, x, 0);
    }
    //La creacion/recorrido del tst se hace mediante la comparacion de caracteres
    //es decir segun el caracter del nodo y el caracter de la string
    //Si es menor se recorre hacia la izquierda si no hacia la derecha
    //si es igual seguimos por el nodo del medio
    private TSTNodo insertar(TSTNodo t, String key, X x, int l) throws Exception {
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
            if (c == fin && tChar.valor == fin) throw new KeyAlreadyExistsTST(key);
            if (c < tChar.valor) t.left = insertar(t.left,key,x,l);
            else if (c > tChar.valor) t.right = insertar(t.right,key,x,l);
            else t.middle = insertar(t.middle,key,x,l+1);
        }
        return t;
    }


    public X insertarSinExc(String key, X x) {
        List<X> aux = new ArrayList<X>();
        root = (TSTNodoChar) insertarSinExc(root, key, x, 0,aux);
        if (aux.isEmpty()) return x;
        else return aux.get(0);

    }
    //La creacion/recorrido del tst se hace mediante la comparacion de caracteres
    //es decir segun el caracter del nodo y el caracter de la string
    //Si es menor se recorre hacia la izquierda si no hacia la derecha
    //si es igual seguimos por el nodo del medio
    private TSTNodo insertarSinExc(TSTNodo t, String key, X x, int l,List<X> v) {
        char c;
        if (l < key.length()) c = key.charAt(l);
        else c = fin;

        if (t == null) {
            t = new TSTNodoChar(c);
            if (c == fin) {
                t.middle = new TSTNodoFinal(x);
                ++N;
            }
            else t.middle = insertarSinExc(t.middle,key,x,l+1,v);
        }
        else {
            TSTNodoChar tChar = (TSTNodoChar) t;
            if (c == fin && tChar.valor == fin) {
                TSTNodoFinal F = (TSTNodoFinal)t.middle;
                //print("yesss");
                v.add((X)F.valor);
                return t;
            }
            if (c < tChar.valor) t.left = insertarSinExc(t.left, key, x, l,v);
            else if (c > tChar.valor) t.right = insertarSinExc(t.right, key, x, l,v);
            else t.middle = insertarSinExc(t.middle,key,x,l+1,v);
        }
        return t;
    }
//#######################################################################################
/**###################################OBTENER###########################################*/
//#######################################################################################


    public X obtener(String key) throws Exception{
        return obtener(root, key, 0);
    }
    // Se obtiene el objeto con clave key
    // si no esta ese lanza excepcion
    // el caso en que no esta es cuando llegamos a un nodo null
    private X obtener(TSTNodo t,String key,int d) throws Exception{
        if (t == null) throw new KeyNotExistsTST(key);

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (tChar.valor > c) return obtener(t.left, key, d);
        else if (tChar.valor < c) return obtener(t.right, key, d);
        else {
            if (d < key.length()) return obtener(t.middle, key, d + 1);
            else if (tChar.valor==c) {
                TSTNodoFinal f = (TSTNodoFinal) t.middle;
                return (X)f.valor;
            }
            else throw new KeyNotExistsTST(key);
        }
    }

//#######################################################################################
/**###############################CONTIENE/EXISTE######################################*/
//#######################################################################################

    public boolean existe(String key) {
        return existe(root, key, 0);
    }
    //se recorre el arbol formando la string key
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
    //Para borrar primero llegamos a el nodo con clave key
    // Si llegamos(existe) se elimina este nodo y se
    //borrar tantos nodos vacios como haya
    private TSTNodo borrar(TSTNodo t,TSTNodo padre,String key,int d) throws Exception {
        //si t == null esque no existe el objeto que estamos buscando
        if (t == null) throw new KeyNotExistsTST(key);

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;
        TSTNodoChar tChar = (TSTNodoChar) t;

        if (c < tChar.valor) {
            t.left =  borrar(t.left, t, key, d);
        }
        else if (c > tChar.valor ) {
            t.right = borrar(t.right, t, key, d);
        }
        else {
            if (d < key.length()) {
                t.middle = borrar(t.middle, t, key, d + 1);
            }
            else if (tChar.valor==fin) {
                //Segun el convenio de claves garantia
                // que se pueda hacer esta operacion
                t = t.right;
                --N;
            }
            else {
                throw new KeyNotExistsTST(key);
            }
        }
        //Eliminar cuando volvemos
        if (t != null && t.left == null && t.right == null && t.middle == null) {
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
        if (t == null) throw new KeyNotExistsTST(key);

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
                throw new KeyNotExistsTST(key);

            }
        }
        return t;
    }

//#######################################################################################
/**#############################__FALTA EFICIENCIA__#####################################*/
//#######################################################################################
    //Solo modificamos si la clave nueva es diferente a la vieja
    //Modificación comp --> Modificamos el key pero mantenemos el objeto
    public void modificar(String oldKey, String newKey) throws Exception {
        if (!(oldKey.equals(newKey))) {
            root = (TSTNodoChar) modificar(root, null, oldKey, newKey,0);
        }
    }
    //Modificación comp ---> Modificamos el key y canviamos el objeto
    public void modificar(String oldKey, String newKey, X x) throws Exception {
        if (!(oldKey.equals(newKey)))    {
            root = (TSTNodoChar) modificar(root, x, oldKey,newKey,0);
        }
    }

    //Modificación Compuesta falta hacer que si la clave vieja y
    //la clave nueva tienen un prefijo minimo no hace falta borrar
    //la clave y insertar, pudiendo reaprovecharel recorrido
    /** FALTA HACER EFICIENTE*/
    private void modificar1(TSTNodo t, String oldKey, X x, String newKey,int d) throws Exception {
        if (oldKey.charAt(0) != newKey.charAt(0)) {
            if (x == null) {
                x = obtener(oldKey);
            }
            if (!existe(oldKey)) throw new KeyNotExistsTST(oldKey);
            if (existe(newKey)) throw new KeyAlreadyExistsTST(newKey);
            print("size:"+ N);
            borrar(oldKey);
            print("size:"+ N);
            insertar(newKey,x);
            print("size:"+ N);
        }
        else {
            if (x == null) {
                x = obtener(oldKey);
            }
            if (!existe(oldKey)) throw new KeyNotExistsTST(oldKey);
            if (existe(newKey)) throw new KeyAlreadyExistsTST(newKey);
            borrar(oldKey);
            insertar(newKey,x);
        }
    }
    // a ---> for old key
    // b ---> for new key
    public void modComp(String oldK, String newK) throws Exception {
        root = (TSTNodoChar) modificar(root, null, oldK, newK, 0);
    }
    private TSTNodo modificar(TSTNodo t, X x,String oldKey, String newKey, int d) throws Exception {
        if (t == null) throw new KeyNotExistsTST(oldKey);

        char c1;
        if (d < oldKey.length()) c1 = oldKey.charAt(d);
        else c1 = fin;

        char c2;
        if (d < newKey.length()) c2 = newKey.charAt(d);
        else c2 = fin;

        //print("c1 " + c1 + " c2 " + c2);
        /*if (c1 == fin) {
            TSTNodoFinal aux2 = (TSTNodoFinal) t.middle;
            t = insertar(t, newKey, aux2.valor, d); // si esta petara
            t = t.right;
            //return t.right;
        }*/
        if (c1 == c2) { // yeah
            TSTNodoChar aux = (TSTNodoChar) t;
            if (c1 < aux.valor) t.left = modificar(t.left, x, oldKey, newKey,d);
            else if (c1 > aux.valor) t.right = modificar(t.right, x, oldKey, newKey,d);
            else { //c1 == aux.valor
                if (d < oldKey.length()) t.middle = modificar(t.middle, x, oldKey, newKey,d+1);
                else  print("wtf"); /*debug*/ //no hi ha else
            }
        }
        else {
            t = obtenerEspecial(t,t,x,oldKey,newKey,d,d);
        }
        return t;
    }
    private TSTNodo obtenerEspecial(TSTNodo r, TSTNodo t, X x,String key, String newKey, int d , int d2) throws Exception {
        if (t == null) throw new KeyNotExistsTST(key);

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (c < tChar.valor) t.left = obtenerEspecial(r, t.left, x, key, newKey, d, d2);
        else if (c > tChar.valor) t.right = obtenerEspecial(r, t.right, x, key, newKey, d, d2);
        else {
            if (d < key.length()) t.middle =  obtenerEspecial(r, t.middle, x, key, newKey, d + 1, d2);
            else if (c == tChar.valor) {
                TSTNodoFinal aux = (TSTNodoFinal) t.middle;
                if (x == null) x = (X)aux.valor;
                r = insertar(r, newKey,x,d2);
                t = t.right;
                --N;
            }
            else throw new KeyNotExistsTST(key);
        }
        if (t != null && t.left == null && t.right == null && t.middle == null) {
            t = null;
        }
        return t;
    }

    private static void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) throws Exception {
        TST<Integer> tst = new TST<Integer>();
        tst.insertar("abcd", 12);
        tst.insertar("abc",12);
        tst.insertar("b",12);
        tst.insertar("ab",12);
        print("a: "+tst.obtener("ab"));
        //tst.insertar("ab",13);
        //print(tst.consultarClaves()+"");
        //tst.modificar("ab", "a",14);
        //tst.insertar("ab",12);
        //TSTIterator a = new TSTIterator(tst);
        print(tst.consultarClaves() + "");
        //print("a: "+tst.obtener("a"));

    }

//###################################################################
/**###########################CONSULTAR#############################*/
//###################################################################
    public String toString() {
        ArrayList<String> v = new ArrayList<String>();
        imprimir(root, "", v);
        return v+"";
    }
    /*public String ConsultarClavesString() {
        ArrayList<String> v = new ArrayList<String>();
        imprimir(root, "",v);
        return "TST_CLAVES: "+v;
    }*/
    public ArrayList<String> consultarClaves() {
        ArrayList<String> v = new ArrayList<String>();
        imprimir(root, "", v);
        return v;
    }
    private void imprimir(TSTNodo r, String word, ArrayList<String> v) {
        if (r != null) {
            //hijo izquierdo
            imprimir(r.left, word,v);
            //Hijo del medio
            TSTNodoChar rChar = (TSTNodoChar)r;
            if (rChar.valor == fin) v.add(word);
            else imprimir(r.middle, word+rChar.valor,v);
            //hijo derecho
            imprimir(r.right, word, v);
        }
    }
    public List<String> clavesPrefijoConStop(String prefix, char stop, int threshold) {
        ArrayList<String> v = new ArrayList<String>();
        return imprimir0(root, prefix,0,stop,threshold);
    }
    private List<String> imprimir0(TSTNodo t,String key,int d,char stop,int threshold) {
        if (t == null) return null;

        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (tChar.valor > c) return imprimir0(t.left, key, d,stop,threshold);
        else if (tChar.valor < c) return imprimir0(t.right, key, d,stop,threshold);
        else {
            ++d;
            if (d < key.length()) return imprimir0(t.middle, key, d,stop,threshold);
            else {
                List<String> aux = new LinkedList<String>();
                imprimir3(t.middle, key, aux,stop,threshold);
                return aux;
            }
        }
    }
    private void imprimir3(TSTNodo r, String word, List<String> v,char stop,int threshold) {
        if (r != null && v.size() < threshold) {
            imprimir3(r.left, word, v, stop, threshold);
            TSTNodoChar rChar = (TSTNodoChar)r;
            if (rChar.valor == fin || rChar.valor == stop) v.add(word);
            else imprimir3(r.middle, word + rChar.valor, v,stop,threshold);
            imprimir3(r.right, word, v,stop,threshold);
        }
    }

    //Utilizamos una lista  para
    public List<X> consultarObjetos() {
        List<X> v = new ArrayList<X>();
        imprimir2(root, "", v);
        return v;
    }
    //Funcion muy semejante a imprimir solo que costruimos una
    //lista de X
    private void imprimir2(TSTNodo t, String word, List<X> v) {
        if (t != null) {
            imprimir2(t.left, word, v);
            TSTNodoChar rChar = (TSTNodoChar)t;
            if (rChar.valor == fin) {
                TSTNodoFinal f = (TSTNodoFinal) t.middle;
                v.add((X)f.valor);
            }
            else imprimir2(t.middle, word + rChar.valor, v);
            imprimir2(t.right, word, v);
        }
    }
    public List<X> prefijo(String key) {
        return obtenerPrefijo(root, key, 0);
    }

    private List<X> obtenerPrefijo(TSTNodo t,String key,int d) {
        if (t == null) return null;
        char c;
        if (d < key.length()) c = key.charAt(d);
        else c = fin;

        TSTNodoChar tChar = (TSTNodoChar) t;
        if (tChar.valor > c) return obtenerPrefijo(t.left, key, d);
        else if (tChar.valor < c) return obtenerPrefijo(t.right, key, d);
        else {
            ++d;
            if (d < key.length()) return obtenerPrefijo(t.middle, key, d);
            else {
                List<X> aux = new LinkedList<X>();
                imprimir2(t.middle,"",aux);
                return aux;
            }
        }
    }

    //funcion que recorre el "arbol del objeto" para ver si contiene la funcion clonar
    private X X_clone(X x) throws Exception {
        for (Class i : x.getClass().getInterfaces())
            if (i.getName().equals("java.lang.Cloneable")) //Nos aseguramos que tiene la interfaz cloneable
                for (Method m : x.getClass().getMethods())
                    if (m.getName().equals("clone") && // No aseguramos que tiene un clon() apropiado
                            m.getParameterTypes().length == 0 &&
                            m.getReturnType().getName().equals("java.lang.Object")) {
                        return (X) m.invoke(x); // Invocamos clone si no copia
                    }
        return x;
    }


}
