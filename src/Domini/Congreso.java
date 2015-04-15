package Domini;

import java.util.*;

/**
 * Created by bug on 20/03/15.
 */
public class Congreso {
    static final String error1 = "Congreso no contiene el dni";
    static final String error2 = "Congreso ya contiene el dni";
    static final String error3 = "DNI NO VALIDO";
    private ArrayList<Congresista> Lista;
    //private int N;
    public Congreso() {
        Lista = new ArrayList<Congresista>();
        //N = 0;
    }
    private int BuscarIndice(Dni dni) {
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
    }
    public void addCongresista(Congresista C) {
        if (!contieneCongresista(C.obtDni())) {
            Lista.add(C);
        }
        else throw new IllegalArgumentException(error2);
        //++N;
    }
    public void eliminarCongresista(Congresista C) {
        Lista.remove(C);
        //--N;
    }
    public ArrayList<Congresista> obtener_lista() {
        return Lista;
    }
    /*public boolean contieneCongresista(Congresista C) {
        //post: retorna verdadero si el congresista esta en el congreso
        return Lista.contains(C);
    }*/
    public boolean contieneCongresista(Dni dni) {
        //post: retorna verdadero si el dni esta en el congreso
        int n = Lista.size();
        int i = 0;
        while (i < n) {
            if (Lista.get(i).obtDni().equals(dni)) return true;
            else ++i;
        }
        return false;
    }
    public void EliminarCongresista(Dni dni) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.remove(i);
            else throw new IllegalArgumentException(error1);
    }
    public void EliminarCongreso() {
        Lista.clear();
    }

    //####################################
    //########MODIFICADORAS###############
    //####################################
    public void ModificarDniCongresista(Dni dni,int edad) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.get(i).modEdad(edad);
        else throw new IllegalArgumentException(error1);
    }
    public void ModificarNombreCongresista(Dni dni,int nombre) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.get(i).modEdad(nombre);
        else throw new IllegalArgumentException(error1);
    }
    public void ModificarApellidoCongresista(Dni dni,int apellido) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.get(i).modEdad(apellido);
        else throw new IllegalArgumentException(error1);
    }
    public void ModificarEdadCongresista(Dni dni,int edad) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.get(i).modEdad(edad);
        else throw new IllegalArgumentException(error1);
    }
    public void ModificarCiudadCongresista(Dni dni,int ciudad) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.get(i).modEdad(ciudad);
        else throw new IllegalArgumentException(error1);
    }
    public void ModificarEstadoCongresista(Dni dni,int estado) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.get(i).modEdad(estado);
        else throw new IllegalArgumentException(error1);
    }
    public void ModificarPartidoCongresista(Dni dni,int partido) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.get(i).modEdad(partido);
        else throw new IllegalArgumentException(error1);
    }
    public void ModificarCongresista(Dni dni,Dni dni2 , String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        int i = BuscarIndice(dni);
        if (i != -1) Lista.get(i).modEdad(edad);
        else throw new IllegalArgumentException(error1);
    }
    public Congresista ConsultarCongresista(Dni dni) {
        int i = BuscarIndice(dni);
        if (i != -1) return Lista.get(i);
        else throw new IllegalArgumentException(error1);
    }


}
