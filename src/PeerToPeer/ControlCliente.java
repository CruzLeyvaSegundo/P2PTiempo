/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PeerToPeer;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SEGUNDO
 */
public class ControlCliente extends Thread {
   private static Interfaz panel;
   private final ObjectOutputStream salida;
   private final ObjectInputStream entrada;
   private final String apodo;
   private String apodoServidor;
   private final Socket cliente;
    ControlCliente(Socket socket,ObjectInputStream input,ObjectOutputStream output,Interfaz panel,String apodo) {
        ControlCliente.panel = panel;
        this.cliente=socket;
        this.entrada=input;
        this.salida=output;
        this.apodo=apodo;
    }
   public void enviarDatosCarga(String mensaje )
   {
      // enviar objeto al servidor
      try {
          
         salida.writeObject( mensaje );
         salida.flush();
      }

      // procesar los problemas que pueden ocurrir al enviar el objeto
      catch ( IOException excepcionES ) {
          System.out.println("\nError al escribir el objeto" );
      }
   } 
    public void enviarDatosTexto(String mensaje )
   {
      // enviar objeto al servidor
      try {
          
         salida.writeObject( mensaje );
         salida.flush();
      }

      // procesar los problemas que pueden ocurrir al enviar el objeto
      catch ( IOException excepcionES ) {
          System.out.println("\nError al escribir el objeto" );
      }
   } 
    // conectarse al servidor y procesar mensajes del servidor
   @Override
   public void run() 
   {
      // procesar la conexion
      try {
       //  panel.mostrarMensaje( "<i>"+"Intentando realizar conexion"+"</i><br>"  );
         System.out.println("cliente!!!: "+cliente);
         // mostrar la informacion de la conexion
       /* panel.mostrarMensaje( "<i>Conectado a: " + 
         cliente.getInetAddress().getHostName() +"</i><br>" );*/
         procesarConexion(); // Paso 3: procesar la conexion
      }
      // el servidor cerro la conexion
      catch ( EOFException excepcionEOF ) {
         System.err.println( "El cliente termino la conexion" );
      }

      // procesar los problemas que pueden ocurrir al comunicarse con el servidor
      catch ( IOException excepcionES ) {
      } catch (ClassNotFoundException ex) {
           Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
       }
      finally {
         cerrarConexion(); // Paso 4: cerrar la conexion
      }

   } // fin del motodo run

   // procesar la conexion con el servidor
   private void procesarConexion() throws IOException,ClassNotFoundException
   {
        apodoServidor = (String) entrada.readObject();
        panel.mostrarMensajeEvento("<i>Este nodo se ha conectado al servidor "+apodoServidor+"!!!</i><br>");
        System.out.println("Cliente "+apodo+" reportando apodoServidor: "+apodoServidor);       
        while (true)
        {      
            try{
            //System.out.println("error = " +entrada.readObject().getClass());         
            Object  mensaje =(Object)entrada.readObject();

            }
            catch (IOException exx)
            {
           
            }

        }   
   } // fin del metodo procesarConexion
   
   // cerrar flujos y socket
   private void cerrarConexion() 
   {
    //  panel.mostrarMensaje( "\nCerrando conexion" );

      try {
         salida.close();
         entrada.close();
         cliente.close();
      }
      catch( IOException excepcionES ) {
         excepcionES.printStackTrace();
      }
   }
   

   
    
}
