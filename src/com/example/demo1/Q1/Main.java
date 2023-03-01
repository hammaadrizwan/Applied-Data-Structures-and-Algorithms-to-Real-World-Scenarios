package com.example.demo1.Q1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of transactions: ");
        int noOfTransactions = input.nextInt();
        String transactions[][]= new String[noOfTransactions][];
        for (int index = 0;index<noOfTransactions;index++){
            System.out.println("Enter the transaction\n Press + to Buy\n Press - to Sell");
            String option = input.next().toString().strip();
            System.out.println("Enter the amount of share: ");
            int shares = input.nextInt();
            System.out.println("Enter the price per share: ");
            int price = input.nextInt();
            String transaction[] = {option,String.valueOf(shares),String.valueOf(price)};
            transactions[index] = transaction;
            System.out.println("");
        }

        Stock stock = new Stock();
        int totalCapitalGain = stock.totalCapitalGain(transactions);
        System.out.println("Total Capital Gain: $" + totalCapitalGain);
    }


}