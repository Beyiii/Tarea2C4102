// Clase para el Árbol Binario de Búsqueda Clásico
class ABB {
    private Nodo raiz;

    // Constructor
    public ABB() {
        this.raiz = null;
    }

    // Método de búsqueda
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return false; // Elemento no encontrado
        }
        if (valor == nodo.valor) {
            return true; // Elemento encontrado
        }
        // Buscar en el subárbol izquierdo o derecho
        if (valor < nodo.valor) {
            return buscarRecursivo(nodo.izquierdo, valor);
        } else {
            return buscarRecursivo(nodo.derecho, valor);
        }
    }

    // Método de inserción
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor); // Crear un nuevo nodo si el lugar está vacío
        }
        if (valor < nodo.valor) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarRecursivo(nodo.derecho, valor);
        }
        return nodo; // Retornar el nodo sin cambios si el valor ya está presente
    }

    // Método para imprimir el árbol (opcional, para depuración)
    public void imprimirEnOrden() {
        imprimirEnOrdenRecursivo(raiz);
        System.out.println();
    }

    private void imprimirEnOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            imprimirEnOrdenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            imprimirEnOrdenRecursivo(nodo.derecho);
        }
    }

    // Función mejorada para imprimir la estructura del árbol
    public void imprimirEstructura() {
        System.out.println(imprimirEstructuraRecursivo(raiz));
    }

    private String imprimirEstructuraRecursivo(Nodo nodo) {
        if (nodo == null) {
            return "()"; // Nodo vacío
        }
        return "(" + nodo.valor + " " 
                + imprimirEstructuraRecursivo(nodo.izquierdo) + " " 
                + imprimirEstructuraRecursivo(nodo.derecho) + ")";
    }
}
