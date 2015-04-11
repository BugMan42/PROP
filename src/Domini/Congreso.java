package Domini;

import java.util.*;

/**
 * Created by bug on 20/03/15.
 */
public class Congreso {
    static final String error1 = "Congreso no contiene el dni";
    static final String error2 = "Congreso ya contiene el dni";
    static final String error3 = "DNI NO VALIDO";
    static final String error4 = "";
    static final String error5 = "";
    static final String error6 = "";
    static final String error7 = "";
    private ArrayList<Congresista> Lista;
    //private int N;
    public Congreso() {
        Lista = new ArrayList<Congresista>();
        //N = 0;
    }
    private int BuscarIndice(String dni) {
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
        if (!contieneCongresista(C)) {
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
    public boolean contieneCongresista(Congresista C) {
        //post: retorna verdadero si el congresista esta en el congreso
        return Lista.contains(C);
    }
    public boolean contieneCongresista(String dni) {
        //post: retorna verdadero si el dni esta en el congreso
        int n = Lista.size();
        boolean found = false;
        int i = 0;
        while (!found && i < n) {
            if (Lista.get(i).obtDni().equals(dni)) return true;
            else ++i;
        }
        return false;
    }
    private static boolean Valido(String dni) {
        return Congresista.DniValido(dni);
    }
    public void EliminarCongresista(String dni) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.remove(i);
            else throw new IllegalArgumentException(error1);
        }
    }
    public void EliminarCongreso() {
        Lista.clear();
    }
    //NOOOOOOO
    public void ModificarDniCongresista(String dni,int edad) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.get(i).modEdad(edad);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException(error3);
    }
    public void ModificarNombreCongresista(String dni,int nombre) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.get(i).modEdad(nombre);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException(error3);
    }
    public void ModificarApellidoCongresista(String dni,int apellido) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.get(i).modEdad(apellido);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException(error3);
    }
    public void ModificarEdadCongresista(String dni,int edad) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.get(i).modEdad(edad);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException(error3);
    }
    public void ModificarCiudadCongresista(String dni,int ciudad) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.get(i).modEdad(ciudad);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException(error3);
    }
    public void ModificarEstadoCongresista(String dni,int estado) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.get(i).modEdad(estado);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException(error3);
    }
    public void ModificarPartidoCongresista(String dni,int partido) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.get(i).modEdad(partido);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException(error3);
    }
    public void ModificarCongresista(String dni,String dni2 , String nombre, String apellido, int edad, String ciudad, String estado, String partido) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) Lista.get(i).modEdad(edad);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException(error3);
    }
    public Congresista ConsultarCongresista(String dni) {
        if (Valido(dni)) {
            int i = BuscarIndice(dni);
            if (i != -1) return Lista.get(i);
            else throw new IllegalArgumentException(error1);
        }
        else throw new IllegalArgumentException(error3);
    }


}
