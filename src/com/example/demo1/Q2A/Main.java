package com.example.demo1.Q2A;

import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        LinkedList cardDeck = new LinkedList(); //creates a new LinkedList for all the cards in the pack
        ArrayList<LinkedList> players = new ArrayList<>();// creates an array list to store the players hands
        String cardSuit;
        String cardRank;

        Random random = new Random();

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
                cardRank = switch (noOfRank) {
                    case 11 -> "Jack";
                    case 12 -> "Queen";
                    case 13 -> "King";
                    case 14 -> "Ace";
                    default -> String.valueOf(noOfRank);
                };

                cardDeck.addACard(cardRank, cardSuit);

            }
        }

        Scanner input = new Scanner(System.in);
        int noOfPlayers;

        while (true){
            while (true) {// TO validate if an integer is entered
                System.out.println();
                System.out.println("Enter the number of players");
                String userInput = input.nextLine();

                try {
                    noOfPlayers = Integer.parseInt(userInput);
                    break; // Exit the loop if input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }//until the user enters a correct data type the code will run
            }
            if (noOfPlayers > 52 || noOfPlayers <= 1){
                System.out.println("Unable to continue as cards should be distributed equally, enter a value in between 2 and 52 Inclusive");
            }else{//until the user enters a value between 1 and 52 the system will not proceed
                break;
            }
        }


        for (int playerNo = 1; playerNo <= noOfPlayers; playerNo++) {
            LinkedList playerHand = new LinkedList();
            players.add(playerHand);//creates locations to store the linkedlist of the players Hand.. player[<playerOneHand>,<playerTwoHand>,...,<playerNHand>]
        }


        String functionOption;
        int playerChoice;



        do {// this will run until deck of cards are empty
            System.out.println();
            System.out.println("Enter a function:\nPress (A) to add a card to a player\nPress (P) to play a card\nPress (I) to get the count of all cards in a players hand\nPress (S) to get a count of all the suits available to a player\nPress (ALL) to add card to all players, in rounds (Only works if there are 2,4,13,26 or 52 players)\nPress (Q) to QUIT");
            functionOption = input.next().toUpperCase();

            switch (functionOption) { //checks the option needed

                case "A" -> {
                    if(!isCardDeckEmpty(cardDeck)){
                        System.out.println("to which player, enter the number");//asks the host which player needs to be dealt with.
                        playerChoice = input.nextInt() - 1;
                        if ((playerChoice + 1) <= noOfPlayers) {
                            int cardPosition = random.nextInt((cardDeck.getSize()-1));//generates random number between 0 to N meaning until 51 as the card positons go from 0 to 51
                            Node cardToBeAdded = cardDeck.get(cardPosition);//gets the Node that stores the Card object from the deck of cards randomly
                            String rank = cardToBeAdded.value.getRank();//dissects data into rank and suit
                            String suit = cardToBeAdded.value.getSuit();
                            cardDeck.delete(cardPosition);//removes the card from the card pack
                            players.get(playerChoice).addACard(rank, suit);//adds the card to the requested players Hand
                            displayDeal(players);//display the card after added

                            //IF ITS THE FINAL DEAL WE NEED TO SORT
                            if (isThisAFinalDeal(players, numberOfMaxDeals(players))) {//iff all players have the same number of cards at each interation meaning they have been dealt with the same cards then we sort their cards
                                System.out.println("After sorting all hands");
                                sortSuit(players);
                            }
                        } else {
                            System.out.println("Error: Only " + noOfPlayers + " are taking part in this game");//incorrect player number will display a mesage.
                        }
                    }
                }
                case "P" -> {//Playing a card
                    System.out.println("to which player, enter the number");
                    playerChoice = input.nextInt() - 1;//one is deducted because in lists are stored from 0 to the required number
                    if ((playerChoice + 1) <= noOfPlayers) {
                        System.out.println("Enter the suit");
                        System.out.println("Suit:");//gets the suit that needs to be played
                        String suit = input.next();
                        if (isSuitValid(suit)) {//validates the suit input
                            String cardDetails = players.get(playerChoice).playACard(suit).displayCard();
                            System.out.println("Card PLayed " + cardDetails);//plays a card from the suit ifnot plays the card thats at the first position in the hand.
                        } else {
                            System.out.println("Invalid suit");
                        }
                    } else {
                        System.out.println("Error: Only " + noOfPlayers + " are taking part in this game");
                    }

                }
                case "I" -> { //Iterator function
                    System.out.println("from which player, enter the number");
                    playerChoice = input.nextInt() - 1;
                    if ((playerChoice + 1) <= noOfPlayers) {
                        int noOfCards = players.get(playerChoice).iterator();//gets the number of cards that the relavant player has
                        System.out.println("Number of cards for Player " + (playerChoice + 1) + " is " + noOfCards);
                    } else {
                        System.out.println("Error: Only " + noOfPlayers + " are taking part in this game");
                    }
                }
                case "S" -> {//Suit Iterator function
                    System.out.println("from which player, enter the number");
                    playerChoice = input.nextInt() - 1;
                    if ((playerChoice + 1) <= noOfPlayers) {
                        System.out.println("Enter a suit");
                        String suit = input.next();//same as iterator but here it returns the number of cards from the same suit
                        if (isSuitValid(suit)) {
                            int noOfCardsofSuit = players.get(playerChoice).suitIterator(suit);
                            System.out.println("No of " + suit + " " + noOfCardsofSuit);
                        } else {
                            System.out.println("Invalid suit");
                        }
                    } else {
                        System.out.println("Error: Only " + noOfPlayers + " are taking part in this game");
                    }
                }

                case"ALL"->{//This is to give the computer to distribute cards evenly to all the players until the deck of card is empty.
                    if(!isCardDeckEmpty(cardDeck)){
                        if (52%noOfPlayers==0){//checks if sufficent players are available, inorder to distribute evenly
                            do{
                                for (int i = 0; i < noOfPlayers; i++) {
                                    playerChoice =i;
                                    int cardPosition = random.nextInt((cardDeck.getSize()));
                                    Node cardToBeAdded = cardDeck.get(cardPosition);
                                    String rank = cardToBeAdded.value.getRank();
                                    String suit = cardToBeAdded.value.getSuit();
                                    cardDeck.delete(cardPosition);//removes the card from the card pack
                                    players.get(playerChoice).addACard(rank, suit);//adds the card to the requested players Hand
                                }
                                System.out.println("After sorting all hands");
                                sortSuit(players);
                            }while (cardDeck.getSize()> 0);

                        } else{
                            System.out.println("Cannot simulate as cards cannot be distributed equally");
                        }
                    }
                }
                case "Q" -> System.exit(0);
                default -> System.out.println("Invalid Option");
            }
        } while (true);//repeats till the deck of cards are empty


    }
    public static boolean isCardDeckEmpty(LinkedList deck){
        if (deck.getSize()==0){
            System.out.println("All cards have been dealt.");
            return true;
        }
        return false;
    }
    public static boolean isSuitValid(String suit) {
        return suit.equals("Spades") || suit.equals("Diamonds") || suit.equals("Clubs") || suit.equals("Hearts");
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

    public static void sortSuit(ArrayList<LinkedList> players) {
        for (int i=0;i<players.size();i++ ) {
            LinkedList playerHand = players.get(i);
            ArrayList<String> order = playerHand.suitOrder();

            Card[] playerHandSorted = new Card[playerHand.iterator()];
            for (int j = 0; j < playerHandSorted.length; j++) {
                Card card = playerHand.get(j).value;
                playerHandSorted[j] = card;
            }

            for (String suitOrder : order) {//Sort by suit
                for (int outer = 0; outer < playerHandSorted.length; outer++) {
                    for (int inner = 0; inner < playerHandSorted.length - 1; inner++) {
                        if (playerHandSorted[inner].getSuit().equals(suitOrder) && !playerHandSorted[inner + 1].getSuit().equals(suitOrder)) {
                            Card temp = playerHandSorted[inner];
                            playerHandSorted[inner] = playerHandSorted[inner + 1];
                            playerHandSorted[inner + 1] = temp;
                        }

                    }
                }
            }
            for (int j = 0; j < playerHandSorted.length; j++) {//sort by Rank
                for (int k = 0; k < playerHandSorted.length - 1; k++) {
                    if ((playerHandSorted[k].getSuit().equals(playerHandSorted[k + 1].getSuit()))) {
                        if ((playerHandSorted[k].rankValue(playerHandSorted[k].getRank())) > (playerHandSorted[k + 1].rankValue(playerHandSorted[k + 1].getRank()))) {
                            Card temp = playerHandSorted[k];
                            playerHandSorted[k] = playerHandSorted[k + 1];
                            playerHandSorted[k + 1] = temp;
                        }
                    }

                }
            }

            System.out.print("Player " + (i+1) + ": ");
            for (Card sorted : playerHandSorted) {
                System.out.print(sorted.displayCard() + "  ");
            }
            System.out.println();
        }
    }
}



