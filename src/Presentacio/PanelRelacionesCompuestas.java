package Presentacio;

import java.util.List;

/**
 * Created by jose on 4/06/15.
 */
public class PanelRelacionesCompuestas extends Panel3Listas {

    private CPRelaciones cpr;

    public PanelRelacionesCompuestas(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    private void inicializar(){
        pl1.titulo.setText("Congresistas");
        pl2.titulo.setText("Eventos");
        pl3.titulo.setText("Relaciones compuestas");
    }

    public void actualizar(){

    }
}
