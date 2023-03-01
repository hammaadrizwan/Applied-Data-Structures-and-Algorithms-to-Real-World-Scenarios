package com.example.demo1.Q1;

public class Stock {
    private LinkedList<Share> shares; // a stock is a collection of shares

    public Stock() {
        shares = new LinkedList<>(); // a linked list is used to store all the shares the owner has
    }
    // Stock has methods such as buy, sell and calculate the Total Capital Gain.
    public void buyShare(int day, int price, int quantity) {  // to buy a share it takes the date, price and quantity of the share; for example on day 1, 100 shares were bought at $20 per share.
        for (int index = 0; index < quantity; index++) { //this will create a new object of Share and add the date and price of that specific share. if 2 shares where bought on day one for $5 then the shares list would
            Share share = new Share(day, price);// look like this: [((1,5),1),((1,5),null)]
            shares.enqueue(share);// to add the share into the end of the list we use the enqueue method frim our linkedList class.
        }
    }

    public int sell(int quantity, int price) {
        if (shares.size() < quantity) { // inorder to sell a share we have to check how many shares we have in our Stock, if we  have less number of shares in our stock than the amount we want to sell, the program will
            System.out.println("Insufficient number of shares in stock, cannot sell");// display a message to the user saying insufficient number of shares in stock
            return 0;//returns 0 as no share has been sold
        }

        int capitalGain = 0; //initially the capital gain is zero
        for (int index = 0; index < quantity; index++) {
            Share share = shares.dequeue(); // until the quantity of shares need to be sold are met the capital gain is being calculated from subtracting the selling price of the share form the price it was bought.
            capitalGain += price - share.price;// this will be repeated until all the shares are being removed from the list of shares available
        }

        return capitalGain;//this will return the total capital gain.
    }

    public int totalCapitalGain(String[][] listOfTransactions) {
        int totalGain = 0;
        for (String[] transaction : listOfTransactions) {
            String option = String.valueOf(transaction[0]);
            int quantity = Integer.parseInt(transaction[1]);
            int price = Integer.parseInt(transaction[2]);

            if (option.equals("+")) {
                int day = shares.size() + 1;
                buyShare(day, price, quantity);
            } else if (option.equals("-")) {
                totalGain += sell(quantity, price);
            }
        }

        return totalGain;
    }

    private static class Share {
        int day;
        int price;

        Share(int day, int price) {
            this.day = day;
            this.price = price;
        }
    }

    private class LinkedList<Share> {
        private Node<Share> front;
        private Node<Share> rear;

        public void enqueue(Share share) {
            Node<Share> newShare = new Node<Share>(share);
            if (rear == null) {
                front = rear = newShare;
            } else {
                rear.next = newShare;
                rear = newShare;
            }
        }

        public Share dequeue() {
            if (front == null) {
                return null;
            } else {
                Share share = front.element;
                front = front.next;
                if (front == null) {
                    rear = null;
                }
                return share;
            }
        }
        public int size() {
            int count = 0;
            Node current = front;
            while (current != null) {
                count++;
                current = current.next;
            }
            return count;
            }

        }

        private static class Node<Share> {
            Share element;
            Node<Share> next;

            Node(Share element) {
                this.element = element;
            }
        }
}