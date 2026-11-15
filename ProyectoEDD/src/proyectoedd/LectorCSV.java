/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 * Lee archivos CSV y construye con ellos la tabla hash y el grafo.
 * Acepta dos tipos de líneas: estaciones (5 columnas) y conexiones (3 columnas).
 */
public class LectorCSV {

    /**
     * Carga un archivo CSV en la tabla hash y en el grafo.
     *
     * @param archivo   archivo CSV elegido por el usuario
     * @param tablaHash tabla donde se indexan las estaciones
     * @param grafo     grafo donde se registran los vértices y las conexiones
     * @return true si la carga fue exitosa, false si ocurrió un error
     */
    public static boolean cargarArchivo(File archivo, TablaHash tablaHash, GrafoPonderado grafo){
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;

            while((linea = br.readLine()) != null){
                linea = linea.trim();
                if(linea.isEmpty()) continue;

                //Se ignoran las cabeceras, estén donde estén en el archivo
                if(linea.startsWith("Estacion_Origen") || linea.startsWith("ID_Estacion")){
                    continue;
                }

                String[] partes = linea.split(",");

                //Caso 1: archivo de conexiones de 3 columnas: Origen, Destino y Tiempo
                if (partes.length == 3){
                    String origen = partes[0].trim();
                    String destino = partes[1].trim();
                    int tiempo = Integer.parseInt(partes[2].trim());

                    if(tablaHash.buscar(origen) == null){
                        tablaHash.insertar(origen, new Estacion(origen, origen, "Línea 1", "General", 0));
                    }
                    if(tablaHash.buscar(destino) == null){
                        tablaHash.insertar(destino, new Estacion(destino, destino, "Línea 1", "General", 0));
                    }

                    grafo.agregarAristaNoDirigida(origen, destino, tiempo);
                }
                //Caso 2: archivo de datos de estaciones (5 columnas)
                else if (partes.length == 5){
                    String id = partes[0].trim();
                    String nombre = partes[1].trim();
                    String lineaMetro = partes[2].trim();
                    String zona = partes[3].trim();
                    int capacidad = Integer.parseInt(partes[4].trim());

                    Estacion e = new Estacion(id, nombre, lineaMetro, zona, capacidad);
                    tablaHash.insertar(id, e);
                    grafo.agregarVertice(id);
                }
            }
            return true; //Carga exitosa
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error al procesar el archivo CSV: " + ex.getMessage(), "Error de lectura", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
