package com.example.demo1.Q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        LinkedList cardDeck = new LinkedList(); //creates a new LinkedList for all the cards in the pack
        ArrayList<LinkedList> players = new ArrayList<>();// creates an array list to store the players hands
        String cardSuit;
        String cardRank;

        String suit;
        String rank;
        //Intialises the 52 deck of cards
        for (int noOfSuit = 1; noOfSuit <= 4; noOfSuit++) {
            if (noOfSuit == 1) {
                cardSuit = "Spades";
            } else if (noOfSuit == 2) {
                cardSuit = "Diamonds";
            } else if (noOfSuit == 3) {
                cardSuit = "Clubs";
            } else {
                cardSuit = "Hearts";
            }
            for (int noOfRank = 2; noOfRank <= 14; noOfRank++) {
                if (noOfRank == 11) {
                    cardRank = "Jack";
                } else if (noOfRank == 12) {
                    cardRank = "Queen";
                } else if (noOfRank == 13) {
                    cardRank = "King";
                } else if (noOfRank == 14) {
                    cardRank = "Ace";
                } else {
                    cardRank = String.valueOf(noOfRank);
                }

                cardDeck.addACard(cardRank, cardSuit);

            }
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of players");
        int noOfPlayers = input.nextInt();

        if (noOfPlayers >= 52 || noOfPlayers <= 1) { //there should be more than one player and less than 52 players in order to distribute the cards equally
            System.out.println("Unable to continue as cards should be distributed equally, enter a value below 52");
        } else {

            for (int playerNo = 1; playerNo <= noOfPlayers; playerNo++) {
                LinkedList playerHand = new LinkedList();
                players.add(playerHand);//creates locations to store the linkedlist of the players Hand.. player[<playerOneHand>,<playerTwoHand>,...,<playerNHand>]
            }
        }

        String functionOption;
        int playerChoice;
        int noOfDeals = 0;
        // initially its false for both players
        ArrayList<boolean[]> noOfFinalDeals;
        boolean[] dealTracker = new boolean[noOfPlayers];

        do {// this will run until deck of cards are empty
            System.out.println();
            System.out.println("Enter a function:\nPress (A) to add a card to a player\nPress (P) to play a card\nPress (I) to get the count of all cards in a players hand\nPress (S) to get a count of all the suits available to a player");
            functionOption = input.next().toUpperCase();

            switch (functionOption) { //checks the option needed
                case "A": {//Adding a card this will run till there are no cards in deck. assuming only 4 players participate in the games
                    System.out.println("to which player, enter the number");
                    playerChoice = input.nextInt() - 1;
                    if ((playerChoice + 1) <= noOfPlayers) {
                        System.out.println("Enter a rank and suit");
                        System.out.println("Rank:");
                        rank = input.next();
                        System.out.println("Suit:");
                        suit = input.next();
                        if (isRankValid(rank) && isSuitValid(suit)) {//checks if the rank and suit entered are valid.
                            if (cardDeck.find(rank, suit) > -1) {//finds the card in the cardDeck
                                int index = cardDeck.find(rank, suit);// gets the index of that card
                                cardDeck.delete(index);//removes the card from the card pack
                                players.get(playerChoice).addACard(rank, suit);//adds the card to the requested players Hand
                                displayDeal(players);//display the card after added
                                ArrayList<String> order;
                                //IF ITS THE FINAL DEAL WE NEED TO SORT
                                if (isThisAFinalDeal(players,numberOfMaxDeals(players))){
                                    for (int i = 0; i < players.size(); i++) {
                                        order = players.get(i).suitOrder();
                                        for (String suit1:order){
                                            System.out.print (suit1+"  ");
                                        }
                                        System.out.println();
//                                        players.set(i,players.get(i).sortBySuit(players.get(i)));
//                                        players.get(i).sortByRank();
                                    }
                                    displayDeal(players);

                                }
//
                            } else {
                                System.out.println("Card is not in deck, add another card");
                            }
                        } else {
                            System.out.println("Invalid rank or suit");
                        }
                    } else {
                        System.out.println("Error: Only " + noOfPlayers + " are taking part in this game");
                    }

                    break;
                }

                case "P": {//Playing a card
                    System.out.println("to which player, enter the number");
                    playerChoice = input.nextInt() - 1;//one is deducted because in lists are stored from 0 to the required number
                    if ((playerChoice + 1) <= noOfPlayers) {
                        System.out.println("Enter the suit");
                        System.out.println("Suit:");
                        suit = input.next();
                        if (isSuitValid(suit)) {
                            String cardDetails = players.get(playerChoice).playACard(suit).displayCard();
                            System.out.println("Card PLayed " + cardDetails);
                        } else {
                            System.out.println("Invalid suit");
                        }
                    } else {
                        System.out.println("Error: Only " + noOfPlayers + " are taking part in this game");
                    }

                    break;
                }

                case "I": //Iterator function
                    System.out.println("from which player, enter the number");
                    playerChoice = input.nextInt() - 1;
                    if ((playerChoice + 1) <= noOfPlayers) {
                        int noOfCards = players.get(playerChoice).iterator();
                        System.out.println("Number of cards for Player " + (playerChoice + 1) + " is " + noOfCards);
                    } else {
                        System.out.println("Error: Only " + noOfPlayers + " are taking part in this game");
                    }

                    break;
                case "S"://Suit Iterator function
                    System.out.println("from which player, enter the number");
                    playerChoice = input.nextInt() - 1;
                    if ((playerChoice + 1) <= noOfPlayers) {
                        System.out.println("Enter a suit");
                        suit = input.next();
                        if (isSuitValid(suit)) {
                            int noOfCardsofSuit = players.get(playerChoice).suitIterator(suit);
                            System.out.println("No of " + suit + " " + noOfCardsofSuit);
                        } else {
                            System.out.println("Invalid suit");
                        }
                    } else {
                        System.out.println("Error: Only " + noOfPlayers + " are taking part in this game");
                    }
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (cardDeck.getSize() > 0);//repeats till the deck of cards are empty


    }

    public static boolean isSuitValid(String suit) {
        if (suit.equals("Spades") || suit.equals("Diamonds") || suit.equals("Clubs") || suit.equals("Hearts")) {
            return true;
        }
        return false;
    }

    public static boolean isRankValid(String rank) {//rank validator, to chek if the rank is in the range between 2 to 10,J,Q,K,A
        if (rank.equals("Jack") || rank.equals("King") || rank.equals("Queen") || rank.equals("Ace") || Integer.parseInt(rank) >= 2 || Integer.parseInt(rank) <= 10) {
            return true;
        }
        return false;
    }

    public static boolean isThisAFinalDeal(ArrayList<LinkedList> player,int maxDeals) {
        for (LinkedList players:player) {
            if (players.getSize()!=maxDeals){
                return false;
            }
        }return true;

    }
    public static int numberOfMaxDeals(ArrayList<LinkedList> players){
        int maximumNumberOfDeals=0;
        for (LinkedList player:players) {
            if (player.getSize()>maximumNumberOfDeals){
                maximumNumberOfDeals = player.getSize();
            }
        }
        return maximumNumberOfDeals;
    }


    public static void displayDeal(ArrayList<LinkedList> players) {
        for (int i = 0; i < players.size(); i++) {
            LinkedList playerHand = players.get(i);
            System.out.print("Player " + (i+1) + ": ");
            for (int cards = 0; cards < playerHand.getSize(); cards++) {
                String cardDetails = playerHand.get(cards).value.displayCard();
                System.out.print(cardDetails + "  ");
            }
            System.out.println();
        }
    }


//    public static ArrayList<LinkedList> sortByRank(ArrayList<LinkedList> players){
//        for (int handPosition = 0; handPosition < players.size(); handPosition++) {
//            LinkedList playerHand = players.get(handPosition);
//            LinkedList sortedByRank = playerHand.sortToDisplay(playerHand);
//            players.set(handPosition,sortedByRank);
//        }
//        return players;
//    }


}


