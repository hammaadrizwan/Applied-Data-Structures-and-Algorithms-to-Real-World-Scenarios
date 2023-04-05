package com.example.demo1.Q2A;

import java.util.ArrayList;

public class LinkedList{
    public Node head;//start pointer of the linked list
    public Node tail;//tail poiter of the linkedList
    private int size;//size of the list

    public LinkedList() {
        this.size = 0;
    }

    public void addACard(String rank,String suit) {
        if (suit.equals("Spades")||suit.equals("Diamonds")||suit.equals("Clubs")||suit.equals("Hearts")){
            Card card = new Card(rank,suit);//creates a new card array of suit and rank
            if (size == 0) {//if the list is empty create a new node and store the card in the node

                Node node = new Node(card,null);
                node.next = head;
                head = node;// the new node is sest to be the head node

                if (tail == null) {//add the tail to be the head
                    tail = head;
                }
                size += 1;
            }else{
                Node temp = head;//stores the head node as a temporary node
                for (int i = 1; i < size; i++) {
                    temp = temp.next;//gets the last node entered,
                }

                Node node = new Node(card, temp.next);//the new node will store the card, and will point to null
                temp.next = node;//the prevoius node now points to the new node

                size++;// the size increases
            }

        }else{
            System.out.println("invalid Suit");
        }

    }


    public Card playACard(String suit) {
        int index=0;
        boolean cardFound=false;
        int listSize = getSize();
        if (listSize==0){//if the size of the list is empty there is no card to play
            System.out.println("No card to play, Add a card first.");
            return null;
        }else{
            Node node = head;//gets the first node in the list
            while (node != null) {
                if (node.value.getSuit().equals(suit)) {//travereses in the list until it finds the node with the relavant suit
                    Card removed = delete(index);//removes the node from the list
                    return removed;//displays the card removed
                }
                node = node.next;
                index++;//keeps a track of the nodes it visited
            }
        }
        if (cardFound!=true){//if card is not found
            Card removed = deleteFirst();//remove which ever card is available at first
            System.out.println(removed.getRank()+" "+removed.getSuit());
            return removed;
        }
        return null;
    }
    public int find(String rank,String suit) {
        int index = 0;
        Node node = head;
        while (node != null) {
            if (node.value.getSuit().equals(suit)&&node.value.getRank().equals(rank)) {//to find the position of a specific card and retursn the index if found, else returns -1.(no card exsists)
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    public Card delete(int index) {
        if (index == 0) {//if index is zero, means remove the first element of the list
            Card val = head.value;//value of head is being stored
            head = head.next;//head is set to null since its removed
            if (head == null) {
                tail = null;
            }//both pointers are set to null
            size--;//size of the list reduces by 1
            return val;
        }
        Node prev = get(index - 1);//if its anyother index then we store the prvoius node
        Card val = prev.next.value;//get the value to be deleted

        prev.next = prev.next.next;//sets the prevoius node pointer to the next available position after deleting the node.
        size--;
        return val;
    }


    public int iterator(){
        Node node = head;
        int count=0;
        while (node != null) {//counts the number of nodes in the list
            node = node.next;
            count++;
        }
        return count;
    }
    public int suitIterator(String suit) {
        Node node = head;
        int count=0;
        while (node != null) {//same as iterator but checks for the number of nodes with the specific suit
            if (suit.equals(node.value.getSuit())) {
                count++;
            }
            node = node.next;
        }
        return count;
    }

    public Node get(int index) {
        Node node = head;//returns the node of that specific index
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public ArrayList<String> suitOrder(){
        ArrayList<String> allSuits = new ArrayList<>();//this array list will store the order of suits
        Node node = head;
        String suit;
        while (node != null) {
            boolean suitExsists=false;
            Card card =node.value;
            suit=card.getSuit();
            if (allSuits.size()==0){//if this is the first card entered then its stored in the list containgin the order of the suit
                allSuits.add(suit);
            }else{
                for (String suitAvailable:allSuits){//a for loop checks if the new suit is available in the list of siuit then its not added
                    if (suit.equals(suitAvailable)){
                        suitExsists=true;
                    }
                }
                if (suitExsists!=true){
                    allSuits.add(suit);//if not its inserted to the order of the suit
                }
            }

            node = node.next;
        }
        return allSuits;
    }


    public Card deleteFirst() {
        Card val = head.value;
        head = head.next;//deletes the head
        if (head == null) {
            tail = null;//sets the tail to be null
        }
        size--;//the size reduces by one
        return val;
    }


    public int getSize(){
        return size;
    }// returns the size of the LinkedList

}



