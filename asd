// Clase para el Splay Tree
class SplayTree {
    private NodoSplay raiz;

    // Constructor
    public SplayTree() {
        this.raiz = null;
    }

    // Rotación Zig (cuando x es hijo izquierdo de la raíz)
    private NodoSplay zig(NodoSplay x, NodoSplay p) {
        p.izquierdo = x.derecho;
        x.derecho = p;
        return x; // x se convierte en la nueva raíz del subárbol
    }

    // Rotación Zag (cuando x es hijo derecho de la raíz)
    private NodoSplay zag(NodoSplay x, NodoSplay p) {
        p.derecho = x.izquierdo;
        x.izquierdo = p;
        return x; // x se convierte en la nueva raíz del subárbol
    }

    // Rotación Zig-Zig
    private NodoSplay zigZig(NodoSplay x, NodoSplay p, NodoSplay g) {
        // Realizar dos rotaciones Zig consecutivas
        p = zig(x, p);
        return zig(x, g);
    }

    // Rotación Zag-Zag
    private NodoSplay zagZag(NodoSplay x, NodoSplay p, NodoSplay g) {
        // Realizar dos rotaciones Zag consecutivas
        p = zag(x, p);
        return zag(x, g);
    }

    // Rotación Zig-Zag
    private NodoSplay zigZag(NodoSplay x, NodoSplay p, NodoSplay g) {
        // Realizar una rotación Zig seguida de una rotación Zag
        g.izquierdo = zag(x, p);
        return zig(x, g);
    }

    // Rotación Zag-Zig
    private NodoSplay zagZig(NodoSplay x, NodoSplay p, NodoSplay g) {
        // Realizar una rotación Zag seguida de una rotación Zig
        g.derecho = zig(x, p);
        return zag(x, g);
    }

    // Método Splay
    private NodoSplay splay(NodoSplay raiz, int valor) {
        if (raiz == null || raiz.valor == valor) {
            return raiz;
        }

        if (valor < raiz.valor) {
            // x está en el subárbol izquierdo
            if (raiz.izquierdo == null) return raiz; // No se encuentra el valor

            if (valor < raiz.izquierdo.valor) {
                // Zig-Zig (x es hijo izquierdo del hijo izquierdo de la raíz)
                raiz.izquierdo.izquierdo = splay(raiz.izquierdo.izquierdo, valor);
                raiz = zig(raiz.izquierdo, raiz); // Primera rotación
            } else if (valor > raiz.izquierdo.valor) {
                // Zig-Zag (x es hijo derecho del hijo izquierdo de la raíz)
                raiz.izquierdo.derecho = splay(raiz.izquierdo.derecho, valor);
                if (raiz.izquierdo.derecho != null) {
                    raiz.izquierdo = zag(raiz.izquierdo.derecho, raiz.izquierdo);
                }
            }
            return (raiz.izquierdo == null) ? raiz : zig(raiz.izquierdo, raiz);
        } else {
            // x está en el subárbol derecho
            if (raiz.derecho == null) return raiz; // No se encuentra el valor

            if (valor < raiz.derecho.valor) {
                // Zag-Zig (x es hijo izquierdo del hijo derecho de la raíz)
                raiz.derecho.izquierdo = splay(raiz.derecho.izquierdo, valor);
                if (raiz.derecho.izquierdo != null) {
                    raiz.derecho = zig(raiz.derecho.izquierdo, raiz.derecho);
                }
            } else if (valor > raiz.derecho.valor) {
                // Zag-Zag (x es hijo derecho del hijo derecho de la raíz)
                raiz.derecho.derecho = splay(raiz.derecho.derecho, valor);
                raiz = zag(raiz.derecho, raiz); // Primera rotación
            }
            return (raiz.derecho == null) ? raiz : zag(raiz.derecho, raiz);
        }
    }


    // Método de búsqueda con splay
    public boolean buscar(int valor) {
        NodoSplay actual = raiz;
        while (actual != null) {
            if (valor < actual.valor) {
                actual = actual.izquierdo;
            } else if (valor > actual.valor) {
                actual = actual.derecho;
            } else {
                raiz = splay(raiz, valor);
                return true;
            }
        }
        return false; // No se encontró el valor
    }
    

// Modificar el método de insertar en el Splay Tree
public void insertar(int valor) {
    if (raiz == null) {
        raiz = new NodoSplay(valor); // Árbol vacío: creamos la raíz
        return;
    }

    // Realizar la inserción de manera similar al ABB
    NodoSplay actual = raiz;
    NodoSplay padre = null;
    while (actual != null) {
        padre = actual;
        if (valor < actual.valor) {
            actual = actual.izquierdo;
        } else if (valor > actual.valor) {
            actual = actual.derecho;
        } else {
            // El valor ya existe en el árbol: hacer splay y retornar
            raiz = splay(raiz, valor);
            return;
        }
    }

    // Crear el nuevo nodo
    NodoSplay nuevoNodo = new NodoSplay(valor);
    if (valor < padre.valor) {
        padre.izquierdo = nuevoNodo;
    } else {
        padre.derecho = nuevoNodo;
    }

    // Hacer splay al nuevo nodo
    raiz = splay(raiz, valor);
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

    // Función mejorada para imprimir la estructura del árbol
    public void imprimirEstructura() {
        System.out.println(imprimirEstructuraRecursivo(raiz));
    }

    private String imprimirEstructuraRecursivo(NodoSplay nodo) {
        if (nodo == null) {
            return "()"; // Nodo vacío
        }
        return "(" + nodo.valor + " " 
                + imprimirEstructuraRecursivo(nodo.izquierdo) + " " 
                + imprimirEstructuraRecursivo(nodo.derecho) + ")";
    }
}
