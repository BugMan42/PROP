package Presentacio;

import Domini.Congresista;
import Domini.ControladorCjtEvento;
import Domini.ControladorRelaciones;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;


public class PanelCongreso extends PanelLista {

    //Referència al controlador de presentació que crea la vista
    private Boolean[] vError;
    CPCongreso cvc;
    CPRelaciones cpr;

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


    public PanelCongreso(CPCongreso c)
    {
        super();
        vError = new Boolean[7];
        for (int i = 0; i < 7; ++i) {
            vError[i] = false;
        }
        cvc = c;
        // Inicializa los componentes de la ventana
        initGUI();
    }

    public PanelCongreso() {

        super();
        // Inicializa los componentes de la ventana
        //initUI();

    }
    private void initGUI() {
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
        listCongreso = obtJlist();

        // setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Referencias a los objetos superiores
        //JList listCongreso = obtJlist();
//        final DefaultListModel def = (DefaultListModel) name_list.getModel();
        ListUpdate();
        //listCongreso.setListData();

        //Acción realizada al seleccionar un elemento
        listCongreso.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    if (listCongreso.getSelectedIndex() == -1) {
                        //No selection, disable fire button.
                        //acceptButton.setEnabled(false);

                    } else {
                        String dato = (String) listCongreso.getSelectedValue();
                        //errorField.setText("#" + name_list.getSelectedIndex());
                        // Del congresista a los campos
                        String[] campos = dato.split(" ");

                        if (campos.length == 7) {
                            textDni.setText(campos[0]);
                            textName.setText(campos[1]);
                            textSurname.setText(campos[2]);
                            textAge.setText(campos[3]);
                            textCity.setText(campos[4]);
                            textState.setText(campos[5]);
                            textParty.setText(campos[6]);
                            /*errorField.append("\n");
                            errorField.append(dato);
                            errorField.append("\n");
                            errorField.append(iden);*/
                        } else {
                            //nameField.setText(campos[0]);
                        }


                    }
                }
            }
        });

        setDefaultText();


        lName.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lName.setText("Nombre");

        textName.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearText(textName);
            }
        });

        textSurname.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textSurname.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearText(textSurname);
            }
        });

        lSurname.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lSurname.setText("Apellido");

        lDni.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lDni.setText("Dni");

        textDni.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textDni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearText(textDni);
            }
        });

        lAge.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lAge.setText("Edad");

        textAge.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textAge.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearText(textAge);
            }
        });

        lCity.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lCity.setText("Ciudad");

        textCity.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textCity.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearText(textCity);
            }
        });

        lState.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lState.setText("Estado");

        textState.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textState.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearText(textState);
            }
        });

        lParty.setFont(new java.awt.Font("Ubuntu", 0, 18));
        lParty.setText("Partido");

        textParty.setFont(new java.awt.Font("Ubuntu", 0, 18));
        textParty.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearText(textParty);
            }
        });

        bClear.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bClear.setText("Clear");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        lError.setText(" ");
        lError.setFont(new java.awt.Font("Ubuntu", 0, 18));

        bAgregarCongresista.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bAgregarCongresista.setText("AgregarCongresista");
        bAgregarCongresista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarCongresistaActionPerformed(evt);
            }
        });

        bAgregarRandom.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bAgregarRandom.setText("AgregarRandom");
        bAgregarRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarRandomActionPerformed(evt);
            }
        });

        bEliminarCongresista.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bEliminarCongresista.setText("EliminarCongresista");
        bEliminarCongresista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarCongresistaActionPerformed(evt);
            }
        });

        bModificarCongresista.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bModificarCongresista.setText("Modificar");
        bModificarCongresista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarCongresistaActionPerformed(evt);
            }
        });

        bEliminarCongreso.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bEliminarCongreso.setText("EliminarCongreso");
        bEliminarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarCongresoActionPerformed(evt);
            }
        });

        bCargarCongreso.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bCargarCongreso.setText("CargarCongreso");
        bCargarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCargarCongresoActionPerformed(evt);
            }
        });

        bGuardarCongreso.setFont(new java.awt.Font("Ubuntu", 0, 18));
        bGuardarCongreso.setText("GuardarCongreso");
        bGuardarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarCongresoActionPerformed(evt);
            }
        });

        SpinnerNum.setFont(new java.awt.Font("Ubuntu", 0, 18));
        SpinnerNum.setValue(1);


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
        );

    }// </editor-fold>

    private void clearText(JTextField aux) {
        if (!aux.getText().equals("") && aux.getText().charAt(0) == 'I') aux.setText("");
    }


    private void print(String str) {
        System.out.println(str);
    }

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {
        setDefaultText();
    }

    private void bAgregarCongresistaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        emptyLError();
        if (validarTodo()) {
            try {
                cvc.CC.agregarCongresista(textDni.getText(),textName.getText(),textSurname.getText(),Integer.parseInt(textAge.getText()),textCity.getText(),textState.getText(),textParty.getText());
                setDefaultText();
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
    private boolean validarTodo() {
        boolean change = false;
        String errores = "";
        if (!validarJText(textName)) {
            errores+="Nombre";
            lName.setForeground(Color.RED);
            vError[0] = true;
            change = true;
        }
        if (!validarJText(textSurname)) {
            if (!change) errores+="Apellido";
            else errores+=", Apellido";
            lSurname.setForeground(Color.RED);
            vError[1] = true;
            change = true;
        }
        if (!validarJText(textDni)) {
            if (!change) errores+="Dni";
            else errores+=", Dni";
            lDni.setForeground(Color.RED);
            vError[2] = true;
            change = true;
        }
        if (!validarJText(textAge)) {
            if (!change) errores+="Edad";
            else errores+=", Edad";
            lAge.setForeground(Color.RED);
            vError[3] = true;
        }
        if (!validarJText(textCity)) {
            if (!change) errores+="Ciudad";
            else errores+=", Ciudad";
            lCity.setForeground(Color.RED);
            vError[4] = true;
            change = true;
        }
        if (!validarJText(textState)) {
            if (!change) errores+="Estado";
            else errores+=", Estado";
            lState.setForeground(Color.RED);
            vError[5] = true;
            change = true;
        }
        if (!validarJText(textParty)) {
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

    private void bEliminarCongresistaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO ARREGLAR MERDA CONTROLADORS
        emptyLError();
        ControladorCjtEvento aux = new ControladorCjtEvento();
        ControladorRelaciones aux2 = new ControladorRelaciones(cvc.CC, aux);
        if (validarJText(textDni)) {
            try {
                cvc.CC.eliminarCongresista(textDni.getText(), aux2);
                setDefaultText();
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
    private boolean validarJText(JTextField aux) {
        String[] a = aux.getText().split(" ");
        return a.length == 1 && !aux.getText().equals(" ") && !aux.getText().equals("");
    }

    private void bModificarCongresistaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        emptyLError();
        if (validarTodo()) {
            //cvc.CC.mod
        }

    }

    private void bEliminarCongresoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here
        emptyLError();
        ControladorCjtEvento aux = new ControladorCjtEvento();
        ControladorRelaciones aux2 = new ControladorRelaciones(cvc.CC,aux);
        cvc.CC.eliminarCongreso(aux2);
        ListUpdate();
    }

    private void bAgregarRandomActionPerformed(java.awt.event.ActionEvent evt) {
        emptyLError();
        Integer n = (Integer)SpinnerNum.getValue();
        if (n > 0 && n < 1000001) {
            cvc.CC.agregarCongresistaRandom(n);
            ListUpdate();
        }
        else {
            setError("El numero de congresistas random tienen que ser > 0 y menor que 1000 001");
        }
    }
        // Carregant ......

    private void bCargarCongresoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        emptyLError();
        JFileChooser chooser = new JFileChooser();
        //FileNameExtensionFilter filter = new FileNameExtensionFilter(
        //        "JPG & GIF Images", "jpg", "gif");
        //chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
    }

    private void bGuardarCongresoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        emptyLError();
        JFileChooser chooser = new JFileChooser();
        //FileNameExtensionFilter filter = new FileNameExtensionFilter(
        //        "JPG & GIF Images", "jpg", "gif");
        //chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
    }

    private void ListUpdate() {
        String a[] = {"No hay Congresistas"};
        if (cvc.CC.esVacio()) listCongreso.setListData(a);
        else {
            ArrayList<String> aux = cvc.CC.obtenerCongresoTotal();
            listCongreso.setListData(aux.toArray());
        }
    }
    private void setDefaultText() {
        textName.setText("Introduzca el Nombre");
        textSurname.setText("Introduzca el Apellido");
        textDni.setText("Introduzca el Dni");
        textAge.setText("Introduzca la Edad");
        textCity.setText("Introduzca la Ciudad");
        textState.setText("Introduzca el Estado");
        textParty.setText("Introduzca el Partido");
        emptyLError();
    }

    private void emptyLError() {
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
    
    
    
    
    
    

    private void initUI()
    {

        JLabel name = new JLabel("Nombre:");
        JLabel surname = new JLabel("Apellido:");
        JLabel dni = new JLabel("DNI:");
        JLabel age = new JLabel("Edad:");
        JLabel city = new JLabel("Ciudad:");
        JLabel state = new JLabel("Estado:");
        JLabel partido = new JLabel("Partido:");
        final JTextField nameField = new JTextField("Introduzca nombre");
        //nameField.setMaximumSize(new Dimension(200, 25));
        final JTextField dniField = new JTextField("8 numbers + 1 letter");
        //dniField.setMaximumSize(new Dimension(200, 25));
        final JTextField surnameField = new JTextField("Introduzca apellido");
        //surnameField.setMaximumSize(new Dimension(200, 25));
        final JTextField partidoField = new JTextField("Introduzca partido");
        //partidoField.setMaximumSize(new Dimension(200, 25));
        final JTextField ageField = new JTextField("Introduzca edad");
        //ageField.setMaximumSize(new Dimension(200, 25));
        final JTextField cityField = new JTextField("Introduzca ciudad");
        //cityField.setMaximumSize(new Dimension(200, 25));
        final JTextField stateField = new JTextField("Introduzca estado");
        //stateField.setMaximumSize(new Dimension(200, 25));
        nameField.putClientProperty("JComponent.sizeVariant", "large");
        dniField.putClientProperty("JComponent.sizeVariant", "regular");
        surnameField.putClientProperty("JComponent.sizeVariant", "regular");
        partidoField.putClientProperty("JComponent.sizeVariant", "regular");
        ageField.putClientProperty("JComponent.sizeVariant", "regular");
        cityField.putClientProperty("JComponent.sizeVariant", "regular");
        stateField.putClientProperty("JComponent.sizeVariant", "regular");

        final JTextArea errorField = new JTextArea("No errors found");
        errorField.setMaximumSize(new Dimension(200, 50));
        errorField.setEditable(false);


        final JButton acceptButton;
        acceptButton = new JButton("Aceptar");
        acceptButton.setEnabled(false);

        // Referencias a los objetos superiores
        final JList name_list = obtJlist();
//        final DefaultListModel def = (DefaultListModel) name_list.getModel();

        //Acción realizada al seleccionar un elemento
        name_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    if (name_list.getSelectedIndex() == -1) {
                        //No selection, disable fire button.
                        acceptButton.setEnabled(false);

                    } else {
                        //Selection, enable the fire button.
                        acceptButton.setEnabled(true);
                        String dato = (String) name_list.getSelectedValue();
                        errorField.setText("#" + name_list.getSelectedIndex());

                        // Del congresista a los campos
                        String[] campos = dato.split(" ");

                        if (campos.length >= 2 && campos[1].charAt(0) == '[') {
                            String iden = campos[1].substring(1, campos[1].length() - 1);
                            nameField.setText(campos[0]);
                            dniField.setText(iden);

                            errorField.append("\n");
                            errorField.append(dato);
                            errorField.append("\n");
                            errorField.append(iden);
                        } else {
                            nameField.setText(campos[0]);
                        }


                    }
                }
            }
        });

        //Acción del botón Aceptar
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int in = name_list.getSelectedIndex();
                String nom = nameField.getText();
                String iden = dniField.getText();

                Congresista cong = null;

                try {
                    //cvc.agregarCongresista(dniField.getText(), nameField.getText(), surnameField.getText(), Integer.parseInt(ageField.getText()), cityField.getText(), partidoField.getText(), stateField.getText());
                    //cong = cvc.consultarCongresista(iden);
                    errorField.setText("");
                    //def.setElementAt(nom + " [" + iden + "]", in);
                } catch (Exception e1) {
                    //e1.printStackTrace();
                    errorField.setText(e1.getMessage());
                }


            }
        });

       /* //Botón eliminar
        final JButton delb = obtElimBoton();
        delb.setEnabled(false);
        delb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String iden = dniField.getText();
                def.remove(name_list.getSelectedIndex());


                try {
                    //cvc.eliminarCongresista(iden, cp.getControlRelaciones());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

                if (def.getSize() - 1 < 0) delb.setEnabled(false);
                else name_list.setSelectedIndex(def.getSize() - 1);

            }
        });

        //Botón añadir
        final JButton addb = obtAgrBoton();
        addb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.addElement("Nuevo");
                //nameField.setText("Insert troll here");
                nameField.requestFocusInWindow();
                //surnameField.setText("Insert meme here");
                //dniField.setText("66666666X");
                //partidoField.setText("Insert RageQuit here");
                //ageField.setText("21");
                //cityField.setText("Insert Mordor here");
                name_list.setSelectedIndex(def.getSize() - 1);


                delb.setEnabled(true);
            }
        });*/

        JPanel der = new JPanel();
        der.add(name);
        der.add(nameField);
        der.add(surname);
        der.add(surnameField);
        der.add(dni);
        der.add(dniField);
        der.add(age);
        der.add(ageField);
        der.add(partido);
        der.add(partidoField);

        //Obtenemos el SplitPanel de la clase padre y le asignamos el panel a la parte derecha
        obtSp().setRightComponent(der);


        GroupLayout gr = new GroupLayout(der);
        der.setLayout(gr);
        gr.setAutoCreateGaps(true);
        gr.setAutoCreateContainerGaps(true);



        // Layout

        gr.setHorizontalGroup(
                gr.createSequentialGroup()
                        .addGroup(gr.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gr.createSequentialGroup()
                                                        .addGroup(gr.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(name)
                                                                        .addComponent(nameField)
                                                                        .addComponent(dni)
                                                                        .addComponent(dniField)
                                                                        .addComponent(age)
                                                                        .addComponent(ageField)
                                                                        .addComponent(state)
                                                                        .addComponent(stateField)
                                                        )
                                                        .addGroup(gr.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(surname)
                                                                        .addComponent(surnameField)
                                                                        .addComponent(partido)
                                                                        .addComponent(partidoField)
                                                                        .addComponent(city)
                                                                        .addComponent(cityField)
                                                        )
                                        )
                                        .addGroup(gr.createSequentialGroup()
                                                        .addComponent(errorField)
                                                                //.addComponent(delete)
                                                        .addComponent(acceptButton)
                                        )
                        )
        );




        gr.setVerticalGroup(
                gr.createParallelGroup()
                        .addGroup(gr.createSequentialGroup()
                                        .addGroup(gr.createParallelGroup()
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(name)
                                                                        .addComponent(nameField)
                                                        )
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(surname)
                                                                        .addComponent(surnameField)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(dni)
                                                                        .addComponent(dniField)
                                                        )
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(partido)
                                                                        .addComponent(partidoField)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(age)
                                                                        .addComponent(ageField)
                                                        )
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(city)
                                                                        .addComponent(cityField)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup()
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(state)
                                                                        .addComponent(stateField)
                                                        )
                                                        .addGroup(gr.createSequentialGroup()
                                                                        .addComponent(city)
                                                                        .addComponent(cityField)
                                                        )
                                        )
                                        .addGroup(gr.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(errorField)
                                                                //.addComponent(delete)
                                                        .addComponent(acceptButton)
                                        )
                        )


        );


    }


}
