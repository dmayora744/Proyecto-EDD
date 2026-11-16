/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;

/**
 * Panel que dibuja la red de transporte usando GraphStream.
 * Solo se usa para el renderizado; los datos viven en GrafoPonderado.
 */
public class PanelGrafo extends JPanel {

    private final Graph dibujo;
    private GrafoPonderado grafo;
    
    // Un color por subred; si hay más subredes que colores, se repiten
private static final String[] COLORES = {
    "#4a90d9", "#e67e22", "#27ae60", "#9b59b6", "#e74c3c", "#16a085"
};

    /**
     * Crea el panel y arranca el visor de GraphStream.
     * @param grafo grafo con los datos en memoria
     */
    public PanelGrafo(GrafoPonderado grafo) {
        this.grafo = grafo;
        this.setLayout(new BorderLayout());

        System.setProperty("org.graphstream.ui", "swing");
        dibujo = new SingleGraph("red");

        Viewer visor = new SwingViewer(dibujo, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        visor.enableAutoLayout();
        Component vista = (Component) visor.addDefaultView(false);
        this.add(vista, BorderLayout.CENTER);

        refrescar();
    }

    /**
     * Cambia el grafo a dibujar y vuelve a pintar.
     * @param grafo nuevo grafo
     */
    public void setGrafo(GrafoPonderado grafo) {
        this.grafo = grafo;
        refrescar();
    }

    /** Borra el dibujo y lo reconstruye desde el grafo en memoria. */
    public void refrescar() {
        dibujo.clear();
        dibujo.setAttribute("ui.stylesheet",
            "node { size: 18px; text-size: 12px; fill-color: #4a90d9; }"
            + "edge { text-size: 11px; fill-color: #666666; }");

        if (grafo == null) {
            return;
        }

        // 1) Un nodo por cada vértice
        NodoLista nv = grafo.getVertices().getCabeza();
        while (nv != null) {
            Vertice v = (Vertice) nv.getContenido();
            Node n = dibujo.addNode(v.getIdEstacion());
            n.setAttribute("ui.label", v.getIdEstacion());
            nv = nv.getSiguiente();
        }

        // 2) Una arista por cada conexión (cada una está guardada en ambos vértices)
        nv = grafo.getVertices().getCabeza();
        while (nv != null) {
            Vertice v = (Vertice) nv.getContenido();
            NodoLista na = v.getAdyacentes().getCabeza();
            while (na != null) {
                Arista a = (Arista) na.getContenido();
                String origen = v.getIdEstacion();
                String destino = a.getIdDestino();
                if (origen.compareTo(destino) < 0) {
                    Edge e = dibujo.addEdge(origen + "-" + destino, origen, destino);
                    e.setAttribute("ui.label", a.getTiempoMinutos() + " min");
                }
                na = na.getSiguiente();
            }
            nv = nv.getSiguiente();
        }
        // 3) Colorear cada subred con un color distinto   <- pega desde aquí
        ListaEnlazada subredes = grafo.obtenerSubredes();
        NodoLista ns = subredes.getCabeza();
        int numero = 0;
        while (ns != null) {
            ListaEnlazada subred = (ListaEnlazada) ns.getContenido();
            String color = COLORES[numero % COLORES.length];

            NodoLista nid = subred.getCabeza();
            while (nid != null) {
                Node n = dibujo.getNode((String) nid.getContenido());
                if (n != null) {
                    n.setAttribute("ui.style", "fill-color: " + color + ";");
                }
                nid = nid.getSiguiente();
            }
            numero++;
            ns = ns.getSiguiente();
        }
    }
}
