package com.example.practicefive;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class Downloader extends AsyncTask<Void, Void, Void> {

    private final String url;
    private final DownloadManager downloadManager;

    public Downloader(String url, DownloadManager downloadManager) {
        this.url = url;
        this.downloadManager = downloadManager;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Downloading");
        request.setDescription("Downloading " + "Image" + ".png");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Image" + ".png");
        downloadManager.enqueue(request);

        return null;
    }

}
