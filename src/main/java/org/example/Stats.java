package org.example;

import java.util.concurrent.atomic.AtomicLong;

public class Stats {
    private AtomicLong counter = new AtomicLong(0L);

    public void increment() {
       counter.getAndAdd(1L);
    }

    public long counter() {
        return counter.get();
    }
}
