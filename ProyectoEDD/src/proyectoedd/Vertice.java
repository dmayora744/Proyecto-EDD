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
    private boolean visitado;
    
    public Vertice(String idEstacion){
        this.idEstacion = idEstacion;
        this.adyacentes = new ListaEnlazada();
        this.visitado = false;
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
    
    
    /**
 * Indica si el vértice ya fue visitado en el recorrido actual.
 *
 * @return true si ya fue visitado
 */
public boolean isVisitado(){
    return visitado;
}

/**
 * Marca o desmarca el vértice como visitado.
 *
 * @param visitado nuevo valor de la marca
 */
public void setVisitado(boolean visitado){
    this.visitado = visitado;
}
}
