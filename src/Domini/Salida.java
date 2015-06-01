package Domini;

import java.util.ArrayList;
import java.util.Set;

public class Salida {
    final static String error11 = "La i tiene que estar entre 0 y el numero de comunidades";

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
        if (!mensajes.isEmpty()){
            int n = mensajes.size()-1;
            return mensajes.get(n);
        }
        else return "";
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


    public Set<Integer> comunidad_at(int i) throws Exception {
        if (i < 0 || i >= comunidades.size()) throw new Exception(error11);
        return comunidades.get(i);
    }

    public int numComunidades() {return comunidades.size();}
}
