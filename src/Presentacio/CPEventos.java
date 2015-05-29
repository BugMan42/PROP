package Presentacio;

import Domini.ControladorCjtEvento;
import Domini.ControladorRelaciones;

public class CPEventos {
    PanelEventos PE;
    ControladorCjtEvento CCE;
    CPRelaciones CR;

    public CPEventos() {
            CCE = new ControladorCjtEvento();
        }

    public PanelEventos obtPanel() {
        if (PE == null) PE = new PanelEventos(this);
        return PE;
    }

    public ControladorCjtEvento obtCCE() {return CCE;}

    public void agregarRandom(int n) throws Exception {
        CCE.AgregarEventoRandom(n);
    }

}
