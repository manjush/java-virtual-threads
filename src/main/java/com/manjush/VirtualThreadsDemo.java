package com.manjush;

import java.util.concurrent.Executors;

public class VirtualThreadsDemo {

    public static void main(String[] args) throws InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 5; i++) {
                int threadNumber = i;
                executor.submit(() -> {
                    System.out.println("Task " + threadNumber + " running in: " + Thread.currentThread());
                });
            }
        }
        System.out.println("All tasks submitted!");
    }
}
