import Presentacio.ControladorPresentacion;

/**
 * Created by Jose on 10/05/2015.
 */
public class Main {

    private ControladorPresentacion cp;

    /*public static void main(String[] args) {
        Main m = new Main();
        m.cp = new ControladorPresentacion();
    }*/

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        ControladorPresentacion ctrlPres = new ControladorPresentacion();
                        ctrlPres.inicializarPresentacion();
                    }
                }
        );
    }
}
