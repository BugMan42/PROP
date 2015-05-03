package Domini;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jose on 15/04/15.
 */
public class Salida {

    private ArrayList<String> mensajes;
    private ArrayList<Set<Integer>> comunidades;

    public Salida()
    {
        mensajes = new ArrayList<String>();
        comunidades = new ArrayList<Set<Integer>>();
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
    public void agregarComunidad(Set<Integer> s) {
        comunidades.add(s);
    }
    public ArrayList<Set<Integer>> comunidad() {
        return comunidades;
    }

    public Set<Integer> comunidad_at(int i) {
        return comunidades.get(i);
    }
}
