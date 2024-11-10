import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Experiment {
    private static final int[] N_VALUES = {100000}; //, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000};
    private static final int M_FACTOR = 100;

    // Método principal para realizar el experimento y registrar en CSV
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("resultados_experimento.csv"))) {
            writer.println("n_escenario,tipo_arbol,t_prom"); // Encabezado del archivo CSV

            for (int N : N_VALUES) {
                int M = M_FACTOR * N;
                System.out.println("Experimento con N = " + N + " y M = " + M);

                // Genera valores aleatorios únicos para insertar
                int[] elementos = IntStream.range(0, N).toArray();
                Collections.shuffle(Arrays.asList(elementos));

                // Instancia de ABB y Splay Tree
                ABB abb = new ABB();
                SplayTree splayTree = new SplayTree();

                // Escenario 1: Inserción aleatoria y búsqueda aleatoria
                for (int valor : elementos) {
                    abb.insertar(valor);
                    splayTree.insertar(valor);
                }
                int[] busquedasEscenario1 = generarArregloBusquedaAleatoria(elementos, M);
                registrarTiempoPromedio(writer, 1, "ABB", realizarBusquedaABB(abb, busquedasEscenario1), M);
                registrarTiempoPromedio(writer, 1, "Splay Tree", realizarBusquedaSplayTree(splayTree, busquedasEscenario1), M);

                // Escenario 2: Inserción aleatoria y búsqueda sesgada
                double C = calcularConstanteC(N);
                int[] busquedasEscenario2 = generarArregloBusquedaSesgado(elementos, M, C);
                registrarTiempoPromedio(writer, 2, "ABB", realizarBusquedaABB(abb, busquedasEscenario2), M);
                registrarTiempoPromedio(writer, 2, "Splay Tree", realizarBusquedaSplayTree(splayTree, busquedasEscenario2), M);

                // Escenario 3: Inserción ordenada y búsqueda aleatoria
                Arrays.sort(elementos);
                ABB abbOrdenado = new ABB();
                SplayTree splayTreeOrdenado = new SplayTree();
                for (int valor : elementos) {
                    abbOrdenado.insertar(valor);
                    splayTreeOrdenado.insertar(valor);
                }
                registrarTiempoPromedio(writer, 3, "ABB", realizarBusquedaABB(abbOrdenado, busquedasEscenario1), M);
                registrarTiempoPromedio(writer, 3, "Splay Tree", realizarBusquedaSplayTree(splayTreeOrdenado, busquedasEscenario1), M);

                // Escenario 4: Inserción ordenada con búsqueda sesgada (elementos originales en desorden)
                int[] elementosDesordenados = elementos.clone();
                Collections.shuffle(Arrays.asList(elementosDesordenados));
                int[] busquedasEscenario4 = generarArregloBusquedaSesgado(elementosDesordenados, M, C);
                registrarTiempoPromedio(writer, 4, "ABB", realizarBusquedaABB(abbOrdenado, busquedasEscenario4), M);
                registrarTiempoPromedio(writer, 4, "Splay Tree", realizarBusquedaSplayTree(splayTreeOrdenado, busquedasEscenario4), M);

                System.out.println("Resultados guardados para N = " + N);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    // Método para registrar el tiempo promedio en el archivo CSV
    private static void registrarTiempoPromedio(PrintWriter writer, int nEscenario, String tipoArbol, long tiempoTotal, int M) {
        double tProm = tiempoTotal / (double) M;
        writer.println(nEscenario + "," + "\"" + tipoArbol + "\"," + tProm);
    }

    // Genera el arreglo de búsquedas aleatorias para el Escenario 1
    private static int[] generarArregloBusquedaAleatoria(int[] elementos, int M) {
        int N = elementos.length;
        int[] busquedas = new int[M];
        for (int i = 0; i < N; i++) {
            int repeticiones = M / N;
            Arrays.fill(busquedas, i * repeticiones, (i + 1) * repeticiones, elementos[i]);
        }
        Collections.shuffle(Arrays.asList(busquedas));
        return busquedas;
    }

    // Calcula la constante C para la función de probabilidad
    private static double calcularConstanteC(int N) {
        double suma = 0;
        for (int i = 0; i < N; i++) {
            suma += 1.0 / Math.pow(i + 1, 2);
        }
        return 1.0 / suma;
    }

    // Genera el arreglo de búsquedas sesgado según la función de probabilidad
    private static int[] generarArregloBusquedaSesgado(int[] elementos, int M, double C) {
        int N = elementos.length;
        List<Integer> listaBusqueda = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int copias = (int) Math.floor(M * (C / Math.pow(i + 1, 2)));
            listaBusqueda.addAll(Collections.nCopies(copias, elementos[i]));
        }
        Collections.shuffle(listaBusqueda);
        return listaBusqueda.stream().mapToInt(Integer::intValue).toArray();
    }

    // Realiza el experimento en el ABB y calcula el tiempo total de búsqueda
    private static long realizarBusquedaABB(ABB abb, int[] elementos) {
        long inicio = System.nanoTime();
        for (int valor : elementos) {
            abb.buscar(valor);
        }
        return System.nanoTime() - inicio;
    }

    // Realiza el experimento en el Splay Tree y calcula el tiempo total de búsqueda
    private static long realizarBusquedaSplayTree(SplayTree splayTree, int[] elementos) {
        long inicio = System.nanoTime();
        for (int valor : elementos) {
            splayTree.buscar(valor);
        }
        return System.nanoTime() - inicio;
    }
}
