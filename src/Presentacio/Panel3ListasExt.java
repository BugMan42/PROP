package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jose on 29/05/15.
 */
public abstract class Panel3ListasExt extends JPanel {

    protected JSplitPane sp1, sp2, sp3;
    protected PanelListaSimple pl1, pl2, pl3;
    protected JButton bañadir;
    protected JButton beliminar;
    protected JButton bañadirrandom;
    protected JButton beliminartodas;
    protected JButton bguardar;
    protected JButton bcargar;
    protected JComboBox cbvoto;
    protected JLabel jLabel1;
    protected JLabel jLabel2;
    protected JSpinner nr;

    public Panel3ListasExt(){
        initGUI();
    }

    private void initGUI(){
        sp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        pl1 = new PanelListaSimple();
        pl2 = new PanelListaSimple();
        pl3 = new PanelListaSimple();
        bañadir = new JButton();
        beliminar = new JButton();
        bañadirrandom = new JButton();
        beliminartodas = new JButton();
        bguardar = new JButton();
        bcargar = new JButton();
        cbvoto = new JComboBox();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        nr = new JSpinner();

        bañadir.setText("Añadir");
        beliminar.setText("Eliminar");
        cbvoto.setModel(new DefaultComboBoxModel(new String[]{"Positivo", "Negativo", "Blanco", "Abstencion", "Nulo"}));
        cbvoto.setEnabled(false);
        jLabel1.setText("Seleccionar voto:");
        jLabel1.setEnabled(false);
        //jLabel2.setText("Nº rel:");
        bañadirrandom.setText("Añadir aleatorias");
        beliminartodas.setText("Eliminar todas");
        bguardar.setText("Guardar");
        bcargar.setText("Cargar");

        pl1.lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        pl2.lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        pl3.lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        nr.setModel(new SpinnerNumberModel(1, 1, 9999999,1));
        nr.setPreferredSize(new Dimension(100,25));

        JPanel botones = new JPanel();
        botones.setMinimumSize(new Dimension(174, 300));
        GroupLayout lb = new GroupLayout(botones);
        botones.setLayout(lb);
        lb.setHorizontalGroup(
                lb.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(lb.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(lb.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bcargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bañadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(beliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbvoto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bañadirrandom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(beliminartodas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lb.createSequentialGroup()
                                                .addGap(0, 17, Short.MAX_VALUE)
                                                .addGroup(lb.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lb.createSequentialGroup()
                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(15, 15, 15))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lb.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(14, 14, 14)
                                                                .addComponent(nr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(14, 14, 14)))))
                                .addContainerGap())
        );
        lb.setVerticalGroup(
                lb.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(lb.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(bañadir)
                                .addGap(12, 12, 12)
                                .addComponent(beliminar)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbvoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(lb.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bañadirrandom)
                                .addGap(12, 12, 12)
                                .addComponent(beliminartodas)
                                .addGap(50, 50, 50)
                                .addComponent(bguardar)
                                .addGap(12, 12, 12)
                                .addComponent(bcargar))
        );


        sp1.setLeftComponent(pl1);
        sp1.setRightComponent(sp2);
        sp2.setLeftComponent(pl2);
        sp2.setRightComponent(sp3);
        sp3.setLeftComponent(botones);
        sp3.setRightComponent(pl3);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sp1, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sp1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }

    public void activar_voto(boolean b){
        cbvoto.setEnabled(b);
        jLabel1.setEnabled(b);
    }

}
