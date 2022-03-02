package com.yagmur.javalearning;

public class Loops {
    public static void main(String[] args){
        int[] mynum = {1,2,3,4};
        for(int i = 0; i< mynum.length; i++){
            System.out.println(mynum[i]);
        }
        for(int number: mynum){
            System.out.println(number);
        }
        int j= 0;
        while( j < 10){
            j++;
            System.out.println(j);
        }

    }
}
