package com.example;

public class Task {
    private int number;

    public Task(int number) {
        this.number = number;
    }

    public Resoult compute() {
        Resoult resoult = new Resoult(number);
        int upperLimit = (int) Math.sqrt(number);

        resoult.setPrime(true);

        for(int i=2; i<=upperLimit; i++) {
            if (number % i == 0) {
                resoult.setPrime(false);
            }
        }

        return resoult;

    }


}
