public class Main {
    public static void main(String[] args) {
        int[] N_VALUES = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000};
        Experiment globalExperiment = new Experiment();

        for (int N : N_VALUES) {
            int M = 100 * N; // Número de búsquedas
            globalExperiment.ejecutarExperimento(N, M);
        }
    }
}
