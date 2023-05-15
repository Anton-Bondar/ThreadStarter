package org.example;

import java.util.concurrent.TimeUnit;

public class Main {
    private static boolean stopRequested;

    private static synchronized void stopRequested() {
        stopRequested = true;
    }

    private static synchronized boolean requestStop() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!requestStop()) i++;
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested();
    }
}