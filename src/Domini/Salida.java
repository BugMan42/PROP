package Domini;

import java.util.ArrayList;

/**
 * Created by jose on 15/04/15.
 */
public class Salida {

    private ArrayList<String> mensajes;

    public Salida()
    {
        mensajes = new ArrayList<String>();
    }


    public void agregarMensaje(String s)
    {
        mensajes.add(s);
    }

    public String mostrarMensaje()
    {
        int n = mensajes.size()-1;
        return mensajes.get(n);
    }

    public ArrayList<String> mostrarHistorial()
    {
        return mensajes;
    }

}
