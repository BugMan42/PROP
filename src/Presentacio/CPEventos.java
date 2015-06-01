package Presentacio;

import Domini.ControladorCjtEvento;

public class CPEventos {
    private PanelEventos PE;
    private ControladorCjtEvento CCE;
    private CPRelaciones CR;

    public CPEventos() {
            CCE = new ControladorCjtEvento();
        }

    public PanelEventos obtPanel() {
        if (PE == null) PE = new PanelEventos(this);
        return PE;
    }

    public ControladorCjtEvento obtCCE() {return CCE;}

    public void modCR(CPRelaciones cpr) {CR = cpr;}

    public CPRelaciones obtCPR() {return CR;}

    public void nuevo(){
        if (CCE.size()>0) CCE.EliminarCjtEvento(CR.obtCR());
        actualizar();
    }

    public void actualizar(){
        if(PE!=null){
            PE.limpiarcampos();
            PE.actualizarLista();
        }
    }

}
