/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

/**
 *
 * @author Usuario
 */
class Vertice {

    
    private String idEstacion;
    private ListaEnlazada adyacentes;
    
    public Vertice(String idEstacion){
        this.idEstacion = idEstacion;
        this.adyacentes = new ListaEnlazada();
    }
    
    public String getIdEstacion(){
        return idEstacion;
    }
    
    public void setIdEstacion(String idEstacion){
        this.idEstacion = idEstacion;
    }
    
    public ListaEnlazada getAdyacentes(){
        return adyacentes;
    }
    
    public void agregarAdyacencia(Object arista){
        this.adyacentes.agregar(arista);
    }
    
}
