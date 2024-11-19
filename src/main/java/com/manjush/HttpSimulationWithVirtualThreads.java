package com.manjush;

import java.util.concurrent.Executors;

public class HttpSimulationWithVirtualThreads {
    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 3; i++) {
                int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " started on " + Thread.currentThread());
                    simulateHttpCall(taskId);
                    System.out.println("Task " + taskId + " finished on " + Thread.currentThread());
                });
            }
        }

        System.out.println("All tasks submitted!");
    }

    private static void simulateHttpCall(int taskId) {
        try {
            Thread.sleep(2000);
            System.out.println("Task " + taskId + " got HTTP response!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Task " + taskId + " interrupted.");
        }
    }
}

