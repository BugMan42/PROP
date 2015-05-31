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
public class PanelRelacionesEvento extends Panel3Listas {

    private class SH1 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (!lsm.isSelectionEmpty()) {
                String[] aux = pl1.lista.getModel().getElementAt(pl1.lista.getSelectedIndex()).toString().split(" ");
                pl3.lista.setListData(cpr.obtRelaciones(aux[1], aux[2]));
                activar_voto(aux[0].equals("Votacion"));
            }
            else {
                pl3.lista.setListData(new String[0]);
                activar_voto(false);
            }
        }
    }

    private CPRelaciones cpr;

    public PanelRelacionesEvento(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    public void inicializar(){
        pl1.titulo.setText("Eventos");
        pl2.titulo.setText("Congresistas");
        pl3.titulo.setText("Asistentes");

        ListSelectionModel lsm1 = pl1.lista.getSelectionModel();
        lsm1.addListSelectionListener(new SH1());

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
        if(!cpr.esVacioEventos()) pl1.lista.setListData(cpr.obtEventos());
        else pl1.lista.setListData(new String[0]);
        if(!cpr.esVacioCongreso()) pl2.lista.setListData(cpr.obtCongreso());
        else pl2.lista.setListData(new String[0]);
    }

    private void bAñadirActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty() && !pl2.lista.isSelectionEmpty()){
            String[] aux = pl1.lista.getModel().getElementAt(pl1.lista.getSelectedIndex()).toString().split(" ");
            String nombre = aux[1];
            String fecha = aux[2];
            List<String> sel2 = pl2.lista.getSelectedValuesList();
            for(String i : sel2){
                String dni = i.split(" ")[0];
                if (aux[0].equals("Votacion")) cpr.agregarVoto(dni, nombre, fecha, cbvoto.getModel().getSelectedItem().toString());
                else cpr.agregarRelacion(dni,nombre,fecha);
            }
            pl3.lista.setListData(cpr.obtRelaciones(nombre,fecha));
        }
    }

    private void bEliminarActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty() && !pl3.lista.isSelectionEmpty()){
            String[] aux = pl1.lista.getModel().getElementAt(pl1.lista.getSelectedIndex()).toString().split(" ");
            String nombre = aux[1];
            String fecha = aux[2];
            List<String> sel3 = pl3.lista.getSelectedValuesList();
            for(String i : sel3){
                String[] aux2 = i.split(" ");
                String dni = aux2[0];
                if(aux[0].equals("Votacion")) cpr.eliminarVoto(dni,nombre,fecha,aux2[4]);
                else cpr.eliminarRelacion(dni,nombre,fecha);
            }
            String[] s = cpr.obtRelaciones(nombre,fecha);
            if(s.length>0) pl3.lista.setListData(s);
            else pl3.lista.setListData(new String[0]);
        }
    }

}
