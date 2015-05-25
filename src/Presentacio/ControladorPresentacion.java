package Presentacio;

import Domini.ControladorAlgoritmo;
import Domini.ControladorCjtEvento;
import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;

/**
 * Created by bug on 23/03/15.
 */
public class ControladorPresentacion {

    /*private ControladorCongreso cc;
    private ControladorCjtEvento ce;
    private ControladorAlgoritmo ca;
    private ControladorRelaciones cr;*/

    private CPAlgoritmo cpa;
    private CPCongreso cpc;
    private CPEventos cpe;
    private CPRelaciones cpr;

    private VistaPrincipal v;
    private VistaPrincipal vp;

    public ControladorPresentacion(){
        cpc = new CPCongreso();
        cpa = new CPAlgoritmo();
        cpe = new CPEventos();
        cpr = new CPRelaciones(cpc, cpe);

        v = new VistaPrincipal(this);

       // / vp = new VistaPrincipal();
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

    public CPAlgoritmo getCPA() { return cpa; }
    public CPCongreso getCPC() { return cpc; }
    public CPEventos obtCPE() { return cpe;}
    public CPRelaciones obtCPR() { return cpr;}

}
