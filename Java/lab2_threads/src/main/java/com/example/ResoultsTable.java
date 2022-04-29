package com.example;

import java.util.ArrayList;
import java.util.List;

public class ResoultsTable {
    private List<Resoult> resoults = new ArrayList<>();

    public synchronized void addResoult(Resoult newResoult) {
        //System.out.println("Obliczono, ze " + newResoult.getNumber() + " prime: "+newResoult.isPrime());
        resoults.add(newResoult);
    }
}
