// Clase principal para probar el Splay Tree
public class MainSplay {
    public static void main(String[] args) {
        SplayTree splayTree = new SplayTree();
        // Ejemplo de inserciones
        splayTree.insertar(10);
        splayTree.insertar(5);
        splayTree.insertar(15);
        splayTree.insertar(2);
        splayTree.insertar(8);

        // Ejemplo de búsqueda
        System.out.println("Buscar 10: " + splayTree.buscar(10)); // true
        System.out.println("Buscar 7: " + splayTree.buscar(7));   // false

        // Imprimir el árbol en orden
        splayTree.imprimirEnOrden();
    }
}