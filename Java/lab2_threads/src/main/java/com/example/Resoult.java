package com.example;

public class Resoult {
    private int number;
    private boolean isPrime;
    private boolean isComputed;

    public Resoult(int number) {
        this.number = number;
        this.isComputed = false;
        this.isPrime = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
        isComputed = true;
    }

}
