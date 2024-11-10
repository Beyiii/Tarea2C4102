// Clase principal para probar el ABB
public class TestABB {
    public static void main(String[] args) {
        ABB abb = new ABB();
        // Ejemplo de inserciones
        abb.insertar(10);
        abb.insertar(5);
        abb.insertar(15);
        abb.insertar(2);
        abb.insertar(8);
        abb.insertar(12);
        abb.insertar(20);
        abb.insertar(1);
        abb.insertar(3);
        abb.insertar(7);
        abb.insertar(9);
        abb.insertar(11);
        abb.insertar(13);
        

        // Ejemplo de búsqueda
        System.out.println("Buscar 10: " + abb.buscar(10)); // true
        System.out.println("Buscar 7: " + abb.buscar(7));   // false

        // Imprimir el árbol en orden
        abb.imprimirEstructura();
    }
}