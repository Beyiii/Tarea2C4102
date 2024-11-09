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
        if (key < root.key && root.left != null && key < root.left.key) {
            root.left.left = splay(root.left.left, key);
            root = rotateRight(root);
        }

        // Zig-Zag (left-right case)
        else if (key < root.key && root.left != null && key > root.left.key) {
            root.left.right = splay(root.left.right, key);
            if (root.left.right != null) {
                root.left = rotateLeft(root.left);
            }
        }

        // Zag-Zag (right-right case)
        else if (key > root.key && root.right != null && key > root.right.key) {
            root.right.right = splay(root.right.right, key);
            root = rotateLeft(root);
        }

        // Zag-Zig (right-left case)
        else if (key > root.key && root.right != null && key < root.right.key) {
            root.right.left = splay(root.right.left, key);
            if (root.right.left != null) {
                root.right = rotateRight(root.right);
            }
        }

        return (key < root.key) ? rotateRight(root) : rotateLeft(root);
    }

    // Rotaciones simples
    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        return x;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        return x;
    }
}
