package Presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by jose on 4/06/15.
 */
public class PanelRCConjuntos extends Panel3Listas {

    private class SH1 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (!lsm.isSelectionEmpty()) {
                String[] aux = pl1.lista.getModel().getElementAt(pl1.lista.getSelectedIndex()).toString().split(" ");
                activar_voto(aux[0].equals("Votacion"));
            }
            else {
                pl3.lista.setListData(new String[0]);
                activar_voto(false);
            }
        }
    }

    private CPRelaciones cpr;

    public PanelRCConjuntos(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    private void inicializar(){
        pl1.titulo.setText("Eventos");
        pl2.titulo.setText("Congresistas");
        pl3.titulo.setText("Conjuntos");

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

    public void actualizar(){
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

        actualizaListaConjuntos();
    }

    private void actualizaListaConjuntos(){
        cpr.cargarConjuntos();
        pl3.lista.setPrototypeCellValue("PRUEBA");
        pl3.model = new AbstractListModel(){
            public int getSize() {
                return cpr.sizeConjuntos(); }
            public Object getElementAt(int index) {
                //System.out.println("rel indice "+index);
                return cpr.obtConjunto(index); }
        };
        pl3.lista.setModel(pl3.model);
    }

    private void bAñadirActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty() && !pl2.lista.isSelectionEmpty()){
            String[] aux = pl1.lista.getModel().getElementAt(pl1.lista.getSelectedIndex()).toString().split(" ");
            String nombre = aux[1];
            String fecha = aux[2];
            List<String> sel2 = pl2.lista.getSelectedValuesList();
            String congresistas = "";
            for(String i : sel2) congresistas += i.split(" ")[0]+"\n";
            if (aux[0].equals("Votacion")) cpr.agregarConjuntoConVoto(nombre,fecha,cbvoto.getModel().getSelectedItem().toString(),congresistas);
            else cpr.agregarConjuntoSinVoto(nombre,fecha,congresistas);
            actualizaListaConjuntos();
        }
    }

    private void bEliminarActionPerformed(ActionEvent evt) {
        if(!pl3.lista.isSelectionEmpty()){
            List<String> sel3 = pl3.lista.getSelectedValuesList();
            for(String i : sel3) cpr.eliminarConjunto(Integer.parseInt(i.split(" ")[0]));
            actualizaListaConjuntos();
        }
    }
}
