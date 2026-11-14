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
    
    public Object getVertices(){
        return vertices;
    }
    
}
