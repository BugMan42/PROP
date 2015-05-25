package Presentacio;

import Domini.ControladorCjtEvento;
import Domini.ControladorCongreso;
import Domini.ControladorRelaciones;

//Por defecto cambiar si conviene
public class CPRelaciones {
    PanelRelaciones PR;
    ControladorRelaciones CR;

    public CPRelaciones() {
        CR = new ControladorRelaciones(new ControladorCongreso(), new ControladorCjtEvento());
    }

    public PanelRelaciones obtPanel() {
        if (PR == null) PR = new PanelRelaciones(this);
        return PR;
    }
}
