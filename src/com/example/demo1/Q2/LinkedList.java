package com.example.demo1.Q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//    public LinkedList sortToDisplay(LinkedList playerHand){
//        for (int i =0;i<playerHand.getSize();i++){
//            for (int index =0;index<playerHand.getSize()-1;index++){
//                Card currentCard = playerHand.get(index).value;
//                Card nextCard = playerHand.get(index+1).value;
//                if (currentCard.getSuit().equals(nextCard.getSuit())){
//                    if (currentCard.rankValue(currentCard.getRank())>nextCard.rankValue(nextCard.getRank())){
//                        Card temp_card =currentCard;
//                        playerHand.set(index,nextCard);
//                        playerHand.set(index+1,temp_card);
//                    }
//                }else{
//                    if (currentCard.rankValue(currentCard.getRank())>nextCard.rankValue(nextCard.getRank())){
//                        Card temp_card =currentCard;
//                        playerHand.set(index,nextCard);
//                        playerHand.set(index+1,temp_card);
//                    }
//                }
//
//            }
//        }
//        return playerHand;
//    }



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


    public ArrayList<String> suitOrder(){
        Node temp = head;
        int index=0;
        boolean suitExsists = false;
        ArrayList<String> suitOrder = new ArrayList<>();
        while (temp != null) {
            Card card = temp.value;
            String suit = card.getSuit();
            if (suitOrder.size()==0){
                suitOrder.add(suit);//[Spades]
                //return suitOrder;
            }else{
                for (String suitAvaliable:suitOrder) {
                    if (suitAvaliable.equals(suit)){
                        suitExsists=true;
                        break;
                    }
                }
            }
            if (suitExsists!=true){suitOrder.add(suit);}
            temp = temp.next;
        }
        return suitOrder;
    }

    public void sortByRank()
    {
        // Node current will point to head
        Node current = head, index = null;

        Card temp;

        if (head == null) {
            return;
        }
        else {
            while (current != null) {
                // Node index will point to node next to
                // current
                index = current.next;

                while (index != null) {
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them
                    if (current.value.rankValue(current.value.getRank()) > index.value.rankValue(index.value.getRank())) {
                        temp = current.value;
                        current.value= index.value;
                        index.value= temp;
                    }

                    index = index.next;
                }
                current = current.next;
            }
        }
    }

//    public LinkedList sortBySuit(LinkedList playerHand) {
//        ArrayList<String> suitOrder = playerHand.suitOrder();
//        LinkedList sortedHand = new LinkedList();
//        for (String suit : suitOrder) {
//            for (int cardPos = 0; cardPos < playerHand.getSize(); cardPos++) {
//                if (playerHand.get(cardPos).value.getSuit().equals(suit)) {
//                    Card card = playerHand.get(cardPos).value;
//                    sortedHand.addACard(card.getRank(), card.getSuit());
//                }
//            }
//        }
//    }

}



