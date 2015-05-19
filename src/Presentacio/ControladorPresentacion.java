package Presentacio;

import Domini.ControladorAlgoritmo;
import Domini.ControladorCjtEvento;
import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;

/**
 * Created by bug on 23/03/15.
 */
public class ControladorPresentacion {

    private ControladorCongreso cc;
    private ControladorCjtEvento ce;
    private ControladorAlgoritmo ca;
    private ControladorRelaciones cr;

    private Vista v; //Principal vp;
    private VistaPrincipal vp;

    public ControladorPresentacion(){
        cc = new ControladorCongreso();
        ce = new ControladorCjtEvento();
        cr = new ControladorRelaciones(cc,ce);
        ca = new ControladorAlgoritmo(cr);

        v = new VistaAlgoritmo(this); //Principal();
        vp = new VistaPrincipal();
    }

    public Vista getVista()
    {
        return v;
    }

    public VistaPrincipal getVistaPrincipal()
    {
        return vp;
    }

    public ControladorCongreso getControlCongreso()
    {
        return cc;
    }
    public ControladorRelaciones getControlRelaciones()
    {
        return cr;
    }


}
