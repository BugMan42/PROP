package Presentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jose on 29/05/15.
 */
public class PanelListaSimple extends JPanel {

    protected JList lista;
    protected JScrollPane scrollPane1;
    protected JLabel titulo;
    protected String[] bloque;
    protected int totalBloques;
    protected int bloque1, bloque2;
    protected int tam_bloque2;
    protected DefaultListModel model;

    public PanelListaSimple(){
        initGUI();
    }

    private void initGUI(){
        lista = new JList();
        scrollPane1 = new JScrollPane(lista);
        titulo = new JLabel();
        bloque = new String[0];
        bloque1 = bloque2 = -1;

        titulo.setFont(new java.awt.Font("Ubuntu", Font.BOLD, 18));


        setMinimumSize(new Dimension(281, 300));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(titulo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(titulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(3, 3, 3)
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
        );
    }

}
