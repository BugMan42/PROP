package Domini;

/**
 * Created with IntelliJ IDEA.
 * User: anselmo.lopez
 * Date: 25/03/15
 * Time: 9:22
 * To change this template use File | Settings | File Templates.
 */
public class Parámetro {
    private int num_iter;
    public Parámetro() {}

    public Parámetro(int num_iter) {
        this.num_iter = num_iter;
    }

    public int obt_numero_iteraciones() {
        return num_iter;
    }

    public void mod_numero_iteraciones(int n) {
        num_iter = n;
    }
}
