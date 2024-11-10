// Clase para el Splay Tree
public class SplayTree {
    private NodoSplay raiz;

    // Rotaciones del árbol

    // Zig
    private void zig(NodoSplay x) {
        NodoSplay p = x.padre;
        if (p != null && p.izquierdo == x) {
            NodoSplay B = x.derecho;
            x.derecho = p;
            p.izquierdo = B;
            if (B != null) B.padre = p;
            reemplazarPadre(p, x);
            x.padre = p.padre;
            p.padre = x;
        }
    }

    // Zag
    private void zag(NodoSplay x) {
        NodoSplay p = x.padre;
        if (p != null && p.derecho == x) {
            NodoSplay B = x.izquierdo;
            x.izquierdo = p;
            p.derecho = B;
            if (B != null) B.padre = p;
            reemplazarPadre(p, x);
            x.padre = p.padre;
            p.padre = x;
        }
    }

    // Zig-zig
    private void zigZig(NodoSplay x) {
        NodoSplay p = x.padre;
        NodoSplay g = p.padre;
        zig(p);
        zig(x);
    }

    // Zag-zag
    private void zagZag(NodoSplay x) {
        NodoSplay p = x.padre;
        NodoSplay g = p.padre;
        zag(p);
        zag(x);
    }

    // Zig-zag
    private void zigZag(NodoSplay x) {
        zig(x);
        zag(x);
    }

    // Zag-zig
    private void zagZig(NodoSplay x) {
        zag(x);
        zig(x);
    }

    // Reemplaza el NodoSplay p con el NodoSplay x en el árbol
    private void reemplazarPadre(NodoSplay p, NodoSplay x) {
        if (p.padre == null) {
            raiz = x;
        } else if (p == p.padre.izquierdo) {
            p.padre.izquierdo = x;
        } else {
            p.padre.derecho = x;
        }
        if (x != null) x.padre = p.padre;
    }

    // Operación Splay
    private void splay(NodoSplay x) {
        while (x.padre != null) {
            NodoSplay p = x.padre;
            NodoSplay g = p.padre;
            if (g == null) {
                // Caso raíz: Zig o Zag
                if (x == p.izquierdo) zig(x);
                else zag(x);
            } else if (x == p.izquierdo && p == g.izquierdo) {
                // Zig-zig
                zigZig(x);
            } else if (x == p.derecho && p == g.derecho) {
                // Zag-zag
                zagZag(x);
            } else if (x == p.izquierdo && p == g.derecho) {
                // Zag-zig
                zagZig(x);
            } else {
                // Zig-zag
                zigZag(x);
            }
        }
    }

    // Búsqueda en Splay Tree
    public NodoSplay buscar(int valor) {
        NodoSplay x = raiz;
        while (x != null) {
            if (valor < x.valor) {
                x = x.izquierdo;
            } else if (valor > x.valor) {
                x = x.derecho;
            } else {
                splay(x);
                return x;
            }
        }
        return null;
    }

    // Inserción en Splay Tree
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new NodoSplay(valor);
            return;
        }
        NodoSplay x = raiz;
        NodoSplay p = null;
        while (x != null) {
            p = x;
            if (valor < x.valor) {
                x = x.izquierdo;
            } else if (valor > x.valor) {
                x = x.derecho;
            } else {
                splay(x);
                return;
            }
        }
        NodoSplay nuevo = new NodoSplay(valor);
        nuevo.padre = p;
        if (valor < p.valor) {
            p.izquierdo = nuevo;
        } else {
            p.derecho = nuevo;
        }
        splay(nuevo);
    }

    // Método para imprimir el árbol en orden
    public void enOrden(NodoSplay NodoSplay) {
        if (NodoSplay != null) {
            enOrden(NodoSplay.izquierdo);
            System.out.print(NodoSplay.valor + " ");
            enOrden(NodoSplay.derecho);
        }
    }

    public void imprimir() {
        enOrden(raiz);
        System.out.println();
    }

    // Función mejorada para imprimir la estructura del árbol
    public void imprimirEstructura() {
        System.out.println(imprimirEstructuraRecursivo(raiz));
    }

    private String imprimirEstructuraRecursivo(NodoSplay NodoSplay) {
        if (NodoSplay == null) {
            return "()"; // NodoSplay vacío
        }
        return "(" + NodoSplay.valor + " " 
                + imprimirEstructuraRecursivo(NodoSplay.izquierdo) + " " 
                + imprimirEstructuraRecursivo(NodoSplay.derecho) + ")";
    }
}