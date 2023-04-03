package com.example.demo1.Q1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        int numberOfTransaction;
        while (true) {
            System.out.println("Enter the number of transactions");
            String userInput = input.nextLine();

            try {
                numberOfTransaction = Integer.parseInt(userInput);
                break; // Exit the loop if input is valid
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }

        Queue<Share> stock = new Queue<>(numberOfTransaction); // creates a Stock of Queue Data type which stores only values of the Share datatype
        boolean allSharesSold=false;//checks if all the shares are sold
        int exsistingShares=0;//keeps a track of all the avaliable shares
        int capGain = 0;// initially capital gain is 0 since no transaction has been made
        String option;

        for (int index = 0;index<numberOfTransaction;index++){
            System.out.println("");
            while(true){//Exception Handelling for each inpput.. option,noOfshares and price
                System.out.print("Buy or sell: "); // gets the option from the user whether to buy or sell
                option = input.nextLine().toLowerCase().strip();
                try{
                    if(!(option.equals("buy")||option.equals("sell"))){
                        throw new InputMismatchException();
                    }else{
                        break;
                    }
                }catch (InputMismatchException e){
                    System.out.println();
                    System.out.println("Invalid option, enter 'Buy' or 'sell'");
                }
            }

            int amountOfShares;//this repaeats until the user enter the correct data type
            while (true) {
                System.out.print(">> Number of shares: ");
                String userInput = input.nextLine();

                try {
                    amountOfShares = Integer.parseInt(userInput);
                    break; // Exit the loop if input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
            }

            int price;
            while (true) {
                System.out.print(">> Unit price: $");
                String userInput = input.nextLine();

                try {
                    price = Integer.parseInt(userInput);// gets the number of shares and the unit price
                    break; // Exit the loop if input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
            }

            if (option.equals("buy")) {// if the option is to buy then it will be inserted to the Queue
                Share share = new Share(amountOfShares, price);//Creates a object of Share from the values passed by the user,
                stock.insert(share);//inserts the Share to the stock
                exsistingShares += share.size;//adds the number of shares to the exsisting shares
            } else if (option.equals("sell")) {
                while (!allSharesSold) {//in order to find the capital gain all shares need to be sold if the operation is to sell
                    if (exsistingShares >= amountOfShares) {//if the exsisiting share is more than the shares need to sell then user is able to sell there shares
                        Share removedShare = stock.remove();//stores the value of the removed share
                        if (amountOfShares <= removedShare.size) { //if the amount of shares are lower than the removed share it means that this is the balance amount of shares that needs to be sold
                            capGain += amountOfShares * (price - removedShare.price);//the balance amount of shares is multipled by the price difference to get the capital gain.
                            allSharesSold = true;//all shares which needs to be sold is complete
                        } else {
                            capGain += removedShare.size * (price - removedShare.price);//if not capital gain is calculated from the amount of the removed share by its price difference
                        }
                        amountOfShares -= removedShare.size;//once share is sold it is deducted from the total number of shares need to be sold. to get a count of the balance number of shares
                        exsistingShares -= removedShare.size;//exsisting number of shares are being deducted when a share is sold as it goes out of their stock

                    } else {
                        System.out.println("Not enough shares to sell");//if there are not enough shares to sell, the user will be notified
                        break;
                    }
                }

            }
        }
        //Once done the Capital gain after all transactions hace been displayed
        System.out.println(" ");
        System.out.println("Total Capital Gain: $"+capGain);



    }


}