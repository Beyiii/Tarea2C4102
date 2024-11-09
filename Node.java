class Node {
    int key;
    Node left, right, parent;  // Añadimos el campo parent

    public Node(int item) {
        key = item;
        left = right = parent = null;
    }
}
