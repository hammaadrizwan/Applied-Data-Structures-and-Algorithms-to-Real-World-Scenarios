package com.example.demo1.Q3;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
    HashMap<String, String> map = new HashMap<>();

    public void loadData() throws FileNotFoundException {
        File file = new File("src/com/example/demo1/Q3/data.txt");
        Scanner readFile = new Scanner(file);
        while (readFile.hasNextLine()) {//until end of file the data is being added to the tree.
            String data = readFile.nextLine();
            String wordName = data.trim();
            String code = Metaphone.metaphone(wordName);
            this.map.put(wordName,code);
        }
        readFile.close();
    }

    public ArrayList<String> findCode(String code){
        int count = 0;
        ArrayList<String> possiblePhoneticSubstitutions = new ArrayList<>();
        for (Map.Entry<String,String> entry: map.entrySet()){
            if(entry.getValue().equals(code)&&count<5){
                possiblePhoneticSubstitutions.add(entry.getKey());
                count++;
            }
        }
        return possiblePhoneticSubstitutions;
    }


    public String findWord(String target){
        for (Map.Entry<String,String> entry: map.entrySet()){
            if (entry.getKey().equals(target)){
                return entry.getKey().toString();
            }
        }
        return null;
    }





}
