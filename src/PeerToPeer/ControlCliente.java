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
import java.util.Calendar;
import java.util.Date;
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
    public void horaActual(Date tiempo)
    {
        Calendar calendario = Calendar.getInstance();     
        int hora, minutos, segundos,milisegundos;
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        tiempo.setHours(hora);
        tiempo.setMinutes(minutos);
        tiempo.setSeconds(segundos);       
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
            //System.out.println("error = " +entrada.readObject().getClass());         
            Object  mensaje =(Object)entrada.readObject();
            System.out.println("clase mensaje: "+mensaje.getClass().toString());
            if(mensaje.getClass().toString().compareTo("class PeerToPeer.nodoNombre")==0)
            {
                nodoNombre T1=(nodoNombre)mensaje;
                Date tiempoServidor=T1.getTiempo();
                Date tiempoCliente=new Date();
                System.out.println(tiempoServidor.getHours()+":"+tiempoServidor.getMinutes()+":"+tiempoServidor.getSeconds());
                horaActual(tiempoCliente);
                salida.writeObject(new nodoNombre(apodo,tiempoCliente));
            }
            else 
            {
                String desv=(String)mensaje;
                System.out.println("Mi desviacion es: "+desv);
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
