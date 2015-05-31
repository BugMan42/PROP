package Presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 27/05/15.
 */
public class PanelRelacionesCongresista extends Panel3Listas {

    private class SH1 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (!lsm.isSelectionEmpty()) pl3.lista.setListData(cpr.obtRelaciones(obtDni()));
            else pl3.lista.setListData(new String[0]);
        }
    }

    private class SH2 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (lsm.isSelectionEmpty()) {
                activar_voto(false);
            } else {
                // Si el usuario no está ajustando la selección.
                if (!e.getValueIsAdjusting()) {
                    activar_voto(false);
                    List<String> sel2 = pl2.lista.getSelectedValuesList();
                    for (String i : sel2)
                        if(i.split(" ")[0].equals("Votacion")) activar_voto(true);
                }
            }
        }
    }

    private CPRelaciones cpr;

    public PanelRelacionesCongresista(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    public void inicializar(){
        pl1.titulo.setText("Congresistas");
        pl2.titulo.setText("Eventos");
        pl3.titulo.setText("Eventos a los que asiste");

        ListSelectionModel lsm1 = pl1.lista.getSelectionModel();
        lsm1.addListSelectionListener(new SH1());

        ListSelectionModel lsm2 = pl2.lista.getSelectionModel();
        lsm2.addListSelectionListener(new SH2());

        bañadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAñadirActionPerformed(evt);
            }
        });

        beliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
    }

    public void actualizar() {
        if(!cpr.esVacioCongreso()) pl1.lista.setListData(cpr.obtCongreso());
        else pl1.lista.setListData(new String[0]);
        if(!cpr.esVacioEventos()) pl2.lista.setListData(cpr.obtEventos());
        else pl2.lista.setListData(new String[0]);
    }

    private String obtDni(){
        return pl1.lista.getModel().getElementAt(pl1.lista.getSelectedIndex()).toString().split(" ")[0];
    }

    private void bAñadirActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty() && !pl2.lista.isSelectionEmpty()){
            String dni = obtDni();
            List<String> sel2 = pl2.lista.getSelectedValuesList();
            for(String i : sel2){
                String[] aux = i.split(" ");
                String nombre = aux[1];
                String fecha = aux[2];
                if (aux[0].equals("Votacion")) cpr.agregarVoto(dni, nombre, fecha, cbvoto.getModel().getSelectedItem().toString());
                else cpr.agregarRelacion(dni,nombre,fecha);
            }
            pl3.lista.setListData(cpr.obtRelaciones(dni));
        }
    }

    private void bEliminarActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty() && !pl3.lista.isSelectionEmpty()){
            String dni = obtDni();
            List<String> sel3 = pl3.lista.getSelectedValuesList();
            for(String i : sel3){
                String[] aux = i.split(" ");
                String nombre = aux[1];
                String fecha = aux[2];
                if(aux[0].equals("Votacion")) cpr.eliminarVoto(dni,nombre,fecha,aux[3]);
                else cpr.eliminarRelacion(dni,nombre,fecha);
            }
            String[] s = cpr.obtRelaciones(dni);
            if(s.length>0) pl3.lista.setListData(s);
            else pl3.lista.setListData(new String[0]);
        }
    }

}
