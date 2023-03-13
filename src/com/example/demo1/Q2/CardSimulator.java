package com.example.demo1.Q2;

import java.util.ArrayList;

public class CardSimulator {
    LinkedList[] hands;
    int currentPlayer;
    CardSimulator(int numPlayers) {
            // Initialize the hands for each player
            this.hands = new Hand[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                this.hands[i] = new LinkedList();
            }
            // Start with the first player
            this.currentPlayer = 0;
        }
//        LinkedList playerOneHand = new LinkedList(); //new hand for each player
//        LinkedList playerTwoHand = new LinkedList();
//        LinkedList playerThreeHand = new LinkedList();
//        LinkedList playerFourHand = new LinkedList();
//
//        playerOneHand.addACard("2","Spades");
//        playerTwoHand.addACard("4","Spades");
//        playerOneHand.addACard("Ace","Diamonds");
//        playerOneHand.addACard("5","Hearts");
//        playerOneHand.addACard("9","Spades");
//        playerOneHand.displayList();
//
//        Card cardPlayed = playerOneHand.playACard("Diamonds");
//
//        playerOneHand.displayList();


    public LinkedList sort(LinkedList playerHand){
        for (int i =0;i<playerHand.getSize();i++){
            for (int index =0;index<playerHand.getSize()-1;index++){
                Card currentCard = playerHand.get(index).value;
                Card nextCard = playerHand.get(index+1).value;
                if (currentCard.getSuit().equals(nextCard.getSuit())){
                    if (currentCard.rankValue(currentCard.getRank())>nextCard.rankValue(nextCard.getRank())){
                        Card temp =currentCard;
                        playerHand.set(index,nextCard);
                        playerHand.set(index+1,temp);
                    }
                }else{
                    if (currentCard.rankValue(currentCard.getRank())>nextCard.rankValue(nextCard.getRank())){
                        Card temp =currentCard;
                        playerHand.set(index,nextCard);
                        playerHand.set(index+1,temp);
                    }
                }

            }
        }
        return playerHand;
    }

    public static void main(String[] args) {
        CardSimulator game = new CardSimulator(4);
        System.out.println(game);

    }
}


