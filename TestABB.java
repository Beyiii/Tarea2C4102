public class TestABB {
    public static void main(String[] args) {
        ABB arbol = new ABB();

        System.out.println("Prueba de inserciones en ABB:");

        // Caso 1: Inserción en un árbol vacío
        System.out.println("Insertando valor 10 en árbol vacío:");
        arbol.insertar(10);
        arbol.imprimirEnOrden(); // Debe mostrar: 10

        // Caso 2: Inserciones en el subárbol izquierdo
        System.out.println("\nInsertando valores 5 y 3 en el subárbol izquierdo:");
        arbol.insertar(5);
        arbol.insertar(3);
        arbol.imprimirEnOrden(); // Debe mostrar: 3 5 10

        // Caso 3: Inserciones en el subárbol derecho
        System.out.println("\nInsertando valores 15 y 20 en el subárbol derecho:");
        arbol.insertar(15);
        arbol.insertar(20);
        arbol.imprimirEnOrden(); // Debe mostrar: 3 5 10 15 20

        // Caso 4: Inserción de un valor duplicado
        System.out.println("\nIntentando insertar un valor duplicado (10):");
        arbol.insertar(10);
        arbol.imprimirEnOrden(); // Debe mostrar: 3 5 10 15 20 (sin duplicados)

        // Caso 5: Inserciones adicionales para crear un árbol con varios niveles
        System.out.println("\nInsertando valores 8, 12, y 18 para verificar estructura:");
        arbol.insertar(8);
        arbol.insertar(12);
        arbol.insertar(18);
        arbol.imprimirEnOrden(); // Debe mostrar: 3 5 8 10 12 15 18 20

        // Imprimir la estructura del árbol para verificar visualmente
        System.out.println("\nEstructura del árbol (en preorden):");
        arbol.imprimirEstructura();
    }
}
