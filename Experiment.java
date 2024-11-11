import java.util.*;
import java.io.*;

public class Experiment {

    // Función f(i) = C / (i + 1)^2
    private static double funcionProbabilidad(int i, double C) {
        return C / Math.pow(i + 1, 2);
    }

    // Método para calcular la constante C para la función de probabilidad
    private static double calcularC(int N) {
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += 1 / Math.pow(i + 1, 2);
        }
        return 1 / sum; // Invertimos la suma para que la suma de f(i) sea 1
    }

    // Método para generar un arreglo con N elementos aleatorios
    private static int[] generarArregloAleatorio(int N) {
        Random rand = new Random();
        int[] arreglo = new int[N];
        for (int i = 0; i < N; i++) {
            arreglo[i] = rand.nextInt(1000000); // números aleatorios en un rango
        }
        return arreglo;
    }

    // Método para crear el arreglo de búsqueda para el escenario 1
    private static int[] crearArregloBusqueda(int[] A, int M) {
        int N = A.length;
        int[] B = new int[M];
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M / N; j++) {
                B[count++] = A[i];
            }
        }
        // Mezclar el arreglo B aleatoriamente
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            lista.add(B[i]);
        }
        Collections.shuffle(lista);
        for (int i = 0; i < M; i++) {
            B[i] = lista.get(i);
        }
        return B;
    }

    // Método para crear el arreglo de búsqueda para el escenario 2
    private static int[] crearArregloBusquedaConProbabilidades(int[] A, int M, double C) {
        int N = A.length;
        List<Integer> B = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int copias = (int) Math.floor(M * funcionProbabilidad(i, C));
            for (int j = 0; j < copias; j++) {
                B.add(A[i]);
            }
        }
        // Convertir lista a arreglo
        int[] arregloB = new int[B.size()];
        for (int i = 0; i < B.size(); i++) {
            arregloB[i] = B.get(i);
        }
        // Mezclar el arreglo B aleatoriamente
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            lista.add(B.get(i));
        }
        Collections.shuffle(lista);
        for (int i = 0; i < B.size(); i++) {
            arregloB[i] = lista.get(i);
        }
        return arregloB;
    }

    // Método para realizar la experimentación y registrar costos promedio de cada árbol
    public static void ejecutarExperimento(int N, int M) {
        // Crear los árboles
        ABB abb = new ABB();
        SplayTree splayTree = new SplayTree();

        // Generar arreglo A
        int[] A = generarArregloAleatorio(N);

        // Escenario 1: Inserción aleatoria y búsqueda con mezcla
        for (int i = 0; i < N; i++) {
            abb.insertar(A[i]);
            splayTree.insertar(A[i]);
        }
        int[] B = crearArregloBusqueda(A, M);

        // Tiempo de búsqueda en ABB
        long tiempoInicio = System.nanoTime();
        for (int i = 0; i < M; i++) {
            abb.buscar(B[i]);
        }
        long tiempoFin = System.nanoTime();
        double costoPromedioABB = (double) (tiempoFin - tiempoInicio) / M;
        guardarResultado("Escenario 1", "ABB", costoPromedioABB, N, M);

        // Tiempo de búsqueda en SplayTree
        tiempoInicio = System.nanoTime();
        for (int i = 0; i < M; i++) {
            splayTree.buscar(B[i]);
        }
        tiempoFin = System.nanoTime();
        double costoPromedioSplayTree = (double) (tiempoFin - tiempoInicio) / M;
        guardarResultado("Escenario 1", "SplayTree", costoPromedioSplayTree, N, M);

        // Escenario 2: Inserción aleatoria y búsqueda con probabilidades sesgadas
        double C = calcularC(N);
        int[] BProb = crearArregloBusquedaConProbabilidades(A, M, C);

        // Tiempo de búsqueda en ABB
        tiempoInicio = System.nanoTime();
        for (int i = 0; i < BProb.length; i++) {
            abb.buscar(BProb[i]);
        }
        tiempoFin = System.nanoTime();
        costoPromedioABB = (double) (tiempoFin - tiempoInicio) / M;
        guardarResultado("Escenario 2", "ABB", costoPromedioABB, N, M);

        // Tiempo de búsqueda en SplayTree
        tiempoInicio = System.nanoTime();
        for (int i = 0; i < BProb.length; i++) {
            splayTree.buscar(BProb[i]);
        }
        tiempoFin = System.nanoTime();
        costoPromedioSplayTree = (double) (tiempoFin - tiempoInicio) / M;
        guardarResultado("Escenario 2", "SplayTree", costoPromedioSplayTree, N, M);

        // Escenario 3: Inserción ordenada y búsqueda con mezcla
        Arrays.sort(A);
        for (int i = 0; i < N; i++) {
            abb.insertar(A[i]);
            splayTree.insertar(A[i]);
        }
        B = crearArregloBusqueda(A, M);

        // Tiempo de búsqueda en ABB
        tiempoInicio = System.nanoTime();
        for (int i = 0; i < M; i++) {
            abb.buscar(B[i]);
        }
        tiempoFin = System.nanoTime();
        costoPromedioABB = (double) (tiempoFin - tiempoInicio) / M;
        guardarResultado("Escenario 3", "ABB", costoPromedioABB, N, M);

        // Tiempo de búsqueda en SplayTree
        tiempoInicio = System.nanoTime();
        for (int i = 0; i < M; i++) {
            splayTree.buscar(B[i]);
        }
        tiempoFin = System.nanoTime();
        costoPromedioSplayTree = (double) (tiempoFin - tiempoInicio) / M;
        guardarResultado("Escenario 3", "SplayTree", costoPromedioSplayTree, N, M);

        // Escenario 4: Inserción en orden y búsqueda con probabilidades sesgadas
        int[] CArray = Arrays.copyOf(A, A.length);
        Arrays.sort(CArray);
        for (int i = 0; i < N; i++) {
            abb.insertar(CArray[i]);
            splayTree.insertar(CArray[i]);
        }
        BProb = crearArregloBusquedaConProbabilidades(A, M, C);

        // Tiempo de búsqueda en ABB
        tiempoInicio = System.nanoTime();
        for (int i = 0; i < BProb.length; i++) {
            abb.buscar(BProb[i]);
        }
        tiempoFin = System.nanoTime();
        costoPromedioABB = (double) (tiempoFin - tiempoInicio) / M;
        guardarResultado("Escenario 4", "ABB", costoPromedioABB, N, M);

        // Tiempo de búsqueda en SplayTree
        tiempoInicio = System.nanoTime();
        for (int i = 0; i < BProb.length; i++) {
            splayTree.buscar(BProb[i]);
        }
        tiempoFin = System.nanoTime();
        costoPromedioSplayTree = (double) (tiempoFin - tiempoInicio) / M;
        guardarResultado("Escenario 4", "SplayTree", costoPromedioSplayTree, N, M);
    }

    // Método para guardar los resultados en un archivo CSV
    private static void guardarResultado(String escenario, String tipoArbol, double costoPromedio, int N, int M) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.csv", true))) {
            writer.write(escenario + "," + tipoArbol + "," + costoPromedio + "," + N + "," + M + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int N = 500000; // Número de elementos
        int M = 100 * N; // Número de búsquedas
        ejecutarExperimento(N, M);
    }
}
