package hackerrank;

public class BinaryTree {
    public static void insert(Node node, int value) {
        if( value < node.value) {
            if(node.left != null) {
                insert(node.left, value);
            } else {
                node.left = new Node(value);
            }
        } else if(node.right != null) {
            insert(node.right, value);
        } else {
            node.right = new Node(value);
        }
    }

    public static void visit(Node node) {
        System.out.println("Node: " + node.value);
    }

    public static void traverseInOrder(Node node) {
        if(node != null) {
            traverseInOrder(node.left);
            visit(node);
            traverseInOrder(node.right);
        }
    }

    public static void traversePreOrder(Node node) {
        if(node != null) {
            visit(node);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public static void traversePostOrder(Node node) {
        if(node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            visit(node);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        insert(root, 4);
        insert(root, 9);
        insert(root, 3);
        insert(root, 6);
        traversePostOrder(root);
    }
}
