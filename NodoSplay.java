// Clase para representar un nodo del Splay Tree
class NodoSplay {
    int valor;
    NodoSplay izquierdo, derecho;

    public NodoSplay(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}