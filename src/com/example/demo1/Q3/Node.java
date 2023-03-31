package com.example.demo1.Q3;

public class Node {
    public String data,code;
    public Node leftChild, rightChild;// each node has a data and a left/right child its linking to, uses the linkedlist approach(doubly linkedlist)

    public Node(String d,String c){
        data = d;
        code = c;
        leftChild = null;
        rightChild = null;
    }

};
