package com.example.demo1.Q2A;

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

    public String displayCard(){
        return(rank + " "+suit);
    }//returns the card info
    public int rankValue(String rank) {// converts the String to a integer of its repective value
        int rankValue;
        switch (rank) {
            case "Ace" -> rankValue = 14;
            case "King" -> rankValue = 13;
            case "Queen" -> rankValue = 12;
            case "Jack" -> rankValue = 11;
            default -> rankValue = Integer.parseInt(rank);
        }
        return rankValue;
    }
}