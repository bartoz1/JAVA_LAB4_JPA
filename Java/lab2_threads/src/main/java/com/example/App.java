package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class App {
    private static List<Thread> threads = new ArrayList<>();
    private static ResoultsTable resoultsTable = new ResoultsTable();
    private static Resource resources = new Resource();

    public static void main( String[] args ) throws InterruptedException {
        int threadsCount = Integer.parseInt(args[0]);


        // creating and starting threadsCount threads
        for (int i=0; i<threadsCount; i++) {
            Thread newThread = new Thread(new Worker(resoultsTable, resources));
            threads.add(newThread);
            newThread.start();
        }
        // adding random numbers as tasks for threads
        for (int i=0; i<20; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(50, 500000);
            resources.put(randomNum);
        }

        Scanner scanner = new Scanner(System.in);
        boolean terminate = false;

        printMenu();

        // handling input
        while(!terminate && (scanner.hasNext())) {
            switch (scanner.next()) {
                case "1":
                    System.out.print("Number to check: ");
                    int number = scanner.nextInt();
                    resources.put(number);
                    printMenu();
                    break;

                case "2":
                    terminate = true;
                    System.out.println("Program terminated");
                    break;

                default:
                    System.out.println("Wrong command! Try again!");
                    printMenu();
            }
        }

        scanner.close();
        for (Thread thread : threads) {
            thread.interrupt();
            thread.join();
        }


    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1 - add number to check if prime");
        System.out.println("2 - terminate program");
    }
}
