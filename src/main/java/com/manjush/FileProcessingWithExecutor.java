package com.manjush;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import java.util.concurrent.Executors;

public class FileProcessingWithExecutor {
    public static void main(String[] args) {
        // List of resource file names
        List<String> resourceFiles = List.of("file1.txt", "file2.txt", "file3.txt");

        // Create an executor for Virtual Threads
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            resourceFiles.forEach(resource -> executor.submit(() -> {
                try (var inputStream = FileProcessingWithExecutor.class.getClassLoader().getResourceAsStream(resource)) {
                    if (inputStream == null) {
                        System.err.println("Error: File " + resource + " not found.");
                        return;
                    }
                    try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        String content = reader.lines().collect(Collectors.joining("\n"));
                        System.out.println("Read from " + resource + ": " + content);
                    }
                } catch (Exception e) {
                    System.err.println("Error processing file " + resource + ": " + e.getMessage());
                }
            }));
        } // Executor automatically shuts down when exiting try-with-resources

        System.out.println("All file reading tasks submitted!");
    }
}
