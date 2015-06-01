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
                actualizaListaRelaciones(aux[1], aux[2]);
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
        cpr.reiniciarCacheEv();
        pl1.lista.setPrototypeCellValue("PRUEBA");
        pl1.model = new AbstractListModel(){
            public int getSize() { return cpr.sizeEventos(); }
            public Object getElementAt(int index) {
                //System.out.println("ev indice "+index);
                return cpr.obtEventoCache(index); }
        };
        pl1.lista.setModel(pl1.model);

        cpr.reiniciarCacheCon();
        pl2.lista.setPrototypeCellValue("PRUEBA");
        pl2.model = new AbstractListModel(){
            public int getSize() { return cpr.sizeCongreso(); }
            public Object getElementAt(int index) {
                //System.out.println("con indice "+index);
                return cpr.obtCongresistaCache(index); }
        };
        pl2.lista.setModel(pl2.model);
    }

    private void actualizaListaRelaciones(String nombre, String fecha){
        cpr.cargarCache(nombre,fecha);
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
            actualizaListaRelaciones(nombre, fecha);
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
            actualizaListaRelaciones(nombre,fecha);
        }
    }

}
