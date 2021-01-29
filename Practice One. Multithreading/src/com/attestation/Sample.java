package com.attestation;

import java.util.concurrent.TimeUnit;

public class Sample {

    private static int mCount = 0;

    public static void main(String[] args) {
        for (int x = 0; x < 10; x++) {
            new Thread(() -> {
                for (int y = 0; y < 100; y++) {
                    mCount++;
                    System.out.println(mCount + " " + Thread.currentThread().getName());
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
