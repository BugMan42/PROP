package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelRelaciones extends Panel {

    private CPRelaciones cpr;
    private JPanel pan;

    public PanelRelaciones(CPRelaciones cpRelaciones) {
        cpr = cpRelaciones;
        pan = cpr.obtPanelRG();

        JToolBar menu = new JToolBar("");
        menu.setFloatable(false);

        JButton bTodas = new JButton("Todas las relaciones");
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

        JButton bCongresista = new JButton("Por Congresista");
        bCongresista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                pan = cpr.obtPanelRC();
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

        JButton bCompuestas = new JButton("Relaciones compuestas");
        bCompuestas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(pan);
                pan = cpr.obtPanelRCO();
                add(pan);
                invalidate();
                revalidate();
                repaint();
            }
        });

        bTodas.setFont(new java.awt.Font("Sans Serif", 0, 17));
        bCongresista.setFont(new java.awt.Font("Sans Serif", 0, 17));
        bEvento.setFont(new java.awt.Font("Sans Serif", 0, 17));
        bCompuestas.setFont(new java.awt.Font("Sans Serif", 0, 17));

        menu.add(bTodas);
        menu.add(bCongresista);
        menu.add(bEvento);
        //menu.add(bCompuestas);

        setLayout(new BorderLayout());
        add(menu, BorderLayout.PAGE_START);
        add(pan, BorderLayout.CENTER);
    }

}
