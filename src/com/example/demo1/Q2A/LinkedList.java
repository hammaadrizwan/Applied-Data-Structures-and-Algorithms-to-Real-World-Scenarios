package com.example.demo1.Q2A;

import java.util.ArrayList;

public class LinkedList{
    public Node head;
    public Node tail;
    private int size;

    public LinkedList() {
        this.size = 0;
    }

    public void addACard(String rank,String suit) {
        if (suit.equals("Spades")||suit.equals("Diamonds")||suit.equals("Clubs")||suit.equals("Hearts")){
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

        }else{
            System.out.println("invalid Suit");
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
    public int find(String rank,String suit) {
        int index = 0;
        Node node = head;
        while (node != null) {
            if (node.value.getSuit().equals(suit)&&node.value.getRank().equals(rank)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
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


    public int iterator(){
        Node node = head;
        int count=0;
        while (node != null) {
            node = node.next;
            count++;
        }
        return count;
    }
    public int suitIterator(String suit) {
        Node node = head;
        int count=0;
        while (node != null) {
            if (suit.equals(node.value.getSuit())) {
                count++;
            }
            node = node.next;
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

    public ArrayList<String> suitOrder(){
        ArrayList<String> allSuits = new ArrayList<>();
        Node node = head;
        String suit;
        while (node != null) {
            boolean suitExsists=false;
            Card card =node.value;
            suit=card.getSuit();
            if (allSuits.size()==0){
                allSuits.add(suit);
            }else{
                for (String suitAvailable:allSuits){
                    if (suit.equals(suitAvailable)){
                        suitExsists=true;
                    }
                }
                if (suitExsists!=true){
                    allSuits.add(suit);
                }
            }

            node = node.next;
        }
        return allSuits;
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


    public int getSize(){
        return size;
    }

    public void sortBySuit(ArrayList<String> suitOrder){
        for (String suit:suitOrder) {
            Node current = head;
            Node index = null;
            Card temp;
            while (current != null) {
                index = current.next;

                while (index != null) {
                    if ((current.value.getSuit().equals(suit)&&index.value.getSuit().equals(suit))){
                        if (current.value.rankValue(current.value.getRank()) > index.value.rankValue(index.value.getRank())) {
                            temp = current.value;
                            current.value= index.value;
                            index.value= temp;
                        }
                    }

                    index = index.next;
                }
                current = current.next;
            }
    }
}


}



