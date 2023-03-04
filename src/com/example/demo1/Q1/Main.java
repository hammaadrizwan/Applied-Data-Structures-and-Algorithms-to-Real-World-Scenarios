package com.example.demo1.Q1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of transactions: ");
        int numberOfTransaction = input.nextInt();
        Queue<Share> stock = new Queue<>(numberOfTransaction); // creates a Stock of Queue Data type which stores only values of the Share datatype

        boolean allSharesSold=false;//checks if all the shares are sold
        int exsistingShares=0;//keeps a track of all the avaliable shares
        int capGain = 0;// initially capital gain is 0 since no transaction has been made
        for (int index = 0;index<numberOfTransaction;index++){
            System.out.println("");
            System.out.print("Buy or sell: ");//gets the option from the user whether to buy or sell
            String option = input.next().toLowerCase().strip();
            System.out.print(">> Number of shares: ");
            int amountOfShares = input.nextInt();
            System.out.print(">> Unit price: $");
            int price = input.nextInt();// gets the number of shares and the unit price
            if (option.equals("buy")){// if the option is to buy then it will be inserted to the Queue
                Share share = new Share(amountOfShares,price);//Creates a object of Share from the values passed by the user,
                stock.insert(share);//inserts the Share to the stock
                exsistingShares+=share.size;//adds the number of shares to the exsisting shares
            }else {
                while (!allSharesSold){//in order to find the capital gain all shares need to be sold if the operation is to sell
                    if (exsistingShares>=amountOfShares){//if the exsisiting share is more than the shares need to sell then user is able to sell there shares
                        Share removedShare = stock.remove();//stores the value of the removed share
                        if (amountOfShares<=removedShare.size){ //if the amount of shares are lower than the removed share it means that this is the balance amount of shares that needs to be sold
                            capGain+=amountOfShares*(price - removedShare.price);//the balance amount of shares is multipled by the price difference to get the capital gain.
                            allSharesSold=true;//all shares which needs to be sold is complete
                        }else{
                            capGain+= removedShare.size*(price - removedShare.price);//if not capital gain is calculated from the amount of the removed share by its price difference
                        }
                        amountOfShares-=removedShare.size;//once share is sold it is deducted from the total number of shares need to be sold. to get a count of the balance number of shares
                        exsistingShares-=removedShare.size;//exsisting number of shares are being deducted when a share is sold as it goes out of their stock

                    }else{
                        System.out.println("Not enough shares to sell");//if there are not enough shares to sell, the user will be notified
                        break;
                    }
                }

            }

        }
        System.out.println(" ");
        System.out.println("Capital Gain: $"+capGain);//displays the capital gain in the end



    }


}