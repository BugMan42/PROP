package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jose on 29/05/15.
 */
public abstract class Panel3Listas extends JPanel {

    protected JSplitPane sp1, sp2, sp3;
    protected PanelListaSimple pl1, pl2, pl3;
    protected JButton bañadir;
    protected JButton beliminar;
    protected JComboBox cbvoto;
    protected JLabel jLabel1;

    public Panel3Listas(){
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
        cbvoto = new JComboBox();
        jLabel1 = new JLabel();

        bañadir.setText("Añadir");
        beliminar.setText("Eliminar");
        cbvoto.setModel(new DefaultComboBoxModel(new String[]{"Positivo", "Negativo", "Blanco", "Abstencion", "Nulo"}));
        cbvoto.setEnabled(false);
        jLabel1.setText("Seleccionar voto:");
        jLabel1.setEnabled(false);

        pl1.lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pl2.lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        pl3.lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JPanel botones = new JPanel();
        botones.setMinimumSize(new Dimension(174, 300));
        GroupLayout layoutb = new GroupLayout(botones);
        botones.setLayout(layoutb);
        layoutb.setHorizontalGroup(
                layoutb.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layoutb.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layoutb.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layoutb.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layoutb.createSequentialGroup()
                                                .addGroup(layoutb.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(cbvoto, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(bañadir, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(beliminar, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                                .addContainerGap())))
        );
        layoutb.setVerticalGroup(
                layoutb.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layoutb.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(bañadir)
                                .addGap(18, 18, 18)
                                .addComponent(beliminar)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbvoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(273, Short.MAX_VALUE))
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
