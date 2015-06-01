package Presentacio;

import Domini.TST;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class PanelCongreso extends PanelLista {
    final String[] def={"Introduzca el Nombre", "Introduzca el Apellido", "Introduzca el Dni", "Introduzca la Edad", "Introduzca la Ciudad", "Introduzca el Estado", "Introduzca el Partido"};

    //Referència al controlador de presentació que crea la vista
    CPCongreso CPC;
    boolean t;
    private int ItemOnView;
    private Boolean[] vError;
    //CPRelaciones cpr;

    AbstractListModel<String> bigData;
    JFileChooser choosersave;
    JFileChooser chooserload;
    JButton bAgregarCongresista;
    JButton bAgregarRandom;
    JButton bCargarCongreso;
    JButton bClear;
    JButton bEliminarCongresista;
    JButton bEliminarCongreso;
    JButton bGuardarCongreso;
    JButton bModificarCongresista;
    Box.Filler filler1;
    JSpinner SpinnerNum;
    JTextField textError;
    JLabel lError;
    JLabel lName;
    JLabel lSurname;
    JLabel lDni;
    JLabel lAge;
    JLabel lCity;
    JLabel lState;
    JLabel lParty;
    JTextField textName;
    JTextField textSurname;
    JTextField textDni;
    JTextField textAge;
    JTextField textCity;
    JTextField textState;
    JTextField textParty;
    JList listCongreso;


    public PanelCongreso(CPCongreso c) {
        super();
        vError = new Boolean[7];
        for (int i = 0; i < 7; ++i) {
            vError[i] = false;
        }
        CPC = c;
        // Inicializa los componentes de la ventana
        iniComp();
        createjlist();
        //updatejlist();
        iniFont();
        initGUI();

    }

    public PanelCongreso() {

        super();
        // Inicializa los componentes de la ventana
        //initUI();

    }
    private void iniFont() {
        lName.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textName.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lAge.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textSurname.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textAge.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textDni.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lDni.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lState.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textState.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lError.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lParty.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bAgregarCongresista.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bEliminarCongresista.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bAgregarRandom.setFont(new java.awt.Font("Ubuntu", 0, 18));

        bCargarCongreso.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bGuardarCongreso.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bEliminarCongreso.setFont(new java.awt.Font("Ubuntu", 0, 18));
        SpinnerNum.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bModificarCongresista.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textParty.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bClear.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lCity.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textCity.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lSurname.setFont(new java.awt.Font("Ubuntu", 0, 18));
    }
    private void iniComp() {
        lName = new JLabel();
        textName = new JTextField();
        lSurname = new JLabel();
        textSurname = new JTextField();
        lDni = new JLabel();
        textDni = new JTextField();
        lAge = new JLabel();
        textAge = new JTextField();
        lCity = new JLabel();
        textCity = new JTextField();
        lState = new JLabel();
        textState = new JTextField();
        lParty = new JLabel();
        textParty = new JTextField();
        filler1 = new Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        bClear = new JButton();
        textError = new JTextField();
        lError = new JLabel();
        bAgregarCongresista = new JButton();
        bAgregarRandom = new JButton();
        bEliminarCongresista = new JButton();
        bModificarCongresista = new JButton();
        bEliminarCongreso = new JButton();
        bCargarCongreso = new JButton();
        bGuardarCongreso = new JButton();
        SpinnerNum = new JSpinner();
        listCongreso = obtJlist();//new JList(bigData);
    }
    private void initGUI() {
        cargarFilechoser();




        // setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Referencias a los objetos superiores
        //JList listCongreso = obtJlist();
//        final DefaultListModel def = (DefaultListModel) name_list.getModel();
        //ListUpdate();
        t = true;
        setTextLabelStatus("Loading...");
        //labelStatus.setText("cargandoooooo");
        //listCongreso.setListData();

        // functions

        //Acción realizada al seleccionar un elemento
        listCongreso.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    if (listCongreso.getSelectedIndex() == -1) {
                        //No selection, disable fire button.
                        //acceptButton.setEnabled(false);

                    } else {
                        emptyLError();
                        String dato = (String) listCongreso.getSelectedValue();
                        //errorField.setText("#" + name_list.getSelectedIndex());
                        // Del congresista a los campos
                        String[] campos = dato.split(" ");

                        if (campos.length == 7) {
                            defColorText(Color.BLACK);
                            textDni.setText(campos[0]);
                            textName.setText(campos[1]);
                            textSurname.setText(campos[2]);
                            textAge.setText(campos[3]);
                            textCity.setText(campos[4]);
                            textState.setText(campos[5]);
                            textParty.setText(campos[6]);
                        } else {
                            //wtf
                            //nameField.setText(campos[0]);
                        }
                    }
                }
            }
        });

        setDefaultText();


        lName.setText("Nombre");
        textName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                clearText(textName);
                textName.setForeground(Color.BLACK);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (checkStart(textName)) {
                    textName.setText(def[0]);
                    textName.setForeground(Color.GRAY);
                }
            }
        });

        textSurname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textSurname.setForeground(Color.BLACK);
                clearText(textSurname);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (checkStart(textSurname)) {
                    textSurname.setText(def[1]);
                    textSurname.setForeground(Color.GRAY);
                }
            }
        });

        lSurname.setText("Apellido");

        lDni.setText("Dni");

        textDni.setAutoscrolls(true);
        textDni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textDni.setForeground(Color.BLACK);
                clearText(textDni);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (checkStart(textDni)) {
                    textDni.setText(def[2]);
                    textDni.setForeground(Color.GRAY);
                }
            }
        });

        lAge.setText("Edad");

        textAge.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                clearText(textAge);
                textAge.setForeground(Color.BLACK);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (checkStart(textAge)) {
                    textAge.setText(def[3]);
                    textAge.setForeground(Color.GRAY);
                }
            }
        });

        lCity.setText("Ciudad");

        textCity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textCity.setForeground(Color.BLACK);
                clearText(textCity);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (checkStart(textCity)) {
                    textCity.setForeground(Color.GRAY);
                    textCity.setText(def[4]);
                }
            }
        });

        lState.setText("Estado");

        textState.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textState.setForeground(Color.BLACK);
                clearText(textState);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (checkStart(textState)) {
                    textState.setForeground(Color.GRAY);
                    textState.setText(def[5]);
                }
            }
        });

        lParty.setText("Partido");

        textParty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textParty.setForeground(Color.BLACK);
                clearText(textParty);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (checkStart(textParty)) {
                    textParty.setForeground(Color.GRAY);
                    textParty.setText(def[6]);
                }
            }
        });

        bClear.setText("Limpiar");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        lError.setText(" ");

        bAgregarCongresista.setText("AgregarCongresista");
        bAgregarCongresista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarCongresistaActionPerformed(evt);
            }
        });

        bAgregarRandom.setText("AgregarRandom");
        bAgregarRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarRandomActionPerformed(evt);
            }
        });

        bEliminarCongresista.setText("EliminarCongresista");
        bEliminarCongresista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarCongresistaActionPerformed(evt);
            }
        });

        bModificarCongresista.setText("Modificar");
        bModificarCongresista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarCongresistaActionPerformed(evt);
            }
        });
        bEliminarCongreso.setText("EliminarCongreso");
        bEliminarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarCongresoActionPerformed(evt);
            }
        });

        bCargarCongreso.setText("CargarCongreso");
        bCargarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCargarCongresoActionPerformed(evt);
            }
        });

        bGuardarCongreso.setText("GuardarCongreso");
        bGuardarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarCongresoActionPerformed(evt);
            }
        });

        SpinnerNum.setModel(new SpinnerNumberModel(1, 1, 1000000, 1));
        //SpinnerNum.setModel(new SpinnerNumberModel(new SpinnerNumberModel(5.0, 0.0, 9.0, 1.0));


        JPanel right = new JPanel();
        right.add(lName);
        right.add(textName);
        right.add(lSurname);
        right.add(textSurname);
        right.add(lDni);
        right.add(textDni);
        right.add(lAge);
        right.add(textAge);
        right.add(lCity);
        right.add(textCity);
        right.add(lState);
        right.add(textState);
        right.add(lParty);
        right.add(textParty);
        right.add(bClear);
        right.add(lError);
        right.add(bAgregarCongresista);
        right.add(bEliminarCongresista);
        right.add(bModificarCongresista);
        right.add(bEliminarCongreso);
        right.add(SpinnerNum);
        right.add(bAgregarRandom);
        right.add(bCargarCongreso);
        right.add(bGuardarCongreso);

        //Obtenemos el SplitPanel de la clase padre y le asignamos el panel a la parte rightecha
        obtSp().setRightComponent(right);


        //GroupLayout gr = new GroupLayout(right);
        //right.setLayout(gr);
        //gr.setAutoCreateGaps(true);
        //gr.setAutoCreateContainerGaps(true);



        //TODO
        GroupLayout layout = new GroupLayout(right);
        right.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        //getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lParty, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textCity, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lCity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textDni, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lDni, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                                        .addComponent(lName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textParty, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textState)
                                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lState, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(textAge, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(textSurname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                                        .addComponent(lAge, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(2, 2, 2))))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(bCargarCongreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SpinnerNum)
                                        .addComponent(bModificarCongresista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bAgregarCongresista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(bEliminarCongresista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(bEliminarCongreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(bGuardarCongreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(bAgregarRandom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addComponent(lError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lSurname))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lDni)
                                        .addComponent(lAge))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textAge, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lCity)
                                        .addComponent(lState))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textState))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lParty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textParty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bClear))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bAgregarCongresista)
                                        .addComponent(bEliminarCongresista))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bModificarCongresista)
                                        .addComponent(bEliminarCongreso))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bAgregarRandom)
                                        .addComponent(SpinnerNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bCargarCongreso)
                                        .addComponent(bGuardarCongreso))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lError, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
        );
       /*
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lError)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(lParty, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textCity, GroupLayout.Alignment.LEADING)
                                        .addComponent(lCity, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textDni, GroupLayout.Alignment.LEADING)
                                        .addComponent(lDni, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textName, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                        .addComponent(lName, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textParty, GroupLayout.Alignment.LEADING))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(textState)
                                        .addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lState, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lSurname, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bClear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(textAge, GroupLayout.Alignment.LEADING)
                                                        .addComponent(textSurname, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                                        .addComponent(lAge, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(2, 2, 2))))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(bCargarCongreso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SpinnerNum)
                                        .addComponent(bModificarCongresista, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bAgregarCongresista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(bEliminarCongresista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(bEliminarCongreso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(bGuardarCongreso, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(bAgregarRandom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lSurname))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lDni)
                                        .addComponent(lAge))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(textDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textAge, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lCity)
                                        .addComponent(lState))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lParty)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textParty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bClear))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addComponent(lError, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(bAgregarCongresista)
                                        .addComponent(bEliminarCongresista))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(bModificarCongresista)
                                        .addComponent(bEliminarCongreso))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(bAgregarRandom)
                                        .addComponent(SpinnerNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(bCargarCongreso)
                                        .addComponent(bGuardarCongreso))
                                .addGap(30, 30, Short.MAX_VALUE))
        );*/

    }// </editor-fold>

    private boolean checkStart(JTextField aux) {
        String saux = aux.getText();
        int n = saux.length();
        return saux.equals("") || saux.charAt(0) == ' ' ;
    }

    private void clearText(JTextField aux) {
        if (!aux.getText().equals("") && aux.getText().charAt(0) == 'I') aux.setText("");
    }


    private void print(String str) {
        System.out.println(str);
    }

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {
        setDefaultText();
        jlist.clearSelection();
    }

    private void bAgregarCongresistaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        emptyLError();
        if (validarTodo()) {
            try {
                CPC.obtCC().agregarCongresista(textDni.getText(), getTextString(textName), getTextString(textSurname), Integer.parseInt(textAge.getText()), getTextString(textCity), getTextString(textState), getTextString(textParty));
                setDefaultText();
                setMsg("Congresista agregado satisfactoriamente");
                ListUpdate();
            }
            catch (NumberFormatException a) {
                setError("La edad tiene que ser un número > 0");
            }
            catch (Exception a2) {
                setError(a2.getMessage());
            }
        }
    }
    private String getTextString(JTextField aux) {
        String aux2 = "";
        String[] campos = aux.getText().split(" ");
        if (campos.length != 1) {
            for (int i = 0; i < campos.length; ++i) {
                if (i != 0) aux2 += "-";
                aux2 += (campos[i]);
            }
        }
        else aux2 = aux.getText();
        return aux2;
    }


    private void bEliminarCongresistaActionPerformed(java.awt.event.ActionEvent evt) {
        emptyLError();
        if (jlist.isSelectionEmpty()) {
            if (validarJText(textDni,2)) {
                try {
                    CPC.eliminarCongresista(textDni.getText().substring(0,9));
                    setDefaultText();
                    setMsg("Congresista eliminado satisfactoriamente");
                    ListUpdate();
                }
                catch (Exception a) {
                    setError(a.getMessage());
                }
            }
            else {
                setError("Campo Dni No valido");
                lDni.setForeground(Color.RED);
                vError[2] = true;
            }
        }
        else {
            try {
                //int[] howMany = jlist.getSelectedIndices();
                List<String> campos = jlist.getSelectedValuesList();
                for (int i = 0; i < campos.size(); ++i) {
                    String[] con = campos.get(i).split(" ");
                    CPC.eliminarCongresista(con[0]);
                }
                setDefaultText();
                setMsg("Congresistas eliminados satisfactoriamente");
                ListUpdate();
            }
            catch (Exception a) {
                setError(a.getMessage());
            }
        }
    }
    private boolean validarJText(JTextField aux, int n) {
        String[] a = aux.getText().split(" ");
        return (!aux.getText().equals(def[n])) && !(a.length == 0) ;// && !aux.getText().equals(" ") && !aux.getText().equals("");
    }
    private boolean validarJTextDni(JTextField aux) {
        return ((!aux.getText().equals(def[2])) );// && !aux.getText().equals(" ") && !aux.getText().equals("");
    }

    private void bModificarCongresistaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO no funciona
        emptyLError();
        String dato = (String) listCongreso.getSelectedValue();
        if (listCongreso.getSelectedIndices().length > 1) setError("Selecciona solo uno");
        else if (jlist.isSelectionEmpty() || dato.equals("No hay Congresistas")) {
            setError("Selecciona un Congresista");
        }
        else {
            if (validarTodo()) {
               // String dato = (String) listCongreso.getSelectedValue();
                String[] campos = dato.split(" ");
                try {
                    CPC.obtCC().modCongresista(campos[0], textDni.getText(), getTextString(textName), getTextString(textSurname), Integer.parseInt(textAge.getText()), getTextString(textCity), getTextString(textState), getTextString(textParty), CPC.obtCPR().obtCR());
                    setMsg("Congresista modificado satisfactoriamente");
                    ListUpdate();
                    //print("Todavia no ha petado");


                }
                catch (NumberFormatException a) {
                    setError("La edad tiene que ser un número > 0");
                }
                catch (Exception a2) {
                    setError(a2.getMessage());
                }
            }
        }

    }


    private void bEliminarCongresoActionPerformed(java.awt.event.ActionEvent evt) {
        emptyLError();
        setDefaultText();
        if (CPC.size() != 0) {
            CPC.eliminarCongreso();
            setMsg("Congreso eliminado satisfactoriamente");
            ListUpdate();
        }
        else {
            setError("No hay Congresistas");
        }
    }

    private void bAgregarRandomActionPerformed(java.awt.event.ActionEvent evt) {
        emptyLError();
        try {
            //long tini = System.currentTimeMillis();
            long timeini = System.nanoTime();
            //print("antes de agregar");
            Integer n = (Integer)SpinnerNum.getValue();
            //print("AGREGAR RANDOM");
            CPC.agregarCongresistaRandom(n);
            //print
            //BigData aux = (BigData) listCongreso.getModel();
            //aux.update(0);
            //print(listCongreso.getFirstVisibleIndex() + " first index");
            //print(listCongreso.getLastVisibleIndex()+" last index");
            //createjlist();
            //listCongreso.update
            //updatejlist();
            ListUpdate();
            print((System.nanoTime() - timeini) / 1000000000.0 + "");
            //print(String.valueOf("Tiempo agregar : " + (System.currentTimeMillis() - tini)));
            //print(String.valueOf("Tiempo agregar total: " + (System.currentTimeMillis() - tini)));
            //print(listCongreso.getLastVisibleIndex() + "last index");
            // print(listCongreso.getFirstVisibleIndex()+"last index");
        } catch (Exception a) {
            setError(a.getMessage());
        }
    }

    private void bCargarCongresoActionPerformed(java.awt.event.ActionEvent evt) {
        emptyLError();
        int returnVal = chooserload.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                CPC.cargar(chooserload.getSelectedFile().getAbsolutePath());
            } catch (Exception a) {
                setError(a.getMessage());
            }
        }
        ListUpdate();
    }

    private void bGuardarCongresoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        emptyLError();
        if (CPC.size() == 0) setError("No hay Congresistas");
        else {
            int returnVal = choosersave.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    CPC.guardar(choosersave.getSelectedFile().getAbsolutePath());
                } catch (Exception a) {
                    setError(a.getMessage());
                }
            }
        }
        ListUpdate();
    }

    public void ListUpdate() {
        String a[] = {"No hay Congresistas"};
        //updatejlist();
        //if (CPC.obtCC().esVacio()) listCongreso.setListData(a);
       // else {
        //long tini=System.currentTimeMillis();
            //print(tini+"");
            //print("antes de update UI");
            //listCongreso.updateUI();
            createjlist();
            //updatejlist();

            // ListModel<String> aux = listCongreso.getModel();
            //ArrayList<String> aux = CPC.obtCC().obtenerCongresoTotal();
            //print("hacemos update");
            //listCongreso.setListData(CPC.obtCC().obtenerCongresoTotal().toArray());
            //print("fin");
        //print(String.valueOf("Tiempo update UI: "+(System.currentTimeMillis()-tini)));

        //}
    }
    public void setDefaultText() {
        defColorText(Color.GRAY);
        textName.setText(def[0]);
        textSurname.setText(def[1]);
        textDni.setText(def[2]);
        textAge.setText(def[3]);
        textCity.setText(def[4]);
        textState.setText(def[5]);
        textParty.setText(def[6]);
        emptyLError();
    }
    public void emptyLError() {
        lError.setText(" ");
        lName.setForeground(Color.BLACK);
        lSurname.setForeground(Color.BLACK);
        lDni.setForeground(Color.BLACK);
        lAge.setForeground(Color.BLACK);
        lCity.setForeground(Color.BLACK);
        lState.setForeground(Color.BLACK);
        lParty.setForeground(Color.BLACK);
    }
    private void setError(String str) {
        lError.setText("");
        lError.setText(str);
        lError.setForeground(Color.RED);
    }
    private void setMsg(String str) {
        lError.setText("");
        lError.setText(str);
        lError.setForeground(Color.BLACK);
    }

    protected void boxSortActionPerformed(ActionEvent evt) {
        //labelStatus.setText(boxSort.getSelectedIndex()+"");
        switch (boxSort.getSelectedIndex()) {
            case 0:
                CPC.sortByDni();
                break;
            case 1:
                CPC.sortByName();
                break;
            case 2:
                CPC.sortBySurName();
                break;
            case 3:
                CPC.sortByAge();
                break;
            case 4:
                CPC.sortByCity();
                break;
            case 5:
                CPC.sortByState();
                break;
            case 6:
                CPC.sortByParty();
                break;
        }
        //TODO mejorar, te quedas donde estabas
        ListUpdate();
    }
    //Modificar para buscar
    protected  void buttonSearchActionPerformed(ActionEvent evt)    {
        /*try {
            //print("YOLO");
           // print(boxSearch.getSelectedIndex()+"");
            /*if (t) {
                t = false;
                labelStatus.setVisible(true);
            } else {
                t = true;
                labelStatus.setVisible(false);
            }*/
        /*
            switch (boxSearch.getSelectedIndex()) {
                case 0:
                    CPC.searchByDni(textSearch.getText());
                    ListUpdateBusqueda();
                    break;
                case 1:
                    CPC.searchByName(textSearch.getText());
                    ListUpdateBusqueda();
                    break;
                case 2:
                    CPC.searchBySurName(textSearch.getText());
                    ListUpdateBusqueda();
                    break;
                case 3:
                    CPC.searchByAge(Integer.parseInt(textSearch.getText()));
                    ListUpdateBusqueda();
                    break;
                case 4:
                    CPC.searchByCity(textSearch.getText());
                    ListUpdateBusqueda();
                    break;
                case 5:
                    CPC.searchByState(textSearch.getText());
                    ListUpdateBusqueda();
                    break;

                case 6:
                    CPC.searchByParty(textSearch.getText());
                    ListUpdateBusqueda();
                    break;
            }
        }catch (Exception a) {
            labelStatus.setVisible(true);
            labelStatus.setText(a.getMessage());
        }*/
        try {
            HardWorker op = new HardWorker();
            op.doInBackground();
        } catch (Exception a ) {
            labelStatus.setVisible(true);
            labelStatus.setText(a.getMessage());
        }
    }
    private void ListUpdateBusqueda() {
        String a[] = {"Búsqueda sin resultados"};
        if (CPC.resultados()) listCongreso.setListData(a);
        else {
            //print("todavia no");
            ArrayList<String> aux = CPC.obtCC().obtenerBusqueda();
            listCongreso.setListData(aux.toArray());
        }
    }
    //Modifcar para buscar, guardar como quieras
    //protected abstract void boxSearchActionPerformed(ActionEvent evt);
    protected void setBoxSort() {
        boxSort.setModel(new DefaultComboBoxModel(new String[]{"Orden Dni", "por Nombre", "por Apellido", "por Edad", "por Ciudad", "por Estado", "por Partido"}));
        boxSort.setToolTipText(" Ordenar ");
    }
    protected void setBoxSearch() {
        boxSearch.setModel(new DefaultComboBoxModel(new String[]{"Busca Dni", "por Nombre", "por Apellido", "por Edad", "por Ciudad", "por Estado", "por Partido"}));
        boxSearch.setToolTipText(" Buscar ");
    }
    protected void textSearchTyped(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (textSearch.getText().equals("")) ListUpdate();
            else {
                try {
                    HardWorker op = new HardWorker();
                    op.doInBackground();
                } catch (Exception a ) {
                    labelStatus.setVisible(true);
                    labelStatus.setText(a.getMessage());
                }
            }
        }
        else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
           // textSearch.setText(textSearch.getText().substring(0,text));
        }
        else {
            try {
                HardWorker op = new HardWorker();
                op.doInBackground();
            } catch (Exception a ) {
                labelStatus.setVisible(true);
                labelStatus.setText(a.getMessage());
            }
        }

    }

    private final class HardWorker extends SwingWorker<Void, Void> {
        @Override  protected Void doInBackground() throws Exception {
            //try {
                switch (boxSearch.getSelectedIndex()) {
                    case 0:
                        CPC.searchByDni(textSearch.getText());
                        ListUpdateBusqueda();
                        break;
                    case 1:
                        CPC.searchByName(textSearch.getText());
                        ListUpdateBusqueda();
                        break;
                    case 2:
                        CPC.searchBySurName(textSearch.getText());
                        ListUpdateBusqueda();
                        break;
                    case 3:
                        CPC.searchByAge(Integer.parseInt(textSearch.getText()));
                        ListUpdateBusqueda();
                        break;
                    case 4:
                        CPC.searchByCity(textSearch.getText());
                        ListUpdateBusqueda();
                        break;
                    case 5:
                        CPC.searchByState(textSearch.getText());
                        ListUpdateBusqueda();
                        break;
                    case 6:
                        CPC.searchByParty(textSearch.getText());
                        ListUpdateBusqueda();
                        break;
                }
            //}
            /*catch (Exception a) {
                labelStatus.setVisible(true);
                labelStatus.setText(a.getMessage());
            }*/
            return null;
        }
        @Override protected void done() {
            try {
                //get the data fetched above, in doInBackground()
               // List<Quote> quotes = get();
                if (true) {
                   // showUpdated(quotes);
                }
                else {
                    //fSummaryView.showStatusMessage("Failed - Please connect to the web.");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private final class HardRandom extends SwingWorker<Void, Void> {
        @Override  protected Void doInBackground() throws Exception {
            Integer n = (Integer)SpinnerNum.getValue();
            CPC.obtCC().agregarCongresistaRandom(n);
            return null;
        }
        @Override protected void done() {
            try {
                //get the data fetched above, in doInBackground()
                // List<Quote> quotes = get();
                if (true) {
                    // showUpdated(quotes);
                }
                else {
                    //fSummaryView.showStatusMessage("Failed - Please connect to the web.");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private boolean validarTodo() {
        boolean change = false;
        String errores = "";
        if (!validarJText(textName,0)) {
            errores+="Nombre";
            lName.setForeground(Color.RED);
            vError[0] = true;
            change = true;
        }
        if (!validarJText(textSurname,1)) {
            if (!change) errores+="Apellido";
            else errores+=", Apellido";
            lSurname.setForeground(Color.RED);
            vError[1] = true;
            change = true;
        }
        if (!validarJTextDni(textDni)) {
            if (!change) errores+="Dni";
            else errores+=", Dni";
            lDni.setForeground(Color.RED);
            vError[2] = true;
            change = true;
        }
        if (!validarJText(textAge,3)) {
            if (!change) errores+="Edad";
            else errores+=", Edad";
            lAge.setForeground(Color.RED);
            vError[3] = true;
        }
        if (!validarJText(textCity,4)) {
            if (!change) errores+="Ciudad";
            else errores+=", Ciudad";
            lCity.setForeground(Color.RED);
            vError[4] = true;
            change = true;
        }
        if (!validarJText(textState,5)) {
            if (!change) errores+="Estado";
            else errores+=", Estado";
            lState.setForeground(Color.RED);
            vError[5] = true;
            change = true;
        }
        if (!validarJText(textParty,6)) {
            if (!change) errores+="Partido";
            else errores+=", Partido";
            lParty.setForeground(Color.RED);
            vError[6] = true;
            change = true;
        }
        if (change) {
            setError("Campo "+ errores+" No Valido");
            return false;
        }
        return true;
    }
    private void defColorText(Color aux) {
        textName.setForeground(aux);
        textSurname.setForeground(aux);
        textDni.setForeground(aux);
        textAge.setForeground(aux);
        textCity.setForeground(aux);
        textState.setForeground(aux);
        textParty.setForeground(aux);
    }

    private void cargarFilechoser() {
        chooserload = new JFileChooser();
        choosersave = new JFileChooser();
        UIManager.put("FileChooser.openDialogTitleText", "Cargar");
        UIManager.put("FileChooser.saveDialogTitleText", "Guardar");
        //UIManager.put("FileChooser.saveInLabelText", "Guardar en:");
        UIManager.put("FileChooser.openButtonText", "Cargar");
        UIManager.put("FileChooser.saveButtonText", "Guardar");
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");
        UIManager.put("FileChooser.fileNameLabelText", "Nombre del archivo:");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Tipo de Fichero:");
        UIManager.put("FileChooser.openButtonToolTipText", "Abrir Fichero seleccionado");
        UIManager.put("FileChooser.saveButtonToolTipText", "Guardar Fichero");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Cancelar");
        UIManager.put("FileChooser.fileNameHeaderText", "Nombre");
        UIManager.put("FileChooser.upFolderToolTipText", "Subir un nivel en la jerarquía");
        UIManager.put("FileChooser.homeFolderToolTipText", "Escritorio");
        UIManager.put("FileChooser.newFolderToolTipText", "Crear nueva carpeta");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
        UIManager.put("FileChooser.newFolderButtonText", "CreateNewFolder");
        UIManager.put("FileChooser.renameFileButtonText", "RenameFile");
        UIManager.put("FileChooser.deleteFileButtonText", "DeleteFile");
        UIManager.put("FileChooser.filterLabelText", "TypeFiles");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalles");
        UIManager.put("FileChooser.fileSizeHeaderText", "Tamaño");
        UIManager.put("FileChooser.fileDateHeaderText", "DataModificación");
        UIManager.put("FileChooser.acceptAllFileFilterText", "Todos los tipos");
        UIManager.put("FileChooser.lookInLabelText", "Buscar en:");
        SwingUtilities.updateComponentTreeUI(chooserload);
        UIManager.put("FileChooser.lookInLabelText", "Guardar en:");
        SwingUtilities.updateComponentTreeUI(choosersave);
        chooserload.setAcceptAllFileFilterUsed(true);
        chooserload.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        chooserload.addChoosableFileFilter(new FileNameExtensionFilter(".in", "in"));
        chooserload.addChoosableFileFilter(new FileNameExtensionFilter(".out", "out"));
        choosersave.setAcceptAllFileFilterUsed(true);
        choosersave.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        choosersave.addChoosableFileFilter(new FileNameExtensionFilter(".in", "in"));
        choosersave.addChoosableFileFilter(new FileNameExtensionFilter(".out", "out"));
    }


    class BigData<String> extends AbstractListModel<java.lang.String>
    {
        public int getSize() {
            if (CPC.size() == 0) return 1;
            else return CPC.size();
        }
        public java.lang.String getElementAt(int index) {
            if (CPC.size() == 0) return "No hay Congresistas";
            //else if (indexisOutOfBounds(index)) return ""
            print("index: " + index);
            return CPC.obtCongresista(index);
            //return "Index " + index;
        }
        protected void fireContentsChanged(Object source, int index0, int index1) {
            for (int i = index0; i < index1; ++i) update(i);
        }
        public void update(int index)
        {
            fireContentsChanged(this, index, index);
        }
    }
    private void createjlist() {
        //print("dentro de update");
        bigData = new AbstractListModel<String>() {
            public int getSize() {
                if (CPC.size() == 0) return 1;
                else return CPC.size();
            }
            public String getElementAt(int index) {
                if (CPC.size() == 0) return "No hay Congresistas";
                //else if (indexisOutOfBounds(index)) return ""
                print("index: "+index);
                return CPC.obtCongresista(index);
                //return "Index " + index;
            }
        };

        listCongreso.setPrototypeCellValue("If");
        //bigData = new BigData();
        listCongreso.setModel(bigData);
        //listCongreso.setListData();
        //listCongreso.setModel(bigData);
    }
    private void updatejlist() {
        listCongreso.setModel(bigData);
    }
    private boolean indexisOutOfBounds(int index) {
        return !CPC.indexVisible(index);

        //}
        //if (index >= bqarriba && index < bqarriba+99) return true;
        //print(listCongreso.getLastVisibleIndex()+" last index");
        //if (listCongreso.getAnchorSelectionIndex())

        //return true;
    }
}
