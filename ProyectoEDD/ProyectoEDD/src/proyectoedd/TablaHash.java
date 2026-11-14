

package proyectoedd;

// Clase auxiliar para almacenar el par Clave-Valor en la Tabla Hash
class EntradaHash {
    private String clave;
    private Estacion valor;

    public EntradaHash(String clave, Estacion valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public String getClave() { return clave; }
    public Estacion getValor() { return valor; }
    public void setValor(Estacion valor) { this.valor = valor; }
}

public class TablaHash {

    // Ya no usamos <EntradaHash> aquí
    private ListaEnlazada[] tabla;
    private int capacidad;
    private int numElementos;

    public TablaHash(int capacidad) {
        this.capacidad = capacidad;
        this.numElementos = 0;
        this.tabla = new ListaEnlazada[capacidad];
        
        // Inicializar cada cubeta con una lista enlazada normal
        for (int i = 0; i < capacidad; i++) {
            this.tabla[i] = new ListaEnlazada();
        }
    }

    private int funcionHash(String clave) {
        if (clave == null) return 0;
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (31 * hash + clave.toUpperCase().charAt(i)) % capacidad;
        }
        return Math.abs(hash);
    }

    public void insertar(String clave, Estacion estacion) {
        int indice = funcionHash(clave);
        ListaEnlazada lista = tabla[indice];

        NodoLista actual = lista.getCabeza();
        while (actual != null) {
            // Se castea el objeto genérico a EntradaHash
            EntradaHash entrada;
            entrada = (EntradaHash) actual.getContenido();
            if (entrada.getClave().equalsIgnoreCase(clave)) {
                entrada.setValor(estacion); 
                return;
            }
            actual = actual.getSiguiente();
        }

        lista.agregar(new EntradaHash(clave, estacion));
        numElementos++;
    }

    public Estacion buscar(String clave) {
        if (clave == null) return null;
        int indice = funcionHash(clave);
        ListaEnlazada lista = tabla[indice];

        NodoLista actual = lista.getCabeza();
        while (actual != null) {
            EntradaHash entrada = (EntradaHash) actual.getContenido();
            if (entrada.getClave().equalsIgnoreCase(clave)) {
                return entrada.getValor();
            }
            actual = actual.getSiguiente();
        }
        return null; 
    }

    public boolean eliminar(String clave) {
        if (clave == null) return false;
        int indice = funcionHash(clave);
        ListaEnlazada lista = tabla[indice];

        NodoLista actual = lista.getCabeza();
        while (actual != null) {
            EntradaHash entrada = (EntradaHash) actual.getContenido();
            if (entrada.getClave().equalsIgnoreCase(clave)) {
                lista.eliminar(entrada);
                numElementos--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public int getNumElementos() {
        return numElementos;
    }
}