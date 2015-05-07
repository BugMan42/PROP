package Presentacio;

import Domini.ControladorAlgoritmo;
import Domini.ControladorCjtEvento;
import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;

/**
 * Created by bug on 23/03/15.
 */
public class ControladorPresentacio {

    private ControladorCongreso cc;
    private ControladorCjtEvento ce;
    private ControladorAlgoritmo ca;
    private ControladorRelaciones cr;

    public ControladorPresentacio(){
        cc = new ControladorCongreso();
        ce = new ControladorCjtEvento();
        cr = new ControladorRelaciones(cc,ce);
        ca = new ControladorAlgoritmo(cr);
    }
}
