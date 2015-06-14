package Presentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jose on 13/06/15.
 */
public abstract class PanelListaInfo extends Panel {

    protected JSplitPane sp1, sp2, sp3;
    protected PanelListaSimple pl1, pl2;
    protected JButton band;
    protected JButton bor;
    protected JButton bnot;
    protected JButton bdeshacer;
    protected JButton bdeshacertodo;
    protected JLabel jLabel1, jLabel2, lConj;

    public PanelListaInfo(){
        initGUI();
    }

    private void initGUI() {
        sp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        pl1 = new PanelListaSimple();
        pl2 = new PanelListaSimple();
        band = new JButton();
        bor = new JButton();
        bnot = new JButton();
        bdeshacer = new JButton();
        bdeshacertodo = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        lConj = new JLabel();

        band.setText("And");
        bor.setText("Or");
        bnot.setText("Not");
        bdeshacer.setText("Deshacer");
        bdeshacertodo.setText("Deshacer todo");
        jLabel1.setText("Información");
        jLabel2.setText("Conjunto:");

        jLabel1.setFont(new java.awt.Font("Ubuntu", Font.BOLD, 18));
        jLabel2.setFont(new java.awt.Font("Ubuntu", Font.BOLD, 18));
        lConj.setFont(new java.awt.Font("Ubuntu", Font.PLAIN, 18));

        pl1.lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JPanel botones = new JPanel();
        botones.setMinimumSize(new Dimension(174, 300));
        GroupLayout layout = new GroupLayout(botones);
        botones.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(bdeshacertodo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(band, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bor, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bnot, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                        .addComponent(bdeshacer, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(band)
                                .addGap(18, 18, 18)
                                .addComponent(bor)
                                .addGap(18, 18, 18)
                                .addComponent(bnot)
                                .addGap(69, 69, 69)
                                .addComponent(bdeshacer)
                                .addGap(38, 38, 38)
                                .addComponent(bdeshacertodo)
                                .addContainerGap(145, Short.MAX_VALUE))
        );

        JPanel info = new JPanel();
        info.setMinimumSize(new Dimension(250, 300));
        GroupLayout layout2 = new GroupLayout(info);
        info.setLayout(layout2);
        layout2.setHorizontalGroup(
                layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout2.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(lConj))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout2.setVerticalGroup(
                layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout2.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1)
                                .addGap(80, 80, 80)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lConj)
                                .addContainerGap(398, Short.MAX_VALUE))
        );


        sp1.setLeftComponent(pl1);
        sp1.setRightComponent(sp2);
        sp2.setLeftComponent(botones);
        sp2.setRightComponent(sp3);
        sp3.setLeftComponent(info);
        sp3.setRightComponent(pl2);

        GroupLayout ly = new GroupLayout(this);
        setLayout(ly);
        ly.setHorizontalGroup(
                ly.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sp1, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        ly.setVerticalGroup(
                ly.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sp1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

    }




}
