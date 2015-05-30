package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelRelaciones extends JPanel {

    private CPRelaciones cpr;
    private JPanel pan;

    public PanelRelaciones(CPRelaciones cpRelaciones) throws Exception {
        cpr = cpRelaciones;
        pan = cpr.obtPanelRC();

        JToolBar menu = new JToolBar("");
        menu.setFloatable(false);

        JButton bCongresista = new JButton("Por Congresista");
        bCongresista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                try {
                    pan = cpr.obtPanelRC();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                add(pan);
                invalidate();
                revalidate();
                repaint();
            }
        });

        JButton bEvento = new JButton("Por Evento");
        bEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                pan = cpr.obtPanelRE();
                add(pan);
                invalidate();
                revalidate();
                repaint();
            }
        });

        JButton bTodas = new JButton("Mostrar todas");
        bTodas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                pan = cpr.obtPanelRG();
                add(pan);
                invalidate();
                revalidate();
                repaint();
            }
        });

        bCongresista.setFont(new java.awt.Font("Sans Serif", 0, 17));
        bEvento.setFont(new java.awt.Font("Sans Serif", 0, 17));
        bTodas.setFont(new java.awt.Font("Sans Serif", 0, 17));

        menu.add(bCongresista);
        menu.add(bEvento);
        menu.add(bTodas);

        setLayout(new BorderLayout());
        add(menu, BorderLayout.PAGE_START);
        add(pan, BorderLayout.CENTER);
    }

}
