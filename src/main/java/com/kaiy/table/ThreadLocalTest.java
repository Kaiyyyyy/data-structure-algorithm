package com.kaiy.table;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
        new Thread(() -> {
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(threadLocal2.get());
        }).start();
    }

    Runnable run = () -> {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set(Thread.currentThread().getName());
    };

}
