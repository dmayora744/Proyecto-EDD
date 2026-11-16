/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

/**
 *
 * @author Santiago
 */

/**
 * Resultado de una búsqueda de ruta: las estaciones recorridas, en orden,
 * y el tiempo total del trayecto.
 */
public class Ruta {

    private final ListaEnlazada estaciones;
    private final int tiempoTotal;

    /**
     * Crea una ruta.
     *
     * @param estaciones lista con los IDs de las estaciones, de origen a destino
     * @param tiempoTotal tiempo total del trayecto en minutos
     */
    public Ruta(ListaEnlazada estaciones, int tiempoTotal){
        this.estaciones = estaciones;
        this.tiempoTotal = tiempoTotal;
    }

    /**
     * Devuelve los IDs de las estaciones de la ruta, de origen a destino.
     *
     * @return lista de IDs (String)
     */
    public ListaEnlazada getEstaciones(){
        return estaciones;
    }

    /**
     * Devuelve el tiempo total del trayecto.
     *
     * @return tiempo en minutos
     */
    public int getTiempoTotal(){
        return tiempoTotal;
    }
}
