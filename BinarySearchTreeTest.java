public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree(); // Crear el árbol

        // Insertar algunos valores
        bst.insert(10);
        bst.insert(20);
        bst.insert(5);

        // Imprimir resultados de la búsqueda
        System.out.println("Buscar 10: " + (bst.search(bst.root, 10) != null ? "Encontrado" : "No encontrado"));
        System.out.println("Buscar 5: " + (bst.search(bst.root, 5) != null ? "Encontrado" : "No encontrado"));
        System.out.println("Buscar 20: " + (bst.search(bst.root, 20) != null ? "Encontrado" : "No encontrado"));
        System.out.println("Buscar 15: " + (bst.search(bst.root, 15) != null ? "Encontrado" : "No encontrado"));
    }
}
