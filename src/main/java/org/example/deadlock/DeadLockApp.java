package org.example.deadlock;

import lombok.SneakyThrows;
import org.example.Stats;

import java.util.concurrent.TimeUnit;

public class DeadLockApp {

    @SneakyThrows
    public static void main(String[] args) {
        var s1 = new Stats();
        var s2 = new Stats();
        var t1 = new Thread(() -> {
            synchronized (s1) {
                sleep();
                s2.increment();
                System.out.println("Incremented for s2");
            }
        });
        var t2 = new Thread(() -> {
            synchronized (s2) {
                s1.increment();
                System.out.println("Incremented for s1");
            }
        });

        var statePrinter = new Thread(()-> {
            while (true) {
                System.out.println(t1.getState());
                System.out.println(t2.getState());
            }
        });
        //statePrinter.start();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("s1 counter: "+s1.counter());
        System.out.println("s2 counter: "+s2.counter());
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
