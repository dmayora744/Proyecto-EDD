/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

/**
 *
 * @author ile1
 */
public class ListaEnlazada {

    private NodoLista cabeza;
    private int tamano;
    
    public ListaEnlazada(){
        this.cabeza = null;
        this.tamano = 0;
    }
    
    public NodoLista getCabeza(){
        return cabeza;
    }
    
    //Método para agreagr un elemento al final de la lista
    public void agregar(Object contenido){
        NodoLista nuevoNodo = new NodoLista(contenido);
        
        if(cabeza == null){
            cabeza = nuevoNodo;
        }else{
            NodoLista actual = cabeza;
            while (actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }
            tamano++;
        }
    
        }
    //Método para eliminar un nodo específico
    public void eliminar(Object contenido){
            if(cabeza == null) return;
            
            //Si el elemento a eliminar es la cabeza
            if (cabeza.getContenido().equals(contenido)){
                cabeza = cabeza.getSiguiente();
                tamano--;
                return;
            }   
            NodoLista actual = cabeza;
            while(actual.getSiguiente()!=null){
                if (actual.getSiguiente().getContenido().equals(contenido)){
                    actual.setSiguiente(actual.getSiguiente().getSiguiente());
                    tamano--;
                    return;
            
                }         
                actual = actual.getSiguiente();
            }
    }
    public int getTamano(){
        return tamano;
    }
    
    
}
