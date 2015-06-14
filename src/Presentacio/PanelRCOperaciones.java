package Presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by jose on 13/06/15.
 */
public class PanelRCOperaciones extends PanelListaInfo {

    private class SH1 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (!lsm.isSelectionEmpty()) {
                String[] aux = pl1.lista.getModel().getElementAt(pl1.lista.getSelectedIndex()).toString().split(" ");
                actualizarListaCongresistas(Integer.parseInt(aux[0]));
                lConj.setText("<html>"+cpr.obtDescripcionComp(Integer.parseInt(aux[0]))+"</html>");
            }
            else {
                pl2.lista.setListData(new String[0]);
                lConj.setText("");
            }
        }
    }

    private CPRelaciones cpr;

    public PanelRCOperaciones(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    private void inicializar(){
        pl1.titulo.setText("Relaciones Compuestas");
        pl2.titulo.setText("Congresistas");

        ListSelectionModel lsm1 = pl1.lista.getSelectionModel();
        lsm1.addListSelectionListener(new SH1());

        band.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAndActionPerformed(evt);
            }
        });

        bor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOrActionPerformed(evt);
            }
        });

        bnot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNotActionPerformed(evt);
            }
        });

        bdeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeshacerActionPerformed(evt);
            }
        });

        bdeshacertodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeshacerTodoActionPerformed(evt);
            }
        });
    }



    private void bAndActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty()){
            List<String> sel = pl1.lista.getSelectedValuesList();
            if (sel.size()==2) {
                int i = Integer.parseInt(sel.get(0).split(" ")[0]);
                int d = Integer.parseInt(sel.get(1).split(" ")[0]);
                cpr.agregarCompuestaAnd(i,d);
                actualizar();
            }
        }
    }

    private void bOrActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty()){
            List<String> sel = pl1.lista.getSelectedValuesList();
            if (sel.size()==2) {
                int i = Integer.parseInt(sel.get(0).split(" ")[0]);
                int d = Integer.parseInt(sel.get(1).split(" ")[0]);
                cpr.agregarCompuestaOr(i, d);
                actualizar();
            }
        }
    }

    private void bNotActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty()){
            List<String> sel = pl1.lista.getSelectedValuesList();
            if (sel.size()==1) {
                int id = Integer.parseInt(sel.get(0).split(" ")[0]);
                cpr.agregarCompuestaNot(id);
                actualizar();
            }
        }
    }

    private void bDeshacerActionPerformed(ActionEvent evt) {
        if(!pl1.lista.isSelectionEmpty()) {
            List<String> sel = pl1.lista.getSelectedValuesList();
            if (sel.size() == 1) {
                int id = Integer.parseInt(sel.get(0).split(" ")[0]);
                cpr.deshacerCompuesta(id);
                actualizar();
            }
        }
    }

    private void bDeshacerTodoActionPerformed(ActionEvent evt) {
        cpr.deshacerTodasCompuestas();
        actualizar();
    }

    public void actualizar(){
        cpr.cargarCompuestas();
        pl1.lista.setPrototypeCellValue("PRUEBA");
        pl1.model = new AbstractListModel(){
            public int getSize() { return cpr.sizeCompuestas(); }
            public Object getElementAt(int index) {
                //System.out.println("ev indice "+index);
                return cpr.obtCompuesta(index); }
        };
        pl1.lista.setModel(pl1.model);
    }

    public void actualizarListaCongresistas(int id){
        cpr.cargarCongresistasComp(id);
        pl2.lista.setPrototypeCellValue("PRUEBA");
        pl2.model = new AbstractListModel(){
            public int getSize() { return cpr.sizeCongresistasComp(); }
            public Object getElementAt(int index) {
                //System.out.println("ev indice "+index);
                return cpr.obtCongresistaComp(index); }
        };
        pl2.lista.setModel(pl2.model);
    }

}
