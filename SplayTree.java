public class SplayTree {
    Node root;

    public SplayTree() {
        root = null;
    }

    public Node search(int key) {
        root = splay(root, key);
        return root != null && root.key == key ? root : null;
    }

    public void insert(int key) {
        root = splay(root, key);
        if (root == null || root.key != key) {
            Node newNode = new Node(key);
            if (root == null) {
                root = newNode;
            } else if (key < root.key) {
                newNode.right = root;
                newNode.left = root.left;
                root.left = null;
                root = newNode;
            } else {
                newNode.left = root;
                newNode.right = root.right;
                root.right = null;
                root = newNode;
            }
        }
    }

    private Node splay(Node root, int key) {
        if (root == null || root.key == key) return root;

        // Zig-Zig (left-left case)
        if (key < root.key && root.left != null) {
            if (key < root.left.key && root.left.left != null) { // Left-left
                root.left.left = splay(root.left.left, key);
                root = rotateRight(root);
            } else if (key > root.left.key && root.left.right != null) { // Left-right
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null) {
                    root.left = rotateLeft(root.left);
                }
            }
            return (root.left == null) ? root : rotateRight(root);
        }

        // Zag-Zag (right-right case)
        if (key > root.key && root.right != null) {
            if (key > root.right.key && root.right.right != null) { // Right-right
                root.right.right = splay(root.right.right, key);
                root = rotateLeft(root);
            } else if (key < root.right.key && root.right.left != null) { // Right-left
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null) {
                    root.right = rotateRight(root.right);
                }
            }
            return (root.right == null) ? root : rotateLeft(root);
        }

        return root;
    }

    // Rotaciones simples
    private Node rotateRight(Node x) {
        if (x == null || x.left == null) {
            return x; // Si x o x.left es null, no se realiza la rotación
        }

        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
        return y;
    }


    private Node rotateLeft(Node x) {
        if (x == null || x.right == null) {
            return x; // Si x o x.right es null, no se realiza la rotación
        }

        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
        return y;
    }


}
