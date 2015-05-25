package Presentacio;

import Domini.ControladorCjtEvento;

/**
 * Created by USUARIO on 22/05/2015.
 */
public class CPEventos {
    private PanelEventos PE;
    private ControladorCjtEvento CCE;

    public CPEventos() {
            CCE = new ControladorCjtEvento();
        }

    public PanelEventos obtPanel() {
        if (PE == null) PE = new PanelEventos(this);
        return PE;
    }

    public ControladorCjtEvento obtCCE() {return CCE;}
}
