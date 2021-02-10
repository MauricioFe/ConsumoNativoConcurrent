package com.mauriciofe.github.io.testeasyncrequest;

import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ConfigConsumeApi {
    //Roda baseado no número de threads do processador
    //private final static ExecutorService executorService = Executors.newFixedThreadPool(1);
    //Roda síncrono
    //private final static ExecutorService executorService = Executors.newSingleThreadExecutor();
    //Roda assíncrono
    private final static ExecutorService executorService = Executors.newWorkStealingPool();

    private final static Handler handler = new Handler(Looper.getMainLooper());

    public static void requestApi(RepositoryCallback<String> callback, String uri) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String result = getRequest(uri);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onComplete(result);
                    }
                });
            }
        });
    }

    public static String getRequest(String uri) {
        BufferedReader reader;
        try {
            URL url = new URL(uri);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/json");

            reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
