package Domini;

import java.util.Random;
import java.util.Vector;

public class Dni  {
    private class DNINOVALIDO extends Exception {
        public DNINOVALIDO(String s) {
            super("DNI "+s+" no valido");
        }
    }
    /**Formato: 9 caracteres (8 numeros y 1 letra)
     * 8 primeros caracteres son numeros
     * ultimo caracter es una letra
     * el Dni siempre se guardara con el ultimo
     * caracter con mayuscula
     * dos dnis son iguales si tienen los mismos caracteres
     * sin importar si es mayuscula o minuscula la ultima letra
     */
    private String dni;

    public Dni(String s) throws Exception {
        if (valido(s)) {
            dni = trad(s);
        }
        else throw new DNINOVALIDO(s);
    }

    public Dni() {
        dni = new String();
        Random rand = new Random();
        for (int i = 0; i < 9; ++i) {
            if (i == 8) dni += (char) ('A'+ rand.nextInt(26));
            else dni += String.valueOf(rand.nextInt(10));
        }
    }

    private String trad(String d) {
        if (java.lang.Character.isLowerCase(d.charAt(8))) {
            d = d.toUpperCase();
        }
        return d;
    }

    void modDni(String s) throws Exception {
        if (valido(s)) {
            dni = trad(s);
        }
        else throw new DNINOVALIDO(s);
    }
    //Comprueba si es valido el dni mediante un
    // recorrido secuencial
    public static boolean valido(String d) {
        if (d.length() != 9) return false;
        for (int i = 0; i <8; ++i) {
            if (!Character.isDigit((d.charAt(i)))) return false;
        }
        return Character.isLetter(d.charAt(8));
    }

    public String toString() {
        return dni;
    }

    public boolean equals(Dni D) {
        return D.dni.equals(dni);
    }

    public void print(String str) {
        System.out.println(str);
    }
}
