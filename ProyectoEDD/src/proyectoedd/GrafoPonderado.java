/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

/**
 *
 * @author ile1
 */
public class GrafoPonderado {

    private ListaEnlazada vertices;
    
    public GrafoPonderado(){
        this.vertices = new ListaEnlazada();
    }
    
    public void agregarVertices(String id){
        if (buscarVertice(id) == null){
            vertices.agregar (new Vertice(id));
        }
    }
    
    public Vertice buscarVertice(String id){
        NodoLista actual = vertices.getCabeza();
        while (actual != null){
            Vertice v = (Vertice) actual.getContenido();
            if (v.getIdEstacion().equals(id)){
                return v;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public void agregarVertice(String id){
        if(buscarVertice(id) == null){
            vertices.agregar(new Vertice(id));
        }
    }
    
    public void agregarAristaNoDirigida(String origen, String destino, int tiempo){
        Vertice vOrigen = buscarVertice(origen);
        Vertice vDestino = buscarVertice(destino);
        
        if (vOrigen == null){
            vOrigen = new Vertice(origen);
            vertices.agregar(vOrigen);
        }
        if (vDestino == null){
            vDestino = new Vertice(destino);
            vertices.agregar(vDestino);
        }
        
        vOrigen.getAdyacentes().agregar(new Arista (destino, tiempo));
        vDestino.getAdyacentes().agregar(new Arista(origen, tiempo));
    }
    
    public ListaEnlazada getVertices(){
        return vertices;
    }
    
    /**
 * Busca, en la lista de adyacentes de un vértice, la arista que llega a un destino dado.
 *
 * @param v         vértice cuyas conexiones se revisan
 * @param idDestino ID de la estación al otro extremo de la arista
 * @return la arista encontrada, o null si no existe
 */
private Arista buscarArista(Vertice v, String idDestino){
    NodoLista actual = v.getAdyacentes().getCabeza();
    while (actual != null){
        Arista a = (Arista) actual.getContenido();
        if (a.getIdDestino().equals(idDestino)){
            return a;
        }
        actual = actual.getSiguiente();
    }
    return null;
}

/**
 * Elimina la conexión entre dos estaciones (en ambos sentidos).
 *
 * @param origen  ID de una de las estaciones
 * @param destino ID de la otra estación
 * @return true si la conexión existía y fue eliminada, false en caso contrario
 */
public boolean eliminarArista(String origen, String destino){
    Vertice vOrigen = buscarVertice(origen);
    Vertice vDestino = buscarVertice(destino);
    if (vOrigen == null || vDestino == null){
        return false;
    }

    boolean eliminada = false;
    Arista a = buscarArista(vOrigen, destino);
    while (a != null){
        vOrigen.getAdyacentes().eliminar(a);
        eliminada = true;
        a = buscarArista(vOrigen, destino);
    }
    a = buscarArista(vDestino, origen);
    while (a != null){
        vDestino.getAdyacentes().eliminar(a);
        eliminada = true;
        a = buscarArista(vDestino, origen);
    }
    return eliminada;
}

/**
 * Elimina una estación del grafo junto con todas sus conexiones.
 *
 * @param id ID de la estación a eliminar
 * @return true si la estación existía y fue eliminada, false en caso contrario
 */
public boolean eliminarVertice(String id){
    Vertice v = buscarVertice(id);
    if (v == null){
        return false;
    }

    //Quitar de cada vecino la arista que apunta a este vértice
    NodoLista actual = v.getAdyacentes().getCabeza();
    while (actual != null){
        Arista a = (Arista) actual.getContenido();
        Vertice vecino = buscarVertice(a.getIdDestino());
        if (vecino != null){
            Arista inversa = buscarArista(vecino, id);
            while (inversa != null){
                vecino.getAdyacentes().eliminar(inversa);
                inversa = buscarArista(vecino, id);
            }
        }
        actual = actual.getSiguiente();
    }

    //Quitar el vértice de la lista de vértices
    vertices.eliminar(v);
    return true;
}
}

