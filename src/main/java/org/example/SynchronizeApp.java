package org.example;

public class SynchronizeApp {

    public static void main(String[] args) throws InterruptedException {
        var stats = new Stats();
        Runnable incrementLogic = () -> {
            for (int i = 0; i < 10_000; i++) {
                stats.increment();
            }
        };

        var t1 = new Thread(incrementLogic);
        var t2 = new Thread(incrementLogic);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(stats.counter());
    }
}
