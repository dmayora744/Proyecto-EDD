/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

/**
 *  Cola (estructura FIFO: el primero en entrar es el primero en salir),
 * implementada con nodos enlazados y apuntadores al frente y al fondo.
 * @author Santiago
 */

public class Cola {

    private NodoLista frente;
    private NodoLista fondo;
    private int tamano;

    /** Crea una cola vacía. */
    public Cola(){
        this.frente = null;
        this.fondo = null;
        this.tamano = 0;
    }

    /**
     * Indica si la cola está vacía.
     *
     * @return true si no tiene elementos
     */
    public boolean estaVacia(){
        return frente == null;
    }

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param elemento objeto que se encola
     */
    public void encolar(Object elemento){
        NodoLista nuevo = new NodoLista(elemento);
        if (estaVacia()){
            frente = nuevo;
        } else {
            fondo.setSiguiente(nuevo);
        }
        fondo = nuevo;
        tamano++;
    }

    /**
     * Extrae y devuelve el elemento que está al frente de la cola.
     *
     * @return el elemento del frente, o null si la cola está vacía
     */
    public Object desencolar(){
        if (estaVacia()){
            return null;
        }
        Object elemento = frente.getContenido();
        frente = frente.getSiguiente();
        if (frente == null){
            fondo = null;
        }
        tamano--;
        return elemento;
    }

    /**
     * Devuelve el elemento del frente sin extraerlo.
     *
     * @return el elemento del frente, o null si la cola está vacía
     */
    public Object verFrente(){
        if (estaVacia()){
            return null;
        }
        return frente.getContenido();
    }

    /**
     * Devuelve la cantidad de elementos en la cola.
     *
     * @return número de elementos
     */
    public int getTamano(){
        return tamano;
    }
}
