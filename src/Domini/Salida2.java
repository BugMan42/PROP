package Domini;

import java.util.ArrayList;
import java.util.Set;

public class Salida2 {
    final static String error11 = "La i tiene que estar entre 0 y el numero de comunidades";

    private ArrayList<String> mensajes;
    private ArrayList<Set<Congresista>> comunidades;

    public Salida2()
    {
        mensajes = new ArrayList<String>();
        comunidades = new ArrayList<Set<Congresista>>();
    }

    public void eliminarSalida() {
        mensajes.clear();
        comunidades.clear();
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

    public void agregarComunidad(Set<Congresista> s) {
        comunidades.add(s);
    }


    public ArrayList<Set<Congresista>> comunidad() {
        return comunidades;
    }


    public Set<Congresista> comunidad_at(int i) throws Exception {
        if (i < 0 || i >= comunidades.size()) throw new Exception(error11);
        return comunidades.get(i);
    }

    public int numComunidades() {return comunidades.size();}
}
