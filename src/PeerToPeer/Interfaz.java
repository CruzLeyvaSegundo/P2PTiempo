/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PeerToPeer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author SEGUNDO
 */
public class Interfaz extends javax.swing.JFrame {

    //Cliente
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket cliente;   
    private String IP;
    private String contraseña="";
    
    //Servidor
    private String apodoNodo;
    private boolean estadoServer = false;
    private Vector<nodoNombre> clientes=new Vector();
    private String documentoEvento ="";
    private String documentoReporte = "";
    public Interfaz(Vector<nodoNombre> clientes) {
        initComponents();
        goPanel(false);
        this.clientes=clientes;
    }
    public void setEstadoServer(boolean estadoServer) {
        this.estadoServer = estadoServer;
    }
    
    public void setDocumentoEvento(String documentoEvento) {
        this.documentoEvento = documentoEvento;
    }
    public String getApodoNodo() {
        return apodoNodo;
    }   
    private void goPanel(boolean bandera)
    {
        conectar.setEnabled(bandera);
        generar.setEnabled(bandera);
        retrasar.setEnabled(bandera);
        tiempoActual.setEnabled(bandera);
        adelantar.setEnabled(bandera);
        reporte.setEnabled(bandera);
        DirIP1.setEnabled(bandera);
        DirIP2.setEnabled(bandera);
        DirIP3.setEnabled(bandera);
        DirIP4.setEnabled(bandera);
        item.setEnabled(bandera);
    }
     public void sendToAll(int msg) throws IOException {
        Enumeration e = clientes.elements();
        while (e.hasMoreElements()) {
            // Obtener el flujo de salida de los clientes
            nodoNombre nodo =(nodoNombre)e.nextElement();
            ObjectOutputStream chat = nodo.getSalida();
            // envia para todos, menos al emisor
                    chat.writeObject(msg);
                    chat.flush();
            
        }
      }   
    public void enviarDatosCarga(String mensaje,ObjectOutputStream chat) {
        // enviar objeto al cliente
        try {

            chat.writeObject(mensaje);
            chat.flush();
            //mostrarMensaje("\n"+mensaje);
        } // procesar problemas que pueden ocurrir al enviar el objeto
        catch (IOException excepcionES) {
            //areaPantalla.append("\nError al escribir objeto");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public void mostrarMensajeEvento( final String mensajeAMostrar )
    {

          documentoEvento+=mensajeAMostrar;
      // mostrar mensaje del subproceso de ejecucion de la GUI
      SwingUtilities.invokeLater(new Runnable() {  // clase interna para asegurar que la GUI se actualice apropiadamente

            public void run() // actualiza areaPantalla
            {         
               eventos.setText(documentoEvento);
            }

         }  // fin de la clase interna

      ); // fin de la llamada a SwingUtilities.invokeLater
    }
    public void mostrarMensajeReporte( final String mensajeAMostrar )
    {

          documentoReporte+=mensajeAMostrar;
      // mostrar mensaje del subproceso de ejecucion de la GUI
      SwingUtilities.invokeLater(new Runnable() {  // clase interna para asegurar que la GUI se actualice apropiadamente

            public void run() // actualiza areaPantalla
            {         
               reporte.setText(documentoEvento);
            }

         }  // fin de la clase interna

      ); // fin de la llamada a SwingUtilities.invokeLater
    }    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel6 = new javax.swing.JLabel();
        panelAlgoritmo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        item = new javax.swing.JComboBox();
        generar = new javax.swing.JButton();
        panelEventos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        reporte = new javax.swing.JEditorPane();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        eventos = new javax.swing.JEditorPane();
        jLabel10 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        panelCliente = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        DirIP1 = new javax.swing.JTextField();
        DirIP2 = new javax.swing.JTextField();
        DirIP3 = new javax.swing.JTextField();
        DirIP4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        conectar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        panelTiempo = new javax.swing.JPanel();
        retrasar = new javax.swing.JButton();
        adelantar = new javax.swing.JButton();
        tiempoActual = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        go = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Algoritmos de tiempo");
        setResizable(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Algoritmos");

        item.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cristhian", "Berkeley" }));
        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemActionPerformed(evt);
            }
        });

