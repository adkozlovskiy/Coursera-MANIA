package com.example.practicethree.services;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class AsyncLoader extends AsyncTask<Void, Void, Void> {

    private final WeakReference<TextView> textViewWeakReference;
    private final WeakReference<ProgressBar> progressBarWeakReference;
    private final WeakReference<Button> buttonWeakReference;

    public AsyncLoader(TextView textView, ProgressBar progressBar, Button button) {
        this.textViewWeakReference = new WeakReference<>(textView);
        this.progressBarWeakReference = new WeakReference<>(progressBar);
        this.buttonWeakReference = new WeakReference<>(button);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Button button = buttonWeakReference.get();
        button.setEnabled(false);

        ProgressBar progressBar = progressBarWeakReference.get();
        progressBar.setVisibility(View.VISIBLE);

        TextView textView = textViewWeakReference.get();
        textView.setText("Loading...");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Button button = buttonWeakReference.get();
        button.setEnabled(true);

        ProgressBar progressBar = progressBarWeakReference.get();
        progressBar.setVisibility(View.GONE);

        TextView textView = textViewWeakReference.get();
        textView.setText("Ready");
    }
}
