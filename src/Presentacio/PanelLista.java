package Presentacio;

import javax.swing.*;
import java.awt.*;


public class PanelLista extends Panel {

    //Referència al controlador de presentació que crea la vista
    JSplitPane sp;
    JList jlist;
    JButton addb;
    JButton delb;
    JScrollPane jScrollPane1;

    public PanelLista(VistaPrincipal v) {

        super(v);
        // Inicializa los componentes de la ventana
        initUI();
    }

    public PanelLista()
    {
        super();
        initUI();
    }

    public JList obtLista()
    {
        return jlist;
    }

    public JButton obtAgrBoton()
    {
        return addb;
    }

    public JButton obtElimBoton()
    {
        return delb;
    }

    public JSplitPane obtSP()
    {
        return sp;
    }

    private void initUI()
    {
        jScrollPane1 = new javax.swing.JScrollPane();
        jlist = new javax.swing.JList();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlist.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4", "Item 3", "Item 4" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlist);

        /*GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );*/

        //final DefaultListModel def = new DefaultListModel();
        //jlist = new JList(def);

        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setLayoutOrientation(JList.VERTICAL);
        //Acción realizada al seleccionar un elemento
        //Las acciones las tendrán que definir

        //JScrollPane scroll = new JScrollPane(list);

        //Botón eliminar
        ImageIcon i_del = new ImageIcon("images/del.png");
        delb = new JButton(i_del);

        //Botón añadir
        ImageIcon i_add = new ImageIcon("images/add.png");
        addb = new JButton(i_add);

        JPanel izq = new JPanel();
        GroupLayout izq_lay = new GroupLayout(izq);
        izq.setLayout(izq_lay);
        izq_lay.setAutoCreateGaps(true);
        izq_lay.setAutoCreateContainerGaps(true);

        izq_lay.setHorizontalGroup(
                izq_lay.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(izq_lay.createSequentialGroup()
                                        .addComponent(addb)
                                        .addComponent(delb)
                        )
                        .addComponent(jScrollPane1)
        );

        izq_lay.setVerticalGroup(
                izq_lay.createSequentialGroup()
                        .addGroup(izq_lay.createParallelGroup()
                                        .addComponent(addb)
                                        .addComponent(delb)
                        )
                        .addComponent(jScrollPane1)
        );

        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setLeftComponent(izq);
        sp.setBorder(BorderFactory.createLineBorder(Color.black));
        GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        //add(sp);

    }

}
