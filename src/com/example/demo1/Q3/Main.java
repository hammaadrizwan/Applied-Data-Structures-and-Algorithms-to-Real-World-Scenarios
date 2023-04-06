package com.example.demo1.Q3;

import com.example.demo1.Q2B.BinaryTree;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        dictionary.loadData();

        while(true){
            System.out.print("Enter the word to find: ");//asks the user to enter teh word to find
            String data = input.next();

            if (data.equals("q")){break;} //exit condition

            ArrayList<String> listContainingWords = spellChecker(dictionary,data);//stores the list of words returned from the spellchecker

            if (listContainingWords.get(0).equals("suggestions>>")){ // the word is incorrect, the list of words will start with the string"suggestions>>", to signal its incorrect
                if(listContainingWords.size()>1){//if that list has more than one element it means there are suggesstions,
                    System.out.print("Did you mean?  ");
                    for (String possibleWord:spellChecker(dictionary,data)) {
                        if (possibleWord.equals("suggestions>>")){//prints all the words after suggesttions
                            continue;
                        }
                        System.out.print(possibleWord.toLowerCase()+" ");
                    }
                    System.out.println();
                }else{//if not there are no suggesstions
                    System.out.println("Word is incorrect,no suggestions found");
                }
            }else {//if the list isnot a suggesstions list then print the word, since it is corect
                String wordFound = listContainingWords.get(0);
                System.out.println(wordFound);
            }
            System.out.println();
        }

    }
    public static ArrayList<String> spellChecker(Dictionary dictionary, String value){
        ArrayList<String> listOfWords = new ArrayList<>();//creates a listOfWords array
        if(dictionary.findWord(value)==null){// if word is not found
            if(findPossibleWords(dictionary,value).size()>0){//finds all the possible words
                listOfWords = findPossibleWords(dictionary, value);//adds the possible words to the list ofwords
            }//returns the list of all the possiblewords
            return listOfWords;
        }
        else {
            listOfWords.add(dictionary.findWord(value));//adds the word to the list of words. as requried
            return (listOfWords);
        }
    }

    public static ArrayList<String> findPossibleWords(Dictionary dictionary,String data){
        ArrayList<String> allPossibleWords = new ArrayList<>();// this stores all the possible words that could have been meant
        ArrayList<String> removeCharacterPossibleWords =removeCharacter(dictionary,data);//loads the list generated from each method
        ArrayList<String> insertCharacterPossibleWords =insertCharacter(dictionary,data);
        ArrayList<String> swapCharacterPossibleWords =swapCharacter(dictionary,data);
        ArrayList<String> replaceCharacterPossibleWords =replaceCharacter(dictionary,data);
        ArrayList<String> phoneticPossibleWords = phoneticSubstitution(dictionary,data);

        allPossibleWords.add("suggestions>>");//since allof thesse are suggesstions the list returned will have the first element with the tag "suggesstions"
        allPossibleWords.addAll(removeCharacterPossibleWords);
        allPossibleWords.addAll(insertCharacterPossibleWords);
        allPossibleWords.addAll(swapCharacterPossibleWords);
        allPossibleWords.addAll(replaceCharacterPossibleWords);//combines all the words from the methods and removes the duplicates
        allPossibleWords.addAll(phoneticPossibleWords);

        ArrayList<String> allPossibleWordsRemovedDuplicates = new ArrayList<>();
        for (String word:allPossibleWords) {
            if(!allPossibleWordsRemovedDuplicates.contains(word)){//if the new list contains the word, thene dont insert it
                allPossibleWordsRemovedDuplicates.add(word);
            }
        }
        return allPossibleWordsRemovedDuplicates; //returns all the possibilities
    }


    //REMOVE CHARACTER METHOD
    public static ArrayList<String> removeCharacter(Dictionary dictionary, String data){//takes the tree and data as parameters like other functions
        ArrayList<String> possibleWords = new ArrayList<>();
        for(int i = 0;i<data.length();i++){//for each letter in the word
            char[] letters = data.toCharArray();
            letters[i]=' ';//converts that letter to a blank
            String removedLetter = new String(letters);//then creates a new array which is a combination of the list of characters
            String newWord = removedLetter.replace(" ","");//using the replace command to replace the space with no space,and stores this as a new word
            if (dictionary.findWord(newWord)!=(null)) {//we find if this new word is in the binary tree
                possibleWords.add(newWord);//if its availalble we add to the list of possible words
            }
        }
        return possibleWords;
    }

    //INSERT CHARACTER METHOD
    private static char[] addLetter(int position, char[] letters, char letterToAdd) {//this function is only accessible to the insert character method,it takes the values from that function call
        char[] newArray = new char [letters.length+1];// new array with an additional slot,since we have to store this new letter.

        for (int outerLoop = 0, innerLoop = 0; outerLoop < letters.length; outerLoop++, innerLoop++) {//we have to copy the values from the prevous array and then if the positon is hit, then we add the character we need to the array,and continue copying the rest of the characters
            if (outerLoop == position && outerLoop!=letters.length) {
                newArray[innerLoop++] = letterToAdd;//inserting the new value to the correct slot
            }
            newArray[innerLoop] = letters[outerLoop];//copying the value from the original list
        }
        if (position == letters.length) {//if the index that we need to insert the value is in the last position
            newArray[letters.length] = letterToAdd;//we add it to the correct positon in the new array, this condition is not being met by the for loop therefore its used here.
        }

        return newArray;
    }

    public static ArrayList<String> insertCharacter(Dictionary dictionary, String data){
        ArrayList<String> possibleWords = new ArrayList<>();

        ArrayList<Character> characters = new ArrayList<>();//creates a list of characters from the aplhpabet
        for(char character = 'a'; character <= 'z'; character++){
            characters.add(character);
        }

        char[] exsistingLetters=data.toCharArray();//converts the word to a list of letters
        for (int index = 0; index <= exsistingLetters.length; index++) {// for each index in the word
            for (Character character: characters) {//test with all the characters
                char newLetter = character;
                char[] addedNewLetter = addLetter(index,exsistingLetters,newLetter);//calls the addLetter fucntion to add the character to the relavent index position
                String newWord= new String(addedNewLetter);//converts the new list to a new word
                if (dictionary.findWord(newWord)!=(null)) {//if its found in the binary tree then this  could be a possible word
                    possibleWords.add(newWord);
                }
            }
        }
        return possibleWords;
    }

    //SWAP CHARACTER METHOD
    public static ArrayList<String> swapCharacter(Dictionary dictionary, String data){
        ArrayList<String> possibleWords = new ArrayList<>();
        char[] letters = data.toCharArray();
        int inner=0;
        int swaps=0;//this records the number of swaps
        while (swaps<letters.length){
            letters = data.toCharArray();//each time its sapped it resets to the original state to check if the word is in the BST.
            if (inner==letters.length-1){//if j is one before the last index, then it swaps the last element with the before element, if we use the normal approach we get index out of bounds
                char temp = letters[inner];
                letters[inner]=letters[letters.length-1];
                letters[letters.length-1]=temp;
            }else{
                char temp = letters[inner];
                letters[inner]=letters[inner+1];
                letters[inner+1]=temp;
            }
            swaps++;//swaps increments by one
            inner=swaps;//resets the innerloop to be the number of swaps, therby the inner value doesnt go out of bounds
            String newWord= new String(letters);
            if (dictionary.findWord(newWord)!=(null)) {//if its found in the binary tree then this  could be a possible word
                possibleWords.add(newWord);
            }
        }
        return possibleWords;
    }

    //REPLACE CHARACTER METHOD
    public static ArrayList<String> replaceCharacter(Dictionary dictionary, String data) {
        ArrayList<String> possibleWords = new ArrayList<>();
        ArrayList<Character> characters = new ArrayList<>();
        for (char character = 'a'; character <= 'z'; character++) {//same as inserting a character to the word, we generate all the possible characters
            characters.add(character);
        }
        char[] letters = data.toCharArray();
        for (int i = 0; i < letters.length; i++) {// for each index in the word,
            for (int j = 0; j < characters.size(); j++) {//generate all the possible characters that could have been entered
                letters[i] = characters.get(j);//sets the new chracter to the letters at that index position
                String newWord = new String(letters);
                if (dictionary.findWord(newWord) != (null)) {//checks if that is a word, does this untill all the characters are being entered to all the positions of the word
                    possibleWords.add(newWord);
                }
            }
        }
        return possibleWords;
    }

    public static ArrayList<String> phoneticSubstitution(Dictionary dictionary, String data) {
        ArrayList<String> listContainingWords = dictionary.findCode(Metaphone.metaphone(data));
        return listContainingWords;
    }
}


