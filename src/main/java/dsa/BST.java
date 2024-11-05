package dsa;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value val) {
        put(root, key, val);
    }

    private Node put(Node root, Key key, Value val) {
        if (root == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = put(root.left, key, val);
        } else if (cmp > 0) {
            root.right = put(root.right, key, val);
        } else {
            root.val = val;
        }
        return root;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return get(root.left, key);
        } else if (cmp > 0) {
            return get(root.right, key);
        } else return root.val;
    }

    private Node min(Node root) {
        if(root == null) return null;
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }

    public void deleteMin() {
        deleteMin(root);
    }

    private Node deleteMin(Node root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        return root;
    }

    public void delete(Key key) {
        delete(root, key);
    }

    private Node delete(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = delete(root.left, key);
        } else if (cmp > 0) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node rootBackup = root;
            root = min(root.right);
            root.right = deleteMin(root.right);
            root.left = rootBackup.left;
        }
        return root;
    }

//    public Iterable<Key> iterator() { /* see next slides */ }
}