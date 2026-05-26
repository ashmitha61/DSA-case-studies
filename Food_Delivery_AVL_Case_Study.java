import java.util.*;

class Node {
    int orderId, height;
    String customerName;
    Node left, right;

    Node(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        height = 1;
    }
}

public class Food_Delivery_AVL_Case_Study {

    Node root;

    // Get Height
    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Get Balance Factor
    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Right Rotation
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left Rotation
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert Order
    Node insert(Node node, int orderId, String customerName) {

        if (node == null)
            return new Node(orderId, customerName);

        if (orderId < node.orderId)
            node.left = insert(node.left, orderId, customerName);

        else if (orderId > node.orderId)
            node.right = insert(node.right, orderId, customerName);

        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Case
        if (balance > 1 && orderId < node.left.orderId)
            return rightRotate(node);

        // RR Case
        if (balance < -1 && orderId > node.right.orderId)
            return leftRotate(node);

        // LR Case
        if (balance > 1 && orderId > node.left.orderId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Case
        if (balance < -1 && orderId < node.right.orderId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Preorder Traversal
    void preOrder(Node node) {
        if (node != null) {
            System.out.println("Order ID: " + node.orderId +
                    " | Customer: " + node.customerName);

            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {

        Food_Delivery_AVL_Case_Study tree =
                new Food_Delivery_AVL_Case_Study();

        // Insert Food Orders
        tree.root = tree.insert(tree.root, 30, "Rahul");
        tree.root = tree.insert(tree.root, 20, "Anjali");
        tree.root = tree.insert(tree.root, 40, "Kiran");
        tree.root = tree.insert(tree.root, 10, "Sneha");

        System.out.println("Food Delivery Orders (AVL Tree):");
        tree.preOrder(tree.root);
    }
}