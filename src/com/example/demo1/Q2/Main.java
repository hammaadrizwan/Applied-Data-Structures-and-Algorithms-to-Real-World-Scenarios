package com.example.demo1.Q2;

public class Main {
    public static void main(String[] args) {
        LinkedList cardDeck=new LinkedList();
        cardDeck = cardDeck.initialiseDeckOfCards();
        LinkedList playerOne = new LinkedList();
        LinkedList playerTwo = new LinkedList();
        LinkedList playerThree = new LinkedList();
        LinkedList playerFour = new LinkedList();

        playerOne.addACard("2","Spades");
        playerOne.addACard("2","Diamonds");
        playerOne.addACard("3","Hearts");
        playerOne.addACard("6","Diamonds");
        playerOne.displayList();
        System.out.println();
        cardDeck.displayList();

//        playerOne.playACard("Hearts");
//        playerOne.displayList();
//        playerOne.addACard("5","Hearts");
//        playerOne.displayList();
    }
}
