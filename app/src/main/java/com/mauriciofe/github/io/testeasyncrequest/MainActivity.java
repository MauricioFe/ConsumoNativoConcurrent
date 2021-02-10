package com.mauriciofe.github.io.testeasyncrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Bacon", "onCreate: "+Runtime.getRuntime().availableProcessors());
        TextView textView = findViewById(R.id.txtTeste);
        ConfigConsumeApi.requestApi(new RepositoryCallback<String>() {
                            @Override
                            public void onComplete(String result) {
                                Log.d("bacon", result.toString());
                            }
                        },
                "https://pokeapi.co/api/v2/pokemon?limit=1000&offset=200"
        );
        ConfigConsumeApi.requestApi(new RepositoryCallback<String>() {
                            @Override
                            public void onComplete(String result) {
                                Log.d("bacon", result.toString());
                            }
                        },
                "https://jsonplaceholder.typicode.com/todos/2"
        );
    }
}

