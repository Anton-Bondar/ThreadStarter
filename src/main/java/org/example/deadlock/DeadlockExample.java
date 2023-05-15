package org.example.deadlock;

public class DeadlockExample {
   public static Object lock1 = new Object();
   public static Object lock2 = new Object();
   
   public static void main(String[] args) {
      Thread t1 = new Thread(() -> {
         synchronized (lock1) {
            System.out.println("Thread 1: Holding lock 1...");
            try {
               Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.out.println("Thread 1: Waiting for lock 2...");
            synchronized (lock2) {
               System.out.println("Thread 1: Holding lock 1 and 2...");
            }
         }
      });

      Thread t2 = new Thread(() -> {
         synchronized (lock2) {
            System.out.println("Thread 2: Holding lock 2...");
            try {
               Thread.sleep(10);
            } catch (InterruptedException e) {}
            System.out.println("Thread 2: Waiting for lock 1...");
            synchronized (lock1) {
               System.out.println("Thread 2: Holding lock 1 and 2...");
            }
         }
      });

      t1.start();
      t2.start();
   }
}
