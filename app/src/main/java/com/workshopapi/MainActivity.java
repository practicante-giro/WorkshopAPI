package com.workshopapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.workshopapi.Adapters.FilmsAdapter;
import com.workshopapi.Models.Film;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FilmsAdapter adapter;
    List<Film> filmList = new ArrayList<>();
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //adapter = new FilmsAdapter(this,filmList);
        //recyclerView.setAdapter(adapter);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://swapi.co/api/films/?format=json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("No se pudo porque",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                JsonArray jsonArray;
                JsonObject jsonObject = new Gson().fromJson(json,(Type)JsonObject.class);
                jsonArray = jsonObject.getAsJsonArray("results");

                Gson gson = new GsonBuilder().create();
                Type list = new TypeToken<List<Film>>(){}.getType();
                filmList = gson.fromJson(jsonArray.toString(),list);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new FilmsAdapter(getApplicationContext(),filmList);
                        recyclerView.setAdapter(adapter);

                    }
                });
            }
        });

        update();




    }

    public void update(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new FilmsAdapter(getApplicationContext(),filmList);
                adapter.notifyDataSetChanged();

            }
        });
    }

 }
