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

    private CPAlgoritmo cpa;
    private CPCongreso cpc;

    private VistaPrincipal v; //Principal vp;
    private VistaPrincipal vp;

    public ControladorPresentacion(){
        cc = new ControladorCongreso();
        ce = new ControladorCjtEvento();
        cr = new ControladorRelaciones(cc,ce);
        ca = new ControladorAlgoritmo(cr);

        cpc = new CPCongreso();
        cpa = new CPAlgoritmo();

        v = new VistaPrincipal(this);

       // vp = new VistaPrincipal();
    }

    public void inicializarPresentacion() {
        v.pack();
        v.setVisible(true);
    }

    public VistaPrincipal getVista()
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
    public CPAlgoritmo getCPA() { return cpa; }
    public CPCongreso getCPC() { return cpc; }


}
