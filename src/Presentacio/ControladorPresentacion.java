package Presentacio;

import Domini.ControladorAlgoritmo;
import Domini.ControladorCjtEvento;
import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;

/**
 * Clase ControladorPresentación
 * Created by bug on 23/03/15.
 */
public class ControladorPresentacion {

    private CPAlgoritmo cpa;
    private CPCongreso cpc;
    private CPEventos cpe;
    private CPRelaciones cpr;

    private VistaPrincipal v;
    private PanelAyuda pa;

    public ControladorPresentacion(){
        cpc = new CPCongreso();
        cpe = new CPEventos();
        cpr = new CPRelaciones(cpc, cpe);
        cpa = new CPAlgoritmo(cpr);

        v = new VistaPrincipal(this);
    }

    public void inicializarPresentacion() {
        v.pack();
        v.setVisible(true);
    }

    public VistaPrincipal getVista() {
        return v;
    }

    public PanelAyuda obtPanelAyuda() {
        if (pa == null) pa = new PanelAyuda();
        return pa;
    }

    public CPAlgoritmo getCPA() { return cpa; }
    public CPCongreso getCPC() { return cpc; }
    public CPEventos obtCPE() { return cpe;}
    public CPRelaciones getCPR() { return cpr;}

    public boolean modificado(){
        return !cpc.obtCC().esVacio() || cpe.obtCCE().size()>0;
    }

    public void nuevo(){
        cpc.nuevo();
        cpe.nuevo();
        cpr.actualizar();
        cpa.nuevo();
    }

    public void guardar(String ruta){
        cpr.guardar(ruta);
    }

    public void cargar(String ruta){
        cpr.cargar(ruta);
    }

}
