package com.example.demo1.Q2;

public class LinkedList{
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.size = 0;
    }

    public void addACard(String rank,String suit) {
        Card card = new Card(rank,suit);
        if (size == 0) {

            Node node = new Node(card);
            node.next = head;
            head = node;

            if (tail == null) {
                tail = head;
            }
            size += 1;
        }else{
            Node temp = head;
            for (int i = 1; i < size; i++) {
                temp = temp.next;
            }

            Node node = new Node(card, temp.next);
            temp.next = node;

            size++;
        }
    }


    public Card playACard(String suit) {
        int index=0;
        boolean cardFound=false;
        int listSize = getSize();
        if (listSize==0){
            System.out.println("No card to play, Add a card first.");
            return null;
        }else{
            Node node = head;
            while (node != null) {
                if (node.value.getSuit().equals(suit)) {
                    Card removed = delete(index);
                    return removed;
                }
                node = node.next;
                index++;

            }
        }
        if (cardFound!=true){
            Card removed = deleteFirst();
            System.out.println(removed.getRank()+" "+removed.getSuit());
            return removed;
        }
        return null;
    }

    public Card delete(int index) {
        if (index == 0) {
            Card val = head.value;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return val;
        }

        Node prev = get(index - 1);
        Card val = prev.next.value;

        prev.next = prev.next.next;
        size--;
        return val;
    }

    public int find(Card value) {
        Node node = head;
        int count=0;
        while (node != null) {
            if (value.equals(node.value)) {
                return count;
            }
            node = node.next;
            count++;
        }
        return -1;
    }
    public int iterator(){
        Node node = head;
        int count=0;
        while (node != null) {
            node = node.next;
            count++;
        }
        return count;
    }

    public Node get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    public void set(int index,Card val) {
        Node currentVal = get(index);
        Node nextVal = get(index+1);
        currentVal.value=val;
        currentVal.next=nextVal;
    }



    public Card deleteFirst() {
        Card val = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return val;
    }

    public void displayList() {
        Node temp = head;
        while (temp != null) {
            Card card = temp.value;
            System.out.print(card.getRank() +" "+ card.getSuit()+" ");
            temp = temp.next;
        }
        System.out.println();
    }
    public int getSize(){
        return size;
    }
    }



