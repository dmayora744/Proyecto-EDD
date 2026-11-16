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
    private int distancia;
private Vertice anterior;
    
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

/**
 * Devuelve el grado del vértice: la cantidad de conexiones directas que tiene.
 *
 * @return número de aristas incidentes al vértice
 */
public int getGrado(){
    return adyacentes.getTamano();
}
/**
 * Devuelve la distancia (tiempo mínimo conocido) desde el origen de la búsqueda.
 *
 * @return distancia en minutos
 */
public int getDistancia(){
    return distancia;
}

/**
 * Establece la distancia desde el origen de la búsqueda.
 *
 * @param distancia nueva distancia en minutos
 */
public void setDistancia(int distancia){
    this.distancia = distancia;
}

/**
 * Devuelve el vértice previo en el camino más corto encontrado.
 *
 * @return vértice anterior, o null si no tiene
 */
public Vertice getAnterior(){
    return anterior;
}

/**
 * Establece el vértice previo en el camino más corto.
 *
 * @param anterior vértice previo
 */
public void setAnterior(Vertice anterior){
    this.anterior = anterior;
}
}
