package com.example.practicetwo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainService extends Service {

    private final IBinder binder = new LocalBinder();
    private ScheduledExecutorService scheduledExecutorService;

    public class LocalBinder extends Binder {
        public MainService getService() {
            return MainService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Intent sendIntent = new Intent(MainReceiver.ACTION);
                sendBroadcast(sendIntent);
            }
        }, 0, 200, TimeUnit.MILLISECONDS);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        scheduledExecutorService.shutdown();
        return super.onUnbind(intent);
    }
}
