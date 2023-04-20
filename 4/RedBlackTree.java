public class RedBlackTree<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        T data;
        Node left, right;
        boolean color;

        Node(T data, boolean color) {
            this.data = data;
            this.color = color;
        }
    }

    public void add(T data) {
        root = add(root, data);
        root.color = BLACK; //
    }

    private Node add(Node node, T data) {
        if (node == null) {
            return new Node(data, RED);
        }

        int cmp = data.compareTo(node.data);

        if (cmp < 0) {
            node.left = add(node.left, data);
        } else if (cmp > 0) {
            node.right = add(node.right, data);
        } else {
            // /
            return node;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
}

