/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

/**
 *
 * @author Usuario
 */
public class Arista {
    private final String idDestino;
    private int tiempoMinutos;
    
    public Arista(String idDestino, int tiempoMinutos){
        this.idDestino = idDestino;
        this.tiempoMinutos = tiempoMinutos;
    }
    
    public String getIdDestino(){
        return idDestino;
    }
    
    public int getTiempoMinutos(){
        return tiempoMinutos;
    }

    void setTiempo(int nuevoTiempo) {
        this.tiempoMinutos = nuevoTiempo;
    }
    
   
}
