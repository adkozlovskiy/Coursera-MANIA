package com.example.practicetwo.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainReceiver extends BroadcastReceiver {

    private final WeakReference<ProgressBar> progressBarWeakReference;
    private final WeakReference<Button> buttonWeakReference;

    public static final String ACTION = BroadcastReceiver.class.getPackage().getName();

    public MainReceiver(ProgressBar progressBar, Button button) {
        this.progressBarWeakReference = new WeakReference<>(progressBar);
        this.buttonWeakReference = new WeakReference<>(button);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ProgressBar progressBar = progressBarWeakReference.get();
        Button button = buttonWeakReference.get();

        if (progressBar.getProgress() == 100) {
            Toast.makeText(context, "Loading completed!", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setProgress(progressBar.getProgress() + 5);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progressBar.getProgress() >= 50) {
                    progressBar.setProgress(progressBar.getProgress() - 50);
                } else progressBar.setProgress(0);
            }
        });
    }
}
