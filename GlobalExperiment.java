

public class GlobalExperiment {
    private static final int[] N_VALUES = {100000}; //, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000};
    
    Experiment experiment = new Experiment();

    public void runExperiments() {
        for (int N : N_VALUES) {
            int M = 100 * N; // Número de búsquedas
            experiment.ejecutarExperimento(N, M);
        }
    }

}
