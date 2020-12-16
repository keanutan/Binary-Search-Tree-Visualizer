// Written by: Keanu, Natchev

public class BinaryTree {

    Node root;

    public BinaryTree() {
    }

    /**
     * Creates tree with root of value value.
     * 
     * @param value
     */
    public BinaryTree(int value) {
        root = new Node(value);
    }

    /**
     * Adds a node of value value to the tree.
     * 
     * @param value
     */
    public void add(int value) {
        root = addNode(root, value);
    }

    /**
     * Helper method to add that adds node of value value to the tree.
     * 
     * @param current
     * @param value
     * @return
     */
    public Node addNode(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.integerValue) {
            current.left = addNode(current.left, value);
        } else if (value > current.integerValue) {
            current.right = addNode(current.right, value);
        } else {
            return current;
        }

        return current;
    }

    /**
     * Removes a node of value value from the tree.
     * @param value
     */
    public void remove(int value) {
        root = removeNode(root, value);
    }

    /**
     * Helper method to remove that removes a node of value value from the tree.
     * @param node
     * @param key
     * @return
     */
    public Node removeNode(Node node, int key) {
        // If tree is empty.
        if (node == null)
            return node;

        // Find the node with the key.
        if (key < node.integerValue)
            node.left = removeNode(node.left, key);
        else if (key > node.integerValue)
            node.right = removeNode(node.right, key);

        // When the node with the key is finally found.
        else {
            // Node with one or no children.
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            // Node with two children.
            // Get the inorder successor by finding the smallest node in the right subtree.
            node.integerValue = minimumValue(node.right);

            // Delete the inorder successor.
            node.right = removeNode(node.right, node.integerValue);
        }
        return node;
    }

    /**
     * Finds the smallest node.
     * 
     * @param node
     * @return
     */
    int minimumValue(Node node) {
        int minv = node.integerValue;
        while (node.left != null) {
            minv = node.left.integerValue;
            node = node.left;
        }
        return minv;
    }

    /**
     * Gets the node with integerValue value from the tree.
     * 
     * @param current
     * @param value
     * @return
     */
    public Node get(Node current, int value) {
        if (current.integerValue == value || current == null)
            return current;
        if (current.integerValue < value)
            return get(current.right, value);
        return get(current.left, value);
    }
}