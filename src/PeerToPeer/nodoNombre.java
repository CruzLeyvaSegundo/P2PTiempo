/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PeerToPeer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author SEGUNDO
 */
public class nodoNombre implements Serializable{
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private String nombre; 

    public nodoNombre(ObjectOutputStream salida, ObjectInputStream entrada, String nombre) {
        this.salida = salida;
        this.entrada = entrada;
        this.nombre = nombre;
    }

    public nodoNombre(ObjectOutputStream salida, String nombre) {
        this.salida = salida;
        this.nombre = nombre;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public String getNombre() {
        return nombre;
    }
    
}
