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
            if (!lsm.isSelectionEmpty()) actualizaListaRelaciones();
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
        cpr.reiniciarCacheCon();
        pl1.lista.setPrototypeCellValue("PRUEBA");
        pl1.model = new AbstractListModel(){
            public int getSize() { return cpr.sizeCongreso(); }
            public Object getElementAt(int index) {
                //System.out.println("con indice "+index);
                return cpr.obtCongresistaCache(index); }
        };
        pl1.lista.setModel(pl1.model);

        cpr.reiniciarCacheEv();
        pl2.lista.setPrototypeCellValue("PRUEBA");
        pl2.model = new AbstractListModel(){
            public int getSize() { return cpr.sizeEventos(); }
            public Object getElementAt(int index) {
                //System.out.println("ev indice "+index);
                return cpr.obtEventoCache(index); }
        };
        pl2.lista.setModel(pl2.model);
    }

    private void actualizaListaRelaciones(){
        cpr.cargarCache(obtDni());
        cpr.reiniciarCacheRel();
        pl3.lista.setPrototypeCellValue("PRUEBA");
        pl3.model = new AbstractListModel(){
            public int getSize() { return cpr.sizeResCache(); }
            public Object getElementAt(int index) {
                //System.out.println("rel indice "+index);
                return cpr.obtResCache(index); }
        };
        pl3.lista.setModel(pl3.model);
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
            actualizaListaRelaciones();
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
            actualizaListaRelaciones();
        }
    }

}
