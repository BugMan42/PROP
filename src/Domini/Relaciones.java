package Domini;

import java.util.ArrayList;

/**
 * Created by bug on 20/03/15.
 */


public class Relaciones {
    private ArrayList<Relacion> Lista;

    public Relaciones() {
        Lista = new ArrayList<Relacion>();
    }
    public ArrayList<Relacion> obt_lista() {
        return Lista;
    }
    public void add_Relacion(Relacion R) {
        Lista.add(R);
    }
    public void eliminar_relacion(Relacion R) {
        Lista.remove(R);
    }
    public boolean contiene(Relacion R) {
        return Lista.contains(R);
    }
    static final String error1 = "Congreso no contiene el dni";
    static final String error2 = "Congreso ya contiene el dni";
    static final String error3 = "DNI NO VALIDO";
    //private int N;
    /*private int BuscarIndice(Dni dni) {
        //si no se encuentra retorna -1
        int n = Lista.size();
        boolean found = false;
        int i = 0;
        while (!found && i < n) {
            if (Lista.get(i).obtDni().equals(dni)) found = true;
            else ++i;
        }
        if (!found) i = -1;
        return i;
    }*/
    /*public void addRelacion(Relacion C) {
        if (!contieneRelacion(C.obtDni())) {
            Lista.add(C);
        }
        else throw new IllegalArgumentException(error2);
        //++N;
    }*/
    public void eliminarRelacion(Relacion R) {
        Lista.remove(R);
        //--N;
    }
    public ArrayList<Relacion> obtener_lista() {
        return Lista;
    }
    /*public boolean contieneRelacion(Relacion C) {
        //post: retorna verdadero si el Relacion esta en el congreso
        return Lista.contains(C);
    }*/
   /* public boolean contieneRelacion(Dni dni) {
        //post: retorna verdadero si el dni esta en el congreso
        int n = Lista.size();
        int i = 0;
        while (i < n) {
            if (Lista.get(i).obtDni().equals(dni)) return true;
            else ++i;
        }
        return false;
    }*/
    /*public void EliminarRelacion(Dni dni) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.remove(i);
        else throw new IllegalArgumentException(error1);
    }
    public void EliminarRelaciones() {
        Lista.clear();
    }*/

    //####################################
    //########MODIFICADORAS###############
    //####################################
   /* public void ModificarRelacion(Dni dni,Dni dni2 , String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.get(i).modEdad(edad);
        else throw new IllegalArgumentException(error1);
    }*/
    /*public Relacion ConsultarRelacion(Dni dni) {
        int i = BuscarIndice(dni);
        if (i != -1) return Lista.get(i);
        else throw new IllegalArgumentException(error1);
    }*/

}
