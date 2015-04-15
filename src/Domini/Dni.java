package Domini;

/**
 * Created by bug on 15/04/15.
 */
public class Dni  {
    ////formato: 9 caracteres (8 numeros y 1 letra)
    private String dni;

    Dni(String s) throws Exception {
        if (valido(s)) {
            dni = s;
        }
        else throw new Exception("DNI no valido");
    }
    void modDni(String s) throws Exception {
        if (valido(s)) {
            dni = s;
        }
        else throw new Exception("DNI no valido");
    }
    String DNI() {
        return dni;
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
}
