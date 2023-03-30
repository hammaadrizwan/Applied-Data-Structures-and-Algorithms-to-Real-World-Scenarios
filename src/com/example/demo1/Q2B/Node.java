package com.example.demo1.Q2B;

public class Node {
    public String data;
    public Node leftChild, rightChild;// each node has a data and a left/right child its linking to, uses the linkedlist approach(doubly linkedlist)

    public Node(String d){
        data = d;
        leftChild = null;
        rightChild = null;
    }

};
