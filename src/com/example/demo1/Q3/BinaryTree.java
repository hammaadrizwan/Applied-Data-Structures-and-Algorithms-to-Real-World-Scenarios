package com.example.demo1.Q3;

public class BinaryTree {
    private Node root;//initially the tree has the root node

    // recursive function to search the value
    private Node finding(String expected, Node node) {
        if (node == null) {//binary search is being imlpemented, Efficeint compared to linear search with Linked List.
            return null;// if not found returns null
        } else if (node.data.equalsIgnoreCase(expected)) {// when both of the strings are equal that mean value found
            return node;
        } else if (node.data.compareToIgnoreCase(expected) < 0) {//if the data stored in the tree is less than the data searched then move to the right subtree
            return finding(expected, node.rightChild);
        } else {
            return finding(expected, node.leftChild);// else continue looking on the left side
        }
    }

    // search for a value starting at the root - this metohd start searching from root
    public String find(String expected) {
        Node node = finding(expected, root);//this gets the value from the recursive function call
        if (node == null) {
            return null;
        } else {
            return node.data;
        }
    }

    private Node searchingCode(String codeExpected, Node node) {
        if (node == null) {//binary search is being imlpemented, Efficeint compared to linear search with Linked List.
            return null;// if not found returns null
        } else if (node.code.equalsIgnoreCase(codeExpected)) {// when both of the strings are equal that mean value found
            return node;
        } else if (node.code.compareToIgnoreCase(codeExpected) < 0) {//if the data stored in the tree is less than the data searched then move to the right subtree
            return finding(codeExpected, node.rightChild);
        } else {
            return finding(codeExpected, node.leftChild);// else continue looking on the left side
        }
    }

    // search for a value starting at the root - this metohd start searching from root
    public String searchCode(String codeExpected) {
        Node node = searchingCode(codeExpected, root);//this gets the value from the recursive function call
        if (node == null) {
            return null;
        } else {
            return node.data;
        }
    }

    public void insert(String data,String code){
        root = inserting(root,data,code);//uses recursion to insert the node, from the inserting function
    }
    private Node inserting(Node node, String data,String code){
        if (node == null){
            node = new Node(data,code);// if the tree is empty node is being inserted into the tree, this is the base case
            return node;
        }
        if (data.compareTo(node.data)<0){
            node.leftChild = inserting(node.leftChild,data,code);//if the data searched is less than the value stored in the tree then move to the left subtree .
        }
        else if (data.compareTo(node.data)>0){
            node.rightChild = inserting(node.rightChild,data,code);//if not call the inserting function on the right side of the tree
        }
        return node;//returns the node to the recent function call.
    }

    }


