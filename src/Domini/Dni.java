package Domini;

import java.util.Random;

public class Dni  {
    public class DNINOVALIDO extends Exception {
        public DNINOVALIDO(String s) {
            super("DNI "+s+" no valido: Formato [8 num]+[1letra]");
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
    private static Random rand;

    public Dni(String s) throws Exception {
        if (valido(s)) {
            dni = trad(s);
        }
        else throw new DNINOVALIDO(s);
    }
    public Dni(Dni a) {
        dni = a.dni;
    }
    public Dni() {
       dni =  generarRandom2();
    }
    private String generarRandom2() {
        String dni2 = new String();
        if (rand == null) {
            rand = new Random();
        }
        dni2+=String.valueOf(rand.nextInt(89999999)+10000000);
        dni2 += (char) ('A'+ rand.nextInt(26));
        return dni2;
    }
    public void cambiarLetra(char l) throws Exception {
        if (!Character.isLetter(l)) throw new Exception("l tiene que ser una letra");
        dni = dni.substring(0, dni.length()-1);
        dni += l;
        dni.toUpperCase();
    }
    private String trad(String d) {
        if (java.lang.Character.isLowerCase(d.charAt(8))) {
            d = d.toUpperCase();
        }
        return d;
    }
    public void modDni(String s) throws Exception {
        if (valido(s)) {
            dni = trad(s);
        }
        else throw new DNINOVALIDO(s);
    }
    //Comprueba si es valido el dni mediante un
    // recorrido secuencial
    public static boolean valido(String d) {
        if (d.length() != 9) return false;
        for (int i = 0; i < 8; ++i) {
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
    private void print(String str) {
        System.out.println(str);
    }
}
