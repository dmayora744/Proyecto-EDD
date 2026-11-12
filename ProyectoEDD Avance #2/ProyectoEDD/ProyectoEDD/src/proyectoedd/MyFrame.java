
package proyectoedd;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        
        buttonGuardar = new JButton("5) Guardar Red a CSV");
        buttonGuardar.addActionListener(this);
        panelBotones.add(buttonGuardar);
        //Inicio de las estructuras de datos vacías al arrancar la ventana
        this.tablaHash = new TablaHash(101);
        this.grafo = new GrafoPonderado();
        this.hayDatosEnMemoria = false;
        
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
                if(!hayDatosEnMemoria){
                JOptionPane.showMessageDialog(this, "No hay datos en memoria para guardar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
                }
                
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar archivo de red");
                fileChooser.setSelectedFile(new File("red_actualizada.csv"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));
                
                int userSelection = fileChooser.showSaveDialog(this);
                
                if (userSelection == JFileChooser.APPROVE_OPTION){
                    File archivoGuardar = fileChooser.getSelectedFile();
                    
                    //Asegurar que archivo termine .csv
                    if (!archivoGuardar.getName().toLowerCase().endsWith(".csv")){
                        archivoGuardar = new File(archivoGuardar.getParentFile(), archivoGuardar.getName() + ".csv");
                    }
                    
                    try(java.io.PrintWriter writer = new java.io.PrintWriter(archivoGuardar)){
                        writer.println("ID_Estacion, Nombre_Estacion, Linea, Zona, Capacidad_Pasajeros_Hora");
                        
                        //Extraer estaciones del Grafo (o Tabla Hash)
                        NodoLista actualV = grafo.getVertices().getCabeza();
                    }
                }
                }
            }
            
        }
    }
    
}

