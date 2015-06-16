package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public abstract class PanelLista extends Panel {

    //Referència al controlador de presentació que crea la vista
    final String defaultTextLabelSearch = "Buscar";
    protected JSplitPane sp;
    protected JList jlist;
    protected JScrollPane jScrollPane1;
    protected JComboBox boxSort;
    protected JTextField textSearch;
    protected JComboBox boxSearch;
    protected JButton buttonSearch;
    protected JLabel labelStatus;
    protected javax.swing.JCheckBox checkInv;
    protected javax.swing.JCheckBox checkAuto;

    public PanelLista(VistaPrincipal v) {

        super(v);
        // Inicializa los componentes de la ventana
        initGUI();
    }

    public PanelLista() {
        super();
        initGUI();
    }

    public JSplitPane obtSp() {
        return sp;
    }
    public JList obtJlist() {
        return jlist;
    }
    public JScrollPane obtjScrollPane1() {
        return jScrollPane1;
    }
    public JComboBox obtBoxSort() {
        return boxSort;
    }
    public JTextField obtTextSearch() {
        return textSearch;
    }
    public JComboBox obtBoxSearch() {
        return boxSearch;
    }
    public JButton obtButtonSearch() {
        return buttonSearch;
    }

    private void initGUI() {

        jScrollPane1 = new JScrollPane();
        jlist = new JList();
        boxSort = new JComboBox();
        textSearch = new JTextField();
        boxSearch = new JComboBox();
        buttonSearch = new JButton();
        labelStatus = new JLabel();
        checkInv = new javax.swing.JCheckBox();
        checkAuto = new javax.swing.JCheckBox();

        checkInv.setText("Invertir Ordenación");
        checkInv.setFont(new Font("Ubuntu", 0, 17));
        checkInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInvActionPerformed(evt);
            }
        });

        checkAuto.setText("Permitir Autocompletar");
        checkAuto.setFont(new Font("Ubuntu", 0, 17));
        checkAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // checkAutoActionPerformed(evt);
            }
        });
        checkInv.setVisible(false);
        checkAuto.setVisible(false);

        jlist.setFont(new Font("Ubuntu", 0, 17));
        jScrollPane1.setViewportView(jlist);

        boxSort.setFont(new Font("Ubuntu", 0, 17));
        boxSort.setMinimumSize(new Dimension(130, 30));
        boxSort.setPreferredSize(new Dimension(130, 30));
        setBoxSort();
        boxSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boxSortActionPerformed(evt);
            }
        });

        textSearch.setFont(new Font("Ubuntu", 0, 17));
        textSearch.setText(defaultTextLabelSearch);
        textSearch.setForeground(Color.GRAY);
        textSearch.setMinimumSize(new Dimension(83, 30));
        textSearch.setPreferredSize(new Dimension(83, 30));
        textSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                //clearLabelSearch(textSearch);
                textSearchTyped(evt);
            }
        });

        textSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textSearch.setForeground(Color.BLACK);
                clearLabelSearch(textSearch);
                textSearch.setSelectionStart(0);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textSearch.getText().equals("") || textSearch.getText().charAt(0) == ' ') {
                    textSearch.setForeground(Color.GRAY);
                    textSearch.setText("Buscar");
                } else textSearchFocusLost();
            }
        });

        setBoxSearch();
        boxSearch.setFont(new Font("Ubuntu", 0, 17));
        boxSearch.setToolTipText("");
        boxSearch.setMinimumSize(new Dimension(134, 28));
        boxSearch.setPreferredSize(new Dimension(134, 30));
        boxSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxSearchActionPerformed(e);
            }
        });

        buttonSearch.setIcon(new ImageIcon(getClass().getResource("/images/search25.png"))); // NOI18N
        buttonSearch.setMaximumSize(new Dimension(17, 17));
        buttonSearch.setMinimumSize(new Dimension(17, 17));
        buttonSearch.setPreferredSize(new Dimension(17, 17));
        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        labelStatus.setFont(new java.awt.Font("Ubuntu", 0, 17));
        labelStatus.setVisible(false);
        labelStatus.setForeground(Color.BLACK);


        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(410, 300));
        GroupLayout leftLayout = new GroupLayout(panel);
        panel.setLayout(leftLayout);
        leftLayout.setAutoCreateGaps(true);
        leftLayout.setAutoCreateContainerGaps(true);

        leftLayout.setHorizontalGroup(
                leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(boxSort, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(35, 35, 60)
                                .addComponent(textSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxSearch, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonSearch, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))                        .addComponent(jScrollPane1)
                        .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                                .addComponent(checkAuto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkInv))
        );
        leftLayout.setVerticalGroup(
                leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(boxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(boxSort, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGap(4, 4, 4)
                                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(checkInv)
                                        .addComponent(checkAuto)))

        );

        //pack();
       /* leftLayout.setHorizontalGroup(
                leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(boxSort, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(35, 35, 60)
                                .addComponent(textSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxSearch, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonSearch, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                        .addComponent(labelStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftLayout.setVerticalGroup(
                leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                                .addGroup(leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(buttonSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(boxSearch, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                        .addComponent(boxSort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGap(3, 3, 3)
                                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
        );*/


        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setLeftComponent(panel);
        //sp.setBorder(BorderFactory.createLineBorder(Color.black));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sp, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sp, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        //pack();
    }

    protected  abstract void textSearchFocusLost();

    protected void clearLabelSearch(JTextField aux) {
        if (aux.getText().equals(defaultTextLabelSearch)) aux.setText("");
    }
    // lable between list and buttons
    protected void setLableStatusVisible(boolean t) {
        labelStatus.setVisible(t);
    }
    protected void setTextLabelStatus(String text) {
        labelStatus.setText(text);
    }
    protected abstract void boxSortActionPerformed(ActionEvent evt) ;
    //Modificar para buscar
    protected abstract void buttonSearchActionPerformed(ActionEvent evt);
    //Modifcar para buscar, guardar como quieras
    //protected abstract void boxSearchActionPerformed(ActionEvent evt);
    protected abstract void setBoxSort();

    protected abstract void setBoxSearch();

    protected abstract void textSearchTyped(KeyEvent evt) ;

    protected abstract void boxSearchActionPerformed(ActionEvent e);

    protected abstract void checkInvActionPerformed(ActionEvent evt);
}
