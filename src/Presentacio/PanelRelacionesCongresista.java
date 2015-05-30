package Presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by jose on 27/05/15.
 */
public class PanelRelacionesCongresista extends Panel3Listas {

    private class SH1 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (!lsm.isSelectionEmpty()) try {
                pl3.lista.setListData(cpr.obtRelaciones(obtDni()));
            } catch (Exception e1) {
                e1.printStackTrace();
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
    }

    public void actualizar() throws Exception {
        pl1.lista.setListData(cpr.obtCongreso());
        pl2.lista.setListData(cpr.obtEventos());
    }

    private boolean esVotacion(int i){
        String[] aux = pl2.lista.getModel().getElementAt(i).toString().split(" ");
        return aux[0].equals("Votacion");
    }

    private String obtDni(){
        return pl1.lista.getModel().getElementAt(pl1.lista.getSelectedIndex()).toString().split(" ")[0];
    }

    private void bAñadirActionPerformed(ActionEvent evt) throws Exception {
        if(!pl1.lista.isSelectionEmpty() && !pl2.lista.isSelectionEmpty()){
            String dni = obtDni();
            int n = sel2.size();
            for(int i=0; i<n; ++i){
                String[] aux = pl2.lista.getModel().getElementAt(sel2.get(i)).toString().split(" ");
                String nombre = aux[1];
                String fecha = aux[2];
                if (aux[0].equals("Votacion")) cpr.agregarVoto(dni, nombre, fecha, cbvoto.getModel().getSelectedItem().toString());
                else cpr.agregarRelacion(dni,nombre,fecha);
            }
            pl3.lista.setListData(cpr.obtRelaciones(dni));
        }
    }

    private void bEliminarActionPerformed(ActionEvent evt) throws Exception {
        if(!pl1.lista.isSelectionEmpty() && !pl3.lista.isSelectionEmpty()){
            String dni = obtDni();
            int n = sel3.size();
            for(int i=0; i<n; ++i){
                String[] aux = pl3.lista.getModel().getElementAt(sel3.get(i)).toString().split(" ");
                String nombre = aux[1];
                String fecha = aux[2];
                if(aux[0].equals("Votacion")) cpr.eliminarVoto(dni,nombre,fecha,aux[3]);
                else cpr.eliminarRelacion(dni,nombre,fecha);
            }
            pl3.lista.setListData(cpr.obtRelaciones(dni));
        }
    }

}
