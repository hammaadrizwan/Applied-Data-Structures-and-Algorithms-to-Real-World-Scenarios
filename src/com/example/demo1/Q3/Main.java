package com.example.demo1.Q3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        BinaryTree binaryTree;
        String data;
        binaryTree = readFromFile("src/com/example/demo1/Q3/data.txt");// reads the words from the text file and populates the tree accordingly

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the word to find");
        data = input.next();
        while(!data.equals("q")){
            if(!spellChecker(binaryTree,data).equals("Not Found")){;//if the word is found then display else find the word that was meant to be searched.
                System.out.println(spellChecker(binaryTree,data));
            }else {
                ArrayList<String> possibleWords = removeCharacter(binaryTree,data);
                for (String word:possibleWords) {
                    System.out.println(word);
                }
            }
            data = input.next();
        }
    }

    public static BinaryTree readFromFile(String filename) throws FileNotFoundException {
        BinaryTree binaryTree = new BinaryTree();
        File file = new File(filename);
        Scanner readFile = new Scanner(file);
        while (readFile.hasNextLine()) {//until end of file the data is being added to the tree.
            String data = readFile.nextLine();
            binaryTree.insert(data.trim());
        }
        readFile.close();
        return binaryTree;
    }

    public static String spellChecker(BinaryTree binaryTree,String value){
        if(binaryTree.find(value)!=null){
            return value;
        }
        return "Not Found";
    }

    public static ArrayList<String> removeCharacter(BinaryTree binaryTree, String data){
        ArrayList<String> possibleWords = new ArrayList<>();
        for(int i = 0;i<data.length();i++){
            String newWord = data;
            String letter= String.valueOf(data.charAt(i));
            newWord = newWord.replace(letter,"");
            if (binaryTree.find(newWord)==(null)){
                continue;
            }else {
                possibleWords.add(newWord);
            }

        }
        return possibleWords;
    }
}
