package lw06.unguided;

import java.util.Scanner;

class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree tree = new BinarySearchTree();

        // Line 1: values to insert
        Scanner line = new Scanner(read.nextLine());
        while (line.hasNextInt()) {
            tree.insert(line.nextInt());
        }
        line.close();

        // Line 2: target to search
        int target = read.nextInt();

        System.out.print("Preorder : ");
        tree.preOrder();

        System.out.print("Inorder : ");
        tree.inOrder();

        System.out.print("Postorder : ");
        tree.postOrder();

        System.out.println("Search " + target + " : " + tree.search(target));
        System.out.println("Nodes : " + tree.countNodes());
        System.out.println("Leaves : " + tree.countLeaves());

        read.close();
    }
}