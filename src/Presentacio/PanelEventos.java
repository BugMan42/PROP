package Presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.List;

public class PanelEventos extends PanelLista {
    CPEventos cpe;

    private String[] defecto = {"Introduzca un nombre", "Introduzca una importancia"};

    private JFileChooser guardar;
    private JFileChooser cargar;

    private AbstractListModel<String> bloque;

    private JLabel lbnombre;
    private JTextField ctnombre;
    private JLabel lbfecha;
    private JSpinner dia;
    private JSpinner mes;
    private JSpinner any;
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

        crearFile();

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

    private void crearFile() {

        guardar = new JFileChooser();
        cargar = new JFileChooser();

        UIManager.put("FileChooser.lookInLabelText", "Buscar en:");
        SwingUtilities.updateComponentTreeUI(cargar);
        cargar.setAcceptAllFileFilterUsed(true);
        cargar.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        cargar.addChoosableFileFilter(new FileNameExtensionFilter(".in", "in"));
        cargar.addChoosableFileFilter(new FileNameExtensionFilter(".out", "out"));
        UIManager.put("FileChooser.lookInLabelText", "Guardar en:");
        SwingUtilities.updateComponentTreeUI(guardar);
        guardar.setAcceptAllFileFilterUsed(true);
        guardar.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        guardar.addChoosableFileFilter(new FileNameExtensionFilter(".in", "in"));
        guardar.addChoosableFileFilter(new FileNameExtensionFilter(".out", "out"));
    }


