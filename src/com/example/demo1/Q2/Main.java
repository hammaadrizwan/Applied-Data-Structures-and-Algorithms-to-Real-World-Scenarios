package com.example.demo1.Q2;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList playerOneHand = new LinkedList();
        LinkedList playerTwoHand = new LinkedList();
        LinkedList playerThreeHand = new LinkedList();
        LinkedList playerFourHand = new LinkedList();

        Scanner input = new Scanner(System.in);
        String functionOption;
        int playerChoice;
        int cardDeck = 52;

        do{
            System.out.println("Enter a function:\nPress (A) to add a card to a player\nPress (P) to play a card\nPress (I) to get the count of all cards in a players hand\nPress (S) to get a count of all the suits available to a player\nPresss (Q) to quit.");
            functionOption = input.next().toUpperCase();

            switch (functionOption){
                case "A"://Adding a card
                    System.out.println("to which Player?\nPress (1) to add a card to Player 1\nPress (2) to add a card to Player 2\nPress (3) to add a card to Player 3\nPress (4) to add a card to Player 4");
                    playerChoice = input.nextInt();
                    if (playerChoice== 1){
                        System.out.println("Enter a rank and suit");
                        System.out.println("Rank:");
                        String rank = input.next();
                        System.out.println("Suit:");
                        String suit = input.next();
                        if (cardDeck>0){
                            playerOneHand.addACard(rank,suit);
                            playerOneHand.displayList();
                            System.out.println();
                            cardDeck--;
                        }else{
                            System.out.println("Card Deck is empty");
                        }
                    } else if (playerChoice== 2) {
                        System.out.println("Enter a rank and suit");
                        System.out.println("Rank:");
                        String rank = input.next();
                        System.out.println("Suit:");
                        String suit = input.next();
                        if (cardDeck > 0) {
                            playerTwoHand.addACard(rank, suit);
                            playerTwoHand.displayList();
                            System.out.println();
                            cardDeck--;
                        }else{
                            System.out.println("Card Deck is empty");
                        }
                    } else if (playerChoice== 3) {
                        System.out.println("Enter a rank and suit");
                        System.out.println("Rank:");
                        String rank = input.next();
                        System.out.println("Suit:");
                        String suit = input.next();
                        if (cardDeck > 0) {
                            playerThreeHand.addACard(rank, suit);
                            playerThreeHand.displayList();
                            System.out.println();
                            cardDeck--;
                        }else{
                            System.out.println("Card Deck is empty");
                        }

                    }else if (playerChoice== 4){
                        System.out.println("Enter a rank and suit");
                        System.out.println("Rank:");
                        String rank = input.next();
                        System.out.println("Suit:");
                        String suit = input.next();
                        if (cardDeck>0){
                            playerFourHand.addACard(rank,suit);
                            playerOneHand.displayList();
                            System.out.println();
                            cardDeck--;
                        }else{
                            System.out.println("Card Deck is empty");
                        }
                    }else{
                        System.out.println("Invalid Option");
                    }
                    break;
                case "P"://Playing a card
                    System.out.println("from which Player?\nPress (1) to add a card to Player 1\nPress (2) to add a card to Player 2\nPress (3) to add a card to Player 3\nPress (4) to add a card to Player 4");
                    playerChoice = input.nextInt();
                    if (playerChoice== 1){
                        System.out.println("Enter a suit");
                        String suit = input.next();
                        if (playerOneHand.getSize()>0){
                            Card played = playerOneHand.playACard(suit);
                            System.out.println("Card Played "+played);
                        }else{
                            System.out.println("No card available to play");
                        }
                    } else if (playerChoice== 2) {
                        System.out.println("Enter a suit");
                        String suit = input.next();
                        if (playerTwoHand.getSize()>0){
                            Card played = playerTwoHand.playACard(suit);
                            System.out.println("Card Played "+played);
                        }else{
                            System.out.println("No card available to play");
                        }
                    } else if (playerChoice== 3) {
                        System.out.println("Enter a suit");
                        String suit = input.next();
                        if (playerThreeHand.getSize()>0){
                            Card played = playerThreeHand.playACard(suit);
                            System.out.println("Card Played "+played);
                        }else{
                            System.out.println("No card available to play");
                        }
                    }else if (playerChoice== 4){
                        System.out.println("Enter a suit");
                        String suit = input.next();
                        if (playerFourHand.getSize()>0){
                            Card played = playerFourHand.playACard(suit);
                            System.out.println("Card Played "+played);
                        }else{
                            System.out.println("No card available to play");
                        }
                    }else{
                        System.out.println("Invalid Option");
                    }
                    break;
                case "I":
                    System.out.println("from which Player?\nPress (1) to add a card to Player 1\nPress (2) to add a card to Player 2\nPress (3) to add a card to Player 3\nPress (4) to add a card to Player 4");
                    playerChoice = input.nextInt();
                    if (playerChoice== 1){
                        System.out.println("Enter a suit");
                        String suit = input.next();
                        if (playerOneHand.getSize()>0){
                            Card played = playerOneHand.playACard(suit);
                            System.out.println("Card Played "+played);
                        }else{
                            System.out.println("No card available to play");
                        }
                    } else if (playerChoice== 2) {
                        System.out.println("Enter a suit");
                        String suit = input.next();
                        if (playerTwoHand.getSize()>0){
                            Card played = playerTwoHand.playACard(suit);
                            System.out.println("Card Played "+played);
                        }else{
                            System.out.println("No card available to play");
                        }
                    } else if (playerChoice== 3) {
                        System.out.println("Enter a suit");
                        String suit = input.next();
                        if (playerThreeHand.getSize()>0){
                            Card played = playerThreeHand.playACard(suit);
                            System.out.println("Card Played "+played);
                        }else{
                            System.out.println("No card available to play");
                        }
                    }else if (playerChoice== 4){
                        System.out.println("Enter a suit");
                        String suit = input.next();
                        if (playerFourHand.getSize()>0){
                            Card played = playerFourHand.playACard(suit);
                            System.out.println("Card Played "+played);
                        }else{
                            System.out.println("No card available to play");
                        }
                    }else{
                        System.out.println("Invalid Option");
                    }
                    break;
                case "S":
                    System.out.println("Suit Iterator");
                    break;
                case "Q":
                    System.out.println("Program terminated");
                    break;
                default:
                    System.out.println("Invalid Option");
            }

        }while (!functionOption.equals("Q"));
    }
}