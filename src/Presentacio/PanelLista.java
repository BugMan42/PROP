package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PanelLista extends Panel {

    //Referència al controlador de presentació que crea la vista
    JSplitPane sp;
    JList jlist;
    JScrollPane jScrollPane1;
    JComboBox boxSort;
    JTextField textSearch;
    JComboBox boxSearch;
    JButton buttonSearch;

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

       // setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);

        jlist.setFont(new Font("Ubuntu", 0, 17)); // NOI18N
        jlist.setModel(new AbstractListModel() {
            String[] strings = {"00000000A Edsger Dijkstra 72 Rotterdam NT Pirata ", "00000000B Gordon Moore 86 San Francisco CA Berkeley", "00000000C Richard Hamming 82 Monterey CA Pirata", "00000000D Max Newman 87 Chealsea LO Democrata", "00000000E Allen Newll 65 San Francisco SF Pirata", "00000000F Jon-Von Neumann 53 Budapest AU Pirata", "00000000G Alan Turing 41 London LO Liberal"};

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(jlist);

        boxSort.setFont(new Font("Ubuntu", 0, 17));
        boxSort.setModel(new DefaultComboBoxModel(new String[]{"Sort By Dni", "Sort By Nombre", "Sort By Partido"}));
        boxSort.setMinimumSize(new Dimension(113, 30));
        boxSort.setPreferredSize(new Dimension(113, 30));
        boxSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boxSortActionPerformed(evt);
            }
        });

        textSearch.setFont(new Font("Ubuntu", 0, 17));
        textSearch.setText("Buscar");
        textSearch.setMinimumSize(new Dimension(83, 30));
        textSearch.setPreferredSize(new Dimension(83, 30));
        textSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textSearchActionPerformed(evt);
            }
        });

        boxSearch.setFont(new Font("Ubuntu", 0, 17));
        boxSearch.setModel(new DefaultComboBoxModel(new String[]{"Search By Dni", "Search By Nombre", "Search By Partido"}));
        boxSearch.setToolTipText("");
        boxSearch.setMinimumSize(new Dimension(134, 28));
        boxSearch.setPreferredSize(new Dimension(134, 30));

        buttonSearch.setIcon(new ImageIcon(getClass().getResource("/images/search25.png"))); // NOI18N
        buttonSearch.setMaximumSize(new Dimension(16, 16));
        buttonSearch.setMinimumSize(new Dimension(16, 16));
        buttonSearch.setPreferredSize(new Dimension(16, 16));
        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });


        JPanel panel = new JPanel();
        GroupLayout leftLayout = new GroupLayout(panel);
        panel.setLayout(leftLayout);
        leftLayout.setAutoCreateGaps(true);
        leftLayout.setAutoCreateContainerGaps(true);

        leftLayout.setHorizontalGroup(
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
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
        );

        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setLeftComponent(panel);
        sp.setBorder(BorderFactory.createLineBorder(Color.black));
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
    private void boxSortActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void buttonSearchActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void textSearchActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

   /* private void initUI()
    {
        jScrollPane2 = new  JScrollPane();
        jlist = new  JList();

        //setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);

        jlist.setModel(new  AbstractListModel() {
            String[] strings = { "00000000A Edsger Dijkstra 72 Rotterdam NT Pirata ", "00000000B Gordon Moore 86 San Francisco CA Berkeley", "00000000C Richard Hamming 82 Monterey CA Pirata", "00000000D Max Newman 87 Chealsea LO Democrata", "00000000E Allen Newll 65 San Francisco SF Pirata", "00000000F Jon-Von Neumann 53 Budapest AU Pirata", "00000000G Alan Turing 41 London LO Liberal" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        jlist.setFont(new Font("Sans Serif", 0, 17));
        jScrollPane2.setViewportView(jlist);

        /*GroupLayout layout = new  GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1,  GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1,  GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );*/

        //final DefaultListModel def = new DefaultListModel();
        //jlist = new JList(def);

        /*jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setLayoutOrientation(JList.VERTICAL);
        //Acción realizada al seleccionar un elemento
        //Las acciones las tendrán que definir

        //JScrollPane scroll = new JScrollPane(list);

        //Botón eliminar
        ImageIcon i_del = new ImageIcon(getClass().getResource("/images/del.png"));
        delb = new JButton(i_del);

        //Botón añadir
        ImageIcon i_add = new ImageIcon(getClass().getResource("/images/add.png"));
        addb = new JButton(i_add);

        JPanel left = new JPanel();
        GroupLayout leftLayout = new GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setAutoCreateGaps(true);
        leftLayout.setAutoCreateContainerGaps(true);

        leftLayout.setHorizontalGroup(
                leftLayout.createParallelGroup( GroupLayout.Alignment.LEADING)
                        .addGroup(leftLayout.createSequentialGroup()
                                        .addComponent(addb)
                                        .addComponent(delb)
                        )
                        .addComponent(jScrollPane1)
        );

        leftLayout.setVerticalGroup(
                leftLayout.createSequentialGroup()
                        .addGroup(leftLayout.createParallelGroup()
                                        .addComponent(addb)
                                        .addComponent(delb)
                        )
                        .addComponent(jScrollPane1)
        );

        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setLeftComponent(left);
        sp.setBorder(BorderFactory.createLineBorder(Color.black));
        GroupLayout layout = new  GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                        .addComponent(sp,  GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                        .addComponent(sp,  GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        //add(sp);

    }*/

}
