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

    private CPRelaciones cpr;
    private JFileChooser fc;

    public PanelRelacionesGeneral(CPRelaciones c){
        cpr = c;
        inicializar();
    }

    public void inicializar() {
        pl1.titulo.setText("Congresistas");
        pl2.titulo.setText("Eventos");
        pl3.titulo.setText("Relaciones");
        fc = new JFileChooser();

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

        bguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarActionPerformed(evt);
            }
        });

        bcargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCargarActionPerformed(evt);
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

        actualizaListaRelaciones();
    }

    private void actualizaListaRelaciones(){
        cpr.reiniciarCacheRel();
        pl3.lista.setPrototypeCellValue("PRUEBA");
        pl3.model = new AbstractListModel(){
            public int getSize() { return cpr.sizeRelaciones(); }
            public Object getElementAt(int index) {
                //System.out.println("rel indice "+index);
                return cpr.obtRelacionesCache(index); }
        };
        pl3.lista.setModel(pl3.model);
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
            actualizaListaRelaciones();
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
            actualizaListaRelaciones();
        }
    }

    private void bAñadirRandomActionPerformed(ActionEvent evt) {
        cpr.agregarRelacionRandom((Integer)nr.getValue());
        actualizaListaRelaciones();

    }

    private void bEliminarTodasActionPerformed(ActionEvent evt) {
        if (!cpr.esVacioRelaciones()) {
            cpr.eliminarRelaciones();
            actualizaListaRelaciones();
        }
    }

    private void bGuardarActionPerformed(ActionEvent evt) {
        if (!cpr.esVacioRelaciones()) {
            int op = fc.showSaveDialog(this);
            if (op == JFileChooser.APPROVE_OPTION)
                cpr.guardar(fc.getSelectedFile().getAbsolutePath());
        }
    }

    private void bCargarActionPerformed(ActionEvent evt) {
        int op = fc.showOpenDialog(this);
        if (op == JFileChooser.APPROVE_OPTION)
            cpr.cargar(fc.getSelectedFile().getAbsolutePath());
    }

}
