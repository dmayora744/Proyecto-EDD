/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

/**
 *representa una estaciòn (parada) de la red de transporte multi modal.
 * @author ile1
 */
public class Estacion {
    private String id;
    private String nombre;
    private String linea;
    private String zona;
    private int capcacidad;
    
    public Estacion(String id, String nombre, String linea, String zona, int capacidad){
        this.id = id;
        this.nombre = nombre;
        this.linea = linea;
        this.zona = zona;
        this.capcacidad = capacidad;
    }
    
    public String getID() {return id;}
    public String getNombre() {return nombre;}
    public String getLinea() {return linea; }
    public String getZona() {return zona;}
    public int getCapacidad() {return capcacidad; }
}