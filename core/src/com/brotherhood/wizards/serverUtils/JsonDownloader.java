package com.brotherhood.wizards.serverUtils;

import com.badlogic.gdx.utils.async.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Wojciech Osak on 2015-09-05.
 */
public class JsonDownloader implements AsyncTask {
    private String jsonDownloaded;
    private boolean isDownloading = false;
    private String url;
    private JsonDownloaderListener jsonDownloaderListener;


    public void setJsonDownloaderListener(JsonDownloaderListener jsonDownloaderListener) {
        this.jsonDownloaderListener = jsonDownloaderListener;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public String getJsonDownloaded() {
        return jsonDownloaded;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String call() throws Exception {
        isDownloading = true;
        try{
            URL url = new URL(this.url);
            HttpURLConnection httpURLConnection  = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type","0");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.connect();
            int status = httpURLConnection.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    jsonDownloaded = sb.toString();
            }
        } catch (IOException e) {
            if(jsonDownloaderListener!=null)
                jsonDownloaderListener.onError();
            e.printStackTrace();
        }
        if(jsonDownloaderListener!=null)
            jsonDownloaderListener.onLoadFinished(jsonDownloaded);
        return jsonDownloaded;
    }

    public interface JsonDownloaderListener{
        void onLoadFinished(String json);
        void onError();
    }
}
