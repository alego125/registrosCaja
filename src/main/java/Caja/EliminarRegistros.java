/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Caja;

import clases.Registros;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC-HOGAR
 */
public class EliminarRegistros extends javax.swing.JFrame {
    
    int id, idU;
    boolean ing;
    String dat, ingreso, egreso, detalle, observacion;
    Registros registro;    
    private ArrayList<Registros> registros = new ArrayList();

    //CReamos el modelo para luego agregar a la lista de elementos
    DefaultListModel listModel = new DefaultListModel();
    String Dato[] = new String[5];
    //Creamos la conexion a la base de datos
    //Declaramos una coneccion en null al igual que un statement tambien en null
    Connection connection = null;
    Statement statement = null;
    //Creamos un objeto tabla, un modelo de tabla para trabjar con el, para colocar las consultas en el datatable
    DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

    private Connection connect() {
        //Inicializamos la conexion
        Connection conn = null;
        try {
            //Pasamos el nombre de la base de datos a la cual nos vamos a conectar
            conn = DriverManager.getConnection("jdbc:sqlite:dbCaja.db");
        } catch (SQLException e) {
            //Mandamos un mensaje de error a la consola en caso de que aparezca uno
            System.out.println(e.getMessage());
        }
        //Por ultimo retornamos la conexion
        return conn;
    }

    public EliminarRegistros() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //Obtenemos la fecha actual
        Date fecha = new Date();
        //Traemos los todos registros de la base de datos
        try {
            //CONECTA A LA BD
            connection = this.connect();
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
            while (rs.next()) {
                //Filtramos los registros por fecha y los guardamos acada campo en una variable
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

                    //Luego creamos un objeto con esos datos y lo asignamos a la lista de elementos
                    registro = new Registros(id, dat, ingreso, egreso, observacion, detalle);
                    this.registros.add(registro);
                }
            }

            //Seteamos el modelo de la lista y los mostramos en pantalla
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItems = new javax.swing.JList<>();
        btnDelete = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ELIMINAR REGISTROS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        listItems.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listItems);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, 110));

        btnDelete.setBackground(new java.awt.Color(204, 0, 0));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ELIMINAR REGISTRO");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 230, -1));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Boton de salir
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    //Boton eliminar
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (listItems.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe elegir al medos un elemento a editar");
        } else {
            //Obtenemos el indice del elemento seleccionado de la lista 
            int elementoSeleccionado = listItems.getSelectedIndex();
            //Obtenemos de la lista de registros con el indice de la seleccion el id de ese elemento que esta en la clase registro
            int idBuscar = registros.get(elementoSeleccionado).getId();
            try {
                connection = this.connect();
                statement = connection.createStatement();
                //Borramos el elemento que coincide con el id que hemos obtenido
                PreparedStatement pt = connection.prepareStatement("DELETE FROM caja WHERE id=" + idBuscar);
                pt.executeUpdate();
                
                this.dispose();
                Caja cj = new Caja();
                cj.setVisible(true);
                
                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");

            } catch (SQLException e) {
                System.err.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Error: \n" + e.getMessage() + "\n VUELVA A INTENTARLO");
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
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(EliminarRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EliminarRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EliminarRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EliminarRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EliminarRegistros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listItems;
    // End of variables declaration//GEN-END:variables
}
