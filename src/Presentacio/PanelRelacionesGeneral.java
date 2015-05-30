package Presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by jose on 29/05/15.
 */
public class PanelRelacionesGeneral extends Panel3ListasExt {

    private class SH1 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (lsm.isSelectionEmpty()) {
                sel1 = new ArrayList<Integer>();
            } else {
                // Si el usuario no está ajustando la selección.
                if (!e.getValueIsAdjusting()) {
                    sel1 = new ArrayList<Integer>();
                    // Guardar índices seleccionados.
                    int minIndex = lsm.getMinSelectionIndex();
                    int maxIndex = lsm.getMaxSelectionIndex();
                    for (int i = minIndex; i <= maxIndex; i++) {
                        if (lsm.isSelectedIndex(i)) sel1.add(i);
                    }
                }
            }
        }
    }

    private class SH2 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (lsm.isSelectionEmpty()) {
                sel2 = new ArrayList<Integer>();
                activar_voto(false);
            } else {
                // Si el usuario no está ajustando la selección.
                if (!e.getValueIsAdjusting()) {
                    sel2 = new ArrayList<Integer>();
                    // Guardar índices seleccionados.
                    int minIndex = lsm.getMinSelectionIndex();
                    int maxIndex = lsm.getMaxSelectionIndex();
                    activar_voto(false);
                    for (int i = minIndex; i <= maxIndex; i++) {
                        if (lsm.isSelectedIndex(i)) {
                            sel2.add(i);
                            if(esVotacion(i)) activar_voto(true);
                        }
                    }
                }
            }
        }
    }

    private class SH3 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (lsm.isSelectionEmpty()) {
                sel3 = new ArrayList<Integer>();
            } else {
                // Si el usuario no está ajustando la selección.
                if (!e.getValueIsAdjusting()) {
                    sel3 = new ArrayList<Integer>();
                    // Guardar índices seleccionados.
                    int minIndex = lsm.getMinSelectionIndex();
                    int maxIndex = lsm.getMaxSelectionIndex();
                    for (int i = minIndex; i <= maxIndex; i++) {
                        if (lsm.isSelectedIndex(i)) sel3.add(i);
                    }
                }
            }
        }
    }

    private CPRelaciones cpr;

    public PanelRelacionesGeneral(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    public void inicializar(){
        pl1.titulo.setText("Congresistas");
        pl2.titulo.setText("Eventos");
        pl3.titulo.setText("Relaciones");

        ListSelectionModel lsm1 = pl1.lista.getSelectionModel();
        lsm1.addListSelectionListener(new SH1());

        ListSelectionModel lsm2 = pl2.lista.getSelectionModel();
        lsm2.addListSelectionListener(new SH2());

        ListSelectionModel lsm3 = pl3.lista.getSelectionModel();
        lsm3.addListSelectionListener(new SH3());

        bañadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    bAñadirActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        beliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    bEliminarActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        bañadirrandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    bAñadirRandomActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        beliminartodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    bEliminarTodasActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void actualizar() throws Exception {
        if(!cpr.esVacioCongreso()) pl1.lista.setListData(cpr.obtCongreso());
        else pl1.lista.setListData(new String[0]);
        if(!cpr.esVacioEventos()) pl2.lista.setListData(cpr.obtEventos());
        else pl2.lista.setListData(new String[0]);
        if(!cpr.esVacioRelaciones()) pl3.lista.setListData(cpr.obtRelaciones());
        else pl3.lista.setListData(new String[0]);
    }

    private boolean esVotacion(int i){
        String[] aux = pl2.lista.getModel().getElementAt(i).toString().split(" ");
        return aux[0].equals("Votacion");
    }

    private void bAñadirActionPerformed(ActionEvent evt) throws Exception {
        if(!pl1.lista.isSelectionEmpty() && !pl2.lista.isSelectionEmpty()){
            for (int i : sel1){
                String dni = pl1.lista.getModel().getElementAt(i).toString().split(" ")[0];
                for (int j : sel2){
                    String[] aux = pl2.lista.getModel().getElementAt(j).toString().split(" ");
                    String nombre = aux[1];
                    String fecha = aux[2];
                    if (aux[0].equals("Votacion")) cpr.agregarVoto(dni, nombre, fecha, cbvoto.getModel().getSelectedItem().toString());
                    else cpr.agregarRelacion(dni,nombre,fecha);
                }
            }
            pl3.lista.setListData(cpr.obtRelaciones());
        }
    }

    private void bEliminarActionPerformed(ActionEvent evt) throws Exception {
        if(!pl3.lista.isSelectionEmpty()){
            for(int i : sel3){
                String[] aux = pl3.lista.getModel().getElementAt(i).toString().split(" ");
                if(aux.length==4) cpr.eliminarVoto(aux[0],aux[1],aux[2],aux[3]);
                else cpr.eliminarRelacion(aux[0],aux[1],aux[2]);
            }
            if(!cpr.esVacioRelaciones()) pl3.lista.setListData(cpr.obtRelaciones());
            else pl3.lista.setListData(new String[0]);
        }
    }

    private void bAñadirRandomActionPerformed(ActionEvent evt) throws Exception {
        cpr.agregarRelacionRandom((Integer)nr.getValue());
        pl3.lista.setListData(cpr.obtRelaciones());
    }

    private void bEliminarTodasActionPerformed(ActionEvent evt) throws Exception {
        if (!cpr.esVacioRelaciones()) {
            cpr.eliminarRelaciones();
            pl3.lista.setListData(new String[0]);
        }
    }

}
