package com.example;

public class Worker implements Runnable {
    private ResoultsTable resoultsTable;
    Resource resources;

    public Worker(ResoultsTable resoultsTable, Resource resource){
        this.resoultsTable = resoultsTable;
        this.resources = resource;
    }
    public void run() {
        while(!Thread.interrupted()) {
            try {
                Task task = resources.take();
                Thread.sleep(2000);
                Resoult resoult = task.compute();
                System.out.println("Obliczono, ze " + resoult.getNumber() + " prime: "+resoult.isPrime());
                resoultsTable.addResoult(resoult);

            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
