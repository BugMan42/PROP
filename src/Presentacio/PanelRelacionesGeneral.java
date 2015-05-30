package Presentacio;

import javax.swing.*;

/**
 * Created by jose on 29/05/15.
 */
public class PanelRelacionesGeneral extends Panel3ListasExt {

    private CPRelaciones cpr;

    public PanelRelacionesGeneral(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    public void inicializar(){
        pl1.titulo.setText("Congresistas");
        pl2.titulo.setText("Eventos");
        pl3.titulo.setText("Relaciones");
    }

    public void actualizar(){

    }

}
