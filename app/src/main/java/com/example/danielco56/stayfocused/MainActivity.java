package com.example.danielco56.stayfocused;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button stopButton;
    private Chronometer cronometru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stopButton = (Button) findViewById(R.id.stopButton);
        cronometru= (Chronometer) findViewById (R.id.cron);

    }
}
