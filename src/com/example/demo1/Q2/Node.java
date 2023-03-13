package com.example.demo1.Q2;

public class Node {
    Card value;
    Node next;

    public Node(Card value) {
        this.value = value;
    }

    public Node(Card value, Node next) {
        this.value = value;
        this.next = next;
    }
}
