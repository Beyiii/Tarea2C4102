// Clase para el Splay Tree
class SplayTree {
    private NodoSplay raiz;

    // Constructor
    public SplayTree() {
        this.raiz = null;
    }

    // Método de rotación "Zig"
    private NodoSplay zig(NodoSplay x) {
        NodoSplay y = x.izquierdo;
        x.izquierdo = y.derecho;
        y.derecho = x;
        return y;
    }

    // Método de rotación "Zag"
    private NodoSplay zag(NodoSplay x) {
        NodoSplay y = x.derecho;
        x.derecho = y.izquierdo;
        y.izquierdo = x;
        return y;
    }

    // Método de rotación "Zig-Zig"
    private NodoSplay zigZig(NodoSplay x) {
        x = zig(x);
        return zig(x);
    }

    // Método de rotación "Zag-Zag"
    private NodoSplay zagZag(NodoSplay x) {
        x = zag(x);
        return zag(x);
    }

    // Método de rotación "Zig-Zag"
    private NodoSplay zigZag(NodoSplay x) {
        x.izquierdo = zag(x.izquierdo);
        return zig(x);
    }

    // Método de rotación "Zag-Zig"
    private NodoSplay zagZig(NodoSplay x) {
        x.derecho = zig(x.derecho);
        return zag(x);
    }

    // Método splay para llevar el nodo con valor "valor" a la raíz
    private NodoSplay splay(NodoSplay raiz, int valor) {
        if (raiz == null || raiz.valor == valor) {
            return raiz;
        }

        // Si el valor está en el subárbol izquierdo
        if (valor < raiz.valor) {
            if (raiz.izquierdo == null) return raiz; // El valor no está en el árbol

            if (valor < raiz.izquierdo.valor) { // Zig-Zig (izquierda-izquierda)
                raiz.izquierdo.izquierdo = splay(raiz.izquierdo.izquierdo, valor);
                raiz = zig(raiz);
            } else if (valor > raiz.izquierdo.valor) { // Zig-Zag (izquierda-derecha)
                raiz.izquierdo.derecho = splay(raiz.izquierdo.derecho, valor);
                if (raiz.izquierdo.derecho != null) {
                    raiz.izquierdo = zag(raiz.izquierdo);
                }
            }
            return (raiz.izquierdo == null) ? raiz : zig(raiz);
        } else { // El valor está en el subárbol derecho
            if (raiz.derecho == null) return raiz; // El valor no está en el árbol

            if (valor > raiz.derecho.valor) { // Zag-Zag (derecha-derecha)
                raiz.derecho.derecho = splay(raiz.derecho.derecho, valor);
                raiz = zag(raiz);
            } else if (valor < raiz.derecho.valor) { // Zag-Zig (derecha-izquierda)
                raiz.derecho.izquierdo = splay(raiz.derecho.izquierdo, valor);
                if (raiz.derecho.izquierdo != null) {
                    raiz.derecho = zig(raiz.derecho);
                }
            }
            return (raiz.derecho == null) ? raiz : zag(raiz);
        }
    }

    // Método de búsqueda con splay
    public boolean buscar(int valor) {
        raiz = splay(raiz, valor);
        return (raiz != null && raiz.valor == valor);
    }

    // Método de inserción con splay
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new NodoSplay(valor);
            return;
        }
        raiz = splay(raiz, valor);

        if (raiz.valor == valor) {
            return; // El valor ya está en el árbol
        }

        NodoSplay nuevoNodo = new NodoSplay(valor);
        if (valor < raiz.valor) {
            nuevoNodo.derecho = raiz;
            nuevoNodo.izquierdo = raiz.izquierdo;
            raiz.izquierdo = null;
        } else {
            nuevoNodo.izquierdo = raiz;
            nuevoNodo.derecho = raiz.derecho;
            raiz.derecho = null;
        }
        raiz = nuevoNodo;
    }

    // Método para imprimir el árbol (opcional, para depuración)
    public void imprimirEnOrden() {
        imprimirEnOrdenRecursivo(raiz);
        System.out.println();
    }

    private void imprimirEnOrdenRecursivo(NodoSplay nodo) {
        if (nodo != null) {
            imprimirEnOrdenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            imprimirEnOrdenRecursivo(nodo.derecho);
        }
    }
}