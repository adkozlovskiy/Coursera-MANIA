package com.attestation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PerfectSolution {

    public static void main(String[] args) throws Exception {
        System.out.println(countLevels());
    }

    private static final int levelCount = 10, threadCount = 3;
    private static ReentrantLock lock = new ReentrantLock();
    private static int allCount = 0;

    private static Integer countLevels() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        Future<Boolean> future = null;
        for (int j = 0; j < levelCount; j++) {
            future = service.submit(() -> {
                for (int i = 0; i < 5; i++) {
                    lock.lock();
                    try {
                        int val = allCount;
                        val++;
                        allCount = val;
                    } finally {
                        lock.unlock();
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                return true;
            });
        }
        while (!future.isDone()) {
            TimeUnit.MILLISECONDS.sleep(100);
        }
        return allCount;
    }
}
