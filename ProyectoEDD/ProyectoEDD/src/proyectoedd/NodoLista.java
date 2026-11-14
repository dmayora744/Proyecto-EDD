/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

/**
 *
 * @author ile1
 */
public class NodoLista {

    private Object contenido;
    private NodoLista siguiente;

    public NodoLista(Object contenido){
        this.contenido = contenido;
        this.siguiente = null; 
    }
  
    public Object getContenido(){
        return contenido;
    }
    
    public void setContenido(Object contenido){
        this.contenido = contenido;
    }
    
    public NodoLista getSiguiente(){
        return siguiente;
    }
    
    public void setSiguiente(NodoLista siguiente){
        this.siguiente = siguiente;
    }
    
}
