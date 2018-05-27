package com.example.danielco56.stayfocused;

import android.content.Intent;
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

import static com.example.danielco56.stayfocused.Calcularea_alcolemiei.rezultat;
import static com.example.danielco56.stayfocused.Controller.bauturi;

public class Main2Activity extends AppCompatActivity {

    public static String data;
    public static String ss;


    private Calcularea_alcolemiei calculator=new Calcularea_alcolemiei();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView lst = (ListView)findViewById(R.id.lista);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.000000");

        //calculator alcolemie si transfomrare in string
        calculator.alcolemie();
        ss = decimalFormat.format(rezultat);

        //data formatare si transformare in string
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentTime = Calendar.getInstance().getTime();
        String data = df.format(currentTime);

        //introducere date in hashmap
        HashMap<String, String> alcolemie = new HashMap<>();
        alcolemie.put(data, ss );


        List<HashMap<String,String>> listItems = new ArrayList<>()
                ;
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,R.layout.list_item,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.txtitem,R.id.txtitem2});

        Iterator it = alcolemie.entrySet().iterator();
        while(it.hasNext())
        {
            HashMap<String,String> resultMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultMap.put("First Line",pair.getKey().toString());
            resultMap.put("Second Line",pair.getValue().toString());
            listItems.add(resultMap);
        }

        lst.setAdapter(adapter);

        ImageView bt2 = (ImageView) findViewById(R.id.backButton);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Main2Activity.this,Controller.class);
                startActivity(next);
            }
        });



    }



}
