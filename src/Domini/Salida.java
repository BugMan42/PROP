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

    /**
     * Creadora de salida
     * Pre: Cierto
     * Post: Se ha creado una instancia de salida
     */
    public Salida()
    {
        mensajes = new ArrayList<String>();
        comunidades = new ArrayList<Set<Integer>>();
    }
    /**
     *
     * Pre: Cierto
     * Post: s ha sido guardado
     */
    public void agregarMensaje(String s)
    {
        mensajes.add(s);
    }

    /**
     *
     * Pre: Cierto
     * Post: Se devuelve el ultimo mensaje almacenado
     * o vacio en caso de que no haya ninguno.
     */
    public String mostrarMensaje()
    {
        if (!mensajes.isEmpty()){
            int n = mensajes.size()-1;
            return mensajes.get(n);
        }
        else return "";
    }

    /**
     * Muestra todos los mensajes
     * Pre: Cierto
     * Post: Se devuelven todos los mensaje almacenados
     */
    public ArrayList<String> mostrarHistorial()
    {
        return mensajes;
    }
    /**
     *
     * Pre: s no puede estar vacio
     * Post: s ha sido almacenada
     */
    public void agregarComunidad(Set<Integer> s) {
        comunidades.add(s);
    }

    /**
     * Devuelve todas las comunidades
     * Pre: Cierto
     * Post: Se devuelven todas las comunidades que han sido previamente almacenadas
     */
    public ArrayList<Set<Integer>> comunidad() {
        return comunidades;
    }

    /**Devuelve la comunidad de num i
     *Pre: 0 <= i < numcomunidades
     * Post: Devuelve la comunidad numero i
     */
    public Set<Integer> comunidad_at(int i) throws NoValido{
        if (i < 0 || i >= comunidades.size()) throw new NoValido("Indice", 11);
        return comunidades.get(i);
    }

    /**
     *
     * Pre: Cierto
     * Post: Devuelve el numero de comunidades que hay en salida
     */
    public int numComunidades() {return comunidades.size();}
}
