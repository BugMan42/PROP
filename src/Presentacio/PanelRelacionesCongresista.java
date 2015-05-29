package Presentacio;

import javax.swing.*;

/**
 * Created by jose on 27/05/15.
 */
public class PanelRelacionesCongresista extends Panel3Listas {

    private CPRelaciones cpr;

    public PanelRelacionesCongresista(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    public void inicializar(){
        pl1.titulo.setText("Congresistas");
        pl2.titulo.setText("Eventos disponibles");
        pl3.titulo.setText("Eventos a los que asiste");
    }

    public void actualizar(){
        pl1.lista.setListData(cpr.obtCongreso());
    }

}
