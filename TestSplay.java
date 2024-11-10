// Clase principal para probar el Splay Tree
public class TestSplay {
    public static void main(String[] args) {
        SplayTree splayTree = new SplayTree();
        // Ejemplo de inserciones
        splayTree.insertar(10);
        System.out.println("Buscar 10: " + splayTree.buscar(10)); // true
        splayTree.imprimirEstructura();
        splayTree.insertar(5);
        splayTree.imprimirEstructura();
        splayTree.insertar(15);
        splayTree.imprimirEstructura();
        splayTree.insertar(2);
        splayTree.imprimirEstructura();
        splayTree.insertar(8);
        splayTree.imprimirEstructura();

        // Ejemplo de búsqueda
        System.out.println("Buscar 10: " + splayTree.buscar(10)); // true
        splayTree.imprimirEstructura();
        System.out.println("Buscar 7: " + splayTree.buscar(7));   // false
        splayTree.imprimirEstructura();
        System.out.println("Buscar 15: " + splayTree.buscar(15)); // true
        splayTree.imprimirEstructura();
        System.out.println("Buscar 10: " + splayTree.buscar(10)); // true
        splayTree.imprimirEstructura();
        System.out.println("Buscar 5: " + splayTree.buscar(5)); // true
        splayTree.imprimirEstructura();
        System.out.println("Buscar 8: " + splayTree.buscar(8)); // true
        splayTree.imprimirEstructura();
    }
}
