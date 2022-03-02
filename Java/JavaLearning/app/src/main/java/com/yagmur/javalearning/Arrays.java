package com.yagmur.javalearning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Arrays {
    public static void main(String[] args){
        String[] myStrArr = new String[3];
        myStrArr[0] = "James";
        int[] numbers = {1,2,3};
        System.out.println(numbers[0]);

        ArrayList<String> myMusicians = new ArrayList<>();
        myMusicians.add("James");
        myMusicians.add(0,"Kirk");
        System.out.println(myMusicians.get(0)); //Kirk

        HashSet<String> mySet = new HashSet<>();
        mySet.add("James");
        mySet.add("James");
        mySet.size(); //1

        HashMap<String, String> myHashMap = new HashMap<>();
        myHashMap.put("name", "James");
        myHashMap.put("instrument","Guitar");
        myHashMap.get("name"); //James
        //cannot give primitive variable for int -> Integer

    }
}
