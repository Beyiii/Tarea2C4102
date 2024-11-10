// Clase del nodo del árbol binario de búsqueda
class NodoSplay {
    int valor;
    NodoSplay izquierdo, derecho, padre;

    public NodoSplay(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}