package Presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USUARIO on 22/05/2015.
 */
public class PanelEventos extends PanelLista {
    CPEventos cpe;

    private JLabel lbnombre;
    private JTextField ctnombre;
    private JLabel lbfecha;
    private JTextField ctfecha;
    private JLabel lbimportancia;
    private JTextField ctimportancia;
    private JLabel lbtipo;
    private JComboBox<String> cbtipo;
    private JButton btguardar;
    private JButton btlimpiar;
    private JLabel lbinfo;

    public PanelEventos(CPEventos cpEventos) {
        super();
        cpe = cpEventos;
        inicializar();
    }

    private void inicializar() {

        lbnombre = new JLabel("Nombre:");
        ctnombre = new JTextField();
        ctnombre.setText("");

        lbfecha = new JLabel("Fecha:");
        ctfecha = new JTextField();
        ctfecha.setText("");

        lbimportancia = new JLabel("Importancia:");
        ctimportancia = new JTextField();
        ctimportancia.setText("");

        lbtipo = new JLabel("Tipo:");
        cbtipo = new JComboBox<String>();
        cbtipo.setModel(new DefaultComboBoxModel<String>(new String[]{"Seleccione un tipo", "Votación", "Reunión Profesional", "Reunión Personal", "Acto Oficial", "Acto No Oficial"}));

        btguardar = new JButton();
        btlimpiar = new JButton();

        lbinfo = new JLabel();
        lbinfo.setVisible(false);

        btguardar.setText("Guardar");
        btguardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = ctnombre.getText();
                String fecha = ctfecha.getText();
                String importanciaStr = ctimportancia.getText();
                if (!validar(nombre, fecha, importanciaStr, cbtipo.getSelectedIndex())) {
                    mostrarmensaje("Debe ingresar todos los campos");
                    return;
                }
                try {
                    int importancia = Integer.parseInt(importanciaStr);
                    String tipo = (String) cbtipo.getSelectedItem();
                    limpiarcampos();
                    if (tipo.equals("Votación")) cpe.obtCCE().AgregarVotacion(nombre, fecha, importancia);
                    else if (tipo.equals("Reunión Profesional")) cpe.obtCCE().AgregarReunionProfesional(nombre, fecha, importancia);
                    else if (tipo.equals("Reunión Personal")) cpe.obtCCE().AgregarReunionPersonal(nombre, fecha, importancia);
                    else if (tipo.equals("Acto Oficial")) cpe.obtCCE().AgregarActoOficial(nombre, fecha, importancia);
                    else if (tipo.equals("Acto No Oficial")) cpe.obtCCE().AgregarActoNoOficial(nombre, fecha, importancia);
                } catch (Exception ex) {
                    mostrarmensaje("Error en el ingreso");
                }
            }
        });

        btlimpiar.setText("Limpiar");
        btlimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarcampos();
            }
        });

        JPanel right = new JPanel();
        right.add(lbnombre);
        right.add(ctnombre);
        right.add(lbfecha);
        right.add(ctfecha);
        right.add(lbimportancia);
        right.add(ctimportancia);
        right.add(lbtipo);
        right.add(cbtipo);
        right.add(lbinfo);
        right.add(btguardar);
        right.add(btlimpiar);

        //Obtenemos el SplitPanel de la clase padre y le asignamos el panel a la parte rightecha
        obtSp().setRightComponent(right);
    }

    private void mostrarmensaje(String s) {
        lbinfo.setText(s);
        lbinfo.setVisible(true);
    }
    private void limpiarcampos() {
        ctnombre.setText("");
        ctfecha.setText("");
        ctimportancia.setText("");
        cbtipo.setSelectedIndex(0);
        lbinfo.setVisible(false);
        
    }

    private boolean validar(String nombre, String fecha, String importancia, int i) {
        return !nombre.equals("") && !fecha.equals("") && !importancia.equals("") && i != 0;
    }
}
