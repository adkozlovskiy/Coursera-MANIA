package com.attestation;

import java.util.concurrent.*;

public class Solution {

    public static void main (String[] args){
        int floors = 6, threads = 5;
        int count = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < floors; i++) {
            Future future = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int result = 0;
                    for (int y = 0; y < 100; y++) {
                        result++;
                    }
                    return result;
                }
            });

            try {
                count += (int) future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(count);
    }
}