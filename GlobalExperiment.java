

public class GlobalExperiment {
    private static final int[] N_VALUES = {800000, 900000, 1000000};
    
    Experiment experiment = new Experiment();

    public void runExperiments() {
        for (int N : N_VALUES) {
            int M = 100 * N; // Número de búsquedas
            Experiment.ejecutarExperimento(N, M);
        }
    }

    public static void main(String[] args) {
        GlobalExperiment globalExperiment = new GlobalExperiment();
        globalExperiment.runExperiments();
    }

}
