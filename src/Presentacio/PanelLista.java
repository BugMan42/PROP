package Presentacio;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by falc on 7/05/15.
 *
 */

public class PanelLista extends Panel {

    //Referència al controlador de presentació que crea la vista
    JSplitPane sp;
    JList list;
    JButton addb;
    JButton delb;

    public PanelLista(Vista v) {

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
        return list;
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
        final DefaultListModel def = new DefaultListModel();
        list = new JList(def);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        //Acción realizada al seleccionar un elemento
        //Las acciones las tendrán que definir

        JScrollPane scroll = new JScrollPane(list);

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
                izq_lay.createParallelGroup()
                        .addGroup(izq_lay.createSequentialGroup()
                                        .addComponent(addb)
                                        .addComponent(delb)
                        )
                        .addComponent(scroll)
        );

        izq_lay.setVerticalGroup(
                izq_lay.createSequentialGroup()
                        .addGroup(izq_lay.createParallelGroup()
                                        .addComponent(addb)
                                        .addComponent(delb)
                        )
                        .addComponent(scroll)
        );

        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setLeftComponent(izq);
        sp.setBorder(BorderFactory.createLineBorder(Color.black));
        add(sp);

    }

}
