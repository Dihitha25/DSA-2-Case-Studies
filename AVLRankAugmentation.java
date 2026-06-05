import java.util.*;

class Node {
    int key;
    int height;
    int size;
    Node left, right;

    Node(int key) {
        this.key = key;
        this.height = 1;
        this.size = 1;
    }
}

public class AVLRankAugmentation {

    Node root;

    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    int size(Node n) {
        return (n == null) ? 0 : n.size;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    void update(Node n) {
        if (n != null) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
            n.size = 1 + size(n.left) + size(n.right);
        }
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        update(y);
        update(x);

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        update(x);
        update(y);

        return y;
    }

    Node insert(Node node, int key) {

        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        update(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Node kthElement(Node node, int k) {

        if (node == null)
            return null;

        int leftSize = size(node.left);

        if (k == leftSize + 1)
            return node;

        if (k <= leftSize)
            return kthElement(node.left, k);

        return kthElement(node.right, k - leftSize - 1);
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        AVLRankAugmentation tree = new AVLRankAugmentation();

        int members[] = {101, 205, 310, 415, 520, 610, 720};

        for (int member : members) {
            tree.root = tree.insert(tree.root, member);
        }

        System.out.println("Discord Members (Join Order IDs):");
        tree.inorder(tree.root);

        System.out.println("\n");

        int k = 4;

        Node result = tree.kthElement(tree.root, k);

        if (result != null) {
            System.out.println("Find " + k + "th Most Senior Member");
            System.out.println("Output: Member ID = " + result.key);
        } else {
            System.out.println("Invalid Rank");
        }
    }
}