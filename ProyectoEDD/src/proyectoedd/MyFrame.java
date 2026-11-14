
package proyectoedd;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFrame extends JFrame implements ActionListener{
    
    private JButton buttonGuardar;
            
    //Componentes de la interfaz
    private JButton buttonCargar;
    
    //Estructuras de datos principales
    private TablaHash tablaHash;
    private GrafoPonderado grafo;
    
    //Bandera booleana para las sobresctrituras
    private boolean hayDatosEnMemoria;
    
    public MyFrame(){
        
        buttonGuardar = new JButton("Guardar Red a CSV");
        buttonGuardar.addActionListener(this);
        this.add(buttonGuardar);
        //Inicio de las estructuras de datos vacías al arrancar la ventana
        this.tablaHash = new TablaHash(101);
        this.grafo = new GrafoPonderado();
        this.hayDatosEnMemoria = false;
        
        //Interfaz gráfica al JFrame
        Interfaz panelInterfaz = new Interfaz(this.tablaHash, this.grafo);
        this.add(panelInterfaz);
        
        //Configuración básica del JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setTitle("Urban Route - Red Multimodal");
        
        //Configuración del botón
        buttonCargar = new JButton("Cargar Archivo CSV");
        buttonCargar.addActionListener(this);
        
        this.add(buttonCargar);
        this.pack();
        this.setLocationRelativeTo(null); //Centrar la ventana en pantalla
    }
    //Alerta de datos
    public boolean verificarCambiosSinGuardar(){ 
        if(hayDatosEnMemoria){
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "Atención: Hay datos cargados en memoria. Si carga un nuevo archivo se sobrescribirán. ¿Desea continuar?",
                    "Alerta de Sobrescritura",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            return respuesta == JOptionPane.YES_OPTION;    
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
         
        
        if (e.getSource() == buttonCargar){
            //Ejecutar la alerta
            if(!verificarCambiosSinGuardar()){
                return;
            }
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            
            int response = fileChooser.showOpenDialog(this);
            
            if (response == JFileChooser.APPROVE_OPTION){
                File archivoSeleccionado = fileChooser.getSelectedFile();
                
                this.tablaHash = new TablaHash(101);
                this.grafo = new GrafoPonderado();
                
                boolean exito = LectorCSV.cargarArchivo(archivoSeleccionado, this.tablaHash, this.grafo);
                
                if (exito){
                    this.hayDatosEnMemoria = true;
                    JOptionPane.showMessageDialog(
                            this,
                            "¡Éxito! El archivo '" + archivoSeleccionado.getName() + "' fue cargado correctamente en memoria.",
                            "Carga Completada",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else if(e.getSource() == buttonGuardar){
               if (!hayDatosEnMemoria) {
                JOptionPane.showMessageDialog(this, "No hay datos en memoria para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fileChooserGuardar = new JFileChooser();
            fileChooserGuardar.setDialogTitle("Guardar archivo de red");
            fileChooserGuardar.setSelectedFile(new File("red_actualizada.csv"));
            fileChooserGuardar.setFileFilter(new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));

            int userSelection = fileChooserGuardar.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File archivoGuardar = fileChooserGuardar.getSelectedFile();
                
                if (!archivoGuardar.getName().toLowerCase().endsWith(".csv")) {
                    archivoGuardar = new File(archivoGuardar.getParentFile(), archivoGuardar.getName() + ".csv");
                }

                try (java.io.PrintWriter writerGuardar = new java.io.PrintWriter(archivoGuardar)) {
                    
                    writerGuardar.println("ID_Estacion,Nombre_Estacion,Linea,Zona,Capacidad_Pasajeros_Hora");

                    NodoLista actualV = ((ListaEnlazada) grafo.getVertices()).getCabeza();
                    while (actualV != null) {
                        Vertice v = (Vertice) actualV.getContenido(); 
                        String id = v.getIdEstacion();
                        Estacion eHash = tablaHash.buscar(id);
                        
                        if (eHash != null) {
                            writerGuardar.println(eHash.getID() + "," + eHash.getNombre() + "," + eHash.getLinea() + "," + eHash.getZona() + "," + eHash.getCapacidad());
                        }
                        actualV = actualV.getSiguiente();
                    }

                    writerGuardar.println();
                    writerGuardar.println("Estacion_Origen,Estacion_Destino,Tiempo_Traslado_Min");

                    actualV = ((ListaEnlazada) grafo.getVertices()).getCabeza();
                    String aristasImpresas = ""; 

                    while (actualV != null) {
                        Vertice v = (Vertice) actualV.getContenido();
                        NodoLista actualArista = v.getAdyacentes().getCabeza();
                        
                        while (actualArista != null) {
                            Arista a = (Arista) actualArista.getContenido();
                            String origen = v.getIdEstacion();
                            String destino = a.getIdDestino();
                            
                            if (!aristasImpresas.contains(destino + "-" + origen)) {
                                writerGuardar.println(origen + "," + destino + "," + a.getTiempoMinutos());
                                aristasImpresas += origen + "-" + destino + ",";
                            }
                            
                            actualArista = actualArista.getSiguiente();
                        }
                        actualV = actualV.getSiguiente();
                    }

                    JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        }  
        }
        }
    }  