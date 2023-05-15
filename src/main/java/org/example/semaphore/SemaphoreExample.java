package org.example.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String args[]) throws InterruptedException {
        // Створення симафору з початковою кількістю 2
        Semaphore semaphore = new Semaphore(2);

        // Створення потоків
        Thread thread1 = new Thread(new MyThread(semaphore, "Thread-1"));
        Thread thread2 = new Thread(new MyThread(semaphore, "Thread-2"));
        Thread thread3 = new Thread(new MyThread(semaphore, "Thread-3"));
        Thread thread4 = new Thread(new MyThread(semaphore, "Thread-4"));

        // Запуск потоків
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // Очікування завершення потоків
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }
}

class MyThread implements Runnable {
    Semaphore semaphore;
    String name;

    MyThread(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    public void run() {
        try {
            System.out.println(name + " is waiting for a permit.");
            semaphore.acquire();
            System.out.println(name + " gets a permit.");

            // Симуляція роботи потоку
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(name + " is working.");
            }

            // Звільнення симафору
            semaphore.release();
            System.out.println(name + " releases the permit.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}