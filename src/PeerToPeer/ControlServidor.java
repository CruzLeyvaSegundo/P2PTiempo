/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PeerToPeer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author SEGUNDO
 */
public class ControlServidor extends Thread{
    private static Vector<nodoNombre> clientes =new Vector();
    private static int n=0;
    private static Interfaz panel;
    private static String nombre;  
    private final String clave ="";
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private ServerSocket servidor;
    private final Socket conexion;

    public ControlServidor(Socket conexion) {
        this.conexion = conexion;
        
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
    public int obtenerDesviacion(Date actual,Date antes,Date cliente)
    {
        int segActual,segAntes,segCliente,D;
        segActual=actual.getHours()*3600+actual.getMinutes()*60+actual.getSeconds();
        segAntes=antes.getHours()*3600+antes.getMinutes()*60+antes.getSeconds();
        segCliente=cliente.getHours()*3600+cliente.getMinutes()*60+cliente.getSeconds();
        D=segActual-segAntes;       
        return segCliente-segAntes-D/2;
    }
    // configurar y ejecutar el servidor 
    public void sendTo(String destino,String msg) throws IOException {
        Enumeration e = clientes.elements();
        while (e.hasMoreElements()) {
            // Obtener el flujo de salida de los clientes
            nodoNombre nodo =(nodoNombre)e.nextElement();
            ObjectOutputStream chat = nodo.getSalida();
            // envia para todos, menos al emisor
            if (nodo.getNombre().compareToIgnoreCase(destino)==0) {
                panel.enviarDatosCarga(msg,chat);
            }
        }
      }
     
    @Override
    public void run() {
        try {
            obtenerFlujos();        // Paso 3: obtener flujos de entrada y salida.
            procesarConexion(); // Paso 4: procesar la conexion.
            cerrarConexion();          
        } catch (IOException ex) {
            Logger.getLogger(ControlServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // obtener flujos para enviar y recibir datos
    private void obtenerFlujos() throws IOException {
        // establecer flujo de salida para los objetos
        salida = new ObjectOutputStream( conexion.getOutputStream() );
        salida.flush(); // vaciar búfer de salida para enviar información de encabezado
        
        // establecer flujo de entrada para los objetos
        entrada = new ObjectInputStream( conexion.getInputStream() );
        // establecer flujo de entrada para los objetos

        //panel.mostrarMensaje("\nEn Server: Se recibieron los flujos de E/S \n");
    }
    // procesar la conexion con el cliente
    private void procesarConexion() throws IOException, ClassNotFoundException {
            //SE CAPTURA EL NOMBRE DE LA PC 
            nombre=panel.getApodoNodo();
            //SE RECIBE EL NOMBRE DEL CLIENTE
            String apodoCliente=(String)entrada.readObject();
            n++;             
            System.out.println("apodoCliente: "+ apodoCliente);
            clientes.add(new nodoNombre(salida,entrada,apodoCliente));
            String contraServidor =(String)entrada.readObject();
            System.out.println("contra: "+contraServidor);
            if(contraServidor.compareToIgnoreCase(clave)!=0)
            {
                //panel.enviarDatos("<i>Clave incorrecta su acceso fue denegado</i><br>" ,salida);
                
            }
            else
            {             
                salida.writeObject(nombre);
                System.out.println(nombre + " se ha conectado al servidor!");       
                panel.mostrarMensajeEvento("<i>El cliente "+apodoCliente + " se ha conectado a este nodo!!!</i><br>");
                while (true)
                {
                    // Envia la linea a todos los clientes conectados              
                        //sendToAll(salida,"<i>"+nombre+">>>"+msg+"</i><br>");
                    // Esperar una nueva linea
                    Object msg =(Object)entrada.readObject();
                    if(msg.getClass().toString().compareTo("class PeerToPeer.nodoNombre")==0)
                    {
                        nodoNombre T2=(nodoNombre)msg;
                        Date tiempoCliente=T2.getTiempo();
                        Date tiempoNodoActual=new Date();
                        System.out.println(tiempoNodoActual.getHours()+":"+tiempoNodoActual.getMinutes()+":"+tiempoNodoActual.getSeconds());
                        horaActual(tiempoNodoActual);
                        Date tiempoNodoAntes=panel.getTiempoNodo();
                        int desvT2=obtenerDesviacion(tiempoNodoActual,tiempoNodoAntes,tiempoCliente);                        
                        sendTo(T2.getNombre(),String.valueOf(desvT2));
                    }
                    else
                    {
                        System.out.println("error recibiendo hora");
                    }                    
   
                }
                //si el cliente envia linea en blaco, se cierra su chat y la conexion
                // Mensaje de salida para los clientes conectados
            }
            //remove nombre de la lista de clientes conectados            
            clientes.remove(salida);
            //Cerrar la conexion de ese cliente 
            cerrarConexion();
            

    } // fin del metodo procesarConexion

    // cerrar flujos y socket
    private void cerrarConexion() {
        try {
            salida.close();
            entrada.close();
            conexion.close();
        } catch (IOException excepcionES) {
                 
        }
    }
    // enviar mensaje al cliente  
    public static void main(String args[]) {
        clientes=new Vector<nodoNombre>();
        JFrame.setDefaultLookAndFeelDecorated(true);   
        try {  
            panel=new Interfaz(clientes);
            panel.setVisible(true);
            panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
            // crea un socket que esta escuchando en el puerto 12345
            ServerSocket server = new ServerSocket(12345,100);      
            panel.setEstadoServer(true);
            // Loop principal.
            while (true) {
                // Esperar Cliente
                Socket conexion = server.accept();
                // crea un nuevo hilo para atender una conexion
                Thread t = new ControlServidor(conexion);
                t.start();
                //volver al bucle para esperar otra conexion
            }
        } catch (IOException e) {
                // Si hay alguna excepcion ES
            System.out.println("IOException: " + e);
        }          
    }    
}
