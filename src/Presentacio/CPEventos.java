package Presentacio;

import Domini.ControladorCjtEvento;

/**
 * Created by USUARIO on 22/05/2015.
 */
public class CPEventos {
    private PanelEventos PE;
    private ControladorCjtEvento CC;

        public CPEventos() {}

        public PanelEventos obtPanel() {
            if (PE == null) PE = new PanelEventos(this);
            return PE;
        }
}
