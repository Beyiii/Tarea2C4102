import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Experiment {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] ns = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000};
        int mFactor = 100;
        for (int n : ns) {
            int m = mFactor * n;
            List<Integer> elements = generateUniqueRandomIntegers(n);

            System.out.println("Running experiments for N = " + n + ", M = " + m);
            runScenario1(elements, m);
            runScenario2(elements, m);
            runScenario3(elements, m);
            runScenario4(elements, m);
        }
    }

    // Ejemplo de implementación de la experimentación
    public static void runScenario1(List<Integer> elements, int m) {
        BinarySearchTree bst = new BinarySearchTree();
        SplayTree splayTree = new SplayTree();

        for (int value : elements) {
            bst.insert(value);
            splayTree.insert(value);
        }

        List<Integer> searches = generateSearchPattern(elements, m / elements.size());
        Collections.shuffle(searches);

        long startTime = System.currentTimeMillis();
        for (int value : searches) {
            bst.search(bst.root, value);
        }
        System.out.println("ABB search time for Scenario 1: " + (System.currentTimeMillis() - startTime) + " ms");

        startTime = System.currentTimeMillis();
        for (int value : searches) {
            splayTree.search(value);
        }
        System.out.println("Splay Tree search time for Scenario 1: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    private static List<Integer> generateUniqueRandomIntegers(int n) {
        List<Integer> elements = new ArrayList<>();
        while (elements.size() < n) {
            int value = random.nextInt(10 * n);
            if (!elements.contains(value)) {
                elements.add(value);
            }
        }
        return elements;
    }

    private static List<Integer> generateSearchPattern(List<Integer> elements, int repetitionFactor) {
        List<Integer> searches = new ArrayList<>();
        for (int value : elements) {
            for (int i = 0; i < repetitionFactor; i++) {
                searches.add(value);
            }
        }
        return searches;
    }

    private static double calculateConstantC(int n) {
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += 1.0 / Math.pow(i + 1, 2);
        }
        return 1.0 / sum;
    }

    private static List<Integer> generateBiasedSearchPattern(List<Integer> elements, int m, double c) {
        List<Integer> searches = new ArrayList<>();
        int n = elements.size();
        for (int i = 0; i < n; i++) {
            int repetitions = (int) Math.floor(m * c / Math.pow(i + 1, 2));
            for (int j = 0; j < repetitions; j++) {
                searches.add(elements.get(i));
            }
        }
        Collections.shuffle(searches);
        return searches;
    }

    public static void runScenario2(List<Integer> elements, int m) {
        BinarySearchTree bst = new BinarySearchTree();
        SplayTree splayTree = new SplayTree();

        for (int value : elements) {
            bst.insert(value);
            splayTree.insert(value);
        }

        double c = calculateConstantC(elements.size());
        List<Integer> searches = generateBiasedSearchPattern(elements, m, c);

        long startTime = System.currentTimeMillis();
        for (int value : searches) {
            bst.search(bst.root, value);
        }
        System.out.println("ABB search time for Scenario 2: " + (System.currentTimeMillis() - startTime) + " ms");

        startTime = System.currentTimeMillis();
        for (int value : searches) {
            splayTree.search(value);
        }
        System.out.println("Splay Tree search time for Scenario 2: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public static void runScenario3(List<Integer> elements, int m) {
        Collections.sort(elements);

        BinarySearchTree bst = new BinarySearchTree();
        SplayTree splayTree = new SplayTree();

        for (int value : elements) {
            bst.insert(value);
            splayTree.insert(value);
        }

        List<Integer> searches = generateSearchPattern(elements, m / elements.size());
        Collections.shuffle(searches);

        long startTime = System.currentTimeMillis();
        for (int value : searches) {
            bst.search(bst.root, value);
        }
        System.out.println("ABB search time for Scenario 3: " + (System.currentTimeMillis() - startTime) + " ms");

        startTime = System.currentTimeMillis();
        for (int value : searches) {
            splayTree.search(value);
        }
        System.out.println("Splay Tree search time for Scenario 3: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public static void runScenario4(List<Integer> elements, int m) {
        List<Integer> sortedElements = new ArrayList<>(elements);
        Collections.sort(sortedElements);

        BinarySearchTree bst = new BinarySearchTree();
        SplayTree splayTree = new SplayTree();

        for (int value : sortedElements) {
            bst.insert(value);
            splayTree.insert(value);
        }

        double c = calculateConstantC(elements.size());
        List<Integer> searches = generateBiasedSearchPattern(elements, m, c);

        long startTime = System.currentTimeMillis();
        for (int value : searches) {
            bst.search(bst.root, value);
        }
        System.out.println("ABB search time for Scenario 4: " + (System.currentTimeMillis() - startTime) + " ms");

        startTime = System.currentTimeMillis();
        for (int value : searches) {
            splayTree.search(value);
        }
        System.out.println("Splay Tree search time for Scenario 4: " + (System.currentTimeMillis() - startTime) + " ms");
    }

}
