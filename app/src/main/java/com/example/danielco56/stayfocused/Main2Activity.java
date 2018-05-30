package com.example.danielco56.stayfocused;

import android.content.Intent;
import android.content.SharedPreferences;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.danielco56.stayfocused.Controller.bauturi;

public class Main2Activity extends AppCompatActivity {

    private ArrayList<Inregistrare> inregistrari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        loadData();

        PersonalAdapter adapter = new PersonalAdapter(this, R.layout.list_item, inregistrari);
        ListView list = (ListView) findViewById(R.id.lista);
        list.setAdapter(adapter);

        ImageView bt2 = (ImageView) findViewById(R.id.backButton);

        bt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Statistics.class);
                startActivity(intent);
            }
        });


    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataBase", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("lista", null);
        Type type = new TypeToken<ArrayList<Inregistrare>>() {
        }.getType();
        inregistrari = gson.fromJson(json, type);
        if (inregistrari == null) {
            inregistrari = new ArrayList<>();
        }
    }


}
