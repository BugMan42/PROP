package Domini;

/**
 * Created by falc on 14/04/15.
 */
public class TST<X>{

    private TST<X> low = null;
    private TST<X> equal = null;
    private TST<X> high = null;

    private char key = '\0';
    private X objeto = null;

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

}
