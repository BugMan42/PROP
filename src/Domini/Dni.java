package Domini;

public class Dni  {
    ////formato: 9 caracteres (8 numeros y 1 letra)
    private String dni;

    public Dni(String s) throws Exception {
        if (valido(s)) {
            dni = trad(s);
        }
        else throw new Exception("DNI no valido");
    }
    private String trad(String d) {
        if (java.lang.Character.isLowerCase(d.charAt(8))) {
            d = d.toUpperCase();
        }
        return d;
    }
    void modDni(String s) throws Exception {
        if (valido(s)) {
            dni = s;
        }
        else throw new Exception("DNI no valido");
    }

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
}
