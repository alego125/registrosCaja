/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caja;

import clases.Registros;
import db.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author PC-HOGAR
 */
public class EditarRegistros extends javax.swing.JFrame {

    int id, idU;
    boolean ing;
    String dat, ingreso, egreso, detalle, observacion;
    Registros registro;
    private ArrayList<Registros> registros = new ArrayList();

    //CReamos el modelo para luego agregar a la lista de elementos
    DefaultListModel listModel = new DefaultListModel();
    
    //Creamos un objeto tabla, un modelo de tabla para trabjar con el, para colocar las consultas en el datatable
    DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

    
    //Creamos la conexion a la base de datos
    //Declaramos una coneccion en null al igual que un statement tambien en null
    Connection connection = null;
    Statement statement = null;
    
    //Instanciamos la conexion nueva
    Conexion conec = new Conexion();

    public EditarRegistros() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        buttonGroup1.add(radioEgreso);
        buttonGroup1.add(radioIngreso);
        buttonGroup2.add(radioMp);
        buttonGroup2.add(radioEfectivo);

        Date fecha = new Date();

        try {
            //CONECTA A LA BD
            connection = conec.connect();
            //Iniciamos el statement de la conexion
            statement = connection.createStatement();
            //Le decimos al statement mediante el metodo setQueryTimeout que si se tarda mas de 20 segundo sin usar entonces se cierra la conexion 
            statement.setQueryTimeout(20);
            //QUERY QUE TRAE TODOS LOS DATOS
            //Ejecutamos el query y guardamos todo lo que nos trae dentro de un ResultSet
            //CICLO QUE LLENA TODO EL MODELO
            //Recorremos los datos que trajo la consulta y de lo asignamos a cada uno de las posiciones correspondientes del array de tipo string de 3 posiciones que llamamos Dato[]
            String query = "SELECT * FROM caja";
            ResultSet rs = statement.executeQuery(query);
            BusquedaFecha registros = new BusquedaFecha();
            while (rs.next()) {
                //Soloc guardamos los elementos que coincidan la fecha actual con la fecha de la base de datos o sea solo traemos los elementos de hoy
                if (f.format(Long.parseLong(rs.getString("fecha"))).equals(f.format(fecha))) {
                    //Convertimos la fecha de time stamp a fecha con formato dado arriba, usamos la conversion de string a long por que es un dato demaciado largo par que sea integer
                    id = rs.getInt("id");
                    dat = f.format(Long.parseLong(rs.getString("fecha")));
                    if (rs.getString("ingreso") == null) {
                        ingreso = "0";
                        egreso = rs.getString("egreso");
                    } else if (rs.getString("egreso") == null) {
                        ingreso = rs.getString("ingreso");
                        egreso = "0";
                    }
                    observacion = rs.getString("observacion");
                    detalle = rs.getString("detalle");

                    //Creamos objeto registros con la informacion recuperada de la base de datos
                    registro = new Registros(id, dat, ingreso, egreso, observacion, detalle);
                    //Agregamos este objeto dentro de la lista registros
                    this.registros.add(registro);
                }
            }

            //Recorremos la lista de registros y agregamos a list model cada uno
            for (int i = 0; i < this.registros.size(); i++) {
                //Añadir cada elemento del ArrayList en el modelo de la lista
                listModel.add(i, this.registros.get(i));
            }
            //Asociar el modelo de lista a la lista de elementos
            listItems.setModel(listModel);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error en la buscqueda: \n" + e.getMessage() + "\n VUELVA A INTENTARLO");
        } finally {
            //Al finalizar cerramos la conexion
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed. en el caso de que haya un error en la conexion
                System.err.println(e);
                JOptionPane.showMessageDialog(null, "Error: \n" + e.getMessage() + "\n VUELVA A INTENTARLO");
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItems = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDetalle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        radioIngreso = new javax.swing.JRadioButton();
        radioEgreso = new javax.swing.JRadioButton();
        btnGuardar = new javax.swing.JButton();
        radioMp = new javax.swing.JRadioButton();
        radioEfectivo = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EDITAR REGISTROS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        listItems.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listItems);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, 110));

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("EDITAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 100, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Monto");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 190, 60, -1));

        txtMonto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 200, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Detalle");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 240, 60, -1));

        txtDetalle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 200, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Observacion");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, -1, -1));

        txtObservacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtObservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 200, -1));

        radioIngreso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioIngreso.setText("Ingreso");
        jPanel1.add(radioIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, -1, -1));

        radioEgreso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioEgreso.setText("Egreso");
        jPanel1.add(radioEgreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 80, -1));

        btnGuardar.setBackground(new java.awt.Color(204, 0, 0));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR CAMBIOS");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 200, -1));

        radioMp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioMp.setText("MP");
        jPanel1.add(radioMp, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 80, -1));

        radioEfectivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioEfectivo.setText("Efectivo");
        jPanel1.add(radioEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, -1));

        jMenu1.setText("Archivo");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Agregar Item");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Boton de salir
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    //Boton editar
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //Validamos que se haya seleccionado un elemento dentro de la lista
        if (listItems.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe elegir al medos un elemento a editar");
        } else {
            //Recuperamos el indice seleccionado del elemento de la lista
            int elementoSeleccionado = listItems.getSelectedIndex();
            int idBuscar = registros.get(elementoSeleccionado).getId();
            try {
                //CONECTA A LA BD
                connection = conec.connect();
                //Iniciamos el statement de la conexion
                statement = connection.createStatement();
                //Le decimos al statement mediante el metodo setQueryTimeout que si se tarda mas de 20 segundo sin usar entonces se cierra la conexion 
                //Recorremos los datos que trajo la consulta y de lo asignamos a cada uno de las posiciones correspondientes del array de tipo string de 3 posiciones que llamamos Dato[]
                String query = "SELECT * FROM caja WHERE id=" + idBuscar;
                ResultSet rs = statement.executeQuery(query);
                //Convertimos la fecha de time stamp a fecha con formato dado arriba, usamos la conversion de string a long por que es un dato demaciado largo par que sea integer
                //REcuperamos el id y lo guardamos en una variable, para luego usarlo como referencia para guardar el dato en el registro que coincida con el id
                idU = rs.getInt("id");
                //Formateamos la fecha y la guardamos en una variable
                dat = f.format(Long.parseLong(rs.getString("fecha")));
                //Validamos si es un ingreso o egreso
                if (rs.getString("ingreso") == null) {
                    if (rs.getInt("mp") == 1) {
                        ingreso = "0";
                        egreso = rs.getString("egreso");
                        //Luego de recuperar el egreso lo seteamos en el campo de valor
                        txtMonto.setText(egreso);
                        //Para saber si es ingreso o egreso cuando hagamos update a la base de datos
                        //Hacemos que se seleccione el radio button de egreso
                        radioEgreso.setSelected(true);
                        //Si el campo mp de la base de datos viene con un valor de 1 quiere decir que esta en true por lo que colocamos el radiobutton seleccionado
                        radioMp.setSelected(true);
                    } else {
                        ingreso = "0";
                        egreso = rs.getString("egreso");
                        //Luego de recuperar el egreso lo seteamos en el campo de valor
                        txtMonto.setText(egreso);
                        //Para saber si es ingreso o egreso cuando hagamos update a la base de datos
                        //Hacemos que se seleccione el radio button de egreso
                        radioEgreso.setSelected(true);
                        radioEfectivo.setSelected(true);
                    }
                } else if (rs.getString("egreso") == null) {
                    if (rs.getInt("mp") == 1) {
                        ingreso = rs.getString("ingreso");
                        egreso = "0";
                        //Luego de recuperar el ingreso lo seteo dentro del campo de valor
                        txtMonto.setText(ingreso);
                        //Hacemos que se seleccione el radio button de ingreso
                        radioIngreso.setSelected(true);
                        radioMp.setSelected(true);
                    }else{
                        ingreso = rs.getString("ingreso");
                        egreso = "0";
                        //Luego de recuperar el ingreso lo seteo dentro del campo de valor
                        txtMonto.setText(ingreso);
                        //Hacemos que se seleccione el radio button de ingreso
                        radioIngreso.setSelected(true);
                        radioEfectivo.setSelected(true);
                    }
                }
                //Obtenemos de la base de datos las observaciones y el detalle y seteamos sus valores dentro de los campos de la parte visual
                observacion = rs.getString("observacion");
                txtObservacion.setText(observacion);
                detalle = rs.getString("detalle");
                txtDetalle.setText(detalle);

            } catch (SQLException e) {
                System.err.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Error en la buscqueda: \n" + e.getMessage() + "\n VUELVA A INTENTARLO");
            } finally {
                //Al finalizar cerramos la conexion
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // connection close failed. en el caso de que haya un error en la conexion
                    System.err.println(e);
                }

            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    //Boton de guardar Cambios
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            connection = conec.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            PreparedStatement pt = null;
            if (radioIngreso.isSelected()) {
                if(radioMp.isSelected()){
                    pt = connection.prepareStatement("UPDATE caja SET ingreso = ?, egreso=null, detalle = ?, "
                        + "observacion = ?, mp = 1 WHERE id = " + this.idU);
                }else if(radioEfectivo.isSelected()){
                    pt = connection.prepareStatement("UPDATE caja SET ingreso = ?, egreso=null, detalle = ?, "
                        + "observacion = ?, mp = 0 WHERE id = " + this.idU);
                }
            } else if (radioEgreso.isSelected()) {
                if(radioMp.isSelected()){
                    pt = connection.prepareStatement("UPDATE caja SET egreso = ?, ingreso=null, detalle = ?, "
                        + "observacion = ?, mp = 1 WHERE id = " + this.idU);
                }else if(radioEfectivo.isSelected()){
                    pt = connection.prepareStatement("UPDATE caja SET egreso = ?, ingreso=null, detalle = ?, "
                        + "observacion = ?, mp = 0 WHERE id = " + this.idU);
                }
            }

            //Seteamos el monto detalle y observacion que son los valores que se pueden modificar
            pt.setString(1, txtMonto.getText());
            pt.setString(2, txtDetalle.getText());
            pt.setString(3, txtObservacion.getText());

            pt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro Actualizado Correctamente");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error en la buscqueda: \n" + e.getMessage() + "\n VUELVA A INTENTARLO");
        } finally {
            //Al finalizar cerramos la conexion
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed. en el caso de que haya un error en la conexion
                System.err.println(e);
                JOptionPane.showMessageDialog(null, "Error: \n" + e.getMessage() + "\n VUELVA A INTENTARLO");
            }

        }
        Caja cj = new Caja();
        cj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    //Boton pantalla agregar item
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Caja cj = new Caja();
        cj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditarRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarRegistros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listItems;
    private javax.swing.JRadioButton radioEfectivo;
    private javax.swing.JRadioButton radioEgreso;
    private javax.swing.JRadioButton radioIngreso;
    private javax.swing.JRadioButton radioMp;
    private javax.swing.JTextField txtDetalle;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtObservacion;
    // End of variables declaration//GEN-END:variables
}
