package com.example.practicefive;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS_CODE = 39;
    private DownloadManager downloadManager;
    private Button downloadButton;
    private ImageView imageView;
    private EditText editText;
    private Button insertButton;
    BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            insertButton.setEnabled(true);
            downloadButton.setEnabled(false);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(downloadReceiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);
        downloadButton = findViewById(R.id.download_button);
        insertButton = findViewById(R.id.insert_button);

        if (!isPermissionGranted()) {
            requestPermission();
        }

        downloadButton.setOnClickListener(view -> {
            String url = editText.getText().toString();
            if (url.isEmpty()) {
                Toast.makeText(this, "URL is empty.", Toast.LENGTH_SHORT).show();

            } else if (!url.endsWith(".jpeg") && !url.endsWith(".png") && !url.endsWith(".bmp")) {
                Toast.makeText(this, "File you want download is not an image.", Toast.LENGTH_SHORT).show();

            } else {
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

                Downloader downloader = new Downloader(url, downloadManager);
                downloader.execute();
            }
        });

        insertButton.setOnClickListener(view -> {
            File imgFile = new File(Environment.DIRECTORY_DOWNLOADS, "Image.png");
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);

        });

    }

    void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE);
    }

    boolean isPermissionGranted() {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(downloadReceiver);
    }
}