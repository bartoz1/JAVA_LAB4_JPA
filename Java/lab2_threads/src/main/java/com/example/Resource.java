package com.example;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    private List<Task> tasks = new ArrayList<>();

    public synchronized Task take() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait();
        }
        Task outTask = tasks.remove(0);
        return outTask;
    }

    public synchronized void put(Task newTask) {
        tasks.add(newTask);
        notifyAll();
    }
    public synchronized void put(int newPossiblePrime) {
        Task newTask = new Task(newPossiblePrime);
        tasks.add(newTask);
        notifyAll();
    }


}
