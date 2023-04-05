package com.example.demo1.Q2A;

public class Node {
    Card value;
    Node next;

    public Node(Card value, Node next) {//THe node takes in the object card and another node. which is another card pointing to another node
        this.value = value;
        this.next = next;
    }
}
