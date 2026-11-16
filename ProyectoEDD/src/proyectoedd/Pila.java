/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

/**
 *
 * @author Santiago
 *
/**
 * Pila (estructura LIFO: el último en entrar es el primero en salir),
 * implementada con nodos enlazados y un apuntador a la cima.
 */
public class Pila {

    private NodoLista cima;
    private int tamano;

    /** Crea una pila vacía. */
    public Pila(){
        this.cima = null;
        this.tamano = 0;
    }

    /**
     * Indica si la pila está vacía.
     *
     * @return true si no tiene elementos
     */
    public boolean estaVacia(){
        return cima == null;
    }

    /**
     * Agrega un elemento en la cima de la pila.
     *
     * @param elemento objeto que se apila
     */
    public void apilar(Object elemento){
        NodoLista nuevo = new NodoLista(elemento);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamano++;
    }

    /**
     * Extrae y devuelve el elemento de la cima.
     *
     * @return el elemento de la cima, o null si la pila está vacía
     */
    public Object desapilar(){
        if (estaVacia()){
            return null;
        }
        Object elemento = cima.getContenido();
        cima = cima.getSiguiente();
        tamano--;
        return elemento;
    }

    /**
     * Devuelve el elemento de la cima sin extraerlo.
     *
     * @return el elemento de la cima, o null si la pila está vacía
     */
    public Object verCima(){
        if (estaVacia()){
            return null;
        }
        return cima.getContenido();
    }

    /**
     * Devuelve la cantidad de elementos en la pila.
     *
     * @return número de elementos
     */
    public int getTamano(){
        return tamano;
    }
}
