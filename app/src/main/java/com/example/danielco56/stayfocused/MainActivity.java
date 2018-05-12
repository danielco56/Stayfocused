package com.example.danielco56.stayfocused;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import static com.example.danielco56.stayfocused.FirstPage.GGGreutate;
import static com.example.danielco56.stayfocused.FirstPage.VVVarsta;

public class MainActivity extends AppCompatActivity {

    Button nextB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextB = (Button) findViewById(R.id.nextB);


        Boolean isFirstRun = getSharedPreferences("Preference", MODE_PRIVATE).getBoolean("isfirstrun", true);

        if (isFirstRun) {
            showStartDialog();
        }

        //     Calcularea_alcolemiei bere = new Calcularea_alcolemiei(500, 5, GGGreutate, 2);
        //     Calcularea_alcolemiei vin = new Calcularea_alcolemiei(1000, 13, 76, 2);
        //    Calcularea_alcolemiei spirtoase = new Calcularea_alcolemiei(100, 50, 76, 2);

        //     double rezult1 = bere.alcolemie();
        //     double rezult2 = vin.alcolemie();
        //     double rezult3 = spirtoase.alcolemie();

        //   TextView a = (TextView) findViewById(R.id.hhh);
        //     a.setText(Double.toString(rezult1));

        //    TextView b = (TextView) findViewById(R.id.yyy);
        //    b.setText(Double.toString(rezult2));

        //    TextView c = (TextView) findViewById(R.id.zzz);
        //    c.setText(Double.toString(rezult3));

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Controller.class);
                startActivity(intent);
            }
        });
    }

    private void showStartDialog() {
        SharedPreferences prefs = getSharedPreferences("Preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Intent firstActivity = new Intent(MainActivity.this, FirstPage.class);
        startActivity(firstActivity);

        editor.putBoolean("isfirstrun", false);
        editor.apply();
    }


}

