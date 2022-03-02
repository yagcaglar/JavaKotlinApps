package com.yagmur.javalearning;

public class Statements {
    public static void main(String[] args){
        int x = 5;
        int y = 30;
        //x++ works
        // and && or ||

        if(y>x){
            System.out.println("y is greater");
        }else if(x>y){
            System.out.println(("x is greater"));
        }else{
            System.out.println("equal");
        }

        int day = 1;
        String dayStr = "";
        switch(day){
            case 1:
                dayStr = "Monday";
                break;
            case 2:
                dayStr = "Tuesday";
                break;
            default:
                dayStr = "Sunday";
                break;
        }
        System.out.println(dayStr);

    }
}
