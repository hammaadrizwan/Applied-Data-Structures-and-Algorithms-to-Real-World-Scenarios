package com.example.demo1.Q2A;

import java.util.ArrayList;

import java.util.InputMismatchException;
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
            while(true){//Exception Handelling for each inpput.. option,noOfshares and price
                System.out.println("Enter a function:\nPress (A) to add a card to a player\nPress (P) to play a card\nPress (I) to get the count of all cards in a players hand\nPress (S) to get a count of all the suits available to a player\nPress (ALL) to add card to all players, in rounds (Only works if there are 2,4,13,26 or 52 players)\nPress (Q) to QUIT");
                functionOption = input.next().toUpperCase();
                try{
                    if(!(functionOption.equals("A")||functionOption.equals("P")||functionOption.equals("I")||functionOption.equals("S")||functionOption.equals("Q")||functionOption.equals("ALL"))){
                        throw new InputMismatchException();
                    }else{
                        break;
                    }
                }catch (InputMismatchException e){
                    System.out.println();
                    System.out.println("Invalid option, try again");
                }
            }

            switch (functionOption) { //checks the option needed
                case "A" -> {
                    if(!isCardDeckEmpty(cardDeck)){
                        while (true) {// TO validate if an integer is entered
                            System.out.println("to which player, enter the number");//asks the host which player needs to be dealt with.
                            String userInput = input.next();
                            try {
                                playerChoice = Integer.parseInt(userInput);
                                break; // Exit the loop if input is valid
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter an integer.");
                            }//until the user enters a correct data type the code will run
                        }
                        playerChoice-=1;
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
                    while (true) {// TO validate if an integer is entered
                        System.out.println("to which player, enter the number");//asks the host which player needs to be dealt with.
                        String userInput = input.next();
                        try {
                            playerChoice = Integer.parseInt(userInput);
                            break; // Exit the loop if input is valid
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer.");
                        }//until the user enters a correct data type the code will run
                    }
                    playerChoice-=1;
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
                    while (true) {// TO validate if an integer is entered
                        System.out.println("to which player, enter the number");//asks the host which player needs to be dealt with.
                        String userInput = input.next();
                        try {
                            playerChoice = Integer.parseInt(userInput);
                            break; // Exit the loop if input is valid
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer.");
                        }//until the user enters a correct data type the code will run
                    }
                    playerChoice-=1;
                    if ((playerChoice + 1) <= noOfPlayers) {
                        int noOfCards = players.get(playerChoice).iterator();//gets the number of cards that the relavant player has
                        System.out.println("Number of cards for Player " + (playerChoice + 1) + " is " + noOfCards);
                    } else {
                        System.out.println("Error: Only " + noOfPlayers + " are taking part in this game");
                    }
                }

                case "S" -> {//Suit Iterator function
                    while (true) {// TO validate if an integer is entered
                        System.out.println("to which player, enter the number");//asks the host which player needs to be dealt with.
                        String userInput = input.next();
                        try {
                            playerChoice = Integer.parseInt(userInput);
                            break; // Exit the loop if input is valid
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer.");
                        }//until the user enters a correct data type the code will run
                    }
                    playerChoice-=1;
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

    public static boolean isCardDeckEmpty(LinkedList deck){//to check if the deck of card is empty
        if (deck.getSize()==0){
            System.out.println("All cards have been dealt.");
            return true;
        }
        return false;
    }

    public static boolean isSuitValid(String suit) {//Checks if the suit entered is valid
        return suit.equals("Spades") || suit.equals("Diamonds") || suit.equals("Clubs") || suit.equals("Hearts");
    }


    public static boolean isThisAFinalDeal(ArrayList<LinkedList> player,int maxDeals) {
        for (LinkedList players:player) {
            if (players.getSize()!=maxDeals){//if all the players have the same amount of cards at that moment
                return false;
            }
        }return true;

    }

    public static int numberOfMaxDeals(ArrayList<LinkedList> players){
        int maximumNumberOfDeals=0;//taking the count of all the cards for each players
        for (LinkedList player:players) {
            if (player.getSize()>maximumNumberOfDeals){//if the hand has more cards than the maximum deals then this is the new maximumNumber of deals is available
                maximumNumberOfDeals = player.getSize();
            }
        }
        return maximumNumberOfDeals;//this is the amount of cards other hands also needs to have inorder to complete the final deal for that round, then they can sort the cards until that final deal.
    }

    public static void displayDeal(ArrayList<LinkedList> players) {
        for (int i = 0; i < players.size(); i++) {
            LinkedList playerHand = players.get(i); //gets the players hand from the list of players hands
            System.out.print("Player " + (i+1) + ": ");
            for (int cards = 0; cards < playerHand.getSize(); cards++) {//displays each card in the hand after dealing a card to a player
                String cardDetails = playerHand.get(cards).value.displayCard();
                System.out.print(cardDetails + "  ");
            }
            System.out.println();
        }
    }

    public static void sortSuit(ArrayList<LinkedList> players) {
        for (int i=0;i<players.size();i++ ) {
            LinkedList playerHand = players.get(i);//gets each players hand
            ArrayList<String> order = playerHand.suitOrder();//gets the order of the suit
            Card[] playerHandSorted = new Card[playerHand.iterator()];//creates an array of Card object to store the player cards

            for (int j = 0; j < playerHandSorted.length; j++) {
                Card card = playerHand.get(j).value;//populates the array of cards
                playerHandSorted[j] = card;
            }

            for (String suitOrder : order) {//Sort by suit, using bubble sort algorithm as its a straightforward and stable algortihm, that has a lower time complextity O(n^2), therefore its more suited to handle small and meduim lists like the card Array
                for (int outer = 0; outer < playerHandSorted.length; outer++) {
                    for (int inner = 0; inner < playerHandSorted.length - 1; inner++) {
                        if (playerHandSorted[inner].getSuit().equals(suitOrder) && !playerHandSorted[inner + 1].getSuit().equals(suitOrder)) {//checks if the current suit equals to the order of suit entrerd, and if the next suit is not of the same suit then we swap both the elements, repeats untill all elements are checked
                            Card temp = playerHandSorted[inner];
                            playerHandSorted[inner] = playerHandSorted[inner + 1];
                            playerHandSorted[inner + 1] = temp;
                        }

                    }
                }
            }
            for (int j = 0; j < playerHandSorted.length; j++) {//sort by Rank
                for (int k = 0; k < playerHandSorted.length - 1; k++) {
                    if ((playerHandSorted[k].getSuit().equals(playerHandSorted[k + 1].getSuit()))) {//only sorts if both are of same suit
                        if ((playerHandSorted[k].rankValue(playerHandSorted[k].getRank())) > (playerHandSorted[k + 1].rankValue(playerHandSorted[k + 1].getRank()))) {//gets the value of the rank and sorts it based n its rank value 2 being lowest while Ace is the highest
                            Card temp = playerHandSorted[k];
                            playerHandSorted[k] = playerHandSorted[k + 1];
                            playerHandSorted[k + 1] = temp;
                        }
                    }

                }
            }

            System.out.print("Player " + (i+1) + ": ");//once sorted the Card array is being displayed to the user
            for (Card sorted : playerHandSorted) {
                System.out.print(sorted.displayCard() + "  ");
            }
            System.out.println();
        }
    }
}



