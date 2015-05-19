package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jose on 09/05/2015.
 */
public class VistaMenu extends JPanel {

    private JButton b1, b2, b3, b4, b5, b6;
    private ActionListener l;

    public VistaMenu(){
        b1 = new JButton("Congreso");
        b2 = new JButton("Eventos");
        b3 = new JButton("Relaciones");
        b4 = new JButton("Grafo");
        b5 = new JButton("Búsqueda de comunidades");
        b6 = new JButton("Salir");


        l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==b1) {

                }
                else if (e.getSource()==b2) {

                }
                else if (e.getSource()==b3) {

                }
                else if (e.getSource()==b4) {

                }
                else if (e.getSource()==b5) {

                }
                else if (e.getSource()==b6) {
                    System.exit(0);
                }
            }
        };

        setLayout(new GridLayout(3,2,50,15));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 35, 40));

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        b1.addActionListener(l);
        b2.addActionListener(l);
        b3.addActionListener(l);
        b4.addActionListener(l);
        b5.addActionListener(l);
        b6.addActionListener(l);
    }
}
