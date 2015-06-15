package Presentacio;

import Domini.Congresista;
import Domini.Dni;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;


public class PanelCongreso extends PanelLista {
    final String[] def={"Introduzca el Nombre", "Introduzca el Apellido", "Introduzca el Dni", "Introduzca la Edad", "Introduzca la Ciudad", "Introduzca el Estado", "Introduzca el Partido"};

    //Referència al controlador de presentació que crea la vista
    CPCongreso CPC;
    boolean t;
    private Boolean[] vError;
    int tamSearch;
    private String textBusq;
    private String actualSearch;
    private int selectedAuto;

    JPopupMenu searchPopMenu;
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
        iniComp();
        updateJList(0);
        iniFont();
        initGUI();
        tamSearch = 0;
        selectedAuto = 0;

    }
    private void iniFont() {
        textBusq = "";
        actualSearch = "";
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
        searchPopMenu = new JPopupMenu();
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
    }
    private void initGUI() {
        cargarFilechoser();

        t = true;
        //Acción realizada al seleccionar un elemento
        listCongreso.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    congresistaSeleccionado();
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
        textName.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                dynamicSearch(1, textName, evt);
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
        textSurname.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                dynamicSearch(2, textSurname, evt);
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
        textDni.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                dynamicSearch(0, textDni, evt);
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
        textAge.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                dynamicSearch(3, textAge, evt);
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
        textCity.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                dynamicSearch(4, textCity, evt);
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
        textState.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                dynamicSearch(5, textState, evt);
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
        textParty.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                dynamicSearch(6, textParty, evt);
            }
        });

        bClear.setText("Limpiar");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        lError.setText(" ");

        bAgregarCongresista.setText("Agregar Congresista");
        bAgregarCongresista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarCongresistaActionPerformed(evt);
            }
        });

        bAgregarRandom.setText("Agregar Random");
        bAgregarRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarRandomActionPerformed(evt);
            }
        });

        bEliminarCongresista.setText("Eliminar Congresista");
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
        bEliminarCongreso.setText("Eliminar Congreso");
        bEliminarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarCongresoActionPerformed(evt);
            }
        });

        bCargarCongreso.setText("Cargar Congreso");
        bCargarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCargarCongresoActionPerformed(evt);
            }
        });

        bGuardarCongreso.setText("Guardar Congreso");
        bGuardarCongreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarCongresoActionPerformed(evt);
            }
        });


        SpinnerNum.setModel(new SpinnerNumberModel(1, 1, 50000000, 1));
        ((JSpinner.DefaultEditor)SpinnerNum.getEditor()).getTextField().addKeyListener((new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    agregarRandom();
                }
            }
        }));


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
                                .addGap(29, 29, 29)));
    }

    private void congresistaSeleccionado() {
        if (!(listCongreso.getSelectedIndex() == -1)) {
            emptyLError();
            String dato = (String) listCongreso.getSelectedValue();
            String[] campos = dato.split(" ");
            if (campos.length == 7) {
                int order = boxSort.getSelectedIndex();
                defColorText(Color.BLACK);
                switch (order) {
                    case 0:
                        setTexts(campos[0],campos[1],campos[2],campos[3],campos[4],campos[5],campos[6]);
                        break;
                    case 1:
                        setTexts(campos[2],campos[0],campos[1],campos[3],campos[4],campos[5],campos[6]);
                        break;
                    case 2:
                        setTexts(campos[2],campos[1],campos[0],campos[3],campos[4],campos[5],campos[6]);
                        break;
                    case 3:
                        setTexts(campos[1],campos[2],campos[3],campos[0],campos[4],campos[5],campos[6]);
                        break;
                    case 4:
                        setTexts(campos[1],campos[2],campos[3],campos[4],campos[0],campos[5],campos[6]);
                        break;
                    case 5:
                        setTexts(campos[1],campos[2],campos[3],campos[4],campos[5],campos[0],campos[6]);
                        break;
                    case 6:
                        setTexts(campos[1],campos[2],campos[3],campos[4],campos[5],campos[6],campos[0]);
                        break;
                }
            }
        }
    }
    private void setTexts(String dni, String name, String surname, String age, String city, String state, String party) {
        textDni.setText(dni);
        textName.setText(name);
        textSurname.setText(surname);
        textAge.setText(age);
        textCity.setText(city);
        textState.setText(state);
        textParty.setText(party);
    }
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
        emptyLError();
        if (validarTodo()) {
            try {
                CPC.agregarCongresista(textDni.getText(), getTextString(textName), getTextString(textSurname), Integer.parseInt(textAge.getText()), getTextString(textCity), getTextString(textState), getTextString(textParty));
                setDefaultText();
                setMsg("Congresista agregado satisfactoriamente");
                ListUpdate();
            }
            catch (NumberFormatException a) {
                setError("La edad tiene que ser un número > 0");
                lAge.setForeground(Color.RED);
            }
            catch (Congresista.EdadNoValida a) {
                setError("La edad tiene que ser un número > 0");
                lAge.setForeground(Color.RED);
            }
            catch (Dni.DNINOVALIDO a) {
                setError(a.getMessage());
                lDni.setForeground(Color.RED);
            }
            catch (Exception a) {
                setError(a.getMessage());
            }
        }
    }
    private String getTextString(JTextField aux) {
        String aux2 = "";
        String[] campos = aux.getText().split(" ");
        if (campos.length != 1) {
            for (int i = 0; i < campos.length; ++i) {
                if (i != 0) aux2 += "";
                aux2 += (campos[i]);
            }
        }
        else aux2 = aux.getText();
        return aux2;
    }
    private void bEliminarCongresistaActionPerformed(java.awt.event.ActionEvent evt) {
        emptyLError();
        if (jlist.isSelectionEmpty()) {
            setError("Selecciona un Congresista");
        }
        else {
            try {
                List<String> campos = jlist.getSelectedValuesList();
                if (campos.size() == CPC.size()) {
                    CPC.eliminarCongreso();
                }
                else {
                    for (int i = 0; i < campos.size(); ++i) {
                        String[] con = campos.get(i).split(" ");
                        CPC.eliminarCongresista(con[0],con[1],con[2],Integer.valueOf(con[3]),con[4],con[5],con[6]);
                    }
                }
                setDefaultText();
                setMsg("Congresista/s eliminados satisfactoriamente");
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
        emptyLError();
        String dato = (String) listCongreso.getSelectedValue();
        if (listCongreso.getSelectedIndices().length > 1) setError("Selecciona solo uno");
        else if (jlist.isSelectionEmpty() || dato.equals("No hay Congresistas")) {
            setError("Selecciona un Congresista");
        }
        else {
            if (validarTodo()) {
                String[] campos = dato.split(" ");
                try {
                    CPC.modCongresista(campos[0], textDni.getText(), campos[1], getTextString(textName), campos[2], getTextString(textSurname), Integer.valueOf(campos[3]), Integer.parseInt(textAge.getText()), campos[4], getTextString(textCity), campos[5], getTextString(textState), campos[6], getTextString(textParty));
                    setMsg("Congresista modificado satisfactoriamente");
                    ListUpdate();
                }
                catch (NumberFormatException a) {
                    setError("La edad tiene que ser un número > 0");
                    lAge.setForeground(Color.RED);
                }
                catch (Congresista.EdadNoValida a) {
                    setError("La edad tiene que ser un número > 0");
                    lAge.setForeground(Color.RED);
                }
                catch (Dni.DNINOVALIDO a) {
                    setError(a.getMessage());
                    lDni.setForeground(Color.RED);
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
        agregarRandom();
    }
    private void agregarRandom() {
        emptyLError();
        try {
            Integer n = (Integer)SpinnerNum.getValue();
            CPC.agregarCongresistaRandom(n);
            ListUpdate();
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
            ListUpdate();
        }
    }
    private void bGuardarCongresoActionPerformed(java.awt.event.ActionEvent evt) {
        emptyLError();
        if (CPC.size() == 0) setError("No hay Congresistas");
        else {
            int returnVal = choosersave.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    CPC.guardar(choosersave.getSelectedFile().getAbsolutePath()+choosersave.getFileFilter().getDescription());
                } catch (Exception a) {
                    setError(a.getMessage());
                }
            }
        }
        ListUpdate();
    }
    public void ListUpdate() {
        updateJList(boxSort.getSelectedIndex());
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
        setDefaultText();
        emptyLError();
        ListUpdate();
    }
    private void dynamicSearch(int which,JTextField aux,KeyEvent evt) {
        if (listCongreso.isSelectionEmpty() && CPC.size()!= 0) {
            String buscar = aux.getText();
            if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (buscar.equals("")) {
                    labelStatus.setVisible(false);
                    labelStatus.setText("");
                    ListUpdate();
                }
                else {
                    Search(buscar, which);
                    labelStatus.setVisible(true);
                    labelStatus.setText("Congresistas encontrados: "+CPC.sizeBusqueda());
                }
            }
            else {
                Search(buscar, which);
                labelStatus.setVisible(true);
                labelStatus.setText("Congresistas encontrados: " + CPC.sizeBusqueda());
            }
        }
    }
    //Modificar para buscar
    protected  void buttonSearchActionPerformed(ActionEvent evt) {
        //do nothing
    }
    private void ListUpdateBusqueda2() {
        /*
       String a[] = {"Búsqueda sin resultados"};
        if (CPC.resultados()) listCongreso.setListData(a);
        else {
            ArrayList<String> aux = CPC.obtCC().obtenerBusqueda();
            listCongreso.setListData(aux.toArray());
        }*/
    }
    private void ListUpdateBusqueda() {
        ListModel bigData2 = new AbstractListModel<String>() {
            public int getSize() {
                if (CPC.sizeBusqueda() == 0) return 1;
                else return CPC.sizeBusqueda();
            }
            public String getElementAt(int index) {
                if (CPC.sizeBusqueda() == 0) return "Búsqueda sin resultados";
                return CPC.obtCongresistaBusqueda(index);
            }
        };
        listCongreso.setPrototypeCellValue("If ----------- ");
        listCongreso.setModel(bigData2);
    }
    protected void setBoxSort() {
        boxSort.setModel(new DefaultComboBoxModel(new String[]{"Orden Dni", "por Nombre", "por Apellido", "por Edad", "por Ciudad", "por Estado", "por Partido"}));
        boxSort.setToolTipText(" Ordenar ");
    }
    protected void setBoxSearch() {
        boxSearch.setModel(new DefaultComboBoxModel(new String[]{"Busca Dni", "por Nombre", "por Apellido", "por Edad", "por Ciudad", "por Estado", "por Partido"}));
        boxSearch.setToolTipText(" Buscar ");
    }
    ////////////////////////HACER///////////////////////////////
    protected void boxSearchActionPerformed(ActionEvent e) {
        String buscar = textSearch.getText();
        if (!buscar.equals(defaultTextLabelSearch)) {
            textSearch.setText(actualSearch);
            int which = boxSearch.getSelectedIndex();
            Search(actualSearch, which);
            labelStatus.setVisible(true);
            labelStatus.setText("Congresistas encontrados: " + CPC.sizeBusqueda());
            setAutocompletar(actualSearch, which);
        }
    }
    ///////////////////////HACER//////////////////////////////////
    protected void textSearchTyped(KeyEvent evt) {
        searchPopMenu.setFocusable(false);
        String buscar = textSearch.getText();
        int which = boxSearch.getSelectedIndex();
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (buscar.equals("")) {
                labelStatus.setVisible(false);
                labelStatus.setText("");
                ListUpdate();
                searchPopMenu.setVisible(false);
                actualSearch = "";
            }
            else {
                actualSearch = actualSearch.substring(0,actualSearch.length()-1);
                Search(actualSearch, which);
                labelStatus.setVisible(true);
                labelStatus.setText("Congresistas encontrados: " + CPC.sizeBusqueda());
                if (actualSearch.equals("")) {
                    labelStatus.setVisible(false);
                    labelStatus.setText("");
                    ListUpdate();
                    searchPopMenu.setVisible(false);
                    textSearch.setText(actualSearch);
                }
                else {
                    textSearch.setText(actualSearch);
                    setAutocompletar(actualSearch, which);
                }
            }
        }
        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            up();
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            down();
        }
        else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            searchPopMenu.setVisible(false);
            actualSearch = textSearch.getText();
            Search(actualSearch, which);
            labelStatus.setVisible(true);
            labelStatus.setText("Congresistas encontrados: " + CPC.sizeBusqueda());
        }
        else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            searchPopMenu.setVisible(false);
            actualSearch = textSearch.getText();
            Search(actualSearch, which);
            labelStatus.setVisible(true);
            labelStatus.setText("Congresistas encontrados: " + CPC.sizeBusqueda());
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
           // print("nuv");
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            actualSearch = textSearch.getText();
            textSearch.setSelectionStart(actualSearch.length());
            searchPopMenu.setVisible(false);
            Search(actualSearch, which);
            labelStatus.setVisible(true);
            labelStatus.setText("Congresistas encontrados: " + CPC.sizeBusqueda());

            //textSearch.setCaretPosition(actualSearch.length());
        }
        else {
            actualSearch+=evt.getKeyChar();
            Search(actualSearch, which);
            labelStatus.setVisible(true);
            labelStatus.setText("Congresistas encontrados: " + CPC.sizeBusqueda());
            setAutocompletar(buscar,which);
        }
    }
    private void up() {
        if (searchPopMenu.isVisible()) {
            int n = searchPopMenu.getSubElements().length;
            if (n != 0 && selectedAuto != 0) {
                ((JMenuItem) searchPopMenu.getComponent(selectedAuto)).setArmed(false);
                selectedAuto--;
                ((JMenuItem) searchPopMenu.getComponent(selectedAuto)).setArmed(true);
                String sel = ((JMenuItem) searchPopMenu.getComponent(selectedAuto)).getText();
                textSearch.setText(sel);
                textSearch.setSelectionStart(actualSearch.length());
            }
        }
    }
    private void down() {
        if (searchPopMenu.isVisible()) {
            int n = searchPopMenu.getSubElements().length;
            if (n != 0 && selectedAuto != n-1) {
                ((JMenuItem) searchPopMenu.getComponent(selectedAuto)).setArmed(false);
                selectedAuto++;
                ((JMenuItem) searchPopMenu.getComponent(selectedAuto)).setArmed(true);
                String sel = ((JMenuItem) searchPopMenu.getComponent(selectedAuto)).getText();
                textSearch.setText(sel);
                textSearch.setSelectionStart(actualSearch.length());
            }
        }

    }
    private void setAutocompletar(String buscar, int which) {
        String[] aux = CPC.obtAutocompletar(buscar.toUpperCase(),which);
        if (aux != null && aux.length >= 1) {
            searchPopMenu = new JPopupMenu();
            searchPopMenu.setFocusable(false);
            searchPopMenu.setFont(new Font("Ubuntu", 0, 20));
            int i = 0;
            for (; i < aux.length; ++i) {
                searchPopMenu.add(aux[i]);
            }
            searchPopMenu.setPopupSize(textSearch.getWidth(), 25 * aux.length);
            searchPopMenu.show(textSearch, 0, textSearch.getHeight());
            searchPopMenu.setVisible(true);
            textSearch.setText(aux[0]);
            textSearch.setSelectionStart(buscar.length());
            selectedAuto = 0;
            ((JMenuItem) searchPopMenu.getComponent(0)).setArmed(true);

        }
        else {
            searchPopMenu.setVisible(false);
        }
    }
    private void Search(String buscar,int w) {
        if (!buscar.isEmpty()) {
            CPC.reiniciarBusqueda();
            switch (w) {
                case 0:
                    CPC.searchByDni(buscar);
                    ListUpdateBusqueda();
                    break;
                case 1:
                    CPC.searchByName(buscar);
                    ListUpdateBusqueda();
                    break;
                case 2:
                    CPC.searchBySurName(buscar);
                    ListUpdateBusqueda();
                    break;
                case 3:
                    CPC.searchByAge(buscar);
                    ListUpdateBusqueda();
                    break;
                case 4:
                    CPC.searchByCity(buscar);
                    ListUpdateBusqueda();
                    break;
                case 5:
                    CPC.searchByState(buscar);
                    ListUpdateBusqueda();
                    break;
                case 6:
                    CPC.searchByParty(buscar);
                    ListUpdateBusqueda();
                    break;
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
    private void updateJList(int orden) {
        CPC.modOrder(orden);
       // print(CPC.obtCongresista(0));
        bigData = new AbstractListModel<String>() {
            public int getSize() {
                if (CPC.size() == 0) return 1;
                else return CPC.size();
            }
            public String getElementAt(int index) {
                if (CPC.size() == 0) return "No hay Congresistas";
                return CPC.obtCongresistaCache(index);
            }
        };
        listCongreso.setPrototypeCellValue("If ----------- ");
        listCongreso.setModel(bigData);
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
        chooserload.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        chooserload.addChoosableFileFilter(new FileNameExtensionFilter(".in", "in"));
        chooserload.addChoosableFileFilter(new FileNameExtensionFilter(".out", "out"));
        choosersave.setAcceptAllFileFilterUsed(true);
        choosersave.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        choosersave.addChoosableFileFilter(new FileNameExtensionFilter(".in", "in"));
        choosersave.addChoosableFileFilter(new FileNameExtensionFilter(".out", "out"));
    }

    protected void textSearchFocusLost() {
        print("entro el focus lost");
        searchPopMenu.setVisible(false);
        print("print actual search"+actualSearch);
        if (!textSearch.getText().equals(defaultTextLabelSearch)) {
            textSearch.setText(actualSearch);
        }
    }
}
