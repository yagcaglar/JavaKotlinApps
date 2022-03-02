package com.yagmur.oop;

//inheritance
public class SuperMus extends Musician{

    //as musicians need constructor this also needs const
    public SuperMus(String name, String instrument, int age) {
        super(name, instrument, age);
    }

    public void sing(){
        System.out.println("I am super");
    }
}
