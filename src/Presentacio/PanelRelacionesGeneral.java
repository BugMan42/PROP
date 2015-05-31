package Presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by jose on 29/05/15.
 */
public class PanelRelacionesGeneral extends Panel3ListasExt {

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

    /*
    class AL1 implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            JScrollBar sb = pl1.scrollPane1.getVerticalScrollBar();
            if (e.getValueIsAdjusting()) {
                int pos = sb.getValue();
                System.out.println("Vertical Scroll Bar changed position to " + (sb.getValue()+sb.getModel().getExtent())+" Maximum: "+sb.getMaximum()+" Size: "+pl1.model.size());
                int extent = sb.getModel().getExtent();
                if (pos+extent == sb.getMaximum() && pl1.bloque2 < pl1.totalBloques-1){
                    ++pl1.bloque1;
                    ++pl1.bloque2;
                    System.out.println("bloque2 = "+pl1.bloque2+" totalbloques = "+pl1.totalBloques+" scroll down");
                    if(pl1.bloque2>1) pl1.model.removeRange(0,tam_bloque-1);
                    pl1.bloque = cpr.obtBloqueCongreso(pl1.bloque2,tam_bloque);
                    pl1.tam_bloque2 = pl1.bloque.length;
                    for (int i=0; i<pl1.bloque.length; ++i) pl1.model.addElement(pl1.bloque[i]);
                    pl1.lista.setModel(pl1.model);
                }
                else if (pos == sb.getMinimum() && pl1.bloque1 > 0){
                    --pl1.bloque1;
                    --pl1.bloque2;
                    System.out.println("bloque1 = "+pl1.bloque1+" totalbloques = "+pl1.totalBloques+" scroll up");
                    pl1.model.removeRange(pl1.model.size()-pl1.tam_bloque2,pl1.model.size()-1);
                    pl1.bloque = cpr.obtBloqueCongreso(pl1.bloque1,tam_bloque);
                    for (int i=pl1.bloque.length-1; i>=0; --i) pl1.model.add(0, pl1.bloque[i]);
                    pl1.lista.setModel(pl1.model);
                }
            }
        }
    }*/

    private CPRelaciones cpr;

    public PanelRelacionesGeneral(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    public void inicializar() {
        pl1.titulo.setText("Congresistas");
        pl2.titulo.setText("Eventos");
        pl3.titulo.setText("Relaciones");

        ListSelectionModel lsm2 = pl2.lista.getSelectionModel();
        lsm2.addListSelectionListener(new SH2());

        /*
        AdjustmentListener al1 = new AL1();
        pl1.scrollPane1.getVerticalScrollBar().addAdjustmentListener(al1);*/


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

        bañadirrandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAñadirRandomActionPerformed(evt);
            }
        });

        beliminartodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarTodasActionPerformed(evt);
            }
        });
    }

    public void actualizar() {
        /*
        if (cpr.sizeCongreso()>0){
            pl1.totalBloques = cpr.sizeCongreso()/tam_bloque + 1;
            pl1.bloque = cpr.obtBloqueCongreso(0,tam_bloque);
            pl1.model = new DefaultListModel();
            for (int i=0; i<pl1.bloque.length; ++i) pl1.model.addElement(pl1.bloque[i]);
            pl1.lista.setModel(pl1.model);
            pl1.bloque2 = 0;
        }
        else pl1.lista.setListData(new String[0]);*/

        cpr.reiniciarBCon();
        pl1.lista.setPrototypeCellValue("PRUEBA");
        pl1.model = new AbstractListModel(){
            public int getSize() { return cpr.sizeCongreso(); }
            public Object getElementAt(int index) {
                System.out.println("obteniendo indice "+index);
                return cpr.obtCongresista(index); }
        };
        pl1.lista.setModel(pl1.model);

        /*if(!cpr.esVacioCongreso()) pl1.lista.setListData(cpr.obtCongreso());
        else pl1.lista.setListData(new String[0]);*/
        if(!cpr.esVacioEventos()) pl2.lista.setListData(cpr.obtEventos());
        else pl2.lista.setListData(new String[0]);
        if(!cpr.esVacioRelaciones()) pl3.lista.setListData(cpr.obtRelaciones());
        else pl3.lista.setListData(new String[0]);
    }

    private void bAñadirActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty() && !pl2.lista.isSelectionEmpty()){
            List<String> sel1 = pl1.lista.getSelectedValuesList();
            for (String i : sel1){
                String dni = i.split(" ")[0];
                List<String> sel2 = pl2.lista.getSelectedValuesList();
                for (String j : sel2){
                    String[] aux = j.split(" ");
                    String nombre = aux[1];
                    String fecha = aux[2];
                    if (aux[0].equals("Votacion")) cpr.agregarVoto(dni, nombre, fecha, cbvoto.getModel().getSelectedItem().toString());
                    else cpr.agregarRelacion(dni,nombre,fecha);
                }
            }
            pl3.lista.setListData(cpr.obtRelaciones());
        }
    }

    private void bEliminarActionPerformed(ActionEvent evt) {
        if(!pl3.lista.isSelectionEmpty()){
            List<String> sel3 = pl3.lista.getSelectedValuesList();
            for(String i : sel3){
                String[] aux = i.split(" ");
                if(aux.length==4) cpr.eliminarVoto(aux[0],aux[1],aux[2],aux[3]);
                else cpr.eliminarRelacion(aux[0],aux[1],aux[2]);
            }
            if(!cpr.esVacioRelaciones()) pl3.lista.setListData(cpr.obtRelaciones());
            else pl3.lista.setListData(new String[0]);
        }
    }

    private void bAñadirRandomActionPerformed(ActionEvent evt) {
        cpr.agregarRelacionRandom((Integer)nr.getValue());
        pl3.lista.setListData(cpr.obtRelaciones());
    }

    private void bEliminarTodasActionPerformed(ActionEvent evt) {
        if (!cpr.esVacioRelaciones()) {
            cpr.eliminarRelaciones();
            pl3.lista.setListData(new String[0]);
        }
    }

}
