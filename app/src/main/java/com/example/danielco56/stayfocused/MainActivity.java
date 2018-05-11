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

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean isFirstRun = getSharedPreferences("Preference", MODE_PRIVATE).getBoolean("isfirstrun",true);

        if(isFirstRun){
            showStartDialog();
        }

    }

    private void showStartDialog() {
        Intent firstActivity = new Intent(MainActivity.this,FirstPage.class);
        startActivity(firstActivity);

        SharedPreferences prefs = getSharedPreferences("Preference",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isfirstrun",false);
        editor.apply();
    }




}

