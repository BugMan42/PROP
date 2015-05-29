package Presentacio;

/**
 * Created by jose on 27/05/15.
 */
public class PanelRelacionesEvento extends Panel3Listas {

    private CPRelaciones cpr;

    public PanelRelacionesEvento(CPRelaciones c){
        cpr = c;

        pl1.titulo.setText("Eventos");
        pl2.titulo.setText("Congresistas");
        pl3.titulo.setText("Asistentes");
    }

    public void actualizar(){
        pl1.lista.setListData(cpr.obtEventos());
        pl2.lista.setListData(cpr.obtCongreso());
    }

}
