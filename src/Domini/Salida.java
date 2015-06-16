package Domini;

import java.util.ArrayList;
import java.util.Set;

public class Salida {
    final static String error11 = "La i tiene que estar entre 0 y el numero de comunidades";

    private ArrayList<String> mensajes1;
    private ArrayList<String> mensajes2;
    private ArrayList<String> mensajes3;

    private ArrayList<Set<Integer>> comunidades1;
    private ArrayList<Set<Integer>> comunidades2;
    private ArrayList<Set<Integer>> comunidades3;

    public Salida()
    {
        mensajes1 = new ArrayList<String>();
        mensajes2 = new ArrayList<String>();
        mensajes3 = new ArrayList<String>();

        comunidades1 = new ArrayList<Set<Integer>>();
        comunidades2 = new ArrayList<Set<Integer>>();
        comunidades3 = new ArrayList<Set<Integer>>();
    }

    public void eliminarSalida() {
        mensajes1.clear();
        mensajes2.clear();
        mensajes3.clear();
        comunidades1.clear();
        comunidades2.clear();
        comunidades3.clear();
    }

    public void agregarMensaje1(String s)
    {
        mensajes1.add(s);
    }
    public void agregarMensaje2(String s)
    {
        mensajes2.add(s);
    }
    public void agregarMensaje3(String s)
    {
        mensajes3.add(s);
    }

    public String mostrarMensaje()
    {
        if (!mensajes1.isEmpty()){
            int n = mensajes1.size()-1;
            return mensajes1.get(n);
        }
        else return "";
    }


    public ArrayList<String> mostrarHistorial1()
    {
        return mensajes1;
    }

    public ArrayList<String> mostrarHistorial2()
    {
        return mensajes2;
    }

    public ArrayList<String> mostrarHistorial3()
    {
        return mensajes3;
    }

    public void agregarComunidad1(Set<Integer> s) {
        comunidades1.add(s);
    }

    public void agregarComunidad2(Set<Integer> s) {
        comunidades2.add(s);
    }

    public void agregarComunidad3(Set<Integer> s) {
        comunidades3.add(s);
    }


    public ArrayList<Set<Integer>> comunidad1() {
        return comunidades1;
    }

    public ArrayList<Set<Integer>> comunidad2() {
        return comunidades2;
    }

    public ArrayList<Set<Integer>> comunidad3() {
        return comunidades3;
    }

    public Set<Integer> comunidad1_at(int i) throws Exception {
        if (i < 0 || i >= comunidades1.size()) throw new Exception(error11);
        return comunidades1.get(i);
    }

    public Set<Integer> comunidad2_at(int i) throws Exception {
        if (i < 0 || i >= comunidades2.size()) throw new Exception(error11);
        return comunidades2.get(i);
    }

    public Set<Integer> comunidad3_at(int i) throws Exception {
        if (i < 0 || i >= comunidades3.size()) throw new Exception(error11);
        return comunidades3.get(i);
    }

    public int numComunidades1() {return comunidades1.size();}

    public int numComunidades2() {return comunidades2.size();}

    public int numComunidades3() {return comunidades3.size();}
}
