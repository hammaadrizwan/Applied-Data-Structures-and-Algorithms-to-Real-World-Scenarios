package com.example.demo1.Q3;

public class BinaryTree {
    private Node root;//initially ythe tree has the root node

    public Node find(String data){
        Node node = root;
        while (node != null){//binary search is being imlpemented, Efficeint compared to linear search with Linked List.
            if (node.data.compareTo(data)==0){// when both of the strings are equal that mean value found
                return node;
            }
            if (node.data.compareTo(data)<0){ //if the data stored in the tree is less than the data searched then move to the right subtree
                node = node.rightChild;
            }
            else{
                node = node.leftChild; // else continue looking on the left side
            }
        }
        return null; // if not found returns null
    }

    public void insert(String data){
        root = inserting(root,data);//uses recursion to insert the node, from the inserting function
    }
    private Node inserting(Node node, String data){
        if (node == null){
            node = new Node(data);// if the tree is empty node is being inserted into the tree, this is the base case
            return node;
        }
        if (data.compareTo(node.data)<0){
            node.leftChild = inserting(node.leftChild,data);//if the data searched is less than the value stored in the tree then move to the left subtree .
        }
        else if (data.compareTo(node.data)>0){
            node.rightChild = inserting(node.rightChild,data);//if not call the inserting function on the right side of the tree
        }
        return node;//returns the node to the recent function call.
    }

    public void display(){
        displaying(root);//same as inserting but here we use recursion to display the tree
    }
    private void displaying(Node node){
        if (node !=null){//until all the elements has been displayed first the all the left side nodes are being displayed then the rightside nodes. This shows how the final sorted function works using Search trees.
            displaying(node.leftChild);
            System.out.println(node.data+" ");
            displaying(node.rightChild);
        }
    }











//    private Node root;
//
//    // set the whole tree to null
//    public BinaryTree() {
//        root = null;
//    }
//
//    // perform inorder traversal on the tree
//    private int inorderPrint(Node node) {
//        if (node != null) {
//            inorderPrint(node.left);
//            System.out.print(node.data + " ");
//            inorderPrint(node.right);
//        }
//    }
//
//    // print the entire tree
//    public void print() {
//        int count = inorderPrint(root);
//        System.out.println();
//        System.out.println(count);
//    }
//
//    // recursive function to insert at a particular node
//    private Node insertAt(String value, Node node) {
//        if (node == null) {
//            return new Node(value);
//        }
//
//        if (node.data.compareTo(value) < 0) {
//            // right
//            node.right = insertAt(value, node.right);
//        } else {
//            // left
//            node.left = insertAt(value, node.left);
//        }
//
//        return node;
//    }
//
//    // insert a number calls recursive function to insert at the root
//    public void insert(String value) {
//        root = insertAt(value, root);
//    }
//
//    // perform the recursive search at a given node
//    private Node searchAt(String target, Node node) {
//        if (node == null) {
//            return null;
//        } else if (node.data.equals(target)) {
//            return node;
//        } else if (node.data.compareTo(target) < 0) {
//            return searchAt(target, node.right);
//        } else {
//            return searchAt(target, node.left);
//        }
//    }
//
//    // search for a value and return it, or null if not found
//    public String search(String target) {
//        Node node = searchAt(target, root);
//        if (node == null) {
//            return null;
//        } else {
//            return node.data;
//        }
//    }
//
//    // find the smallest node value in a subtree
//    private Node min(Node node) {
//        if (node == null) {
//            return null;
//        } else if (node.left == null) {
//            return node;
//        } else {
//            return min(node.left);
//        }
//    }

}
