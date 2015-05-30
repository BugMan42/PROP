package Presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class PanelEventos extends PanelLista {
    CPEventos cpe;

    private String[] defecto = {"Introduzca un nombre", "Introduzca una fecha", "Introduzca una importancia"};

    private JLabel lbnombre;
    private JTextField ctnombre;
    private JLabel lbfecha;
    private JTextField ctfecha;
    private JLabel lbimportancia;
    private JTextField ctimportancia;
    private JLabel lbtipo;
    private JComboBox<String> cbtipo;
    private JButton btlimpiar;
    private JButton btagregar;
    private JButton bteliminar;
    private JButton btmodificar;
    private JButton bteliminarTodo;
    private JSpinner contador;
    private JButton btagregarRandom;
    private JButton btcargarTodo;
    private JButton btguardarTodo;
    private JLabel lbinfo;
    private JList lista;

    public PanelEventos(CPEventos cpEventos) {
        super();
        cpe = cpEventos;
        inicializar();
    }

    private void inicializar() {

        crearComponentesVista();

        agregarFormatoComponentes();

        actualizarLista();

        agregarAccionesJtext();

        agregarAccionesBotones();

        JPanel right = new JPanel();

        asignaComponentesPanel(right);

        //Obtenemos el SplitPanel de la clase padre y le asignamos el panel a la parte derecha
        obtSp().setRightComponent(right);
        crearLayout(right);
    }

    private void crearComponentesVista() {
        lbnombre = new JLabel("Nombre:");
        ctnombre = new JTextField(defecto[0]);
        lbfecha = new JLabel("Fecha:");
        ctfecha = new JTextField(defecto[1]);
        lbimportancia = new JLabel("Importancia:");
        ctimportancia = new JTextField(defecto[2]);
        lbtipo = new JLabel("Tipo:");
        cbtipo = new JComboBox<String>();
        cbtipo.setModel(new DefaultComboBoxModel<String>(new String[]{"Seleccione un tipo", "Votacion", "ReunionProfesional", "ReunionPersonal", "ActoOficial", "ActoNoOficial"}));
        lbinfo = new JLabel(" ");

        btlimpiar = new JButton("Limpiar Campos");
        btagregar = new JButton("Agregar Evento");
        bteliminar = new JButton("Eliminar Evento");
        contador = new JSpinner();
        btagregarRandom = new JButton("Agregar Evento Random");
        btmodificar = new JButton("Modificar Evento");
        bteliminarTodo = new JButton("Eliminar CjtEventos");
        btguardarTodo = new JButton("Guardar CjtEventos");
        btcargarTodo = new JButton("Cargar CjtEventos");

        lista = obtJlist();
    }

    private void agregarFormatoComponentes() {
        lbnombre.setFont(new java.awt.Font("Ubuntu", 0, 18));
        ctnombre.setFont(new java.awt.Font("Ubuntu", 0, 18));
        ctnombre.setForeground(Color.GRAY);
        lbfecha.setFont(new java.awt.Font("Ubuntu", 0, 18));
        ctfecha.setFont(new java.awt.Font("Ubuntu", 0, 18));
        ctfecha.setForeground(Color.GRAY);
        lbimportancia.setFont(new java.awt.Font("Ubuntu", 0, 18));
        ctimportancia.setFont(new java.awt.Font("Ubuntu", 0, 18));
        ctimportancia.setForeground(Color.GRAY);
        lbtipo.setFont(new java.awt.Font("Ubuntu", 0, 18));
        cbtipo.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lbinfo.setFont(new java.awt.Font("Ubuntu", 0, 18));

        btlimpiar.setFont(new java.awt.Font("Ubuntu", 0, 18));
        btagregar.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bteliminar.setFont(new java.awt.Font("Ubuntu", 0, 18));
        contador.setModel(new SpinnerNumberModel(1, 1, 2000000, 1));
        contador.setFont(new java.awt.Font("Ubuntu", 0, 18));
        btagregarRandom.setFont(new java.awt.Font("Ubuntu", 0, 18));
        btmodificar.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bteliminarTodo.setFont(new java.awt.Font("Ubuntu", 0, 18));
        btguardarTodo.setFont(new java.awt.Font("Ubuntu", 0, 18));
        btcargarTodo.setFont(new java.awt.Font("Ubuntu", 0, 18));
    }

    private void actualizarLista() {
        String info[] = {"No hay eventos creados"};
        if (cpe.obtCCE().size() == 0) lista.setListData(info);
        else lista.setListData(cpe.obtCCE().ConsultarTodosEventosP().toArray());
    }

    private void agregarAccionesJtext() {
        ctnombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                limpiarTexto(ctnombre, 0);
                ctnombre.setForeground(Color.BLACK);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (estaVacio(ctnombre)) {
                    ctnombre.setText(defecto[0]);
                    ctnombre.setForeground(Color.GRAY);
                }
            }
        });

        ctfecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                limpiarTexto(ctfecha, 1);
                ctfecha.setForeground(Color.BLACK);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (estaVacio(ctfecha)) {
                    ctfecha.setText(defecto[1]);
                    ctfecha.setForeground(Color.GRAY);
                }
            }
        });

        ctimportancia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                limpiarTexto(ctimportancia, 2);
                ctimportancia.setForeground(Color.BLACK);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (estaVacio(ctimportancia)) {
                    ctimportancia.setText(defecto[2]);
                    ctimportancia.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void limpiarTexto(JTextField ct, int i) {
        if (ct.getText().equals(defecto[i])) ct.setText("");
    }

    private boolean estaVacio(JTextField ct) {
        return ct.getText().equals("");
    }

    private void agregarAccionesBotones() {
        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lista.getSelectedIndex() != -1) {
                    if (!lista.getSelectedValue().equals("No hay eventos creados")) {
                        String s = (String) lista.getSelectedValue();
                        String formu[] = s.split("\\s");
                        rellenarFormulario(formu);
                    }
                }
            }
        });

        btlimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarcampos();
            }
        });

        btagregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btagregarAccion(e);
            }
        });

        bteliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bteliminarAccion(e);
            }
        });

        btagregarRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btagregarRandomAccion(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btmodificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btmodificarAccion(e);
            }
        });

        bteliminarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bteliminarTodoAccion(e);
            }
        });

        btguardarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btguardarTodoAccion(e);
            }
        });

        btcargarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btcargarTodoAccion(e);
            }
        });
    }

    private void rellenarFormulario(String info[]) {
        cbtipo.setSelectedItem(info[0]);
        ctnombre.setForeground(Color.BLACK);
        ctnombre.setText(info[1]);
        ctfecha.setForeground(Color.BLACK);
        ctfecha.setText(info[2]);
        ctimportancia.setForeground(Color.BLACK);
        ctimportancia.setText(info[3]);
    }

    private void btagregarAccion(ActionEvent e) {
        String nombre = ctnombre.getText();
        String fecha = ctfecha.getText();
        String importanciaStr = ctimportancia.getText();
        if (!validar(nombre, fecha, importanciaStr, cbtipo.getSelectedIndex())) {
            mostrarmensaje("Debe ingresar todos los campos");
            return;
        }
        try {
            int importancia = Integer.parseInt(importanciaStr);
            switch (cbtipo.getSelectedIndex()) {
                case 0:
                    System.out.println("revisa tu codigo");
                    break;
                case 1:
                    cpe.obtCCE().AgregarVotacion(nombre, fecha, importancia);
                    break;
                case 2:
                    cpe.obtCCE().AgregarReunionProfesional(nombre, fecha, importancia);
                    break;
                case 3:
                    cpe.obtCCE().AgregarReunionPersonal(nombre, fecha, importancia);
                    break;
                case 4:
                    cpe.obtCCE().AgregarActoOficial(nombre, fecha, importancia);
                    break;
                case 5:
                    cpe.obtCCE().AgregarActoNoOficial(nombre, fecha, importancia);
                    break;
                default:
                    System.out.println("Imposible");
            }
            lbinfo.setText(" ");
            actualizarLista();
        }
        catch (NumberFormatException a) {
            mostrarmensaje("La fecha y la importancia tiene que tener numeros");
        }
        catch (Exception ex) {
            ponerRojo(ex.getMessage());
            mostrarmensaje(ex.getMessage());
        }
    }

    private boolean validar(String nombre, String fecha, String importancia, int i) {
        return !nombre.equals(defecto[0]) && !fecha.equals(defecto[1]) && !importancia.equals(defecto[2]) && i != 0;
    }

    private void limpiarcampos() {
        ctnombre.setText(defecto[0]);
        ctnombre.setForeground(Color.GRAY);
        ctfecha.setText(defecto[1]);
        ctfecha.setForeground(Color.GRAY);
        ctimportancia.setText(defecto[2]);
        ctimportancia.setForeground(Color.GRAY);
        cbtipo.setSelectedIndex(0);
        lbinfo.setText(" ");
        lista.clearSelection();
    }

    private void mostrarmensaje(String s) {
        lbinfo.setText(s);
    }

    private void ponerRojo(String s) {
        String data[] = s.split(":");
        if (data.length == 2) {
            String aux[] = data[0].split("\\s");
            if (aux[0].equals("Nombre")) ctnombre.setForeground(Color.red);
            if (aux[0].equals("Fecha")) ctfecha.setForeground(Color.red);
            else if (aux[0].equals("Importancia")) ctimportancia.setForeground(Color.red);
        }
        mostrarmensaje(data[0]);
    }

    /////////////////////////////////////////HACER///////////////////////////////
    private void bteliminarAccion(ActionEvent e) {
        if (lista.getSelectedIndex() == -1 || lista.getSelectedValue().equals("No hay eventos creados")) mostrarmensaje("Primero selecciona el elemento");
        else {
            try {
                List<String> elementos = lista.getSelectedValuesList();
                for (String s : elementos) {
                    String aux[] = s.split(" ");
                    cpe.obtCCE().EliminarEvento(aux[1], aux[2], cpe.obtCPR().obtCR());
                }
                lbinfo.setText(" ");
                actualizarLista();
            }
            catch (Exception ex) {
                mostrarmensaje(ex.getMessage());
            }
        }
    }

    private void btmodificarAccion(ActionEvent e) {

    }

    private void btagregarRandomAccion(ActionEvent e) throws Exception {
        cpe.obtCCE().AgregarEventoRandom((Integer)contador.getValue());
        lbinfo.setText(" ");
        actualizarLista();
    }

    private void bteliminarTodoAccion(ActionEvent e) {

    }

    private void btguardarTodoAccion(ActionEvent e) {

    }

    private void btcargarTodoAccion(ActionEvent e) {

    }



    private void asignaComponentesPanel(JPanel right) {
        right.add(lbnombre);
        right.add(ctnombre);
        right.add(lbfecha);
        right.add(ctfecha);
        right.add(lbimportancia);
        right.add(ctimportancia);
        right.add(lbtipo);
        right.add(cbtipo);
        right.add(lbinfo);
        right.add(btagregar);
        right.add(btlimpiar);
    }

    private void crearLayout(JPanel right) {
        GroupLayout gl = new GroupLayout(right);
        right.setLayout(gl);
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);
        gl.setHorizontalGroup(
                gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbnombre)
                                                        .addComponent(ctnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbfecha)
                                                        .addComponent(ctfecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                        )
                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbimportancia)
                                                        .addComponent(ctimportancia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbtipo)
                                                        .addComponent(cbtipo, 220, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                        )
                        .addComponent(btlimpiar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbinfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                        .addComponent(btagregar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bteliminar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                        .addComponent(contador, 225, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btagregarRandom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(btmodificar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btguardarTodo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(bteliminarTodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btcargarTodo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                        )
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                                        .addComponent(lbnombre)
                                                        .addComponent(ctnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        )
                                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                                        .addComponent(lbfecha)
                                                        .addComponent(ctfecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        )
                        )
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                                        .addComponent(lbimportancia)
                                                        .addComponent(ctimportancia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        )
                                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                                        .addComponent(lbtipo)
                                                        .addComponent(cbtipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        )
                        )
                        .addComponent(btlimpiar, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(lbinfo, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btagregar, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                                        .addComponent(bteliminar, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(contador, 34, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                                        .addComponent(btagregarRandom, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                                        .addComponent(btmodificar, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                                                        .addComponent(btguardarTodo, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                                        )
                                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                                        .addComponent(bteliminarTodo, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                                                        .addComponent(btcargarTodo, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                                        )
                        )
        );
    }

    protected void boxSortActionPerformed(ActionEvent evt) {

    }
    //TODO MODIFICAR
    //Modificar para buscar
    protected  void buttonSearchActionPerformed(ActionEvent evt) {

    }
    //TODO MODIFICAR
    //Modifcar para buscar, guardar como quieras
    //protected abstract void boxSearchActionPerformed(ActionEvent evt);
    protected void setBoxSort() {
        boxSort.setModel(new DefaultComboBoxModel<String>(new String[]{"Orden Nombre", "Orden fecha", "Orden importancia"}));
        boxSort.setToolTipText("Orden de la lista");
    }
    //TODO MODIFICAR
    protected void setBoxSearch() {
        boxSearch.setModel(new DefaultComboBoxModel<String>(new String[]{"Por nombre", "Por fecha", "Por importancia"}));
        boxSearch.setToolTipText(" Buscar ");
    }
    //TODO MODIFICAR
    protected void textSearchTyped(KeyEvent evt) {}

}