        generar.setText("Generar");
        generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAlgoritmoLayout = new javax.swing.GroupLayout(panelAlgoritmo);
        panelAlgoritmo.setLayout(panelAlgoritmoLayout);
        panelAlgoritmoLayout.setHorizontalGroup(
            panelAlgoritmoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelAlgoritmoLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(generar)
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlgoritmoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAlgoritmoLayout.setVerticalGroup(
            panelAlgoritmoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlgoritmoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generar)
                .addContainerGap())
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Eventos");

        reporte.setEditable(false);
        reporte.setContentType("text/html"); // NOI18N
        reporte.setToolTipText("");
        jScrollPane3.setViewportView(reporte);

        eventos.setEditable(false);
        eventos.setContentType("text/html"); // NOI18N
        eventos.setToolTipText("");
        jScrollPane4.setViewportView(eventos);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Reporte");

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout panelEventosLayout = new javax.swing.GroupLayout(panelEventos);
        panelEventos.setLayout(panelEventosLayout);
        panelEventosLayout.setHorizontalGroup(
            panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEventosLayout.createSequentialGroup()
                .addGroup(panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEventosLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEventosLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)))
            .addComponent(jSeparator3)
        );
        panelEventosLayout.setVerticalGroup(
            panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEventosLayout.createSequentialGroup()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addGroup(panelEventosLayout.createSequentialGroup()
                        .addGroup(panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEventosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4)))))
        );

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Conectarse a un servidor");

        jLabel5.setText("Direccion IP:");

        DirIP1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DirIP1.setText("127");
        DirIP1.setToolTipText("");
        DirIP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DirIP1ActionPerformed(evt);
            }
        });
        DirIP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DirIP1KeyTyped(evt);
            }
        });

        DirIP2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DirIP2.setText("0");
        DirIP2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DirIP2KeyTyped(evt);
            }
        });

        DirIP3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DirIP3.setText("0");
        DirIP3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DirIP3KeyTyped(evt);
            }
        });

        DirIP4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DirIP4.setText("1");
        DirIP4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DirIP4KeyTyped(evt);
            }
        });

        jLabel7.setText(" .");

        jLabel8.setText(" .");

        jLabel9.setText(" .");

        conectar.setText("Conectar");
        conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelClienteLayout = new javax.swing.GroupLayout(panelCliente);
        panelCliente.setLayout(panelClienteLayout);
        panelClienteLayout.setHorizontalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClienteLayout.createSequentialGroup()
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(conectar)
                        .addGap(96, 96, 96))
                    .addGroup(panelClienteLayout.createSequentialGroup()
                        .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelClienteLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DirIP1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(DirIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(DirIP3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(DirIP4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelClienteLayout.setVerticalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DirIP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(DirIP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DirIP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DirIP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conectar)
                .addGap(7, 7, 7))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        retrasar.setText("Retrasar 3 min");
        retrasar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrasarActionPerformed(evt);
            }
        });

        adelantar.setText("Adelantar 3 min");

        tiempoActual.setText("Obtener tiempo real");
        tiempoActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiempoActualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTiempoLayout = new javax.swing.GroupLayout(panelTiempo);
        panelTiempo.setLayout(panelTiempoLayout);
        panelTiempoLayout.setHorizontalGroup(
            panelTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTiempoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(retrasar)
                .addGap(50, 50, 50)
                .addComponent(tiempoActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(adelantar)
                .addGap(18, 18, 18))
        );
        panelTiempoLayout.setVerticalGroup(
            panelTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTiempoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(retrasar)
                    .addComponent(adelantar)
                    .addComponent(tiempoActual))
                .addGap(2, 2, 2))
        );

        jLabel3.setText("Nombre:");

        nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre.setText("N");
        nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        go.setText("GO");
        go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(go)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(go)
                    .addComponent(jLabel3)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panelAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelEventos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelTiempo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator5)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(panelAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemActionPerformed

    }//GEN-LAST:event_itemActionPerformed

    private void DirIP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DirIP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DirIP1ActionPerformed

    private void DirIP1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DirIP1KeyTyped
        String c = String.valueOf(evt.getKeyChar());
        if(!(c.matches("[0-9]"))||DirIP1.getText().length()== 3) {
            evt.consume();
        }
    }//GEN-LAST:event_DirIP1KeyTyped

    private void DirIP2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DirIP2KeyTyped
        String c = String.valueOf(evt.getKeyChar());
        if(!(c.matches("[0-9]"))||DirIP2.getText().length()== 3) {
            evt.consume();
        }
    }//GEN-LAST:event_DirIP2KeyTyped

    private void DirIP3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DirIP3KeyTyped
        String c = String.valueOf(evt.getKeyChar());
        if(!(c.matches("[0-9]"))||DirIP3.getText().length()== 3) {
            evt.consume();
        }
    }//GEN-LAST:event_DirIP3KeyTyped

    private void DirIP4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DirIP4KeyTyped
        String c = String.valueOf(evt.getKeyChar());
        if(!(c.matches("[0-9]"))||DirIP4.getText().length()== 3) {
            evt.consume();
        }
    }//GEN-LAST:event_DirIP4KeyTyped

    private void goActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goActionPerformed
        apodoNodo=nombre.getText();
        nombre.setEnabled(false);
        go.setEnabled(false); 
        goPanel(true);        
        if(estadoServer)
        {
            mostrarMensajeEvento("<i>Servidor "+apodoNodo+" activado!!!</i><br>");
        }
        else
        {
            mostrarMensajeEvento("<i>El servidor no pudo arrancar!!!</i><br>");
        }
    }//GEN-LAST:event_goActionPerformed

    private void conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarActionPerformed
        // TODO add your handling code here:
        IP=DirIP1.getText()+"."+DirIP2.getText()+"."+DirIP3.getText()+"."+DirIP4.getText();
        if(DirIP1.getText().compareToIgnoreCase("")==0 || DirIP2.getText().compareToIgnoreCase("")==0 
                || DirIP3.getText().compareToIgnoreCase("")==0 || DirIP4.getText().compareToIgnoreCase("")==0)
        {
            JOptionPane.showMessageDialog(null, "Acceso denegado:\n"
                + "Por favor llene todos los espacios", "Acceso denegado",
                JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try {
                cliente= new Socket(IP,12345);                        //Paso 1 : Conectar a servidor
                salida=new ObjectOutputStream( cliente.getOutputStream() );     //Paso 2 : Obtener flujos de entrada y salida
                salida.flush();
                entrada=new ObjectInputStream( cliente.getInputStream()) ;      //
                System.out.println("apodo  :"+ apodoNodo);
                salida.writeObject(apodoNodo);
                salida.flush();
                salida.writeObject( contraseña);  //Enviar clave al servidor
                salida.flush();
                Thread thread=new ControlCliente(cliente,entrada,salida,this,apodoNodo);//hilo del cliente
                thread.start();               
            }  catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }
    }//GEN-LAST:event_conectarActionPerformed

    private void generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarActionPerformed
        generar.setEnabled(false);
        String reporte="";
        if(item.getSelectedItem()=="Emisor secuencial")
        {

        }
        else if(item.getSelectedItem()=="Emisor menor carga")
        {

        }
        generar.setEnabled(true);
    }//GEN-LAST:event_generarActionPerformed

    private void tiempoActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiempoActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiempoActualActionPerformed

    private void retrasarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrasarActionPerformed
        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos,milisegundos;
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        milisegundos = calendario.get(Calendar.MILLISECOND);
        System.out.println(hora+":"+minutos+":"+segundos+":"+milisegundos);
    }//GEN-LAST:event_retrasarActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DirIP1;
    private javax.swing.JTextField DirIP2;
    private javax.swing.JTextField DirIP3;
    private javax.swing.JTextField DirIP4;
    private javax.swing.JButton adelantar;
    private javax.swing.JButton conectar;
    private javax.swing.JEditorPane eventos;
    private javax.swing.JButton generar;
    private javax.swing.JButton go;
    private javax.swing.JComboBox item;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField nombre;
    private javax.swing.JPanel panelAlgoritmo;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelEventos;
    private javax.swing.JPanel panelTiempo;
    private javax.swing.JEditorPane reporte;
    private javax.swing.JButton retrasar;
    private javax.swing.JButton tiempoActual;
    // End of variables declaration//GEN-END:variables
}
