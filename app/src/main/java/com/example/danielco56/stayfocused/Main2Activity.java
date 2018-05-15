package com.example.danielco56.stayfocused;

import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static com.example.danielco56.stayfocused.Calcularea_alcolemiei.rezultat;

public class Main2Activity extends AppCompatActivity {


    private Calcularea_alcolemiei calculator=new Calcularea_alcolemiei();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView lst = (ListView)findViewById(R.id.lista);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.000000");

        calculator.alcolemie();

        String ss = decimalFormat.format(rezultat);

        Log.v(ss,"asdasdasdasdasdasdasd");
        HashMap<String, String> alcolemie = new HashMap<>();
        alcolemie.put("13/Mai/2018 ", ss );


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

    }


}
