package com.example.demo1.Q2;

public class Card {
    public String rank;
    public String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public void displayCard(){
        System.out.println(rank + " "+suit);
    }
    public int rankValue(String rank) {
        if(rank.equals("Ace")){
            return 14;
        } else if (rank.equals("King")) {
            return 13;
        } else if (rank.equals("Queen")) {
            return 12;
        } else if (rank.equals("Jack")) {
            return 11;
        }
        int rankValue = Integer.parseInt(rank);
        if (rankValue>=2 && rankValue<=10){
            return rankValue;
        }
        return -1;
    }
}