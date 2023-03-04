package com.example.demo1.Q1;

public class Queue<Type> {
    private Type[] data;//creates an array of the Type passed in declaration.... making it available for any datatype(String,int,also custom data type, Share)

    private static final int DEFAULT_SIZE = 10;//default queue size is 10, if user didnt input

    int end = 0;//only used end node, to add item in the end

    public Queue(int size) {//constructor class to create a QUEUE of the size passed by the user
        this.data = (Type[]) new Object[size];
    }
    public Queue(){ //incase if the user had forgotton to input a size for the queue a default size would be passed which is 10.
        this(DEFAULT_SIZE);
    }

    public boolean isFull() {//checks if the queue is full
        return end == data.length; // returns true if the end pointer is equal to the size of the queue,meaning theres no remaining space
    }

    public boolean isEmpty() {//same as full, but checks if the end pointer is equal to zero,meaining no data has been entered
        return end == 0;
    }

    public boolean insert(Type item) {//takes in a parameter from the user..
        if (isFull()) { //checks if the queue is full
            System.out.println("Queue is full");
            return false;
        }
        data[end++] = item;//increments the end pointers and inserts the item to the new end pointers location O(1)
        return true;
    }

    public Type remove() {
        if (isEmpty()) {
            System.out.println("Stock is empty");//if Queue is empty.
        }

        Type removed = data[0]; //removes the data in the first position of the array, and stores it in its relavent dataType passed by the user.

        // shift the elements to left then its easy to Remove the next element from the array and keep space for any new possible items that will be added.
        for (int index = 1; index < end; index++) {//until index is less than end, all the data will shift one position to the left.
            data[index-1] = data[index];
        }
        end--;//end decrements as there is one free space in the Queue after removing an element
        return removed; //returns the value to the user.
    }

    public Type front() throws Exception{ //to check the first element in the array.
        if (isEmpty()) {
            System.out.println("Stock is empty");//if array is empty then user will get a message saying the stock is empty.
        }
        return data[0];// returns the first element in the array of the Type given by the user.
    }


    }