    private void crearComponentesVista() {

        lbnombre = new JLabel("Nombre:");
        ctnombre = new JTextField(defecto[0]);
        lbfecha = new JLabel("Fecha:");
        dia = new JSpinner();
        mes = new JSpinner();
        any = new JSpinner();
        lbimportancia = new JLabel("Importancia:");
        ctimportancia = new JTextField(defecto[1]);
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
        ctnombre.setMinimumSize(new Dimension(225, 34));
        lbfecha.setFont(new java.awt.Font("Ubuntu", 0, 18));
        Calendar c = Calendar.getInstance();
        dia.setModel(new SpinnerNumberModel(c.get(Calendar.DATE), 1, 31, 1));
        dia.setFont(new java.awt.Font("Ubuntu", 0, 18));
        dia.setForeground(Color.BLACK);
        dia.setMinimumSize(new Dimension(75, 34));
        mes.setModel(new SpinnerNumberModel(c.get(Calendar.MONTH)+1, 1, 12, 1));
        mes.setFont(new java.awt.Font("Ubuntu", 0, 18));
        mes.setForeground(Color.BLACK);
        dia.setMinimumSize(new Dimension(75, 34));
        any.setModel(new SpinnerNumberModel(c.get(Calendar.YEAR), 1, 9999, 1));
        any.setFont(new java.awt.Font("Ubuntu", 0, 18));
        any.setForeground(Color.BLACK);
        dia.setMinimumSize(new Dimension(75, 34));
        lbimportancia.setFont(new java.awt.Font("Ubuntu", 0, 18));
        ctimportancia.setFont(new java.awt.Font("Ubuntu", 0, 18));
        ctimportancia.setForeground(Color.GRAY);
        ctimportancia.setMinimumSize(new Dimension(225, 34));
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

    public void actualizarLista() {
        lista.setPrototypeCellValue(" ");
        cpe.refrescar();
        bloque = new AbstractListModel<String>() {
            @Override
            public int getSize() {
                if (cpe.obtCCE().size() == 0) return 1;
                else return cpe.obtCCE().size();
            }

            @Override
            public String getElementAt(int index) {
                if (cpe.obtCCE().size() == 0) return "No hay eventos creados";
                return cpe.obtEvento(index);
            }
        };
        lista.setModel(bloque);
        /*String info[] = {"No hay eventos creados"};
        if (cpe.obtCCE().size() == 0) lista.setListData(info);
        else lista.setListData(cpe.obtCCE().ConsultarTodosEventosP().toArray());*/
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

        /*ctfecha.addFocusListener(new java.awt.event.FocusAdapter() {
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
        });*/

        ctimportancia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                limpiarTexto(ctimportancia, 1);
                ctimportancia.setForeground(Color.BLACK);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (estaVacio(ctimportancia)) {
                    ctimportancia.setText(defecto[1]);
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
        switch (cpe.obtOrden()) {
            case 0:
                rellenar(info, 1, 2, 3);
                break;
            case 1:
                rellenar(info, 2, 1, 3);
                break;
            case 2:
                rellenar(info, 2, 3, 1);
                break;
            default:
                System.out.println("Caso imposible detectado en rellenarFormulario PanelEventos");
                break;
        }
    }

    private void rellenar(String[] s, int nombre, int fecha, int imp) {
        ctnombre.setForeground(Color.BLACK);
        ctnombre.setText(s[nombre]);
        String[] aux = s[fecha].split("/");
        dia.setValue(Integer.parseInt(aux[0]));
        mes.setValue(Integer.parseInt(aux[1]));
        any.setValue(Integer.parseInt(aux[2]));
        ctimportancia.setForeground(Color.BLACK);
        ctimportancia.setText(s[imp]);
    }

    private void btagregarAccion(ActionEvent e) {
        String nombre = ctnombre.getText();
        String fecha = dia.getValue() + "/" + mes.getValue() + "/" + any.getValue();
        String importanciaStr = ctimportancia.getText();
        if (!validar(nombre , importanciaStr, cbtipo.getSelectedIndex())) {
            mostrarmensaje("Debe ingresar todos los campos");
            return;
        }
        if (!validarImp(importanciaStr)) return;
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
            limpiarcampos();
            actualizarLista();
        }
        catch (NumberFormatException a) {
            mostrarmensaje("La fecha tiene que tener numeros");
        }
        catch (Exception ex) {
            ponerRojo(ex.getMessage());
        }
    }

    private boolean validar(String nombre, String importancia, int i) {
        return !nombre.equals(defecto[0]) && !importancia.equals(defecto[1]) && i != 0;
    }

    private boolean validarImp(String s) {
        if (!esNumero(s)) {
            ctimportancia.setForeground(Color.red);
            mostrarmensaje("La importancia tiene que ser un numero");
            return false;
        }
        return true;
    }

    private boolean esNumero(String s) {
        int n = s.length();
        if (s.charAt(0) != '-' && !Character.isDigit((s.charAt(0)))) return false;
        for (int i = 1; i < n; ++i) {
            if (!Character.isDigit((s.charAt(i)))) return false;
        }
        return true;
    }

    public void limpiarcampos() {
        ctnombre.setText(defecto[0]);
        ctnombre.setForeground(Color.GRAY);
        restaurarFecha();
        ctimportancia.setText(defecto[1]);
        ctimportancia.setForeground(Color.GRAY);
        cbtipo.setSelectedIndex(0);
        lbinfo.setText(" ");
        contador.setValue(1);
        lista.clearSelection();
    }

    private void restaurarFecha() {
        Calendar c = Calendar.getInstance();
        dia.setValue(c.get(Calendar.DATE));
        dia.setForeground(Color.BLACK);
        mes.setForeground(Color.BLACK);
        mes.setValue(c.get(Calendar.MONTH)+1);
        any.setForeground(Color.BLACK);
        any.setValue(c.get(Calendar.YEAR));
    }

    private void mostrarmensaje(String s) {
        lbinfo.setText(s);
    }

    private void ponerRojo(String s) {
        String data[] = s.split("\\s");
        boolean clave = false;
        if (data[0].equals("Nombre")) ctnombre.setForeground(Color.red);
        else if (data[0].equals("Fecha")) {
            dia.setForeground(Color.red);
            mes.setForeground(Color.red);
            any.setForeground(Color.red);
        }
        else if(data[0].equals("Mes")) {
            mes.setForeground(Color.red);
        }
        else if (data[0].equals("Importancia")) ctimportancia.setForeground(Color.red);
        else if (data[0].equals("Clave")) {
            ctnombre.setForeground(Color.red);
            dia.setForeground(Color.red);
            mes.setForeground(Color.red);
            any.setForeground(Color.red);
            clave = true;
        }
        //if (!clave)
            mostrarmensaje(s);
    }



    /////////////////////////////////////////HACER///////////////////////////////
    private void bteliminarAccion(ActionEvent e) {
        if (lista.getSelectedIndex() == -1 || lista.getSelectedValue().equals("No hay eventos creados"))
            mostrarmensaje("Primero selecciona el elemento");
        else {
            try {
                List<String> elementos = lista.getSelectedValuesList();
                for (String s : elementos) {
                    String aux[] = parametrosOrden(s);
                    cpe.obtCCE().EliminarEvento(aux[1], aux[2], Integer.parseInt(aux[3]), cpe.obtCPR().obtCR());
                }
                lbinfo.setText(" ");
                actualizarLista();
            } catch (Exception ex) {
                ponerRojo(ex.getMessage());
            }
        }
    }

    private String[] parametrosOrden(String s) {
        String[] aux = s.split(" ");
        //System.out.println("Mis parametros son: " + aux[0] + " " + aux[1] + " " + aux[2] + " "+ aux[3]);
        String c;
        switch (cpe.obtOrden()) {
            case 1:
                c = aux[1];
                aux[1] = aux[2];
                aux[2] = c;
                //System.out.println("Han sido reordenados asi: " + aux[0] + " " + aux[1] + " " + aux[2] + " "+ aux[3]);
                break;
            case 2:
                c = aux[1];
                aux[1] = aux[2];
                aux[2] = aux[3];
                aux[3] = c;
                System.out.println("Han sido reordenados asi: " + aux[0] + " " + aux[1] + " " + aux[2] + " "+ aux[3]);
                break;
        }
        return aux;
    }


    private void btmodificarAccion(ActionEvent e) {
        if (lista.getSelectedIndex() == -1 || lista.getSelectedValue().equals("No hay eventos creados"))
            mostrarmensaje("Primero selecciona el elemento");
        else {
            try {
                int[] indices = lista.getSelectedIndices();
                if (indices.length > 1) {
                    mostrarmensaje("Selecciona solo un elemento");
                    return;
                }
                String evento = (String) lista.getSelectedValue();
                String[] aux = parametrosOrden(evento);
                String nombre = ctnombre.getText();
                String fecha = dia.getValue() + "/" + mes.getValue() + "/" + any.getValue();
                String importancia = ctimportancia.getText();
                if (!validarImp(importancia)) return;
                cpe.obtCCE().ModificarNombreEvento(aux[1], aux[2], nombre, Integer.parseInt(aux[3]), cpe.obtCPR().obtCR());
                cpe.obtCCE().ModificarFechaEvento(nombre, aux[2], fecha, Integer.parseInt(aux[3]), cpe.obtCPR().obtCR());
                cpe.obtCCE().ModificarImpEvento(nombre, fecha, Integer.parseInt(aux[3]), Integer.parseInt(importancia));
                limpiarcampos();
                actualizarLista();
            } catch (Exception ex) {
                ponerRojo(ex.getMessage());
            }
        }
    }

    private void btagregarRandomAccion(ActionEvent e) throws Exception {
        long ini = System.currentTimeMillis();
        cpe.obtCCE().AgregarEventoRandom((Integer) contador.getValue());
        lbinfo.setText(" ");
        actualizarLista();
        //System.out.println((System.currentTimeMillis() - ini) / 1000);
    }

    private void bteliminarTodoAccion(ActionEvent e) {
        limpiarcampos();
        if (cpe.obtCCE().size() > 0) {
            cpe.obtCCE().EliminarCjtEvento(cpe.obtCPR().obtCR());
            actualizarLista();
        } else mostrarmensaje("No se puede eliminar porque no hay eventos");
    }

    private void btguardarTodoAccion(ActionEvent e) {
        if (cpe.obtCCE().size() > 0) {
            int valor = guardar.showSaveDialog(this);
            if (valor == JFileChooser.APPROVE_OPTION) {
                try {
                    cpe.obtCCE().guardar(guardar.getSelectedFile().getAbsolutePath() + guardar.getFileFilter().getDescription());
                } catch (Exception ex) {
                    mostrarmensaje(ex.getMessage());
                }
            }
        } else mostrarmensaje("No hay eventos");
    }

    private void btcargarTodoAccion(ActionEvent e) {
        int returnVal = cargar.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                cpe.obtCCE().cargar(cargar.getSelectedFile().getAbsolutePath(), cpe.obtCPR().obtCR());
            } catch (Exception a) {
                mostrarmensaje(a.getMessage());
            }
        }
        actualizarLista();
    }

    private void asignaComponentesPanel(JPanel right) {
        right.add(lbnombre);
        right.add(ctnombre);
        right.add(lbfecha);
        right.add(dia);
        right.add(mes);
        right.add(any);
        right.add(lbimportancia);
        right.add(ctimportancia);
        right.add(lbtipo);
        right.add(cbtipo);
        right.add(btlimpiar);
        right.add(btagregar);
        right.add(bteliminar);
        right.add(contador);
        right.add(btagregarRandom);
        right.add(btmodificar);
        right.add(bteliminarTodo);
        right.add(btguardarTodo);
        right.add(btcargarTodo);
        right.add(lbinfo);
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
                                                        .addComponent(ctnombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbfecha)
                                                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                                                        .addComponent(dia, 69, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(mes, 69, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(any, 69, 69, Short.MAX_VALUE)
                                                        )
                                        )
                        )
                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbimportancia)
                                                        .addComponent(ctimportancia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbtipo)
                                                        .addComponent(cbtipo, 220, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                        )
                        )
                        .addComponent(btlimpiar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                        .addComponent(btagregar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bteliminar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                        .addComponent(contador, 225, 225, Short.MAX_VALUE)
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
                        .addGroup(GroupLayout.Alignment.LEADING, gl.createSequentialGroup()
                                        .addComponent(lbinfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(dia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(mes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(any, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        )
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbinfo, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
        );
    }

    protected void boxSortActionPerformed(ActionEvent evt) {
        int op = boxSort.getSelectedIndex();
        cpe.ModOrden(op);
        labelStatus.setVisible(false);
        //labelStatus.setText("Num. eventos encontrados: 0");
        actualizarLista();
        //boxSearch.setSelectedIndex(op);
    }

    //TODO MODIFICAR
    //Modificar para buscar
    protected void buttonSearchActionPerformed(ActionEvent evt) {
        /*String busqueda = textSearch.getText();
        if (busqueda.equals("")) {
            labelStatus.setVisible(false);
            labelStatus.setText("");
            actualizarLista();
        }
        else {
            int op = obtBoxSearch().getSelectedIndex();
            switch (op) {
                case 0:
                    actualizarListaB(0, busqueda);
                    break;
                case 1:
                    actualizarListaB(1, busqueda);
                    break;
                case 2:
                    actualizarListaB(2, busqueda);
                    break;
                default:
                    break;
            }
        }*/
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
        boxSearch.setToolTipText("Busqueda");
    }
    //TODO MODIFICAR

    protected void boxSearchActionPerformed(ActionEvent e) {
        int op = boxSearch.getSelectedIndex();
        cpe.ModOrden(op);
        //boxSort.setSelectedIndex(op);
        busqueda();
    }

    private void busqueda() {
        String busqueda = textSearch.getText();
        if (busqueda.equals("")) {
            labelStatus.setVisible(false);
            //labelStatus.setText("Num. eventos encontrados: 0");
            actualizarLista();
        }
        else {
            int op = obtBoxSearch().getSelectedIndex();
            switch (op) {
                case 0:
                    if (cpe.obtOrden()!= 0) {
                        cpe.ModOrden(0);
                        cpe.obtCCE().buscarBN(busqueda);
                    }
                    break;
                case 1:
                    cpe.ModOrden(1);
                    cpe.obtCCE().buscarBF(busqueda);
                    break;
                case 2:
                    cpe.ModOrden(2);
                    cpe.obtCCE().buscarBI(busqueda);
                    break;
                default:
                    break;
            }
            labelStatus.setVisible(true);
            labelStatus.setText("Num. eventos encontrados: " + Integer.toString(cpe.obtCCE().sizeB()));
            actualizarListaB();
        }
    }

    private void busquedaDin(KeyEvent evt) {
        String busqueda = textSearch.getText();
        if (busqueda.equals("")) {
            labelStatus.setVisible(false);
            //labelStatus.setText("Num. eventos encontrados: 0");
            actualizarLista();
        }
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (busqueda.equals("")) {
                labelStatus.setVisible(false);
                //labelStatus.setText("Num. eventos encontrados: 0");
                actualizarLista();
            }
            else {
                int op = obtBoxSearch().getSelectedIndex();
                switch (op) {
                    case 0:
                        if (cpe.obtOrden() != 0) {
                            cpe.ModOrden(0);
                            cpe.obtCCE().buscarBN(busqueda);
                        }
                        break;
                    case 1:
                        if (cpe.obtOrden() != 1) {
                            cpe.ModOrden(1);
                            cpe.obtCCE().buscarBF(busqueda);
                        }
                        break;
                    case 2:
                        if (cpe.obtOrden() != 2) {
                            cpe.ModOrden(2);
                            cpe.obtCCE().buscarBI(busqueda);
                        }
                        break;
                }
                labelStatus.setVisible(true);
                labelStatus.setText("Num. eventos encontrados: " + Integer.toString(cpe.obtCCE().sizeB()));
                actualizarListaB();
            }
        }
        else {
            int op = obtBoxSearch().getSelectedIndex();
            switch (op) {
                case 0:
                    cpe.ModOrden(0);
                    cpe.obtCCE().buscarBN(busqueda);
                    break;
                case 1:
                    cpe.ModOrden(1);
                    cpe.obtCCE().buscarBF(busqueda);
                    break;
                case 2:
                    cpe.ModOrden(2);
                    cpe.obtCCE().buscarBI(busqueda);
                    break;
                default:
                    break;
            }
            labelStatus.setVisible(true);
            labelStatus.setText("Num. eventos encontrados: " + Integer.toString(cpe.obtCCE().sizeB()));
            actualizarListaB();
        }
    }

    protected void textSearchTyped(KeyEvent evt) {
        busquedaDin(evt);
    }

    public void actualizarListaB() {
        lista.setPrototypeCellValue(" ");
        cpe.refrescarB();
        bloque = new AbstractListModel<String>() {
            @Override
            public int getSize() {
                if (cpe.obtCCE().sizeB() == 0) return 1;
                else return cpe.obtCCE().sizeB();
            }
            @Override
            public String getElementAt(int index) {
                if (cpe.obtCCE().sizeB() == 0) return "No hay coincidencias";
                return cpe.obtEventoB(index);
            }
        };
        lista.setModel(bloque);
        /*String info[] = {"No hay coincidencias"};
        if (cpe.obtCCE().sizeB() == 0 || prefijo.equals("")) lista.setListData(info);
        else {
            switch (i) {
                case 0:
                cpe.ModOrdenB(0);
                lista.setListData(cpe.obtBloqueB(prefijo));
                    break;
                case 1:
                    cpe.ModOrdenB(1);
                    lista.setListData(cpe.obtBloqueB(prefijo));
                    break;
                case 2:
                    cpe.ModOrdenB(0);
                    lista.setListData(cpe.obtBloqueB(prefijo));
                    break;
            }
        }*/
    }
    /** TE PUEDE HACER FALTA*/
    protected void textSearchFocusLost() {

    }

}
