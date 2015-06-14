package Domini;

import java.util.ArrayList;

public abstract class RelacionCompuesta extends Relacion {
    private static int next_id = 0;

    private int id;

    public RelacionCompuesta(){
        id = next_id++;
    }

    public boolean esSimple() {
        return false;
    }

    public boolean esConjunto() {
        return false;
    }

    public boolean esAnd() {
        return false;
    }

    public boolean esOr() {
        return false;
    }

    public boolean esNot() {
        return false;
    }

    public abstract ArrayList<Congresista> obtConjunto();

    public RelacionCompuesta obtHijo() throws Exception {
        throw new Exception("La relación compuesta no es una Not.");
    }

    public RelacionCompuesta obtHijoIzq() throws Exception {
        throw new Exception("La relación compuesta no es And o Or.");
    }

    public RelacionCompuesta obtHijoDer() throws Exception {
        throw new Exception("La relación compuesta no es And o Or.");
    }

    public boolean contieneConjunto(int id) throws Exception {
        throw new Exception("La relación compuesta es un conjunto.");
    }

    public int obtId(){
        return id;
    }

    public abstract String descripcion();

    public abstract String toString();

}
