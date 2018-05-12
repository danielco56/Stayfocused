package com.example.danielco56.stayfocused;

import android.app.Activity;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Controller extends AppCompatActivity {

    private Button stopButton, beerButton, wineButton, alcButton;
    private Chronometer cronometru;
    private TextView textView;
    private ArrayList<Alcool> bauturi = new ArrayList<Alcool>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        stopButton = (Button) findViewById(R.id.stopButton);
        beerButton = (Button) findViewById(R.id.bereButton);
        wineButton = (Button) findViewById(R.id.vinButton);
        alcButton = (Button) findViewById(R.id.alcButton);
        cronometru = (Chronometer) findViewById(R.id.cron);
        textView = (TextView) findViewById(R.id.testV);

        cronometru.setBase(SystemClock.elapsedRealtime());
        cronometru.start();
        textView.setText("Pana acum ai consumat: 0 bauturi alcoolice");

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("dasfdasda");
                cronometru.stop();
                int elapsedMillis = (int) (SystemClock.elapsedRealtime() - cronometru.getBase()); // milisecunde
                int seconds = (int) (elapsedMillis / 1000) % 60;
                int minutes = (int) ((elapsedMillis / (1000 * 60)) % 60);
                int hours = (int) ((elapsedMillis / (1000 * 60 * 60)) % 24);
                //   textView.setText(String.valueOf(String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds)));


            }
        });

        beerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bauturi.add(new Alcool(500, 5));
                Toast.makeText(getApplicationContext(), "Bere adaugata!", Toast.LENGTH_SHORT).show();
                textView.setText("Pana acum ai consumat: " + bauturi.size() + " bauturi alcoolice!");

            }
        });

        wineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bauturi.add(new Alcool(120, 13));
                Toast.makeText(getApplicationContext(), "Vin adaugat!", Toast.LENGTH_SHORT).show();
                textView.setText("Pana acum ai consumat: " + bauturi.size() + " bauturi alcoolice!");
            }
        });

        alcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bauturi.add(new Alcool(50, 40));
                Toast.makeText(getApplicationContext(), "Bautura adaugata!", Toast.LENGTH_SHORT).show();
                textView.setText("Pana acum ai consumat: " + bauturi.size() + " bauturi alcoolice!");
            }
        });


    }


}
