package com.example.networking;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";
    private Mountain[] mountains;
    Gson gson = new Gson();
    ArrayList<Mountain> items = new ArrayList<>();

    @SuppressWarnings("SameParameterValue")
    private String readFile(String mountains) {
        try {
            //noinspection CharsetObjectCanBeUsed
            return new Scanner(getApplicationContext().getAssets().open(mountains), Charset.forName("UTF-8").name()).useDelimiter("\\A").next();
        } catch (IOException e) {

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Mountain item) {
                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        String s = readFile("mountains.json");
        Log.d("MainActivity","The following text was found in textfile:\n\n"+s);


        mountains = gson.fromJson(s, Mountain[].class);

        for (int i = 0; i < mountains.length; i++) {
            Log.d("mainActivity ==>","Hittade ett berg " +mountains[i].getName() + " " + mountains[i].getAuxdata().getWiki());
        }


        new JsonFile(this, this).execute(JSON_FILE);
        /*new JsonTask(this).execute(JSON_URL);*/
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Type type = new TypeToken<List<Mountain>>() {}.getType();
        List<Mountain> listOfMountains = gson.fromJson(json, type);
        items.addAll(listOfMountains);
    }

}